import {
    RECEIVE_CONFERENCE,
    REQUEST_CONFERENCE,
    RECEIVE_ERROR
} from './Actions'

const conferenceReducer = (state = {}, action) => {
    console.log("Action Called: " + action.type);
    switch (action.type) {
        case REQUEST_CONFERENCE:
            return Object.assign({}, state, {
                loading: true,
                conference: null,
                error: null
            });
        case RECEIVE_CONFERENCE:
            return Object.assign({}, state, {
                loading: false,
                conference: action.conference,
                error: null
            });
        case  RECEIVE_ERROR:
            return Object.assign({}, state, {
                loading: false,
                conference: null,
                error: action.error
            });
        default:
            return state;
    }
};

export default conferenceReducer