package com.example.madchef;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AddMealActivity extends AppCompatActivity {
    public static final int YOUR_IMAGE_CODE = 1;
    private Button add;
    private Dialog dialog;
    //private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    //private AdapterReminders adapter;
    private List<AddMeal> temp;
    private TextView empty;
    public static Uri selectedImageUri = null;
    public static final String ExtraUri = "Uri";
    public static final String ExtraDate = "date";
    public static final String ExtraMood = "mood";
    public static final String ExtraDayNight = "daynight";
    public static final String ExtraNote = "note";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        final TextView textView = findViewById(R.id.date);

        //Button select,add;
        //select = dialog.findViewById(R.id.selectDate);
        //add = dialog.findViewById(R.id.addButton);
        final EditText message = findViewById(R.id.message);
        final Calendar newCalender = Calendar.getInstance();
        ImageView imgpicker = findViewById(R.id.test);
        imgpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "select a picture"), YOUR_IMAGE_CODE);
                /*TextView test = findViewById(R.id.textView);
                test.setText(selectedImageUri.toString());
                ImageView img = findViewById(R.id.test);
                Glide.with(AddMealActivity.this)
                        .load(new File(selectedImageUri.getPath()))
                        .into(img);*/
            }
        });
        Button select = findViewById(R.id.selectDate);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(AddMealActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                        final Calendar newDate = Calendar.getInstance();
                        //Calendar newTime = Calendar.getInstance();
                        newDate.set(year,month,dayOfMonth,0,0,0);
                        if (dayOfMonth==1)
                            textView.setText(new SimpleDateFormat("EEE").format(newDate.getTime())+", "+new SimpleDateFormat("MMM").format(newDate.getTime())+" 1st");
                        else if (dayOfMonth==2)
                            textView.setText(new SimpleDateFormat("EEE").format(newDate.getTime())+", "+new SimpleDateFormat("MMM").format(newDate.getTime())+" 2nd");
                        else if (dayOfMonth==3)
                            textView.setText(new SimpleDateFormat("EEE").format(newDate.getTime())+", "+new SimpleDateFormat("MMM").format(newDate.getTime())+" 3rd");
                        else
                            textView.setText(new SimpleDateFormat("EEE").format(newDate.getTime())+", "+new SimpleDateFormat("MMM").format(newDate.getTime())+" "+dayOfMonth+"th");
                    }
                },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();

            }
        });

    final Button button = findViewById(R.id.addButton);
        button.setOnClickListener(view -> {
        Intent replyIntent = new Intent();
        String date = textView.getText().toString();
        String note = message.getText().toString();

        if (TextUtils.isEmpty(textView.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        }
        else if (TextUtils.isEmpty(message.getText())){
            setResult(RESULT_CANCELED, replyIntent);
        }
        else if (selectedImageUri==null){
            replyIntent.putExtra(ExtraDate, date);
            replyIntent.putExtra(ExtraNote, note);
            replyIntent.putExtra(ExtraUri,"");
            //replyIntent.putExtra(ExtraMood, Integer.toString(mood));
            //replyIntent.putExtra(ExtraDayNight, Boolean.toString(dayNight));
            setResult(RESULT_OK, replyIntent);
        }
        else {
            replyIntent.putExtra(ExtraDate, date);
            replyIntent.putExtra(ExtraNote, note);
            replyIntent.putExtra(ExtraUri,selectedImageUri.toString());
           //replyIntent.putExtra(ExtraMood, Integer.toString(mood));
            //replyIntent.putExtra(ExtraDayNight, Boolean.toString(dayNight));
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    });
    /*public void addReminder(){

        dialog = new Dialog(MainPage.this);
        dialog.setContentView(R.layout.floating_popup);

        final TextView textView = dialog.findViewById(R.id.date);
        Button select,add;
        select = dialog.findViewById(R.id.selectDate);
        add = dialog.findViewById(R.id.addButton);
        final EditText message = dialog.findViewById(R.id.message);


        final Calendar newCalender = Calendar.getInstance();
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(AddMealActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                        final Calendar newDate = Calendar.getInstance();
                        Calendar newTime = Calendar.getInstance();
                        TimePickerDialog time = new TimePickerDialog(MainPage.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                Calendar tem = Calendar.getInstance();
                                Log.w("TIME",System.currentTimeMillis()+"");
                                if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0)
                                    textView.setText(newDate.getTime().toString());
                                else
                                    Toast.makeText(MainPage.this,"Invalid time",Toast.LENGTH_SHORT).show();

                            }
                        },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                        time.show();

                    }
                },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));

                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoomDAO roomDAO = appDatabase.getRoomDAO();
                Reminders reminders = new Reminders();
                reminders.setMessage(message.getText().toString().trim());
                Date remind = new Date(textView.getText().toString().trim());
                reminders.setRemindDate(remind);
                roomDAO.Insert(reminders);
                List<Reminders> l = roomDAO.getAll();
                reminders = l.get(l.size()-1);
                Log.e("ID chahiye",reminders.getId()+"");

                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
                calendar.setTime(remind);
                calendar.set(Calendar.SECOND,0);
                Intent intent = new Intent(MainPage.this,NotifierAlarm.class);
                intent.putExtra("Message",reminders.getMessage());
                intent.putExtra("RemindDate",reminders.getRemindDate().toString());
                intent.putExtra("id",reminders.getId());
                PendingIntent intent1 = PendingIntent.getBroadcast(MainPage.this,reminders.getId(),intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intent1);

                Toast.makeText(MainPage.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
                setItemsInRecyclerView();
                AppDatabase.destroyInstance();
                dialog.dismiss();

            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    public void setItemsInRecyclerView(){

        RoomDAO dao = appDatabase.getRoomDAO();
        temp = dao.orderThetable();
        if(temp.size()>0) {
            empty.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter = new AdapterReminders(temp);
        recyclerView.setAdapter(adapter);

    }*/
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent();
        if (requestCode == YOUR_IMAGE_CODE) {
            if(resultCode == RESULT_OK)

                selectedImageUri = data.getData();
                ImageView imgpicker = findViewById(R.id.test);
                imgpicker.setImageURI(selectedImageUri);

        }
    }}