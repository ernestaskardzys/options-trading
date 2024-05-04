package info.ernestas.options.trading.service;

import org.apache.commons.math3.distribution.NormalDistribution;

public class BlackScholesService {

    // Calculates call option prices using Black-Scholes method.
    public double callPrice(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        NormalDistribution normDist = new NormalDistribution();
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        return stockCurrentPrice * normDist.cumulativeProbability(d1) - strikePrice * Math.exp(-riskFreeInterestRate * timeUntilExpirationInYears) * normDist.cumulativeProbability(d2);
    }

    // Calculates put option prices using Black-Scholes method.
    public double putPrice(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        NormalDistribution normDist = new NormalDistribution();
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        return strikePrice * Math.exp(-riskFreeInterestRate * timeUntilExpirationInYears) * normDist.cumulativeProbability(-d2) - stockCurrentPrice * normDist.cumulativeProbability(-d1);
    }

    protected double getD1(double price, double volatility, double timeUntilExpirationInYears) {
        return price / (volatility * Math.sqrt(timeUntilExpirationInYears));
    }

    protected double getD2(double timeUntilExpirationInYears, double volatility, double d1) {
        return d1 - volatility * Math.sqrt(timeUntilExpirationInYears);
    }

}
