package com.summer.record.ui.setting;

import com.summer.x.base.ui.VA;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SettingHomeVA extends VA {

    private ArrayList<String> nums = new ArrayList<>();

    @Override
    public void initVA() {
        super.initVA();
        for(int i=2;i<12;i++){
            nums.add(i+"");
        }
    }
}
