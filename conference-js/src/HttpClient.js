export class HttpClient {
  async get(url) {
    return fetch(url).then((response) => {
      return {
        status: response.status,
        async getBody() {
          return response.text();
        },
      };
    });
  }
}
