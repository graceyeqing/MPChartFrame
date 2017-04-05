
package com.icaomei.mpchartlib.utils;

import com.icaomei.mpchartlib.data.Entry;

/**
 * Interface that can be used to return a customized color instead of setting
 * colors via the setColor(...) method of the DataSet.
 * 
 * @author Philipp Jahoda
 */
public interface ColorFormatter {

    public int getColor(Entry e, int index);
}