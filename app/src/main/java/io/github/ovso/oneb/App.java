package io.github.ovso.oneb;

import android.app.Application;
import com.facebook.stetho.Stetho;
import lombok.Getter;
import timber.log.Timber;

public class App extends Application {
  @Getter private static App instance;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
