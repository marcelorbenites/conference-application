export const RECEIVE_CONFERENCE = 'RECEIVE_CONFERENCE';
export const RECEIVE_ERROR = 'RECEIVE_ERROR';
export const REQUEST_CONFERENCE = 'REQUEST_CONFERENCE';

export const loadConference = (dispatch) => {
    dispatch(({
        type: REQUEST_CONFERENCE
    }));
    fetch("http://localhost:80/conferences")
        .then(x => new Promise(resolve => setTimeout(() => resolve(x), 5000)))
        .then(res => res.json())
        .then(
            (result) => {
                console.log("Conference Loaded: " + result[0].name);
                dispatch(({
                    type: RECEIVE_CONFERENCE,
                    conference: result[0]
                }));
            },
            (error) => {
                console.log("Conference Error: " + error);
                dispatch(({
                    type: RECEIVE_ERROR,
                    error: error
                }));
            }
        );
};