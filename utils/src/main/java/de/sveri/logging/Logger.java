package de.sveri.logging;

import java.util.logging.Level;

public class Logger {

    private final java.util.logging.Logger logger;

    public static Logger getInstance(String name){
        return new Logger(java.util.logging.Logger.getLogger(name));
    }

    private Logger(java.util.logging.Logger logger){
        this.logger = logger;
    }

    public void info(String msg, Exception e){
        logger.log(Level.INFO, msg, e);
    }

    public void error(String msg){
        logger.log(Level.SEVERE, msg);
    }
}
