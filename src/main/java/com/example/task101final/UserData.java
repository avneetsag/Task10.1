package com.example.task101final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.firebase.auth.FirebaseAuth;

public class UserData extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Button logoutbutton;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        logoutbutton = findViewById(R.id.logout);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain= new Intent(UserData.this,MainActivity.class);
                startActivity(intoMain);
            }
        });
    }

    public void showMenu(View view)
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
                Intent intent = new Intent(UserData.this,MainActivity2.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent intent1 = new Intent(UserData.this,UserData.class);
                startActivity(intent1);
                return true;

            case R.id.item3:
                Intent intent2 = new Intent(UserData.this,MainActivity4.class);
                startActivity(intent2);
                return true;

            default:
                return false;
        }
    }

}