package com.example.demo;

import com.example.demo.controller.EdgeController;
import com.example.demo.controller.NodeController;
import com.example.demo.dao.EdgeDao;
import com.example.demo.service.EdgeService;
import com.example.demo.service.NodeService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    EdgeController edgeController;
    @Autowired
    NodeController nodeController;
    @Autowired
    NodeService nodeService;
    @Autowired
    EdgeService edgeService;
    @Autowired
    EdgeDao edgeDao;

    private static Gson gson=new Gson();
    @Test
    public void contextLoads() {
//        System.out.println(nodeController.myNode(999,999));
//        System.out.println(nodeController.addNewNode(106.6126,29.554735,"test999","test999"));
//        System.out.println(edgeService.findMvpRoute(1,5,true,false));
//        edgeService.addRoute("test1",1,4,1,90);
//        System.out.println(nodeController.findNearTag(29.539642,106.610875,"tea"));
        System.out.println(edgeController.addRoute("测试路径","外语学院",106.616678,29.541849,90,"计算机学院",106.612986,29.537945,"hill"));
        System.out.println(edgeService.addRoute("测试路径",12,13,222,45,"way"));
        //工具准确度测算
//        System.out.println(LocationUtils.getDistance(106.602646,29.566167,106.594517,29.566654));
//        double[] ranges=LatLonUtil.getAround(106.614396,29.537611,1000);
        /*for (double range:ranges){
            System.out.println(range);
        }
        int [] test=edgeDao.test(ranges[0],ranges[2],ranges[3],ranges[1]);
        for (int a:test){
            System.out.println(a);
        }

        for (Edge edge:edgeDao.test1()){
            System.out.println(edge.toString());
        }*/

        /*for (Edge edge:edgeDao.getEdgeNoStairs(ranges[0],ranges[2],ranges[3],ranges[1])){
            System.out.println(edge.toString());
        }*/
    }
}
