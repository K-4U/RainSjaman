package k4unl.minecraft.rainSjaman.lib;

import k4unl.minecraft.rainSjaman.lib.config.ModInfo;
import k4unl.minecraft.rainSjaman.lib.config.RsConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(ModInfo.ID);

    public static void init() {

        logger.log(Level.INFO, ModInfo.NAME + " starting up!");
    }

    public static void info(String message) {

        logger.log(Level.INFO, message);
    }

    public static void error(String message) {

        logger.log(Level.ERROR, message);
	}
	
	public static void warning(String message){
		logger.log(Level.WARN, message);
	}


    public static void debug(String message){
        if(RsConfig.INSTANCE.getBool("debug")){
            logger.log(Level.INFO, message);
        }
    }
}
