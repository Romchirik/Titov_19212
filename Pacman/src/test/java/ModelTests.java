import org.junit.jupiter.api.Test;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Pacman;

import org.junit.jupiter.api.Assertions;
import static ru.nsu.titov.model.GameObject.checkCollision;
public class ModelTests {
    @Test
    void testCollision(){
        Pacman obj1 = new Pacman(9, 9, 10, 10);
        Pacman obj2 = new Pacman(0, 0, 10, 10);

        Assertions.assertTrue(checkCollision(obj1, obj2));

        obj1.setX( -10);
        obj1.setY(-10);

        Assertions.assertFalse(checkCollision(obj1, obj2));
    }
}
