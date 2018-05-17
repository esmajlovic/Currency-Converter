package com.example.emina.dva232_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MainActivity extends Activity {
    ListView lst;
    // public static ArrayList<MyCurrency> currencies;
    Adapter adapter;
    Button button;
    // public String[] currrencies = {"EUR", "SEK", "USD", "GBP", "CNY", "JPY", "KRW"};
    // public BigDecimal[] rates = {new BigDecimal(1), new BigDecimal(9.95116) , new BigDecimal(1.18448), new BigDecimal(0.88412), new BigDecimal(7.82116), new BigDecimal(133.784), new BigDecimal(1284.47)};
    // public int[] images = {R.drawable.EUR, R.drawable.SEK, R.drawable.USD, R.drawable.GBP, R.drawable.CNY, R.drawable.JPY, R.drawable.KRW };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        CurrencyConversionResponse response = ConvertCurrency.getResponse(ConvertCurrency.API_PROVDIER+"/latest");
        ArrayList<String> rates = new ArrayList<>();
        if(response != null) {

            rates = new ArrayList<>(response.getRates().values());
        }
        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        ConvertCurrency.class);
                startActivity(myIntent);
                finish();
            }
        });
        lst = (ListView) findViewById(R.id.listview);
        adapter = new Adapter(getApplicationContext(), rates);
        lst.setAdapter(adapter);
    }

}
