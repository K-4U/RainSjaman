package k4unl.minecraft.tmr.events;

import k4unl.minecraft.tmr.lib.Log;
import k4unl.minecraft.tmr.lib.config.TmrConfig;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

/**
 * @author Koen Beckers (K-4U)
 */
public class Sjaman {

    public static void init() {

        MinecraftForge.EVENT_BUS.register(new Sjaman());
    }


    private boolean isRaining        = false;
    private boolean isWaitingForRain = false;
    private int     rainTicks        = 0;
    private int     rainMaxTicks     = 0;

    @SubscribeEvent
    public void tickWorld(TickEvent.WorldTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            World world = event.world;
            if (world.provider.getDimensionId() != 0) {
                return;
            }
            if (!isWaitingForRain && !world.isRaining() && !isRaining) {
                //Not waiting, not raining, wait what?
                //First startup, I could save all of this to NBT but.. meh
                Random rand = new Random();
                int nextRainTicks = (rand.nextInt(TmrConfig.INSTANCE.getInt("maxRainlessTime") - TmrConfig.INSTANCE.getInt("minRainlessTime")) +
                  TmrConfig.INSTANCE.getInt("minRainlessTime")) * 20;
                Log.debug("Waiting on rain, which should arrive in " + nextRainTicks + " ticks");
                world.getWorldInfo().setRainTime(nextRainTicks);
                isWaitingForRain = true;
            } else if (isWaitingForRain && world.isRaining() && !isRaining) {
                //The rain has started!
                Random rand = new Random();
                rainMaxTicks = (rand.nextInt(TmrConfig.INSTANCE.getInt("maxRainTime") - TmrConfig.INSTANCE.getInt("minRainTime")) +
                  TmrConfig.INSTANCE.getInt("minRainTime")) * 20;
                rainTicks = 0;
                Log.debug("Rain started! It should last " + rainMaxTicks + " ticks");

                boolean shouldThunder = rand.nextDouble() >= TmrConfig.INSTANCE.getDouble("thunderstormChance");
                world.getWorldInfo().setThundering(shouldThunder);
                if (shouldThunder) {
                    Log.debug("Oh, and it's a thunder storm!");
                }

                isRaining = true;
                isWaitingForRain = false;
            } else if (!isWaitingForRain && world.isRaining() && isRaining) {
                //Rain is ongoing:
                rainTicks++;
                if (rainTicks >= rainMaxTicks) {
                    world.getWorldInfo().setRaining(false);
                    isRaining = false;
                    Log.debug("Rain force-stopped after " + rainTicks + " ticks");
                }
            } else if (!isWaitingForRain && !world.isRaining() && isRaining) {
                //Rain stopped too soon!
                rainTicks++;
                Log.debug("Rain stopped after " + rainTicks + " ticks");
                if (rainTicks >= rainMaxTicks) {
                    world.getWorldInfo().setRaining(false);
                    isRaining = false;
                    Log.debug("This was okay");
                } else {
                    world.getWorldInfo().setRaining(true);
                    Log.debug("This was not okay, restarting the rain!");
                }
            }
        }
    }

    public static void forceRain(World w) {

        if (w.provider.getDimensionId() != 0) {
            return;
        }
        w.getWorldInfo().setRaining(true);
    }
}
