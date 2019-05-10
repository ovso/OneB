package io.github.ovso.oneb.ui.main;

import io.github.ovso.oneb.App;
import io.github.ovso.oneb.R;
import io.github.ovso.oneb.utils.SimOperator;
import io.github.ovso.oneb.utils.UserAccountFetcher;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private final View view;
  private int checkedItemId = 0;
  private String email;
  private boolean testMode;

  public MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
    String title = App.getInstance().getString(R.string.main_title);
    view.setupTitle(title);
    setupOperators();
  }

  @Override public void onCheckedChange(int checkedId) {
    Timber.d("onCheckedChange(%d)", checkedId);
  }

  @Override public void onEmailTextChanged(String email) {
    this.email = email;
    view.enableSaveButton(UserAccountFetcher.isValidEmail(email));
  }

  @Override public void onTestModeChecked(boolean checked) {
    testMode = checked;
    Timber.d("testMode = %s", testMode);
  }

  @Override public void onSaveClick() {
    
  }

  private void setupOperators() {
    for (int i = 0; i < SimOperator.Type.values().length; i++) {
      view.addOperatorItem(SimOperator.Type.values()[i], i);
    }

    view.checkOperator(checkedItemId);
    view.setupOperatorGroup();
  }
}
