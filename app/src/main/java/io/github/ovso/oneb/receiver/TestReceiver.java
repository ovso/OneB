package io.github.ovso.oneb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;

public class TestReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    Timber.d("OneB onReceive()");
  }
}
