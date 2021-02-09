package implementations.dataset;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.google.gson.JsonObject;

import implementations.util.NetworkCommunication;
import interfaces.dataset.DatasetInterface;

public class DatasetImplementation implements DatasetInterface {

    @Override
    public JsonObject insertDatasetSync(String datasetURI, String datasetName) {
        try {
            Properties propertiess = new Properties();
            propertiess.load(new FileInputStream("config.properties"));

            Map<String, String> args = new TreeMap<>();
            args.put("datasetURI", datasetURI);
            args.put("datasetName", datasetName);

            JsonObject request = new JsonObject();
            for (String key : args.keySet()) {
                request.addProperty(key, args.get(key));
            }

            JsonObject jsonObj = NetworkCommunication.jsonRequest(
                    "POST", "microservice_dataset", false, request);

            if (jsonObj == null) throw new AssertionError();
            String result = jsonObj.get("result").getAsString();

            String search = propertiess.getProperty("search_content");

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
    public JsonObject insertDatasetAsync(String datasetURI, String datasetName) {
        try {
            Map<String, String> args = new TreeMap<>();
            args.put("datasetURI", datasetURI);
            args.put("datasetName", datasetName);
            JsonObject request = new JsonObject();
            for (String key : args.keySet()) {
                request.addProperty(key, args.get(key));
            }
            return NetworkCommunication.jsonRequest(
                    "POST", "microservice_dataset", false, request);

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject updateDatasetSync(String datasetURI, String datasetName) {
        try {
            JsonObject request = new JsonObject();
            request.addProperty("datasetName", datasetName);
            request.addProperty("datasetURI", datasetURI);

            JsonObject jsonObj = NetworkCommunication.jsonRequest(
                    "PUT", "microservice_dataset", true, request);

            if (jsonObj == null) throw new AssertionError();

            String result = jsonObj.get("result").getAsString();
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            String search = properties.getProperty("search_content");

            if (result.endsWith(search)) {
                JsonObject jsonObjectAux = await(datasetName);
                if (jsonObjectAux != null)
                    jsonObj = jsonObjectAux;
            }
            return jsonObj;

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject updateDatasetAsync(String datasetURI, String datasetName) {
        try {
            JsonObject request = new JsonObject();
            request.addProperty("datasetName", datasetName);
            request.addProperty("datasetURI", datasetURI);

            return NetworkCommunication.jsonRequest(
                    "PUT", "microservice_dataset", true, request);

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject await(String datasetURL) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String address = properties.getProperty("address");
            String microService = properties.getProperty("microservice_dataset");
            String searchMetadata = properties.getProperty("search_metadata");
            int sleep = Integer.parseInt(properties.getProperty("wait_time"));

            return NetworkCommunication.confirmAsync(
                    address, microService, "microservice_dataset",
                    datasetURL, searchMetadata, null, sleep);

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject deleteDatasetSync(String datasetName) {
        try {
            JsonObject jsonObj = NetworkCommunication.confirm(
                    datasetName, "DELETE", "microservice_dataset");
            if (jsonObj == null) throw new AssertionError();

            String result = jsonObj.get("result").getAsString();
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
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
    public JsonObject deleteDatasetAsync(String datasetName) {
        try {
            return NetworkCommunication.confirm(
                    datasetName, "DELETE", "microservice_dataset");
        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject searchAllDatasets() {
        try {
            return NetworkCommunication.confirm(
                    "", "GET", "microservice_dataset");
        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject searchDatasetContent(String datasetName, int pageSize, int currentPage) {
        try {
            String searchContent = " ?query={}&limit=" + pageSize + "&skip=" + currentPage;
            return NetworkCommunication.confirm(
                    datasetName + searchContent,
                    "GET", "microservice_dataset");
        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonObject searchDatasetContent(String datasetName) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            String searchContent = properties.getProperty("search_content");

            return NetworkCommunication.confirm(
                    datasetName + searchContent, "GET", "microservice_dataset");

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }


}
