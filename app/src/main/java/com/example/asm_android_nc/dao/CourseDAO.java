package com.example.asm_android_nc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.asm_android_nc.database.Database;
import com.example.asm_android_nc.models.AppCourse;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Database dAtabase;

    public CourseDAO(Context context) {
        dAtabase = Database.getInstance(context);
    }

    //select
    public List<AppCourse> getAll(){
        List<AppCourse> list = new ArrayList<>();
        String sql = "SELECT ID,NAME, CODE, ROOM, TIME, AVAILABLE FROM COURSES";
        SQLiteDatabase db = dAtabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try{
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer _id = cursor.getInt(0);
                    String _name = cursor.getString(1);
                    String _code = cursor.getString(2);
                    String _room = cursor.getString(3);
                    String _time = cursor.getString(4);
                    Integer _available = cursor.getInt(5);
                    AppCourse appCourse = new AppCourse(_id,_available,_name,_code,_time,_room);
                    list.add(appCourse);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.d(">>>>>>>>TAG","login: " + e.getMessage());
            System.out.println(e);
        }finally {
            if (cursor != null && !cursor.isClosed()){ //nếu con trỏ khác null và con trỏ chưa đóng
                cursor.close();//đóng con trỏ lại
            }
        }

        return list;
    }

    public boolean insert (AppCourse appCourse){
        Boolean result = false;
        SQLiteDatabase db = dAtabase.getWritableDatabase();
        db.beginTransaction();//bắt đầu tương tác database
        try{
            ContentValues values = new ContentValues();
            values.put("NAME", appCourse.getName());
            values.put("CODE", appCourse.getCode());
            values.put("TIME", appCourse.getTime());
            values.put("ROOM", appCourse.getRoom());
            values.put("AVAILABLE", appCourse.getAvailable());
            long rows = db.insertOrThrow("COURSES",null,values);
            db.setTransactionSuccessful();
            result = rows >=1;
        }catch (Exception e){
            Log.d(">>>>>>>>TAG","register: " + e.getMessage());
            System.out.println(e);
        }finally {
            db.endTransaction();//kết thúc tương tác database
        }
        return result;
    }

    public boolean update(AppCourse appCourse){
        Boolean result = false;
        SQLiteDatabase db = dAtabase.getWritableDatabase();
        db.beginTransaction();//bắt đầu tương tác database
        try{
            ContentValues values = new ContentValues();
            values.put("NAME", appCourse.getName());
            values.put("CODE", appCourse.getCode());
            values.put("TIME", appCourse.getTime());
            values.put("ROOM", appCourse.getRoom());
            values.put("AVAILABLE", appCourse.getAvailable());
            long rows = db.update("COURSES",values,"ID=?", new String[]{appCourse.getId().toString()});
            db.setTransactionSuccessful();
            result = rows >=1;
        }catch (Exception e){
            Log.d(">>>>>>>>TAG","register: " + e.getMessage());
            System.out.println(e);
        }finally {
            db.endTransaction();//kết thúc tương tác database
        }
        return result;
    }

    public boolean delete(Integer id){
        Boolean result = false;
        SQLiteDatabase db = dAtabase.getWritableDatabase();
        db.beginTransaction();//bắt đầu tương tác database
        try{
            long rows = db.delete("COURSES","ID=?", new String[]{id.toString()});
            db.setTransactionSuccessful();
            result = rows >=1;
        }catch (Exception e){
            Log.d(">>>>>>>>TAG","register: " + e.getMessage());
            System.out.println(e);
        }finally {
            db.endTransaction();//kết thúc tương tác database
        }
        return result;
    }

}
