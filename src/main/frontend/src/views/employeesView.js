import {AddEmployeeIcon, CrossIcon} from "../icons/"
import CreateEmployeeForm from "../components/createEmployeeForm"
import '../styles/employees.css'

export default function EmployeesView({
    employees,
    deleteEmployee,
    firstName,
    setFirstName,
    lastName,
    setLastName,
    email,
    setEmail,
    addEmployee
}) {

    const createEmployeeForm = (
        <CreateEmployeeForm
            firstName={firstName}
            setFirstName={firstName => setFirstName(firstName)}
            lastName={lastName}
            setLastName={lastName => setLastName(lastName)}
            email={email}
            setEmail={email => setEmail(email)}
        />
    )
    
    return(

        <div className="employeesViewContainer">
            <div className="addEmployeeContainer">
                <h2>Add new employee</h2>
                {createEmployeeForm}
                <input type="button" value="Add employee" onClick={() => addEmployee()} />
            </div>
        </div>
    )          
}