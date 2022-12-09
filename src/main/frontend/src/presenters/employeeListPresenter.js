// EmployeeList.js
import React from 'react';
import EmployeeListView from '../views/employeeListView';

export default function EmployeeListPresenter ({ employees, isLoading, error, handleDeleteEmployee }) {
  if (error) {
    return <p>{error.message}</p>;
  }

  if (isLoading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      {employees.length > 0 ? (
        <EmployeeListView 
        employees={employees}
        deleteClicked={email => handleDeleteEmployee(email)} />
      ) : (
        <p>No employees found</p>
      )}
    </div>
  );
};