package com.icaomei.mpchartlib.interfaces;

import com.icaomei.mpchartlib.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    public BarData getBarData();
    public boolean isDrawBarShadowEnabled();
    public boolean isDrawValueAboveBarEnabled();
    public boolean isDrawHighlightArrowEnabled();
    //public boolean isDrawValuesForWholeStackEnabled();
}
