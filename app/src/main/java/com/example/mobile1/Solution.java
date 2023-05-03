package com.example.mobile1;

public class Solution {


    String solution;
    String equation;
    String Type;

public Solution(String Type,String equation,String solution){
    this.solution=solution;
    this.Type=Type;
    this.equation=equation;
}
public String getType(){
    return Type;
}
public String getEqu(){
    return equation;
}
public String getSol(){
    return solution;
}



}
