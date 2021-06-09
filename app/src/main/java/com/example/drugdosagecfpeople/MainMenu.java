package com.example.drugdosagecfpeople;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button Hist;
    Button current_view;
    Button shutdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        shutdown=(Button)findViewById(R.id.close);
        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exit();
            }
        });
        current_view = (Button) findViewById(R.id.curr_View);
        current_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCurrentView();
            }
        });

        Hist = (Button) findViewById(R.id.hist);
        Hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMainActivity();
            }
        });

    }

    public void OpenCurrentView() {
        Intent intent = new Intent(this, CurrentView.class);
        startActivity(intent);
    }

    public void OpenMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Exit(){
        finish();
        System.exit(0);
    }



}

