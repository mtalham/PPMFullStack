import React, {useState} from "react";
import {Link} from "react-router-dom";
import Backlog from "./Backlog";
import {connect} from "react-redux";
import {getBacklog} from "../../actions/backlogActions";
import Users from "../users/Users";
import {getProjectAccess} from "../../actions/ProjectActions";

const ProjectBoardError = ({error}) =>
  error.length > 0 ? (
    <div className="alert alert-danger text-center" role="alert">
      {error}.
    </div>
  ) : (
    <div className="alert alert-danger text-center" role="alert">
      No project task on this Board.
    </div>
  );

const ProjectBoard = ({match, backlog, errors, getBacklog, getProjectAccess, access}) => {
  const [openModal, setOpenModal] = useState(false);

  const {id} = match.params;
  React.useEffect(() => {
    getProjectAccess(id);
    getBacklog(id);
    // eslint-disable-next-line
  }, [id]);

  return (
    <div className="container">
      <div style={{display: "flex", justifyContent: "space-between"}}>
        {access && <Link to={`/addProjectTask/${id}`} disabled className="btn btn-primary">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>}
        <button className="btn btn-primary" disabled={!access}>
          <i className="fas fa-plus-circle" onClick={() => setOpenModal(true)}>
            Add User
          </i>
        </button>
      </div>
      <br/>
      <hr/>
      {backlog.projectTasks.length === 0 ? (
        <ProjectBoardError error={errors}/>
      ) : (
        <Backlog projectTasks={backlog.projectTasks} />
      )}
      {openModal && (
        <Users
          openModal={openModal}
          setOpenModal={setOpenModal}
          projectId={id}
        />
      )}
    </div>
  );
};

const mapStateToProps = state => ({
  access: state.project.hasAccess,
  backlog: state.backlog,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  {getBacklog, getProjectAccess}
)(ProjectBoard);
