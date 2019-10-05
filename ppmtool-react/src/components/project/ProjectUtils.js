import React from "react";
import moment from "moment";

export const validate = values => {
  const errors = {};
  if (!values.projectName) {
    errors.projectName = "Project name is required";
  }
  if (!values.projectIdentifier) {
    errors.projectIdentifier = "Unique Id is required";
  }
  if (!values.description) {
    errors.description = "Project description is required";
  }
  return errors;
};

export const projectTaskValidator = values => {
  const errors = {};
  if (!values.summary) errors.summary = "Project task summary is required";
  return errors;
};

export const renderField = ({
  input,
  label,
  type,
  disabled,
  meta: { touched, error }
}) => (
  <div className="alert-danger">
    <input
      {...input}
      placeholder={label}
      type={type}
      disabled={disabled}
      className="form-control form-control-lg"
    />
    {touched && (error && <span>{error}</span>)}
  </div>
);

export const dateFormat = date => {
  if (date === null) return;
  return moment(date).format("YYYY-MM-DD");
};
