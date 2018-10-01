package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Make {
    @JsonProperty("make_id")
    private String id;
    @JsonProperty("make_display")
    private String name;
    @JsonProperty("make_country")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
