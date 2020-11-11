package novamachina.exnihilosequentia;

import novamachina.exnihilosequentia.client.setup.ClientSetup;
import novamachina.exnihilosequentia.common.init.ModInitialization;
import novamachina.exnihilosequentia.common.loot.modifier.UseHammerModifier;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(Constants.ModIds.EX_NIHILO_SEQUENTIA)
public class ExNihiloSequentia {

    public ExNihiloSequentia() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        ModInitialization.init(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModInitialization::setupNonTagBasedRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModInitialization::registerTOP);
        MinecraftForge.EVENT_BUS.addListener(ModInitialization::addReloadListeners);
    }

    @EventBusSubscriber(modid = Constants.ModIds.EX_NIHILO_SEQUENTIA, bus = Bus.MOD)
    public static class EventHandlers {

        @SubscribeEvent
        public static void registerModifierSerializers(
            @Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            event.getRegistry()
                .register(new UseHammerModifier.Serializer()
                    .setRegistryName(Constants.ModIds.EX_NIHILO_SEQUENTIA, "use_hammer"));
        }
    }
}
