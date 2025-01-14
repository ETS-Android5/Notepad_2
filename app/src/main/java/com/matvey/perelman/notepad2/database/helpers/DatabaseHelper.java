package com.matvey.perelman.notepad2.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "main_db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE main(" +

                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "parent INTEGER," +
                        "name TEXT NOT NULL," +
                        "type INTEGER NOT NULL," +
                        "content TEXT," +

                        "FOREIGN KEY(parent) REFERENCES main(id)," +
                        "UNIQUE(parent, name)," +
                        "CHECK (type >= 0 and type < 3)," +
                        "CHECK (type == 0 <> NOT (content is NULL))" +
                        ");");
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = OFF");
        sqLiteDatabase.execSQL("INSERT INTO main(ID, parent, name, type, content) values(0, 0, '/', 0, NULL)");
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE main");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
