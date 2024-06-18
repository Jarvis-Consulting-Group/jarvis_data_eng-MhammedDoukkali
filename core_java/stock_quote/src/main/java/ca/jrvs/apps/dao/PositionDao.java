package ca.jrvs.apps.dao;

import ca.jrvs.apps.models.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PositionDao implements CrudDao<Position, String> {

    private Map<String, Position> positionDatabase = new HashMap<>();

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        if (entity == null || entity.getTicker() == null ) {
            throw new IllegalArgumentException("Entity and Id must not be null");
        }
        positionDatabase.put(entity.getTicker(), entity);
        return entity;
    }

    @Override
    public Optional<Position> findById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        return Optional.ofNullable(positionDatabase.get(id));
    }

    @Override
    public Iterable<Position> findAll() {
        return positionDatabase.values();
    }

    @Override
    public void deleteById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        positionDatabase.remove(id);
    }

    @Override
    public void deleteAll() {
        positionDatabase.clear();
    }

}
