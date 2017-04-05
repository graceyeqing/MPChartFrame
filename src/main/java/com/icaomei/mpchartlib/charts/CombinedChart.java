
package com.icaomei.mpchartlib.charts;

import android.content.Context;
import android.util.AttributeSet;

import com.icaomei.mpchartlib.data.BarData;
import com.icaomei.mpchartlib.data.BubbleData;
import com.icaomei.mpchartlib.data.BubbleDataSet;
import com.icaomei.mpchartlib.data.CandleData;
import com.icaomei.mpchartlib.data.CombinedData;
import com.icaomei.mpchartlib.data.LineData;
import com.icaomei.mpchartlib.data.ScatterData;
import com.icaomei.mpchartlib.interfaces.BarDataProvider;
import com.icaomei.mpchartlib.interfaces.BubbleDataProvider;
import com.icaomei.mpchartlib.interfaces.CandleDataProvider;
import com.icaomei.mpchartlib.interfaces.LineDataProvider;
import com.icaomei.mpchartlib.interfaces.ScatterDataProvider;
import com.icaomei.mpchartlib.renderer.CombinedChartRenderer;
import com.icaomei.mpchartlib.utils.FillFormatter;

/**
 * This chart class allows the combination of lines, bars, scatter and candle
 * data all displayed in one chart area.
 * 
 * @author Philipp Jahoda
 */
public class CombinedChart extends BarLineChartBase<CombinedData> implements LineDataProvider,
        BarDataProvider, ScatterDataProvider, CandleDataProvider, BubbleDataProvider {

    /** the fill-formatter used for determining the position of the fill-line */
    protected FillFormatter mFillFormatter;

    /** flag that enables or disables the highlighting arrow */
    private boolean mDrawHighlightArrow = false;

    /**
     * if set to true, all values are drawn above their bars, instead of below
     * their top
     */
    private boolean mDrawValueAboveBar = true;

    /**
     * if set to true, a grey area is drawn behind each bar that indicates the
     * maximum value
     */
    private boolean mDrawBarShadow = false;

    protected DrawOrder[] mDrawOrder = new DrawOrder[] {
            DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER
    };

    /**
     * enum that allows to specify the order in which the different data objects
     * for the combined-chart are drawn
     */
    public enum DrawOrder {
        BAR, BUBBLE, LINE, CANDLE, SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
    }

    public CombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mFillFormatter = new DefaultFillFormatter();
        // mRenderer = new CombinedChartRenderer(this, mAnimator,
        // mViewPortHandler);
    }

    @Override
    protected void calcMinMax() {
        super.calcMinMax();
        
        if (getBarData() != null || getCandleData() != null || getBubbleData() != null) {
            mXChartMin = -0.5f;
            mXChartMax = mData.getXVals().size() - 0.5f;

            if (getBubbleData() != null) {

                for (BubbleDataSet set : getBubbleData().getDataSets()) {

                    final float xmin = set.getXMin();
                    final float xmax = set.getXMax();

                    if (xmin < mXChartMin)
                        mXChartMin = xmin;

                    if (xmax > mXChartMax)
                        mXChartMax = xmax;
                }
            }
        } else {
            mXChartMin = 0f;
            mXChartMax = mData.getXValCount()-1;
        }

        mDeltaX = Math.abs(mXChartMax - mXChartMin);
    }

    @Override
    public void setData(CombinedData data) {
        mData = null;
        mRenderer = null;
        super.setData(data);
        mRenderer = new CombinedChartRenderer(this, mAnimator, mViewPortHandler);
        mRenderer.initBuffers();
    }

    public void setFillFormatter(FillFormatter formatter) {

        if (formatter == null)
            formatter = new DefaultFillFormatter();
        else
            mFillFormatter = formatter;
    }

    @Override
    public FillFormatter getFillFormatter() {
        return mFillFormatter;
    }

    @Override
    public LineData getLineData() {
        if (mData == null)
            return null;
        return mData.getLineData();
    }

    @Override
    public BarData getBarData() {
        if (mData == null)
            return null;
        return mData.getBarData();
    }

    @Override
    public ScatterData getScatterData() {
        if (mData == null)
            return null;
        return mData.getScatterData();
    }

    @Override
    public CandleData getCandleData() {
        if (mData == null)
            return null;
        return mData.getCandleData();
    }

    @Override
    public BubbleData getBubbleData() {
        if (mData == null)
            return null;
        return mData.getBubbleData();
    }

    @Override
    public boolean isDrawBarShadowEnabled() {
        return mDrawBarShadow;
    }

    @Override
    public boolean isDrawValueAboveBarEnabled() {
        return mDrawValueAboveBar;
    }

    @Override
    public boolean isDrawHighlightArrowEnabled() {
        return mDrawHighlightArrow;
    }

    /**
     * set this to true to draw the highlightning arrow
     * 
     * @param enabled
     */
    public void setDrawHighlightArrow(boolean enabled) {
        mDrawHighlightArrow = enabled;
    }

    /**
     * If set to true, all values are drawn above their bars, instead of below
     * their top.
     * 
     * @param enabled
     */
    public void setDrawValueAboveBar(boolean enabled) {
        mDrawValueAboveBar = enabled;
    }


    /**
     * If set to true, a grey area is drawn behind each bar that indicates the
     * maximum value. Enabling his will reduce performance by about 50%.
     * 
     * @param enabled
     */
    public void setDrawBarShadow(boolean enabled) {
        mDrawBarShadow = enabled;
    }

    /**
     * Returns the currently set draw order.
     * 
     * @return
     */
    public DrawOrder[] getDrawOrder() {
        return mDrawOrder;
    }

    /**
     * Sets the order in which the provided data objects should be drawn. The
     * earlier you place them in the provided array, the further they will be in
     * the background. e.g. if you provide new DrawOrer[] { DrawOrder.BAR,
     * DrawOrder.LINE }, the bars will be drawn behind the lines.
     * 
     * @param order
     */
    public void setDrawOrder(DrawOrder[] order) {
        if (order == null || order.length <= 0)
            return;
        mDrawOrder = order;
    }
}
