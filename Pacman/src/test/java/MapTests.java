import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.model.playfield.FieldLoader;
import ru.nsu.titov.model.playfield.FieldObjectsFactory;
import ru.nsu.titov.model.playfield.GameField;
import ru.nsu.titov.model.playfield.Wall;

import java.io.IOException;

public class MapTests {

    @Test
    public void loaderTest() {
        GameField playfield = new GameField();
        try {
            playfield = FieldLoader.loadMap("default_map");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void factoryTest() {
        FieldObjectsFactory factory = new FieldObjectsFactory();

        GameObject tmp = factory.createMapObject(' ', 0, 0);
        Assertions.assertEquals(tmp.getID(), ObjectId.VOID);

        tmp = factory.createMapObject('.', 0, 0);
        Assertions.assertEquals(tmp.getID(), ObjectId.FOOD);

        tmp = factory.createMapObject('#', 0, 0);
        Assertions.assertEquals(tmp.getID(), ObjectId.WALL);

        tmp = factory.createMapObject('0', 0, 0);
        Assertions.assertEquals(tmp.getID(), ObjectId.ENERGIZER);

    }

    @Test
    public void cycleMoveTest(){
        GameField playfield = new GameField();
        try {
            playfield = FieldLoader.loadMap("default_map");
        } catch (IOException e) {
            e.printStackTrace();
        }

        playfield.getObjectAt(30, 15);
    }
}
