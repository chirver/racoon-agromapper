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
 * Precise_home feature command/event interface.
 */
public class ArsdkFeaturePreciseHome {

    /**
     * Precise home mode.
     */
    public enum Mode {

        /**
         * Precise home is disabled.
         */
        DISABLED(0),

        /**
         * Precise home is enabled, in standard mode.
         */
        STANDARD(1);

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
        public static Mode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Mode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Mode bitfield value " + value);
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
        public static EnumSet<Mode> fromBitfield(int bitfield) {
            EnumSet<Mode> enums = EnumSet.noneOf(Mode.class);
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
        public static int toBitField(@NonNull Mode... enums) {
            int bitField = 0;
            for (Mode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Mode(int value) {
            this.value = value;
        }

        private static final SparseArray<Mode> MAP;

        static {
            MAP = new SparseArray<>();
            for (Mode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Precise home feature state.
     */
    public enum State {

        /**
         * It is currently impossible to activate precise home if the drone lands.
         */
        UNAVAILABLE(0),

        /**
         * Precise home will be activated if the drone lands.
         */
        AVAILABLE(1),

        /**
         * Precise home is currently active (drone is landing).
         */
        ACTIVE(2);

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
    public static final int UID = 0x9700;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0001;

    /** Uid of mode event. */;
    public static final int MODE_UID = 0x0002;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x0004;

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
         * @param modesBitField: Supported precise home modes.
         */
        public default void onCapabilities(int modesBitField) {}

        /**

         * 
         * @param mode: Current precise home mode
         */
        public default void onMode(@Nullable Mode mode) {}

        /**

         * 
         * @param state: Current precise home state.
         */
        public default void onState(@Nullable State state) {}
    }

    private static void capabilities(Callback cb, int modesBitField) {
        try {
            cb.onCapabilities(modesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: precise_home.capabilities [modes: " + modesBitField + "]", e);
        }
    }

    private static void mode(Callback cb, int mode) {
        ArsdkFeaturePreciseHome.Mode enumMode = ArsdkFeaturePreciseHome.Mode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeaturePreciseHome.Mode value " + mode);
        try {
            cb.onMode(enumMode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: precise_home.mode [mode: " + mode + "]", e);
        }
    }

    private static void state(Callback cb, int state) {
        ArsdkFeaturePreciseHome.State enumState = ArsdkFeaturePreciseHome.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeaturePreciseHome.State value " + state);
        try {
            cb.onState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: precise_home.state [state: " + state + "]", e);
        }
    }
    
    /**
     * Configures precise home mode.
     * 
     * @param mode: Requested precise home mode.
     */
    public static ArsdkCommand encodeSetMode(@NonNull Mode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetMode(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetMode(long nativeCmd, int mode);
}
