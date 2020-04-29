import { StateMachine } from "./StateMachine";

export class ConferenceStateMachine extends StateMachine {
  constructor(conferenceGateway) {
    super();
    this.conferenceGateway = conferenceGateway;
  }

  async start() {
    await this.loadConference();
  }

  async loadConference() {
    this.moveToLoading();
    try {
      this.moveToLoaded(await this.conferenceGateway.getConference());
    } catch (e) {
      this.moveToError(e);
    }
  }
}
