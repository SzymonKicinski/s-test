package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Trims {
    @JsonProperty("Trims")
    private List<Trim> trimList;

    public List<Trim> getTrimList() {
        return trimList;
    }

    public void setTrimList(List<Trim> trimList) {
        this.trimList = trimList;
    }
}
