package com.example.drugdosagecfpeople;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.util.Log;
import android.widget.SearchView.OnQueryTextListener;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class CurrentView extends AppCompatActivity {
    TextView date_time;
    ListView current;
    ArrayAdapter<String>arrayAdapter;
    private MyCustomAdapter mCustomAdapter;
    ArrayList<String>present_view = new ArrayList<>();
    //SearchView search;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_view);



        date_time=(TextView)findViewById(R.id.textView);
        String currDate_Time= java.text.DateFormat.getDateTimeInstance().format(new Date());
        date_time.setText(currDate_Time);

        //search=(SearchView)findViewById(R.id.searching);
        current=(ListView)findViewById(R.id.cur_view);
        JSONObject rec1 = new JSONObject();
        try {
            rec1.put("name", "Albuterol");
            rec1.put("mgs", "2.5 mg(s)");
            rec1.put("time", "Tue Jun 09 21:18:00 GMT+05:00 2021");



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject  rec2 = new JSONObject();
        try {
            rec2.put("name", "Pulmozyme");
            rec2.put("mgs", "1 mg(s)");
            rec2.put("time", "Wed Jun 09 21:15:00 GMT+05:00 2021");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject  rec3 = new JSONObject();
        try {
            rec3.put("name", "Primaxin");
            rec3.put("mgs", "500 mg(s)");
            rec3.put("time", "Thu Jun 09 21:12:00 GMT+05:00 2021");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject  rec4 = new JSONObject();
        try {
            rec4.put("name", "Elexacaftor");
            rec4.put("mgs", "100 mg(s)");
            rec4.put("time", "Fri Jun 09 21:09:00 GMT+05:00 2021");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONArray medArray = new JSONArray();

        medArray.put(rec1);
        medArray.put(rec2);
        medArray.put(rec3);
        medArray.put(rec4);

        mCustomAdapter=new MyCustomAdapter(medArray, this);
        current.setAdapter(mCustomAdapter);

        current.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_LONG).show();
            }
        });


    }

}

