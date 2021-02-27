package ru.nsu.context;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;


public class Context {

    static final Logger logger = Logger.getLogger(Context.class);

    private boolean skipNextInstruction = false;

    public Deque<Integer> stack;
    public InstructionPointer instructionPointer;
    public Playfield playfield;

    private Context() {
    }

    public Context(String playfieldPath) throws IOException {
        logger.debug("Initializing execution context...");
        stack = new ArrayDeque<>();
        instructionPointer = new InstructionPointer();
        playfield = PlayfieldLoader.loadField(playfieldPath);
    }

    public void setSkipTrue() {
        skipNextInstruction = true;
    }

    public void step() {
        instructionPointer.step();
        if (skipNextInstruction) {
            instructionPointer.step();
            skipNextInstruction = false;
        }
    }

    public Character getCurrentInstruction() {
        return playfield.getInstruction(instructionPointer.getRow(), instructionPointer.getColumn());
    }

    public void setInstruction(Integer row, Integer column, Character instruction) {
        playfield.setInstruction(row, column, instruction);
    }
}
