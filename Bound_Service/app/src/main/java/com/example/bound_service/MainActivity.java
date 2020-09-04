package com.example.bound_service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.example.bound_service.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService yashwinService;
    boolean isBound = false;    //To check whether this service is bound or not

    public void showTime(View view) {
        String currentTime = yashwinService.getCurrentTime();
        TextView yashwinText = (TextView) findViewById(R.id.yashwinText);
        yashwinText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, MyService.class);
        bindService(i, yashwinConnection, Context.BIND_AUTO_CREATE);      //Second parameter has to be an object of ServiceConnection()
    }

    private ServiceConnection yashwinConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            yashwinService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

}
