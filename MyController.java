package application;
//C4 change change change
import java.awt.Desktop;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class MyController implements Initializable {
	
	//private Desktop desktop = Desktop.getDesktop(); 
	@FXML
	public static Graph myGraph;

	@FXML
    private Stage stageFile = new Stage();
	@FXML
    private Stage  stageSearchBridge = new Stage();
	@FXML
	private Stage  newTEXT = new Stage();
	@FXML
	private Stage  ShortestPath = new Stage();
	
	@FXML
	private Stage  randStage = new Stage();

	@FXML
	private TextField bridgeWord1,bridgeWord2,bridgeOut;
	@FXML
	private TextArea inText,outText;
	@FXML
	private TextField shortWord1,shortWord2;
	@FXML
	private TextArea shortOut;
	@FXML
	private TextField walkText;
	

   @FXML
   private Button showGraph,searchBridge,newText,shorestPath,randWalk;

   


   @Override
   public void initialize(URL location, ResourceBundle resources) {

       // TODO (don't really need to do anything here).

   }

   // When user click on myButton
   // this method will be called.

   
   @FXML
   public void openFiles(ActionEvent event) throws IOException {  // 打开文件
	   FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
       fileChooser.getExtensionFilters().add(extFilter);
       File file = fileChooser.showOpenDialog(stageFile);
       
       if (file != null) {
    	   showGraph.setVisible(true);
    	   searchBridge.setVisible(true);
    	   newText.setVisible(true);
    	   shorestPath.setVisible(true);
    	   randWalk.setVisible(true);
    	   
   		Reader in = null;
   		
   		in = new FileReader(file);
   		
   		char[] temp = new char[2000];
   		int i  = in.read(temp);
   		
   		
   		in.close();
   		
   		String temp_string = new String(temp,0,i);
   		temp_string = temp_string.toLowerCase();
   		
   		String[] a = temp_string.split("[^a-zA-Z]+");
   		myGraph = new Graph();
   		
   		myGraph.readGraph(a);
    	   

       }
       else
       {
    	   //*******************文件打开失败的处理
    	   showGraph.setVisible(false);
    	   searchBridge.setVisible(false);
    	   newText.setVisible(false);
    	   shorestPath.setVisible(false);
    	   randWalk.setVisible(false);
    	   
       }

	   
   }
   @FXML
   public void exit(ActionEvent event) {   // 退出按钮事件
	   
	   System.exit(0);
   }
   
   @FXML
   public void showGraph(ActionEvent event)// 展示按钮事件
   {
	  myGraph.showDirectedGraph();
	   
	   try {
		    Desktop.getDesktop().open(new File("GRAPH1.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   }
   
   @FXML
   public void searchBridge(ActionEvent event) // 打开查询桥接词界面
   {
	   stageSearchBridge.setTitle("查询桥接词");
		Pane myPane = null;
		try {
			myPane = (Pane) FXMLLoader.load(getClass().getResource("SearchBridge.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene myScene = new Scene(myPane);
		stageSearchBridge.setScene(myScene);
		stageSearchBridge.show();
   
   }
   
   @FXML
   public void Search(ActionEvent event) { // 查询按钮
	   
	   String xString  = myGraph.queryBridgeWords(bridgeWord1.getText(), bridgeWord2.getText());
	   
	   bridgeOut.setText(xString);
   }
   
   
   @FXML
   public void newText(ActionEvent event) // 生成新文本窗口
   {
	    newTEXT.setTitle("更改文本");
		Pane myPane = null;
		try {
			myPane = (Pane) FXMLLoader.load(getClass().getResource("NewText.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene myScene = new Scene(myPane);
		newTEXT.setScene(myScene);
		newTEXT.show();

   }
   @FXML
   public void Change(ActionEvent event) // 生成窗口
   {
	   String xString  =  myGraph.generateNewText(inText.getText());
	   outText.setText(xString);
   }
   
   
   @FXML
   public void shortestPath(ActionEvent event) // 生成对短路径窗口
   {
	    ShortestPath.setTitle("最短路径");
		Pane myPane = null;
		try {
			myPane = (Pane) FXMLLoader.load(getClass().getResource("ShortestPath.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene myScene = new Scene(myPane);
		ShortestPath.setScene(myScene);
		ShortestPath.show();
   }
   @FXML
   public void findPath(ActionEvent event)  // 打印最短路径按钮事件
   {
	   String word1 = shortWord1.getText();
	   String word2 = shortWord2.getText();
	   String xString;
	   if(word1.equals("") && word2.equals(""))
	   {
		   shortOut.setText("No words!");
	   }
	   else if(!word1.equals("") && word2.equals(""))
	   {
		   xString = myGraph.f_to_one(word1);
		   shortOut.setText(xString);
	   }
	   else if(!word1.equals("") && !word2.equals(""))
	   {
		   xString = myGraph.calcShortestPath(word1, word2);
		   shortOut.setText(xString);
	   }
	   else
	   {
		   shortOut.setText("We dont support from all to word2 path!");
	   }  
   }

   
   @FXML
   public void randWalk(ActionEvent event)  // 随机游走窗口
   {
        randStage.setTitle("随机游走");
 		Pane myPane = null;
 		try {
 			myPane = (Pane) FXMLLoader.load(getClass().getResource("RandWalk.fxml"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		
 		Scene myScene = new Scene(myPane);
 		randStage.setScene(myScene);
 		randStage.show();
 		
 		 String xString = myGraph.randomWalk();
 		 
 		 wwalk  = xString.split(" ");
 		 ww = 0;
   }
   
   @FXML
   public static String wwalk[];
   @FXML
   public static int ww = 0;
   
   @FXML
   public void Walk(ActionEvent event)  // 游走按钮事件
   {
	  
	   if(ww < wwalk.length)
	   {
		   
		   String xString = "";
		   for(int i = 0;i<=ww;i++)
		   {
			   if(i == 0)
			   {
				   xString = wwalk[0];
			   }
			   else
				   xString = xString +" "  + wwalk[i];
		   }
		   ww++;
		   
		   walkText.setText(xString);
	   }
	   else
	   {
		   walkText.setText("遍历完了");
	   }
   }
   @FXML
   public void saveWalk(ActionEvent event) { //保存按钮事件
	   String xString = "";
	   for(int i = 0;i<ww;i++)
	   {
		   if(i == 0)
		   {
			   xString = wwalk[0];
		   }
		   else
			   xString = xString +" "  + wwalk[i];
	   }
	   xString += "\r\n";
	   
	   try {
		   File file = new File("randWalk.txt");
		   if(!file.exists())
		   {
			   file.createNewFile();
		   }
		   Writer fileWritter = new FileWriter(file,true);
		   fileWritter.write(xString);
		   fileWritter.close();
		
	   } catch (IOException e) {
		e.printStackTrace();
		// TODO: handle exception
	   }

   }

}




class Constant
{
	public static final int INfINITE = 100000; 
	
}

class Graph
{

    private int V = 0;  // 节点个数 
	private int E = 0;  // 边个数
	
	private HashMap<String, Integer> vexToInt;  // 单词映射到对应的节点下标
	
	private Vertex[] vertexs;                   // 顶点数组
	private Edge[] edges;                       // 边数组
	
	private int[][] D;                          // Floyd算法距离矩阵
	private String [][] P;						// Floyd算法最短路径矩阵
	
	private Stack<String> stack_p = new Stack<String>();  // 栈（字符串类型）
	
	
	class Vertex								// 顶点结构体
	{
		String ver;                             // 单词
		LinkedList<Edge> edge;                  // 边链表
		
		public Vertex()
		{
			ver = "";
			edge = new LinkedList<Edge>();
		}
	}
	
	class Edge                                  // 边结构体
	{
		String start_edge = "";
		String end_edge = "";
		int weight = 0;		                    // 权值
	}

	public void readGraph(String[] readin)      //生成图算法
	{	
		vexToInt = new HashMap<String, Integer>();
		edges = new Edge[readin.length];
		
		for(int i = 0;i<readin.length;i++)       // 边初始化
		{
			edges[i] = new Edge();
		}
		
		for(int i = 0;i+1<readin.length;i++)     // 读取边
		{
			String title = readin[i];
			String last = readin[i+1];
		
			int flag;
			flag = 0;
			for(int j = 0;j<E;j++)                // 已有边加权值
			{
				if(edges[j].end_edge.equals(last)&&edges[j].start_edge.equals(title) )
				{
					edges[j].weight++;
					flag = 1;
					break;
				}
			}
			if(flag == 0)                          // 没有边添加
			{
				edges[E].end_edge = last;
				edges[E].start_edge = title;
				edges[E].weight = 1;
				E++;
			}
		
		}
		
		for(int i = 0;i<readin.length;i++)          // 单词映射顶点下标Hashmap初始化
		{
			vexToInt.put(readin[i],-1);
		}
		int value = 0;
		for(String key:vexToInt.keySet())           // 为单词(键)顶映射下标值(值)
		{
			vexToInt.replace(key,value);
			value++;
		}
		V = value;
		vertexs = new Vertex[value];
		

		
		D = new int[V][V];
		P = new String[V][V];
		

		//************为了防止用户不调用Floyd算法就使用最短距离算法，
		//************故在生成图时调用弗洛伊德算法，将矩阵生成
		
		for(String key:vexToInt.keySet())          // 距离矩阵D和路径矩阵P初始化
		{
			int i = vexToInt.get(key);
			vertexs[i] = new Vertex();
			vertexs[i].ver = key;
			
			D[i] = new int[V];
			P[i] = new String[V];
			for(int j = 0;j<V;j++)
			{
				D[i][j] = Constant.INfINITE;
				P[i][j] = "";
			}
			
			
		}
		
		
		for(int i = 0;i<E;i++)                      // 将边加入对应顶点边链表
		{
			int j = vexToInt.get(edges[i].start_edge);
			vertexs[j].ver = edges[i].start_edge;
			vertexs[j].edge.add(edges[i]);
	
			
			D[vexToInt.get(edges[i].start_edge)][vexToInt.get(edges[i].end_edge)] = edges[i].weight;
		}
		
		for(String k:vexToInt.keySet())            // Floyd算法
		{
			for(String i:vexToInt.keySet())
			{
				for(String j:vexToInt.keySet())
				{
					int ii,jj,kk;
					ii = vexToInt.get(i);
					jj = vexToInt.get(j);
					kk = vexToInt.get(k);
					if(D[ii][kk] + D[kk][jj] < D[ii][jj])
					{
						D[ii][jj] = D[ii][kk] + D[kk][jj];
						P[ii][jj] = k;
					}
				}
			}
		}	
	}
	
	public void showDirectedGraph() {         // 展示图算法
		GraphViz gV = new GraphViz();
		String adj;
		
		gV.addln(gV.start_graph());
		
		for(int j = 0;j<E;j++)
		{
			adj = edges[j].start_edge + "->" + edges[j].end_edge +"[label=\"" +  edges[j].weight +"\"]";
			gV.addln(adj);
		}
		gV.addln(gV.end_graph());
		
		
		File file = new File("GRAPH1.jpg");  // 生成.jpg
		gV.writeGraphToFile(gV.getGraph(gV.getDotSource(), "jpg"), file);
		
		
	}
	
	/**********************************************************
	 * 由于课程要求的queryBridgeWords和generateNewText都需要桥接词             *
	 * 所以将重复代码整合为bridgeWords旨在生成桥接词，便于桥接词的查询，生成新文本 *
	 * ********************************************************
	*/
	private String bridgeWords(String word1,String word2) // 生成桥接词算法
	{
		String brigewords = "";
		int word_index = vexToInt.get(word1);
		LinkedList<Edge> startLink = vertexs[word_index].edge;
		Stack<String> maybridge = new Stack<String>();
		Stack<String> wholebridge = new Stack<String>();
		
		for(Edge edge:startLink)
		{
			maybridge.push(edge.end_edge);
		}
		while(!maybridge.empty())                      // 可能的桥接词栈不为空，则说明还有可能的桥接词
		{
			int maybrige_index = vexToInt.get(maybridge.pop()); // 将他出栈
			LinkedList<Edge> bridgeLink = vertexs[maybrige_index].edge;
			
			for(Edge end:bridgeLink)                // 遍历可能桥接词的边，如果有end_edge为word2的，说明可能桥接词为真的桥接词，将其如wholebridge栈
			{
				if(end.end_edge.equals(word2))
				{
					wholebridge.push(vertexs[maybrige_index].ver);
				}
			}
		}
		
		while(!wholebridge.empty())              // 全部桥接词wholebridge不为空，出栈
		{
			String temp = wholebridge.pop();
			if(!wholebridge.empty())
			{
				brigewords = brigewords + temp +",";
			}
			else
			{
				brigewords = brigewords + temp +".";
			}	
		}
		return brigewords;                       // 返回桥接词
	}
	
	public String queryBridgeWords(String word1,String word2) // 查询桥接词
	{
		String outputString = "";
		if(vexToInt.containsKey(word1) && vexToInt.containsKey(word2))// word1word2都存在
		{
			String bridgeWord = bridgeWords(word1, word2); // 调用生成桥接词算法
			if(bridgeWord.equals(""))
			{
				outputString = "No bridge words from " +word1 +" to " +word2+" !";	
			}
			else
			{
				outputString = "The bridge words from " +word1 + " to " + word2 +" are: " + bridgeWord;
			}
		}
		else // word1或word2不存在
		{
			outputString = "No word1 or word2 in the graph!";
		}
		return outputString;
	}
	
	
	public String generateNewText(String inputText) // 生成新文本
	{
		String outputText ="";
		String[] splitText = inputText.toLowerCase().split("[^a-zA-Z]+");// 分割新文本
		
		int i = 0;
		for(;i+1<splitText.length;i++)            // 遍历新文本
		{
			outputText = outputText + splitText[i]+" ";
			//相邻词之间调用桥接词算法
			if(vexToInt.containsKey(splitText[i]) && vexToInt.containsKey(splitText[i+1]))
			{
				String bridgeWords = bridgeWords(splitText[i],splitText[i+1]);
				
				if(!bridgeWords.equals(""))
				{
					String[] temp = bridgeWords.split("[^a-zA-Z]+");
					
					Random for_insert = new Random();
					int insert_index = for_insert.nextInt(temp.length);
					

					outputText = outputText + temp[insert_index]+" ";
				}
			}
		}
		outputText = outputText + splitText[i];
		
		return outputText;
	}
	/**********************************
	 * 最短路径算法
	 * shortestPath用于生成一条最短路径(递归算法)
	 * f_to_one是单源最短路径
	 * calcShortestPath是查询两个点之间的最短路径
	 * 由于最短路径算法对于单源和指定的点对是重复部分，故写shortestPath函数来简化代码
	 */
	
	
	private String shortestPath(String start,String end)// 生成一条最短路径
	{
		String outt = "";
		int i = vexToInt.get(start);
		int j = vexToInt.get(end);
		
		if(P[i][j].equals(""))
		{
			return "";
		}
		else
		{
			stack_p.push(P[i][j]);
			outt = outt + shortestPath(start, P[i][j]); // 递归
			if(!stack_p.empty())
				outt = outt + stack_p.pop()+"->";
			outt = outt + shortestPath(P[i][j], end);
			if(!stack_p.empty())
				outt = outt + stack_p.pop()+"->";
			return outt;
		}
	}
	
	
	public String f_to_one(String start) // 单源最短路径
	{
		String outt= "";
		for(String end:vexToInt.keySet())// 遍历其他点
		{
			if(start.equals(end))
				continue;
			else
				outt = outt + calcShortestPath(start, end);
		}
		return outt;
	}

	public String calcShortestPath(String word1,String word2) // 点对之间的最短路径
	{
		String outt = "";
		if(vexToInt.containsKey(word1) && vexToInt.containsKey(word2))// word1word2存在
		{
			if(word1.equals(word2) || D[vexToInt.get(word1)][vexToInt.get(word2)] == Constant.INfINITE)
			{
				outt = "No way!";
			}
			else  // 有路径
			{
				String temp = shortestPath(word1,word2);
				outt = word1 +"->" + temp + word2 +
						"\nThe length of path is "+D[vexToInt.get(word1)][vexToInt.get(word2)]+"\n";
			}	
		}
		else
		{
			outt = word1 +" or "+word2+" is not in the graph!";
		}
		return outt;
	}
	
	public String randomWalk()//随机游走
	{
		String outt = "";
		
		HashMap<Edge, Boolean> visit = new HashMap<Edge,Boolean>();// 边是否遍历过，初始化false
		for(int j = 0;j<E;j++)
		{
			visit.put(edges[j], false);
		}
		

		Random r1 = new Random();
		int x = r1.nextInt(V);
		String start = vertexs[x].ver ;
        stack_p.push(start);          //随机一个点起手
        
        outt = start;
		LinkedList<Edge> p;

		while(!stack_p.empty())
		{
			p = vertexs[vexToInt.get(stack_p.pop())].edge;
			if(!p.isEmpty())   // 无路可走
			{
				int j = r1.nextInt(p.size());
				Edge edge = p.get(j);
				
				outt  = outt + " " + edge.end_edge;
				if(!visit.get(edge)) // 重复的边
				{
				   
					visit.replace(edge, true);
					
					stack_p.push(edge.end_edge);
				}
				
				
			}
		}
		return outt;
	}
	
	
	
   
}

