package com.example.emina.dva232_assignment2;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Emina on 21-Dec-17.
 */

public class CurrencyLocationResposne {
    String status;
    String ip;
    String ip_version;
    Map<String,String > country = new TreeMap<String, String>();
    Map<String,String> continent = new TreeMap<String, String>();
    Map<String,String> currency = new TreeMap<String, String>();
    Map<String,String > language = new TreeMap<String, String>();
    Map<String,String> region = new TreeMap<String, String>();
    Map<String,String> timezone = new TreeMap<String, String>();

    public String getCode() {
        return currency.get("code");
    }

}
