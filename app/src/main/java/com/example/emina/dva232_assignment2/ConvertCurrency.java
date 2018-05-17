package com.example.emina.dva232_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Emina on 20-Dec-17.
 */

public class ConvertCurrency extends Activity {
    Spinner spinner1;
    Spinner spinner2;
    String currency1;
    String currency2;
    public static final String API_PROVDIER = "http://api.fixer.io/";
    //ArrayList<MyCurrency> currencies;
    Button button;
    public static String [] currencies = { "EUR", "AUD", "BGN","BRL","CAD","CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR","ILS","INR","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR" };
    public static int [] currency_icons = { R.drawable.eur, R.drawable.aus, R.drawable.bgn, R.drawable.brl, R.drawable.cad, R.drawable.chf, R.drawable.cny, R.drawable.czk, R.drawable.dkk, R.drawable.gbp, R.drawable.hkd, R.drawable.hrk, R.drawable.huk, R.drawable.idr, R.drawable.ils, R.drawable.inr, R.drawable.jpy, R.drawable.krw, R.drawable.mxn, R.drawable.myr, R.drawable.nok, R.drawable.nzd, R.drawable.php, R.drawable.pln, R.drawable.ron, R.drawable.rub, R.drawable.sek, R.drawable.sgd, R.drawable.thb, R.drawable.trylira, R.drawable.usd, R.drawable.zar };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_converter);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        button = (Button) findViewById(R.id.button1);
        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(ConvertCurrency.this,
                        MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        spinner1.setAdapter(adapter1);
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        String currencycode = getLocalCurrencyCode(ip);
        spinner1.setSelection(adapter1.getPosition(currencycode));
        spinner2.setAdapter(adapter2);

        final ImageView image1 = (ImageView) findViewById(R.id.image1);
        image1.setImageResource(currency_icons[getCurrencyID(currencycode)]);
        final ImageView image2 = (ImageView) findViewById(R.id.image2);
        image2.setImageResource(R.drawable.eur);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency1 = adapterView.getItemAtPosition(i).toString();
                image1.setImageResource(currency_icons[getCurrencyID(currency1)]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency2 = adapterView.getItemAtPosition(i).toString();
                image2.setImageResource(currency_icons[getCurrencyID(currency2)]);
                // image2.setImageResource(currency2.getImageID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final EditText text1 = (EditText)findViewById(R.id.input);
        final TextView text2 = (TextView)findViewById(R.id.output);
        text2.setText("0.0");
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value = text1.getText().toString();
                try {
                    BigDecimal input = new BigDecimal(value);

                    double coversionRate = convert(currency1, currency2);
                    input = input.multiply(new BigDecimal(coversionRate));
                    text2.setText(input.round(new MathContext(6, RoundingMode.CEILING)).toString());
                }
                catch (Exception e) {
                    Toast.makeText(ConvertCurrency.this, "Incorrect input!",
                            Toast.LENGTH_LONG).show();
                }
                // Code here executes on main thread after user presses button
            }
        });
    }

    private String getLocalCurrencyCode(String ip) {
        String currencycode = "EUR";
        CurrencyLocationResposne resposne = null;
        Gson gson = new Gson();
        StringBuffer sb = new StringBuffer();
        String strUrl = "https://usercountry.com/v1.0/json/"+ip;
        URL url;

        try {

            url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            int data = stream.read();
            while (data != -1) {
                sb.append((char) data);
                data = stream.read();
            }
            stream.close();

            resposne = gson.fromJson(sb.toString(), CurrencyLocationResposne.class);
            currencycode = resposne.getCode();

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return currencycode;
    }

    public int getImageID (String name) {
        return R.drawable.eur;
    }
    public static double convert(String fromCurrencyCode, String toCurrencyCode) {

        if ((fromCurrencyCode != null && !fromCurrencyCode.isEmpty())
                && (toCurrencyCode != null && !toCurrencyCode.isEmpty())) {
            if (fromCurrencyCode.equals(toCurrencyCode))
                return 1.0;
            CurrencyConversionResponse response = getResponse(API_PROVDIER+"/latest?base="+fromCurrencyCode);

            if(response != null) {

                String rate = response.getRates().get(toCurrencyCode);

                double conversionRate = Double.valueOf((rate != null)?rate:"0.0");

                return conversionRate;
            }

        }

        return 0.0;
    }
    // Method to get the response from API
    public static CurrencyConversionResponse getResponse(String strUrl) {

        CurrencyConversionResponse response = null;

        Gson gson = new Gson();
        StringBuffer sb = new StringBuffer();

        if(strUrl == null || strUrl.isEmpty()) {

            System.out.println("Application Error");
            return null;
        }

        URL url;
        try {
            url = new URL(strUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream stream = connection.getInputStream();

            int data = stream.read();

            while (data != -1) {

                sb.append((char) data);

                data = stream.read();
            }

            stream.close();

            response = gson.fromJson(sb.toString(), CurrencyConversionResponse.class);

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(response);
        return response;
    }
    public static int getCurrencyID (String currency) {
        for (int i=1; i<currencies.length; i++) {
            if (currencies[i].equals(currency))
                return i;
        }
        return 0;
    }
}
