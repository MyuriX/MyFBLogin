package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mylogin.pojo.Student_info;

import java.util.List;


public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addStudentInfo(new Student_info(1,"Ravi","Dhanane","DEP11" ,"P11","S11"));
        db.addStudentInfo(new Student_info(2,"Srinivas","Harane","DEP22","P22", "S22"));
        db.addStudentInfo(new Student_info(3,"Tommy","Irani" ,"DEP33","P33","S33"));
        db.addStudentInfo(new Student_info(4,"Karthik","Dhirani","DEP44","P44", "S44"));


        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Student_info> studentInfo = db.getAllStudent_info();


        for (Student_info cn : studentInfo) {
            String log = "Id: " + cn.getID() + " ,First Name: " + cn.getFname() + " ,Last Name: " +
                    cn.getLname() + " ,Dep Id: " + cn.getDept_id() + " ,PCource Id: " + cn.getPcource_id() + " ,SCource Id " + cn.getScource_id();
            Toast.makeText(this, "Name :" + cn.getFname(), Toast.LENGTH_SHORT).show();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
