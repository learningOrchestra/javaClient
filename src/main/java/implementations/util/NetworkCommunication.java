package implementations.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import implementations.dataset.Result;

public class NetworkCommunication {

    public static JsonObject jsonRequest(String method, String microserviceName,
                                         boolean usesDatasetName, JsonObject jsonArgs) {
        HttpURLConnection connection;

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String address = properties.getProperty("address");
            String microService;

            if (!usesDatasetName)
                microService = properties.getProperty(microserviceName);
            else
                microService = properties.getProperty(microserviceName + jsonArgs.get("datasetName"));

            URL url = new URL(address + microService);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.connect();

            Gson gson = new Gson();
            String json = gson.toJson(jsonArgs);

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            osw.write(json);
            osw.flush();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            JsonObject jsonObj = gson.fromJson(stringBuilder.toString(), JsonObject.class);
            osw.close();
            bufferedReader.close();
            inputStreamReader.close();
            return jsonObj;

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    public static JsonObject confirmAsync(String address, String microservice,
                                          String microserviceName, String databaseName,
                                          String searchMetadata, JsonObject jsonObject, int sleep) {
        try {
            String response = "false";

            while (response.equals("false")) {
                Thread.sleep(sleep);

                JsonObject jsonObjResp = confirm(
                        databaseName + searchMetadata, "GET", microserviceName);

                Type listType = new TypeToken<ArrayList<Result>>() {}.getType();

                Gson gson = new Gson();
                if (jsonObjResp == null) throw new AssertionError();
                List<Result> results = gson.fromJson(
                        jsonObjResp.getAsJsonArray("result"), listType);
                Result result = results.get(0);
                response = result.getFinished();

                if (response.equals("true")) jsonObject = jsonObjResp;
            }
            return jsonObject;

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }

    public static JsonObject confirm(String operation, String method, String microserviceName) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));

            String address = properties.getProperty("address");
            String microService = properties.getProperty(microserviceName);

            HttpURLConnection connection =
                    (HttpURLConnection) new URL(address + microService + operation).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestMethod(method);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            Gson gson = new Gson();

            return gson.fromJson(stringBuilder.toString(), JsonObject.class);

        } catch (Exception exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
            return null;
        }
    }
}
