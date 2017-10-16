package application;

//The third file's change

//B2 change change change

//B1 change change change

//C4 change change change

// partner change
//My changeee
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
   public void openFiles(ActionEvent event) throws IOException {  // ���ļ�
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
    	   //*******************�ļ���ʧ�ܵĴ���
    	   showGraph.setVisible(false);
    	   searchBridge.setVisible(false);
    	   newText.setVisible(false);
    	   shorestPath.setVisible(false);
    	   randWalk.setVisible(false);
    	   
       }

	   
   }
   @FXML
   public void exit(ActionEvent event) {   // �˳���ť�¼�
	   
	   System.exit(0);
   }
   
   @FXML
   public void showGraph(ActionEvent event)// չʾ��ť�¼�
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
   public void searchBridge(ActionEvent event) // �򿪲�ѯ�ŽӴʽ���
   {
	   stageSearchBridge.setTitle("��ѯ�ŽӴ�");
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
   public void Search(ActionEvent event) { // ��ѯ��ť
	   
	   String xString  = myGraph.queryBridgeWords(bridgeWord1.getText(), bridgeWord2.getText());
	   
	   bridgeOut.setText(xString);
   }
   
   
   @FXML
   public void newText(ActionEvent event) // �������ı�����
   {
	    newTEXT.setTitle("�����ı�");
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
   public void Change(ActionEvent event) // ���ɴ���
   {
	   String xString  =  myGraph.generateNewText(inText.getText());
	   outText.setText(xString);
   }
   
   
   @FXML
   public void shortestPath(ActionEvent event) // ���ɶԶ�·������
   {
	    ShortestPath.setTitle("���·��");
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
   public void findPath(ActionEvent event)  // ��ӡ���·����ť�¼�
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
   public void randWalk(ActionEvent event)  // ������ߴ���
   {
        randStage.setTitle("�������");
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
   public void Walk(ActionEvent event)  // ���߰�ť�¼�
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
		   walkText.setText("��������");
	   }
   }
   @FXML
   public void saveWalk(ActionEvent event) { //���水ť�¼�
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

    private int V = 0;  // �ڵ���� 
	private int E = 0;  // �߸���
	
	private HashMap<String, Integer> vexToInt;  // ����ӳ�䵽��Ӧ�Ľڵ��±�
	
	private Vertex[] vertexs;                   // ��������
	private Edge[] edges;                       // ������
	
	private int[][] D;                          // Floyd�㷨�������
	private String [][] P;						// Floyd�㷨���·������
	
	private Stack<String> stack_p = new Stack<String>();  // ջ���ַ������ͣ�
	
	
	class Vertex								// ����ṹ��
	{
		String ver;                             // ����
		LinkedList<Edge> edge;                  // ������
		
		public Vertex()
		{
			ver = "";
			edge = new LinkedList<Edge>();
		}
	}
	
	class Edge                                  // �߽ṹ��
	{
		String start_edge = "";
		String end_edge = "";
		int weight = 0;		                    // Ȩֵ
	}

	public void readGraph(String[] readin)      //����ͼ�㷨
	{	
		vexToInt = new HashMap<String, Integer>();
		edges = new Edge[readin.length];
		
		for(int i = 0;i<readin.length;i++)       // �߳�ʼ��
		{
			edges[i] = new Edge();
		}
		
		for(int i = 0;i+1<readin.length;i++)     // ��ȡ��
		{
			String title = readin[i];
			String last = readin[i+1];
		
			int flag;
			flag = 0;
			for(int j = 0;j<E;j++)                // ���б߼�Ȩֵ
			{
				if(edges[j].end_edge.equals(last)&&edges[j].start_edge.equals(title) )
				{
					edges[j].weight++;
					flag = 1;
					break;
				}
			}
			if(flag == 0)                          // û�б����
			{
				edges[E].end_edge = last;
				edges[E].start_edge = title;
				edges[E].weight = 1;
				E++;
			}
		
		}
		
		for(int i = 0;i<readin.length;i++)          // ����ӳ�䶥���±�Hashmap��ʼ��
		{
			vexToInt.put(readin[i],-1);
		}
		int value = 0;
		for(String key:vexToInt.keySet())           // Ϊ����(��)��ӳ���±�ֵ(ֵ)
		{
			vexToInt.replace(key,value);
			value++;
		}
		V = value;
		vertexs = new Vertex[value];
		

		
		D = new int[V][V];
		P = new String[V][V];
		

		//************Ϊ�˷�ֹ�û�������Floyd�㷨��ʹ����̾����㷨��
		//************��������ͼʱ���ø��������㷨������������
		
		for(String key:vexToInt.keySet())          // �������D��·������P��ʼ��
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
		
		
		for(int i = 0;i<E;i++)                      // ���߼����Ӧ���������
		{
			int j = vexToInt.get(edges[i].start_edge);
			vertexs[j].ver = edges[i].start_edge;
			vertexs[j].edge.add(edges[i]);
	
			
			D[vexToInt.get(edges[i].start_edge)][vexToInt.get(edges[i].end_edge)] = edges[i].weight;
		}
		
		for(String k:vexToInt.keySet())            // Floyd�㷨
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
	
	public void showDirectedGraph() {         // չʾͼ�㷨
		GraphViz gV = new GraphViz();
		String adj;
		
		gV.addln(gV.start_graph());
		
		for(int j = 0;j<E;j++)
		{
			adj = edges[j].start_edge + "->" + edges[j].end_edge +"[label=\"" +  edges[j].weight +"\"]";
			gV.addln(adj);
		}
		gV.addln(gV.end_graph());
		
		
		File file = new File("GRAPH1.jpg");  // ����.jpg
		gV.writeGraphToFile(gV.getGraph(gV.getDotSource(), "jpg"), file);
		
		
	}
	
	/**********************************************************
	 * ���ڿγ�Ҫ���queryBridgeWords��generateNewText����Ҫ�ŽӴ�             *
	 * ���Խ��ظ���������ΪbridgeWordsּ�������ŽӴʣ������ŽӴʵĲ�ѯ���������ı� *
	 * ********************************************************
	*/
	private String bridgeWords(String word1,String word2) // �����ŽӴ��㷨
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
		while(!maybridge.empty())                      // ���ܵ��ŽӴ�ջ��Ϊ�գ���˵�����п��ܵ��ŽӴ�
		{
			int maybrige_index = vexToInt.get(maybridge.pop()); // ������ջ
			LinkedList<Edge> bridgeLink = vertexs[maybrige_index].edge;
			
			for(Edge end:bridgeLink)                // ���������ŽӴʵıߣ������end_edgeΪword2�ģ�˵�������ŽӴ�Ϊ����ŽӴʣ�������wholebridgeջ
			{
				if(end.end_edge.equals(word2))
				{
					wholebridge.push(vertexs[maybrige_index].ver);
				}
			}
		}
		
		while(!wholebridge.empty())              // ȫ���ŽӴ�wholebridge��Ϊ�գ���ջ
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
		return brigewords;                       // �����ŽӴ�
	}
	
	public String queryBridgeWords(String word1,String word2) // ��ѯ�ŽӴ�
	{
		String outputString = "";
		if(vexToInt.containsKey(word1) && vexToInt.containsKey(word2))// word1word2������
		{
			String bridgeWord = bridgeWords(word1, word2); // ���������ŽӴ��㷨
			if(bridgeWord.equals(""))
			{
				outputString = "No bridge words from " +word1 +" to " +word2+" !";	
			}
			else
			{
				outputString = "The bridge words from " +word1 + " to " + word2 +" are: " + bridgeWord;
			}
		}
		else // word1��word2������
		{
			outputString = "No word1 or word2 in the graph!";
		}
		return outputString;
	}
	
	
	public String generateNewText(String inputText) // �������ı�
	{
		String outputText ="";
		String[] splitText = inputText.toLowerCase().split("[^a-zA-Z]+");// �ָ����ı�
		
		int i = 0;
		for(;i+1<splitText.length;i++)            // �������ı�
		{
			outputText = outputText + splitText[i]+" ";
			//���ڴ�֮������ŽӴ��㷨
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
	 * ���·���㷨
	 * shortestPath��������һ�����·��(�ݹ��㷨)
	 * f_to_one�ǵ�Դ���·��
	 * calcShortestPath�ǲ�ѯ������֮������·��
	 * �������·���㷨���ڵ�Դ��ָ���ĵ�����ظ����֣���дshortestPath�������򻯴���
	 */
	
	
	private String shortestPath(String start,String end)// ����һ�����·��
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
			outt = outt + shortestPath(start, P[i][j]); // �ݹ�
			if(!stack_p.empty())
				outt = outt + stack_p.pop()+"->";
			outt = outt + shortestPath(P[i][j], end);
			if(!stack_p.empty())
				outt = outt + stack_p.pop()+"->";
			return outt;
		}
	}
	
	
	public String f_to_one(String start) // ��Դ���·��
	{
		String outt= "";
		for(String end:vexToInt.keySet())// ����������
		{
			if(start.equals(end))
				continue;
			else
				outt = outt + calcShortestPath(start, end);
		}
		return outt;
	}

	public String calcShortestPath(String word1,String word2) // ���֮������·��
	{
		String outt = "";
		if(vexToInt.containsKey(word1) && vexToInt.containsKey(word2))// word1word2����
		{
			if(word1.equals(word2) || D[vexToInt.get(word1)][vexToInt.get(word2)] == Constant.INfINITE)
			{
				outt = "No way!";
			}
			else  // ��·��
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
	
	public String randomWalk()//�������
	{
		String outt = "";
		
		HashMap<Edge, Boolean> visit = new HashMap<Edge,Boolean>();// ���Ƿ����������ʼ��false
		for(int j = 0;j<E;j++)
		{
			visit.put(edges[j], false);
		}
		

		Random r1 = new Random();
		int x = r1.nextInt(V);
		String start = vertexs[x].ver ;
        stack_p.push(start);          //���һ��������
        
        outt = start;
		LinkedList<Edge> p;

		while(!stack_p.empty())
		{
			p = vertexs[vexToInt.get(stack_p.pop())].edge;
			if(!p.isEmpty())   // ��·����
			{
				int j = r1.nextInt(p.size());
				Edge edge = p.get(j);
				
				outt  = outt + " " + edge.end_edge;
				if(!visit.get(edge)) // �ظ��ı�
				{
				   
					visit.replace(edge, true);
					
					stack_p.push(edge.end_edge);
				}
				
				
			}
		}
		return outt;
	}
	
	
	
   
}

