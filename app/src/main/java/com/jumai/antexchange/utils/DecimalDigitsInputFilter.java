package com.jumai.antexchange.utils;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * @author yf
 * @date 2019/3/6
 * 描述：
 */

public class DecimalDigitsInputFilter implements InputFilter {

    private final int mDecimalDigits;

    public DecimalDigitsInputFilter(int decimalDigits) {
        mDecimalDigits = decimalDigits;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int dotPos = -1;
        int len = dest.length();
        for (int i = 0; i < len; i++) {
            char c = dest.charAt(i);
            if (c == '.' || c == ',') {
                dotPos = i;
                break;
            }
        }
        if (dotPos >= 0) {
            // protects against many dots
            if (source.equals(".") || source.equals(",")) {
                return "";
            }
            // if the text is entered before the dot
            if (dend <= dotPos) {
                return null;
            }
            if (len - dotPos > mDecimalDigits) {
                return "";
            }
        }
        return null;
    }
}
