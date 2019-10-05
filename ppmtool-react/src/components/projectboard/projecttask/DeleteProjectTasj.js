import React from "react";
import { connect } from "react-redux";
import { deleteProjectTask } from "../../../actions/backlogActions";

const DeleteProjectTask = ({ deleteProjectTask, backlog_id, pt_id }) => {
  const handleClick = () => {
    if (window.confirm("Are you sure? This will delete the project task.")) {
      deleteProjectTask(backlog_id, pt_id);
    }
  };

  return (
    <button onClick={handleClick} className="btn btn-danger ml-4">
      Delete
    </button>
  );
};

const withConnect = connect(
  null,
  { deleteProjectTask }
);

export default withConnect(DeleteProjectTask);
