package io.github.ovso.oneb.ui.main;

import io.github.ovso.oneb.App;
import io.github.ovso.oneb.R;

public class MainPresenterImpl implements MainPresenter {

  private final View view;

  public MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
    String title = App.getInstance().getString(R.string.main_title);
    view.setupTitle(title);
  }
}
