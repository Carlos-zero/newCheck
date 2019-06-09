package com.example.demo.utils;

import com.example.demo.dao.NodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoUtil {
    private static NodeDao nodeDao;
    @Autowired
    public DaoUtil(NodeDao nodeDao) {
        DaoUtil.nodeDao=nodeDao;
    }

    public static String getPlaceName(int nodeNum){
        return nodeDao.getNodeInfoToNum(nodeNum).getName();
    }
}
