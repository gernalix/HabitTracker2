package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by seste on 04/06/2017.
 */

public class HabitContract {
    public static final String DB_NAME = "com.exemple.udacityhabittracker.db";
    public static final int DB_VERSION = 1;

    public class HabitEntry implements BaseColumns {
        public static final String TABLE = "habits";

        public static final String HABIT_NAME = "habit";
        public static final String HABIT_FREQ = "frequence";
    }
}