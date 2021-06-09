package com.example.drugdosagecfpeople;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmBox extends AppCompatActivity {
private  MyCustomAdapter custom_adap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_box);

        TextView status = (TextView)findViewById(R.id.statusdisplay);

        custom_adap= new MyCustomAdapter();
        AlertDialog.Builder dialog;
        dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Dosage Intake")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        custom_adap.cancelAlarm("Confirmed");
                        Toast.makeText(getApplicationContext(),"Intake for Dosage is Confirmed",Toast.LENGTH_LONG ).show();

                    }
                })
                .setNegativeButton("cancel",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                custom_adap.cancelAlarm("Cancelled");
                Toast.makeText(getApplicationContext(),"Intake for Dosage is Cancelled",Toast.LENGTH_LONG ).show();

            }
        })
                .show();

    }
    public void OpenCurrentView(){
        Intent intent = new Intent(this, CurrentView.class);
        startActivity(intent);
    }
}