package by.tc.task01.server.entity;

public class ClientInfo {
    private String name;
    private String allowance;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }
    public String getAllowance(){
        return allowance;
    }
}
