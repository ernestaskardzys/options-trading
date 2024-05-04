package info.ernestas.options.trading.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackScholesServiceTest {

    private BlackScholesService blackScholesService;

    @BeforeEach
    void setUp() {
        blackScholesService = new BlackScholesService();
    }

    @Test
    void callPrice() {
        double stockCurrentPrice = 23.75, strikePrice = 15, timeUntilExpirationInYears = 0.5, riskFreeInterestRate = 0.01, volatility = 0.35;

        var result = blackScholesService.callPrice(stockCurrentPrice, strikePrice, timeUntilExpirationInYears, riskFreeInterestRate, volatility);

        assertThat(result).isEqualTo(8.879159263714119);
    }

    @Test
    void putPrice() {
        double stockCurrentPrice = 23.75, strikePrice = 15, timeUntilExpirationInYears = 0.5, riskFreeInterestRate = 0.01, volatility = 0.35;

        var result = blackScholesService.putPrice(stockCurrentPrice, strikePrice, timeUntilExpirationInYears, riskFreeInterestRate, volatility);

        assertThat(result).isEqualTo(0.05434645160435192);
    }
}