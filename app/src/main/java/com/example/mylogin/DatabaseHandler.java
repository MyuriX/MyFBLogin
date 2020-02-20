package com.example.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mylogin.pojo.Student_info;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_STUDENT = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_DEPT_ID = "dept_id";
    private static final String KEY_PCOURCE_ID = "pcource_id";
    private static final String KEY_SCOURCE_ID = "scource_id";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT,"+ KEY_LNAME + " TEXT,"+ KEY_DEPT_ID + " TEXT,"+ KEY_PCOURCE_ID + " TEXT,"
                + KEY_SCOURCE_ID + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        // Create tables again
        onCreate(db);
    }

    void addStudentInfo(Student_info studentInfo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, studentInfo.getFname()); // Student_info First Name
        values.put(KEY_LNAME, studentInfo.getLname()); // Student_info Last Name
        values.put(KEY_DEPT_ID, studentInfo.getDept_id()); // Student_info Dep_ID
        values.put(KEY_PCOURCE_ID, studentInfo.getPcource_id()); // Student_info Pcource_id
        values.put(KEY_SCOURCE_ID, studentInfo.getScource_id()); // Student_info Scource_id
        

        // Inserting Row
        try {
            db.insert(TABLE_STUDENT, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection


    }

    Student_info getStudent_ifo(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID,
                        KEY_FNAME, KEY_LNAME,KEY_DEPT_ID,KEY_PCOURCE_ID,KEY_SCOURCE_ID }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student_info studentInfo = new Student_info(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        // return contact
        return studentInfo;
    }

    public List<Student_info> getAllStudent_info(){
        List<Student_info> studentInfoList = new ArrayList<Student_info>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student_info studentInfo = new Student_info();
                studentInfo.setID(Integer.parseInt(cursor.getString(0)));
                studentInfo.setFname(cursor.getString(1));
                studentInfo.setLname(cursor.getString(2));
                studentInfo.setDept_id(cursor.getString(3));
                studentInfo.setPcource_id(cursor.getString(4));
                studentInfo.setScource_id(cursor.getString(5));
                // Adding contact to list
                studentInfoList.add(studentInfo);
            } while (cursor.moveToNext());
        }

        // return contact list
        return studentInfoList;
    }

    public int updateContact(Student_info studentInfo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, studentInfo.getFname());
        values.put(KEY_LNAME, studentInfo.getLname());
        values.put(KEY_DEPT_ID, studentInfo.getDept_id());
        values.put(KEY_PCOURCE_ID,studentInfo.getPcource_id());
        values.put(KEY_SCOURCE_ID, studentInfo.getScource_id());

        // updating row
        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(studentInfo.getID()) });
    }

    public void deleteStudentInfo(Student_info studentInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(studentInfo.getID()) });
        db.close();
    }

    public int getContactsStudentInfo(){
        String countQuery = "SELECT  * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
