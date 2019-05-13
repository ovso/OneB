package io.github.ovso.oneb.ui.main;

import android.content.Intent;
import com.pixplicity.easyprefs.library.Prefs;
import io.github.ovso.oneb.App;
import io.github.ovso.oneb.R;
import io.github.ovso.oneb.utils.Consts;
import io.github.ovso.oneb.utils.SimOperator;
import io.github.ovso.oneb.utils.UserAccountFetcher;
import timber.log.Timber;

public class MainPresenterImpl implements MainPresenter {

  private final View view;
  private int checkedItemId = 0;
  private String email;
  private boolean testMode;

  MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
  }

  @Override public void onCreate() {
    String title = App.getInstance().getString(R.string.main_title);
    view.setupTitle(title);
    String email = UserAccountFetcher.getEmail(App.getInstance());
    view.setupEmail(email);
    view.enableSaveButton(UserAccountFetcher.isValidEmail(email));
    setupOperators();
  }

  @Override public void onCheckedChange(int checkedId) {
    Timber.d("onCheckedChange(%d)", checkedId);
    checkedItemId = checkedId;
  }

  @Override public void onEmailTextChanged(String email) {
    this.email = email;
    view.enableSaveButton(UserAccountFetcher.isValidEmail(email));
  }

  @Override public void onTestModeChecked(boolean checked) {
    testMode = checked;
    Timber.d("testMode = %s", testMode);
    sendDataBroadcast();
    sendModeBroadcast(checked);
  }

  private void sendModeBroadcast(boolean checked) {
    Intent intent = new Intent(Consts.BR_ACTION_MODE).putExtra(Consts.BR_KEY_MODE, checked);
    App.getInstance().sendBroadcast(intent);
  }

  private void sendDataBroadcast() {
    Intent intent = new Intent(Consts.BR_ACTION_DATA)
        .putExtra(Consts.BR_KEY_OPERATOR, Prefs.getInt(Consts.PREFS_KEY_OPERATOR, 0))
        .putExtra(Consts.BR_KEY_EMAIL, Prefs.getString(Consts.PREFS_KEY_EMAIL, ""));
    App.getInstance().sendBroadcast(intent);
  }

  @Override public void onSaveClick() {
    Prefs.putInt(Consts.PREFS_KEY_OPERATOR, checkedItemId);
    Prefs.putString(Consts.PREFS_KEY_EMAIL, email);
    if (testMode) {
      sendDataBroadcast();
    }
  }

  private void setupOperators() {
    for (int i = 0; i < SimOperator.Type.values().length; i++) {
      view.addOperatorItem(SimOperator.Type.values()[i], i);
    }

    view.checkOperator(checkedItemId);
    view.setupOperatorGroup();
  }
}
