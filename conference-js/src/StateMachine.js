import { State } from "./State";

export class StateMachine {
  listeners = new Set();

  addStateChangedListener(listener) {
    this.listeners.add(listener);
  }

  moveToLoading() {
    this.updateState({ name: State.LOADING });
  }

  moveToLoaded(value) {
    this.updateState({ name: State.LOADED, value: value });
  }

  moveToError(error) {
    this.updateState({ name: State.ERROR, error: error });
  }

  updateState(state) {
    this.listeners.forEach((listener) => {
      listener.onStateChanged(state);
    });
  }
}
