package com.example.demo.bean;

import lombok.Data;

import java.util.List;

/**
 *  最短路径
 */
@Data
public class MinStep {
    //是否可达
    private boolean reachable;
    //最短步长
    private int minStep;
    //最短路径
    //      其中最短路径的那个List数组保存了从起点到终点最短路径所经历的节点。
    private List<Integer> step;

    //最短路径经过的地点
    private List<String> place;

    public MinStep() {
    }

    public MinStep(boolean reachable, int minStep) {
        this.reachable = reachable;
        this.minStep = minStep;
    }
}
