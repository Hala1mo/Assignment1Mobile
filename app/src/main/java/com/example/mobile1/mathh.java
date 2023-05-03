package com.example.mobile1;

import java.util.ArrayList;
import java.util.List;

public class mathh {
    private ArrayList<Solution> equ=new ArrayList<>();
    public mathh(){
        equ.add(new Solution("Addition","2+5= ","7"));
        equ.add(new Solution("Subtraction","27-12= ","15"));
        equ.add(new Solution("Multiplication","2*7= ","14"));
        equ.add(new Solution("Division","42/7= ","6"))
        ;
    }
    public List<String> getType(){
        ArrayList<String> data=new ArrayList<>();
        for(Solution b:equ) {
            data.add(b.getType());
        }
        return data;
    }


    public List<Solution> getEqu(){
        ArrayList<Solution> data=new ArrayList<>();
        for(Solution b:equ) {
            data.add(b);
        }
        return data;
    }



}
