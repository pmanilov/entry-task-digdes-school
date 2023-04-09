package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Table table = new Table();
        table.executeRequest("INSERT VALUES 'id'=null , 'lastname'='Pablo', 'age'=22, 'active' = False , 'cost'=1.2");
        table.executeRequest("Update values 'id'=2 , 'lastname'='Egor' where 'id' = 1");
        System.out.println(table.executeRequest("Select "));
        System.out.println(table.executeRequest("DELETE"));
        System.out.println(table.executeRequest("Select "));
        /*System.out.println(table.convertSetExpression("'lastName' = 'Pablo' AND 'id' = 1 Or  'lastname'  like  '%or'"));*/
        String str = "";
        System.out.println(str.split("where")[0]);
    }
}
