package interfaces.explore.pca;

import com.google.gson.JsonObject;

public interface PcaInterface {
    
    JsonObject runPcaSync(String datasetName, String pcaName, String attribute);

    JsonObject runPcaAsync(String datasetName, String pcaName, String attribute);

    JsonObject await(String asyncURL);

    JsonObject searchAllPca();

    JsonObject searchPcaData(String pcaName);

    JsonObject searchPcaPlot(String pcaName);

}
