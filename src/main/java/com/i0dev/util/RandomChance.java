package com.i0dev.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomChance<E> {
    private final NavigableMap<Double, E> map;
    private final Random random;
    private double total;

    public RandomChance() {
        this(new Random());
    }

    public RandomChance(Random random) {
        this.map = new TreeMap();
        this.total = 0.0D;
        this.random = random;
    }

    public void add(double weight, E result) {
        if (!(weight <= 0.0D)) {
            this.total += weight;
            this.map.put(this.total, result);
        }
    }

    public E next() {
        double value = this.random.nextDouble() * this.total;
        return this.map.higherEntry(value).getValue();
    }
}
