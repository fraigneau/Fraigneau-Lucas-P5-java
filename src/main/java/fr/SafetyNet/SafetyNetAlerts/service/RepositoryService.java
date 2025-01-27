package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

public interface RepositoryService<T> {

    public List<T> readAll();

    public T readById(String... args);

    public T Create(T newObject);

    public void deleteById(String... args);

    public T update(T newObject, String... args);

}
