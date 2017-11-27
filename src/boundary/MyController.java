package boundary;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ResourceBundle;

import control.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class MyController implements Initializable {

  // private Desktop desktop = Desktop.getDesktop();
//  @FXML
//  public static Graph myGraph;

  @FXML
  private Stage stageFile = new Stage();
  @FXML
  private Stage stageSearchBridge = new Stage();
  @FXML
  final private Stage newtext = new Stage();
  @FXML
  private Stage shortestPath = new Stage();

  @FXML
  private Stage randStage = new Stage();

  
 
 
 


  @FXML
  private Button showGraph;
  @FXML
  private Button searchBridge;
  @FXML
  private Button newText;
  @FXML
  private Button shorestPath;
  @FXML
  private Button randWalk;



  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // TODO (don't really need to do anything here).

  }

  // When user click on myButton
  // this method will be called.
  /**.
   * @param myGraph the myGraph to set
   */
//  public static void setMyGraph(Graph myGraph) {
//    MyController.myGraph = myGraph;
//  }

  /**.
   * @param ���ļ�
   */
  @FXML
  public void openFiles(ActionEvent event) throws IOException {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter =
        new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
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
      int i = in.read(temp);


      in.close();

      String tempString = new String(temp, 0, i);
      tempString = tempString.toLowerCase();

      String[] a = tempString.split("[^a-zA-Z]+");
      Control.build(a);
    } else {
      // *******************�ļ���ʧ�ܵĴ���
      showGraph.setVisible(false);
      searchBridge.setVisible(false);
      newText.setVisible(false);
      shorestPath.setVisible(false);
      randWalk.setVisible(false);

    }


  }

  @FXML
  public void exit(ActionEvent event) { // �˳���ť�¼�

    System.exit(0);
  }

  /**.
   * @param չʾ��ť�¼�
   */
  @FXML
  public void showGraph(ActionEvent event) {
    Control.showDirectedGraph();

    try {
      Desktop.getDesktop().open(new File("GRAPH1.jpg"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  /**.
   * @param �򿪲�ѯ�ŽӴʽ���
   */
  @FXML
  public void searchBridge(ActionEvent event) {
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

 


  /**.
   * @param �������ı�����
   */
  @FXML
  public void newText(ActionEvent event) {
    newtext.setTitle("�����ı�");
    Pane myPane = null;
    try {
      myPane = (Pane) FXMLLoader.load(getClass().getResource("NewText.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene myScene = new Scene(myPane);
    newtext.setScene(myScene);
    newtext.show();

  }



  /**.
   * @param ���ɶԶ�·������
   */
  @FXML
  public void shortestPath(ActionEvent event) {
    shortestPath.setTitle("���·��");
    Pane myPane = null;
    try {
      myPane = (Pane) FXMLLoader.load(getClass().getResource("ShortestPath.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene myScene = new Scene(myPane);
    shortestPath.setScene(myScene);
    shortestPath.show();
  }

  


  /**.
   * @param ������ߴ���
   */
  @FXML
  public void randWalk(ActionEvent event) {
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

    String stringX = Control.randomWalk();

    RandWalk.wwalk = stringX.split(" ");
    RandWalk.ww = 0;
  }



 

}
