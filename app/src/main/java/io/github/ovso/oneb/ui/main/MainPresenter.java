package io.github.ovso.oneb.ui.main;

import io.github.ovso.oneb.utils.SimOperator;

public interface MainPresenter {

  void onCreate();

  void onCheckedChange(int checkedId);

  void onEmailTextChanged(String email);

  void onTestModeChecked(boolean checked);

  void onSaveClick();

  interface View {
    void setupTitle(String title);

    void addOperatorItem(SimOperator.Type value, int id);

    void checkOperator(int checkedItemId);

    void setupOperatorGroup();

    void enableSaveButton(boolean enable);

    void setupEmail(String email);
  }
}
