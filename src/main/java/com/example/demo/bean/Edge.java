package com.example.demo.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
/**
 *  edge的实体bean
 */
public class Edge {
    private String wayId;
    private String wayName;
    //角度  默认为0，以12点为起点，逆时针转动
    private Integer angel;
    private Integer start;
    private Integer end;
    private Integer len;
    //道路类型   平路：Way  楼梯：stairs 坡路：hill
    private String type;

}
