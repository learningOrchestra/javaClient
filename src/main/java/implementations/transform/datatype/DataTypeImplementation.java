package implementations.transform.datatype;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import implementations.util.NetworkCommunication;
import interfaces.transform.datatype.DataTypeInterface;

public class DataTypeImplementation implements DataTypeInterface {

    @Override
    public JsonObject updateDatasetTypesAsync(String datasetName, Map<String, String> types) {
        JsonObject request = new JsonObject();
        request.addProperty("datasetName", datasetName);
        JsonArray array = new JsonArray();
        Gson gson = new Gson();

        for (Entry<String, String> e : types.entrySet())
            array.add(gson.toJsonTree(e));

        request.add("types", array);

        return NetworkCommunication.jsonRequest(
                "PATCH", "microservice_transform_datatype",
                true, request);
    }

    @Override
    public JsonObject searchDatasetTypes(String datasetName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject isUpdatable(String datasetName, String attribute, String newType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject awaitUpdateOperation(String datasetName) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String address = properties.getProperty("address");
            String microservice = properties.getProperty("microservice_transform_datatype");
            String searchMetadata = properties.getProperty("search_metadata");

            int sleep = Integer.parseInt(properties.getProperty("wait_time"));

            return NetworkCommunication.confirmAsync(
                    address, microservice, "microservice_transform_datatype",
                    datasetName, searchMetadata, null, sleep);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }

    @Override
    public JsonObject updateDatasetTypesSync(String datasetName, Map<String, String> types) {
        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            JsonObject request = new JsonObject();
            request.addProperty("datasetName", datasetName);
            JsonArray array = new JsonArray();
            Gson gson = new Gson();
            for (Entry<String, String> e : types.entrySet())
                array.add(gson.toJsonTree(e));

            request.add("types", array);

            JsonObject jsonObj = NetworkCommunication.jsonRequest(
                    "PATCH", "microservice_transform_datatype",
                    true, request);

            if (jsonObj == null) throw new AssertionError();
            String result = jsonObj.get("result").getAsString();
            String search = properties.getProperty("search_content");

            if (result.endsWith(search)) {
                JsonObject jsonObjectAux = awaitUpdateOperation(datasetName);
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
}
