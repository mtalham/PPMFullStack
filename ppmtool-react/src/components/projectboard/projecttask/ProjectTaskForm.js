import React from "react";
import { Field, reduxForm } from "redux-form";
import { renderField } from "../../project/ProjectUtils";
import { projectTaskValidator as validate } from "../../project/ProjectUtils";

const priorityOptions = [
  <option key={1} value="">Select Priority</option>,
  <option key={2} value="1">High</option>,
  <option key={3} value="2">Medium</option>,
  <option key={4} value="3">Low</option>
  // { value: 1, label: "High" },
  // { value: 2, label: "Medium" },
  // { value: 3, label: "Low" }
];

const ProjectTaskForm = ({ handleSubmit }) => (
  <form onSubmit={handleSubmit}>
    <div className="form-group">
      <Field
        type="text"
        name="summary"
        label="Project Task summary"
        component={renderField}
      />
    </div>
    <div className="form-group">
      <Field
        label="Acceptance Criteria"
        name="acceptanceCriteria"
        type="text"
        component={renderField}
      />
    </div>
    <h6>Due Date</h6>
    <div className="form-group">
      <Field
        type="date"
        component="input"
        className="form-control form-control-lg"
        name="dueDate"
      />
    </div>
    <div className="form-group">
      <Field
        // component={RenderSelectInput}
        // options={priorityOptions}
        component="select"
        className="form-control form-control-lg"
        name="priority"
      >
        {priorityOptions}
      </Field>
    </div>

    <div className="form-group">
      <Field
        component="select"
        className="form-control form-control-lg"
        name="status"
      >
        <option value="">Select Status</option>
        <option value="TO_DO">TO DO</option>
        <option value="IN_PROGRESS">IN PROGRESS</option>
        <option value="DONE">DONE</option>
      </Field>
    </div>

    <input type="submit" className="btn btn-primary btn-block mt-4" />
  </form>
);

const withReduxForm = reduxForm({
  form: "projectTask",
  enableReinitialize: true,
  validate
});

export default withReduxForm(ProjectTaskForm);
