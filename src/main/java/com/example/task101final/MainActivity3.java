package com.example.task101final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void showList1(View view)
    {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }

    public void addNewNote1(View view) {
        Intent intent1 = new Intent(MainActivity3.this, mylist.class);
        startActivity(intent1);
    }

}