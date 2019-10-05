import React from "react";
import { Link } from "react-router-dom";

const AuthenticatedUser = ({ user, logout }) => {
  const handleLogout = () => {
    logout();
    window.location.href = "/";
  };

  return (
    <div className="collapse navbar-collapse" id="mobile-nav">
      <ul className="navbar-nav mr-auto">
        <li className="nav-item">
          <Link className="nav-link" to="/dashboard">
            Dashboard
          </Link>
        </li>
      </ul>

      <ul className="navbar-nav ml-auto">
        <li className="nav-item">
          <Link className="nav-link" to="/dashboard">
            <i className="fas fa-user-circle mr-1" />
            {user.fullName}
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/logout" onClick={handleLogout}>
            Logout
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default AuthenticatedUser;
