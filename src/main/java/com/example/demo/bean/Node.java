package com.example.demo.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Node {
    //怎么通过直接获得新插入的数据的主键
    private int nodeNum;
    //纬度
    private double latitudinal;
    //经度
    private double longitudinal;

    private String tags;
    private String name;
}
