package com.digdes.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    Table table;
    public JavaSchoolStarter(){
        table = new Table();
    }

    public List<Map<String,Object>> execute(String request) throws RequestException {
        return table.executeRequest(request);
    }

}