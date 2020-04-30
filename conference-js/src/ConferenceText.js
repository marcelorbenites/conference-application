import React from "react";

export const ConferenceText = (props) => {
  const {
    errorMessage,
    conferenceName,
    showLoading,
    showError,
    showConference,
  } = props.model;

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
};
