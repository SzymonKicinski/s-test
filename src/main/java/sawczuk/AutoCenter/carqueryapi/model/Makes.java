package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Makes {
    @JsonProperty("Makes")
    private List<Make> makeList;

    public List<Make> getMakeList() {
        return makeList;
    }

    public void setMakeList(List<Make> makeList) {
        this.makeList = makeList;
    }
}
