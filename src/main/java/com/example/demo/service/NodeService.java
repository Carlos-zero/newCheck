package com.example.demo.service;

import com.example.demo.bean.Node;

public interface NodeService {
    //查找最近的某个标签
    Node findNearTag(double lat, double lng, String tag);

    //节点间的距离是否小于10
    Node calDis(double lat, double lng);

    //插入新节点
    int addNode(Node node);

    //通过名字查找最近的节点
    Node findNearNodeToName(String name);
}
