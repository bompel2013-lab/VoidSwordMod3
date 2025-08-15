package com.example.voidsword;

import com.example.voidsword.registry.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VoidSwordMod.MOD_ID)
public class VoidSwordMod {
    public static final String MOD_ID = "voidsword";

    public VoidSwordMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
    }
}
