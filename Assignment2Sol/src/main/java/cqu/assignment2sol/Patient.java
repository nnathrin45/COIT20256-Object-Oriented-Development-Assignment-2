/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.assignment2sol;

/**
 *
 * @author Natthapong Rinsakul - 12290114
 */
public class Patient {
    
    // Private attributes to ensure data encapsulation
    private String fullName;
    private String phone;
    private int age;
    private int systolicBP;
    private double cholesterol;
    private boolean isSmoker;
    
    // Constructor to initialize a new Patient record
    public Patient(String fullName, String phone, int age, int systolic, 
            double cholesterol, boolean isSmoker) {
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.systolicBP = systolic;
        this.cholesterol = cholesterol;
        this.isSmoker = isSmoker;
        
    }
    
    // --- Getter and Setter ---

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the systolicBP
     */
    public int getSystolicBP() {
        return systolicBP;
    }

    /**
     * @param systolicBP the systolicBP to set
     */
    public void setSystolicBP(int systolicBP) {
        this.systolicBP = systolicBP;
    }

    /**
     * @return the cholesterol
     */
    public double getCholesterol() {
        return cholesterol;
    }

    /**
     * @param cholesterol the cholesterol to set
     */
    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * @return the isSmoker
     */
    public boolean isIsSmoker() {
        return isSmoker;
    }

    /**
     * @param isSmoker the isSmoker to set
     */
    public void setIsSmoker(boolean isSmoker) {
        this.isSmoker = isSmoker;
    }
    
    // Calculate the cardiovascular risk score based on medical criteria
    // return total risk score as an integer
    public int calculateRiskScore() {
        int score = 0;
        
        // Age
        if (age < 40) {
            score += 0; 
        } else if (age < 50) {
            score += 5;
        } else if (age < 60) {
            score += 10;
        } else {
            score += 15;
        }
        
        // Scoring based on Systolic Blood Pressure
        if (systolicBP < 120) {
            score += 0;
        } else if (systolicBP < 140) {
            score += 2;
        } else {
            score += 5;
        }
        
        // Additional points if the patient is a smoker
        if (isSmoker) {
            score += 3;
        }
        
        return score;
    }
    
    // Categorizes the risk level based on the calculated score
    public String getRiskLevel() {
        int score = calculateRiskScore();
        
        if (score < 10) {
            return "Low risk";
        } else if (score < 20) {
            return "Medium risk";
        } else {
            return "High risk";
        }
    }
    
    // Returns a formatted string representation of the patient record
    @Override
    public String toString() {
        return "Name: " + fullName +
                " | Phone: " + phone +
                " | Age: " + age +
                " | BP: " + systolicBP +
                " | Cholesterol: " + cholesterol +
                " | Smoker: " + isSmoker +
                " | Score: " + calculateRiskScore() +
                " | Level: " + getRiskLevel();
    }
    
}
