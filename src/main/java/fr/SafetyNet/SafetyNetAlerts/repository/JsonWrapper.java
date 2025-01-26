package fr.SafetyNet.SafetyNetAlerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import fr.SafetyNet.SafetyNetAlerts.model.FireStation;
import fr.SafetyNet.SafetyNetAlerts.model.MedicalRecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import jakarta.annotation.PostConstruct;

@Repository
public class JsonWrapper {

    private static final Logger logger = LoggerFactory.getLogger(JsonWrapper.class);
    private static final String filePath = "src/main/resources/data.json";
    private static final Map<Class<?>, String> CLASS_TO_JSON_FIELD = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode rootNode;

    static {
        CLASS_TO_JSON_FIELD.put(Person.class, "persons");
        CLASS_TO_JSON_FIELD.put(FireStation.class, "firestations");
        CLASS_TO_JSON_FIELD.put(MedicalRecord.class, "medicalrecords");
    }

    @PostConstruct
    public void loadJsonFile() throws IOException {
        this.rootNode = objectMapper.readTree(new File(filePath));
        logger.info("JSON file loaded successfully from: {}", filePath);
    }

    public <T> List<T> getList(Class<T> clazz) throws IOException {
        JsonNode childNode = rootNode.path(JsonField(clazz));

        if (!childNode.isArray()) {
            throw new IllegalArgumentException("Field '" + JsonField(clazz) + "' is not an array.");
        }

        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz);
        return objectMapper.readValue(childNode.traverse(), listType);
    }

    public static String JsonField(Class<?> clazz) {
        String jsonField = CLASS_TO_JSON_FIELD.get(clazz);
        if (jsonField == null) {
            throw new IllegalArgumentException("No JSON field mapped for class: " + clazz.getSimpleName());
        }
        return jsonField;
    }
}