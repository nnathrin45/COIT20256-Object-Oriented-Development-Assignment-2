# CQ Cardiovascular Disease Management System

An Object-Oriented JavaFX Application developed for **COIT20256 Object-Oriented Development (Assignment 2)** at CQUniversity. The system serves as a GUI-based Patient Management System that performs Cardiovascular Risk Assessments, calculates risk scores based on health metrics, and provides persistence via file I/O operations.

---

## 📌 Features

- **Graphical User Interface (GUI):** Built with JavaFX featuring clean `TitledPane` layouts (`Enter Patient Record`, `Display Record`, and `Manipulations`).
- **Patient Risk Assessment:** Calculates cardiovascular risk scores and assigns risk levels (`Low`, `Medium`, `High`) based on age, blood pressure, cholesterol levels, and smoking status.
- **Data Persistence:** Reads from and exports patient records to `PatientRecord.txt`.
- **Core Manipulations:**
  - **Add:** Registers new patient records with automatic risk score and risk level calculation.
  - **Search:** Search patient records by 10-digit mobile phone number.
  - **Sort:** Sorts records in ascending order (by age / name).
  - **View All:** Displays all registered patient records in formatted tabular console text.
  - **Save:** Writes all current patient data back to `PatientRecord.txt`.
  - **Clear:** Resets all input fields and display console.
  - **Exit:** Gracefully closes the application.
- **Robust Data Validation & Error Handling:**
  - Prevents empty field submissions.
  - Ensures numeric inputs for `Age`, `SystolicBP`, and `Cholesterol`.
  - Validates range boundaries:
    - **Age:** $0 \le \text{Age} \le 116$
    - **Systolic Blood Pressure:** $40 \le \text{BP} \le 300$
    - **Cholesterol:** $1.0 \le \text{Cholesterol} \le 15.0$
    - **Phone Number:** Exactly 10 digits starting with `0`.

---

## 🏗️ Project Structure

```text
Assignment2Sol/
├── pom.xml                               # Maven project configuration
├── PatientRecord.txt                     # Data file for patient records
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java          # JavaFX Module Configuration
│       │   └── cqu/
│       │       └── assignment2sol/
│       │           ├── App.java          # Main Application Entry Point
│       │           ├── Controller.java   # GUI Controller handling actions & logic
│       │           └── Patient.java      # Patient Data Model & Risk Logic
│       └── resources/
│           └── cqu/
│               └── assignment2sol/
│                   ├── primary.fxml      # Main JavaFX FXML Interface
│                   └── secondary.fxml    # Auxiliary Scene FXML
```

---

## 🛠️ Requirements & System Prerequisites

- **Java Development Kit (JDK):** JDK 11 or higher (JDK 17+ recommended)
- **JavaFX Framework:** Integrated via Maven dependencies
- **Build Tool:** Apache Maven 3.x+
- **IDE (Optional):** NetBeans, IntelliJ IDEA, or Eclipse with Maven & JavaFX support

---

## 🚀 How to Build & Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/CQ-Cardiovascular-Disease-Management-System.git
cd CQ-Cardiovascular-Disease-Management-System/Assignment2Sol
```

### 2. Build the Project
Using Apache Maven:
```bash
mvn clean compile
```

### 3. Run the Application
Run using the JavaFX Maven Plugin:
```bash
mvn javafx:run
```

---

## 📝 Usage Guide & Application Screenshots

| Feature | Description |
| :--- | :--- |
| **Data Loading** | Upon launch, the system automatically reads records from `PatientRecord.txt`. |
| **View All** | Displays all patient records formatted into clean tabular columns with calculated risk levels. |
| **Add Patient** | Fills in patient details, checks smoking status, validates inputs, and appends to memory. |
| **Search Patient** | Queries patient records by entering their 10-digit mobile phone number. |
| **Sorting** | Re-orders and displays the list sorted by age or name. |
| **Save File** | Overwrites `PatientRecord.txt` with updated patient details. |

---

## 👤 Author

- **Student Name:** Natthapong Rinsakul
- **Student ID:** 12290114
- **Course:** COIT20256 Object-Oriented Development
- **Institution:** CQUniversity Australia
