package src.ee.ttu.algoritmid.trampoline.HW02;

import java.util.ArrayList;
import java.util.List;

public class ResultC implements Result {
    private List<String> jumps;
    private int fine;

    public ResultC() {
        this.jumps = new ArrayList<>();
        this.fine = 0;
    }

    @Override
    public List<String> getJumps() {
        return jumps;
    }

    @Override
    public int getTotalFine() {
        return fine;
    }

    public void addJump(String jump) {
        jumps.add(jump);
    }

    public void addFine(int fine) {
        this.fine += fine;
    }

    @Override
    public String toString() {
        return "ResultC{" +
                "jumps=" + jumps +
                ", fine=" + fine +
                '}';
    }
}
