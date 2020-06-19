import React from "react";
import {BrowserRouter as Router, Route} from "react-router-dom";
import {Provider} from "react-redux";
import {ApolloProvider} from '@apollo/react-hooks'
import jwt_decode from "jwt-decode";

import store from "./Store";
import client from "./Client";

import Header from "./components/layout/Header";
import Landing from "./components/layout/Landing";
import SignUp from "./components/registeration/SignUp";
import Login from "./components/registeration/Login";
import PrivateRoutes from "./PrivateRoutes";
import {logout, setJWT} from "./actions/RegisterationAction";
import {SET_CURRENT_USER} from "./actions/Types";

import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {ThemeProvider} from "styled-components";
import {darkTheme, lightTheme} from "./theme/theme";
import {GlobalStyles} from "./theme/global";
import {useTheme} from "./theme/useTheme";

const token = localStorage.jwt;
if (token) {
  setJWT(token);
  const decodedToken = jwt_decode(token);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decodedToken
  });
  const currentTime = Date.now() / 1000;
  // console.log(decodedToken.exp, currentTime);
  if (decodedToken.exp < currentTime) {
    //handle Logout
    store.dispatch(logout());
    window.location.href = "/";
  }
}

const App = () => {
  const [theme, toggleTheme] = useTheme();
  return (
    <ApolloProvider client={client}>
      <Provider store={store}>
        <Router>
          <ThemeProvider theme={theme === 'light' ? lightTheme : darkTheme}>
            <GlobalStyles/>
            <div className="App">
              <Header theme={theme} toggleTheme={toggleTheme} />
              <Route exact path="/" component={Landing}/>
              <Route exact path="/register" component={SignUp}/>
              <Route exact path="/login" component={Login}/>
              <PrivateRoutes/>
            </div>
          </ThemeProvider>
        </Router>
      </Provider>
    </ApolloProvider>
  );
};

export default App;
