package k4unl.minecraft.rainSjaman;


import k4unl.minecraft.k4lib.lib.config.ConfigHandler;
import k4unl.minecraft.rainSjaman.commands.Commands;
import k4unl.minecraft.rainSjaman.events.Sjaman;
import k4unl.minecraft.rainSjaman.lib.Log;
import k4unl.minecraft.rainSjaman.lib.config.ModInfo;
import k4unl.minecraft.rainSjaman.lib.config.RsConfig;
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

public class RainSjaman {
    @Mod.Instance(value = ModInfo.ID)
    public static RainSjaman instance;

    private ConfigHandler rsConfigHandler = new ConfigHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Sjaman.init();
        Log.init();
        RsConfig.INSTANCE.init();
        rsConfigHandler.init(RsConfig.INSTANCE, event.getSuggestedConfigurationFile());
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
