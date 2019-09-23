package com.archisys.archisysnetworklib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.archisys.archisys_network_lib.ApiClientCallBack;
import com.archisys.archisys_network_lib.ApiClientResponseModel;
import com.archisys.archisys_network_lib.LibPrefs;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LibPrefs.isShowLog=true;
        LibPrefs.BaseUrl="http://core.stockbook.net";
        LibPrefs.getAuthorization="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJjMTlkNDk3OC1jY2E2LTRjOTctOGZmYy0zZDIwM2ZiNzkyODciLCJ1bmlxdWVfbmFtZSI6IjkxLTg4NjY4MTk5MjQiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9tb2JpbGVwaG9uZSI6Ijg4NjY4MTk5MjQiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9jb3VudHJ5IjoiOTEiLCJuYmYiOjE1Njc1MTE4MTMsImV4cCI6MTU4MzA2MzgxMywiaWF0IjoxNTY3NTExODEzfQ.6CO5mJjAP7A27l0O6KoX6BWV7BP4C1usiLdg3DmxOe4";
        LibPrefs.NotificationId = "Notification";
        Log.i("MainActivity","MainActivity , BaseUrl"+LibPrefs.BaseUrl);

        new GeneralServiceImpl().Advertisement(new ApiClientCallBack() {
            @Override
            public void Response(ApiClientResponseModel response) {
                Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
