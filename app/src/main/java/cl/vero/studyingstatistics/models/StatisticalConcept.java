package cl.vero.studyingstatistics.models;

import com.orm.SugarRecord;

public class StatisticalConcept extends SugarRecord{

    String name, description;
    int grade;

    public StatisticalConcept(String name, String description, int grade) {
        this.name = name;
        this.description = description;
        this.grade = grade;
    }

    public StatisticalConcept() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
