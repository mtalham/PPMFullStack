import React from "react";
import { Field, reduxForm } from "redux-form";
import { renderField, validate } from "./ProjectUtils";

const ProjectForm = ({ handleSubmit, errors }) => (
  <form onSubmit={handleSubmit}>
    <div className="form-group">
      <Field
        name="projectName"
        component={renderField}
        type="text"
        label="Project name"
      />
    </div>
    <div className="form-group">
      <Field
        name="projectIdentifier"
        component={renderField}
        type="text"
        label="Unique Project Id"
      />
      {errors && errors.length > 0 && (
        <div className="alert-danger">{errors}</div>
      )}
    </div>

    <div className="form-group">
      <Field
        name="description"
        component={renderField}
        type="text"
        label="Project description"
      />
    </div>

    <h6>Start Date</h6>
    <div className="form-group">
      <Field
        name="startDate"
        component="input"
        type="date"
        className="form-control form-control-lg"
      />
    </div>

    <h6>Estimated End Date</h6>
    <div className="form-group">
      <Field
        name="endDate"
        component="input"
        type="date"
        className="form-control form-control-lg"
      />
    </div>

    <input type="submit" className="btn btn-primary btn-block mt-4" />
  </form>
);

const withReduxForm = reduxForm({
  form: "projectForm",
  enableReinitialize: true,
  validate
});

export default withReduxForm(ProjectForm);
