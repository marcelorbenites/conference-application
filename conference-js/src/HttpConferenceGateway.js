export class HttpConferenceGateway {
  constructor(httpClient, baseUrl = "http://localhost:3000") {
    this.baseUrl = baseUrl;
    this.httpClient = httpClient;
  }
  async getConference() {
    const url = `${this.baseUrl}/conferences`;
    const request = await this.httpClient.get(url);

    if (request.status === 200) {
      return JSON.parse(await request.getBody())[0];
    }
    throw new Error("Failed to return conferences.");
  }
}
