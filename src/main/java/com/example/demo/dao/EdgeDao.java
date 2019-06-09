package com.example.demo.dao;

import com.example.demo.bean.Edge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper
public interface EdgeDao {
    //添加新路线
    @Insert("insert into edge set way_name=#{wayName},start=#{start},end=#{end},len=#{len},angel=#{angel},type=#{type}")
    boolean addRoute(Edge edge);

    //根据start和end来查找路径
    @Select("select * from edge where start=#{start} and end=#{end}")
    Edge getEdge(@Param("start")int start,@Param("end")int end);

    //查找所有边
    @Select("select * from edge where start and end in(select node_num from node where #{latMin}<latitudinal<#{latMax} and #{lngMin}<longitudinal<#{lngMax})")
    Set<Edge> getEdgeInfo(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lngMin")double lngMin,@Param("lngMax")double lngMax);



    //查找没有楼梯stairs的边
    @Select("select * from edge where type='way' or type='hill' and start and end in(select node_num from node where #{latMin}<latitudinal<#{latMax} and #{lngMin}<longitudinal<#{lngMax})")
    Set<Edge> getEdgeNoStairs(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lngMin")double lngMin,@Param("lngMax")double lngMax);

    //查找没有坡的边
    @Select("select * from edge where type='way' or type='stairs' and start and end in(select node_num from node where #{latMin}<latitudinal<#{latMax} and #{lngMin}<longitudinal<#{lngMax})")
    Set<Edge> getEdgeNoHill(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lngMin")double lngMin,@Param("lngMax")double lngMax);

    //查找两者都没有的边
    @Select("select * from edge where type='way' and start and end in(select node_num from node where #{latMin}<latitudinal<#{latMax} and #{lngMin}<longitudinal<#{lngMax})")
    Set<Edge> getEdgeIsWay(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lngMin")double lngMin,@Param("lngMax")double lngMax);

    //删除路线

    /*//查找所有边
    @Select("select * from edge")
    Set<Edge> getEdgeInfo();*/

    /*//test
    @Select("select * from edge where start and end in(1,2,3,4,5,6,7,8,10,11,12,13)")
    Set<Edge> test1();

    //测试
    @Select("select node_num from node where #{latMin}<latitudinal<#{latMax} and #{lngMin}<longitudinal<#{lngMax}")
    int[] test(@Param("latMin")double latMin,@Param("latMax")double latMax,@Param("lngMin")double lngMin,@Param("lngMax")double lngMax);*/
}
