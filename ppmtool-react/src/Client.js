import { ApolloClient } from "apollo-client";
import { ApolloLink } from "apollo-link";
import { HttpLink } from "apollo-link-http";
import { InMemoryCache } from "apollo-cache-inmemory";

const authLink = new ApolloLink((operation, forward) => {
  const token = localStorage.jwt;

  operation.setContext({
    headers: {
      authorization: token ? token : ""
    }
  });

  return forward(operation);
});

const httpLink = new HttpLink({
  uri: "http://localhost:8080/api/graphql",
  credentials: "same-origin"
});

const client = new ApolloClient({
  link: authLink.concat(httpLink),
  cache: new InMemoryCache(),
  connectToDevTools: true
});

export default client;
