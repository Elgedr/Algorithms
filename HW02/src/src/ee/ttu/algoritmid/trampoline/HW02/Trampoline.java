package src.ee.ttu.algoritmid.trampoline.HW02;

public interface Trampoline {

    enum Type {
        NORMAL,
        WITH_FINE,
        WALL
    }

    int getJumpForce();

    Type getType();

}