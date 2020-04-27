export class ViewModel {
  observers = new Set();

  addObserver(observer) {
    this.observers.add(observer);
  }

  removeObserver(observer) {
    this.observers.delete(observer);
  }

  notifyObservers() {
    this.observers.forEach((observer) => {
      observer();
    });
  }
}
