package viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

  private Stage primaryStage;
  private AnchorPane rootLayout;
  public static void main(String[] args) {
    Application.launch(MainApp.class, args);
  }

  @Override
  public void start(Stage stage) {
    this.primaryStage = stage;
    this.primaryStage.setTitle("Regulatory Network");
    initRootLayout();
  }

  public void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainApp.fxml"));
      rootLayout = loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);

      // Give the controller access to the main app.
      MainAppController controller = loader.getController();
      controller.setMainApp(this);

      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

}
