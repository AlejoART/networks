package com.webpt.testing.atf.config;

import java.lang.reflect.Array;
import java.util.*;

public abstract class AbstractConfig {

    private Map<String, Object> properties;

    public AbstractConfig() {
        properties = new HashMap<>();
    }

    public void set(String property, Object value){
        this.properties.put(property, value);
    }

    public String get(String property){
        Object val = getObject(property);
        if(val instanceof String){
            return (String) val;
        }
        return null;
    }

    public Object getObject(String property){
        return this.properties.get(property);
    }

    public Set<String> getKeys(){
        return this.properties.keySet();
    }

    protected List<String> getAsList(String key){
        Object val = getObject(key);
        if(val instanceof String){
            String stringVal = (String) val;
            if(stringVal.contains(",")){
                String[] sa = stringVal.split(",");
                return Arrays.asList(sa);
            }
            if(stringVal.contains(" ")){
                String[] sa = stringVal.split(" ");
                return Arrays.asList(sa);
            }
        }
        if(val instanceof List){
            return (List) val;
        }
        return null;
    }

    protected Boolean getAsBoolean(String key){
        return Boolean.parseBoolean(get(key));
    }

    protected Integer getAsInt(String key){
        return Integer.parseInt(get(key));
    }


}
