package com.example.demo.controller;

import com.example.demo.bean.Node;
import com.example.demo.service.NodeService;
import com.example.demo.utils.LocationUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {

    //学校中心的经纬度
    final double LATITUDINAL=106.60775;
    final double LONGITUDINAL=29.531765;

    @Autowired
    Node node;
    @Autowired
    NodeService nodeService;

    private static Gson gson=new Gson();

    //给自己定个位吧！
    @PostMapping(value = "/myNode")
    public String myNode(double latitudinal,double longitudinal){
        node.setLatitudinal(latitudinal);
        node.setLongitudinal(longitudinal);
        return gson.toJson(node);
    }

    @PostMapping(value = "/addNewNode")
    public String addNewNode(double latitudinal,double longitudinal,String tags,String name){
        int distance= (int) LocationUtils.getDistance(latitudinal,longitudinal,LATITUDINAL,LONGITUDINAL);
        if (distance>=1000){
            return "该点不在地图服务范围内！";
        }
        node.setLatitudinal(latitudinal);
        node.setLongitudinal(longitudinal);
        node.setName(name);
        node.setTags(tags);
        int nodeNum=nodeService.addNode(node);
        node.setNodeNum(nodeNum);
        return gson.toJson(node);
    }

    @PostMapping(value = "/findNearTag")
    public String findNearTag(double latitudinal,double longitudinal,String tag){
        return gson.toJson(nodeService.findNearTag(latitudinal,longitudinal,tag));
    }
}
