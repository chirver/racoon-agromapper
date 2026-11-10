/** Generated, do not edit ! */
package com.parrot.drone.sdkcore.arsdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parrot.drone.sdkcore.arsdk.command.ArsdkCommand;
import com.parrot.drone.sdkcore.ulog.ULog;

import static com.parrot.drone.sdkcore.arsdk.Logging.TAG;

import java.nio.ByteBuffer;
import android.util.SparseArray;

import java.util.function.Consumer;
import java.util.EnumSet;

/**
 * Gauge_fw_updater feature command/event interface.
 */
public class ArsdkFeatureGaugeFwUpdater {

    /**
     * Gauge Firmware Diagnostic.
     */
    public enum Diag {

        /**
         * Gauge Firmware is Up-to-Date.
         */
        UP_TO_DATE(0),

        /**
         * Gauge Firmware cannot be updated.
         */
        CANNOT_UPDATE(1),

        /**
         * Gauge Firmware can be updated.
         */
        UPDATABLE(2);

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
        public static Diag fromValue(int value) {
            return MAP.get(value, null);
        }

        private Diag(int value) {
            this.value = value;
        }

        private static final SparseArray<Diag> MAP;

        static {
            MAP = new SparseArray<>();
            for (Diag e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of Gauge Firmware Service.
     */
    public enum State {

        /**
         * Service is ready to prepare Update.
         */
        READY_TO_PREPARE(0),

        /**
         * Service Preparation is in Progress.
         */
        PREPARATION_IN_PROGRESS(1),

        /**
         * Service is ready to Update.
         */
        READY_TO_UPDATE(2),

        /**
         * Service Update is in Progress.
         */
        UPDATE_IN_PROGRESS(3);

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

    /**
     * Requirements of Gauge Firmware Service.
     */
    public enum Requirements {

        /**
         * USB power is provided.
         */
        USB(0),

        /**
         * RSOC is enough.
         */
        RSOC(1),

        /**
         * Drone is landed.
         */
        DRONE_STATE(2);

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
        public static Requirements fromValue(int value) {
            return MAP.get(value, null);
        }

        /**
         * Tells whether this enum value is armed in a given bitfield.
         *
         * @param bitfield bitfield to process
         *
         * @returns {@code true} if this enum is in specified bitfield, otherwise {@code false}
         */
        public boolean inBitField(int bitfield) {
            return (bitfield & (1 << value)) != 0;
        }

        /**
         * Applies a function to each armed enum value in a given bitfield.
         *
         * @param bitfield bitfield to process
         * @param func     function to call with each armed enum value
         */
        public static void each(int bitfield, @NonNull Consumer<Requirements> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported Requirements bitfield value " + value);
                    break;
                }
                func.accept(fromValue(value));
                bitfield ^= 1 << value;
            }
        }

        /**
         * Extracts armed enum value(s) from a given bitfield.
         *
         * @param bitfield bitfield to process
         *
         * @return a set containing enum value(s) armed in the specified bitfield
         */
        @NonNull
        public static EnumSet<Requirements> fromBitfield(int bitfield) {
            EnumSet<Requirements> enums = EnumSet.noneOf(Requirements.class);
            each(bitfield, enums::add);
            return enums;
        }

        /**
         * Encodes a set of enum value(s) to a bitfield.
         *
         * @param enums enums to arm in the bitfield
         *
         * @return a bitfield where specified enum value(s) are armed
         */
        public static int toBitField(@NonNull Requirements... enums) {
            int bitField = 0;
            for (Requirements e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Requirements(int value) {
            this.value = value;
        }

        private static final SparseArray<Requirements> MAP;

        static {
            MAP = new SparseArray<>();
            for (Requirements e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Result of Gauge Firmware Service.
     */
    public enum Result {

        /**
         * Result is Success.
         */
        SUCCESS(0),

        /**
         * In Progress.
         */
        IN_PROGRESS(1),

        /**
         * Bad Diagnostic.
         */
        BAD_DIAG(2),

        /**
         * Not Prepared.
         */
        NOT_PREPARED(3),

        /**
         * Requirements not Achieved.
         */
        REQUIREMENTS_NOT_ACHIEVED(4),

        /**
         * Problem with Battery.
         */
        BATTERY_ERROR(5);

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
        public static Result fromValue(int value) {
            return MAP.get(value, null);
        }

        private Result(int value) {
            this.value = value;
        }

        private static final SparseArray<Result> MAP;

        static {
            MAP = new SparseArray<>();
            for (Result e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0xA000;

    /** Uid of status event. */;
    public static final int STATUS_UID = 0x0003;

    /** Uid of progress event. */;
    public static final int PROGRESS_UID = 0x0004;

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
         * @param diag: Diagnostic.
         * @param missingRequirementsBitField: Missing Requirements.
         * @param state: State.
         */
        public default void onStatus(@Nullable Diag diag, int missingRequirementsBitField, @Nullable State state) {}

        /**

         * 
         * @param result: Result.
         * @param percent: Percentage (%), only for [prepare](#160-1).
         */
        public default void onProgress(@Nullable Result result, int percent) {}
    }

    private static void status(Callback cb, int diag, int missingRequirementsBitField, int state) {
        ArsdkFeatureGaugeFwUpdater.Diag enumDiag = ArsdkFeatureGaugeFwUpdater.Diag.fromValue(diag);
        if (enumDiag == null) ULog.e(TAG, "Unsupported ArsdkFeatureGaugeFwUpdater.Diag value " + diag);
        ArsdkFeatureGaugeFwUpdater.State enumState = ArsdkFeatureGaugeFwUpdater.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureGaugeFwUpdater.State value " + state);
        try {
            cb.onStatus(enumDiag, missingRequirementsBitField, enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gauge_fw_updater.status [diag: " + diag + ", missing_requirements: " + missingRequirementsBitField + ", state: " + state + "]", e);
        }
    }

    private static void progress(Callback cb, int result, int percent) {
        ArsdkFeatureGaugeFwUpdater.Result enumResult = ArsdkFeatureGaugeFwUpdater.Result.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureGaugeFwUpdater.Result value " + result);
        try {
            cb.onProgress(enumResult, percent);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gauge_fw_updater.progress [result: " + result + ", percent: " + percent + "]", e);
        }
    }
    
    /**
     * Prepare Update
     */
    public static ArsdkCommand encodePrepare() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodePrepare(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Update
     */
    public static ArsdkCommand encodeUpdate() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeUpdate(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodePrepare(long nativeCmd);
    private static native int nativeEncodeUpdate(long nativeCmd);
}
