package com.example.runway_project;

import java.util.Calendar;

public class Gen {
    private int idI = 1897;
    private String idS = "SitG";
    private String idS1 = "kDg";
    private String idS2 = "HB";

    void setIds() {
        Calendar getday = Calendar.getInstance();
        int day = getday.get(Calendar.DAY_OF_WEEK);

        if (day == 1) {
            idI = 23;
            idS = "fL";
            idS1 = "yHu";
            idS2 = "vB";
        } else if (day == 2) {
            idI = 56;
            idS = "uJd";
        } else if (day == 3) {
            idI = 34;
            idS = "PkM";
            idS1 = "tLo";
            idS2 = "qR";
        } else if (day == 4) {
            idI = 76;
            idS = "IuY";
            idS1 = "xL";
            idS2 = "Wtr";
        } else if (day == 5) {
            idI = 802;
            idS = "PeO";
            idS1 = "aQ";
            idS2 = "EVf";
        } else if (day == 6) {
            idI = 403;
            idS = "Kn";
            idS1 = "ui";
            idS2 = "Mb";
        } else if (day == 7) {
            idI = 261;
            idS = "sWW";
            idS1 = "gYb";
            idS2 = "cD";
        }
    }


    String computeGen(){
        int randNo =  (int) Math.floor(Math.random()*(81-0+1)+1);
        int randNo1 =  (int) Math.floor(Math.random()*(35-0+1)+1);
        int randNo2 =  (int) Math.floor(Math.random()*(41-0+1)+1);
        String genString = ((idS2 + randNo) + (idS1 +randNo2)) + idI;
//        System.out.println("genString " + genString);
        return genString;

    }


}
