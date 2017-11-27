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
	    // �߳�ʼ��
    for (int i = 0; i < readin.length; i++) {
      edges[i] = new Edge();
    }
    // ��ȡ��
    for (int i = 0; i + 1 < readin.length; i++) {
      String title = readin[i];
      String last = readin[i + 1];

      int flag;
      flag = 0;
      // ���б߼�Ȩֵ
      for (int j = 0; j < side; j++) {
        if (edges[j].endEdge.equals(last) && edges[j].startEdge.equals(title)) {
          edges[j].weight++;
          flag = 1;
          break;
        }
      }
      // û�б����
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


	    File file = new File("GRAPH1.jpg"); // ����.jpg
	    graphViz.writeGraphToFile(graphViz.getGraph(graphViz.getDotSource(), "jpg"), file);


  }
  
  
  /**
   * .
   * 
   * @param ��һ����
   * @param �ڶ�����
   * @return ��ѯ�ŽӴ�
   */
  public static String queryBridgeWords(String word1, String word2) {
    String outputString = "";
    // word1word2������
    if (myGraph.isExist(word1) && myGraph.isExist(word2)) {
      String bridgeWord = bridgeWords(word1, word2); // ���������ŽӴ��㷨
      if (bridgeWord.equals("")) {
        outputString = "No bridge words from " + word1 + " to " + word2 + " !";
      } else {
        outputString = "The bridge words from " + word1 + " to " + word2 + " are: " + bridgeWord;
      }
      // word1��word2������
    } else {
      outputString = "No word1 or word2 in the graph!";
    }
    return outputString;
  }
  
  /**
   * .
   * 
   * @param �ı�
   * @return �������ı�
   */
  public static String generateNewText(String inputText) {
    String outputText = "";
    String[] splitText = inputText.toLowerCase().split("[^a-zA-Z]+");// �ָ����ı�

    int i = 0;
    // �������ı�
    for (; i + 1 < splitText.length; i++) {
      outputText = outputText + splitText[i] + " ";
      // ���ڴ�֮������ŽӴ��㷨
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
   * @param �������
   * @return null
   */
  public static String randomWalk() {
    String outt = "";

    HashMap<Edge, Boolean> visit = new HashMap<Edge, Boolean>();// ���Ƿ����������ʼ��false
    for (int j = 0; j < myGraph.getSide(); j++) {
      visit.put(edges[j], false);
    }


    Random r1 = new Random();
    int x = r1.nextInt(myGraph.getNode());
    String start = myGraph.getWord(x);
    stackP.push(start); // ���һ��������

    outt = start;
    LinkedList<Edge> p;
    while (!stackP.empty()) {
      p = myGraph.getEdgeList(stackP.pop());
      // ��·����
      if (!p.isEmpty()) {
        int j = r1.nextInt(p.size());
        Edge edge = p.get(j);
        outt = outt + " " + edge.endEdge;
        // �ظ��ı�
        if (!visit.get(edge)) {
          visit.replace(edge, true);
          stackP.push(edge.endEdge);
        }


      }
    }
    return outt;
  }

  
  /**********************************
   * ���·���㷨 shortestPath��������һ�����·��(�ݹ��㷨) . 
   * allToOne�ǵ�Դ���·�� calcshortestPath�ǲ�ѯ������֮������·��
   * �������·���㷨���ڵ�Դ��ָ���ĵ�����ظ����֣���дshortestPath�������򻯴���
   */

  // ����һ�����·��
  private static String shortestPath(String start, String end) {
    String outt = "";
    int i = myGraph.getItem(start);
    int j = myGraph.getItem(end);

    if (myGraph.getPath(i, j).equals("")) {
      return "";
    } else {
      stackP.push(myGraph.getPath(i, j));
      outt = outt + shortestPath(start, myGraph.getPath(i, j)); // �ݹ�
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
   * @param ��һ����
   * @param �ڶ�����
   * @return ���֮������·��
   */
  public static String calcshortestPath(String word1, String word2) {
    String outt = "";
    // word1word2����
    if (myGraph.isExist(word1) && myGraph.isExist(word2) ){
      if (word1.equals(word2)
          || myGraph.getDistance(myGraph.getItem(word1), myGraph.getItem(word2))  == Constant.INfINITE) {
        outt = "No way!";
        // ��·��
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
   * @param ��ʼ��
   * @return ��Դ���·��
   */
  public static String allToOne(String start) {
    String outt = "";
    // ����������
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
   * ���ڿγ�Ҫ���queryBridgeWords��generateNewText����Ҫ�ŽӴ� . 
   * * ���Խ��ظ���������ΪbridgeWordsּ�������ŽӴʣ������ŽӴʵĲ�ѯ���������ı� *
   * ********************************************************
   */
  // �����ŽӴ��㷨
  private static String bridgeWords(String word1, String word2) {
    String brigewords = "";
    //int wordIndex = myGraph.getItem(word1);
    LinkedList<Edge> startLink = myGraph.getEdgeList(word1);
    Stack<String> maybridge = new Stack<String>();
    Stack<String> wholebridge = new Stack<String>();
    for (Edge edge : startLink) {
      maybridge.push(edge.endEdge);
    }
    // ���ܵ��ŽӴ�ջ��Ϊ�գ���˵�����п��ܵ��ŽӴ�
    while (!maybridge.empty()) {
      String temp = maybridge.pop();
      int maybrigeIndex = myGraph.getItem(temp);// ������ջ
      LinkedList<Edge> bridgeLink = myGraph.getEdgeList(temp);
      // ���������ŽӴʵıߣ������endEdgeΪword2�ģ�˵�������ŽӴ�Ϊ����ŽӴʣ�������wholebridgeջ
      for (Edge end : bridgeLink) {
        if (end.endEdge.equals(word2)) {
          wholebridge.push(myGraph.getWord(maybrigeIndex));
        }
      }
    }
    // ȫ���ŽӴ�wholebridge��Ϊ�գ���ջ
    while (!wholebridge.empty()) {
      String temp = wholebridge.pop();
      if (!wholebridge.empty()) {
        brigewords = brigewords + temp + ",";
      } else {
        brigewords = brigewords + temp + ".";
      }
    }
    return brigewords; // �����ŽӴ�
	  }
	  

}
  

