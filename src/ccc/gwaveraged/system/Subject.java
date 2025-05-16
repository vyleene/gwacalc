package ccc.gwaveraged.system;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private int units;
    private double grade;
    private double gpa;
    private double prelimGrade;
    private double midtermGrade;
    private double finalGrade;
    private double prelimWeight;
    private double midtermWeight;
    private double finalWeight;
    private List<List<Double>> gradingSystem;

    public static List<String> subjectNames = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();
    
    public Subject(String name, int units, double prelimWeight, double midtermWeight, double finalWeight) {
        this.name = name;
        this.units = units;
        this.prelimWeight = prelimWeight;
        this.midtermWeight = midtermWeight;
        this.finalWeight = finalWeight;
    }

    public Subject(String name, int units, double grade) {
        this.name = name;
        this.units = units;
        this.grade = grade;
        this.prelimWeight = 0.0;
        this.midtermWeight = 0.0;
        this.finalWeight = 0.0;
    }
    
    public void setGrade(double prelim, double midterm, double finals) {
        this.prelimGrade = prelim;
        this.midtermGrade = midterm;
        this.finalGrade = finals;
    }

    public void setGradingSystem(List<List<Double>> gradingSystem) {
        this.gradingSystem = gradingSystem;
    }
    
    public double calculateGrade() {
        return (prelimGrade * prelimWeight) + 
               (midtermGrade * midtermWeight) + 
               (finalGrade * finalWeight);
    }
    
    public double calculateGradePoint() {
        if (prelimWeight == 0.0 && midtermWeight == 0.0 && finalWeight == 0.0) {
            return grade;
        } else {
            return percentageToPoint(calculateGrade());
        }
    }

    public double percentageToPoint(double percentage) {
        if (this.gradingSystem.isEmpty()) return -1.00;
        for (List<Double> percentages : this.gradingSystem) {
            if (!percentages.isEmpty() && (percentage >= (double) percentages.get(0) && percentage <= (double) percentages.get(1))) {
                return percentages.get(2);
            }
        }
        return -2.00;
    }
    
    // Getters
    public String getSubjectName() { return name; }
    public int getUnits() { return units; }
    public double getGPA() { return gpa; }
    public List<List<Double>> getGradingSystem() { return gradingSystem; }
}
