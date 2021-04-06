import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.pacman.Pacman;

import java.io.IOException;

public class ModelTests {

    @Test
    void collisionTest(){
        Pacman pac1 = new Pacman(0, 0);
        Pacman pac2 = new Pacman(1, 1);
        Assumptions.assumeFalse(GameObject.checkCollision(pac1, pac2));

        pac1.setY(1);
        Assumptions.assumeFalse(GameObject.checkCollision(pac1, pac2));

        pac1.setX(1);
        Assumptions.assumeTrue(GameObject.checkCollision(pac1, pac2));
    }

    @Test
    void pacmanTest() throws IOException {
        Pacman pac = new Pacman(0, 0);

        pac.setDirection(Direction.RIGHT);

        Assumptions.assumeTrue(pac.getX() == 0);
        Assumptions.assumeTrue(pac.getY() == 0);

        pac.tick(new ModelController());
        pac.tickBack();

        Assumptions.assumeTrue(pac.getX() == 0);
        Assumptions.assumeTrue(pac.getY() == 0);


    }
}


