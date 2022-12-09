import React, {useState, useEffect, useCallback} from "react";
import EmployeesView from "../views/employeesView";
import axios from "axios";
import EmployeeList from "./employeeList";

export default function EmployeesPresenter() {

        const [employees, setEmployees] = useState([]);

        const [firstNameState, setFirstName] = useState([]);

        const [lastNameState, setLastName] = useState([]);

        const [emailState, setEmail] = useState([]);



        const inputFirstName = (name) => {
          console.log(name)
          setFirstName(name);
        }

        const inputLastName = (name) => {
          console.log(name)
          setLastName(name);
        }

        const inputEmail = (address) => {
          console.log(address)
          setEmail(address);
        }


        const fetchEmployees = async () => {
          try {
            const response = await axios.get("http://localhost:8080/api");
            console.log(response);
            setEmployees(response.data);
          } catch (error) {
            console.error(error);
          }
        }
        

        const deleteEmployee = (email) => {
          axios.delete(`http://localhost:8080/api/${email}`)
          .then(res => {
            console.log(res);         
          })
          .then(fetchEmployees())
        };

        const addEmployee = () => {

          console.log(firstNameState);

          var config = {
            headers: {
                'Content-Type': 'application/json'
            },
           responseType: 'text'
        };

          axios.post(`http://localhost:8080/api`,
          {
            firstName: firstNameState,
            lastName: lastNameState,
            email: emailState
          },
          config
          )
          .then(inputFirstName(""))
          .then(inputLastName(""))
          .then(inputEmail(""))
          .then(res => {
            console.log(res);         
          })
          .then(fetchEmployees())
          .catch(err => {
            console.log(err)
          })
        };

        useEffect(() => {
          fetchEmployees();
        }, []);

        return (
          <div>
          <EmployeesView 
            employees={employees}    
            deleteEmployee={email => deleteEmployee(email)}
            addEmployee={() => addEmployee()}
            firstName={firstNameState}
            setFirstName={name => inputFirstName(name)}
            lastName={lastNameState}
            setLastName={name => inputLastName(name)}
            email={emailState}
            setEmail={address => inputEmail(address)}
          />

          <EmployeeList title="Employees" />
          </div>
        )  


}