import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.pacman.Pacman;

public class ModelTests {

    @Test
    void collisionTest() {
        Pacman pac1 = new Pacman(0, 0);
        Pacman pac2 = new Pacman(1, 1);
        Assumptions.assumeFalse(GameObject.checkCollision(pac1, pac2));

        pac1.setY(1);
        Assumptions.assumeFalse(GameObject.checkCollision(pac1, pac2));

        pac1.setX(1);
        Assumptions.assumeTrue(GameObject.checkCollision(pac1, pac2));
    }

}


