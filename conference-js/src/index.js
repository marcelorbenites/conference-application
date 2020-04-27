import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { App } from "./App";
import { DependencyManager } from "./DependencyManager";

ReactDOM.render(
  <App dependencyManager={new DependencyManager()} />,
  document.getElementById("root")
);
