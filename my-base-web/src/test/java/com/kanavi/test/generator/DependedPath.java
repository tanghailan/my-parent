package com.kanavi.test.generator;

import java.util.ArrayList;
import java.util.List;

public class DependedPath {
    private List<String> usedPath;
    private List<String> usingPath;

    public DependedPath(List<String> usedPath, List<String> usingPath, List<String> noDependedPath,
                        List<String> paramDependList) {
        this.usedPath = usedPath;
        this.usingPath = usingPath;

    }

    public DependedPath() {
        usedPath = new ArrayList<>();
        usingPath = new ArrayList<>();

        usedPath.add("srcDevice");
        usedPath.add("dstDevice");
        usingPath.add("srcDevice1");
        usingPath.add("dstDevice1");
        usingPath.add("srcDevice2");
        usingPath.add("dstDevice2");

    }

    public List<String> getUsedPath() {
        return usedPath;
    }

    public void setUsedPath(List<String> usedPath) {
        this.usedPath = usedPath;
    }

    public List<String> getUsingPath() {
        return usingPath;
    }

    public void setUsingPath(List<String> usingPath) {
        this.usingPath = usingPath;
    }

}
