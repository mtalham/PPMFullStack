import React from "react";
import classsnames from "classnames";
import { Link } from "react-router-dom";
import DeleteProjectTask from "./DeleteProjectTasj";

const mapPriority = priority => {
  if (priority === 1) return "HIGH";
  if (priority === 2) return "MEDIUM";
  if (priority === 3) return "LOW";
};

const ProjectTask = ({ projectTask }) => {
  const {
    projectSequence,
    summary,
    priority,
    acceptanceCriteria
  } = projectTask;
  return (
    <div className="card mb-1 bg-light">
      <div
        className={classsnames("card-header text-primary", {
          "bg-danger text-light": priority === 1,
          "bg-warning text-light": priority === 2,
          "bg-info text-light": priority === 3
        })}
      >
        ID: {projectSequence} -- Priority: {mapPriority(priority)}
      </div>
      <div className="card-body bg-light">
        <h5 className="card-title">{summary}</h5>
        <p className="card-text text-truncate ">
          {acceptanceCriteria && acceptanceCriteria}
        </p>
        <Link
          to={`/updateProjectTask/${
            projectTask.projectIdentifier
          }/${projectSequence}`}
          className="btn btn-primary"
        >
          View / Update
        </Link>

        <DeleteProjectTask backlog_id={projectTask.projectIdentifier} pt_id={projectSequence} />
      </div>
    </div>
  );
};

export default ProjectTask;
