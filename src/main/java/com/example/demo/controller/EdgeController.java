package com.example.demo.controller;

import com.example.demo.bean.Node;
import com.example.demo.service.EdgeService;
import com.example.demo.service.NodeService;
import com.example.demo.utils.LatLonUtil;
import com.example.demo.utils.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdgeController {
    @Autowired
    EdgeService edgeService;
    @Autowired
    NodeService nodeService;

    //添加路线
    @PostMapping(value = "/addRoute")
    public String addRoute(String wayName,String nodeName1,double lat1,double lng1,int angel,String nodeName2,double lat2,double lng2,String type){
        int start=0;
        int end=0;
        //如果两个节点距离小于10就合成一个节点
        if (nodeService.calDis(lat1,lng1)!=null){
            Node node=nodeService.calDis(lat1,lng1);
            start=node.getNodeNum();
        }else {
            Node node=new Node();
            node.setName(nodeName1);
            node.setLatitudinal(lat1);
            node.setLongitudinal(lng1);
            node.setTags("way");
            start=nodeService.addNode(node);
        }
        if (nodeService.calDis(lat2,lng2)!=null){
            Node node=nodeService.calDis(lat2,lng2);
            end=node.getNodeNum();
        }else {
            Node node=new Node();
            node.setName(nodeName2);
            node.setLatitudinal(lat2);
            node.setLongitudinal(lng2);
            node.setTags("way");
            end=nodeService.addNode(node);
        }
        int distance= (int) LocationUtils.getDistance(lat1,lng1,lat2,lng2);
        if (edgeService.addRoute(wayName,start,end,distance,angel,type)){
            return "成功！";
        }
        return null;
    }

    @PostMapping(value = "/findMvpRoute")
    public String findMvpRoute(double lat,double lng,String name,boolean isHill,boolean isStairs){
        Node nodeStart=nodeService.findNearTag(lat,lng,"way");
        Node nodeEnd=nodeService.findNearNodeToName(name);
        double[] ranges=LatLonUtil.getAround(lat,lng,1000);
        return edgeService.findMvpRoute(nodeStart.getNodeNum(),nodeEnd.getNodeNum(),isHill,isStairs,ranges[0],ranges[2],ranges[3],ranges[1]);
    }
}
