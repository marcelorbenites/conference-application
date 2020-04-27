import React from "react";

export class ConferenceText extends React.Component {
  observer = () => {
    this.setState({});
  };

  constructor(props, context) {
    super(props, context);
    this.viewModel = props.viewModel;
  }

  componentDidMount() {
    this.viewModel.addObserver(this.observer);
  }

  componentWillUnmount() {
    this.viewModel.removeObserver(this.observer);
  }

  render() {
    const {
      errorMessage,
      conferenceName,
      showLoading,
      showError,
      showConference,
    } = this.props.viewModel;

    if (showLoading) {
      return "Loading...";
    }

    if (showError) {
      return errorMessage;
    }

    if (showConference) {
      return conferenceName;
    }

    return "";
  }
}
