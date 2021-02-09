package implementations.transform.projection;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import implementations.util.NetworkCommunication;
import interfaces.transform.projection.ProjectionInterface;

public class ProjectionImplementation implements ProjectionInterface {

    @Override
    public JsonObject insertDatasetAttributeSync(
            String datasetName, String attribute, String existingAttribute,
            Map<String, String> values, boolean newDataset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject reduceDatasetSync(String datasetName, int sizeReduction, boolean newDataset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject enlargeDatasetSync(String datasetName, int sizeEnlarge, boolean newDataset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject joinDatasetsSync(Enumeration<String> datasetNames, String datasetName,
                                       boolean removeExistingDatasets) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject joinDatasetsSync(String datasetNameOne, String datasetNameTwo,
                                       Map<String, String> attributesAssociations, String datasetName,
                                       boolean removeExistingDatasets) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public JsonObject await(String datasetURL) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String address = properties.getProperty("address");
            String microservice = properties.getProperty("microservice_transform_projection");
            String searchMetadata = properties.getProperty("search_metadata");

            int sleep = Integer.parseInt(properties.getProperty("wait_time"));

            return NetworkCommunication.confirmAsync(
                    address, microservice, "microservice_transform_projection",
                    datasetURL, searchMetadata, null, sleep);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject deleteProjectionSync(String datasetName) {
        return null;
    }

    @Override
    public JsonObject deleteProjectionAsync(String datasetName) {
        return null;
    }

    @Override
    public JsonObject searchAllProjections() {
        return null;
    }

    @Override
    public JsonObject searchProjectionContent(String datasetName, int pageSize, int currentPage) {
        return null;
    }

    @Override
    public JsonObject searchProjectionContent(String datasetName) {
        return null;
    }

    @Override
    public JsonObject removeDatasetAttributesSync(String datasetName, String datasetOldName,
                                                  Enumeration<String> attributes,
                                                  boolean newDataset) {
        try {
            Properties properties = new Properties();

            JsonObject jsonObj = removeDatasetAttributesAsync(datasetName,datasetOldName,attributes,newDataset);

            String result = jsonObj.get("result").getAsString();

            String search = properties.getProperty("search_content");

            if (result.endsWith(search)) {
                JsonObject jsonObjectAux = await(datasetName);
                if (jsonObjectAux != null)
                    jsonObj = jsonObjectAux;
            }
            return jsonObj;

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }

    }

    @Override
    public JsonObject updateDatasetValuesSync(String datasetName, String attribute,
                                              Map<String, String> oldToNewValues) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject removeDatasetAttributesAsync(String datasetName, String datasetOldName,
                                                   Enumeration<String> attributes, boolean newDataset) {
        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            JsonObject request = new JsonObject();
            request.addProperty("datasetName", datasetName);
            request.addProperty("datasetOldName", datasetOldName);
            JsonArray array = new JsonArray();

            while (attributes.hasMoreElements())
                array.add(attributes.nextElement());

            request.add("names", array);
            JsonObject jsonObj;

            if (newDataset)
                jsonObj = NetworkCommunication.jsonRequest(
                        "POST", "microservice_transform_projection",
                        true, request);
            else
                jsonObj = NetworkCommunication.jsonRequest(
                        "PATCH", "microservice_transform_projection",
                        true, request);

            return jsonObj;

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject insertDatasetAttributeAsync(String datasetName, String attribute, String existingAttribute, Map<String, String> values, boolean newDataset) {
        return null;
    }

    @Override
    public JsonObject reduceDatasetAsync(String datasetName, int sizeReduction, boolean newDataset) {
        return null;
    }

    @Override
    public JsonObject enlargeDatasetAsync(String datasetName, int sizeEnlarge, boolean newDataset) {
        return null;
    }

    @Override
    public JsonObject joinDatasetsAsync(Enumeration<String> datasetNames, String datasetName, boolean removeExistingDatasets) {
        return null;
    }

    @Override
    public JsonObject joinDatasetsAsync(String datasetNameOne, String datasetNameTwo, Map<String, String> attributesAssociations, String datasetName, boolean removeExistingDatasets) {
        return null;
    }

    @Override
    public JsonObject updateDatasetValuesAsync(String datasetName, String attribute, Map<String, String> oldToNewValues) {
        return null;
    }
}
