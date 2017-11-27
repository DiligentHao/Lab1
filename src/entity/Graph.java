/**
 * 
 */

package entity;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

  private int node = 0; // 节点个数
  private int side = 0; // 边个数

  private HashMap<String, Integer> vexToInt; // 单词映射到对应的节点下标

  private Vertex[] vertexs; // 顶点数组

  private int[][] distance; // Floyd算法距离矩阵
  private String[][] path; // Floyd算法最短路径矩阵

  // 顶点结构体
  class Vertex {
    String ver; // 单词
    LinkedList<Edge> edge; // 边链表

    public Vertex() {
      ver = "";
      edge = new LinkedList<Edge>();
    }
  }
  // 边结构体


  
  public boolean isExist(String word) {
	  if(vexToInt.containsKey(word))
	  {
		  return true;
	  }
	  else
		  return false;
  }
  
  public int getSide() {
	  return this.side;
  }
  
  public int getNode() {
	  return this.node;
  }
  
 
  public int getItem(String word) {
	  return vexToInt.get(word);
  }
  
  public String getWord(int x) {
	  return vertexs[x].ver;
  }
  
  
  public LinkedList<Edge> getEdgeList(String word) {
	  return vertexs[vexToInt.get(word)].edge;
  }
  
  
  public String getPath(int i,int j) {
	  return path[i][j];
  }
  
  public int getDistance(int i,int j) {
	  return distance[i][j];
  }
  

  /**.
   * @param 生成图算法
   */
  public void readGraph(String[] readin,Edge[] edges,int side) {
    vexToInt = new HashMap<String, Integer>();
    this.side = side;
   
    // 单词映射顶点下标Hashmap初始化
    for (int i = 0; i < readin.length; i++) {
      vexToInt.put(readin[i], -1);
    }
    int value = 0;
    // 为单词(键)顶映射下标值(值)
    for (String key : vexToInt.keySet()) {
      vexToInt.replace(key, value);
      value++;
    }
    node = value;
    vertexs = new Vertex[value];


    distance = new int[node][node];
    path = new String[node][node];


    // ************为了防止用户不调用Floyd算法就使用最短距离算法，
    // ************故在生成图时调用弗洛伊德算法，将矩阵生成
    // 距离矩阵D和路径矩阵P初始化
    for (String key : vexToInt.keySet()) {
      int i = vexToInt.get(key);
      vertexs[i] = new Vertex();
      vertexs[i].ver = key;

      distance[i] = new int[node];
      path[i] = new String[node];
      for (int j = 0; j < node; j++) {
        distance[i][j] = Constant.INfINITE;
        path[i][j] = "";
      }


    }

    // 将边加入对应顶点边链表
    for (int i = 0; i < side; i++) {
      int j = vexToInt.get(edges[i].startEdge);
      vertexs[j].ver = edges[i].startEdge;
      vertexs[j].edge.add(edges[i]);


      distance[vexToInt.get(edges[i].startEdge)][vexToInt.get(edges[i].endEdge)] = edges[i].weight;
    }
    // Floyd算法
    for (String k : vexToInt.keySet()) {
      for (String i : vexToInt.keySet()) {
        for (String j : vexToInt.keySet()) {
          int ii = vexToInt.get(i);
          int jj = vexToInt.get(j);
          int kk = vexToInt.get(k);
          if (distance[ii][kk] + distance[kk][jj] < distance[ii][jj]) {
            distance[ii][jj] = distance[ii][kk] + distance[kk][jj];
            path[ii][jj] = k;
          }
        }
      }
    }
  }

}
