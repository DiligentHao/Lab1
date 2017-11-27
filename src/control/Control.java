package control;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import entity.Constant;
import entity.Edge;
import entity.Graph;
import entity.GraphViz;

public class Control {
  public static Edge[] edges;
  public static Graph myGraph;
  
  private static Stack<String> stackP = new Stack<String>();;
  
  public static void build(String[] readin) {
    edges = new Edge[readin.length];
    myGraph = new Graph();
    int side = 0;
	    // 边初始化
    for (int i = 0; i < readin.length; i++) {
      edges[i] = new Edge();
    }
    // 读取边
    for (int i = 0; i + 1 < readin.length; i++) {
      String title = readin[i];
      String last = readin[i + 1];

      int flag;
      flag = 0;
      // 已有边加权值
      for (int j = 0; j < side; j++) {
        if (edges[j].endEdge.equals(last) && edges[j].startEdge.equals(title)) {
          edges[j].weight++;
          flag = 1;
          break;
        }
      }
      // 没有边添加
      if (flag == 0) {
        edges[side].endEdge = last;
        edges[side].startEdge = title;
        edges[side].weight = 1;
        side++;
      }
    }
    myGraph.readGraph(readin, edges,side);
  }
  
  public static void showDirectedGraph() {
	    GraphViz graphViz = new GraphViz();
	    String adj;

	    graphViz.addln(graphViz.start_graph());

	    for (int j = 0; j < myGraph.getSide(); j++) {
	      adj = edges[j].startEdge + "->" + edges[j].endEdge + "[label=\"" + edges[j].weight + "\"]";
	      graphViz.addln(adj);
	    }
	    graphViz.addln(graphViz.end_graph());


	    File file = new File("GRAPH1.jpg"); // 生成.jpg
	    graphViz.writeGraphToFile(graphViz.getGraph(graphViz.getDotSource(), "jpg"), file);


  }
  
  
  /**
   * .
   * 
   * @param 第一个词
   * @param 第二个词
   * @return 查询桥接词
   */
  public static String queryBridgeWords(String word1, String word2) {
    String outputString = "";
    // word1word2都存在
    if (myGraph.isExist(word1) && myGraph.isExist(word2)) {
      String bridgeWord = bridgeWords(word1, word2); // 调用生成桥接词算法
      if (bridgeWord.equals("")) {
        outputString = "No bridge words from " + word1 + " to " + word2 + " !";
      } else {
        outputString = "The bridge words from " + word1 + " to " + word2 + " are: " + bridgeWord;
      }
      // word1或word2不存在
    } else {
      outputString = "No word1 or word2 in the graph!";
    }
    return outputString;
  }
  
  /**
   * .
   * 
   * @param 文本
   * @return 生成新文本
   */
  public static String generateNewText(String inputText) {
    String outputText = "";
    String[] splitText = inputText.toLowerCase().split("[^a-zA-Z]+");// 分割新文本

    int i = 0;
    // 遍历新文本
    for (; i + 1 < splitText.length; i++) {
      outputText = outputText + splitText[i] + " ";
      // 相邻词之间调用桥接词算法
      if (myGraph.isExist(splitText[i]) && myGraph.isExist(splitText[i + 1])) {
        String bridgeWords = bridgeWords(splitText[i], splitText[i + 1]);
        if (!bridgeWords.equals("")) {
          String[] temp = bridgeWords.split("[^a-zA-Z]+");

          Random forInsert = new Random();
          int insertIndex = forInsert.nextInt(temp.length);


          outputText = outputText + temp[insertIndex] + " ";
        }
      }
    }
    outputText = outputText + splitText[i];

    return outputText;
  }

  /**
   * .
   * 
   * @param 随机游走
   * @return null
   */
  public static String randomWalk() {
    String outt = "";

    HashMap<Edge, Boolean> visit = new HashMap<Edge, Boolean>();// 边是否遍历过，初始化false
    for (int j = 0; j < myGraph.getSide(); j++) {
      visit.put(edges[j], false);
    }


    Random r1 = new Random();
    int x = r1.nextInt(myGraph.getNode());
    String start = myGraph.getWord(x);
    stackP.push(start); // 随机一个点起手

    outt = start;
    LinkedList<Edge> p;
    while (!stackP.empty()) {
      p = myGraph.getEdgeList(stackP.pop());
      // 无路可走
      if (!p.isEmpty()) {
        int j = r1.nextInt(p.size());
        Edge edge = p.get(j);
        outt = outt + " " + edge.endEdge;
        // 重复的边
        if (!visit.get(edge)) {
          visit.replace(edge, true);
          stackP.push(edge.endEdge);
        }


      }
    }
    return outt;
  }

  
  /**********************************
   * 最短路径算法 shortestPath用于生成一条最短路径(递归算法) . 
   * allToOne是单源最短路径 calcshortestPath是查询两个点之间的最短路径
   * 由于最短路径算法对于单源和指定的点对是重复部分，故写shortestPath函数来简化代码
   */

