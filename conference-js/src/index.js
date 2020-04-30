import React from "react";
import ReactDOM from "react-dom";
import { App } from "./App";
import { ConferenceApplication } from "./ConferenceApplication";
import { css } from "@emotion/core";
import {HttpClient} from "./HttpClient";

ReactDOM.render(
  <App
    css={() => css`
      body {
        margin: 0;
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto",
          "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans",
          "Helvetica Neue", sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
      }

      code {
        font-family: source-code-pro, Menlo, Monaco, Consolas, "Courier New",
          monospace;
      }
    `}
    application={new ConferenceApplication(new HttpClient())}
  />,
  document.getElementById("root")
);
