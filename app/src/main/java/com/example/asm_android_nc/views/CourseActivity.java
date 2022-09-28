package com.example.asm_android_nc.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asm_android_nc.R;
import com.example.asm_android_nc.adapters.CourseAdapter;
import com.example.asm_android_nc.models.AppCourse;
import com.example.asm_android_nc.services.CourseService;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    ListView lvCourses;
    private CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        lvCourses = findViewById(R.id.lvCourses);

        lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }

    private void onGetCourses(){
        Intent intent = new Intent(this, CourseService.class);
        intent.setAction(CourseService.COURSE_SERVICE_ACTION_GET);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onGetCourses();
        IntentFilter courseFilter = new IntentFilter(CourseService.COURSE_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(courseReceiver, courseFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(courseReceiver);
    }

    private BroadcastReceiver courseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<AppCourse> list = (ArrayList<AppCourse>)
                                        intent.getSerializableExtra("result");
            courseAdapter = new CourseAdapter(list);
            lvCourses.setAdapter(courseAdapter);
        }
    };
}