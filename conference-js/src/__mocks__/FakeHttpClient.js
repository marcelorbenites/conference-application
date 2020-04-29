export class FakeHttpClient {
    constructor(response) {
        this.response = response;
    }

    async get(url) {
        return this.response;
    }
}