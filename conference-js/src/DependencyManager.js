import { ConferenceStateMachine } from "./ConferenceStateMachine";
import { FakeDispatcher } from "./FakeDispatcher";
import { FakeConferenceGateway } from "./FakeConferenceGateway";
import { ConferencePresenter } from "./ConferencePresenter";
import { ViewModel } from "./ViewModel";

export class DependencyManager {
  conferenceViewModel = new ViewModel();

  getConferenceController() {
    let conferenceController = new ConferenceStateMachine(
      new FakeDispatcher(),
      new FakeConferenceGateway({
        name: "Droidcon",
        id: 1,
      })
    );
    conferenceController.addStateChangedListener(
      new ConferencePresenter(this.conferenceViewModel)
    );
    return conferenceController;
  }

  getConferenceViewModel() {
    return this.conferenceViewModel;
  }
}
