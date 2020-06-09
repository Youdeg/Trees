package ru.dima;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    public static int maxId = 0;

    public static int energy;
    public static List<Tree> allTrees = new ArrayList<Tree>();

    public static int maxIdUp() {
        maxId += 1;
        return maxId;
    }

    public static void generateEnergy() {
        energy = NewMath.randomFromTo(30000, 500000);
    }

    public static void getMean() {
        int life = 0;
        int allMax = 0;
        int allSize = 0;
        int allIn = 0;

        for (int i = 0; i < allTrees.size(); i++) {
            if (Forest.allTrees.get(i).getStatus() == 0) continue;
            allMax += Forest.allTrees.get(i).getMaxAge();
            allSize += Forest.allTrees.get(i).getSize();
            allIn += Forest.allTrees.get(i).getInEnergy();
            life++;
        }

        int meanMaxAge = allMax / life;
        int meanSize = allSize / life;
        int meanIn = allIn / life;

        System.out.println("Средняя продолжительность жизни - " + meanMaxAge);
        System.out.println("Средний размер - " + meanSize);
        System.out.println("Средняя поглощаемость - " + meanIn);
    }
}
