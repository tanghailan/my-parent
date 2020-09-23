package com.kanavi.test.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GetDataThread implements Callable<List<BaseDevice>> {

    Latch lanch;
    private String deviceName;

    public GetDataThread(String deviceName, Latch latch) {
        this.deviceName = deviceName;
        this.lanch = latch;
    }

    @Override
    public List<BaseDevice> call() throws Exception {
        BaseDevice baseDevice = new BaseDevice();
        baseDevice.setNameKey(deviceName);
        List<BaseDevice> deviceList = new ArrayList<>();
        deviceList.add(baseDevice);

        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        this.lanch.countdown();
        System.out.println("the device is :" + deviceName);
        return deviceList;
    }
}
