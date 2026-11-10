package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

/**
 * Small in-process broadcast helper with the same public API subset GroundSDK uses
 * (getInstance, registerReceiver, unregisterReceiver, sendBroadcast). Written for Racoon AgroMapper
 * so the app does not need the AndroidX artifact; delivery is asynchronous on the main thread, like AndroidX.
 */
public final class LocalBroadcastManager {
    private static LocalBroadcastManager sInstance;
    private final Context mAppContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<Object[]> mReceivers = new ArrayList<>();

    private LocalBroadcastManager(Context context) { mAppContext = context; }

    public static LocalBroadcastManager getInstance(Context context) {
        synchronized (LocalBroadcastManager.class) {
            if (sInstance == null) sInstance = new LocalBroadcastManager(context.getApplicationContext());
            return sInstance;
        }
    }

    public void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        synchronized (mReceivers) { mReceivers.add(new Object[] {receiver, filter}); }
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        synchronized (mReceivers) {
            for (int i = mReceivers.size() - 1; i >= 0; i--) if (mReceivers.get(i)[0] == receiver) mReceivers.remove(i);
        }
    }

    public boolean sendBroadcast(final Intent intent) {
        final List<BroadcastReceiver> targets = new ArrayList<>();
        synchronized (mReceivers) {
            for (Object[] r : mReceivers) {
                IntentFilter f = (IntentFilter) r[1];
                if (f.match(intent.getAction(), intent.resolveTypeIfNeeded(mAppContext.getContentResolver()),
                        intent.getScheme(), intent.getData(), intent.getCategories(), "LocalBroadcastManager") >= 0) {
                    targets.add((BroadcastReceiver) r[0]);
                }
            }
        }
        if (targets.isEmpty()) return false;
        mHandler.post(new Runnable() {
            @Override public void run() { for (BroadcastReceiver br : targets) br.onReceive(mAppContext, intent); }
        });
        return true;
    }

    public void sendBroadcastSync(Intent intent) {
        if (sendBroadcast(intent)) { /* delivered asynchronously; sync variant kept for API compatibility */ }
    }
}
