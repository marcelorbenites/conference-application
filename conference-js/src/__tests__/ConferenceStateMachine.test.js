import { ConferenceStateMachine } from "../ConferenceStateMachine";
import { FakeDispatcher } from "../FakeDispatcher";
import { FakeConferenceGateway } from "../FakeConferenceGateway";
import { ViewModel } from "../ViewModel";
import { ConferencePresenter } from "../ConferencePresenter";

describe("Conference State Machine", () => {
  it("shows conference name", () => {
    const conference = {
      name: "Droidcon",
      id: 1,
    };
    const stateMachine = new ConferenceStateMachine(
      new FakeDispatcher(),
      new FakeConferenceGateway(conference)
    );
    const viewModel = new ViewModel();

    stateMachine.addStateChangedListener(new ConferencePresenter(viewModel));
    stateMachine.start();

    expect(viewModel.showLoading).toEqual(false);
    expect(viewModel.showError).toEqual(false);
    expect(viewModel.conferenceName).toEqual("Droidcon");
    expect(viewModel.showConference).toEqual(true);
  });

  it("emits an error", () => {
    const errorMessage = "An error occurred.";
    const stateMachine = new ConferenceStateMachine(
      new FakeDispatcher(),
      new FakeConferenceGateway(undefined, new Error(errorMessage))
    );
    const viewModel = new ViewModel();

    stateMachine.addStateChangedListener(new ConferencePresenter(viewModel));
    stateMachine.start();

    expect(viewModel.showLoading).toEqual(false);
    expect(viewModel.showError).toEqual(true);
    expect(viewModel.errorMessage).toEqual(errorMessage);
    expect(viewModel.showConference).toEqual(false);
  });
});
