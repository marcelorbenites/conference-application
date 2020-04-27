import React from "react";
import { css } from "@emotion/core";
import { ConferenceText } from "./ConferenceText";
import logo from "./logo.svg";

export class App extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.dependencyManager = props.dependencyManager;
    this.conferecenController = this.dependencyManager.getConferenceController();
  }

  componentDidMount() {
    this.conferecenController.start();
  }

  render() {
    return (
      <div
        css={css`
          text-align: center;
        `}
      >
        <header
          css={css`
            background-color: #282c34;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: calc(10px + 2vmin);
            color: white;
          `}
        >
          <img
            src={logo}
            alt="logo"
            css={css`
              height: 40px;
            `}
          />
          <ConferenceText
            viewModel={this.dependencyManager.getConferenceViewModel()}
          />
        </header>
      </div>
    );
  }
}
