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
 * Rc feature command/event interface.
 */
public class ArsdkFeatureRc {

    /**
     * RC Receiver state.
     */
    public enum ReceiverState {

        /**
         * RC drone receiver connected to a RC.
         */
        CONNECTED(0),

        /**
         * RC drone receiver not connected to a RC.
         */
        DISCONNECTED(1);

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
        public static ReceiverState fromValue(int value) {
            return MAP.get(value, null);
        }

        private ReceiverState(int value) {
            this.value = value;
        }

        private static final SparseArray<ReceiverState> MAP;

        static {
            MAP = new SparseArray<>();
            for (ReceiverState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Channel action.
     */
    public enum ChannelAction {

        /**
         * Invalid/Unused channel.
         */
        INVALID(0),

        /**
         * Roll axis channel.
         */
        ROLL(1),

        /**
         * Pitch axis channel.
         */
        PITCH(2),

        /**
         * Yaw axis channel.
         */
        YAW(3),

        /**
         * Gaz / Throttle / Altitude axis channel.
         */
        GAZ(4),

        /**
         * Takeoff / Land channel.
         */
        TAKEOFF_LAND(5),

        /**
         * Emergency channel.
         */
        EMERGENCY(6),

        /**
         * Return Home channel.
         */
        RETURN_HOME(7),

        /**
         * RC Piloting mode. Auto mode: used for doing flightplans and for assisted flying with a non-RC controller.
         * Easy manual mode: used for assisted flying with a RC controller. Manual mode: used for non-assisted flying
         * with a RC controller and for directly controlling the servomotors.
         */
        PILOTING_MODE(8),

        /**
         * RC take control. When take control is activated the RC controller, if available, becomes the main controller.
         */
        TAKE_CONTROL(9);

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
        public static ChannelAction fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<ChannelAction> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 10) {
                    ULog.e(TAG, "Unsupported ChannelAction bitfield value " + value);
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
        public static EnumSet<ChannelAction> fromBitfield(int bitfield) {
            EnumSet<ChannelAction> enums = EnumSet.noneOf(ChannelAction.class);
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
        public static int toBitField(@NonNull ChannelAction... enums) {
            int bitField = 0;
            for (ChannelAction e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private ChannelAction(int value) {
            this.value = value;
        }

        private static final SparseArray<ChannelAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChannelAction e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Calibration type.
     */
    public enum CalibrationType {

        /**
         * No calibration.
         */
        NONE(0),

        /**
         * All neutral channels calibration.
         */
        NEUTRAL(1),

        /**
         * Min/Max specific channel calibration.
         */
        MIN_MAX(2);

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
        public static CalibrationType fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationType(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationType> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Channel physical type.
     */
    public enum ChannelType {

        /**
         * Invalid channel physical type.
         */
        INVALID(0),

        /**
         * Signed axis type.
         */
        SIGNED_AXIS(1),

        /**
         * Unsigned axis type.
         */
        UNSIGNED_AXIS(2),

        /**
         * Monostable button type.
         */
        MONOSTABLE_BUTTON(3),

        /**
         * Bistable button type.
         */
        BISTABLE_BUTTON(4),

        /**
         * Tristate button type.
         */
        TRISTATE_BUTTON(5),

        /**
         * Rotary button type.
         */
        ROTARY_BUTTON(6);

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
        public static ChannelType fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<ChannelType> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 7) {
                    ULog.e(TAG, "Unsupported ChannelType bitfield value " + value);
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
        public static EnumSet<ChannelType> fromBitfield(int bitfield) {
            EnumSet<ChannelType> enums = EnumSet.noneOf(ChannelType.class);
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
        public static int toBitField(@NonNull ChannelType... enums) {
            int bitField = 0;
            for (ChannelType e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private ChannelType(int value) {
            this.value = value;
        }

        private static final SparseArray<ChannelType> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChannelType e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8800;

    /** Uid of receiver_state event. */;
    public static final int RECEIVER_STATE_UID = 0x0001;

    /** Uid of channels_monitor_state event. */;
    public static final int CHANNELS_MONITOR_STATE_UID = 0x0003;

    /** Uid of channel_value event. */;
    public static final int CHANNEL_VALUE_UID = 0x0004;

    /** Uid of calibration_state event. */;
    public static final int CALIBRATION_STATE_UID = 0x0005;

    /** Uid of channel_action_item event. */;
    public static final int CHANNEL_ACTION_ITEM_UID = 0x000C;

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
         * State of drone RC receiver
         * 
         * @param state: current state.
         * @param protocol: Protocol used by RC.
         * @param enabled: 1 if enabled, 0 otherwise. If enabled, drone will apply values sent by RC receiver.
         */
        public default void onReceiverState(@Nullable ReceiverState state, String protocol, int enabled) {}

        /**
         * RC Channel monitor state sent by drone
         * 
         * @param state: 1 if enabled, 0 if disabled
         */
        public default void onChannelsMonitorState(int state) {}

        /**
         * RC Channel value sent by drone
         * 
         * @param id: RC channel id.
         * @param action: RC channel action.
         * @param value: RC channel value.
         * @param listFlagsBitField: Item attribute Bitfield. First: indicate it's the first element of the list. Last:
         * indicate it's the last element of the list.
         */
        public default void onChannelValue(int id, @Nullable ChannelAction action, int value, int listFlagsBitField) {}

        /**
         * RC Channels calibration state sent by drone.
         * 
         * @param calibrationType: Current calibration type set to 'none' if no calibration in progress.
         * @param channelAction: Current channel action calibration Only used when calibration_type=min_max.
         * @param requiredBitField: bitfield of required channel actions. Neutral channels calibration is always
         * required.
         * @param calibratedBitField: bitfield of calibrated channel actions.
         * @param neutralCalibrated: 1 if neutral channels are calibrated, 0 otherwise.
         */
        public default void onCalibrationState(@Nullable CalibrationType calibrationType, @Nullable ChannelAction channelAction, int requiredBitField, int calibratedBitField, int neutralCalibrated) {}

        /**
         * Channel action supported by drone.
         * 
         * @param action: Channel action.
         * @param supportedTypeBitField: Bitfield of supported channel types.
         * @param calibratedType: Calibrated action channel type (none if not calibrated).
         * @param inverted: 1 if inverted, 0 otherwise.
         */
        public default void onChannelActionItem(@Nullable ChannelAction action, int supportedTypeBitField, @Nullable ChannelType calibratedType, int inverted) {}
    }

    private static void receiverState(Callback cb, int state, String protocol, int enabled) {
        ArsdkFeatureRc.ReceiverState enumState = ArsdkFeatureRc.ReceiverState.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.ReceiverState value " + state);
        try {
            cb.onReceiverState(enumState, protocol, enabled);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rc.receiver_state [state: " + state + ", protocol: " + protocol + ", enabled: " + enabled + "]", e);
        }
    }

    private static void channelsMonitorState(Callback cb, int state) {
        try {
            cb.onChannelsMonitorState(state);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rc.channels_monitor_state [state: " + state + "]", e);
        }
    }

    private static void channelValue(Callback cb, int id, int action, int value, int listFlagsBitField) {
        ArsdkFeatureRc.ChannelAction enumAction = ArsdkFeatureRc.ChannelAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.ChannelAction value " + action);
        try {
            cb.onChannelValue(id, enumAction, value, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rc.channel_value [id: " + id + ", action: " + action + ", value: " + value + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void calibrationState(Callback cb, int calibrationType, int channelAction, int requiredBitField, int calibratedBitField, int neutralCalibrated) {
        ArsdkFeatureRc.CalibrationType enumCalibrationtype = ArsdkFeatureRc.CalibrationType.fromValue(calibrationType);
        if (enumCalibrationtype == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.CalibrationType value " + calibrationType);
        ArsdkFeatureRc.ChannelAction enumChannelaction = ArsdkFeatureRc.ChannelAction.fromValue(channelAction);
        if (enumChannelaction == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.ChannelAction value " + channelAction);
        try {
            cb.onCalibrationState(enumCalibrationtype, enumChannelaction, requiredBitField, calibratedBitField, neutralCalibrated);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rc.calibration_state [calibration_type: " + calibrationType + ", channel_action: " + channelAction + ", required: " + requiredBitField + ", calibrated: " + calibratedBitField + ", neutral_calibrated: " + neutralCalibrated + "]", e);
        }
    }

    private static void channelActionItem(Callback cb, int action, int supportedTypeBitField, int calibratedType, int inverted) {
        ArsdkFeatureRc.ChannelAction enumAction = ArsdkFeatureRc.ChannelAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.ChannelAction value " + action);
        ArsdkFeatureRc.ChannelType enumCalibratedtype = ArsdkFeatureRc.ChannelType.fromValue(calibratedType);
        if (enumCalibratedtype == null) ULog.e(TAG, "Unsupported ArsdkFeatureRc.ChannelType value " + calibratedType);
        try {
            cb.onChannelActionItem(enumAction, supportedTypeBitField, enumCalibratedtype, inverted);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rc.channel_action_item [action: " + action + ", supported_type: " + supportedTypeBitField + ", calibrated_type: " + calibratedType + ", inverted: " + inverted + "]", e);
        }
    }
    
    /**
     * Enable or Disable RC channels monitoring. If enable, drone will send periodically rc channel value events.
     * 
     * @param enable: 1 for enable / 0 to disable
     */
    public static ArsdkCommand encodeMonitorChannels(int enable) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeMonitorChannels(cmd.getNativePtr(), enable);
        return cmd;
    }

    /**
     * Start a calibration.
     * 
     * @param calibrationType: Type of calibration.
     * @param channelAction: Channel action. only used when calibration_type=min_max.
     * @param channelType: Channel type. only used when calibration_type=min_max.
     */
    public static ArsdkCommand encodeStartCalibration(@NonNull CalibrationType calibrationType, @NonNull ChannelAction channelAction, @NonNull ChannelType channelType) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartCalibration(cmd.getNativePtr(), calibrationType.value, channelAction.value, channelType.value);
        return cmd;
    }

    /**
     * Invert a RC channel values
     * 
     * @param action: Channel action.
     * @param flag: 1 to invert channel 0 to restore channel.
     */
    public static ArsdkCommand encodeInvertChannel(@NonNull ChannelAction action, int flag) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeInvertChannel(cmd.getNativePtr(), action.value, flag);
        return cmd;
    }

    /**
     * Abort current calibration.
     */
    public static ArsdkCommand encodeAbortCalibration() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeAbortCalibration(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Reset calibration to default values.
     */
    public static ArsdkCommand encodeResetCalibration() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetCalibration(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Enable or disable RC receiver. If enable, drone will apply values sent by RC receiver.
     * 
     * @param enable: 1 for enable / 0 to disable
     */
    public static ArsdkCommand encodeEnableReceiver(int enable) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeEnableReceiver(cmd.getNativePtr(), enable);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeMonitorChannels(long nativeCmd, int enable);
    private static native int nativeEncodeStartCalibration(long nativeCmd, int calibration_type, int channel_action, int channel_type);
    private static native int nativeEncodeInvertChannel(long nativeCmd, int action, int flag);
    private static native int nativeEncodeAbortCalibration(long nativeCmd);
    private static native int nativeEncodeResetCalibration(long nativeCmd);
    private static native int nativeEncodeEnableReceiver(long nativeCmd, int enable);
}
