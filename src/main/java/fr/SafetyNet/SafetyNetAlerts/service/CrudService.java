package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

public interface CrudService<T> {

    public List<T> getAll();

    public T findById(String... args);

    public T Create(T newObject);

    public void deleteById();

    public T update();

}
