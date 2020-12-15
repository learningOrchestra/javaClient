package implementations.dataset;

import com.google.gson.JsonObject;

public class DatasetImplementationTypoCorrection extends DatasetImplementation {


    @Override
    public JsonObject searchDatasetContent(String datasetName, int pageSize, int pageStart) {
        return super.searchDatasetContent(typoCorrection(datasetName), pageSize, pageStart);
    }

    @Override
    public JsonObject insertDatasetSync(String datasetURI, String datasetName) {
        return super.insertDatasetSync(datasetURI, typoCorrection(datasetName));
    }


    private String typoCorrection(String datasetName) {
        return null;
    }
}
