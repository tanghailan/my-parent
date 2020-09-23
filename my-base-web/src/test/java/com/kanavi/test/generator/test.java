package com.kanavi.test.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class test {
    public static void main(String[] args) throws Throwable {
        DependedPath dependedPath = new DependedPath();
        List<String> usedPath = dependedPath.getUsedPath();// 被依赖的节点

        List<String> usingPath = dependedPath.getUsingPath();// 依赖别人的节点

        ExecutorService pool = Executors.newCachedThreadPool();// 线程池
        Map<String, BaseDevice> devicesMap = new HashMap<>();// 结果存储器

        Latch latch = new CountDownLatch(usedPath.size());// 第一个锁存器
        List<Future<List<BaseDevice>>> usedPathResults = new ArrayList<>();
        for (String path : usedPath) {
            GetDataThread getBriefDeviceThread = new GetDataThread(path, latch);
            Future<List<BaseDevice>> future = pool.submit(getBriefDeviceThread);
            usedPathResults.add(future);
        }

        for (Future<List<BaseDevice>> future : usedPathResults) {
            List<BaseDevice> deviceList = future.get();
            for (BaseDevice baseDevice : deviceList) {
                devicesMap.put(baseDevice.getNameKey(), baseDevice);
            }

        }
        latch.await();

        System.out.println(devicesMap);
        System.out.println("usedPath finished");


        Latch latch2 = new CountDownLatch(usingPath.size());// 第二个锁存器
        List<Future<List<BaseDevice>>> otherResults = new ArrayList<>();
        for (String path : usingPath) {
            GetDataThread getBriefDeviceThread = new GetDataThread(path, latch2);
            Future<List<BaseDevice>> future = pool.submit(getBriefDeviceThread);
            otherResults.add(future);
        }

        for (Future<List<BaseDevice>> future : otherResults) {
            List<BaseDevice> deviceList = future.get();
            for (BaseDevice baseDevice : deviceList) {
                devicesMap.put(baseDevice.getNameKey(), baseDevice);
            }

        }
        System.out.println(devicesMap);
        latch2.await();

        pool.shutdown();// 关闭线程池

    }

}
