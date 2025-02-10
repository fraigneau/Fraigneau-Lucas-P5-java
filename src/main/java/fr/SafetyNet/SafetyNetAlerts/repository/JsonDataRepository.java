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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import fr.SafetyNet.SafetyNetAlerts.model.Firestation;
import fr.SafetyNet.SafetyNetAlerts.model.Medicalrecord;
import fr.SafetyNet.SafetyNetAlerts.model.Person;
import jakarta.annotation.PostConstruct;
import lombok.Data;

/**
 * Repository for managing data stored in a JSON file.
 */
@Repository
@Data
public class JsonDataRepository {

    private static final Logger logger = LoggerFactory.getLogger(JsonDataRepository.class);
    private static final String filePath = "src/main/resources/data.json";
    private static final Map<Class<?>, String> CLASS_TO_JSON_FIELD = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode rootNode;

    static {
        CLASS_TO_JSON_FIELD.put(Person.class, "persons");
        CLASS_TO_JSON_FIELD.put(Firestation.class, "firestations");
        CLASS_TO_JSON_FIELD.put(Medicalrecord.class, "medicalrecords");
    }

    /**
     * Loads the JSON file into memory after the bean is constructed.
     *
     * @throws IOException if an I/O error occurs
     */
    @PostConstruct
    public void loadJsonFile() throws IOException {
        this.rootNode = objectMapper.readTree(new File(filePath));
        logger.info("JSON file loaded successfully from: {}", filePath);
    }

    /**
     * Retrieves a list of objects of the specified class from the JSON file.
     *
     * @param <T>   the type of objects to retrieve
     * @param klass the class of objects to retrieve
     * @return a list of objects of the specified class
     * @throws IOException if an I/O error occurs
     */
    public <T> List<T> getList(Class<T> klass) throws IOException {
        JsonNode childNode = rootNode.path(JsonField(klass));

        if (!childNode.isArray()) {
            logger.error("Field '{}' is not an array.", JsonField(klass));
            throw new IllegalArgumentException("Field '" + JsonField(klass) + "' is not an array.");
        }

        CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, klass);
        logger.info("List of {} loaded successfully", JsonField(klass));
        return objectMapper.readValue(childNode.traverse(), listType);
    }

    /**
     * Retrieves the JSON field name for the specified class.
     *
     * @param klass the class to retrieve the JSON field name for
     * @return the JSON field name for the specified class
     */
    public static String JsonField(Class<?> klass) {
        String jsonField = CLASS_TO_JSON_FIELD.get(klass);
        if (jsonField == null) {
            logger.error("No JSON field mapped for class: {}", klass.getSimpleName());
            throw new IllegalArgumentException("No JSON field mapped for class: " + klass.getSimpleName());
        }
        return jsonField;
    }

    /**
     * Updates the list of objects of the specified class in the JSON file.
     *
     * @param <T>     the type of objects to update
     * @param klass   the class of objects to update
     * @param persons the list of objects to update
     * @throws IOException if an I/O error occurs
     */
    public <T> void setList(Class<T> klass, List<T> persons) throws IOException {
        try {
            JsonNode copyRootNode = rootNode.deepCopy();
            ObjectNode newRootNode = (ObjectNode) copyRootNode;
            newRootNode.set(JsonField(klass), objectMapper.valueToTree(persons));
            objectMapper.writeValue(new File(filePath), newRootNode);
            logger.info("JSON file updated successfully at: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to update JSON file at: {}", filePath);
            throw e;
        }
    }
}