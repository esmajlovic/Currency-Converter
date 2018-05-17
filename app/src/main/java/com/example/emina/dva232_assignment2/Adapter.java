package com.example.emina.dva232_assignment2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.emina.dva232_assignment2.ConvertCurrency.currencies;

/**
 * Created by Emina on 20-Dec-17.
 */

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> listOfCurrencies;
    //Constructor

    public Adapter(Context mContext, ArrayList<String> listOfCurrencies) {

        this.mContext = mContext;
        this.listOfCurrencies = listOfCurrencies;
    }
    @Override
    public int getCount() {
        return listOfCurrencies.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfCurrencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_item, null);
        TextView currency = (TextView)v.findViewById(R.id.currency_code);
        TextView rate = (TextView)v.findViewById(R.id.currency_rate);
        ImageView image = (ImageView) v.findViewById(R.id.imageView);
        currency.setText(ConvertCurrency.currencies[position+1]);
        rate.setText(listOfCurrencies.get(position));
        String currencyCode = ConvertCurrency.currencies[position+1];
        image.setImageResource(ConvertCurrency.currency_icons[ConvertCurrency.getCurrencyID(currencyCode)]);
        // image.setImageResource(listOfCurrencies.get(position).getImageID());
        return v;
    }
}
