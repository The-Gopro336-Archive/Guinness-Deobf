package dev.guinness.client.util;

import dev.guinness.client.Guinness;
import java.util.Comparator;
import java.util.List;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class MathUtil implements Wrapper
{
    public static double roundDouble(final double val, final int newScale) {
        BigDecimal bd = new BigDecimal(val);
        bd = bd.setScale(newScale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static int getIndex(final String[] array, final String anotherString) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equalsIgnoreCase(anotherString)) {
                return i;
            }
        }
        return -1;
    }
    
    public static String getLongestString(final List list) {
        list.sort(MathUtil::lambda$getLongestString$0);
        return list.get(0);
    }
    
    public static int lambda$getLongestString$0(final String s, final String s2) {
        return Integer.compare((int)Guinness.CUSTOM_FONT.getStringWidth(s2), (int)Guinness.CUSTOM_FONT.getStringWidth(s));
    }
}
