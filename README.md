# Restaurant Management System (Zeta)

## Overview
The Restaurant Management System is a console-based Java application designed to streamline the operations of a restaurant. It provides role-based access for Managers, Customers, Waiters, Chefs, and Receptionists, allowing them to perform tasks relevant to their responsibilities.

## Features
- **Manager**: 
    - Manage staff (add/update staff details, change working status).
    - Manage menu (add new items).
    - View sales and order reports.
    - Generate bills and receive payments.
- **Customer**:
    - View menu.
    - Check table availability.
    - Book tables.
- **Chef**:
    - View current orders.
    - Update order status (Cooking -> Ready).
- **Waiter**:
    - Take orders from tables.
    - Update order status (Served).
- **Receptionist**:
    - Book tables for walk-ins.
    - Generate bills.
    - Receive payments.

## Architecture
- **Language**: Java 17
- **Persistence**: JSON file storage (`src/main/java/data/*.json`).
- **Build Tool**: Maven

## Prerequisites
- Java JDK 17 or higher
- Maven 3.6+

## Concurrency
To simulate multiple users interacting simultaneously (e.g., a Manager adding an item while a Customer views the menu):
1. Open multiple terminal windows.
2. Run the application in each window.
3. Log in as different users.
Updates are persisted to the shared JSON files instantly.
