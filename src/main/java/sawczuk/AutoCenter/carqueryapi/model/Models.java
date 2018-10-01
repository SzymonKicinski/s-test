package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Models {
    @JsonProperty("Models")
    private List<Model> modelList;

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }
}
