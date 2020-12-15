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
     * @param newDataset     is boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method eliminates some attributes of a dataset. It
     * can create a new dataset or reuse the existing one.
     */
    JsonObject updateDatasetAttributesSync(String datasetName, String datasetOldName,
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
     * @param newDataset        is boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method inserts a single attributes into a dataset. It
     * can create a new dataset or reuse the existing one.
     */
    JsonObject insertDatasetAttributeSync(String datasetName, String attribute,
                                          String existingAttribute,
                                          Map<String, String> values, boolean newDataset);

    /**
     * @param datasetName   is the name of the dataset
     * @param sizeReduction is the percentage of reduction. Example 10%, 20%, etc.
     * @param newDataset    is boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method reduces the original number of tuples/registers in a specific percentage.
     * It can just randomly eliminates the tuples or it can adopt a more sophisticated strategy to reduce the dataset
     * size.
     */
    JsonObject reduceDatasetSync(String datasetName, int sizeReduction, boolean newDataset);


    /**
     * @param datasetName is the name of the dataset
     * @param sizeEnlarge is the percentage of growth. Example 10%, 20%, 110%, etc.
     * @param newDataset  is boolean responsible to inform if a new dataset will be created or not
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method enlarges the original number of tuples/registers in a specific percentage.
     * It can just randomly clones the tuples or it can adopt a more sophisticated strategy to enlarge the dataset size.
     */
    JsonObject enlargeDatasetSync(String datasetName, int sizeEnlarge, boolean newDataset);

    /**
     * @param datasetNames           represents the list of datasets to be joined
     * @param datasetName            represents the name of the new dataset
     * @param removeExistingDatasets is a boolean indicating if the existing datasets must be removed. If true one
     *                               of the datasets can be used to join the others.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method joins two or more datasets into a single one. They must have the same attributes.
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
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method joins two datasets into a single one. They can have different attributes and a map
     * associates them.
     */
    JsonObject joinDatasetsSync(String datasetNameOne, String datasetNameTwo,
                                Map<String, String> attributesAssociations,
                                String datasetName, boolean removeExistingDatasets);

    /**
     * @param datasetName    is the dataset name
     * @param attribute      is the attribute that must be updated
     * @param oldToNewValues represents a map, associating the old values of the attribute with the new values
     *                       to be updated.
     * @return a JSON object with error or warning messages. In case of success, it returns the dataset metadata.
     * @summary This method updates some values of an attribute
     */
    JsonObject updateDatasetValuesSync(String datasetName, String attribute,
                                       Map<String, String> oldToNewValues);
    //the map associates old values to new values of an existing attribute


    JsonObject insertDatasetAttributeAsync(String datasetName, String attribute,
                                           String existingAttribute, Map<String,
            String> values, boolean newDataset);

    JsonObject reduceDatasetAsync(String datasetName, int sizeReduction, boolean newDataset);

    JsonObject enlargeDatasetAsync(String datasetName, int sizeEnlarge, boolean newDataset);

    JsonObject joinDatasetsAsync(Enumeration<String> datasetNames, String datasetName,
                                 boolean removeExistingDatasets);

    JsonObject joinDatasetsAsync(String datasetNameOne, String datasetNameTwo,
                                 Map<String, String> attributesAssociations, String datasetName,
                                 boolean removeExistingDatasets);

    JsonObject updateDatasetValuesAsync(String datasetName, String attribute,
                                        Map<String, String> oldToNewValues);
    //the map associates old values to new values of an existing attribute

    JsonObject updateDatasetAttributesAsync(String datasetName, String datasetOldName,
                                            Enumeration<String> attributes,
                                            boolean newDataset);

    JsonObject await(String datasetName);
}
