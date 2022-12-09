import React from 'react';
import axios from 'axios';
import socketIOClient from 'socket.io-client';

class EmployeeList extends React.Component {
  state = {
    employees: [],
    isLoading: false,
    error: null
  };

  

  componentDidMount() {
    this.setState({ isLoading: true });

    axios
      .get('http://localhost:8080/api')
      .then(response => {
        this.setState({ employees: response.data, isLoading: false });
      })
      .catch(error => {
        this.setState({ error, isLoading: false });
      });

    const socket = socketIOClient('http://localhost:8080/api'); // connect to the server using websockets
    socket.on('new-employee', employee => {
      // listen for the "new-employee" event and update the employees array with the new employee data
      this.setState(prevState => ({
        employees: [...prevState.employees, employee]
      }));
    });
  }

  render() {
    const { employees, isLoading, error } = this.state;

    if (error) {
      return <p>{error.message}</p>;
    }

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
        {employees.length > 0 ? (
          employees.map(employee => (
            <div key={employee.id} className="row">
              <div className="col">{employee.firstName}</div>
              <div className="col">{employee.lastName}</div>
              <div className="col">{employee.email}</div>
            </div>
          ))
        ) : (
          <p>No employees found</p>
        )}
      </div>
    );
  }
}

export default EmployeeList;