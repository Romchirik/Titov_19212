package ru.nsu;

import ru.nsu.playfield.Playfield;


import java.util.Deque;

public class Context {
    public Deque<Integer> stack;
    public InstructionPointer instructionPointer;
    public Playfield playfield;
}
