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
 * Security_edition feature command/event interface.
 */
public class ArsdkFeatureSecurityEdition {

    /**
     * Log storage state.
     */
    public enum LogStorageState {

        /**
         * Log storage is disabled.
         */
        DISABLED(0),

        /**
         * Log storage is enabled.
         */
        ENABLED(1);

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
        public static LogStorageState fromValue(int value) {
            return MAP.get(value, null);
        }

        private LogStorageState(int value) {
            this.value = value;
        }

        private static final SparseArray<LogStorageState> MAP;

        static {
            MAP = new SparseArray<>();
            for (LogStorageState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Supported capabilities.
     */
    public enum SupportedCapabilities {

        /**
         * Possibility to deactivate the logs.
         */
        DEACTIVATE_LOGS(0);

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
        public static SupportedCapabilities fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SupportedCapabilities> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 1) {
                    ULog.e(TAG, "Unsupported SupportedCapabilities bitfield value " + value);
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
        public static EnumSet<SupportedCapabilities> fromBitfield(int bitfield) {
            EnumSet<SupportedCapabilities> enums = EnumSet.noneOf(SupportedCapabilities.class);
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
        public static int toBitField(@NonNull SupportedCapabilities... enums) {
            int bitField = 0;
            for (SupportedCapabilities e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SupportedCapabilities(int value) {
            this.value = value;
        }

        private static final SparseArray<SupportedCapabilities> MAP;

        static {
            MAP = new SparseArray<>();
            for (SupportedCapabilities e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0xA900;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0001;

    /** Uid of log_storage_state event. */;
    public static final int LOG_STORAGE_STATE_UID = 0x0003;

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
         * @param supportedCapabilitiesBitField: Supported capabilities.
         */
        public default void onCapabilities(int supportedCapabilitiesBitField) {}

        /**

         * 
         * @param logStorageState: Logs state.
         */
        public default void onLogStorageState(@Nullable LogStorageState logStorageState) {}
    }

    private static void capabilities(Callback cb, int supportedCapabilitiesBitField) {
        try {
            cb.onCapabilities(supportedCapabilitiesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: security_edition.capabilities [supported_capabilities: " + supportedCapabilitiesBitField + "]", e);
        }
    }

    private static void logStorageState(Callback cb, int logStorageState) {
        ArsdkFeatureSecurityEdition.LogStorageState enumLogstoragestate = ArsdkFeatureSecurityEdition.LogStorageState.fromValue(logStorageState);
        if (enumLogstoragestate == null) ULog.e(TAG, "Unsupported ArsdkFeatureSecurityEdition.LogStorageState value " + logStorageState);
        try {
            cb.onLogStorageState(enumLogstoragestate);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: security_edition.log_storage_state [log_storage_state: " + logStorageState + "]", e);
        }
    }
    
    /**
     * Deactivate logs.
     */
    public static ArsdkCommand encodeDeactivateLogs() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeDeactivateLogs(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeDeactivateLogs(long nativeCmd);
}
