import React from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import {
  getProjectTask,
  updateProjectTask
} from "../../../actions/backlogActions";
import ProjectTaskForm from "./ProjectTaskForm";
import { dateFormat } from "../../project/ProjectUtils";

const mapInitialValues = existingTask => ({
  summary: existingTask.summary,
  status: existingTask.status,
  acceptanceCriteria: existingTask.acceptanceCriteria,
  priority: existingTask.priority,
  dueDate: dateFormat(existingTask.dueDate)
});

const UpdateProjectTask = ({
  match,
  updateProjectTask,
  history,
  getProjectTask,
  existingTask,
  errors
}) => {
  const { backlog_id, pt_id } = match.params;
  React.useEffect(() => {
    getProjectTask(backlog_id, pt_id, history);
    // eslint-disable-next-line
  }, [backlog_id, pt_id]);

  const handleOnSubmit = values => {
    console.log(values, 'form');
    updateProjectTask(backlog_id, pt_id, values, history);
  };

  return (
    <div className="add-PBI">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <Link to={`/projectBoard/${backlog_id}`} className="btn btn-light">
              Back to Project Board
            </Link>
            <h4 className="display-4 text-center">Update Project Task</h4>
            {errors.length > 0 && <div className="alert-danger">{errors}</div>}
            <ProjectTaskForm
              initialValues={mapInitialValues(existingTask)}
              onSubmit={handleOnSubmit}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  errors: state.errors,
  existingTask: state.backlog.projectTask
});

const withConnect = connect(
  mapStateToProps,
  { updateProjectTask, getProjectTask }
);

export default withConnect(UpdateProjectTask);
