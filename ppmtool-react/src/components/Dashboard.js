import React, { useEffect } from "react";
import { connect } from "react-redux";

import { getProjects } from "../actions/ProjectActions";
import ProjectItem from "./project/ProjectItem";
import CreateProjectButton from "./project/CreateProjectButton";

const Dashboard = ({ getProjects, project }) => {
  useEffect(() => {
    getProjects();
  }, [getProjects]);

  return (
    <div className="projects">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <h1 className="display-4 text-center">Projects</h1>
            <br />
            <CreateProjectButton />
            <br />
            <hr />
            <ProjectItem projects={project.projects} />
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
