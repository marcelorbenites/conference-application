import React from 'react';
import {connect} from 'react-redux'
import {loadConference} from './Actions'
import './App.css';

class LikeButton extends React.Component {
    render() {
        const {error, conference, loading} = this.props;

        if (loading) {
            return "Loading...";
        }

        if (error) {
            return error.message;
        }

        if (conference) {
            return conference.name;
        }

        return (
            <button onClick={() => {
                loadConference(this.props.dispatch)
            }}>Load Conference</button>
        );
    }
}

export default connect((state) => {
    return state
})(LikeButton)
