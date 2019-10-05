import React from "react";
import ProjectTask from "./projecttask/ProjectTask";
import classnames from "classnames";

const BacklogHeading = ({ className, heading }) => (
  <div className="card text-center mb-2">
    <div className={classnames("card-header text-white", className)}>
      <h3>{heading}</h3>
    </div>
  </div>
);

const BacklogItem = ({ status, projectTasks, heading, className }) => (
  <div className="col-md-4">
    <BacklogHeading heading={heading} className={className} />
    {projectTasks
      .filter(projectTask => projectTask.status === status)
      .map(pt => (
        <ProjectTask key={pt.projectSequence} projectTask={pt} />
      ))}
  </div>
);

const Backlog = ({ projectTasks }) => {
  return (
    <div className="container">
      <div className="row">
        <BacklogItem
          status={"TO_DO"}
          projectTasks={projectTasks}
          heading={"TO DO"}
          className={"bg-secondary"}
        />

        <BacklogItem
          projectTasks={projectTasks}
          status={"IN_PROGRESS"}
          heading={"In Progress"}
          className={"bg-primary"}
        />

        <BacklogItem
          projectTasks={projectTasks}
          status={"DONE"}
          heading={"Done"}
          className={"bg-success"}
        />
      </div>
    </div>
  );
};

export default Backlog;
