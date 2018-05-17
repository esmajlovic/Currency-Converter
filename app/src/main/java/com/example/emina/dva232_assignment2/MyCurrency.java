package com.example.emina.dva232_assignment2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Emina on 20-Dec-17.
 */

public class MyCurrency {
    String currency_name;
    BigDecimal rateEURO;
    int imageID;

    public MyCurrency (String currency_name, BigDecimal rateEURO, int imageid) {
        this.currency_name = currency_name;
        this.rateEURO = rateEURO;
        this.imageID = imageid;
    }

    public String getCurrency_name () { return currency_name; }
    public BigDecimal getRateEuro () { return rateEURO; }
    public String getRateEURO () { return rateEURO.round(new MathContext(6, RoundingMode.CEILING)).toString(); }
    public int getImageID () { return imageID; }

}
