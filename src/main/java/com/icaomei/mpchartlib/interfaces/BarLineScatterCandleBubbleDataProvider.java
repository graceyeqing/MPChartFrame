package com.icaomei.mpchartlib.interfaces;

import com.icaomei.mpchartlib.components.YAxis.AxisDependency;
import com.icaomei.mpchartlib.data.BarLineScatterCandleData;
import com.icaomei.mpchartlib.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    public Transformer getTransformer(AxisDependency axis);
    public int getMaxVisibleCount();
    public boolean isInverted(AxisDependency axis);
    
    public int getLowestVisibleXIndex();
    public int getHighestVisibleXIndex();

    public BarLineScatterCandleData getData();
}
