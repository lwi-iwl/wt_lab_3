package by.tc.task01.server.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentInfo implements Info {
    private String name;
    private String averageScore;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + ", AverageScore: " + getAverageScore();
    }

    public List<String> getParameters(){
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(getName());
        parameters.add(getAverageScore());
        return parameters;
    }
}
