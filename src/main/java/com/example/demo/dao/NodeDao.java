package com.example.demo.dao;

import com.example.demo.bean.Node;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@Mapper
public interface NodeDao {
    @Select("select * from node where node_num=#{nodeNum}")
    Node getNodeInfoToNum(int nodeNum);

    @Select("select * from node where tags=#{tags}")
    Set<Node> getNodeInfoToTags(String tags);

    @Select("select * from node where name=#{name}")
    Set<Node> getNodeInfoToName(String name);

    @Select("select @@IDENTITY as wayNum")
    int getNewWayNum();

    @Insert("insert into node set node_num=#{nodeNum},latitudinal=#{latitudinal},longitudinal=#{longitudinal},tags=#{tags},name=#{name}")
    boolean addNode(Node node);


}
