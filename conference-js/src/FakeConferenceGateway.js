export class FakeConferenceGateway {
  constructor(conference, error = null) {
    this.conference = conference;
    this.error = error;
  }
  getConference() {
    if (this.error) {
      throw this.error;
    }

    return this.conference;
  }
}
