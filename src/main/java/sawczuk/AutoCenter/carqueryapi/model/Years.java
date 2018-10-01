package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Years {
    @JsonProperty("Years")
    private Year year;

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
