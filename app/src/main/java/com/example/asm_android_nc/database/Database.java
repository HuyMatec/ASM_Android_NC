package com.example.asm_android_nc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Database extends SQLiteOpenHelper {

    private static Database instance;
    public static synchronized Database getInstance(Context context){
        if (instance == null){
            instance = new Database(context);
        }
        return instance;
    }

    public Database( Context context){
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT, NAME TEXT, ROLE INTEGER)";
        db.execSQL(sqlUsers);
        String sqlCourses = "CREATE TABLE IF NOT EXISTS COURSES (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CODE TEXT, ROOM TEXT, TIME TEXT, AVAILABLE INTEGER)";
        db.execSQL(sqlCourses);
        String sqlEnrolls = "CREATE TABLE IF NOT EXISTS ENROLLS ( ID INTEGER PRIMARY KEY AUTOINCREMENT, USERID INTEGER, COURSESID TEXT, ROOM TEXT, ROLE INTEGER)";
        db.execSQL(sqlEnrolls);

        db.execSQL("insert into USERS (EMAIL, PASSWORD, NAME, ROLE) values ('ahihi@gmail.com', '1', 'An Ty', 1);");
        db.execSQL("insert into USERS (EMAIL, PASSWORD, NAME, ROLE) values ('hiaa@gmail.com', '1', 'Ma tec', 2);");

        db.execSQL("insert into COURSES (NAME, CODE, ROOM, TIME, AVAILABLE) values ('Ứng dụng phần mềm', 'MOB102', 30, '17:00', 1);");
        db.execSQL("insert into COURSES (NAME, CODE, ROOM, TIME, AVAILABLE) values ('JavaScript', 'MOB103', 26, '15:00', 1);");
        db.execSQL("insert into COURSES (NAME, CODE, ROOM, TIME, AVAILABLE) values ('Java 1', 'MOB103', 26, '15:00', 1);");
        db.execSQL("insert into COURSES (NAME, CODE, ROOM, TIME, AVAILABLE) values ('Thiết kế Website', 'MOB103', 26, '12:00', 1);");
        db.execSQL("insert into COURSES (NAME, CODE, ROOM, TIME, AVAILABLE) values ('Trí tuệ nhân tạo AI', 'MOB103', 26, '09:00', 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS ENROLLS");
            db.execSQL("DROP TABLE IF EXISTS USERS");
            db.execSQL("DROP TABLE IF EXISTS COURSES");
            onCreate(db);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
