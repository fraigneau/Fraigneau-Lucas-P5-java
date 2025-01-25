package fr.SafetyNet.SafetyNetAlerts.service;

import java.util.List;

public interface CrudService {

    public List<Object> getAll();

    public Object findById();

    public Object CreateById();

    public Object deleteById();

    public Object update();

}
