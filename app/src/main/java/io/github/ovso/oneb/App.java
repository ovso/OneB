package io.github.ovso.oneb;

import android.app.Application;
import android.content.ContextWrapper;
import com.facebook.stetho.Stetho;
import com.pixplicity.easyprefs.library.Prefs;
import lombok.Getter;
import timber.log.Timber;

public class App extends Application {
  @Getter private static App instance;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);
    setupTimber();
    setupPrefs();
  }

  private void setupPrefs() {
    new Prefs.Builder()
        .setContext(this)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(getPackageName())
        .setUseDefaultSharedPreference(true)
        .build();
  }

  private void setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
