import { AddEmployeeIcon } from "../icons";
import React, {useEffect} from "react";
import '../styles/employees.css'

export default function CreateEmployeeForm({
    firstName, 
    setFirstName,
    lastName,
    setLastName,
    email,
    setEmail,
}) {  



    return (
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
                        value={firstName}
                        onInput={e => setFirstName(e.target.value)}
                        id="firstName"/>
                    </div>

                    <div className="col">
                        <input
                            type={"text"}
                            value={lastName}
                            onInput={e => setLastName(e.target.value)}
                            id="lastName"/>
                    </div>

                    <div className="col">
                        <input
                            type={"text"}
                            value={email}
                            onInput={e => setEmail(e.target.value)}
                            id="email"/>
                    </div>
                </div>
            </form>
        </div>
    )
}