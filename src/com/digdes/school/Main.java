package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws RequestException {
        JavaSchoolStarter table = new JavaSchoolStarter();
        table.execute("INSERT VALUES 'id'=1 , 'lastname'='Pablo', 'age'=22, 'active' = False , 'cost'=1.2");
        table.execute("Update values 'id'=2 , 'lastname'='Like' where 'lastname' like 'Pablo'");
        System.out.println(table.execute("Select "));
        System.out.println(table.execute("DELETE"));
        System.out.println(table.execute("Select "));
        /*System.out.println(table.convertSetExpression("'lastName' = 'Pablo' AND 'id' = 1 Or  'lastname'  like  '%or'"));*/
    }
}
