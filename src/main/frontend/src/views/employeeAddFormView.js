import '../styles/employees.css'

export default function EmployeeAddFormView({
    newEmployee,
    inputChange,
    addEmployee}) {

    return (
        <div className="addEmployeeContainer">
            <h2>Add employee</h2>
        <div className="formContainer">
            <div className="row header">
                <div className="col">First name</div>
                <div className="col">Last name</div>
                <div className="col">Email address</div>
            </div>
            <form>
                <div className="row">
                    <div className="col">
                        <input 
                        type={"text"}
                        value={newEmployee.firstName}
                        onInput={e => inputChange(e)}
                        id="firstName"/>
                    </div>

                    <div className="col">
                        <input
                            type={"text"}
                            value={newEmployee.lastName}
                            onInput={e => inputChange(e)}
                            id="lastName"/>
                    </div>

                    <div className="col">
                        <input
                            type={"text"}
                            value={newEmployee.email}
                            onInput={e => inputChange(e)}
                            id="email"/>
                    </div>
                    
                </div>
                
            </form>

        </div>
        <input type="button" value="Add employee" onClick={() => addEmployee()} />
        </div>
    )
}