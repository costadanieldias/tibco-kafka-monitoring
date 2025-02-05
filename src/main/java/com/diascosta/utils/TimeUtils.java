package com.diascosta.utils;

import java.util.Date;

public class TimeUtils {

    public static long calculateTimeDelta(Date entryTime, Date exitTime) {
        return exitTime.getTime() - entryTime.getTime();
    }
}
