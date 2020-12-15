package implementations.dataset;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable, Comparable<Result> {

    private static final long serialVersionUID = 1386844168165359337L;
    private String datasetName;
    private List<String> fields;
    private String finished;
    private String timeCreated;
    private String type;
    private String url;

    public Result() {

    }

    public String getDatasetName() {
        return datasetName;
    }


    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }


    public List<String> getFields() {
        return fields;
    }


    public void setFields(List<String> fields) {
        this.fields = fields;
    }


    public String getFinished() {
        return finished;
    }


    public void setFinished(String finished) {
        this.finished = finished;
    }


    public String getTimeCreated() {
        return timeCreated;
    }


    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int compareTo(Result o) {
        if (url.hashCode() > o.getUrl().hashCode()) return 1;
        else if (url.hashCode() < o.getUrl().hashCode()) return -1;

        //otherwise
        return 0;
    }


}
