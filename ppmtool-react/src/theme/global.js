import {createGlobalStyle} from "styled-components";

export const GlobalStyles = createGlobalStyle(({theme}) => `
  *,
  *::after,
  *::before {
    box-sizing: border-box;
  }

  body {
    background: ${theme.body};
    color: ${theme.text};
    font-family: BlinkMacSystemFont, -apple-system, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
    transition: all 0.25s linear;
    .card-body {
      background: ${theme.body};
      color: ${theme.text};
    }
    .list-group-item {
      background: ${theme.body};
    }
  }
`)
