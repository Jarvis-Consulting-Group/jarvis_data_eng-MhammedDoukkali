import ca.jrvs.apps.dao.QuoteDao;
import ca.jrvs.apps.models.Quote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;


public class QuoteDaoTest {

    private QuoteDao quoteDao;
    private Connection mockConnection;


    @BeforeEach
    public void setUp() {
        mockConnection = mock(Connection.class);
        quoteDao = new QuoteDao(mockConnection);
    }

    @Test
    public void testSave() {
        Quote quote = new Quote("IBM", "170.5500");
        when(quoteDao.save(quote)).thenReturn(quote);
    }

    @Test
    public void testFindById() {
        String ticker = "IBM";
        Quote expectedQuote = new Quote(ticker, "170.5500");
        when(quoteDao.findById(ticker)).thenReturn(Optional.of(expectedQuote));

        Optional<Quote> retrievedQuote = quoteDao.findById(ticker);
        assertTrue(retrievedQuote.isPresent());
        assertEquals(expectedQuote, retrievedQuote.get());
    }



}
