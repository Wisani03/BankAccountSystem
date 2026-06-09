# Wimab Bank - Desktop Dashboard Application

A secure, object-oriented Java Swing desktop banking application that models financial transactions across various account archetypes using robust encapsulation, inheritance, and interface structures.

---

## 🚀 How to Test and Run the Application

To test the security features and transaction processing of the **Wimab Bank Dashboard**, launch the application from the `Main.java` entry point.

When prompted for security authorization during a transaction, use the following pre-configured test accounts and PIN codes:

| Account Type | Account Holder | Account Number | Test PIN |
| :--- | :--- | :--- | :--- |
| **Savings Account** | Wisani Mabunda | `SAV-4412` | **`1234`** |
| **Current (Cheque)** | Wisani Mabunda | `CHQ-5502` | **`5678`** |
| **Short-Term Investment** | Tresina Mabunda | `FIX-9901` | **`1111`** |
| **Long-Term Investment** | Tresina Mabunda | `FIX-7704` | **`2222`** |

### 🔒 Security Behavior
* Entering the correct 4-digit PIN will authorize the financial transaction and update the balance live.
* Entering an incorrect PIN will trigger an `ACCESS DENIED` security alert pop-up and flag the violation in the live audit log window.

---

## 🛠️ Tech Stack & Concepts Demonstrated
* **Language:** Java SE
* **GUI Framework:** Java Swing & AWT layouts
* **OOP Principles:** Polymorphism, Class Inheritance, Custom Interfaces, Data Encapsulation
* **Version Control:** Git & GitHub integration