package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        String name = "Petr";
        int age = 33;
        var cr = "C";
        double dbl = 12.5;
        String lastname = "Arsentev";
        byte b = 0;
        short sh = 32;
        boolean bool = false;
        float fl = 745.25F;
        long lg = 23000000L;
        LOG.debug("name : {}, age : {}", name, age);
        LOG.debug("cr : {}, dbl : {}", cr, dbl);
        LOG.debug("lastname : {}, lg {}", lastname, lg);
        LOG.debug("b : {}, fl : {}", b, fl);
        LOG.debug("sh : {}, bool : {}", sh, bool);
    }
}