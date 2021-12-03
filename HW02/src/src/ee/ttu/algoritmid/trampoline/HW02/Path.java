package src.ee.ttu.algoritmid.trampoline.HW02;

import java.util.ArrayList;
import java.util.List;

public class Path implements Cloneable {
    private List<String> path = new ArrayList<>();
    int fine = 0;

    public Path() {
    }


    public int getFine() {
        return fine;
    }

    public void addPath(String s) {
        this.path.add(s);
    }

    public void addFine(double d) {
        this.fine += d;
    }

    @Override
    public String toString() {
        return "Path{" +
                "path=" + path +
                ", fine=" + fine +
                '}';
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Integer getPathSize() {
        return path.size();
    }

    public List<String> getPath() {
        return path;
    }

    @Override
    public Path clone() {
        try {
            Path clone = (Path) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
