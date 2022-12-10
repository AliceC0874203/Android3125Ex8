package com.example.android_3125_ex8;

import java.util.ArrayList;
import java.util.List;

public class TimeObj {
    String time;

    public TimeObj(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public static List<TimeObj> timeObjList = new ArrayList<>();
}
