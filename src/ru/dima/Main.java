package ru.dima;

public class Main {
    public static int life = 0;

    public static void main(String[] args) throws InterruptedException {
        Tree q = new Tree();
        Tree w = new Tree();
        Tree e = new Tree();
        Tree r = new Tree();
        Tree t = new Tree();
        Tree y = new Tree();
        Tree u = new Tree();
        Tree q2 = new Tree();
        Tree w2 = new Tree();
        Tree e2 = new Tree();
        Tree r2 = new Tree();
        Tree t2 = new Tree();
        Tree y2 = new Tree();
        Tree u2 = new Tree();

        int turn = 0;
        int treeThisTurn;
        int lifeBack = 0;
        int lifeAll = 0;
        while (turn < 50000) {
            Forest.generateEnergy();
            System.out.print("Энергии до еды - " + Forest.energy);
            treeThisTurn = Forest.allTrees.size();
            for (int i = 0; i < treeThisTurn; i++) {
                if (Forest.allTrees.get(i).getStatus() == 0) {
                    continue;
                }
                Forest.allTrees.get(i).nextTurn();
                lifeBack++;
            }
            System.out.println("  Энергии после еды - " + Forest.energy);
            System.out.println("Деревьев - " + life + " (было " + lifeBack + ")" + " (за всё время - " + Forest.allTrees.size() + ")");
            Forest.getMean();
            System.out.println("------------------");
            if (life == 0) {
                System.out.println("Все вымерли :(");
                break;
            }
            life = 0;
            lifeBack = 0;
            turn++;
            // Thread.sleep(5000);
        }
    }
}
