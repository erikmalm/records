import React, { useState, useEffect } from "react";
import axios from "axios";
import EmployeeListPresenter from "./employeeListPresenter";
import EmployeeAddFormPresenter from "./employeeAddFormPresenter";
import LogoView from "../views/logoView";

import "../styles/styles.css";

export default function EmployeesPresenter() {
  // Initialize state variables
  const [employees, setEmployees] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [newEmployee, setNewEmployee] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });

  // Fetch employees when the component is mounted
  useEffect(() => {
    fetchEmployees();
  }, []);

  // Helper function to fetch the list of employees
  const fetchEmployees = async () => {
    setIsLoading(true);
    try {
      // Make a GET request to the API to get the list of employees
      const response = await axios.get("http://localhost:8080/api");
      // Update the employees state variable with the response data
      setEmployees(response.data);
    } catch (err) {
      // If there was an error, update the error state variable
      setError(err);
    } finally {
      // Indicate that the request is finished, regardless of whether it was successful or not
      setIsLoading(false);
      setError(null);
    }
  };

  // Helper function to delete an employee
  const deleteEmployee = async (email) => {
    try {
      // Make a DELETE request to the API to delete the employee with the specified email address
      await axios.delete(`http://localhost:8080/api/${email}`);
      // Fetch the updated list of employees
      fetchEmployees();
    } catch (err) {
      // If there was an error, update the error state variable
      setError(err);
    }
  };

  // Helper function to add a new employee
  const addEmployee = async () => {
    try {
      // Make a POST request to the API to add a new employee
      await axios.post("http://localhost:8080/api", {
        firstName: newEmployee.firstName,
        lastName: newEmployee.lastName,
        email: newEmployee.email,
      });
      // Clear the newEmployee state variable
      setNewEmployee({
        firstName: "",
        lastName: "",
        email: "",
      });
      // Fetch the updated list of employees
      fetchEmployees();
    } catch (err) {
      // If there was an error, update the error state variable
      setError(err);
    }
  };

  // Helper function to handle changes to the input fields in the add employee form
  const handleInputChange = (e) => {
    const { value, id } = e.target;
    // Update the newEmployee state variable with the new value
    setNewEmployee({
      ...newEmployee, // spread the current newEmployee state object
      [id]: value, // update the property with the corresponding id with the new value
    });
  };

  return (
    <div>
      <EmployeeAddFormPresenter
        newEmployee={newEmployee}
        sendInputToNewEmployee={handleInputChange}
        sendActionAddEmployee={addEmployee}
        title="Add employee"
      />

      <EmployeeListPresenter
        employees={employees}
        isLoading={isLoading}
        error={error}
        handleDeleteEmployee={deleteEmployee}
        title="Employees"
      />

      <LogoView />
    </div>
  );
}
