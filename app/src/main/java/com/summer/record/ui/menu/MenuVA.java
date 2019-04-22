package com.summer.record.ui.menu;

import com.summer.x.base.ui.VA;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuVA extends VA {

    private ArrayList<String> items;

    private ArrayList<HashMap<String,String>> maps = new ArrayList<>();

    public ArrayList<HashMap<String,String>> changeToMap(ArrayList<String> items){
        maps.clear();
        for(int i=0;i<items.size();i++){
            HashMap hashMap = new HashMap();
            hashMap.put("text",items.get(i));
            maps.add(hashMap);
        }
        return maps;
    }
}
