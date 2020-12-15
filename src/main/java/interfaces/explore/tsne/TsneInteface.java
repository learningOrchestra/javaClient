package interfaces.explore.tsne;

import com.google.gson.JsonObject;

public interface TsneInteface {
    
    JsonObject runTsneSync(String datasetName, String tnseName, String attribute);

    JsonObject runTsneAsync(String datasetName, String tnseName, String attribute);

    JsonObject await(String asyncURL);

    JsonObject searchAllTsne();

    JsonObject searchTsneData(String tnseName);

    JsonObject searchTsnePlot(String tnseName);


}
