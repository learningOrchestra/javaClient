package interfaces.transform.projection;

import java.util.Enumeration;
import java.util.Map;

import com.google.gson.JsonObject;

/**
 * @author Learning Orchestra
 * @version 1.0
 * @summary This interface is responsible for projection operations, i.e.,
 * dataset attribute insertion or removal, dataset reduction or enlargement,
 * join two or more datasets, values update and many more.
 */
public interface ProjectionInterface {

    /**
     * @param datasetName    represents the dataset name
     * @param datasetOldName represents the existing dataset name
     * @param attributes     represents the set of attributes to be considered. The remaining attributes are deleted.
     * @param newDataset     is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method eliminates some attributes of a dataset. It
     * can create a new dataset or not. The removal operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject removeDatasetAttributesSync(String datasetName, String datasetOldName,
                                           Enumeration<String> attributes, boolean newDataset);

    /**
     * @param datasetName       represents the dataset name
     * @param attribute         represents the new attribute
     * @param existingAttribute represents an existing attribute of the dataset
     * @param values            represents the set of values to be used. In this case, the existing attribute values
     *                          are mapped into new values (key-value pair), this way the new attribute can receive
     *                          multiple new values. For instance, the
     *                          existing attribute sex with values male and female can be mapped into the new
     *                          values A and B of
     *                          the new attribute XYZ.
     * @param newDataset        is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method inserts a single attributes into a dataset. It
     * can create a new dataset or not. The attribute insertion operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject insertDatasetAttributeSync(String datasetName, String attribute,
                                          String existingAttribute,
                                          Map<String, String> values, boolean newDataset);

    /**
     * @param datasetName   is the name of the dataset
     * @param sizeReduction is the percentage of reduction. Example 10%, 20%, etc.
     * @param newDataset    is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method reduces the original number of tuples/registers in a specific percentage.
     * It can just randomly eliminates the tuples or it can adopt a more sophisticated strategy to reduce the dataset
     * size. The dataset reduction operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject reduceDatasetSync(String datasetName, int sizeReduction, boolean newDataset);


    /**
     * @param datasetName is the name of the dataset
     * @param sizeEnlarge is the percentage of growth. Example 10%, 20%, 110%, etc.
     * @param newDataset  is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method enlarges the original number of tuples/registers in a specific percentage.
     * It can just randomly clones the tuples or it can adopt a more sophisticated strategy to enlarge the dataset size.
     * The enlargement operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject enlargeDatasetSync(String datasetName, int sizeEnlarge, boolean newDataset);

    /**
     * @param datasetNames           represents the list of datasets to be joined
     * @param datasetName            represents the name of the new dataset
     * @param removeExistingDatasets is a boolean indicating if the existing datasets must be removed. If true one
     *                               of the datasets can be used to join the others.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method joins two or more datasets into a single one. They must have the same attributes. The join operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject joinDatasetsSync(Enumeration<String> datasetNames, String datasetName,
                                boolean removeExistingDatasets);

    /**
     * @param datasetNameOne         represents the first dataset to be joined
     * @param datasetNameTwo         represents the second dataset to be joined
     * @param attributesAssociations represents a map of both datasets attributes. It is used to associate an attribute
     *                               from one dataset to the other.
     * @param datasetName            represents the name of the new dataset
     * @param removeExistingDatasets is a boolean indicating if the existing datasets must be removed. If true one
     *                               of the datasets can be used to join the other.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method joins two datasets into a single one. They can have different attributes and a map
     * associates them. The join operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject joinDatasetsSync(String datasetNameOne, String datasetNameTwo,
                                Map<String, String> attributesAssociations,
                                String datasetName, boolean removeExistingDatasets);

    /**
     * @param datasetName    is the dataset name
     * @param attribute      is the attribute that must be updated
     * @param oldToNewValues represents a map, associating the old values of the attribute with the new values
     *                       to be updated.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method updates some values of an attribute. The update operation is synchronous, this way the caller waits until it finishes.
     */
    JsonObject updateDatasetValuesSync(String datasetName, String attribute,
                                       Map<String, String> oldToNewValues);

