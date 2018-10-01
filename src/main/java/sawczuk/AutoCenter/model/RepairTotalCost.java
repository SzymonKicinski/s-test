package sawczuk.AutoCenter.model;

import sawczuk.AutoCenter.util.NumberUtils;

public class RepairTotalCost {
    Integer exploitationTypeValue;
    Double totalCost;

    public Integer getExploitationTypeValue() {
        return exploitationTypeValue;
    }

    public void setExploitationTypeValue(Integer exploitationTypeValue) {
        this.exploitationTypeValue = exploitationTypeValue;
    }

    public Double getTotalCost() {
        return NumberUtils.truncateDouble(totalCost);
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = NumberUtils.truncateDouble(totalCost);
    }


    public RepairTotalCost() {
    }

    public RepairTotalCost(Integer exploitationTypeValue, Double totalCost) {
        this.exploitationTypeValue = exploitationTypeValue;
        this.totalCost = NumberUtils.truncateDouble(totalCost);
    }
}
