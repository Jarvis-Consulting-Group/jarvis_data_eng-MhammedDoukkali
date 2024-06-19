package ca.jrvs.apps.dao;

import ca.jrvs.apps.dao.db.DatabaseUtil;
import ca.jrvs.apps.models.Quote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuoteDao implements CrudDao<Quote, String>{

//    private Map<String, Quote> quoteDatabase = new HashMap<>();
    private Quote quote;

    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
//        if (entity == null || entity.getSymbol() == null) {
//            throw new IllegalArgumentException("Entity and ID must not be null");
//        }
//        quoteDatabase.put(entity.getSymbol(), entity);
//        return entity;
        String sql = "INSERT INTO quotes (ticker, price) VALUES (?, ?) " +
                        "ON CONFLICT (ticker) DO UPDATE SET price = EXCLUDED.price";

        try (Connection conn= DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, quote.getSymbol());
            stmt.setString(2,quote.getPrice());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quote;

    }

    @Override
    public Optional<Quote> findById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        return Optional.ofNullable(quoteDatabase.get(id));
    }

    @Override
    public Iterable<Quote> findAll() {
        return quoteDatabase.values();
    }

    @Override
    public void deleteById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        quoteDatabase.remove(id);
    }

    @Override
    public void deleteAll() {
        quoteDatabase.clear();
    }
}
