import "../styles/styles.css";
import { AddEmployeeIcon } from "../icons";

export default function EmployeeAddFormView({
  newEmployee,
  inputChange,
  addEmployee,
}) {
  return (
    <div className="addEmployeeContainer">
      <h2>Add employee</h2>
      <div>
        <form className="formContainer">
          <label>
            <div className="text">First name</div>
            <input
              className="item"
              type={"text"}
              value={newEmployee.firstName}
              onInput={(e) => inputChange(e)}
              id="firstName"
            />
          </label>

          <label>
            <div className="text">Last name</div>
            <input
              className="item"
              type={"text"}
              value={newEmployee.lastName}
              onInput={(e) => inputChange(e)}
              id="lastName"
            />
          </label>

          <label>
            <div className="text">Email address</div>
            <input
              className="item"
              type={"text"}
              value={newEmployee.email}
              onInput={(e) => inputChange(e)}
              id="email"
            />
          </label>
        </form>
      </div>

      <button type="button" value="Add" onClick={() => addEmployee()}>
        <AddEmployeeIcon size="24" /> Add employee
      </button>
    </div>
  );
}
