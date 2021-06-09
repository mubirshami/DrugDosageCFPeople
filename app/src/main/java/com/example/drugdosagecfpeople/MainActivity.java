package com.example.drugdosagecfpeople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import android.app.SearchManager;

public class MainActivity extends AppCompatActivity {
    ListView history;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter<String>arrayAdapter;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=(SearchView)findViewById(R.id.searchhist);

        history=(ListView)findViewById(R.id.list_view);
        String med_array = " {\"meds\": [\n" +
                "  {\"name\":\"Albuterol\",\"mgs\":\"2.5 mg(s)\",\"time\":\"9am and 10pm\",\"status\":\"Confirmed\"},\n" +
                "  {\"name\":\"Pulmozyme\",\"mgs\":\"1 mg(s)\",\"time\":\"3pm\",\"status\":\"Cancelled\"},\n" +
                "  {\"name\":\"Primaxin\",\"mgs\":\"500 mg(s)\",\"time\":\"11am and 7pm\",\"status\":\"Confirmed\"},\n" +
                "  {\"name\":\"Elexacaftor\",\"mgs\":\"100 mg(s)\",\"time\":\"5pm\",\"status\":\"Cancelled\"},\n" +
                "  {\"name\":\"Cayston\",\"mgs\":\"75 mg(s)\",\"time\":\"6pm\",\"status\":\"Confirmed\"}]\n" +
                "}";

        try {
            JSONObject jsonObject=new JSONObject(med_array);
            JSONArray jsonArray=jsonObject.getJSONArray("meds");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                String med_name=object.getString("name");
                String med_mgs=object.getString("mgs");
                String med_time=object.getString("time");
                String med_status=object.getString("status");
                data.add("Medicine Name: "+med_name+" "+med_mgs +"\nIntake Time: "+med_time +"\nStatus: "+med_status);

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,data);
        history.setAdapter(arrayAdapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainActivity.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainActivity.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}
