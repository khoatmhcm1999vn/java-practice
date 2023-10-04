package com.mk.examples.demo;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(prices));
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     *
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int leftIndex = 0;
        int rightIndex = 1;
        int maxProfit = 0;
        while (rightIndex < prices.length) {
            if (prices[leftIndex] < prices[rightIndex]) {
                int profit = prices[rightIndex] - prices[leftIndex];
                maxProfit = Integer.max(profit, maxProfit);
            } else {
                leftIndex = rightIndex;
            }

            rightIndex++;
        }

        return maxProfit;
    }
}
