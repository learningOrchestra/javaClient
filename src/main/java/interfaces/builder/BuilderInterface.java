package interfaces.builder;

import com.google.gson.JsonObject;

public interface BuilderInterface {
    
    //nao tenho ideia dos args
    JsonObject runSparkMLSync();

    JsonObject runSparkMLAsync();

    //nao tenho ideia dos args
    JsonObject runTensorFlowSync();

    JsonObject runTensorFlowAsync();

    JsonObject await(String pipelineURL);

    JsonObject searchAllBuilders();

    //retornar os metadados
    JsonObject searchBuilder(String builderName);

    //retorna os resultados do modelo
    JsonObject searchBuilderPredictions(String builderName);

    //retorna os resultados das tuplas
    JsonObject searchBuilderRegisterPredictions(String builderName, int pageSize, int currentPage);

}
