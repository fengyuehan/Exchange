package com.jumai.antexchange.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yf
 * @date 2019/11/1/001
 * 描述：
 */
public class MathUtil {
    public static BigDecimal add(double var1, double var2) {
        return add(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal add(double var1, String var2) {
        return add(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal add(double var1, double var2, int scale) {
        return add(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String addToString(double var1, double var2) {
        return addToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String addToString(double var1, double var2, int scale) {
        return addToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static BigDecimal add(String var1, String var2) {
        return add(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal add(String var1, String var2, int scale) {
        return add(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String addToString(String var1, String var2) {
        return addToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String addToString(String var1, String var2, int scale) {
        return addToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    private static BigDecimal add(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.add(var2).setScale(scale, RoundingMode.HALF_UP);
    }

    private static String addToString(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.add(var2).setScale(scale, RoundingMode.HALF_UP).toPlainString();
    }


    public static BigDecimal subtract(double var1, double var2) {
        return subtract(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal subtract(double var1, double var2, int scale) {
        return subtract(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String subtractToString(double var1, double var2) {
        return subtractToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String subtractToString(double var1, double var2, int scale) {
        return subtractToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static BigDecimal subtract(String var1, String var2) {
        return subtract(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal subtract(String var1, String var2, int scale) {
        return subtract(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String subtractToString(String var1, String var2) {
        return subtractToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String subtractToString(String var1, String var2, int scale) {
        return subtractToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    private static BigDecimal subtract(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.subtract(var2).setScale(scale, RoundingMode.HALF_UP);
    }

    private static String subtractToString(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.subtract(var2).setScale(scale, RoundingMode.HALF_UP).toPlainString();
    }


    public static BigDecimal multiply(double var1, double var2) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal multiply(int var1, BigDecimal var2) {
        return multiply(toBigDecimal(var1), var2, 4);
    }

    public static BigDecimal multiply(double var1, BigDecimal var2) {
        return multiply(toBigDecimal(var1), var2, 4);
    }

    public static BigDecimal multiply(int var1, double var2) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal multiply(String var1, double var2) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal multiply(double var1, double var2, int scale) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String multiplyToString(double var1, double var2) {
        return multiplyToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String multiplyToString(double var1, double var2, int scale) {
        return multiplyToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static BigDecimal multiply(String var1, String var2) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal multiply(String var1, String var2, int scale) {
        return multiply(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String multiplyToString(String var1, String var2) {
        return multiplyToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String multiplyToString(String var1, String var2, int scale) {
        return multiplyToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    private static BigDecimal multiply(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.multiply(var2).setScale(scale, RoundingMode.HALF_UP);
    }

    private static String multiplyToString(BigDecimal var1, BigDecimal var2, int scale) {
        return var1.multiply(var2).setScale(scale, RoundingMode.HALF_UP).toPlainString();
    }


    public static BigDecimal divide(double var1, String var2) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), 4);
    }
    public static BigDecimal divide(String var1, BigDecimal var2) {
        return divide(toBigDecimal(var1), var2, 4);
    }

    public static BigDecimal divide(String var1, double var2) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal divide(double var1, double var2) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal divide(double var1, double var2, int scale) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String divideToString(double var1, double var2) {
        return divideToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String divideToString(double var1, double var2, int scale) {
        return divideToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static BigDecimal divide(String var1, String var2) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static BigDecimal divide(String var1, String var2, int scale) {
        return divide(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    public static String divideToString(String var1, String var2) {
        return divideToString(toBigDecimal(var1), toBigDecimal(var2), 4);
    }

    public static String divideToString(String var1, String var2, int scale) {
        return divideToString(toBigDecimal(var1), toBigDecimal(var2), scale);
    }

    private static BigDecimal divide(BigDecimal var1, BigDecimal var2, int scale) {
        if (BigDecimal.ZERO.compareTo(var2) == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return var1.divide(var2, scale, RoundingMode.HALF_UP);
    }

    private static String divideToString(BigDecimal var1, BigDecimal var2, int scale) {
        if (BigDecimal.ZERO.compareTo(var2) == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return var1.divide(var2, scale, RoundingMode.HALF_UP).toPlainString();
    }

    /**
     * 默认保留4位小数
     */
    public static BigDecimal keepDecimals(String var) {
        return toBigDecimal(var).setScale(4, RoundingMode.HALF_UP);
    }

    private static BigDecimal keepDecimals(double var) {
        return toBigDecimal(var).setScale(4, RoundingMode.HALF_UP);
    }

    private static BigDecimal keepDecimals(String var, int scale) {
        return toBigDecimal(var).setScale(scale, RoundingMode.HALF_UP);
    }

    private static BigDecimal keepDecimals(double var, int scale) {
        return toBigDecimal(var).setScale(scale, RoundingMode.HALF_UP);
    }

    public static String keepExchangeDecimals(String var, int scale) {
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < 4 - scale; i++) {
            zeros.append("0");
        }
        return toBigDecimal(var).setScale(scale, RoundingMode.HALF_UP).toPlainString() + zeros;
    }

    public static boolean greaterEqualsThan(String var1, double var2) {
        return greaterEqualsThan(toBigDecimal(var1), toBigDecimal(var2));
    }

    public static boolean greaterEqualsThan(double var1, double var2) {
        return greaterEqualsThan(toBigDecimal(var1), toBigDecimal(var2));
    }

    public static boolean greaterThan(String var1, String var2) {
        return greaterThan(toBigDecimal(var1), toBigDecimal(var2));
    }

    public static boolean greaterThan(String var1, double var2) {
        return greaterThan(toBigDecimal(var1).setScale(4, RoundingMode.HALF_UP), toBigDecimal(var2).setScale(4, RoundingMode.HALF_UP));
    }

    public static boolean greaterThan(String var1, BigDecimal var2) {
        return greaterThan(toBigDecimal(var1), var2);
    }

    public static boolean greaterThan(double var1, BigDecimal var2) {
        return greaterThan(toBigDecimal(var1), var2);
    }

    public static boolean greaterThan(double var1, double var2) {
        return greaterThan(toBigDecimal(var1), toBigDecimal(var2));
    }

    /**
     * 判断是否大于
     *
     * @return true大于 false小于
     */
    public static boolean greaterThan(BigDecimal var1, BigDecimal var2) {
        return (var1.compareTo(var2) > 0);
    }

    public static boolean greaterEqualsThan(BigDecimal var1, BigDecimal var2) {
        return (var1.compareTo(var2) > 0)||(var1.compareTo(var2) == 0);
    }

    public static boolean equals(String var1, String var2) {
        return equals(toBigDecimal(var1), toBigDecimal(var2));
    }

    public static boolean equals(String var1, double var2) {
        return equals(toBigDecimal(var1), toBigDecimal(var2));
    }

    public static boolean equals(double var1, double var2) {
        return equals(toBigDecimal(var1), toBigDecimal(var2));
    }


    public static boolean equalsZero(String var1) {
        return equals(toBigDecimal(var1), BigDecimal.ZERO);
    }

    public static boolean equalsZero(double var1) {
        return equals(toBigDecimal(var1), BigDecimal.ZERO);
    }

    public static boolean equalsZero(BigDecimal var1) {
        return equals(var1, keepDecimals("0"));
    }

    /**
     * 判断是否等于
     *
     * @return true等于 false不等于
     */
    public static boolean equals(BigDecimal var1, BigDecimal var2) {
        return (var1.compareTo(var2) == 0);
    }

    /**
     * BigDecimal BigDecimal(double d);         不推荐使用,精度不能保证
     * BigDecimal BigDecimal(String s);         推荐使用
     * static BigDecimal valueOf(double d);     推荐使用
     */
    private static BigDecimal toBigDecimal(double var) {
        return BigDecimal.valueOf(var);
    }

    private static BigDecimal toBigDecimal(int var) {
        return BigDecimal.valueOf(var);
    }

    public static BigDecimal toBigDecimal(String var) {
        return new BigDecimal(var);
    }


}
