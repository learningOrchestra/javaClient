package interfaces.transform.datatype;

import java.util.Map;

import com.google.gson.JsonObject;

public interface DataTypeInterface {
    
    JsonObject updateDatasetTypeAsync(String datasetName, Map<String, String> types);

    JsonObject updateDatasetTypeSync(String datasetName, Map<String, String> types);


    /**
     * @param datasetURL is the dataset URL returned by asynchronous method calls of Learning Orchestra API
     * @return a JSON object indicating if the dataset is transformed correctly or not
     * @summary This method is responsible to lock the caller until a dataset is transformed correctly into
     * Learning Orchestra
     */
    JsonObject await(String datasetURL);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is synchronous, so it
     * locks the caller until the deletion finished. If a dataset was used by another task (Ex. projection, histogram, pca, tune
     * and so forth), it cannot be deleted.
     */
    JsonObject deleteDatasetTypeSync(String datasetName);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is asynchronous, so it
     * does not lock the caller until the deletion finished. Instead, it returns a JSON object with a URL for a future use.
     * The caller uses the URL for delete checks.
     * If a dataset was used by another task (Ex. projection, histogram, pca, tune and so forth), it cannot be deleted.
     */
    JsonObject deleteDatasetTypeAsync(String datasetName);

    /**
     * @return all datasets metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all datasets metadata, i.e., it does not retrieve the dataset content.
     */
    JsonObject searchAllDatasetTypes();


    /**
     * @param datasetName is the name of the dataset
     * @param pageSize    is the number of tuples or registers returned per page. It is an optional parameter.
     *                    If not present, the page size will be 20 tuples/registers.
     * @param currentPage is the page where the search will start. It is an optional parameter.
     *                    If not present, the search operation will start from page zero.
     * @return a page with some tuples or registers inside or an error if there is no such dataset.
     * The current page is also returned to be used in future content requests.
     * @summary This method is responsible for retrieving pages of the dataset content
     */
    JsonObject searchDatasetTypeContent(String datasetName, int pageSize, int currentPage);

    /**
     * @param datasetName is the name of the dataset
     * @return a page with the first 20 tuples or registers inside or an error if there is no such dataset.
     * @summary This method is responsible for retrieving the dataset content, precisely the first 20 tuples
     */
    JsonObject searchDatasetTypeContent(String datasetName);

}
