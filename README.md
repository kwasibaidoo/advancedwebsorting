# Advanced Web Sorting
## Project Objectives:
- Design and implement a RESTful API following HATEOAS principles using servlets or Spring Framework.
- Configure and deploy the application on an Apache Tomcat web server, ensuring proper handling of HTTP protocols.
- Develop a Spring-based web application utilizing modules, dependency injection, autowiring, and application context.
- Implement and integrate various sorting algorithms (Heap Sort, Quick Sort, Merge Sort, Radix Sort, and Bucket Sort) for data processing within the application.


## Features:
1. **HTTP Protocols & Apache Tomcat Web Server:**
- Configure and deploy the web application on Apache Tomcat.
- Ensure proper handling of HTTP requests and responses.
2. **API Design:**
- Design a RESTful API adhering to HATEOAS principles.
- Implement API endpoints for CRUD operations on a sample dataset.
3. **Spring Framework:**
- Utilize Spring modules for building the application.
- Implement dependency injection, autowiring, and inversion of control.
- Configure Spring beans and application context.
Develop a sample Spring application showcasing these features.
4. **Sorting Algorithms:**
- Implement Heap Sort, Quick Sort, Merge Sort, Radix Sort, and Bucket Sort algorithms.
- Integrate these sorting algorithms within the application for data manipulation.
- Provide an interface to select and execute different sorting algorithms on a sample dataset.

---
### Base URL: localhost:8080/advancedwebsorting
---

## Dataset Management API

### 1. **Add to Dataset**
   - **Endpoint**: `POST /api/addtodataset`
   - **Description**: Adds a list of numbers to the dataset.
   - **Request Body**: A comma-separated string of integers.
     - Example: `"3, 5, 8, 1, 2"`
   - **Responses**:
     - `200 OK`: Successfully added the numbers to the dataset.
     - `400 Bad Request`: Invalid number format (if any non-integer value is encountered).
     - **Response Body**:
       ```json
       {
         "message": "Number added successfully",
         "success": true,
         "data": ""
       }
       ```

---

### 2. **Get Dataset**
   - **Endpoint**: `GET /api/getdataset`
   - **Description**: Retrieves the current dataset of numbers.
   - **Responses**:
     - `200 OK`: Successfully retrieved the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Numbers retrieved successfully",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

### 3. **Clear Dataset**
   - **Endpoint**: `POST /api/clear`
   - **Description**: Clears all numbers from the dataset.
   - **Responses**:
     - `200 OK`: Successfully cleared the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset cleared",
         "success": true,
         "data": ""
       }
       ```

---

### 4. **Update Dataset**
   - **Endpoint**: `PUT /api/update/{id}`
   - **Description**: Updates a number in the dataset at the specified position (`id`).
   - **Request Body**: A single integer value.
     - Example: `5` (replace the number at the given position with `5`)
   - **Path Variables**:
     - `id`: The position (index) of the number to be updated in the dataset.
   - **Responses**:
     - `200 OK`: Successfully updated the number at the specified position.
     - `400 Bad Request`: Operation failed (e.g., invalid index).
     - **Response Body**:
       ```json
       {
         "message": "Dataset updated",
         "success": true,
         "data": ""
       }
       ```

---

### 5. **Delete from Dataset**
   - **Endpoint**: `POST /api/delete/{position}`
   - **Description**: Deletes the number at the specified position (`position`) in the dataset.
   - **Path Variables**:
     - `position`: The index of the number to be removed.
   - **Responses**:
     - `200 OK`: Successfully removed the number from the dataset.
     - `400 Bad Request`: Operation failed (e.g., invalid position).
     - **Response Body**:
       ```json
       {
         "message": "Number removed from dataset",
         "success": true,
         "data": ""
       }
       ```

---

## Sorting API

### 1. **Bucket Sort**
   - **Endpoint**: `POST /api/sort/bucket`
   - **Description**: Sorts the dataset using the bucket sort algorithm.
   - **Responses**:
     - `200 OK`: Successfully sorted the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset sorted using bucket sort",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

### 2. **Heap Sort**
   - **Endpoint**: `POST /api/sort/heap`
   - **Description**: Sorts the dataset using the heap sort algorithm.
   - **Responses**:
     - `200 OK`: Successfully sorted the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset sorted using heap sort",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

### 3. **Quick Sort**
   - **Endpoint**: `POST /api/sort/quick`
   - **Description**: Sorts the dataset using the quick sort algorithm.
   - **Responses**:
     - `200 OK`: Successfully sorted the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset sorted using quick sort",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

### 4. **Merge Sort**
   - **Endpoint**: `POST /api/sort/merge`
   - **Description**: Sorts the dataset using the merge sort algorithm.
   - **Responses**:
     - `200 OK`: Successfully sorted the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset sorted using merge sort",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

### 5. **Radix Sort**
   - **Endpoint**: `POST /api/sort/radix`
   - **Description**: Sorts the dataset using the radix sort algorithm.
   - **Responses**:
     - `200 OK`: Successfully sorted the dataset.
     - **Response Body**:
       ```json
       {
         "message": "Dataset sorted using radix sort",
         "success": true,
         "data": [1, 2, 3, 5, 8]
       }
       ```

---

## Common Response Structure

All responses are wrapped in the following format:
```json
{
  "message": "message describing the operation",
  "success": true/false,
  "data": the result of the operation (could be a dataset or an error message)
}