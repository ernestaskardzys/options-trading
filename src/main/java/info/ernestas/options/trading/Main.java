package info.ernestas.options.trading;

import info.ernestas.options.trading.service.BlackScholesService;

public class Main {
    private static final BlackScholesService service = new BlackScholesService();

    public static void main(String[] args) {
        double stockCurrentPrice = 100, strikePrice = 105, timeUntilExpirationInYears = 0.5, riskFreeInterestRate = 0.05, volatility = 0.2;

        System.out.println("Call Value: " + service.callPrice(stockCurrentPrice, strikePrice, timeUntilExpirationInYears, riskFreeInterestRate, volatility));
        System.out.println("Put value: " + service.putPrice(stockCurrentPrice, strikePrice, timeUntilExpirationInYears, riskFreeInterestRate, volatility));
    }
}