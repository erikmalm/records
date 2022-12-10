import EmployeesPresenter from "./presenters/employeesPresenter";
import './styles/styles.css'
import background from "./img/skyscraper.jpg";

function App() {
  return (

      <div className="mainContainer">
       <EmployeesPresenter />
      </div>

  );
}

export default App;

// style={{ backgroundImage: `url(${background})` }}