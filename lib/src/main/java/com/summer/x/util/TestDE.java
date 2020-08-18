package com.summer.x.util;

import com.summer.x.base.ui.DE;

import java.util.ArrayList;

public class TestDE extends DE {

    public ArrayList<String> getTestText(int count){
        ArrayList<String> text = new ArrayList<>();
        for(int i=0;i<count;i++){
            text.add(i+"这是测试文本");
        }
        return text;
    }


    public ArrayList<String> getTestImg(int count){
        ArrayList<String> text = new ArrayList<>();
        for(int i=0;i<count;i++){
            text.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3542617805,2989194842&fm=26&gp=0.jpg");
        }
        return text;
    }
}
