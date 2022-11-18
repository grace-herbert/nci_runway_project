package com.example.runway_project;

public class Gen {
    private int idI = 1897;
    private String idS = "SitG";



    String computeGen(){
        Double randNo =  Math.floor(Math.random()*(2100-0+1)+1);
        Double randNo1 =  Math.floor(Math.random()*(3500-0+1)+1);
        Double randNo2 =  Math.floor(Math.random()*(4100-0+1)+1);
        String genString = randNo + randNo1 + idI + idS + randNo2;
        System.out.println("genString " + genString);
        return genString;

    }


}
