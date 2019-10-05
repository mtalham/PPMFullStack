export const signUpValidation = values => {
  const errors = {};

  if (!values.username) {
    errors.username = "Email is required";
  }
  if (!values.fullName) {
    errors.fullName = "Name is required";
  }
  if (!values.password) {
    errors.password = "Password is required";
  }
  if (!values.confirmPassword) {
    errors.confirmPassword = "Password is required";
  }
  if (values && values.password && values.confirmPassword) {
    if (values.password.length < 6) {
      errors.password = "Password must be at least 6 characters long";
    }
    if (values.confirmPassword.length < 6) {
      errors.confirmPassword = "Password must be at least 6 characters long";
    }
    if (values.password !== values.confirmPassword) {
      errors.password = "Passwords must match";
      errors.confirmPassword = "Passwords must match";
    }
  }

  return errors;
};

export const loginValidation = values => {
  const errors = {};
  if (!values.username) {
    errors.username = "Username is required";
  }
  if (!values.password) {
    errors.password = "Password is required";
  }
  return errors;
};
