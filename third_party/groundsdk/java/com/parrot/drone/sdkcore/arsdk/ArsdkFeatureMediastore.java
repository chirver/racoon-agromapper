/** Generated, do not edit ! */
package com.parrot.drone.sdkcore.arsdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parrot.drone.sdkcore.arsdk.command.ArsdkCommand;
import com.parrot.drone.sdkcore.ulog.ULog;

import static com.parrot.drone.sdkcore.arsdk.Logging.TAG;

import java.nio.ByteBuffer;
import android.util.SparseArray;

/**
 * Mediastore feature command/event interface.
 */
public class ArsdkFeatureMediastore {

    /**
     * .
     */
    public enum State {

        /**
         * The media store is not available yet.
         */
        NOT_AVAILABLE(0),

        /**
         * The media store is indexing.
         */
        INDEXING(1),

        /**
         * The media store is indexed and ready to be used.
         */
        INDEXED(2);

        /** Internal arsdk value. */
        public final int value;

        /**
         * Obtains an enum from its internal arsdk value.
         *
         * @param value arsdk enum internal value
         *
         * @returns the corresponding enum in case it exists, otherwise {@code null} 
         */
        @Nullable
        public static State fromValue(int value) {
            return MAP.get(value, null);
        }

        private State(int value) {
            this.value = value;
        }

        private static final SparseArray<State> MAP;

        static {
            MAP = new SparseArray<>();
            for (State e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9600;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x0001;

    /** Uid of counters event. */;
    public static final int COUNTERS_UID = 0x0002;

    /**
     * Decodes a command.
     *
     * @param command : command to decode
     * @param callback: callback receiving decoded events
     */
    public static void decode(@NonNull ArsdkCommand command, @NonNull Callback callback) {
        nativeDecode(command.getNativePtr(), callback);
    }

    /** Callback receiving decoded events. */
    public interface Callback {

        /**

         * 
         * @param state: Media store state.
         */
        public default void onState(@Nullable State state) {}

        /**

         * 
         * @param videoMediaCount: Number of video media in the media store, -1 if the store is not indexed.
         * @param photoMediaCount: Number of photo media in the media store, -1 if the store is not indexed.
         * @param videoResourceCount: Number of video resources in the media store, -1 if the store is not indexed.
         * @param photoResourceCount: Number of photo resources in the media store, -1 if the store is not indexed.
         */
        public default void onCounters(int videoMediaCount, int photoMediaCount, int videoResourceCount, int photoResourceCount) {}
    }

    private static void state(Callback cb, int state) {
        ArsdkFeatureMediastore.State enumState = ArsdkFeatureMediastore.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMediastore.State value " + state);
        try {
            cb.onState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mediastore.state [state: " + state + "]", e);
        }
    }

    private static void counters(Callback cb, int videoMediaCount, int photoMediaCount, int videoResourceCount, int photoResourceCount) {
        try {
            cb.onCounters(videoMediaCount, photoMediaCount, videoResourceCount, photoResourceCount);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mediastore.counters [video_media_count: " + videoMediaCount + ", photo_media_count: " + photoMediaCount + ", video_resource_count: " + videoResourceCount + ", photo_resource_count: " + photoResourceCount + "]", e);
        }
    }
    
    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
}
