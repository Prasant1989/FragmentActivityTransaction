package com.prasant.fragmentactivitytransaction.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prasant.fragmentactivitytransaction.model.StudentModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String STUDENT_TABLE_NAME = "student";
    public static final String STUDENT_COLUMN_ID = "id";
    public static final String STUDENT_COLUMN_NAME = "name";
    public static final String STUDENT_COLUMN_EMAIL = "email";
    public static final String STUDENT_COLUMN_PHONE = "phone";
    public static final int DATABASE_VERSION_ID = 1;
    private HashMap hashMap;


    private static final String TABLE_CREATE =
            "CREATE TABLE " + STUDENT_TABLE_NAME + " (" +
                    STUDENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STUDENT_COLUMN_NAME + " TEXT, " +
                    STUDENT_COLUMN_EMAIL + " TEXT, " +
                    STUDENT_COLUMN_PHONE + " TEXT " +
                    ")";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_ID);
        Log.d("table", TABLE_CREATE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_CREATE + "'");
        onCreate(db);

    }

    public long addUserDetail(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(STUDENT_COLUMN_NAME, name);
        values.put(STUDENT_COLUMN_EMAIL, email);
        values.put(STUDENT_COLUMN_PHONE, phone);
        // insert row in students table
        long insert = db.insert(STUDENT_TABLE_NAME, null, values);

        return insert;
    }

    public ArrayList<StudentModel> getAllUsers() {
        ArrayList<StudentModel> studentModelArrayList = new ArrayList<StudentModel>();

        String selectQuery = "SELECT  * FROM " + STUDENT_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setStd_Id(c.getInt(c.getColumnIndex(STUDENT_COLUMN_ID)));
                studentModel.setStd_name(c.getString(c.getColumnIndex(STUDENT_COLUMN_NAME)));
                studentModel.setStd_email(c.getString(c.getColumnIndex(STUDENT_COLUMN_EMAIL)));
                studentModel.setStd_phone(c.getString(c.getColumnIndex(STUDENT_COLUMN_PHONE)));
                // adding to Students list
                studentModelArrayList.add(studentModel);
            } while (c.moveToNext());
        }
        return studentModelArrayList;
    }

    public int updateUser(int id, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(STUDENT_COLUMN_NAME, name);
        values.put(STUDENT_COLUMN_EMAIL, email);
        values.put(STUDENT_COLUMN_PHONE, phone);

        // update row in students table base on students.is value
        return db.update(STUDENT_TABLE_NAME, values, STUDENT_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE_NAME, STUDENT_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
