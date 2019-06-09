package com.example.demo.service;

import com.example.demo.bean.Node;
import com.example.demo.dao.NodeDao;
import com.example.demo.utils.LocationUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NodeServiceImpl implements NodeService{
    private static Gson gson=new Gson();

    @Autowired
    NodeDao nodeDao;
    @Override
    public Node findNearTag(double lat,double lng,String tag) {
        Set<Node> nodes=nodeDao.getNodeInfoToTags(tag);
        Node mostNear=new Node();
        double distance=999999999;
        for (Node node:nodes){
            double temp=LocationUtils.getDistance(lat,lng,node.getLatitudinal(),node.getLongitudinal());
            if (temp<distance){
                mostNear=node;
                distance=temp;
            }
        }
        return mostNear;
    }

    @Override
    public Node calDis(double lat, double lng) {
        //这些地方好像用haspMap可以快速度。。。
        Set<Node> nodes=nodeDao.getNodeInfoToTags("way");
        Node mostNear=new Node();
        double distance=999999999;
        for (Node node:nodes){
            double temp=LocationUtils.getDistance(lat,lng,node.getLatitudinal(),node.getLongitudinal());
            if (temp<distance){
                mostNear=node;
                distance=temp;
            }
        }
        if (distance<10){
            return mostNear;
        }else {
            return null;
        }
    }

    @Override
    public int addNode(Node node) {
        if (nodeDao.addNode(node)){
            int id=nodeDao.getNewWayNum();
            return id;
        }
        return 0;
    }

    @Override
    public Node findNearNodeToName(String name) {
        Set<Node> nodes=nodeDao.getNodeInfoToName(name);
        Node mostNear=new Node();
        double distance=999999999;
        for (Node node:nodes){
            double temp=LocationUtils.getDistance(node.getLatitudinal(),node.getLongitudinal(),node.getLatitudinal(),node.getLongitudinal());
            if (temp<distance){
                mostNear=node;
                distance=temp;
            }
        }
        return mostNear;
    }
}
