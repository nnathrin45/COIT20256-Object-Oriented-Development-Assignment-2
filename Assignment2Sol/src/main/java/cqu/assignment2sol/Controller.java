/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.assignment2sol;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import javafx.event.ActionEvent;
import cqu.assignment2sol.Patient;
/**
 * FXML Controller class
 *
 * @author Natthapong Rinsakul - 12290114
 */
public class Controller implements Initializable {


    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtBP;
    @FXML
    private TextField txtCho;
    @FXML
    private CheckBox chkisSmoker;
    @FXML
    private TextArea txtDisplay;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Read file when open program
        loadDataFromFile();
    }    
    
    /**
     * Loads patient data from the text file into the system.
     *
     */
    private void loadDataFromFile() {
        File file = new File(FILE_NAME);
        
        // Check if the file exists and contains data before proceeding
        if (!file.exists() || file.length() == 0) {
            txtDisplay.setText("No existing records found or file is empty.");
            return;
        }
        
        // Use try-catch for automatic closure of the Scanner
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Skip empty lines to prevent processing errors
                if (line.trim().isEmpty()) continue;
                
                // Split data by comma
                String[] data = line.split(","); 
                
                // Declare the variable outside the 'if' statement so it can be used when adding to the list.
                String fullName = "";
                String phone = "";
                int age = 0, bp = 0;
                double cho = 0;
                boolean isSmoker = false;
                
                /**
                 * Format 1: Raw data (7 columns)
                 * [First Name, Last Name, Phone, Age, BP, Cholesterol, Smoker]
                 */
                if (data.length == 7) {
                    fullName = data[0].trim() + " " + data[1].trim(); // Combine name and last name
                    phone = data[2];
                    age = Integer.parseInt(data[3]);
                    bp = Integer.parseInt(data[4]);
                    cho = Double.parseDouble(data[5]);
                    isSmoker = Boolean.parseBoolean(data[6]); 
                
                /**
                 * Format 2: Processed data from Save function.
                 * Incase if the data has 6 columns.
                 * [Full Name, Phone, Age, BP, Cholesterol, Smoker]
                **/
                } else if (data.length == 6){ 
                    fullName = data[0].trim();
                    phone = data[1].trim();
                    age = Integer.parseInt(data[2].trim());
                    bp = Integer.parseInt(data[3].trim());
                    cho = Double.parseDouble(data[4].trim());
                    isSmoker = Boolean.parseBoolean(data[5].trim());  
                } else {
                    continue; //Ignore rows that do not match expected formats
                }
                
                // Instantiate and add the new Patient object to the observable list
                patientList.add(new Patient(fullName, phone, age, bp, cho, isSmoker));
            }
            txtDisplay.setText("Success: Patient records loaded from file.");
        } catch (Exception e) {
            // Log error message to the UI if parsing or file access fails
            txtDisplay.setText("Error loading file: " + e.getMessage());
        }
    }
    
    /**
     * Processes the registration of a new patient.
     */
    @FXML
    private void handleAdd(ActionEvent event) {
        try {
            // Extract data from input fields
            String fName = txtFirstName.getText().trim();
            String lName = txtLastName.getText().trim();
            String phone = txtPhone.getText().trim();
            String ageStr = txtAge.getText().trim();
            String bpStr = txtBP.getText().trim();
            String choStr = txtCho.getText().trim();
            boolean isSmoker = chkisSmoker.isSelected();
            
            // Validate empty fields
            if (fName.isEmpty() || lName.isEmpty() || phone.isEmpty() || ageStr.isEmpty() || bpStr.isEmpty() || choStr.isEmpty()) {
                txtDisplay.setText("Error: All fields must be filled.");
                return;
            }
            
            // Data Validation
            // Combine First Name and Last Name for the Patient constructor
            String fullName = fName + " " + lName;
            // Validate phone number (10 digits starting with 0)
            if (!phone.matches("0\\d{9}")) {
                txtDisplay.setText("Error: Phone must be 10 digits and start with 0.");
                return;
            }
        
            // Parse numeric strings
            int age = Integer.parseInt(ageStr);
            int bp = Integer.parseInt(bpStr);
            double cho = Double.parseDouble(choStr);
            
            // Validate age range (0-116)
            if (age < 0 || age > 116) {
                txtDisplay.setText("Error: Age must be between 0 and 116.");
                return;
            }
            
            // Validate BP range (40-300)
            if (bp < 40 || bp > 300) {
                txtDisplay.setText("Error: Blood Pressure must be between 40 and 300.");
                return;
            }
            
            // Validate Cholesterol range (1.0-15.0)
            if (cho < 1.0 || cho > 15.0) {
                txtDisplay.setText("Error: Cholesterol must be between 1.0 and 15.0.");
                return;
            }
            
            // Create Patient object and add to list
            Patient newPatient = new Patient(fullName, phone, age, bp, cho, isSmoker);
            patientList.add(newPatient);
              
            // Update UI and provide feedback
            handleClear(null);
            txtDisplay.setText("Patient added successfully:\n" + newPatient.toString());
            // Setting font to Monospaced for the layout
            txtDisplay.setStyle("-fx-font-family: 'Monospace';");
             
        } catch (NumberFormatException e) {
            // Catch case where non-numeric strings are entered in numeric fields
            txtDisplay.setText("Error: Age, BP, and Cholesterol must be numeric value.");
        } catch (Exception e) {
            // Catch any other unexpected errors
            txtDisplay.setText("Error: " + e.getMessage());
        }
    }
    
    /**
     *  Searches for a specific patient record by using phone number.
     */
    @FXML
    private void handleSearch(ActionEvent event) {
        // Get the search term (phone number) from the text field
        String searchPhone = txtPhone.getText().trim();
        
        // Validate if the search fields is empty
        if (searchPhone.isEmpty()) {
            txtDisplay.setText("Please enter a phone number to search.");
            return;
        }
        
        // Search through the patient list
        Patient foundPatient = null;
        for (Patient p : patientList) {
            // Compare phone numbers
            if (p.getPhone().equals(searchPhone)) {   
                foundPatient = p;
                break; // Stop searching once found
            }
        }
        
        // Display layout
        if (foundPatient != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("--- Patient Found ---\n");
            
            // Header layout
            String header = String.format("%-12s | %-12s | %-12s | %-5s | %-5s | %-12s | %-8s | %-7s | %-10s\n",
                    "First Name", "Last Name", "Phone", "Age", "BP", "Cholesterol", "Smoker", "Score", "Level");
            sb.append(header);
            sb.append("------------------------------------------------------------------------------------------------------------------\n");
            
            // Split name and last name for display
            String fullName = foundPatient.getFullName();
            String firstName = "";
            String lastName = "";

            if (fullName.contains(" ")) {
                String[] nameParts = fullName.split(" ", 2);
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = fullName;
                lastName = "-";
            }
            
            // Display patient that be found
            String row = String.format("%-12s | %-12s | %-12s | %-5d | %-5d | %-12.1f | %-8b | %-7d | %-10s\n",
                    firstName,
                    lastName,
                    foundPatient.getPhone(),
                    foundPatient.getAge(),
                    foundPatient.getSystolicBP(),
                    foundPatient.getCholesterol(),
                    foundPatient.isIsSmoker(),
                    foundPatient.calculateRiskScore(),
                    foundPatient.getRiskLevel());
            sb.append(row);
            
            txtDisplay.setText(sb.toString());
            // Setting font to Monospaced for the layout
            txtDisplay.setStyle("-fx-font-family: 'Monospace';");
        } else {
            txtDisplay.setText("No patient found with phone number: " + searchPhone);
        }
    }
    
    /**
     *  Organizes the patient records by Age in ascending order.
     */
    @FXML
    private void handleSort(ActionEvent event) {
        // Check for empty file
        if (patientList.isEmpty()) {
            txtDisplay.setText("No records to sort.");
            return;
        }
        
        // Sort the list by age using a Comparator
        patientList.sort(Comparator.comparingInt(Patient::getAge));
        
        // Create a header and update display to show the sorted list
        StringBuilder sb = new StringBuilder();
        sb.append("--- Patients Sorted by Age (Ascending) ---\n");
        
        // Header layout
        String header = String.format("%-12s | %-12s | %-12s | %-5s | %-5s | %-12s | %-8s | %-7s | %-10s\n",
                "First Name", "Last Name", "Phone", "Age", "BP", "Cholesterol", "Smoker", "Score", "Level");
        sb.append(header);
        sb.append("------------------------------------------------------------------------------------------------------------------\n");
        
        // For Loop input data to format
        for (Patient p : patientList) {
            // Split name for display same as ViewAll format
            String fullName = p.getFullName();
            String firstName = "";
            String lastName = "";
            
            if (fullName.contains(" ")) {
                String[] nameParts = fullName.split(" ", 2);
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = fullName;
                lastName = "-";
            }
            
            String row = String.format("%-12s | %-12s | %-12s | %-5d | %-5d | %-12.1f | %-8b | %-7d | %-10s\n",
                firstName,
                lastName,
                p.getPhone(),
                p.getAge(),
                p.getSystolicBP(),
                p.getCholesterol(),
                p.isIsSmoker(),
                p.calculateRiskScore(),
                p.getRiskLevel());
            sb.append(row);
        }
        
        // Display in console
        txtDisplay.setText(sb.toString());
        // Setting font to Monospaced for the layout
        txtDisplay.setStyle("-fx-font-family: 'Monospace';");
    }
    
    /**
     * Renders a comprehensive list of all patient records in the system memory.
     * Uses a StringBuilder for efficient string concatenation and formatted output.
     */
    @FXML
    private void handleViewAll(ActionEvent event) {
        // Check if the list is empty
        if (patientList.isEmpty()) {
            txtDisplay.setText("No patient records available to display.");
            return;
        }
        
        // Build a string containing all patient records
        StringBuilder sb = new StringBuilder ("--- All Patient Records ---\n");
        
        // Header Layout
        String header = String.format("%-12s | %-12s | %-12s | %-5s | %-5s | %-12s | %-8s | %-7s | %-10s\n",
                "First Name", "Last Name", "Phone", "Age", "BP", "Cholesterol", "Smoker", "Score", "Level");
        sb.append(header);
        sb.append("------------------------------------------------------------------------------------------------------------------\n");
        
        // Counter to show number of patients
        for (Patient p : patientList) {
            
            // Split full name into first name and last name
            String[] nameParts = p.getFullName().split(" ");
            String firstName = nameParts.length > 0 ? nameParts[0] : "";
            String lastName = nameParts.length > 1 ? nameParts[1] : "";
            
            // Use String.format for space
            String row = String.format("%-12s | %-12s | %-12s | %-5d | %-5d | %-12.1f | %-8b | %-7d | %-10s\n",
                    firstName,
                    lastName,
                    p.getPhone(),
                    p.getAge(),
                    p.getSystolicBP(),
                    p.getCholesterol(),
                    p.isIsSmoker(),
                    p.calculateRiskScore(),
                    p.getRiskLevel());
            sb.append(row);
        }
        
        // Display the result in the TextArea
        txtDisplay.setText(sb.toString());
        // Setting font to Monospaced for the layout
        txtDisplay.setStyle("-fx-font-family: 'Monospace';");
        
    }
    
    /**
     * Handles the Save button action by exporting the current  patient list
     * to a persistent text file.
     */
    @FXML
    private void handleSave(ActionEvent event) {
        // Prevent file operations if there is no data to process
       if (patientList.isEmpty()) {
           txtDisplay.setText("No data to save.");
           return;
       }
       
       /**
        * Use try-catch to ensure PrintWriter is closed automatically.
        * This writes the data in a CSV format.
        */
       try (PrintWriter writer = new PrintWriter(new File(FILE_NAME))) {
           for (Patient p : patientList) {
               // Write data in a comma-separated format for easy loading next time
               // Adjust the output format to match loadDataFromFile
               writer.println(p.getFullName() + "," +
                               p.getPhone() + "," +
                               p.getAge() + "," +
                               p.getSystolicBP() + "," +
                               p.getCholesterol() + "," +
                               p.isIsSmoker());
           }
           txtDisplay.setText("Data successfully saved to " + FILE_NAME);
       } catch (Exception e) {
           txtDisplay.setText("Error saving File: " + e.getMessage());
       }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        txtFirstName.clear();
        txtLastName.clear();
        txtPhone.clear();
        txtAge.clear();
        txtBP.clear();
        txtCho.clear();
        chkisSmoker.setSelected(false);
        txtDisplay.clear();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
    
    private ArrayList<Patient> patientList = new ArrayList<>();
    private final String FILE_NAME = "PatientRecord.txt";   
}
