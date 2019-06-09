package com.example.demo.service;

public interface EdgeService {
    //找到最佳路线
    String findMvpRoute(int start,int end,boolean isHill,boolean isStairs,double latMin,double latMax,double lngMin,double lngMax);
    //添加路线
    boolean addRoute(String wayName,int start,int end,int len,int angel,String type);

}
