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

            <div>
                <h2>List of all employees</h2>

                <div className="employeeListContainer">
                    <div className="row header">
                        <div className="col" >First name</div>
                            <div className="col" >Last name</div>
                            <div className="col" >Email address</div>  
                            <div className="col" ></div>  
                    </div>
                    {employees.map(employee => (
                    <div className="row" key={employee.email}>

                            <div className="col" >{employee.firstName}</div>
                            <div className="col" >{employee.lastName}</div>
                            <div className="col" >{employee.email}</div>  
                            <div className="col" ><CrossIcon color="red" width="16" onClick={() => deleteEmployee(employee.email)} /></div>  

                    </div>
                ))} 
                </div>                
            <div>
        </div>
    </div>
</div>
)          
}