import gql from "graphql-tag";

export const USERS_QUERY = gql`
  query Users {
    users {
      username
      fullName
    }
  }
`;

export const ADD_USER_TO_PROJECT = gql`
  mutation AddUserToProject($username: String!, $projectId: String!) {
      addUserToProject(username: $username, projectId: $projectId)
  }
`;