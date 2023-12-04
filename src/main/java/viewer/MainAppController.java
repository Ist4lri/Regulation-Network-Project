package viewer;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.network.RegulatoryNetwork;
import model.network.RegulatoryNetworkDataManager;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class MainAppController {
  public static final FileChooser.ExtensionFilter REGULATORY_NETWORK_FILES = new ExtensionFilter(
      "Regulatory Network Files", "*.rgn");
  private MainApp mainApp;

  private final RegulatoryNetworkDataManager regulatoryNetworkDataManager = new RegulatoryNetworkDataManager();
  private final RegulatoryNetworkReader regulatoryNetworkReader = new RegulatoryNetworkReader();
  private final RegulatoryNetworkWriter regulatoryNetworkWriter = new RegulatoryNetworkWriter();

  private RegulatoryNetwork regulatoryNetwork = new RegulatoryNetwork(new ArrayList<>(), new ArrayList<>(), 0.001, 20);
  @FXML
  public GeneChart geneChart;

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }

  @FXML
  public void generateData() {
    regulatoryNetwork = regulatoryNetworkDataManager.generate();
    plot(regulatoryNetwork);
  }

  @FXML
  public void openFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(REGULATORY_NETWORK_FILES);
    File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

    if (file != null) {
      try {
        BufferedReader bufferedReader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_16);
        regulatoryNetwork = regulatoryNetworkReader.read(bufferedReader);
        plot(regulatoryNetwork);
        bufferedReader.close();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }
  }

  private void plot(RegulatoryNetwork regulatoryNetwork) {
    geneChart.plot(regulatoryNetwork.getData(), regulatoryNetwork.getTimeUpperBound());
  }

  @FXML
  public void saveFile() {
    FileChooser fileChooser = new FileChooser();

    fileChooser.getExtensionFilters().addAll(REGULATORY_NETWORK_FILES);

    File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

    if (file != null) {
      if (!file.getName().toLowerCase().endsWith(".rgn")) {
        file = new File(file.getAbsolutePath() + ".rgn");
      }
      try {
        BufferedWriter stream = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_16);
        regulatoryNetworkWriter.write(stream, regulatoryNetwork);
        stream.close();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }
  }
}
