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

        return "force | set <configvar> <value>";
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
            } else if(args[0].equalsIgnoreCase("stop")){
                Sjaman.stopRain(sender.getEntityWorld());
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Oeba oeba oeba *dances*"));
            }
        }else if(args.length == 3){
            if(args[0].equalsIgnoreCase("set")){
                switch(args[1].toLowerCase()){
                    case "minrainlesstime":
                    case "maxrainlesstime":
                    case "minraintime":
                    case "maxraintime":
                        RsConfig.INSTANCE.setInt(args[1], Integer.valueOf(args[2]));
                        sender.addChatMessage(new ChatComponentText("Saved!"));
                        break;
                    case "thunderstormchance":
                        RsConfig.INSTANCE.setDouble(args[1], Double.valueOf(args[2]));
                        sender.addChatMessage(new ChatComponentText("Saved!"));
                        break;
                    case "alwaysraining":
                    case "neverraining":
                        RsConfig.INSTANCE.setBool(args[1], (args[2].equalsIgnoreCase("on") || args[2].equalsIgnoreCase("true")));
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
        if(args[0].equalsIgnoreCase("")){
            ret.add("force");
            ret.add("stop");
            ret.add("version");
            ret.add("set");
        }else if(args.length == 2){
            if(args[0].equalsIgnoreCase("set")){
                ret.add("minRainlessTime");
                ret.add("maxRainlessTime");
                ret.add("minRainTime");
                ret.add("maxRainTime");
                ret.add("thunderstormChance");
                ret.add("alwaysRaining");
                ret.add("neverRaining");
            }
        }
        return ret;
    }
}
