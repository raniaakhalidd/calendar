package com.example.calender;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements calenderview.OnItemListner
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerview;
    private LocalDate selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initwidgets()
    {
        calendarRecyclerview = findViewById(R.id.calender);
        monthYearText = findViewById(R.id.monthyearTV);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        calenderview calenderview = new calenderview(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerview.setLayoutManager(layoutManager);
        calendarRecyclerview.setAdapter(calenderview);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> dayInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i=1; i<=42; i++){
            if(i <= dayOfWeek || i > dayOfWeek + daysInMonth)
            {
                dayInMonthArray.add("");
            }
            else {
                dayInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return dayInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM YYYY");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected Date" + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

    }
}