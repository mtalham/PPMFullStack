import React, { useEffect } from "react";
import { Field, reduxForm } from "redux-form";
import { connect } from "react-redux";
import { renderField } from "../project/ProjectUtils";
import { loginUser } from "../../actions/RegisterationAction";
import { loginValidation as validate } from "./UserValidation";

const Login = ({ loginUser, history, errors, handleSubmit, user }) => {
  const onSubmit = values => {
    loginUser(values);
  };

  useEffect(() => {
    if (user && user.validToken) {
      history.push("/dashboard");
    }
  });

  return (
    <div className="login">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center">Log In</h1>
            <form onSubmit={handleSubmit(onSubmit)}>
              <div className="form-group">
                <Field
                  type="email"
                  className="form-control form-control-lg"
                  label="Email Address"
                  name="username"
                  component={renderField}
                />
                {errors && errors.length > 0 && (
                  <div className="alert-danger">{errors}</div>
                )}
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
              <input type="submit" className="btn btn-info btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = state => ({
  user: state.loggedInUser,
  errors: state.errors
});

const withReduxForm = reduxForm({
  form: "loginForm",
  validate
});

const withConnect = connect(
  mapStateToProps,
  { loginUser }
)(Login);

export default withReduxForm(withConnect);
