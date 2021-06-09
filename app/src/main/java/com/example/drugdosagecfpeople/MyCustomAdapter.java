package com.example.drugdosagecfpeople;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.drugdosagecfpeople.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private JSONArray list = new JSONArray();
    private Context context;
    TimePickerDialog timePickerDialog;
    private int pos;
    AlarmManager alarmManager;
    private LayoutInflater inflater;
PendingIntent pendingIntent;

    public  MyCustomAdapter () {
        super();

}

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public MyCustomAdapter(JSONArray list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.length();
    }

    //
    @Override
    public Object getItem(int pos) {
        Object obj=new JSONObject();
        try {
          obj= list.getJSONObject(pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void setTime(int pos, String _time) {
        JSONObject med_json= new JSONObject();
        JSONArray tempArr= new JSONArray();
        try {
            med_json= list.getJSONObject(pos);
            med_json.put("time", _time);
            tempArr=list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list= new JSONArray();
        list=tempArr;
        this.notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void cancelAlarm(String status) {


        alarmManager.cancel(pendingIntent);




    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_custom_work, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_name);
        TextView listmgs=(TextView)view.findViewById(R.id.list_item_mgs);
        TextView listtime=(TextView)view.findViewById(R.id.list_item_time);
        try {
            listItemText.setText(list.getJSONObject(position).getString("name"));
            listmgs.setText(list.getJSONObject(position).getString("mgs"));
            listtime.setText(list.getJSONObject(position).getString("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Handle buttons and add onClickListeners
        Button Alarm = (Button)view.findViewById(R.id.setalarm);

        Alarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openTimePickerDialog(false);

            }
            private void openTimePickerDialog(boolean is24r) {
                Calendar calendar = Calendar.getInstance();

                timePickerDialog = new TimePickerDialog(context,
                        onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), is24r);
                timePickerDialog.setTitle("Set Alarm Time");

                timePickerDialog.show();

            }
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    Calendar calNow = Calendar.getInstance();
                    Calendar calSet = (Calendar) calNow.clone();

                    calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calSet.set(Calendar.MINUTE, minute);
                    calSet.set(Calendar.SECOND, 0);
                    calSet.set(Calendar.MILLISECOND, 0);

                    if (calSet.compareTo(calNow) <= 0) {
                        // Today Set time passed, count to tomorrow
                        calSet.add(Calendar.DATE, 1);
                    }

                    setAlarm(calSet);
                }
            };

            private void setAlarm(Calendar targetCal) {
                pos=position;
                //  targetCal.getTime()
                setTime(position,targetCal.getTime().toString() );
                Intent intent = new Intent(context, MyBroadcastReceiver.class);
                 pendingIntent = PendingIntent.getBroadcast(
                        context,position , intent, PendingIntent.FLAG_ONE_SHOT);
                 alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                        pendingIntent);



            }

        });

        return view;
    }


}
