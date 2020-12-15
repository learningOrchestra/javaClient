package interfaces.explore.histogram;

import com.google.gson.JsonObject;

public interface HistogramInterface {
    
    JsonObject runHistogramSync(String datasetName, String histogramName, String attribute);

    JsonObject runHistogramAsync(String datasetName, String histogramName, String attribute);

    JsonObject await(String asyncURL);

    JsonObject searchAllHistograms();

    JsonObject searchHistogramData(String histogramName);

    JsonObject searchHistogramPlot(String histogramName);

}
