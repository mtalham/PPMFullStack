import React from "react";
import { connect } from "react-redux";

import PropTypes from "prop-types";
import { createProject } from "../../actions/ProjectActions";
import ProjectForm from "./ProjectForm";

const AddProject = ({ createProject, history, errors }) => {
  const onSubmit = values => {
    createProject(values, history);
  };

  return (
    <div>
      <div className="project">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Create Project form</h5>
              <hr />
              <ProjectForm onSubmit={onSubmit} errors={errors} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

AddProject.prototypes = {
  createProject: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});

const withConnect = connect(
  mapStateToProps,
  { createProject }
);

export default withConnect(AddProject);