    /**
     * @param datasetName    represents the dataset name
     * @param datasetOldName represents the existing dataset name
     * @param attributes     represents the set of attributes to be considered. The remaining attributes are deleted.
     * @param newDataset     is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method eliminates some attributes of a dataset. It
     * can create a new dataset or not. The removal operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject removeDatasetAttributesAsync(String datasetName, String datasetOldName,
                                           Enumeration<String> attributes, boolean newDataset);

    /**
     * @param datasetName       represents the dataset name
     * @param attribute         represents the new attribute
     * @param existingAttribute represents an existing attribute of the dataset
     * @param values            represents the set of values to be used. In this case, the existing attribute values
     *                          are mapped into new values (key-value pair), this way the new attribute can receive
     *                          multiple new values. For instance, the
     *                          existing attribute sex with values male and female can be mapped into the new
     *                          values A and B of
     *                          the new attribute XYZ.
     * @param newDataset        is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method inserts a single attributes into a dataset. It
     * can create a new dataset or not. The attribute insertion operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject insertDatasetAttributeAsync(String datasetName, String attribute,
                                          String existingAttribute,
                                          Map<String, String> values, boolean newDataset);

    /**
     * @param datasetName   is the name of the dataset
     * @param sizeReduction is the percentage of reduction. Example 10%, 20%, etc.
     * @param newDataset    is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method reduces the original number of tuples/registers in a specific percentage.
     * It can just randomly eliminates the tuples or it can adopt a more sophisticated strategy to reduce the dataset
     * size. The dataset reduction operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject reduceDatasetAsync(String datasetName, int sizeReduction, boolean newDataset);


    /**
     * @param datasetName is the name of the dataset
     * @param sizeEnlarge is the percentage of growth. Example 10%, 20%, 110%, etc.
     * @param newDataset  is a boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method enlarges the original number of tuples/registers in a specific percentage.
     * It can just randomly clones the tuples or it can adopt a more sophisticated strategy to enlarge the dataset size.
     * The enlargement operation is asynchronous, this way the caller waits until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject enlargeDatasetAsync(String datasetName, int sizeEnlarge, boolean newDataset);

    /**
     * @param datasetNames           represents the list of datasets to be joined
     * @param datasetName            represents the name of the new dataset
     * @param removeExistingDatasets is a boolean indicating if the existing datasets must be removed. If true one
     *                               of the datasets can be used to join the others.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method joins two or more datasets into a single one. They must have the same attributes. The join operation is asynchronous,
     * this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject joinDatasetsAsync(Enumeration<String> datasetNames, String datasetName,
                                boolean removeExistingDatasets);

    /**
     * @param datasetNameOne         represents the first dataset to be joined
     * @param datasetNameTwo         represents the second dataset to be joined
     * @param attributesAssociations represents a map of both datasets attributes. It is used to associate an attribute
     *                               from one dataset to the other.
     * @param datasetName            represents the name of the new dataset
     * @param removeExistingDatasets is a boolean indicating if the existing datasets must be removed. If true one
     *                               of the datasets can be used to join the other.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method joins two datasets into a single one. They can have different attributes and a map
     * associates them. The join operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject joinDatasetsAsync(String datasetNameOne, String datasetNameTwo,
                                Map<String, String> attributesAssociations,
                                String datasetName, boolean removeExistingDatasets);

    /**
     * @param datasetName    is the dataset name
     * @param attribute      is the attribute that must be updated
     * @param oldToNewValues represents a map, associating the old values of the attribute with the new values
     *                       to be updated.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset URL inside the JSON object.
     * @summary This method updates some values of an attribute. The update operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await to understand the wait condition.
     */
    JsonObject updateDatasetValuesAsync(String datasetName, String attribute,
                                       Map<String, String> oldToNewValues);


    /**
     * @param datasetURL is the dataset URL returned by asynchronous method calls of Learning Orchestra API
     * @return a JSON object indicating if the dataset is projected correctly or not
     * @summary This method is responsible to lock the caller until a dataset is projected correctly into
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
    JsonObject deleteProjectionSync(String datasetName);

    /**
     * @param datasetName represents the dataset name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the dataset. This delete operation is asynchronous, so it
     * does not lock the caller until the deletion finished. Instead, it returns a JSON object with a URL for a future use.
     * The caller uses the URL for delete checks.
     * If a dataset was used by another task (Ex. projection, histogram, pca, tune and so forth), it cannot be deleted.
     */
    JsonObject deleteProjectionAsync(String datasetName);

    /**
     * @return all datasets metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all datasets metadata, i.e., it does not retrieve the dataset content.
     */
    JsonObject searchAllProjections();


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
    JsonObject searchProjectionContent(String datasetName, int pageSize, int currentPage);

    /**
     * @param datasetName is the name of the dataset
     * @return a page with the first 20 tuples or registers inside or an error if there is no such dataset.
     * @summary This method is responsible for retrieving the dataset content, precisely the first 20 tuples
     */
    JsonObject searchProjectionContent(String datasetName);
}