  // 生成一条最短路径
  private static String shortestPath(String start, String end) {
    String outt = "";
    int i = myGraph.getItem(start);
    int j = myGraph.getItem(end);

    if (myGraph.getPath(i, j).equals("")) {
      return "";
    } else {
      stackP.push(myGraph.getPath(i, j));
      outt = outt + shortestPath(start, myGraph.getPath(i, j)); // 递归
      if (!stackP.empty()) {
        outt = outt + stackP.pop() + "->";
      }
      outt = outt + shortestPath(myGraph.getPath(i, j), end);
      if (!stackP.empty()) {
        outt = outt + stackP.pop() + "->";
      }
      return outt;
    }
  }
  
  
  /**
   * .
   * 
   * @param 第一个词
   * @param 第二个词
   * @return 点对之间的最短路径
   */
  public static String calcshortestPath(String word1, String word2) {
    String outt = "";
    // word1word2存在
    if (myGraph.isExist(word1) && myGraph.isExist(word2) ){
      if (word1.equals(word2)
          || myGraph.getDistance(myGraph.getItem(word1), myGraph.getItem(word2))  == Constant.INfINITE) {
        outt = "No way!";
        // 有路径
      } else {
        String temp = shortestPath(word1, word2);
        outt = word1 + "->" + temp + word2 + "\nThe length of path is "
            + myGraph.getDistance(myGraph.getItem(word1), myGraph.getItem(word2)) + "\n";
      }
    } else {
      outt = word1 + " or " + word2 + " is not in the graph!";
    }
    return outt;
  }

  /**
   * .
   * 
   * @param 起始点
   * @return 单源最短路径
   */
  public static String allToOne(String start) {
    String outt = "";
    // 遍历其他点
    if(myGraph.isExist(start))
    {
    	for (int i = 0;i<myGraph.getNode();i++) {
    	      if (start.equals(myGraph.getWord(i))) {
    	        continue;
    	      } else {
    	        outt = outt + calcshortestPath(start, myGraph.getWord(i));
    	      }
    	    }
    }
    else
    {
    	outt = "No way!";
    }
    return outt;
  }
  
  
  
  /**********************************************************
   * 由于课程要求的queryBridgeWords和generateNewText都需要桥接词 . 
   * * 所以将重复代码整合为bridgeWords旨在生成桥接词，便于桥接词的查询，生成新文本 *
   * ********************************************************
   */
  // 生成桥接词算法
  private static String bridgeWords(String word1, String word2) {
    String brigewords = "";
    //int wordIndex = myGraph.getItem(word1);
    LinkedList<Edge> startLink = myGraph.getEdgeList(word1);
    Stack<String> maybridge = new Stack<String>();
    Stack<String> wholebridge = new Stack<String>();
    for (Edge edge : startLink) {
      maybridge.push(edge.endEdge);
    }
    // 可能的桥接词栈不为空，则说明还有可能的桥接词
    while (!maybridge.empty()) {
      String temp = maybridge.pop();
      int maybrigeIndex = myGraph.getItem(temp);// 将他出栈
      LinkedList<Edge> bridgeLink = myGraph.getEdgeList(temp);
      // 遍历可能桥接词的边，如果有endEdge为word2的，说明可能桥接词为真的桥接词，将其如wholebridge栈
      for (Edge end : bridgeLink) {
        if (end.endEdge.equals(word2)) {
          wholebridge.push(myGraph.getWord(maybrigeIndex));
        }
      }
    }
    // 全部桥接词wholebridge不为空，出栈
    while (!wholebridge.empty()) {
      String temp = wholebridge.pop();
      if (!wholebridge.empty()) {
        brigewords = brigewords + temp + ",";
      } else {
        brigewords = brigewords + temp + ".";
      }
    }
    return brigewords; // 返回桥接词
	  }
	  

}
  

