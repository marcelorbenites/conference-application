'use strict';

const e = React.createElement;

class LikeButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        error: null,
        conference: null
    };
  }

  render() {
    const { error, conference } = this.state;

    if (error) {
        return error.message
     }

    if (conference) {
      return conference.name;
    }

    return e(
      'button',
      { onClick: () => {fetch("http://localhost/conferences")
                  .then(res => res.json())
                  .then(
                    (result) => {
                      this.setState({
                        conference: result[0]
                      });
                    },
                    (error) => {
                      this.setState({
                        error
                      });
                    }
                  )}},
      'Load Conference'
    );
  }
}

const domContainer = document.querySelector('#like_button_container');
ReactDOM.render(e(LikeButton), domContainer);