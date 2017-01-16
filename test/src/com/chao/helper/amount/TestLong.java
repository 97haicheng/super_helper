package com.chao.helper.amount;

/**
 * Created by think on 2016/11/29.
 */
public class TestLong {
    public static void main(String[] args) {
        try {
            String amount = AmountUtils.changeF2Y("99999999999999999");
            System.out.println(amount);

            long l = Long.parseLong(amount);
            System.out.println(AmountUtils.changeY2F(l));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
