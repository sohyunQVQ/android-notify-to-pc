package com.abigeater.com.notifylistener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Intent intent;
            intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = findViewById(R.id.ip_sure);
        editText = findViewById(R.id.ip_str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendService.setIp(editText.getText().toString());
            }
        });
    }

}