package com.example.gab.babylove.activity;

import android.os.Bundle;

import com.example.gab.babylove.R;
import com.fy.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Gab on 2018/1/26 0026.
 *
 */

public class CalendarViewActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_calendar_view;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        List<EventDay> events = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        events.add(new EventDay(calendar, R.drawable.ic_edit));
//
//        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
//        calendarView.setEvents(events);
//        calendarView.setOnDayClickListener(new OnDayClickListener() {
//            @Override
//            public void onDayClick(EventDay eventDay) {
//                Calendar clickedDayCalendar = eventDay.getCalendar();
//            }
//        });
    }
}
