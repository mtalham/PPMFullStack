import React from "react";
import { connect } from "react-redux";

import PropTypes from "prop-types";
import { editProject, getProject } from "../../actions/ProjectActions";
import { dateFormat } from "./ProjectUtils";
import ProjectForm from "./ProjectForm";

const mapToInitialValues = project => ({
  projectName: project.projectName,
  projectIdentifier: project.projectIdentifier,
  description: project.description,
  startDate: dateFormat(project.startDate),
  endDate: dateFormat(project.endDate)
});

const UpdateProject = ({
  getProject,
  history,
  match,
  editProject,
  project
}) => {
  const { id } = match.params;
  React.useEffect(() => {
    getProject(id, history);
  }, [getProject, id, history]);

  const handleOnSubmit = values => {
    editProject(values, history);
  };

  return (
    <div className="project">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h5 className="display-4 text-center">Update Project form</h5>
            <hr />
            <ProjectForm
              onSubmit={handleOnSubmit}
              initialValues={mapToInitialValues(project)}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

UpdateProject.propTypes = {
  getProject: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
  project: state.project.project
});

const withConnect = connect(
  mapStateToProps,
  { getProject, editProject }
);

export default withConnect(UpdateProject);
