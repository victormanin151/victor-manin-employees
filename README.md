# 📊 Employee Pair Analyzer — Spring MVC Application

This project is a **Spring MVC web application** that identifies the **pair of employees who have worked together for the longest total time** across all shared projects.  
The application accepts a CSV upload, processes employee/project date ranges, and displays the result in a clean HTML view using **Thymeleaf**.

---

## 🚀 Features

- Upload a CSV file containing employee/project participation records  
- Choose between multiple **date formats** before parsing  
- Automatically calculate:
  - All employee pairs per project  
  - Overlapping working days  
  - Total combined days per employee pair across all projects
- Display the **longest collaborating employee pair**
- Clean and simple interface using Thymeleaf
- Graceful error handling (invalid dates, empty files, parse errors)

---

## 🧠 Understanding of the Assignment & My Thought Process

The goal of the assignment was to take a CSV file with employee–project records, figure out which two employees worked together the longest across all shared projects, and then show the result.  

I approached it by breaking the problem into a few clear steps:

- Sketched my whole logic redid it a few times and started making the app.
- My early iterations were brute-force and i tried to optimise the logic to make it work faster.
- Core logic is that there a few projects with many pairs(individual people) working on them.
- It can definitely be improved for many projects with few people (backwards logic to mine)
- It for sure can be improved to work both ways by reading and maybe counting number of projects/numbers of employees.
- Using the faster approach for the given csv file (different cases).


- Read and parse the CSV file  
- Group employees by each project they worked on  
- For every project, check all possible employee pairs  
- Calculate how many days their work periods overlapped  
- Add up the total days if the same pair worked together on multiple projects  
- Finally, return the pair with the highest total shared days  

Once I was confident my console version worked, I moved on to turning it into a web application.
Big part of the testing and troubleshooting was done in the console version.

---

## 🛠️ Design Decisions & Why I Made Them

### **1. Moving from Console to Spring MVC**
I chose to rebuild the app with Spring MVC because it makes the solution more realistic and user-friendly.  
It also allowed me to show that I understand:

- Controllers  
- Services  
- Dependency Injection  
- File upload handling  

Plus, it gives a good foundation if the app ever needs to grow (API, database, etc.).

### **2. Reusing the Core Logic**
The logic I wrote in the console version for calculating pairs and overlaps was already working well, so I didn’t rewrite it.  
I reused 95% of the code.
 
This keeps things clean and avoids repeating code (DRY principle).

### **3. Adding a Service Layer (`EmployeeService`)**
I moved CSV reading and business logic into a service because it:

- Keeps the controller clean  
- Keeps all processing logic in one place  
- Makes the app easier to test and maintain  
- Follows Spring’s recommended structure  

### **4. Using Thymeleaf for the UI**
I wanted a simple UI without overcomplicating the frontend.  
Thymeleaf was perfect for this:

- One page for uploading the CSV  
- One page for showing the results  

No JavaScript needed, and it integrates nicely with Spring.

### **5. Supporting Multiple Date Formats**
(Bonus feature)
Different CSV files may use different date formats, so I added a dropdown to let the user choose which format their file uses:

- `yyyy-MM-dd`  
- `MM-dd-yyyy`  
- `dd-MM-yyyy`  

This makes the app more flexible and prevents invalid parsing.

### **6. Error Handling**
I made sure the app doesn’t crash if the CSV has issues like:

- Wrong date format  
- Missing fields  
- Empty lines  

Instead, the user gets a clear message on the upload page.

### **7. No Database Needed**
The assignment is focused on processing the data directly from the CSV, so I didn’t add a database.  
It keeps the project simpler and aligns with the goal.

---

## 💡 Summary of My Approach

My main focus was:

- Understanding the assignment clearly  
- Splitting the logic into small, clean components  
- Keeping the backend simple but well structured  
- Making the UI straightforward and easy to use  
- Handling errors so the app behaves reliably  

Overall, I tried to build the project the way I would if someone gave it to me in a real job scenario.

---

## 📁 CSV Format

Your uploaded CSV file must follow this format:

Example:

(EmpID, ProjectID, DateFrom, DateTo)

143,10,2022-01-01,2023-03-01
218,10,2022-02-01,2023-04-01
333,10,2022-05-01,2023-06-01
143,20,2022-01-15,NULL
218,20,2022-01-20,2023-02-10
444,30,2022-03-01,2023-05-01
555,30,2022-03-15,2023-04-15


`NULL` in `DateTo` will be interpreted as **today**.

---

## 📅 Supported Date Formats

You can select the date format before uploading:

- `yyyy-MM-dd`
- `MM-dd-yyyy`
- `dd-MM-yyyy`

---

## 🏗️ Project Structure

src/main/java/com.example.employees
│
├── controller
│ └── UploadController.java
| └── ViewController.java
│
├── model
│ ├── RecordEntry.java
│ ├── Pairs.java
│
├── service
│ └── EmployeeService.java
| └── ProjectService.java
│
├── util
| └── CSVReader.java (old function not used)
|
└── VictorManinEmployeesApplication.java

---

## 🖥️ User Flow

### 1️⃣ Upload Page  
User selects:
- CSV file  
- Date format  

Then submits the form.

### 2️⃣ Backend Processing  
Spring receives the file, parses it, calculates all overlapping days, and determines the longest-working employee pair.

### 3️⃣ Result Page  
Displays:

- 👥 The employee pair  
- 📌 Their total combined working days across all projects  

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot (Web, MVC, File Upload)**
- **Thymeleaf Template Engine**
- **Maven**

---

## ▶️ Running the Project

1. Clone the repository:

2. git clone Link: https://github.com/victormanin151/victor-manin-employees.git

3. Open in browser:  http://localhost:8080/
   
Upload your CSV and view results.

---

## ⚠️ Error Handling

The app automatically detects:

- Invalid date formats  
- Empty columns  
- Empty file  
- Incorrect number of CSV fields  

The user is redirected back to the upload page with an error message.

---

## 📌 Future Improvements

- Display a full table of all pairs and days worked in a grid format
- Store history of uploaded results  
- Database integration (MySQL/PostgreSQL)  
- CSV download of computed results  
- Visualize overlaps with Power BI dashboards

---

## 🧑‍💻 Author

**Victor Manin**  
A Spring and Java developer focusing on practical backend projects.



