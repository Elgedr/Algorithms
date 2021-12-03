package src.ee.ttu.algoritmid.trampoline.HW02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HW02 implements TrampolineCenter {
    private HashMap<List<String>, Integer> results2 = new HashMap<List<String>, Integer>();
    private Result result;

    @Override
    public Result play(Trampoline[][] map) {
        List<String> currentPath = new ArrayList<>();
        findPaths(map, 0, 0, currentPath, 0);

        System.out.println(results2);
//        System.out.println(map.length);
//        System.out.println(map[0].length);
        sortResults();

        return result;
    }


    private void findPaths(Trampoline[][] trampoline, int x, int y, List<String> currentPath, int currentFine) {
        int force = trampoline[y][x].getJumpForce();
//        System.out.println("current" + currentPath + " \nx:" + x + "\ty:" + y + "\tforce:" + force);

        if (force == 0) {
            results2.put(currentPath, currentFine);
        } else {
            if (checkIfOk(trampoline, y, force, trampoline.length - 1)) {

                List<String> pathCopy = new ArrayList<>(currentPath);
                String jumpName = "S" + force;
                pathCopy.add(jumpName);
//                System.out.println("copyyyyy" + pathCopy);
//                System.out.println("original" + currentPath);
                if (trampoline[y + force][x].getType() == Trampoline.Type.WITH_FINE) {
                    currentFine += (trampoline[y + force][x].getJumpForce());
                }
                findPaths(trampoline, x, y + force, pathCopy, currentFine);

            }
            if (checkIfOk(trampoline, x, force, trampoline[0].length - 1)) {
                List<String> pathCopy2 = new ArrayList<>(currentPath);
                String jumpName = "E" + force;
                pathCopy2.add(jumpName);
                if (trampoline[y][x + force].getType() == Trampoline.Type.WITH_FINE) {
                    currentFine += (trampoline[y][x + force].getJumpForce());
                }
                findPaths(trampoline, x + force, y, pathCopy2, currentFine);
            }
        }
    }


    private boolean checkIfOk(Trampoline[][] trampolines, int current, int force, int tresHold) {
        if (current + force > tresHold) {
            return false;
        }
        return true;
    }

    private void sortResults() {
        ArrayList<Path> paths = new ArrayList<>();
        for (List<String> keys : results2.keySet()) {
            Path path1 = new Path();
            path1.setPath(keys);
            path1.setFine(results2.get(keys));
            paths.add(path1);
        }
        paths.sort(Comparator.comparing(Path::getPathSize).thenComparing(Path::getFine));
        finalRes(paths);

    }

    private Result finalRes(ArrayList<Path> listt) {
        ResultC resultC = new ResultC();
        for (String s : listt.get(0).getPath()) {
            resultC.addJump(s);
        }
        resultC.addFine(listt.get(0).getFine());
        this.result = resultC;
        return resultC;
    }


    public static void main(String[] args) {
        int[][] forceMap = {
                {2, 2, 2, 2, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 2, 1, 0}
        };

        Trampoline[][] map = new Trampoline[forceMap.length][forceMap[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int finalI = i;
                int finalJ = j;
                map[i][j] = new Trampoline() {
                    @Override
                    public int getJumpForce() {
                        return forceMap[finalI][finalJ];
                    }

                    @Override
                    public Type getType() {
                        return Type.NORMAL;
                    }
                };
            }
        }


        HW02 solution = new HW02();

        Result gameResult = solution.play(map);
        System.out.println(gameResult);


    }


}