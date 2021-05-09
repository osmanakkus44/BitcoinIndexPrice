import com.bitcoinpriceindex.service.BitcoinRateStatistics;
import com.bitcoinpriceindex.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BitcoinRateStatisticsTest {
    private BitcoinRateStatistics btcRate;

    @BeforeEach
    public void setUp() {
        btcRate = new BitcoinRateStatistics();
    }

    @Test
    @DisplayName("Currency should set to USD as default value.")
    public void testGetDefaultCurrency() {
        assertEquals("USD", btcRate.getCurrency(), "Default currency must be USD.");
    }

    @Test
    @DisplayName("Currency should set to EUR.")
    public void testSetSpecifiedCurrency() {
        btcRate.setCurrency("EUR");
        assertEquals("EUR", btcRate.getCurrency(), "Set currency must be EUR.");
        btcRate.setCurrency("USD");
        assertEquals("USD", btcRate.getCurrency(), "Set currency must be USD.");
        btcRate.setCurrency("GBP");
        assertEquals("GBP", btcRate.getCurrency(), "Set currency must be GBP.");
        btcRate.setCurrency("TRY");
        assertEquals("TRY", btcRate.getCurrency(), "Set currency must be TRY.");
        btcRate.setCurrency("CHF");
        assertEquals("CHF", btcRate.getCurrency(), "Set currency must be CHF.");
    }

    @Test
    @DisplayName("Testing current Bitcoin rates with valid currencies.")
    public void testGetCurrentBtcRate() {
        assertNotNull(btcRate.getCurrentBtcRate(), "Current rate should not be null");

        btcRate.setCurrency("EUR");
        assertNotNull(btcRate.getCurrentBtcRate(), "Current rate should not be null");
        btcRate.setCurrency("GBP");
        assertNotNull(btcRate.getCurrentBtcRate(), "Current rate should not be null");
        btcRate.setCurrency("try");
        assertNotNull(btcRate.getCurrentBtcRate(), "Current rate should not be null");
        btcRate.setCurrency("chf");
        assertNotNull(btcRate.getCurrentBtcRate(), "Current rate should not be null");
    }

    @Test
    @DisplayName("Testing lowest and highest bitcoin rates with valid currencies.")
    public void testGetMonthlyBtcRates() {
        assertNotNull(btcRate.getLowestBtcRateInLastMonth(), "Lowest bitcoin rate should not be null");
        assertNotNull(btcRate.getHighestBtcRateInLastMonth(), "Highest bitcoin rate should not be null");

        btcRate.setCurrency("EUR");
        assertNotNull(btcRate.getLowestBtcRateInLastMonth(), "Lowest bitcoin rate should not be null");
        assertNotNull(btcRate.getHighestBtcRateInLastMonth(), "Highest bitcoin rate should not be null");
        btcRate.setCurrency("GBP");
        assertNotNull(btcRate.getLowestBtcRateInLastMonth(), "Lowest bitcoin rate should not be null");
        assertNotNull(btcRate.getHighestBtcRateInLastMonth(), "Highest bitcoin rate should not be null");
        btcRate.setCurrency("try");
        assertNotNull(btcRate.getLowestBtcRateInLastMonth(), "Lowest bitcoin rate should not be null");
        assertNotNull(btcRate.getHighestBtcRateInLastMonth(), "Highest bitcoin rate should not be null");
        btcRate.setCurrency("chf");
        assertNotNull(btcRate.getLowestBtcRateInLastMonth(), "Lowest bitcoin rate should not be null");
        assertNotNull(btcRate.getHighestBtcRateInLastMonth(), "Highest bitcoin rate should not be null");
    }

    @Test
    @DisplayName("Testing invalid currency situation.")
    void testSetCurrencyException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> btcRate.setCurrency("CNY"));
        assertEquals("Please enter a valid currency: "+ Arrays.toString(Constants.CURRENCIES), exception.getMessage());
        Throwable exception2 = assertThrows(UnsupportedOperationException.class, () -> btcRate.setCurrency("asdfg"));
        assertEquals("Please enter a valid currency: "+ Arrays.toString(Constants.CURRENCIES), exception2.getMessage());
    }
}
