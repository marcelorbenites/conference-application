export class ConferenceApplication {
  model = {
    showLoading: true,
    showError: false,
    showConference: false,
    conferenceName: "",
    errorMessage: "",
  };
  callback = null;

  constructor(httpClient, baseUrl = "http://localhost:3000") {
    this.baseUrl = baseUrl;
    this.httpClient = httpClient;
  }

  async loadConference() {
    this.showLoading();
    try {
      const request = await this.httpClient.get(`${this.baseUrl}/conferences`);

      if (request.status === 200) {
        this.showConference(JSON.parse(await request.getBody())[0]);
      } else {
        this.showError("Failed to return conferences.");
      }
    } catch (e) {
      this.showError(e);
    }
  }

  onChange(callback) {
    this.callback = callback;
  }

  showError(errorMessage) {
    this.model.showLoading = false;
    this.model.showError = true;
    this.model.errorMessage = errorMessage;
    this.model.showConference = false;
    this.notifyChange();
  }

  showConference(conference) {
    this.model.showLoading = false;
    this.model.showError = false;
    this.model.showConference = true;
    this.model.conferenceName = conference.name;
    this.notifyChange();
  }

  showLoading() {
    this.model.showLoading = true;
    this.model.showError = false;
    this.model.showConference = false;
    this.notifyChange();
  }

  notifyChange() {
    if (this.callback) {
      this.callback();
    }
  }
}
