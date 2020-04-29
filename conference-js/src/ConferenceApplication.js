import { ConferenceStateMachine } from "./ConferenceStateMachine";
import { HttpConferenceGateway } from "./HttpConferenceGateway";
import { ConferencePresenter } from "./ConferencePresenter";
import { ViewModel } from "./ViewModel";
import { HttpClient } from "./HttpClient";

export class ConferenceApplication {
  conferenceController = null;
  conferenceViewModel = null;

  constructor(httpClient = new HttpClient()) {
    this.httpClient = httpClient;
  }

  async start() {
    await this.getConferenceController().start();
  }

  getConferenceController() {
    if (this.conferenceController == null) {
      const conferenceStateMachine = new ConferenceStateMachine(
        new HttpConferenceGateway(this.httpClient)
      );
      conferenceStateMachine.addStateChangedListener(
        new ConferencePresenter(this.getConferenceViewModel())
      );
      this.conferenceController = conferenceStateMachine;
    }
    return this.conferenceController;
  }

  getConferenceViewModel() {
    if (this.conferenceViewModel == null) {
      this.conferenceViewModel = new ViewModel();
    }
    return this.conferenceViewModel;
  }
}
