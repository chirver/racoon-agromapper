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
 * Thermal_cam feature command/event interface.
 */
public class ArsdkFeatureThermalCam {

    /**
     * Camera state.
     */
    public enum State {

        /**
         * Camera is activated.
         */
        ACTIVATED(0),

        /**
         * Camera is deactivated.
         */
        DEACTIVATED(1),

        /**
         * Activation is pending.
         */
        PENDING(2);

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
     * Thermal range.
     */
    public enum Range {

        /**
         * High range (from 0 to 400°C).
         */
        HIGH(0),

        /**
         * Low range (from 0 to 120°C).
         */
        LOW(1);

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
        public static Range fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Range> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Range bitfield value " + value);
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
        public static EnumSet<Range> fromBitfield(int bitfield) {
            EnumSet<Range> enums = EnumSet.noneOf(Range.class);
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
        public static int toBitField(@NonNull Range... enums) {
            int bitField = 0;
            for (Range e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Range(int value) {
            this.value = value;
        }

        private static final SparseArray<Range> MAP;

        static {
            MAP = new SparseArray<>();
            for (Range e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Battery state.
     */
    public enum ChargingState {

        /**
         * Power cable is unplugged.
         */
        NOT_CHARGING(0),

        /**
         * Power save mode when battery is criticaly low.
         */
        CHARGING_ONLY(1),

        /**
         * Battery is charging correctly.
         */
        CHARGING(2),

        /**
         * Charging state is unknown.
         */
        UNKNOWN(3);

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
        public static ChargingState fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargingState(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargingState> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargingState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Camera Thermal sensor flat field calibration(FFC) status.
     */
    public enum FlatFieldCalibrationState {

        /**
         * Sensor is calibrated.
         */
        CALIBRATED(0),

        /**
         * Sensor needs to be calibrated.
         */
        CALIBRATION_REQUIRED(1),

        /**
         * Sensor calibration is in progress.
         */
        CALIBRATION_IN_PROGRESS(2),

        /**
         * Sensor FFC state is unknown.
         */
        CALIBRATION_UNKNOWN(3);

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
        public static FlatFieldCalibrationState fromValue(int value) {
            return MAP.get(value, null);
        }

        private FlatFieldCalibrationState(int value) {
            this.value = value;
        }

        private static final SparseArray<FlatFieldCalibrationState> MAP;

        static {
            MAP = new SparseArray<>();
            for (FlatFieldCalibrationState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * flat field calibration(FFC) mode.
     */
    public enum FlatFieldCalibrationMode {

        /**
         * calibration occurs automatically when needed.
         */
        AUTOMATIC(0),

        /**
         * calibration occurs on user request only.
         */
        MANUAL(1);

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
        public static FlatFieldCalibrationMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private FlatFieldCalibrationMode(int value) {
            this.value = value;
        }

        private static final SparseArray<FlatFieldCalibrationMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (FlatFieldCalibrationMode e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8E00;

    /** Uid of camera_state event. */;
    public static final int CAMERA_STATE_UID = 0x0003;

    /** Uid of sensitivity event. */;
    public static final int SENSITIVITY_UID = 0x0005;

    /** Uid of calibration_infos event. */;
    public static final int CALIBRATION_INFOS_UID = 0x0006;

    /** Uid of charging_status event. */;
    public static final int CHARGING_STATUS_UID = 0x0007;

    /** Uid of flat_field_calibration_status event. */;
    public static final int FLAT_FIELD_CALIBRATION_STATUS_UID = 0x0009;

    /** Uid of flat_field_calibration_mode event. */;
    public static final int FLAT_FIELD_CALIBRATION_MODE_UID = 0x000B;

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
         * Camera state.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param state:
         * @param listFlagsBitField:
         */
        public default void onCameraState(int camId, @Nullable State state, int listFlagsBitField) {}

        /**
         * Thermal cam sensitivity range.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param currentRange:
         * @param availableRangesBitField:
         * @param listFlagsBitField:
         */
        public default void onSensitivity(int camId, @Nullable Range currentRange, int availableRangesBitField, int listFlagsBitField) {}

        /**
         * Visible camera position relative to the drone. The thermal camera is considered at an ideal position.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param roll: Euler angle roll in degree difference between visible cam and thermal cam.
         * @param pitch: Euler angle pitch in degree difference between visible cam and thermal cam.
         * @param yaw: Euler angle yaw in degree difference between visible cam and thermal cam.
         * @param listFlagsBitField:
         */
        public default void onCalibrationInfos(int camId, float roll, float pitch, float yaw, int listFlagsBitField) {}

        /**
         * Thermal cam charging status.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param state:
         * @param listFlagsBitField:
         */
        public default void onChargingStatus(int camId, @Nullable ChargingState state, int listFlagsBitField) {}

        /**
         * Thermal cam FFC status.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param state:
         * @param listFlagsBitField:
         */
        public default void onFlatFieldCalibrationStatus(int camId, @Nullable FlatFieldCalibrationState state, int listFlagsBitField) {}

        /**
         * Thermal cam FFC mode.
         * 
         * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
         * @param mode:
         * @param listFlagsBitField:
         */
        public default void onFlatFieldCalibrationMode(int camId, @Nullable FlatFieldCalibrationMode mode, int listFlagsBitField) {}
    }

    private static void cameraState(Callback cb, int camId, int state, int listFlagsBitField) {
        ArsdkFeatureThermalCam.State enumState = ArsdkFeatureThermalCam.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermalCam.State value " + state);
        try {
            cb.onCameraState(camId, enumState, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.camera_state [cam_id: " + camId + ", state: " + state + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void sensitivity(Callback cb, int camId, int currentRange, int availableRangesBitField, int listFlagsBitField) {
        ArsdkFeatureThermalCam.Range enumCurrentrange = ArsdkFeatureThermalCam.Range.fromValue(currentRange);
        if (enumCurrentrange == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermalCam.Range value " + currentRange);
        try {
            cb.onSensitivity(camId, enumCurrentrange, availableRangesBitField, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.sensitivity [cam_id: " + camId + ", current_range: " + currentRange + ", available_ranges: " + availableRangesBitField + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void calibrationInfos(Callback cb, int camId, float roll, float pitch, float yaw, int listFlagsBitField) {
        try {
            cb.onCalibrationInfos(camId, roll, pitch, yaw, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.calibration_infos [cam_id: " + camId + ", roll: " + roll + ", pitch: " + pitch + ", yaw: " + yaw + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void chargingStatus(Callback cb, int camId, int state, int listFlagsBitField) {
        ArsdkFeatureThermalCam.ChargingState enumState = ArsdkFeatureThermalCam.ChargingState.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermalCam.ChargingState value " + state);
        try {
            cb.onChargingStatus(camId, enumState, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.charging_status [cam_id: " + camId + ", state: " + state + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void flatFieldCalibrationStatus(Callback cb, int camId, int state, int listFlagsBitField) {
        ArsdkFeatureThermalCam.FlatFieldCalibrationState enumState = ArsdkFeatureThermalCam.FlatFieldCalibrationState.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermalCam.FlatFieldCalibrationState value " + state);
        try {
            cb.onFlatFieldCalibrationStatus(camId, enumState, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.flat_field_calibration_status [cam_id: " + camId + ", state: " + state + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void flatFieldCalibrationMode(Callback cb, int camId, int mode, int listFlagsBitField) {
        ArsdkFeatureThermalCam.FlatFieldCalibrationMode enumMode = ArsdkFeatureThermalCam.FlatFieldCalibrationMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermalCam.FlatFieldCalibrationMode value " + mode);
        try {
            cb.onFlatFieldCalibrationMode(camId, enumMode, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal_cam.flat_field_calibration_mode [cam_id: " + camId + ", mode: " + mode + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }
    
    /**
     * Activate a given thermal camera. Activating a camera may deactivate others on some drones.
     * 
     * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeActivate(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeActivate(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Deactivate a given thermal camera.
     * 
     * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeDeactivate(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeDeactivate(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Set the thermal camera sensitivity range.
     * 
     * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
     * @param range:
     */
    public static ArsdkCommand encodeSetSensitivity(int camId, @NonNull Range range) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetSensitivity(cmd.getNativePtr(), camId, range.value);
        return cmd;
    }

    /**
     * Request the sensor to start thermal flat field calibration
     * 
     * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeFlatFieldCalibrate(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeFlatFieldCalibrate(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Set the FFC mode.
     * 
     * @param camId: Thermal camera id, as given in the [connected accessories](#1-33-0) event.
     * @param mode:
     */
    public static ArsdkCommand encodeSetFlatFieldCalibrationMode(int camId, @NonNull FlatFieldCalibrationMode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetFlatFieldCalibrationMode(cmd.getNativePtr(), camId, mode.value);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeActivate(long nativeCmd, int cam_id);
    private static native int nativeEncodeDeactivate(long nativeCmd, int cam_id);
    private static native int nativeEncodeSetSensitivity(long nativeCmd, int cam_id, int range);
    private static native int nativeEncodeFlatFieldCalibrate(long nativeCmd, int cam_id);
    private static native int nativeEncodeSetFlatFieldCalibrationMode(long nativeCmd, int cam_id, int mode);
}
