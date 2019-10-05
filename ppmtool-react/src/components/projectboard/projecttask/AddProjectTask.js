import React from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { addProjectTask } from "../../../actions/backlogActions";
import ProjectTaskForm from "./ProjectTaskForm";

const AddProjectTask = ({ match, addProjectTask, history, errors }) => {
  const { id } = match.params;

  const handleOnSubmit = values => {
    addProjectTask(id, values, history);
  };

  return (
    <div className="add-PBI">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <Link to={`/projectBoard/${id}`} className="btn btn-light">
              Back to Project Board
            </Link>
            <h4 className="display-4 text-center">Add Project Task</h4>
            {errors.length > 0 && <div className="alert-danger">{errors}</div>}
            <ProjectTaskForm onSubmit={handleOnSubmit} />
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  errors: state.errors
});

const withConnect = connect(
  mapStateToProps,
  { addProjectTask }
);

export default withConnect(AddProjectTask);
