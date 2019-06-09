package com.example.demo.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class MvpRoute {
    //能否到达
    private boolean reachable;
    //总距离
    private int minStep;
    //最短路径
    //      其中最短路径的那个List数组保存了从起点到终点最短路径所经历的节点。
    private List<Integer> step;
    //包含的路径
    private List<Edge> edge;
}
