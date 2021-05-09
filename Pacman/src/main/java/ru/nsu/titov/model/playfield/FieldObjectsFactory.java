package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.GameObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class FieldObjectsFactory {
    private final HashMap<Character, Class<?>> availableObjects = new HashMap<>();
    private final HashMap<Character, String> symbolObjectAssociation = new HashMap<>();


    public FieldObjectsFactory() {
        Properties props = new Properties();
        try (InputStream input = FieldObjectsFactory.class.getResourceAsStream("/factory.properties")) {
            props.load(input);
            props.forEach((key, value) -> {
                symbolObjectAssociation.put(key.toString().charAt(0), value.toString());
            });
        } catch (IOException e) {
            //TODO add exception processing
        }
    }

    private void loadInstruction(Character objectName) throws ClassNotFoundException {
        Class<?> loadedClass = Class.forName(symbolObjectAssociation.get(objectName));
        availableObjects.put(objectName, loadedClass);
    }

    public GameObject createMapObject(Character objectName, int logicalX, int logicalY) {

        try {
            if (availableObjects.get(objectName) == null) {
                loadInstruction(objectName);
            }
            return (GameObject) availableObjects.get(objectName)
                    .getDeclaredConstructor(int.class, int.class)
                    .newInstance(logicalX, logicalY);
        } catch (Exception e) {
            return new Void(logicalX, logicalY);
        }
    }
}