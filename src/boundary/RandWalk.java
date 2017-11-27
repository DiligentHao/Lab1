package boundary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RandWalk {
  @FXML
  private TextField walkText;
  @FXML
  public static String[] wwalk;
  @FXML
  public static int ww = 0;
  
  /**.
   * @param 游走按钮事件
   */
  @FXML
  public void walk(ActionEvent event) {

    if (ww < wwalk.length) {

      String stringX = "";
      for (int i = 0; i <= ww; i++) {
        if (i == 0) {
          stringX = wwalk[0];
        } else {
          stringX = stringX + " " + wwalk[i];
        }
      }
      ww++;

      walkText.setText(stringX);
    } else {
      walkText.setText("遍历完了");
    }
  }

  /**.
   * @param 保存按钮事件
   */
  @FXML
  public void saveWalk(ActionEvent event) {
    String stringX = "";
    for (int i = 0; i < ww; i++) {
      if (i == 0) {
        stringX = wwalk[0];
      } else {
        stringX = stringX + " " + wwalk[i];
      }
    }
    stringX += "\r\n";

    try {
      File file = new File("randWalk.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      Writer fileWritter = new FileWriter(file, true);
      fileWritter.write(stringX);
      fileWritter.close();

    } catch (IOException e) {
      e.printStackTrace();
      // TODO: handle exception
    }

  }
}
