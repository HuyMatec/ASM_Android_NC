package com.example.asm_android_nc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm_android_nc.database.Database;
import com.example.asm_android_nc.models.AppUser;


public class UserDAO {
    private Database database;

    public UserDAO(Context context){
        database = Database.getInstance(context);
    }
    public boolean register(String name, String email, String password, Integer role){
        Boolean result = false;
        SQLiteDatabase db = database.getWritableDatabase();
        db.beginTransaction();//bắt đầu tương tác database
        try{
            ContentValues values = new ContentValues();
            values.put("NAME", name);
            values.put("EMAIL", email);
            values.put("PASSWORD", password);
            values.put("ROLE", role);
            long rows = db.insertOrThrow("USERS",null, values);
            db.setTransactionSuccessful();
            result = rows >=1;
        }catch (Exception e){
            Log.d(">>>>>>>>TAG","register: " + e.getMessage());
            System.out.println(e);
        }finally {
            db.endTransaction();//kết thúc tương tác database
        }
      return result;
    };

    public AppUser login(String email, String password){
        AppUser appUser = null;
        String sql = "SELECT ID, EMAIL, PASSWORD, NAME, ROLE FROM USERS WHERE EMAIL = ?";
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        try{
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer _id = cursor.getInt(0);
                    String _email = cursor.getString(1);
                    String _password = cursor.getString(2);
                    String _name = cursor.getString(3);
                    Integer _role = cursor.getInt(4);
                    if (!password.equals(_password)) break;
                    appUser = new AppUser(_id,_role,_email,_password,_name);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e){
            Log.d(">>>>>>>>TAG","login: " + e.getMessage());
            System.out.println(e);
        } finally {
            if (cursor != null && !cursor.isClosed()) //nếu con trỏ khác null và con trỏ chưa đóng
                cursor.close();//đóng con trỏ lại
        }
        return appUser;
    }
}
