export class FakeDispatcher {
  dispatch(execute, error) {
    try {
      execute();
    } catch (e) {
      error(e);
    }
  }
}
