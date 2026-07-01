# Subscription Renewal Management System

A JSP/Servlet web application for managing subscription renewal records — add new renewals, search a specific record, and view all records — built with plain JDBC and Oracle Database.

## Features
- Add a new renewal record with auto-generated Renewal ID
- View a specific renewal by Subscription ID + Renewal Date
- View all renewal records in a table
- Input validation (subscriber name, subscription ID, amount)
- Duplicate prevention (same subscription ID + renewal date)

## Auto-Generated Renewal ID
Format: `yyyyMMdd` + first 2 letters of Subscription ID (uppercase) + 2-digit Oracle sequence value
Example: renewal on `2026-02-14` for subscription `SB1002` → `20260214SB01`

*(Sequence `RENEWAL_SEQ` cycles 10–99, no cache, no cycle)*

## Project Structure
com.wipro.renewal.bean      → RenewalBean (POJO)
com.wipro.renewal.dao       → RenewalDAO (JDBC data access)
com.wipro.renewal.service   → Administrator (business logic)
com.wipro.renewal.servlets  → MainServlet (controller)
com.wipro.renewal.util      → DBUtil, InvalidInputException

## Tech Stack
- Java, JDBC
- Jakarta Servlet API (`@WebServlet`)
- JSP
- Oracle Database (XE)
- Apache Tomcat

## Database Setup

```sql
CREATE TABLE RENEWAL_TB(
    RENEWALID       VARCHAR2(12) PRIMARY KEY,
    SUBSCRIBERNAME  VARCHAR2(50) NOT NULL,
    SUBSCRIPTIONID  VARCHAR2(20) NOT NULL,
    RENEWALDATE     DATE NOT NULL,
    AMOUNT          NUMBER NOT NULL,
    STATUS          VARCHAR2(20) NOT NULL,
    REMARKS         VARCHAR2(100)
);

CREATE SEQUENCE RENEWAL_SEQ
START WITH 10
INCREMENT BY 1
MINVALUE 10
MAXVALUE 99
NOCACHE
NOCYCLE;
```

## Setup & Run
1. Create the Oracle user `subscribe` (password `subscribe123`) and run the table/sequence scripts above
2. Update `DBUtil.java` if your DB URL/credentials differ
3. Build and deploy the WAR to Tomcat
4. Open in browser:
http://localhost:8080/SubscribeProject/menu.html

## Pages
| Page | Purpose |
|------|---------|
| `menu.html` | Landing page with navigation links |
| `addRenewal.jsp` | Form to add a new renewal record |
| `viewRenewal.jsp` | Form to search a renewal by Subscription ID + Date |
| `displayRenewal.jsp` | Shows a single renewal record's details |
| `viewAllRenewals.jsp` | Trigger to fetch all records |
| `displayAllRenewals.jsp` | Table listing all renewal records |
| `success.html` / `error.html` | Result pages |

## Author
Shruthika R — 717824Y150
