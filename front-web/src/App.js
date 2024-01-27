import { HashRouter } from 'react-router-dom';
import createRouter from './router/createRouter';
import store from './utils/toolkit';
import { Provider } from 'react-redux';
import './App.css';
function App() {
  return (
    <div className="App">
      <Provider store={store}>
        <HashRouter>
          {/* <Route path='/login' element={<Login></Login> } /> */}
          {
            createRouter()
          }
        </HashRouter>
      </Provider>

    </div>
  );
}

export default App;
