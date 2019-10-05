import { combineReducers } from "redux";
import { reducer as formReducer } from "redux-form";
import ErrorReducer from "./ErrorReducer";
import ProjectReducer from "./ProjectReducer";
import BacklogReducer from "./BacklogReducer";
import RegisterationReducer from "./RegisterationReducer";

export default combineReducers({
  errors: ErrorReducer,
  project: ProjectReducer,
  backlog: BacklogReducer,
  loggedInUser: RegisterationReducer,
  form: formReducer
});
