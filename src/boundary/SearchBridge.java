package boundary;

import control.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchBridge {
  @FXML
  private TextField bridgeWord1;
  @FXML
  private TextField bridgeWord2;
  @FXML
  private TextField bridgeOut;
	
  /**.
   * @param ≤È—Ø∞¥≈•
   */
  @FXML
  public void search(ActionEvent event) {

    String stringX = Control.queryBridgeWords(bridgeWord1.getText(), bridgeWord2.getText());

    bridgeOut.setText(stringX);
  }
}
