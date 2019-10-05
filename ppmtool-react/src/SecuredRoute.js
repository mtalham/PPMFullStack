import React from "react";
import { Route, Redirect } from "react-router-dom";
import { connect } from "react-redux";

const SecuredRoute = ({ component: Component, security, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      security.validToken === true ? (
        <Component {...props} />
      ) : (
        <Redirect to="/login" />
      )
    }
  />
);

const mapStateToProps = state => ({
  security: state.loggedInUser
});

const withConnect = connect(mapStateToProps);

export default withConnect(SecuredRoute);
