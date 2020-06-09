package ru.dima;

public class NewMath {
    public static int randomFromTo(int from, int to) {
        to -= from;
        return (int) (Math.random() * ++to) + from;
    }
}
