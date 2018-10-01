package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {
    @JsonProperty("model_name")
    private String name;
    @JsonProperty("model_make_id")
    private String makeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }
}
