
package com.icaomei.mpchartlib.utils;

import java.text.DecimalFormat;

/**
 * This ValueFormatter is just for convenience and simply puts a "%" sign after
 * each value. (Recommeded for PieChart)
 * 
 * @author Philipp Jahoda
 */
public class MoneyFormatter implements ValueFormatter {

    protected DecimalFormat mFormat;

    public MoneyFormatter() {
        mFormat = new DecimalFormat("###,###,##0.00");
    }

    @Override
    public String getFormattedValue(float value) {
        return "￥"+mFormat.format(value);
    }
}
