package edu.ucsd.spendingtracker.view.charts;

import java.util.Map;

import edu.ucsd.spendingtracker.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class PieChartProvider implements IChartProvider{
    @Override
    public Node createChart(Map<Category, Double> data) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        data.forEach((cat, sum) -> {
            pieChartData.add(new PieChart.Data(cat.name(), sum));
        });
        
        PieChart chart = new PieChart(pieChartData);

        for(PieChart.Data entry : pieChartData){
            String color = Category.valueOf(entry.getName()).color;
            Node slice = entry.getNode();
            if(slice != null){
                slice.setStyle("-fx-pie-color: " + color  + ";");
            }
        }

        chart.setLegendVisible(false);
        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }
}
