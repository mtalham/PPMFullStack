import axios from "axios";
import jwt_decode from "jwt-decode";
import { GET_ERRORS, SET_CURRENT_USER } from "./Types";

export const createNewUser = (newUser, history) => async dispatch => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (e) {
    dispatch({
      type: GET_ERRORS,
      payload: e.response.data
    });
  }
};

//  post ==> login request
//  extract token from response
//   store the token in localStorage
//  set the token in header (for Authorization)
//  decode token on React side
//  dispatch the reducer
export const loginUser = loginReq => async dispatch => {
  try {
    const res = await axios.post("/api/users/login", loginReq);
    const { token } = res.data;
    localStorage.setItem("jwt", token);
    setJWT(token);
    dispatch({
      type: SET_CURRENT_USER,
      payload: jwt_decode(token)
    });
  } catch (e) {
    dispatch({
      type: GET_ERRORS,
      payload: e.response.data
    });
  }
};

export const logout = () => dispatch => {
  console.log('logoing out');
  localStorage.removeItem("jwt");
  setJWT(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: null
  });
};

export const setJWT = token => {
  if (token) {
    axios.defaults.headers.common["Authorization"] = token;
  } else delete axios.defaults.headers.common["Authorization"];
};
