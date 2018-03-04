package com.ch.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
public class ViewObject {
    private Map<String,Object> map = new HashMap<>();

    public Object get(String key) {
        return map.get(key);
    }

    public void set(String key ,Object o) {
        map.put(key,o);
    }
}
