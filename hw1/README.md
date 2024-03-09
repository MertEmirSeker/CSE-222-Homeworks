# Customer Tracking System 

## Grade Received
This project was awarded a score of 90 out of 100.

## Overview
This project implements a basic customer tracking system for managing customers, orders, and operators within a retail context. It supports Corporate and Retail customer types and allows for managing diverse customer management strategies. Operators can manage multiple customers, and customers can place multiple orders.

## Features
- **Customer Management:** Manages retail and corporate customers, with corporate customers having additional attributes like a company name.
- **Order Management:** Tracks customer orders, including details like product name, quantity, total price, and status.
- **Operator Assignment:** Assigns customers to operators for management, supporting basic load balancing.
- **Console Interface:** Provides a text-based interface for user interactions, facilitating testing and demonstration through the command line.

## Structure
The project comprises several Java classes, each fulfilling specific functionalities:

- **`Person`:** Base class for entities representing people, storing common attributes.
- **`Customer`:** Inherits from `Person`, base class for customers, holding order and operator information.
  - **`RetailCustomer`:** Represents individual consumers.
  - **`CorporateCustomer`:** Represents businesses or corporations.
- **`Operator`:** Inherits from `Person`, represents an operator managing customers and their orders.
- **`Order`:** Represents a customer order with product details, quantity, price, and status.
- **`Main`:** Application entry point, handling data input, object initialization, and user interaction.

## Data Input Format
The system expects a `content.txt` file with semicolon-separated values. Each line should represent an entity (operator, retail customer, corporate customer, or order) in a specific format:

- Operator: `operator;Name;Surname;Address;Phone;ID;Wage`
- Retail Customer: `retail_customer;Name;Surname;Address;Phone;Customer ID;Operator ID`
- Corporate Customer: `corporate_customer;Name;Surname;Address;Phone;Customer ID;Operator ID;Company Name`
- Order: `order;Product Name;Count;Total Price;Status;Customer ID`

Ensure the file exists and adheres to this format to avoid runtime issues.

## License
Open-sourced under the MIT License. See the LICENSE file for details.
