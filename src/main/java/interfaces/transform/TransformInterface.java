package interfaces.transform;

import com.google.gson.JsonObject;

import java.util.Enumeration;

/**
 * @author Learning Orchestra
 * @version 1.0
 * @summary This interface is responsible for scikitlearn or tensorflow transformations, i.e.,
 * it runs any transformation algorithm of such tools transparently. It is important to understand that
 * the transformed dataset cannot be opened by Learning Orchestra in the current version because each tool
 * has its format particularities. On future versions we will try to enable content searches and consequently pagination for large datasets.
 */
public interface TransformInterface {

    /**
     * @summary This method runs any scikit-learn or tensorflow transformation method. The run operation is synchronous, this way the caller waits until it finishes.
     * @param toolName represents the tool name, precisely scikit-learn or tensorflow
     * @param datasetName represents the name of the transformed dataset
     * @param description represents a description for the transform operation
     * @param toolPackage represents the tool transformation package. Ex. sklearn.preprocessing
     * @param toolClass represents the class of the used package. Ex. LabelEncoder
     * @param constructorParameters represents the constructors parameters, i.e., the arguments used to instantiate a class object
     * @param methodName represents the method of the class, which will be called. Ex. fit_transform
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     */
    public JsonObject runDatasetTransformSync(String toolName, String datasetName, String description,
                                                    String toolPackage, String toolClass, Enumeration<String> constructorParameters,
                                                    String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method runs any scikit-learn or tensorflow transformation method. The run operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await method for wait condition detail
     * @param toolName represents the tool name, precisely scikit-learn or tensorflow
     * @param datasetName represents the name of the transformed dataset
     * @param description represents a description for the transform operation
     * @param toolPackage represents the tool transformation package. Ex. sklearn.preprocessing
     * @param toolClass represents the class of the used package. Ex. LabelEncoder
     * @param constructorParameters represents the constructors parameters, i.e., the arguments used to instantiate a class object
     * @param methodName represents the method of the class, which will be called. Ex. fit_transform
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     */
    public JsonObject runDatasetTransformAsync(String toolName, String datasetName, String description,
                                                   String toolPackage, String toolClass, Enumeration<String> constructorParameters,
                                                   String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method updates previous run operation, i.e., it re-executes a transform method. The update operation is synchronous, this way the caller waits until it finishes.
     * @param datasetName represents the name of the transformed dataset
     * @param methodName represents the method of the class, which will be called. Ex. fit_transform
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     */
    public JsonObject updateDatasetTransformSync(String datasetName, String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method updates previous run operation, i.e., it re-executes a transform method. The update operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await method for wait condition detail
     * @param datasetName represents the name of the transformed dataset
     * @param methodName represents the method of the class, which will be called. Ex. fit_transform
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     */
    public JsonObject updateDatasetTransformAsync(String datasetName, String methodName, Enumeration<String> methodParameters);

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
    JsonObject deleteTransformSync(String datasetName);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is asynchronous, so it
     * does not lock the caller until the deletion finished. Instead, it returns a JSON object with a URL for a future use.
     * The caller uses the URL for delete checks.
     * If a dataset was used by another task (Ex. projection, histogram, pca, tune and so forth), it cannot be deleted.
     */
    JsonObject deleteTransformAsync(String datasetName);

    /**
     * @return all transformations metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all datasets metadata, i.e., it does not retrieve the dataset content.
     */
    JsonObject searchAllTransforms();

    /**
     * @param datasetName is the name of the dataset
     * @return the metadata of a single transform operation
     * @summary This method is responsible for retrieving a transformation metadata.
     */
    JsonObject searchTransform(String datasetName);


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
    JsonObject searchTransformContent(String datasetName, int pageSize, int currentPage);


}
