import React from "react";
import { connect } from "react-redux";
import { Field, reduxForm } from "redux-form";
import { createNewUser } from "../../actions/RegisterationAction";
import { renderField } from "../project/ProjectUtils";
import { signUpValidation as validate } from "./UserValidation";

const SignUp = ({ createNewUser, handleSubmit, history, errors }) => {
  const onSubmit = values => {
    console.log(values);
    createNewUser(values, history);
  };

  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center">Sign Up</h1>
            <p className="lead text-center">Create your Account</p>
            {errors.length > 0 && <div className="alert-danger">{errors}</div>}
            <form onSubmit={handleSubmit(onSubmit)}>
              <div className="form-group">
                <Field
                  type="text"
                  className="form-control form-control-lg"
                  label="Name"
                  name="fullName"
                  component={renderField}
                  required
                />
              </div>
              <div className="form-group">
                <Field
                  type="email"
                  className="form-control form-control-lg"
                  label="Email Address"
                  name="username"
                  component={renderField}
                />
              </div>
              <div className="form-group">
                <Field
                  type="password"
                  className="form-control form-control-lg"
                  label="Password"
                  name="password"
                  component={renderField}
                />
              </div>
              <div className="form-group">
                <Field
                  type="password"
                  className="form-control form-control-lg"
                  label="Confirm Password"
                  name="confirmPassword"
                  component={renderField}
                />
              </div>
              <input type="submit" className="btn btn-info btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  errors: state.errors
});

const withReduxForm = reduxForm({
  form: "createUser",
  validate
});

const withConnect = connect(
  mapStateToProps,
  { createNewUser }
)(SignUp);

export default withReduxForm(withConnect);
