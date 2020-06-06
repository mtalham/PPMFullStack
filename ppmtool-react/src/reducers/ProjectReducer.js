import {GET_PROJECTS, GET_PROJECT, DELETE_PROJECT, GET_PROJECT_ACCESS} from "../actions/Types";
import jwt_decode from "jwt-decode";

const initialState = {
  projects: [],
  project: {},
  hasAccess: false,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload
      };
    case GET_PROJECT:
      return {
        ...state,
        project: action.payload
      };
    case DELETE_PROJECT:
      return {
        ...state,
        projects: state.projects.filter(
          project => project.projectIdentifier !== action.payload
        )
      };
    case GET_PROJECT_ACCESS:
      return {
        ...state,
        hasAccess: verifyAccess(action.payload.projectLeader)
      }
    default:
      return state;
  }
}

const verifyAccess = (projectLeader) => {
  const token = localStorage.jwt;
  const decodedToken = token && jwt_decode(token);
  return !!(decodedToken && decodedToken.username === projectLeader)
}
