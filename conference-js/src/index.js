import "core-js/stable";
import "regenerator-runtime/runtime";
import React from "react";
import ReactDOM from "react-dom";
import { ConferenceApplication } from "./ConferenceApplication";
import { HttpClient } from "./HttpClient";
import App from "./App";

ReactDOM.render(
  <App application={new ConferenceApplication(new HttpClient())} />,
  document.getElementById("root")
);
