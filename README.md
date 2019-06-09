### **给自己定位的接口**：http://120.79.69.85:8080/redRockCheck_war/myNode

​				该接口单纯的传入（double latitudinal,double longitudinal）参数，确定自己的位置

#### 添加景点的接口：http://120.79.69.85:8080/redRockCheck_war/addNewNode

​				addNewNode(double latitudinal,double longitudinal,String tags,String name)

该接口传入参数经度，纬度，和景点标签，景点名字



#### **寻找离自己最近的某类景点：**http://120.79.69.85:8080/redRockCheck_war/findNearTag

​				findNearTag(double latitudinal,double longitudinal,String tag)

该接口传入自己的坐标，已经想要查找的景点类型，来搜索最近的景点



#### 添加路线：http://120.79.69.85:8080/redRockCheck_war/addRoute

​				addRoute(String wayName,String nodeName1,double lat1,double lng1,int angel,String nodeName2,double lat2,double lng2,String type)

该接口传入：道路名   节点名  节点坐标   道路类型  来生成一个新的路线



#### 寻找最近线路：http://120.79.69.85:8080/redRockCheck_war/findMvpRoute

​					findMvpRoute(double lat,double lng,String name,boolean isHill,boolean isStairs)

该接口传入自己坐标，目标名字，以及是否愿意爬坡，走楼梯



### **加分项：**

​      1.在添加路段的时候将路段分为  公路，坡路，楼梯     ，然后，在从数据库提取路径的时候，根据用户的需求，提取相应的类型的路段   以此做到过滤

​		2.时间优化方面，    在查询最短路径的时候       根据用户当前位置，寻找出以用户为圆心，半径X内的所有标签为way的节点，再通过mysql    签到  IN语句    从数据库中提取包含这些节点的路段，然后将这些路段通过算法找出最短路径

​		3.代码运行环境   mysql
