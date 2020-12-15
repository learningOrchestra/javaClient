package interfaces.transform.datatype;

import java.util.Map;

import com.google.gson.JsonObject;

public interface DataTypeInterface {
    
    JsonObject updateDatasetTypesAsync(String datasetName, Map<String, String> types);

    JsonObject updateDatasetTypesSync(String datasetName, Map<String, String> types);

    JsonObject awaitUpdateOperation(String datasetName);

    JsonObject searchDatasetTypes(String datasetName);

    JsonObject isUpdatable(String datasetName, String attribute, String newType);

}
