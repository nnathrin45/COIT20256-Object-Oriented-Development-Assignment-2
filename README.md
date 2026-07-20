# CQ Cardiovascular Disease Management System

A JavaFX Graphical User Interface (GUI) application developed for managing patient records and performing **Cardiovascular Risk Assessments**. This project evaluates patient health data by calculating risk scores based on key health metrics such as age, blood pressure, cholesterol levels, and smoking history.

Developed as part of the **COIT20256 Object-Oriented Development** unit at CQUniversity.

---

## 👤 Author Information

* **Student Name:** Natthapong Rinsakul
* **Student ID:** 12290114
* **Unit Code:** COIT20256
* **Unit Title:** Object-Oriented Development (Assignment 2)

---

## 🚀 System Features & Functionalities

* **JavaFX Graphical User Interface (GUI):** Clean, structured 3-pane layout allowing convenient data entry, data visualization, and manipulation.
* **Persistent File Storage:** Reads existing patient records on startup and saves updated records back to `PatientRecord.txt`.
* **Patient Management Operations:**
  * **Add:** Register new patients with automatic health score calculation and risk level assessment.
  * **Search:** Quick search for specific patient records by 10-digit mobile phone number.
  * **Sort:** Sort patient records in ascending order based on Age (or by Name).
  * **ViewAll:** Format and display all loaded patient records in a structured tabular view.
  * **Save:** Export all active records back into `PatientRecord.txt`.
  * **Clear:** Reset all input text fields and clear the display console.
  * **Exit:** Safely exit the application.
* **Robust Input Validation & Error Handling:**
  * Ensures mandatory fields are not left blank (`Error: All fields must be filled.`).
  * Enforces numeric format checks (`Error: Age, BP, and Cholesterol must be numeric value.`).
  * Enforces field boundaries and formatting constraints:
    * **Phone:** Exactly 10 digits starting with `0`.
    * **Age:** Integer between `0` and `116`.
    * **Systolic BP:** Numeric value between `40` and `300`.
    * **Cholesterol:** Numeric value between `1.0` and `15.0`.
    * **isSmoker:** Boolean flag toggled via CheckBox.

---

## 🛠️ Tech Stack & Prerequisites

* **Language:** Java 17+ (or Java 11+)
* **GUI Framework:** JavaFX (FXML supported)
* **Build System:** Apache Maven / NetBeans Project
* **Data Storage:** Plain text file (`PatientRecord.txt`)

---

## 📂 Project Structure

```text
Assignment2Sol/
├── pom.xml                                  # Maven dependencies and configuration
├── PatientRecord.txt                       # File for storing persistent patient data
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── module-info.java            # Java module configuration
    │   │   └── cqu/
    │   │       └── assignment2sol/
    │   │           ├── App.java             # Main application entry point
    │   │           ├── Controller.java      # JavaFX Controller handling events & validation
    │   │           └── Patient.java         # Patient data model & risk calculation logic
    │   └── resources/
    │       └── cqu/
    │           └── assignment2sol/
    │               ├── primary.fxml         # Main GUI layout definition
    │               └── secondary.fxml       # Secondary scene layout
    └── test/                                # Unit tests
```

---

## 💻 How to Run the Application

### Option 1: Run via Apache Maven Command Line

1. Clone or download this repository.
2. Open a terminal and navigate to the project root directory (`Assignment2Sol`).
3. Run the application using the Maven JavaFX plugin:
   ```bash
   mvn clean compile javafx:run
   ```

### Option 2: Run using Apache NetBeans / Eclipse / IntelliJ IDEA

1. Open your IDE and select **Open Project**.
2. Select the `Assignment2Sol` folder (Maven project).
3. Ensure JDK 17+ (or JDK 11 with JavaFX SDK) is configured.
4. Right-click the project root and select **Run** (or run `App.java`).

---

## 📊 Cardiovascular Risk Calculation Logic

The system evaluates each patient's parameters to compute an overall risk score and classify risk levels:

| Parameter | Criteria / Thresholds |
| :--- | :--- |
| **Age** | Higher age ranges increase risk score points |
| **Systolic BP** | Higher blood pressure values contribute higher risk points |
| **Cholesterol** | Elevated cholesterol levels increase score points |
| **Smoking Status** | Active smoking (`isSmoker = true`) adds significant risk points |

**Risk Level Categories:**
* **Low Risk:** Low overall cumulative score
* **Medium Risk:** Moderate overall score
* **High Risk:** Elevated overall score requiring clinical monitoring

---

## 🖥️ Application Interface Screenshots

### 1. Main Dashboard & Data Loading
![Initial Load](https://via.placeholder.com/800x500?text=Initial+Data+Load+Screen)  
*Figure 1: Program startup successfully loading records from `PatientRecord.txt`.*

### 2. View All Patient Records
![View All Records](https://via.placeholder.com/800x500?text=View+All+Records)  
*Figure 2: Formatted tabular display of all existing patient records in the text area.*

### 3. Add New Patient Record
![Add Patient](https://via.placeholder.com/800x500?text=Add+New+Patient)  
*Figure 3: Adding a new record and calculating the cardiovascular risk score.*

### 4. Input Validation & Error Handling
![Validation Error](https://via.placeholder.com/800x500?text=Input+Validation+Error)  
*Figure 4: Real-time validation preventing invalid values (e.g., out-of-range age or invalid phone number).*

### 5. Sorting Records
![Sorted Records](https://via.placeholder.com/800x500?text=Sorted+Records)  
*Figure 5: Sorting records by Age in ascending order.*

---

## 📜 License

This project was developed for educational purposes under the **COIT20256 Object-Oriented Development** unit at **Central Queensland University (CQU)**.
