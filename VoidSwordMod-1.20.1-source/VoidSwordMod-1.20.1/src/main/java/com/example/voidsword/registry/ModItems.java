package com.example.voidsword.registry;

import com.example.voidsword.VoidSwordMod;
import com.example.voidsword.items.VoidSwordItem;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VoidSwordMod.MOD_ID);

    public static final RegistryObject<Item> VOID_SWORD = ITEMS.register("void_sword", () ->
            new VoidSwordItem(Tiers.NETHERITE, 3, -2.4f, new Item.Properties().rarity(Rarity.EPIC).durability(2031).fireResistant())
    );
}
