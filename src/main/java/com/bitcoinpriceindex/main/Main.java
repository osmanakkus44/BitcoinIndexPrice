package com.bitcoinpriceindex.main;

import com.bitcoinpriceindex.service.BitcoinRateStatistics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BitcoinRateStatistics bitcoinRateStatistics = new BitcoinRateStatistics();

        System.out.println("############ Bitcoin Index Price Application ############");
        System.out.println("############ Press 'Q' for exit #########################");
        String input = "";

        while (!input.equalsIgnoreCase("q")) {
            try {
                System.out.println("Enter the currency: ");
                input = in.nextLine();
                if (!input.equalsIgnoreCase("q")) {
                    bitcoinRateStatistics.setCurrency(input);
                    System.out.println("Current Bitcoin rate in " +
                            input.toUpperCase() + ": " +
                            bitcoinRateStatistics.getCurrentBtcRate());
                    System.out.println("Lowest Bitcoin rate in last 30 days in " +
                            input.toUpperCase() + ": " +
                            bitcoinRateStatistics.getLowestBtcRateInLastMonth());
                    System.out.println("Highest Bitcoin rate in last 30 days in " +
                            input.toUpperCase() + ": " +
                            bitcoinRateStatistics.getHighestBtcRateInLastMonth());
                }
            } catch (UnsupportedOperationException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
