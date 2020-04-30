import { ConferenceApplication } from "../ConferenceApplication";
import { FakeHttpClient } from "../__mocks__/FakeHttpClient";

describe("Application", () => {
  it("shows conference name", async () => {
    const application = new ConferenceApplication(
      new FakeHttpClient({
        status: 200,
        async getBody() {
          return '[{"id" : 1, "name": "Droidcon"}]';
        },
      })
    );

    await application.loadConference();

    expect(application.model).toStrictEqual({
      showLoading: false,
      showError: false,
      showConference: true,
      conferenceName: "Droidcon",
      errorMessage: "",
    });
  });
});
