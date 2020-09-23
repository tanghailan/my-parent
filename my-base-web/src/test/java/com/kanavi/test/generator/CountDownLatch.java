package com.kanavi.test.generator;

public class CountDownLatch extends Latch{
    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            // 当limit >0时，进入阻塞
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void countdown() {
        synchronized (this) {
            if (limit <= 0)
                throw new IllegalStateException("all of task already arrived");
            // 使limit减1，并通知阻塞进程。
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }

}
