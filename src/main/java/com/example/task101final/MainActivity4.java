package com.example.task101final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    RecyclerView recyclerView;
    RecyclerViewAdapter2 recyclerview2;
    List<Model1> listview2;
    DatabaseClass1 databaseclassanother;
    void fetchAllNotesFromDatabase1()
    {
        Cursor cursor = databaseclassanother.readAllData();
        if (cursor.getCount() == 0)
        {


            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while(cursor.moveToNext())
            {
                byte [] imageBytes = cursor.getBlob(1);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                listview2.add(new Model1(cursor.getString(0),objectBitmap,cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
                Toast.makeText(this," values in mylist Database",Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        recyclerView = findViewById(R.id.recyclerView1);
        listview2 = new ArrayList<>();

        databaseclassanother = new DatabaseClass1(this);
        fetchAllNotesFromDatabase1();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerview2 = new RecyclerViewAdapter2(this, MainActivity4.this, listview2);
        recyclerView.setAdapter(recyclerview2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }


    public void addNewNote1(View view) {
        Intent intent1 = new Intent(MainActivity4.this, mylist.class);
        startActivity(intent1);
    }
    public void showMenu1(View view)
    {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.all_list_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent intent1 = new Intent(MainActivity4.this,UserData.class);
                startActivity(intent1);
                return true;

            case R.id.item3:
                Intent intent2 = new Intent(MainActivity4.this,MainActivity4.class);
                startActivity(intent2);
                return true;
            case R.id.item4:
                Intent intent4 = new Intent(MainActivity4.this, AddingCart.class);
                startActivity(intent4);
                return true;

            default:
                return false;
        }
    }
}