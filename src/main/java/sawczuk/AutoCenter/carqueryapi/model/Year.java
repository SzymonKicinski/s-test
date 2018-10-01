package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Year {
    @JsonProperty("min_year")
    private Integer minYear;
    @JsonProperty("max_year")
    private Integer maxYear;

    public Integer getMinYear() {
        return minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }
}
