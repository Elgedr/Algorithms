package src.ee.ttu.algoritmid.trampoline.HW02;

public class TrampolineC implements Trampoline {
    int jumpForce;
    Type type;
    String direction;

    public TrampolineC(int jumpForce, Type type) {
        this.jumpForce = jumpForce;
        this.type = type;

    }

    @Override
    public int getJumpForce() {
        return 0;
    }

    @Override
    public Type getType() {
        return null;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
