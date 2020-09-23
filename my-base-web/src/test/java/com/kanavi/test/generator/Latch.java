package com.kanavi.test.generator;

public abstract  class Latch {
    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    public abstract void await() throws InterruptedException;

    public abstract void countdown();

    public abstract int getUnarrived();
}
