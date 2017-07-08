package com.example.vikram.res_recycler;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                callStartButton(v);


            }
        });

    }
//    https://stackoverflow.com/questions/10905312/receive-result-from-dialogfragment
    public void callStartButton(View v){

        FragmentManager fm = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("key", "TestValue");

        CardViewActivity dFragment1 = new CardViewActivity();
        dFragment1.show(fm, "Dialog Fragment");
        dFragment1.setArguments(args);

    }

}
