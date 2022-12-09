import EmployeeAddFormView from "../views/employeeAddFormView"

export default function employeeAddFormPresenter({newEmployee,sendInputToNewEmployee,sendActionAddEmployee}) {


    return (
        <EmployeeAddFormView 
        newEmployee={newEmployee}
        inputChange={e=> sendInputToNewEmployee(e)}
        addEmployee={sendActionAddEmployee}/>
    )

}