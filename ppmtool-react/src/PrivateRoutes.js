import React from "react";
import { Switch } from "react-router-dom";

import SecuredRoute from "./SecuredRoute";
import Dashboard from "./components/Dashboard";
import AddProject from "./components/project/AddProject";
import UpdateProject from "./components/project/UpdateProject";
import ProjectBoard from "./components/projectboard/ProjectBoard";
import AddProjectTask from "./components/projectboard/projecttask/AddProjectTask";
import UpdateProjectTask from "./components/projectboard/projecttask/UpdateProjectTask";

const PrivateRoutes = () => (
  <Switch>
    <SecuredRoute exact path="/dashboard" component={Dashboard} />
    <SecuredRoute exact path="/addProject" component={AddProject} />
    <SecuredRoute exact path="/updateProject/:id" component={UpdateProject} />
    <SecuredRoute exact path="/projectBoard/:id" component={ProjectBoard} />
    <SecuredRoute exact path="/addProjectTask/:id" component={AddProjectTask} />
    {/*<SecuredRoute exact path="/addUser/:id" component={Users} />*/}
    <SecuredRoute
      exact
      path="/updateProjectTask/:backlog_id/:pt_id"
      component={UpdateProjectTask}
    />
  </Switch>
);

export default PrivateRoutes;
