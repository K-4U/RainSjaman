package k4unl.minecraft.tmr.lib.config;

import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.ConfigOption;

public class TmrConfig extends Config {

    public static final TmrConfig INSTANCE = new TmrConfig();

    public void init() {

        super.init();
        configOptions.add(new ConfigOption("minRainlessTime", 10*60).setComment("How many seconds should there minimally be between rain storms?"));
        configOptions.add(new ConfigOption("maxRainlessTime", 150*60).setComment("How many seconds should there maximally be between rain storms?"));
        configOptions.add(new ConfigOption("minRainTime", 10*60).setComment("What's the minimum time a rain storm lasts?"));
        configOptions.add(new ConfigOption("maxRainTime", 20*60).setComment("What's the maximum time a rain storm lasts?"));
        configOptions.add(new ConfigOption("thunderstormChance", .8).setComment("What's the chance a rain storm will be a thunder storm?"));
    }
}
