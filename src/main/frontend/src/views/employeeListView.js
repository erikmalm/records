import React from "react";
import { CrossIcon } from "../icons";
import "../styles/styles.css";

const EmployeeRow = ({ firstName, lastName, email, deleteClicked }) => (
  <div className="row">
    <div className="col">{firstName}</div>
    <div className="col">{lastName}</div>
    <div className="col">{email}</div>
    <div className="col">
      <CrossIcon color="red" width="16" onClick={() => deleteClicked(email)} />
    </div>
  </div>
);

const EmployeeList = ({ employees, deleteClicked, error }) => {
  return (
    <div>
      {error ? (
        <div className="errorMessage">
          {error.response.data.localizedMessage}
        </div>
      ) : (
        ""
      )}
      <h2>List employees</h2>
      <div className="listEmployeeContainer">
        <div className="row">
          <div className="col">
            <h3>First name</h3>
          </div>
          <div className="col">
            <h3>Last name</h3>
          </div>
          <div className="col">
            <h3>Email</h3>
          </div>
          <div className="col">
            <h3>Remove</h3>
          </div>
        </div>
        {employees.length > 0 ? (
          employees.map((employee) => (
            <EmployeeRow
              key={employee.email}
              firstName={employee.firstName}
              lastName={employee.lastName}
              email={employee.email}
              deleteClicked={deleteClicked}
            />
          ))
        ) : (
          <div className="row">
            <div className="col">
              <p>No employees found</p>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default EmployeeList;
