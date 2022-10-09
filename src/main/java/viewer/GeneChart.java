package viewer;

import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;


import java.util.List;

public class GeneChart extends LineChart<Number, Number> {
  private static final double X_TICK_UNIT = 1;
  private static final double X_LOWER_BOUND = 0;
  private static final double Y_LOWER_BOUND = 0;
  private static final double Y_TICK_UNIT = 1;
  public static final int X_UPPER_BOUND = 20;
  public static final int Y_UPPER_BOUND = 10;


  public GeneChart(){
    super(new NumberAxis(X_LOWER_BOUND, X_UPPER_BOUND, X_TICK_UNIT),
            new NumberAxis(Y_LOWER_BOUND, Y_UPPER_BOUND, Y_TICK_UNIT));
    getXAxis().setSide(Side.BOTTOM);
    getYAxis().setSide(Side.LEFT);
    setCreateSymbols(false);
    getYAxis().setAutoRanging(true);
  }

  public void plot(List<Series<Number, Number>> seriesList, double timeUpperBound){
    ((NumberAxis) getXAxis()).setUpperBound(timeUpperBound);
    getData().clear();
    for(Series<Number, Number> series : seriesList){
      getData().add(series);
    }
  }
}
