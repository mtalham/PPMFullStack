import React from "react";
import { connect } from "react-redux";
import { deleteProject } from "../../actions/ProjectActions";

const DeleteProject = ({ projectId, deleteProject }) => {
  const handleDelete = () => {
    if (
      window.confirm(
        "Are you sure? This will delete the project and all the related data to it."
      )
    ) {
      deleteProject(projectId);
    }
  };

  return (
    <>
      <li className="list-group-item delete" onClick={handleDelete}>
        <i className="fa fa-minus-circle pr-1"> Delete Project</i>
      </li>
    </>
  );
};

const withConnect = connect(
  null,
  { deleteProject }
);

export default withConnect(DeleteProject);
