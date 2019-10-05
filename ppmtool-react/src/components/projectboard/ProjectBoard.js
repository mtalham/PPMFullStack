import React, { useState } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import { getBacklog } from "../../actions/backlogActions";
import Users from "../users/Users";

const ProjectBoardError = ({ error }) =>
  error.length > 0 ? (
    <div className="alert alert-danger text-center" role="alert">
      {error}.
    </div>
  ) : (
    <div className="alert alert-danger text-center" role="alert">
      No project task on this Board.
    </div>
  );

const ProjectBoard = ({ match, backlog, errors, getBacklog }) => {
  const [openModal, setOpenModal] = useState(false);

  const { id } = match.params;
  React.useEffect(() => {
    getBacklog(id);
    // eslint-disable-next-line
  }, [id]);

  return (
    <div className="container">
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <div className="btn btn-primary">
          <i className="fas fa-plus-circle" onClick={() => setOpenModal(true)}>
            Add User
          </i>
        </div>
      </div>
      <br />
      <hr />
      {backlog.projectTasks.length === 0 ? (
        <ProjectBoardError error={errors} />
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
  backlog: state.backlog,
  errors: state.errors
});

export default connect(
  mapStateToProps,
  { getBacklog }
)(ProjectBoard);
