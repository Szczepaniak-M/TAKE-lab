package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "chartBean")
@RequestScoped
public class ChartBean {

    public ChartBean() {
    }

    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        lineModel = new LineChartModel();
        lineModel.setZoom(true);
        lineModel.setTitle("Sinusoid");
        LineChartSeries dataSeries = new LineChartSeries();
        dataSeries.setLabel("sin(x)");
        for(int i = 0; i <=360; i+=10) {
            dataSeries.set(i , Math.sin(Math.toRadians(i)));
        }
        dataSeries.setFill(false);
        lineModel.addSeries(dataSeries);
        Axis yAxis = lineModel.getAxis(AxisType.X);
        yAxis.setMin(0);
        yAxis.setMax(360);
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

}
