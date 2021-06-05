package com.example.task101final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddingCart extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView recyclerView;
    RecyclerViewAdapter3 recyclerviewadapter;
    List<Model3> listview;
    TextView total, new_text;
    DatabaseClass3 databaseClass;
    private Button paypalBtn;

    private int PAYPAL_REQ_CODE = 12;
    private static PayPalConfiguration paypalConfig = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PaypalClientIdConfigClass.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_cart);
        paypalBtn = findViewById(R.id.payBtn);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig);
        startService(intent);

        paypalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaypalPaymentMethods();

            }
        });

        try
        {
            recyclerView = findViewById(R.id.recycleCart);
            total = findViewById(R.id.cost);
            new_text = findViewById(R.id.total);
            listview = new ArrayList<>();

            databaseClass = new DatabaseClass3(this);

            fetchAllNotesFromDatabaseCart();

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerviewadapter = new RecyclerViewAdapter3(this, AddingCart.this, listview);
            recyclerView.setAdapter(recyclerviewadapter);
            total.setText(listview.size() + "");
            Toast.makeText(AddingCart.this,"size changed",Toast.LENGTH_SHORT).show();



        }

        catch (Exception e)
        {
            Toast.makeText(AddingCart.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    void fetchAllNotesFromDatabaseCart()
    {
        Cursor cursor = databaseClass.readAllData();
        if (cursor.getCount() == 0)
        {


            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }

        else
        {

            while(cursor.moveToNext())
            {
                listview.add(new Model3(cursor.getString(0),cursor.getString(1)));

            }

        }

    }

    public void showMenu11(View view)
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
                Intent intent = new Intent(AddingCart.this,MainActivity2.class);
                startActivity(intent);
                return true;

            case R.id.item2:
                Intent intent1 = new Intent(AddingCart.this,UserData.class);
                startActivity(intent1);
                return true;

            case R.id.item3:
                Intent intent2 = new Intent(AddingCart.this,MainActivity4.class);
                startActivity(intent2);
                return true;
            case R.id.item4:
                Intent intent4 = new Intent(AddingCart.this, AddingCart.class);
                startActivity(intent4);
                return true;

            default:
                return false;
        }
    }

    private void PaypalPaymentMethods()
    {
        PayPalPayment payments = new PayPalPayment(new BigDecimal(total.getText().toString()),"AUD","All the items",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,paypalConfig);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payments);

        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }

}