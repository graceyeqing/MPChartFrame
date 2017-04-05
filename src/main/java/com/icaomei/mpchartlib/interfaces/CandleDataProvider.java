package com.icaomei.mpchartlib.interfaces;

import com.icaomei.mpchartlib.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    public CandleData getCandleData();
}
