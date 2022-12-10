import React from "react";
import EmployeeListView from "../views/employeeListView";
import "../styles/styles.css";

export default function EmployeeListPresenter({
  employees,
  isLoading,
  error,
  handleDeleteEmployee,
}) {
  if (isLoading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="bottom-container">
      <EmployeeListView
        employees={employees}
        deleteClicked={(email) => handleDeleteEmployee(email)}
        error={error}
      />
    </div>
  );
}
