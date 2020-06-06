import React from 'react';
import {Field, reduxForm} from "redux-form";
import {RenderSelectInput} from "../layout/SuggestSelect";

const validate = values => {
  const errors = {};
  console.log(values)
  if (!values.username) {
    errors.username = "User is required";
  }
  return errors;
};

const AddUserToProject = ({options, handleSubmit}) => {
  return (
    <form onSubmit={handleSubmit}>
      <div className="form-group small">
        <Field name="username" component={RenderSelectInput} options={options} required />
      </div>
      <input type="submit" className="btn btn-primary btn-block mt-4" />
    </form>
  );
};

const withReduxForm = reduxForm({
  form: "addUserToProject",
  validate
});

export default withReduxForm(AddUserToProject);
