package com.example.asm_android_nc.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_android_nc.R;
import com.example.asm_android_nc.models.AppCourse;

import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {
    private ArrayList<AppCourse> list;
    public CourseAdapter(ArrayList<AppCourse> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int _position, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if (view == null){
            view = View.inflate(_viewGroup.getContext(), R.layout.layout_course_detail_item, null);
            TextView tvCourseCode = view.findViewById(R.id.tvCourseCode);
            TextView tvCourseName = view.findViewById(R.id.tvCourseName);
            TextView tvCourseTime = view.findViewById(R.id.tvCourseTime);
            TextView tvCourseRoom = view.findViewById(R.id.tvCourseRoom);
            ViewHolder holder = new ViewHolder(tvCourseCode, tvCourseName, tvCourseTime, tvCourseRoom);
            view.setTag(holder);
        }
        AppCourse course = (AppCourse) getItem(_position);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (course != null){
            holder.tvCourseCode.setText(course.getCode());
            holder.tvCourseName.setText(course.getName());
            holder.tvCourseTime.setText(course.getTime());
            holder.tvCourseRoom.setText(course.getRoom());
        }
        return view;
    }

    private static class ViewHolder {
        final TextView tvCourseCode, tvCourseName, tvCourseTime, tvCourseRoom;

        public ViewHolder(TextView tvCourseCode, TextView tvCourseName, TextView tvCourseTime, TextView tvCourseRoom) {
            this.tvCourseCode = tvCourseCode;
            this.tvCourseName = tvCourseName;
            this.tvCourseTime = tvCourseTime;
            this.tvCourseRoom = tvCourseRoom;
        }
    }
}

