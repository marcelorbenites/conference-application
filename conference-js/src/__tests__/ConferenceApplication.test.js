import {ConferenceApplication} from "../ConferenceApplication";
import {FakeHttpClient} from "../__mocks__/FakeHttpClient";

describe("Application", () => {
  it("shows conference name", async () => {
    const application = new ConferenceApplication(
      new FakeHttpClient({
        status: 200,
        async getBody() {
          return "[{\"id\" : 1, \"name\": \"Droidcon\"}]";
        },
      })
    );

    const viewModel = application.getConferenceViewModel();

    await application.start();

    expect(viewModel.showLoading).toBe(false);
    expect(viewModel.showError).toBe(false);
    expect(viewModel.showConference).toBe(true);
    expect(viewModel.conferenceName).toBe("Droidcon");
  });
});
