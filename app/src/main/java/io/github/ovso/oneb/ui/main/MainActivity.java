package io.github.ovso.oneb.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import io.github.ovso.oneb.R;
import io.github.ovso.oneb.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainPresenter.View {
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
}
