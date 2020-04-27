import React from "react";
import renderer from "react-test-renderer";
import { DependencyManager } from "../DependencyManager";
import { App } from "../App";

describe("Application", () => {
  it("renders Droidcon on the screen", function () {
    const testRender = renderer.create(
      <App dependencyManager={new DependencyManager()} />
    );
    const appRoot = testRender.root;

    // expect(appRoot.findByType(LikeButton).)
  });
});
