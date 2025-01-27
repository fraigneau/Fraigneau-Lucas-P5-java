package fr.SafetyNet.SafetyNetAlerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudRestController<T> {

    public List<T> readAll();

    public T readById(@PathVariable String... args);

    public T create(@RequestBody T entity);

    public T update(@RequestBody T entity, @PathVariable String... args);

    public void delete(@PathVariable String... args);

}
