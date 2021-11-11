package by.tc.task01.server.entity;

import java.util.List;

public class StudentFactory {
    public StudentInfo getStudent(List<String> parameters){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setName(parameters.get(1));
        studentInfo.setAverageScore(parameters.get(2));
        return studentInfo;
    }
}
