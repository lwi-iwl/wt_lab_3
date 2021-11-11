package by.tc.task01.server.entity;

import java.util.ArrayList;
import java.util.List;

public class ClientInfo implements Info{
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

    @Override
    public String toString(){
        return "Name: " + getName() + ", Allowance: " + getAllowance();
    }

    public List<String> getParameters(){
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(getName());
        parameters.add(getAllowance());
        return parameters;
    }
}
