package boundary;

import control.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FindPath {
  @FXML
  private TextField shortWord1;
  @FXML
  private TextField shortWord2;
  @FXML
  private TextArea shortOut;
  /**.
   * @param 打印最短路径按钮事件
   */
  @FXML
  public void findPath(ActionEvent event) {
    String word1 = shortWord1.getText();
    String word2 = shortWord2.getText();
    String stringX;
    if (word1.equals("") && word2.equals("")) {
      shortOut.setText("No words!");
    } else if (!word1.equals("") && word2.equals("")) {
      stringX = Control.allToOne(word1);
      shortOut.setText(stringX);
    } else if (!word1.equals("") && !word2.equals("")) {
      stringX = Control.calcshortestPath(word1, word2);
      shortOut.setText(stringX);
    } else {
      shortOut.setText("We dont support from all to word2 path!");
    }
  }

}
