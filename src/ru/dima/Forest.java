package ru.dima;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    public static int maxId = 0;
    public static int withSex = 0;
    public static int noSex = 0;
    public static int life = 0;
    public static int allMax = 0;
    public static int allSize = 0;
    public static int allIn = 0;
    public static int allAltruistic = 0;
    public static int allChildGenerate = 0;
    public static int allBreeding = 0;

    public static int energy;
    public static List<Tree> allTrees = new ArrayList<Tree>();

    public static int maxIdUp() {
        maxId += 1;
        return maxId;
    }

    public static void generateEnergy() {
        energy = NewMath.randomFromTo(50000, 500000);
    }

    public static void getMean() {
        life = Main.life;

        int meanMaxAge = allMax / life;
        int meanSize = allSize / life;
        int meanIn = allIn / life;
        int meanAltruistic = 100 - allAltruistic / life;
        int meanChildGenerate = allChildGenerate / life;
        int meanBreeding = allBreeding / life;

        System.out.println("Средняя продолжительность жизни - " + meanMaxAge + "   Средний альтруизм - " + meanAltruistic);
        System.out.println("Средний размер - " + meanSize + "   Средняя плодовитость - " + meanChildGenerate);
        System.out.println("Средняя поглощаемость - " + meanIn + " Статистика полового размножения - " + meanBreeding + "   (П - " + withSex + " Б - " + noSex + ")");

        allMax = 0;
        allSize = 0;
        allIn = 0;
        allAltruistic = 0;
        allChildGenerate = 0;
        allBreeding = 0;
    }

    public static Tree getParrent() {
        for (int i = 0; i < 50; i++) {
            Tree randomTree = randomTree();
            if (randomTree.getSex() == 2) {
                return randomTree;
            }
        }
        return randomTree();
    }

    public static Tree randomTree() {
        while (true) {
            Tree randomTree = allTrees.get(NewMath.randomFromTo(0, allTrees.size() - 1));
            if (randomTree.getStatus() == 1) {
                return randomTree;
            }
        }
    }
}
