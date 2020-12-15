package interfaces.builder;

import com.google.gson.JsonObject;

public interface BuilderInterface {
    
    //nao tenho ideia dos args
    JsonObject runSparkMlPipelineSync();

    JsonObject runSparkMlPipelineAsync();

    //nao tenho ideia dos args
    JsonObject runTensorFlowPipelineSync();

    JsonObject runTensorFlowPipelineAsync();

    JsonObject await(String asyncURL);

    JsonObject searchAllModels();

    //retornar os metadados
    JsonObject searchModel(String modelName);

    //retorna os resultados do modelo
    JsonObject searchModelPrediction(String modelName);

    //retorna os resultados das tuplas
    JsonObject searchDataPredictions(String modelName, int pageSize, int currentPage);

}
