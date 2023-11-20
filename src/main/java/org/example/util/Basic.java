package org.example.util;

import java.util.Random;

public class Basic {

    public  static int getRandom(){

        Random r = new Random();
        int low = 1;
        int high = 100;
        int result = r.nextInt(high-low) + low;
        return result;

    }

}
