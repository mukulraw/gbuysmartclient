package com.mrtecks.epickmartclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseMessaging.getInstance().subscribeToTopic("vendor").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("task", task.toString());
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                String id = SharePreferenceUtils.getInstance().getString("id");

                if (id.length() > 0)
                {
                    if (SharePreferenceUtils.getInstance().getString("type").equals("admin"))
                    {
                        Intent i = new Intent(Splash.this , MainActivity2.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(Splash.this , MainActivity.class);
                        startActivity(i);
                        finish();
                    }


                }
                else
                {
                    Intent i = new Intent(Splash.this , Login.class);
                    startActivity(i);
                    finish();
                }




            }
        } , 1500);

    }
}
