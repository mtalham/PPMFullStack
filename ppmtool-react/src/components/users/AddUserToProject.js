import React from 'react';
import {Field, reduxForm} from "redux-form";
import {RenderSelectInput} from "../layout/SuggestSelect";

const AddUserToProject = ({options, handleSubmit}) => {
  return (
    <form onSubmit={handleSubmit}>
      <div className="form-group small">
        <Field name="username" component={RenderSelectInput} options={options} />
      </div>
      <input type="submit" className="btn btn-primary btn-block mt-4" />
    </form>
  );
};

const withReduxForm = reduxForm({
  form: "addUserToProject"
});

export default withReduxForm(AddUserToProject);