package com.example.perguntasrespostas;

import android.content.Context;

import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Questions.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase implements Database {
    public abstract MyDao myDao();

    private static Database INSTANCE;

    // SINGLETON PATTERN
    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    // Create Unique Instance DB
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "db_questions").allowMainThreadQueries().build();
                }
            }
        }

        return (MyDatabase) INSTANCE;
    }
}
