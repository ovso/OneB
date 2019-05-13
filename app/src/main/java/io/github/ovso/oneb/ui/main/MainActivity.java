package io.github.ovso.oneb.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.github.ovso.oneb.R;
import io.github.ovso.oneb.ui.base.BaseActivity;
import io.github.ovso.oneb.utils.SimOperator;

public class MainActivity extends BaseActivity implements MainPresenter.View {
  @BindView(R.id.radiogroup_main) RadioGroup radioGroup;
  @BindView(R.id.button_main_save) Button saveButton;
  @BindView(R.id.edittext_main_email) EditText emailEditText;

  private MainPresenter presenter = new MainPresenterImpl(this);

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    finish();
    return super.onOptionsItemSelected(item);
  }

  @Override public void setupTitle(String title) {
    setTitle(title);
  }

  @Override public void addOperatorItem(SimOperator.Type value, int id) {
    RadioButton radioButton = new RadioButton(this);
    radioButton.setId(id);
    radioButton.setText(value.getDisplayName());
    radioGroup.addView(radioButton);
  }

  @Override public void checkOperator(int checkedItemId) {
    radioGroup.check(checkedItemId);
  }

  @Override public void setupOperatorGroup() {
    radioGroup.setOnCheckedChangeListener(
        (group, checkedId) -> presenter.onCheckedChange(checkedId));
  }

  @Override public void enableSaveButton(boolean enable) {
    saveButton.setEnabled(enable);
  }

  @Override public void setupEmail(String email) {
    emailEditText.setText(email);
  }

  @OnTextChanged(R.id.edittext_main_email) void onEmailTextChanged(CharSequence s) {
    presenter.onEmailTextChanged(s.toString());
  }

  @OnCheckedChanged(R.id.switch_main_test_on_off) void onTestSwitchCheckedChange(boolean checked) {
    presenter.onTestModeChecked(checked);
  }

  @OnClick(R.id.button_main_save) void onSaveClick() {
    presenter.onSaveClick();
  }
}
