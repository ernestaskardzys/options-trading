package info.ernestas.options.trading.service;

import info.ernestas.options.trading.model.OptionType;
import org.apache.commons.math3.distribution.NormalDistribution;

public class GreekService extends BlackScholesService {

    // Calculate delta function
    public double delta(OptionType type, double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        NormalDistribution normDist = new NormalDistribution();
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        if (type == OptionType.PUT) {
            return normDist.cumulativeProbability(d1) - 1;
        } else {
            return normDist.cumulativeProbability(d1);
        }
    }

    // Calculate gamma function
    public double gamma(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double top = Math.exp(-Math.pow(d, 2) / 2);
        double bottom = stockCurrentPrice * volatility * Math.sqrt(2 * Math.PI * timeUntilExpirationInYears);
        return top / bottom;
    }

    // Calculate rho function
    public double rho(OptionType type, double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        NormalDistribution normDist = new NormalDistribution();
        if (type == OptionType.PUT) {
            return -strikePrice * timeUntilExpirationInYears * Math.exp(-riskFreeInterestRate * timeUntilExpirationInYears) * normDist.cumulativeProbability(-d2);
        } else {
            return strikePrice * timeUntilExpirationInYears * Math.exp(-riskFreeInterestRate * timeUntilExpirationInYears) * normDist.cumulativeProbability(d2);
        }
    }

    // Calculate vega function
    public double vega(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        return stockCurrentPrice * Math.sqrt(timeUntilExpirationInYears) * (Math.exp(-Math.pow(d1, 2) / 2) / Math.sqrt(2 * Math.PI));
    }

    // Calculate theta function
    public double theta(OptionType type, double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        NormalDistribution normDist = new NormalDistribution();
        double p1 = getD1(-stockCurrentPrice * Math.exp(-Math.pow(d1, 2) / 2) * volatility, 2, 2 * Math.PI * timeUntilExpirationInYears);
        double p2 = riskFreeInterestRate * strikePrice * Math.exp(-riskFreeInterestRate * timeUntilExpirationInYears) * normDist.cumulativeProbability(1 - normDist.cumulativeProbability(d2));
        if (type == OptionType.PUT) {
            return p1 + p2;
        } else {
            return p1 - p2;
        }
    }

    // Calculate vanna function
    public double vanna(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        NormalDistribution normDist = new NormalDistribution();
        return -normDist.cumulativeProbability(d1) * d2 / volatility;
    }

    // Calculate vomma function
    public double vomma(double stockCurrentPrice, double strikePrice, double timeUntilExpirationInYears, double riskFreeInterestRate, double volatility) {
        double d1 = getD1((Math.log(stockCurrentPrice / strikePrice) + (riskFreeInterestRate + 0.5 * Math.pow(volatility, 2)) * timeUntilExpirationInYears), volatility, timeUntilExpirationInYears);
        double d2 = getD2(timeUntilExpirationInYears, volatility, d1);
        NormalDistribution normDist = new NormalDistribution();
        return stockCurrentPrice * normDist.cumulativeProbability(d1) * Math.sqrt(timeUntilExpirationInYears) * d1 * d2 / volatility;
    }

}
