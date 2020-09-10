package com.kanavi.test.current;

import com.kanavi.test.util.UnsafeInstance;
import lombok.Data;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author hailan
 * @version 1.0
 * @className LanLock
 * @description TODO 公平锁
 * @date 2020-09-10 18:40
 */
@Data
public class LanLock {
    /*
     * 当前加锁状态，记录加锁的次数
     */
    private volatile int state = 0;

    /*
     * 当前持有锁的线程
     */
    private Thread lockHolder;

    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();


    public boolean aquire() {
        //cas比较与交换-原子算法
        Thread current = Thread.currentThread();
        int c = getState();
        //初始状态
        if (c == 0) { //同步器还没有被持有
            if ((waiters.size() == 0 || current == waiters.peek()) && compareAndSwapState(0, 1)) {
                setLockHolder(current);
                return true;
            }
        }
        return false;
    }

    //加锁
    public void lock() {
        if (aquire()) {
            return;
        }
        Thread current = Thread.currentThread();
        waiters.add(current);
        for (; ; ) {
//           Thread.yield();
            if ((current == waiters.peek()) && aquire()) {
                waiters.poll();//T2从队列中移除
                break;
            }
            //阻塞当前线程
            LockSupport.park(current); //保存对线程的引用
        }
    }


    public void unlock() {
        if (Thread.currentThread() != lockHolder) {
            throw new RuntimeException("lockHolder is not current thread");
        }
        int state = getState();
        if (compareAndSwapState(state, 0)) {
            setLockHolder(null);
            Thread first = waiters.peek();
            if (first != null) {
                LockSupport.unpark(first);
            }
        }
    }

    public final boolean compareAndSwapState(int except, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);

    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(LanLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }
}
