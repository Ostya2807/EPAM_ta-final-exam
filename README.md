# SauceDemo Automated UI Tests

## 🧪 Project Description

This project contains automated UI tests for the login functionality of [SauceDemo](https://www.saucedemo.com/) using:

- **Selenium WebDriver**
- **JUnit** (Test Runner)
- **Maven** (Build tool)
- **Java 17**
- **XPath** (Locators)
- **Logging** (via SLF4J)
- **Chrome & Edge** browsers
- **Test parallelization** and **DataProvider-based parameterization**

---

## 🔍 Use Cases

### ✅ UC-1: Login with Empty Credentials

1. Enter any credentials into **Username** and **Password** fields.
2. Clear both inputs.
3. Click the **Login** button.
4. Validate error message: `"Username is required"`.

### ✅ UC-2: Login with Username Only

1. Enter any **Username**.
2. Enter a **Password**.
3. Clear the **Password** input.
4. Click the **Login** button.
5. Validate error message: `"Password is required"`.

### ✅ UC-3: Login with Valid Credentials

1. Enter a valid **Username** (from "Accepted usernames" section).
2. Enter **Password** as `secret_sauce`.
3. Click the **Login** button.
4. Validate that the **page title is "Swag Labs"**.

---

## ⚙️ Features & Structure

- ✅ **Page Object Model (POM)**
- ✅ **Parameterized tests** using DataProvider
- ✅ **Parallel execution** via Maven Surefire plugin
- ✅ **Configuration via `.properties`** files
- ✅ **Logging** with SLF4J
- ✅ [Optional] Design patterns: Singleton, Builder, Decorator
- ✅ [Optional] BDD support (e.g., Cucumber – not included by default)

---

## 🧰 Technologies Used

| Tool         | Purpose                  |
|--------------|---------------------------|
| Java 17      | Programming language     |
| Selenium     | UI automation            |
| JUnit        | Test execution framework |
| Maven        | Project management       |
| XPath        | Element location         |
| SLF4J        | Logging                  |
| Chrome/Edge  | Target browsers          |

---

## 🚀 How to Run

```bash
# Clean and run tests on Chrome
mvn clean test -Dbrowser=chrome

# Run on Edge
mvn clean test -Dbrowser=edge
