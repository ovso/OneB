package io.github.ovso.oneb.ui.main;

public interface MainPresenter {

  void onCreate();

  interface View {
    void setupTitle(String title);
  }
}
