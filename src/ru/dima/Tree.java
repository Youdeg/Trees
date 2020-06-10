package ru.dima;

public class Tree {
    private final int id;
    private int size;
    private int inEnergy;
    private int maxAge;
    private int age;
    private int altruistic;
    private int doblueChild;
    private int fertility;
    private Tree mother;
    private Tree father;
    private int sex;
    private int breeding;

    public int getBreeding() {
        return breeding;
    }

    public Tree getFather() {
        return father;
    }

    public int getSex() {
        return sex;
    }

    public Tree getMother() {
        return mother;
    }

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
        this.breeding = NewMath.randomFromTo(1, 5);
        this.sex = NewMath.randomFromTo(1, 2);

        this.age = 0;
        this.status = 1;

        Forest.allTrees.add(this);
    }

    public Tree(Tree par) {
        this.id = Forest.maxIdUp();
        this.size = par.size + NewMath.randomFromTo(0, 1);
        this.size = this.size - NewMath.randomFromTo(0, 1);

        if (size <= 0) {
            size = 1;
        }

        this.inEnergy = par.inEnergy + NewMath.randomFromTo(0, 1);
        this.inEnergy = this.inEnergy - NewMath.randomFromTo(0, 1);

        if (inEnergy <= 0) {
            inEnergy = 1;
        }

        this.maxAge = par.maxAge + NewMath.randomFromTo(0, 1);
        this.maxAge = this.maxAge - NewMath.randomFromTo(0, 1);

        if (maxAge < 2) {
            maxAge = 2;
        }

        this.altruistic = par.altruistic + NewMath.randomFromTo(0, 1);
        this.altruistic = this.altruistic - NewMath.randomFromTo(0, 1);

        if (altruistic < 0) {
            altruistic = 0;
        } else if (altruistic > 100) {
            altruistic = 100;
        }

        this.fertility = par.fertility + NewMath.randomFromTo(0, 1);
        this.fertility = this.fertility - NewMath.randomFromTo(0, 1);

        this.breeding = par.breeding + NewMath.randomFromTo(0, 5);
        this.breeding = this.breeding - NewMath.randomFromTo(0, 1);

        this.age = 0;
        this.status = 1;
        this.sex = NewMath.randomFromTo(1, 2);
        Forest.noSex++;

        Forest.allTrees.add(this);
    }

    public Tree(Tree father, Tree mother) {
        this.id = Forest.maxIdUp();

        this.size = (father.getSize() + mother.getSize()) / 2;
        this.inEnergy = (father.getInEnergy() + mother.getInEnergy()) / 2;
        this.maxAge = (father.getMaxAge() + mother.getMaxAge()) / 2;
        this.altruistic = (father.getAltruistic() + mother.getAltruistic()) / 2;
        this.fertility = (father.getFetility() + mother.getFetility()) / 2;
        this.breeding = (father.getBreeding() + mother.getBreeding()) / 2;
        this.sex = NewMath.randomFromTo(1, 2);

        this.age = 0;
        this.status = 1;
        Forest.withSex++;

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

        if (NewMath.randomFromTo(1, 10 + size) == 1) {
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
        Forest.allMax += getMaxAge();
        Forest.allSize += getSize();
        Forest.allIn += getInEnergy();
        Forest.allAltruistic += getAltruistic();
        Forest.allChildGenerate += getFetility();
        Forest.allBreeding += getBreeding();
    }

    public void died() {
        status = 0;
    }

    private void newTrees() {
       if (breeding < 10) {
            newTreesNonSex();
        } else if (breeding >= 20) {
            newTreesSex();
        } else {
            if (NewMath.randomFromTo(1,2) == 2) {
                if (sex == 1) {
                newTreesSex();
                }
            } else {
                newTreesNonSex();
            }
        }
   }

    private void newTreesNonSex() {
        for (int i = 0; i < NewMath.randomFromTo(0, 5 + doblueChild + fertility); i++) {
            Tree newTree = new Tree(this);
        }
    }

    private void newTreesSex() {
        Tree randomTree = Forest.getParrent();
        if (randomTree.getSex() == 2) {
            for (int i = 0; i < NewMath.randomFromTo(0, 5 + doblueChild + fertility); i++) {
                Tree newTree = new Tree(this, randomTree);
            }
        }
    }

    public String toString() {
        return this.id + "";
    }
}
