import { StateMachine } from "./StateMachine";

export class ConferenceStateMachine extends StateMachine {
  constructor(dispatcher, conferenceGateway) {
    super();
    this.dispatcher = dispatcher;
    this.conferenceGateway = conferenceGateway;
  }

  start() {
    this.loadConference();
  }

  loadConference() {
    this.dispatcher.dispatch(
      () => {
        this.moveToLoading();
        this.moveToLoaded(this.conferenceGateway.getConference());
      },
      (e) => {
        this.moveToError(e);
      }
    );
  }
}
