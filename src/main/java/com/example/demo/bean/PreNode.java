package com.example.demo.bean;

import lombok.Data;

/**
 *  每一个节点的最优前一节点
 */
@Data
public class PreNode {
    //最优   前一节点
    private int preNodeNum;
    //最小步长
    private int nodeStep;

    public PreNode(int preNodeNum, int nodeStep) {
        this.preNodeNum = preNodeNum;
        this.nodeStep = nodeStep;
    }
}
