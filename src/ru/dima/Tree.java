package ru.dima;

public class Tree {
    private final int id;
    private int size;
    private int energy;
    private int inEnergy;
    private int maxAge;
    private int age;
    private int altruistic;
    private int doblueChild;
    private int fertility;

    public int getFetility() {
        return fertility;
    }

    public int getAltruistic() {
        return altruistic;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getEnergy() {
        return energy;
    }

    public int getInEnergy() {
        return inEnergy;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getAge() {
        return age;
    }

    public int getStatus() {
        return status;
    }

    private int status;

    public Tree() {
        this.id = Forest.maxIdUp();

        this.size = NewMath.randomFromTo(10, 20);
        this.inEnergy = NewMath.randomFromTo(10, 20);
        this.maxAge = NewMath.randomFromTo(3, 20);
        this.altruistic = NewMath.randomFromTo(10, 40);
        this.fertility = NewMath.randomFromTo(0, 3);

        this.age = 0;
        this.status = 1;

        Forest.allTrees.add(this);
    }

    public Tree(Tree par) {
        this.id = Forest.maxIdUp();
        this.size = par.size + NewMath.randomFromTo(0, 4);
        this.size = par.size - NewMath.randomFromTo(0, 4);

        if (size <= 0) {
            size = 1;
        }

        this.inEnergy = par.inEnergy + NewMath.randomFromTo(0, 4);
        this.inEnergy = par.inEnergy - NewMath.randomFromTo(0, 4);

        if (inEnergy <= 0) {
            inEnergy = 1;
        }

        this.maxAge = par.maxAge + NewMath.randomFromTo(0, 3);
        this.maxAge = par.maxAge - NewMath.randomFromTo(0, 3);

        if (maxAge < 2) {
            maxAge = 2;
        }

        this.altruistic = par.altruistic + NewMath.randomFromTo(0, 10);
        this.altruistic = par.altruistic - NewMath.randomFromTo(0, 10);

        if (altruistic < 0) {
            altruistic = 0;
        } else if (altruistic > 100) {
            altruistic = 100;
        }

        this.fertility = par.fertility + NewMath.randomFromTo(0, 2);
        this.fertility = par.fertility - NewMath.randomFromTo(0, 2);

        this.age = 0;
        this.status = 1;

        Forest.allTrees.add(this);
    }

    public void nextTurn() {
        int needEnergy = size * inEnergy + fertility * 10;
        int getEnergy = inEnergy * size * 10;
        int leftoverEnergy = 0;
        if (needEnergy > Forest.energy) {
            died();
            return;
        }

        if (getEnergy > Forest.energy) {
            getEnergy = Forest.energy;
        }

        if (getEnergy >= needEnergy) {
            Forest.energy = Forest.energy - (needEnergy + (getEnergy - needEnergy) * altruistic / 100);
            if (Forest.energy < 0) Forest.energy = 0;
            leftoverEnergy = needEnergy + (getEnergy - needEnergy) * altruistic / 100;
            for (int i = 0; i < leftoverEnergy / 100; i++) {
                doblueChild++;
            }
        } else {
            died();
            return;
        }

        if ((age & 1) == 0) {
            newTrees();
        }

        age++;
        if (age == maxAge) {
            died();
            return;
        }
        Main.life++;
    }

    public void died() {
        status = 0;
    }

    private void newTrees() {
        for (int i = 0; i < NewMath.randomFromTo(0, 5 + doblueChild + fertility); i++) {
            Tree newTree = new Tree(this);
        }
    }

    public String toString() {
        return this.id + "";
    }
}
