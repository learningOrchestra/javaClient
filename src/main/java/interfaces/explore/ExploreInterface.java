package interfaces.explore;

import com.google.gson.JsonObject;

import java.util.Enumeration;

/**
 * @author Learning Orchestra
 * @version 1.0
 * @summary This interface is responsible for scikit-learn or tensorflow explorations, i.e.,
 * it runs any exploration algorithm of such tools transparently. An example of an explore operation
 * is a clustering algorithm or a PCA algorithm or a t-SNE algorithm. A plot is returned for a better
 * visualization of the explore methods.
 */
public interface ExploreInterface {

    /**
     * @summary This method runs any scikit-learn or tensorflow explore method. The run operation is synchronous, this way the caller waits until it finishes.
     * @param toolName represents the tool name, precisely scikit-learn or tensorflow
     * @param exploreName represents the name of the explored dataset
     * @param description represents a description for the explore operation
     * @param toolPackage represents the tool explore package
     * @param toolClass represents the class of the used package
     * @param constructorParameters represents the constructors parameters, i.e., the arguments used to instantiate a class object
     * @param methodName represents the method of the class, which will be called
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the plot URL inside the JSON object.
     */
    public JsonObject runExploreSync(String toolName, String exploreName, String description,
                                              String toolPackage, String toolClass, Enumeration<String> constructorParameters,
                                              String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method runs any scikit-learn or tensorflow explore method. The run operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await method for wait condition detail
     * @param toolName represents the tool name, precisely scikit-learn or tensorflow
     * @param exploreName represents the name of the explored dataset
     * @param description represents a description for the explore operation
     * @param toolPackage represents the tool explore package
     * @param toolClass represents the class of the used package
     * @param constructorParameters represents the constructors parameters, i.e., the arguments used to instantiate a class object
     * @param methodName represents the method of the class, which will be called
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the plot URL inside the JSON object.
     */
    public JsonObject runExploreAsync(String toolName, String exploreName, String description,
                                               String toolPackage, String toolClass, Enumeration<String> constructorParameters,
                                               String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method updates previous run operation, i.e., it re-executes an explore method. The update operation is synchronous, this way the caller waits until it finishes.
     * @param exploreName represents the name of the explored dataset
     * @param methodName represents the method of the class, which will be called
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the plot URL inside the JSON object.
     */
    public JsonObject updateExploreSync(String exploreName, String methodName, Enumeration<String> methodParameters);


    /**
     * @summary This method updates previous run operation, i.e., it re-executes an explore method. The update operation is asynchronous, this way the caller does not wait until it finishes.
     * @see #await method for wait condition detail
     * @param exploreName represents the name of the explored dataset
     * @param methodName represents the method of the class, which will be called
     * @param methodParameters represents the method parameters
     * @return a JSON object with error or warning messages. In case of success, it returns the plot URL inside the JSON object.
     */
    public JsonObject updateExploreAsync(String exploreName, String methodName, Enumeration<String> methodParameters);

    /**
     * @param plotURL is the plot URL returned by asynchronous method calls of Learning Orchestra API
     * @return a JSON object indicating if the dataset is explored correctly or not
     * @summary This method is responsible to lock the caller until a dataset is explored correctly into
     * Learning Orchestra
     */
    JsonObject await(String plotURL);

    /**
     * @param exploreName represents the explored plot name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the explore plot.
     */
    JsonObject deleteExplore(String exploreName);


    /**
     * @return all explores metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all plots metadata, i.e., it retrieves information about all explore operations performed
     */
    JsonObject searchAllExplores();

    /**
     * @param exploreName is the name of the plot
     * @return the metadata of a single explore operation
     * @summary This method is responsible for retrieving an explore metadata.
     */
    JsonObject searchExplore(String exploreName);

    /**
     * @param exploreName is the name of the plot
     * @return the plot URL into a JSON object
     * @summary This method is responsible for retrieving an explore plot URL.
     */
    JsonObject searchExplorePlot(String exploreName);

}
