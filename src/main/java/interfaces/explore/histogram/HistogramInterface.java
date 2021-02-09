package interfaces.explore.histogram;

import com.google.gson.JsonObject;

/**
 * @author Learning Orchestra
 * @version 1.0
 * @summary This interface is responsible to create a histogram from a dataset attribute
 */
public interface HistogramInterface {
    /**
     * @summary this method runs the histogram algorithm synchronously, this way the caller waits until the execution finishes.
     * @param datasetName represents the dataset name
     * @param histogramName represents the histogram name to be used forward
     * @param attribute represents the attribute used to plot the histogram
     * @return a JSON object with error or warning messages. In case of success, it returns the histogram URL inside the JSON object.
     */
    JsonObject runHistogramSync(String datasetName, String histogramName, String attribute);

    /**
     * @summary this method runs the histogram algorithm asynchronously, this way the caller does not wait until the execution finishes.
     * @see #await method for wait condition strategy
     * @param datasetName represents the dataset name
     * @param histogramName represents the histogram name to be used forward
     * @param attribute represents the attribute used to plot the histogram
     * @return a JSON object with error or warning messages. In case of success, it returns the histogram URL inside the JSON object.
     */
    JsonObject runHistogramAsync(String datasetName, String histogramName, String attribute);

    /**
     * @param histogramURL is the histogram URL returned by asynchronous method calls of Learning Orchestra API
     * @return a JSON object indicating if the histogram is created correctly or not
     * @summary This method is responsible to lock the caller until the histogram is created correctly into
     * Learning Orchestra
     */
    JsonObject await(String histogramURL);

    /**
     * @param histogramName represents the histogram name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the histogram. This delete operation is synchronous, so it
     * locks the caller until the deletion finished.
     */
    JsonObject deleteHistogramSync(String histogramName);

    /**
     * @param histogramName represents the histogram name
     * @return a JSON object with an error message, a warning message or a correct delete message
     * @summary This method is responsible for deleting the histogram. This delete operation is asynchronous, so it
     * does not lock the caller until the deletion finished. Instead, it returns a JSON object with a URL for a future use.
     * The caller uses the URL for delete checks.
     */
    JsonObject deleteHistogramAsync(String histogramName);


    /**
     * @return all histograms metadata stored in Learning Orchestra or an empty result
     * @summary This method retrieves all histograms metadata, i.e., it does not retrieve the histogram content.
     */
    JsonObject searchAllHistograms();


    /**
     * @param histogramName is the name of the histogram
     * @param pageSize    is the number of tuples or registers returned per page. It is an optional parameter.
     *                    If not present, the page size will be 20 tuples/registers.
     * @param currentPage is the page where the search will start. It is an optional parameter.
     *                    If not present, the search operation will start from page zero.
     * @return a page with some tuples or registers inside or an error if there is no such histogram.
     * The current page is also returned to be used in future content requests.
     * @summary This method is responsible for retrieving pages of the histogram content
     */
    JsonObject searchHistogram(String histogramName, int pageSize, int currentPage);

}
