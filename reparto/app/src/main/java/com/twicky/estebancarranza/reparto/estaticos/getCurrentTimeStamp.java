package com.twicky.estebancarranza.reparto.estaticos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by esteban.carranza on 19/05/2018.
 */

public class getCurrentTimeStamp {
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
