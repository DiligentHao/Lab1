package boundary;

import control.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class CreateNewText {
	
  @FXML
  private TextArea inText;
  @FXML
  private TextArea outText;
  /**.
   * @param Éú³É´°¿Ú
   */
  @FXML
  public void change(ActionEvent event) {
    String stringX = Control.generateNewText(inText.getText());
    outText.setText(stringX);
  }


}
