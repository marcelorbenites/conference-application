import React from 'react';
import ReactDOM from 'react-dom';
import {createStore} from 'redux';
import Reducers from './Reducers'
import {Provider} from 'react-redux';
import './index.css';
import App from './App';

const store = createStore(Reducers);

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
