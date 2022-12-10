import EmployeeAddFormView from "../views/employeeAddFormView";
import "../styles/styles.css";

export default function employeeAddFormPresenter({
  newEmployee,
  sendInputToNewEmployee,
  sendActionAddEmployee,
}) {
  return (
    <div className="topContainer">
      <EmployeeAddFormView
        newEmployee={newEmployee}
        inputChange={(e) => sendInputToNewEmployee(e)}
        addEmployee={sendActionAddEmployee}
      />
    </div>
  );
}
