package k4unl.minecraft.tmr.commands;


import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Commands {
    public static void init(FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandTooMuchRain());
    }
}
