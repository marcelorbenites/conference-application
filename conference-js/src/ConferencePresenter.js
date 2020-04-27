import { State } from "./State";

export class ConferencePresenter {
  constructor(viewModel) {
    this.viewModel = viewModel;
  }

  onStateChanged(state) {
    switch (state.name) {
      case State.LOADING:
        this.viewModel.showLoading = true;
        this.viewModel.showError = false;
        this.viewModel.showConference = false;
        break;
      case State.LOADED:
        this.viewModel.showLoading = false;
        this.viewModel.showError = false;
        this.viewModel.showConference = true;
        this.viewModel.conferenceName = state.value.name;
        break;
      case State.ERROR:
        this.viewModel.showLoading = false;
        this.viewModel.showError = true;
        this.viewModel.errorMessage = state.error.message;
        this.viewModel.showConference = false;
        break;
      case State.IDLE:
      default:
        this.viewModel.showLoading = false;
        this.viewModel.showError = false;
        this.viewModel.showConference = false;
        break;
    }
    this.viewModel.notifyObservers();
  }
}
