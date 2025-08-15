package com.example.voidsword.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class VoidSwordItem extends SwordItem {
    public VoidSwordItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isShiftKeyDown()) {
            if (level instanceof ServerLevel serverLevel) {
                // Cooldown: 10 seconds
                if (player.getCooldowns().isOnCooldown(this)) {
                    return InteractionResultHolder.pass(stack);
                }

                ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                int minY = serverLevel.getMinBuildHeight();
                int maxY = serverLevel.getMaxBuildHeight();

                BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
                // Clear the entire chunk to air
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = minY; y < maxY; y++) {
                            mpos.set((chunkPos.x << 4) + x, y, (chunkPos.z << 4) + z);
                            serverLevel.setBlock(mpos, Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }

                // Small harmless "pop" feedback and cooldown + durability hit
                player.getCooldowns().addCooldown(this, 20 * 10);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                player.sendSystemMessage(Component.literal("Chunk (" + chunkPos.x + ", " + chunkPos.z + ") erased.")
                        .withStyle(ChatFormatting.DARK_PURPLE));
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, java.util.List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Shift + Right-Click: Delete current chunk").withStyle(ChatFormatting.LIGHT_PURPLE));
        tooltip.add(Component.literal("10s cooldown • -1 durability").withStyle(ChatFormatting.DARK_GRAY));
    }
}
