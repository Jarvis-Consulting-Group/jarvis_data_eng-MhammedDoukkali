package ca.jrvs.apps.service;

import ca.jrvs.apps.dao.PositionDao;
import ca.jrvs.apps.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PositionService {

    private static final Logger logger = LoggerFactory.getLogger(PositionService.class);

    private final PositionDao dao;

    public PositionService(PositionDao dao) {
        this.dao = dao;
    }

    /**
     * Processes a buy order and updates the database accordingly
     * @param ticker Ticker symbol for the stock
     * @param numberOfShares Number of shares to buy
     * @param price Price per share
     * @return The position in our database after processing the buy
     */
    
    public Position buy(String ticker, int numberOfShares, double price) {
        // fetch existing or create a new one
        Position position = dao.findById(ticker).orElse(new Position(ticker, 0, 0.0));
        
        // update the position with the new buy
        position.setNumOfShares(position.getNumOfShares() + numberOfShares);
//        position.setTicker(position.getTotalCost() + numberOfShares * price);

        // save the updated position to the database
        dao.save(position);
        return position;
    }

    /**
     * Sells all shares of the given ticker symbol
     * @param ticker Ticker symbol for the stock
     */

    public void sell(String ticker) {
        // Find the position and remove it from the database
        Optional<Position> position = dao.findById(ticker);
        position.ifPresent(p ->dao.deleteById(ticker));
    }
}
