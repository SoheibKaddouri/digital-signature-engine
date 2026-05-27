# Java Digital Signature Verification Engine

A production-ready Java implementation demonstrating the asymmetric cryptography workflow for creating and verifying digital signatures. This project mirrors standard Information Technology security protocols used to ensure data integrity, authenticity, and non-repudiation.

## 📌 Project Overview

This repository contains a clean, self-contained implementation of a cryptographic digital signature engine using Java's native `java.security` libraries. It visualizes and executes the end-to-end process of document signing and verification through asymmetric cryptography.

### How It Works (Workflow Diagram)
Below is the architectural workflow implemented by this algorithm:

![Digital Signature Flowchart](cryptography.png)

1. **Signing Phase (Left):** Data is processed through a cryptographic Hash Function (`SHA-256`) to produce a unique message digest. This hash is then encrypted using the signer's **Private Key** to generate the unique **Digital Signature**.
2. **Verification Phase (Right):** The recipient hashes the received data independently. Simultaneously, they decrypt the digital signature using the signer's corresponding **Public Key**. If both hashes are exactly equal, the signature is validated, proving the data has not been tampered with.

---

## 🛠️ Technologies Used

* **Language:** Java (JDK 8 or higher)
* **Cryptographic Framework:** `java.security` API
* **Algorithms:** * **RSA (2048-bit):** Used for asymmetric key pair generation and encryption/decryption of the hash.
  * **SHA-256:** Used as the secure message digest hash function.

---

## 🚀 How to Run It

Since this project relies completely on Java's core library package, no external dependencies or build tools (like Maven or Gradle) are strictly required.

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed.

### Execution Steps
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/SoheibKaddouri/algorithms.git](https://github.com/SoheibKaddouri/algorithms.git)
   cd algorithms/digital-signatures
