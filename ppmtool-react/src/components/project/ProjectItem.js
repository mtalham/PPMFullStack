import React from "react";
import { Link } from "react-router-dom";
import DeleteProject from "./DeleteProject";

const ProjectItem = ({ projects }) => {
  return (
    <div className="container">
      {projects &&
        projects.map(project => (
          <div
            key={project.projectIdentifier}
            className="card card-body mb-3"
          >
            <div className="row">
              <div className="col-2">
                <span className="mx-auto">{project.projectIdentifier}</span>
              </div>
              <div className="col-lg-6 col-md-4 col-8">
                <h3>{project.projectName}</h3>
                <p>{project.description}</p>
              </div>
              <div className="col-md-4 d-none d-lg-block">
                <ul className="list-group">
                  <Link to={`/projectBoard/${project.projectIdentifier}`}>
                    <li className="list-group-item board">
                      <i className="fa fa-flag-checkered pr-1">
                        {" "}
                        Project Board{" "}
                      </i>
                    </li>
                  </Link>
                  <Link to={`/updateProject/${project.projectIdentifier}`}>
                    <li className="list-group-item update">
                      <i className="fa fa-edit pr-1"> Update Project Info</i>
                    </li>
                  </Link>
                  <DeleteProject projectId={project.projectIdentifier} />
                </ul>
              </div>
            </div>
          </div>
        ))}
    </div>
  );
};

export default ProjectItem;
