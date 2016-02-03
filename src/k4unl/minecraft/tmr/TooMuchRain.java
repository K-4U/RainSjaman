package k4unl.minecraft.tmr;


import k4unl.minecraft.k4lib.lib.config.ConfigHandler;
import k4unl.minecraft.tmr.commands.Commands;
import k4unl.minecraft.tmr.events.Sjaman;
import k4unl.minecraft.tmr.lib.Log;
import k4unl.minecraft.tmr.lib.config.ModInfo;
import k4unl.minecraft.tmr.lib.config.TmrConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
  modid = ModInfo.ID,
  name = ModInfo.NAME,
  version = ModInfo.VERSION,
  acceptableRemoteVersions = "*",
  dependencies = "required-after:k4lib"
)

public class TooMuchRain {
    @Mod.Instance(value = ModInfo.ID)
    public static TooMuchRain instance;

    private ConfigHandler tmrConfigHandler = new ConfigHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Sjaman.init();
        Log.init();
        TmrConfig.INSTANCE.init();
        tmrConfigHandler.init(TmrConfig.INSTANCE, event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {

        Commands.init(event);
    }
}
