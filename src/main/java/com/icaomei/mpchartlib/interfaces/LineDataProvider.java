
package com.icaomei.mpchartlib.interfaces;

import com.icaomei.mpchartlib.data.LineData;
import com.icaomei.mpchartlib.utils.FillFormatter;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    public LineData getLineData();

    /**
     * Sets a custom FillFormatter to the chart that handles the position of the
     * filled-line for each DataSet. Set this to null to use the default logic.
     * 
     * @param formatter
     */
    public void setFillFormatter(FillFormatter formatter);

    /**
     * Returns the FillFormatter that handles the position of the filled-line.
     * 
     * @return
     */
    public FillFormatter getFillFormatter();
}
