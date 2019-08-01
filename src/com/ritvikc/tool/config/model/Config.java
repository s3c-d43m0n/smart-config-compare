/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ritvikc.tool.config.model;

import java.util.*;

/**
 *
 * @author ritvikc
 */
public class Config {
    static HashMap<String, String> vFirstConfigFile;
    static HashMap<String, String> vSecondConfigFile;
    
    public static void init(){
        vFirstConfigFile = new HashMap<>();
        vSecondConfigFile = new HashMap<>();
    }
    
    public static String [] getConfig(String key){
        String vFirstValue = vFirstConfigFile.getOrDefault(key, "NULL");
        String vSecondValue = vSecondConfigFile.getOrDefault(key, "NULL");
        boolean isMatching = vFirstValue.equals(vSecondValue);
        boolean isConflict = false;
        if(!isMatching){
            isConflict=true;
            if(vFirstValue.length()==0 || vSecondValue.length()==0){
                isConflict=false;
            }
        }
        String matchString = isMatching?"<html><body><b><font color=\"green\">YES</font></b></body></html>":"<html><body><b><font color=\"red\">NO</font></b></body></html>";
        String conflictString = isConflict?"<html><body><b><font color=\"red\">YES</font></b></body></html>":"<html><body><b><font color=\"green\">NO</font></b></body></html>";
        return new String[]{ 
            "<html><body><b>"+key+"</b></body></html>", 
            vFirstValue, 
            vSecondValue, 
            matchString,
            conflictString
        };
    }
    
    public static void setConfig(String key, String value, Boolean first){
        
        if(first){
            vFirstConfigFile.put(key, value);
        }
        else{
            vSecondConfigFile.put(key, value);
        }
    }
    
    public static TreeSet<String> getKeys(){
        TreeSet<String> keys = new TreeSet<>();
        vFirstConfigFile.keySet().forEach((key) -> {
            keys.add(key);
        });
        vSecondConfigFile.keySet().forEach((key) -> {
            keys.add(key);
        });
        return keys;
    }
}
