package com.example.demo.service;

import com.example.demo.bean.Edge;
import com.example.demo.bean.MinStep;
import com.example.demo.bean.MvpRoute;
import com.example.demo.dao.EdgeDao;
import com.example.demo.utils.Distance;
import com.example.demo.utils.DistanceDijkstraImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EdgeServiceImpl implements EdgeService {
    @Autowired
    EdgeDao edgeDao;
    @Autowired
    Edge edge;
    @Autowired
    MvpRoute mvpRoute;

    private static Gson gson=new Gson();

    @Override
    public String findMvpRoute(int start, int end,boolean isHill,boolean isStairs,double latMin,double latMax,double lngMin,double lngMax) {
        Set<Edge> edges=null;
        if (isHill&&isStairs){
            //啥路都走
            System.out.println("啥路都走！");
            edges=edgeDao.getEdgeInfo(latMin,latMax,lngMin,lngMax);
        }else if ((!isStairs)&&isHill){
            System.out.println("不走楼梯！");
            edges=edgeDao.getEdgeNoStairs(latMin,latMax,lngMin,lngMax);
        }else if (isStairs&&(!isHill)){
            System.out.println("不走坡路");
            edges=edgeDao.getEdgeNoHill(latMin,latMax,lngMin,lngMax);
        }else if (!(isHill&&isStairs)){
            System.out.println("只走平路");
            edges=edgeDao.getEdgeIsWay(latMin,latMax,lngMin,lngMax);
        }

        HashMap<Integer, HashMap<Integer,Integer>> stepLength = new HashMap<Integer, HashMap<Integer,Integer>>();

        int i=1;
        for (Edge edge:edges){
            System.out.println(edge.toString());
            if (!stepLength.containsKey(edge.getStart())) {
                /**
                 *      这个地方很重要很重要很重要
                 */
                HashMap<Integer,Integer> step1= new HashMap<Integer, Integer>();
                stepLength.put(edge.getStart(), step1);
                step1.put(edge.getEnd(),edge.getLen());
            }else if (stepLength.containsKey(edge.getStart())){
                HashMap<Integer,Integer> step=stepLength.get(edge.getStart());
                step.put(edge.getEnd(),edge.getLen());
            }
            i++;
        }

        Distance distance=new DistanceDijkstraImpl();
        MinStep step = distance.getMinStep(start, end, stepLength);
        System.out.println(step.getStep());
        mvpRoute.setReachable(step.isReachable());
        mvpRoute.setMinStep(step.getMinStep());
        mvpRoute.setStep(step.getStep());
        int start1=0;
        int end1=0;
        List<Edge> edges1=new ArrayList<>();
        for (Integer node:step.getStep()){
            if (node!=start){
                //node是目标节点
                end1=node;
                Edge edge=edgeDao.getEdge(start1,end1);
                edges1.add(edge);
            }
            start1=node;
        }
        mvpRoute.setEdge(edges1);
        return gson.toJson(mvpRoute);
    }

    @Override
    public boolean addRoute(String wayName, int start, int end, int len,int angel,String type) {
        Edge edge1=edgeDao.getEdge(start,end);
            if (edge1!=null){
                //这里应该用一个bean来返回json
                //暂时不添加
                return false;
            }
        //此处可以尝试用事务，因为我用的是无向图  所以一次必须进入两个
        edge.setWayName(wayName);
        edge.setStart(start);
        edge.setEnd(end);
        edge.setLen(len);
        edge.setAngel(angel);
        edge.setType(type);
        System.out.println(angel);
        boolean addStart=edgeDao.addRoute(edge);
        edge.setStart(end);
        edge.setEnd(start);
        edge.setAngel(360-angel);
        boolean addEnd=edgeDao.addRoute(edge);
        if (addStart&&addEnd){
            return true;
        }
        return false;
    }

    /*while (iterator.hasNext()){
            Edge edge= (Edge) iterator.next();
            System.out.println(edge.toString());
            stepLength.put(edge.getStart(),step1);
            step1.put(edge.getEnd(),edge.getLen());
            while (iterator.hasNext()){
                Edge edge1= (Edge) iterator.next();
                System.out.println(edge1.toString()+"内层");
                if (edge1.getStart()==edge.getStart()){
                    step1.put(edge1.getEnd(),edge1.getLen());
                }
            }
        }*/
}
