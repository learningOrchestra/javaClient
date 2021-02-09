package interfaces.dataset;

import com.google.gson.JsonObject;

/**
 * @author Learning Orchestra
 * @version 1.0
 * @summary This interface is responsible for the dataset manipulation, i.e.,
 * insertion, update, delete and search.
 */
public interface DatasetInterface {

    /**
     * @param datasetURI  is the dataset URI used to find the data in the Web
     * @param datasetName is the dataset name used inside the Learning Orchestra
     * @return a JSON object with an error or warning message or a URL indicating the correct operation
     * (the caller must use such an URL to perform search operations).
     * @summary This method is responsible to insert a dataset from a URI synchronously,
     * i.e., the caller waits until the dataset is inserted into the Learning Orchestra storage mechanism.
     */
    JsonObject insertDatasetSync(String datasetURI, String datasetName);

    /**
     * @param datasetURI  is the dataset URI used to find the data in the Web
     * @param datasetName is the dataset name used inside the Learning Orchestra
     * @return a JSON object with an error or warning message or a URL indicating the correct operation
     * (the caller must use such an URL to proceed future checks to verify if the dataset is inserted).
     * @summary This method is responsible to insert a dataset from a URI asynchronously,
     * i.e., the caller does not wait until the dataset is inserted into the Learning Orchestra storage mechanism.
     * Instead, the caller receives a JSON object with a URL to proceed future calls to verify if the dataset is
     * inserted.
     * @see #await you must understand the await method description because it is responsible to check if the dataset
     * is inserted or not.
     */
    JsonObject insertDatasetAsync(String datasetURI, String datasetName);

    /**
     * @param datasetName is the dataset name used inside the Learning Orchestra previously in a insertion operation
     * @param datasetURI is the new URI to be used to change the dataset. If an identical insertion URI is used, no update is performed
     * @return a JSON object with an error or warning message or a URL indicating the correct operation
     * (the caller must use such an URL to perform search operations).
     * @summary This method is responsible to update a dataset using a new URI and synchronously,
     * i.e., the caller waits until the dataset is replaced into the Learning Orchestra storage mechanism.
     * The old dataset is removed and the same identification is used for the new one.
     */
    JsonObject updateDatasetSync(String datasetURI, String datasetName);

    /**
     * @param datasetName is the dataset name used inside the Learning Orchestra
     * @param datasetURI is the new URI to be used to change the dataset. If an identical insertion URI is used, no update is performed
     * @return a JSON object with an error or warning message or a URL indicating the correct operation
     * (the caller must use such an URL to proceed future checks to verify if the dataset is updated).
     * @summary This method is responsible to update a dataset from a URI asynchronously,
     * i.e., the caller does not wait until the dataset is replaced into the Learning Orchestra storage mechanism.
     * Instead, the caller receives a JSON object with a URL to proceed future calls to verify if the dataset is
     * updated.
     * @see #await you must understand the await method description because it is responsible to check if the dataset
     * is updated or not.
     */
    JsonObject updateDatasetAsync(String datasetURI, String datasetName);


    /**
     * @param datasetURL is the dataset URL returned by asynchronous method calls of Learning Orchestra API
     * @return a JSON object indicating if the dataset is inserted/updated correctly or a null result
     * @summary This method is responsible to lock the caller until a dataset is inserted or updated correctly into
     * Learning Orchestra
     * @see #insertDatasetAsync the method insertDatasetAsync because it must be called before this method call
     * @see #updateDatasetAsync the method updateDatasetAsync because it must be called before this method call
     */
    JsonObject await(String datasetURL);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is synchronous, so it
     * locks the caller until the deletion finished. If a dataset was used by another task (Ex. projection, histogram, pca, tune
     * and so forth), it cannot be deleted.
     */
    JsonObject deleteDatasetSync(String datasetName);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is asynchronous, so it
     * does not lock the caller until the deletion finished. Instead, it returns a JSON object with a URL for a future use.
     * The caller uses the URL for delete checks.
     * If a dataset was used by another task (Ex. projection, histogram, pca, tune and so forth), it cannot be deleted.
     */
    JsonObject deleteDatasetAsync(String datasetName);

    /**
     * @return all datasets metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all datasets metadata, i.e., it does not retrieve the dataset content.
     */
    JsonObject searchAllDatasets();


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
    JsonObject searchDatasetContent(String datasetName, int pageSize, int currentPage);

    /**
     * @param datasetName is the name of the dataset
     * @return a page with the first 20 tuples or registers inside or an error if there is no such dataset.
     * @summary This method is responsible for retrieving the dataset content, precisely the first 20 tuples
     */
    JsonObject searchDatasetContent(String datasetName);

}
