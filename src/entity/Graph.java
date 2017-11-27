/**
 * 
 */

package entity;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

  private int node = 0; // �ڵ����
  private int side = 0; // �߸���

  private HashMap<String, Integer> vexToInt; // ����ӳ�䵽��Ӧ�Ľڵ��±�

  private Vertex[] vertexs; // ��������

  private int[][] distance; // Floyd�㷨�������
  private String[][] path; // Floyd�㷨���·������

  // ����ṹ��
  class Vertex {
    String ver; // ����
    LinkedList<Edge> edge; // ������

    public Vertex() {
      ver = "";
      edge = new LinkedList<Edge>();
    }
  }
  // �߽ṹ��


  
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
   * @param ����ͼ�㷨
   */
  public void readGraph(String[] readin,Edge[] edges,int side) {
    vexToInt = new HashMap<String, Integer>();
    this.side = side;
   
    // ����ӳ�䶥���±�Hashmap��ʼ��
    for (int i = 0; i < readin.length; i++) {
      vexToInt.put(readin[i], -1);
    }
    int value = 0;
    // Ϊ����(��)��ӳ���±�ֵ(ֵ)
    for (String key : vexToInt.keySet()) {
      vexToInt.replace(key, value);
      value++;
    }
    node = value;
    vertexs = new Vertex[value];


    distance = new int[node][node];
    path = new String[node][node];


    // ************Ϊ�˷�ֹ�û�������Floyd�㷨��ʹ����̾����㷨��
    // ************��������ͼʱ���ø��������㷨������������
    // �������D��·������P��ʼ��
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

    // ���߼����Ӧ���������
    for (int i = 0; i < side; i++) {
      int j = vexToInt.get(edges[i].startEdge);
      vertexs[j].ver = edges[i].startEdge;
      vertexs[j].edge.add(edges[i]);


      distance[vexToInt.get(edges[i].startEdge)][vexToInt.get(edges[i].endEdge)] = edges[i].weight;
    }
    // Floyd�㷨
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
