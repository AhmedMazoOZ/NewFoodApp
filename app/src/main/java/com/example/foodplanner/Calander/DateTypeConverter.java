package com.example.foodplanner.Calander;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {
    @TypeConverter
    public static Long fromDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp);
    }
}
