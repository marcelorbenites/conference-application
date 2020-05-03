// this comment tells babel to convert jsx to calls to a function called jsx instead of React.createElement
/** @jsx jsx */
import { jsx, css } from "@emotion/core";
import { hot } from "react-hot-loader/root";
import React from "react";
import { ConferenceText } from "./ConferenceText";
import logo from "./logo.svg";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.application = this.props.application;
  }

  componentDidMount() {
    this.application.loadConference();
    this.application.onChange(() => {
      this.setState({});
    });
  }

  componentWillUnmount() {
    this.application.onChange(null);
  }

  render() {
    return (
      <div>
        <header
          css={() => css`
            background-color: #282c34;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            color: white;
          `}
        >
          <img
            src={logo}
            alt="logo"
            css={() => css`
              height: 500px;
            `}
          />
          <ConferenceText model={this.application.model} />
        </header>
      </div>
    );
  }
}

export default hot(App);
