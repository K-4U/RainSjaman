package k4unl.minecraft.rainSjaman.commands;

import k4unl.minecraft.k4lib.commands.CommandK4Base;
import k4unl.minecraft.rainSjaman.events.Sjaman;
import k4unl.minecraft.rainSjaman.lib.config.ModInfo;
import k4unl.minecraft.rainSjaman.lib.config.RsConfig;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class CommandRainSjaman extends CommandK4Base {

    public CommandRainSjaman() {

        aliases.add("rs");
    }

    @Override
    public String getCommandName() {

        return "rainsjaman";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {

        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("force")){
                //Forces rain
                Sjaman.forceRain(sender.getEntityWorld());
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Oeba oeba oeba *dances*"));
            } else if(args[0].equalsIgnoreCase("version")){
                sender.addChatMessage(new ChatComponentText("RainSjaman V" + ModInfo.VERSION));
            }
        }else if(args.length == 3){
            if(args[0].equalsIgnoreCase("set")){
                switch(args[1]){
                    case "minRainlessTime":
                    case "maxRainlessTime":
                    case "minRainTime":
                    case "maxRainTime":
                        RsConfig.INSTANCE.setInt(args[1], Integer.valueOf(args[2]));
                        sender.addChatMessage(new ChatComponentText("Saved!"));
                        break;
                    case "thunderstormChance":
                        RsConfig.INSTANCE.setDouble(args[1], Double.valueOf(args[2]));
                        sender.addChatMessage(new ChatComponentText("Saved!"));
                        break;
                    default:
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This option is not available"));
                }
            }
        }
    }


    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {

        List<String> ret = new ArrayList<String>();

        return ret;
    }
}
