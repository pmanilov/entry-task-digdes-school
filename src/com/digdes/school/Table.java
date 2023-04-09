package com.digdes.school;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {
    List<Map<String,Object>> db;
    public Table() {
        db = new ArrayList<>();
    }
    public List<Map<String,Object>> insert(String request) throws RuntimeException{
        String[] requestArray = request.split("\s*=\s*|\s*,\s*");
        Map<String,Object> map = new HashMap<>();
        for(int i = 0; i < requestArray.length; i+=2){
            if(requestArray[i].startsWith("'") && requestArray[i].endsWith("'")) {
                requestArray[i] = requestArray[i].replace("'","").toLowerCase();
                if(requestArray[i].equals("id") || requestArray[i].equals("age")) {
                    if(!requestArray[i+1].equals("null")) {
                        Long value = Long.parseLong(requestArray[i + 1]);
                        map.put(requestArray[i], value);
                    } else {
                        map.put(requestArray[i], null);
                    }
                } else if(requestArray[i].equals("lastname")) {
                    if(!requestArray[i+1].equals("null")) {
                        requestArray[i + 1] = requestArray[i + 1].replace("'", "");
                        map.put(requestArray[i], requestArray[i + 1]);
                    } else {
                        map.put(requestArray[i], null);
                    }
                } else if(requestArray[i].equals("cost")) {
                    if(!requestArray[i+1].equals("null")) {
                        Double value = Double.parseDouble(requestArray[i + 1]);
                        map.put(requestArray[i], value);
                    } else {
                        map.put(requestArray[i], null);
                    }
                } else if(requestArray[i].equals("active")) {
                    if(!requestArray[i+1].equals("null")) {
                        Boolean value = Boolean.parseBoolean(requestArray[i + 1].toLowerCase());
                        map.put(requestArray[i], value);
                    } else {
                        map.put(requestArray[i], null);
                    }
                } else {
                    throw new RuntimeException();
                }
            } else{
                throw new RuntimeException();
            }
        }
        db.add(map);
        List<Map<String,Object>> res = new ArrayList<>();
        res.add(map);
        return res;
    }

    public List<Map<String,Object>> update(String request) throws RuntimeException{
        String[] requestArrayWhere = request.split("\s+[Ww][Hh][Ee][Rr][Ee]\s+");
        String[] requestArrayColumns = requestArrayWhere[0].split("\s*=\s*|\s*,\s*");
        if(requestArrayWhere.length == 1){
            for(int i = 0; i < requestArrayColumns.length; i+=2){
                for(Map<String, Object> row:db){
                    if(requestArrayColumns[i].startsWith("'") && requestArrayColumns[i].endsWith("'")) {
                        requestArrayColumns[i] = requestArrayColumns[i].replace("'","").toLowerCase();
                        if(requestArrayColumns[i].equals("id") || requestArrayColumns[i].equals("age")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Long value = Long.parseLong(requestArrayColumns[i + 1]);
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("lastname")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                requestArrayColumns[i + 1] = requestArrayColumns[i + 1].replace("'", "");
                                row.put(requestArrayColumns[i], requestArrayColumns[i + 1]);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("cost")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Double value = Double.parseDouble(requestArrayColumns[i + 1]);
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("active")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Boolean value = Boolean.parseBoolean(requestArrayColumns[i + 1].toLowerCase());
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else {
                            throw new RuntimeException();
                        }
                    } else{
                        throw new RuntimeException();
                    }
                }
            }
            return db;
        }
        else{
            List<Map<String,Object>> whereList = convertSetExpression(requestArrayWhere[1]);
            for(int i = 0; i < requestArrayColumns.length; i+=2){
                for(Map<String, Object> row:whereList){
                    if(requestArrayColumns[i].startsWith("'") && requestArrayColumns[i].endsWith("'")) {
                        requestArrayColumns[i] = requestArrayColumns[i].replace("'","").toLowerCase();
                        if(requestArrayColumns[i].equals("id") || requestArrayColumns[i].equals("age")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Long value = Long.parseLong(requestArrayColumns[i + 1]);
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("lastname")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                requestArrayColumns[i + 1] = requestArrayColumns[i + 1].replace("'", "");
                                row.put(requestArrayColumns[i], requestArrayColumns[i + 1]);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("cost")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Double value = Double.parseDouble(requestArrayColumns[i + 1]);
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else if(requestArrayColumns[i].equals("active")) {
                            if(!requestArrayColumns[i+1].equals("null")) {
                                Boolean value = Boolean.parseBoolean(requestArrayColumns[i + 1].toLowerCase());
                                row.put(requestArrayColumns[i], value);
                            } else {
                                row.put(requestArrayColumns[i], null);
                            }
                        } else {
                            throw new RuntimeException();
                        }
                    } else{
                        throw new RuntimeException();
                    }
                }
            }
            return whereList;
        }
    }
    public List<Map<String,Object>> select(String request) throws RuntimeException {
        String requestWhere = request.replaceAll("[Ww][Hh][Ee][Rr][Ee]\s+", "");
        /*String[] requestArrayColumns = requestArrayWhere[0].split("\s*,\s*");*/
        if(requestWhere.equals("")){
            return db;
        } else {
            return convertSetExpression(requestWhere);
        }
    }

    public List<Map<String,Object>> delete(String request) throws RuntimeException{
        String requestWhere = request.replaceAll("[Ww][Hh][Ee][Rr][Ee]\s+", "");
        if(requestWhere.equals("")){
            List<Map<String,Object>> res = new ArrayList<>(db);
            db.clear();
            return res;
        } else {
            List<Map<String,Object>> res = new ArrayList<>(convertSetExpression(requestWhere));
            for(Map<String, Object> row: convertSetExpression(requestWhere)){
                db.remove(row);
            }
            return res;
        }
    }
    public List<Map<String,Object>> executeRequest(String request) throws RuntimeException{
        String regexInsert = "^\s*insert\s+values\s+.*$";
        String regexUpdate = "^\s*update\s+values\s+.*$";
        String regexDelete = "^\s*delete\s*.*$";
        String regexSelect = "^\s*select\s*.*$";
        Pattern patternInsert= Pattern.compile(regexInsert);
        Pattern patternUpdate = Pattern.compile(regexUpdate);
        Pattern patternDelete = Pattern.compile(regexDelete);
        Pattern patternSelect = Pattern.compile(regexSelect);
        if(patternInsert.matcher(request.toLowerCase()).find()){
            return insert(request.replaceFirst("\s*[iI][Nn][Ss][Ee][Rr][Tt]\s+[Vv][Aa][Ll][Uu][Ee][Ss]\s+",""));
        } else  if(patternUpdate.matcher(request.toLowerCase()).find()){
            return update(request.replaceFirst("\s*[Uu][Pp][Dd][Aa][Tt][Ee]\s+[Vv][Aa][Ll][Uu][Ee][Ss]\s+",""));
        } else  if(patternDelete.matcher(request.toLowerCase()).find()){
            return delete(request.replaceFirst("\s*[Dd][Ee][Ll][Ee][Tt][Ee]\s*",""));
        } else  if(patternSelect.matcher(request.toLowerCase()).find()){
            return select(request.replaceFirst("\s*[Ss][Ee][Ll][Ee][Cc][Tt]\s*",""));
        } else {
            throw new RuntimeException();
        }
    }

    public List<Map<String,Object>> convertSetExpression(String expAll) throws RuntimeException{
        if(expAll == ""){
            return db;
        }
        Set<Map<String,Object>> res = new HashSet<>();
        String[] expArrayOr = expAll.split("\s+[Oo][Rr]\s+");
        for(String expWithAnd: expArrayOr){
            String[] expArrayAnd = expWithAnd.split("\s+[Aa][Nn][Dd]\s+");
            List<Map<String,Object>> prom = new ArrayList<>();
            prom.addAll(convertSingleExpression(expArrayAnd[0]));
            for (String exp: expArrayAnd){
                List<Map<String,Object>> tmp1 = new ArrayList<>();
                List<Map<String,Object>> tmp2 = new ArrayList<>();
                tmp1.addAll(convertSingleExpression(exp));
                for(Map<String, Object> map: tmp1){
                    if(prom.contains(map)) {
                        tmp2.add(map);
                    }
                }
                prom = new ArrayList<>(tmp2);
            }
            res.addAll(prom);
        }
        return new ArrayList<>(res);
    }

    public List<Map<String,Object>> convertSingleExpression(String exp) throws RuntimeException{
        List<Map<String,Object>> res = new ArrayList<>();
        String regexLike = "^\s*'lastname'\s+like\s+'.*'\s*$";
        String regexILike = "^\s*'lastname'\s+ilike\s+'.*'\s*$";
        Pattern patternLike = Pattern.compile(regexLike);
        Pattern patternILike = Pattern.compile(regexILike);
        if (exp.contains(">=")) {
            String col = exp.substring(0, exp.indexOf(">=")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf(">=")+2);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if( map.containsKey(col) && map.get(col)!=null && Long.parseLong(map.get(col).toString()) >= value){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Double.parseDouble(map.get(col).toString()) >= value){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("cost") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        } else if (exp.contains("<=")) {
            String col = exp.substring(0, exp.indexOf("<=")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("<=")+2);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Long.parseLong(map.get(col).toString()) <= value){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Double.parseDouble(map.get(col).toString()) <= value){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("cost") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        } else if (exp.contains("!=")) {
            String col = exp.substring(0, exp.indexOf("!=")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("!=")+2);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && !map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("lastname")){
                stringValue = stringValue.replace("'","");
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && !map.get("lastname").equals(stringValue)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && !map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("active")){
                Boolean value = Boolean.parseBoolean(stringValue.toLowerCase());
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && !map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("active") || col.equals("cost") || col.equals("lastname") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        }else if (exp.contains("=")) {
            String col = exp.substring(0, exp.indexOf("=")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("=")+1);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("lastname")){
                stringValue = stringValue.replace("'","");
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && map.get("lastname").equals(stringValue)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(col.equals("active")){
                Boolean value = Boolean.parseBoolean(stringValue.toLowerCase());
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && map.get(col).equals(value)){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("active") || col.equals("cost") || col.equals("lastname") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        }else if (exp.contains(">")) {
            String col = exp.substring(0, exp.indexOf(">")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("=")+1);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Long.parseLong(map.get(col).toString()) > value){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Double.parseDouble(map.get(col).toString()) > value){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("cost") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        }else if (exp.contains("<")) {
            String col = exp.substring(0, exp.indexOf("<")).replace("'", "").replace(" ","").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("<")+1);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            if(col.equals("id") || col.equals("age")){
                Long value = Long.parseLong(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Long.parseLong(map.get(col).toString()) < value){
                        res.add(map);
                    }
                }
            }
            if(col.equals("cost")){
                Double value = Double.parseDouble(stringValue);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null && Double.parseDouble(map.get(col).toString()) < value){
                        res.add(map);
                    }
                }
            }
            if(!(col.equals("cost") || col.equals("id") || col.equals("age"))){
                throw new RuntimeException();
            }
        }else if (patternILike.matcher(exp.toLowerCase()).find()) {
            String col = exp.substring(0, exp.indexOf("ilike")).replace("'", "").replace(" ", "").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("ilike")+5);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
            stringValue = stringValue.replace("'","");
            if(col.equals("lastname")){
                String regex = "^"+stringValue.replace("%", ".*").toLowerCase()+"$";
                Pattern pattern = Pattern.compile(regex);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null) {
                        Matcher matcher = pattern.matcher(map.get("lastname").toString().toLowerCase());
                        if (matcher.find()) {
                            res.add(map);
                        }
                    }
                }
            } else {
                throw new RuntimeException();
            }
        } else if (patternLike.matcher(exp.toLowerCase()).find()) {
            String col = exp.substring(0, exp.indexOf("like")).replace("'", "").replace(" ", "").toLowerCase();
            String stringValue = exp.substring(exp.indexOf("like")+4);
            int i = 0;
            while(stringValue.charAt(i) == ' '){
                i++;
            }
            stringValue = stringValue.substring(i);
           stringValue = stringValue.replace("'","");
            if(col.equals("lastname")){
                String regex = "^"+stringValue.replace("%", ".*")+"$";
                Pattern pattern = Pattern.compile(regex);
                for(Map map:db){
                    if(map.containsKey(col) && map.get(col)!=null) {
                        Matcher matcher = pattern.matcher(map.get("lastname").toString());
                        if (matcher.find()) {
                            res.add(map);
                        }
                    }
                }
            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
        return res;
    }
}
