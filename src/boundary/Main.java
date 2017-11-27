package boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      // Read file fxml and draw interface.
      Parent root = FXMLLoader.load(getClass().getResource("/boundary/MyScence.fxml"));

      primaryStage.setTitle("Lab1-��Ա��-����ͼ");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();



    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }



}