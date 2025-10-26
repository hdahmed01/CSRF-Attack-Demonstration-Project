# CSRF Attack Demonstration Project

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://adoptium.net/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A Spring Boot application that demonstrates Cross-Site Request Forgery (CSRF) attacks and protections in a simulated banking environment. This project is designed for educational purposes to help understand how CSRF attacks work and how to prevent them.

##  Overview

This project simulates a simple banking application where:
- Users can log in and view their balance
- Users can transfer money to other accounts
- An attacker page attempts to perform unauthorized transfers

The project demonstrates both protected and vulnerable states to show the importance of CSRF protection.

##  Features

-  Modern banking interface
-  Money transfer functionality
-  Spring Security integration
-  CSRF protection (configurable)
-  Responsive design
-  Simulated attacker page

##  Technologies

- Spring Boot 3.5.7
- Spring Security
- Thymeleaf
- HTML5/CSS3
- Java 17

##  Setup

1. **Prerequisites**
   ```bash
   - Java 17 or higher
   - Maven 3.6 or higher
   ```

2. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd csrf
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8081`

##  Demo Steps

### 1. Protected Mode (Default)

1. Start the application
2. Login at `http://localhost:8081/transfer`
   - Username: `user`
   - Password: `password`
3. Note your balance (1000 DT)
4. Open `http://localhost:8081/attacker/attacker.html` in the same browser
5. Click "Claim Prize"
6.  Transfer fails (403 Forbidden) due to CSRF protection

### 2. Vulnerable Mode

1. Edit `SecurityConfig.java`:
   ```java
   .csrf(csrf -> csrf.disable())
   ```
2. Remove CSRF token from `transfer.html`
3. Restart the application
4. Login again
5. Open attacker page
6. Click "Claim Prize"
7.  Transfer succeeds - 900 DT stolen!

##  Default Accounts

- **Regular User**
  - Username: `user`
  - Password: `password`
  - Initial Balance: 1000 DT

- **Attacker Account**
  - Username: `attacker`
  - Initial Balance: 0 DT

##  Security Notes

This project intentionally demonstrates security vulnerabilities for educational purposes. In a real application, you should:

- ✅ NEVER disable CSRF protection
- ✅ Use HTTPS exclusively
- ✅ Implement proper session management
- ✅ Add rate limiting
- ✅ Implement transaction authorization
- ✅ Add 2FA for sensitive operations

##  UI Components

The application features three main pages:

1. **Transfer Page** (`/transfer`)
   - View balance
   - Make transfers
   - Professional banking interface

2. **Result Page** (`/result`)
   - Transfer confirmation
   - Updated balance
   - Success/failure animations

3. **Attacker Page** (`/attacker/attacker.html`)
   - Simulated phishing page
   - Hidden transfer form
   - Deceptive UI

## Disclaimer

This project is for educational purposes only. The vulnerabilities demonstrated should never be implemented in production systems or used maliciously.

##  License

This project is open source and available under the [MIT License](LICENSE).

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](issues).
