import React from 'react';
import { CrossIcon } from '../icons';

export default function EmployeeListView ({ employees, deleteClicked })

{

    return (
        <div className="employeesViewContainer">
            <h2>List employees</h2>
        {employees.length > 0 ? (
          employees.map(employee => (
            <div key={employee.email} className="row">
              <div className="col">{employee.firstName}</div>
              <div className="col">{employee.lastName}</div>
              <div className="col">{employee.email}</div>
              <div className="col" ><CrossIcon color="red" width="16" onClick={() => deleteClicked(employee.email)} /></div>  
            </div>
          ))
        ) : (
          <p>No employees found</p>
        )}
      </div>
    );

}

