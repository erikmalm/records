import React, {useState, useEffect, useCallback} from "react";
import EmployeesView from "../views/employeesView";
import axios from "axios";
import EmployeeListPresenter from "./employeeListPresenter";
import EmployeeAddFormPresenter from "./employeeAddFormPresenter";

export default function EmployeesPresenter() {

        const [employees, setEmployees] = useState([]);
        const [isLoading, setIsLoading] = useState(false);
        const [error, setError] = useState(null);

        const [newEmployee, setNewEmployee] = useState({
          firstName: "",
          lastName: "",
          email: ""
        });

        function handleInputChange(e) {
          const { value, id } = e.target;

            // Update the newEmployee state using the setNewEmployee function
          setNewEmployee({
            ...newEmployee, // spread the current newEmployee state object
            [id]: value // update the property with the corresponding id with the new value
          });
          console.log(newEmployee);
        };       

        const fetchEmployees = async () => {

          setIsLoading(true)
          try {            
            const response = await axios.get("http://localhost:8080/api");
            console.log(response);
            setEmployees(response.data);
            setIsLoading(false);
          } catch (error) {
            console.error(error);
            setIsLoading(false);
            setError(true);
          }
        }
        
        const deleteEmployee = (email) => {
          axios.delete(`http://localhost:8080/api/${email}`)
          .then(res => {
            console.log(res);         
          })
          .then(fetchEmployees)
        };

        function addEmployee() {

          var config = {
            headers: {
                'Content-Type': 'application/json'
            },
           responseType: 'text'
        };

          axios.post(`http://localhost:8080/api`,
          {
            firstName: newEmployee.firstName,
            lastName: newEmployee.lastName,
            email: newEmployee.email
          },
          config
          )
          .then(res => {
            console.log(res);     
            setNewEmployee({         firstName: "",
            lastName: "",
            email: ""});
          })
          .then(fetchEmployees)
          .catch(err => {
            console.log(err)
          })
        };

        useEffect(() => {
          fetchEmployees();
        }, []);

        return (
          <div className="mainContainer">
            <EmployeeAddFormPresenter             
            newEmployee={newEmployee}
            sendInputToNewEmployee={e=>handleInputChange(e)}
            sendActionAddEmployee={() => addEmployee()}
            />       

          <EmployeeListPresenter 
          employees={employees}
          isLoading={isLoading}
          error={error}
          handleDeleteEmployee={email => deleteEmployee(email)}
          title="Employees" />
          </div>
        )  


}