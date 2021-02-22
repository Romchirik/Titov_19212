import org.apache.log4j.Logger;
import ru.nsu.playfield.*;

import java.io.IOException;

public class Interpreter {
    static final Logger logger = Logger.getLogger(Interpreter.class);

    public static void main(String[] args) {
        Playfield playfield;

        try {
            playfield = PlayfieldLoader.loadField(args[0]);
        } catch (IOException e) {
            logger.error("Unable to open/locate file: " + args[0]);
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Input file expected");
            return;
        }



    }
}
