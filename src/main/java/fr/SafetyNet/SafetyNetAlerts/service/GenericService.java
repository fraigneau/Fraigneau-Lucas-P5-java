package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

/**
 * Generic service interface for managing entities.
 *
 * @param <T> the type of the entity
 */
public interface GenericService<T> {

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    public List<T> readAll();

    /**
     * Retrieves an entity by its identifier.
     *
     * @param args the identifier of the entity to retrieve
     * @return the entity with the specified identifier
     */
    public T readById(String... args);

    /**
     * Creates a new entity.
     *
     * @param newObject the entity to create
     * @return the created entity
     */
    public T create(T newObject);

    /**
     * Deletes an entity by its identifier.
     *
     * @param args the identifier of the entity to delete
     */
    public void deleteById(String... args);

    /**
     * Updates an existing entity.
     *
     * @param newObject the updated entity data
     * @param args      the identifier of the entity to update
     * @return the updated entity
     */
    public T update(T newObject, String... args);

}
