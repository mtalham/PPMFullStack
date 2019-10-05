import axios from "axios";
import {
  DELETE_PROJECT_TASK,
  GET_BACKLOG,
  GET_ERRORS,
  GET_PROJECT_TASK
} from "./Types";

export const addProjectTask = (
  backlog_id,
  projectTask,
  history
) => async dispatch => {
  await axios.post(`/api/backlog/${backlog_id}`, projectTask);
  history.push(`/projectBoard/${backlog_id}`);
  dispatch({
    type: GET_ERRORS,
    payload: {}
  });
};

export const getProjectTask = (
  backlog_id,
  pt_id,
  history
) => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: GET_PROJECT_TASK,
      payload: res.data
    });
  } catch (e) {
    history.push(`/projectBoard/${backlog_id}`);
  }
};

export const updateProjectTask = (
  backlog_id,
  pt_id,
  projectTasks,
  history
) => async dispatch => {
  await axios.patch(`/api/backlog/${backlog_id}/${pt_id}`, projectTasks);
  history.push(`/projectBoard/${backlog_id}`);
  dispatch({
    type: GET_ERRORS,
    payload: {}
  });
};

export const deleteProjectTask = (backlog_id, pt_id) => async dispatch => {
  await axios.delete(`/api/backlog/${backlog_id}/${pt_id}`);
  dispatch({
    type: DELETE_PROJECT_TASK,
    payload: pt_id
  });
};

export const getBacklog = backlog_id => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data
    });
  } catch (e) {
    dispatch({
      type: GET_ERRORS,
      payload: e.response.data
    });
  }
};
