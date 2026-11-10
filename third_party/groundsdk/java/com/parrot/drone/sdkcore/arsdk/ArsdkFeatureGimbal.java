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
 * Gimbal feature command/event interface.
 */
public class ArsdkFeatureGimbal {

    /**
     * Gimbal model.
     */
    public enum Model {

        /**
         * Main gimbal. Only one gimbal of this kind is allowed at a time on a drone.
         */
        MAIN(0);

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
        public static Model fromValue(int value) {
            return MAP.get(value, null);
        }

        private Model(int value) {
            this.value = value;
        }

        private static final SparseArray<Model> MAP;

        static {
            MAP = new SparseArray<>();
            for (Model e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Feature current state.
     */
    public enum State {

        /**
         * Feature is not currently active.
         */
        INACTIVE(0),

        /**
         * Feature is currently active.
         */
        ACTIVE(1);

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
     * Gimbal error.
     */
    public enum Error {

        /**
         * Calibration error. Verify that nothing is blocking the gimbal movement.
         */
        CALIBRATION_ERROR(0),

        /**
         * Overload error. Verify that nothing is blocking the gimbal movement.
         */
        OVERLOAD_ERROR(1),

        /**
         * Communication error. Wait for retry.
         */
        COMM_ERROR(2),

        /**
         * Critical error. Drone must be restarted.
         */
        CRITICAL_ERROR(3);

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
        public static Error fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Error> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported Error bitfield value " + value);
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
        public static EnumSet<Error> fromBitfield(int bitfield) {
            EnumSet<Error> enums = EnumSet.noneOf(Error.class);
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
        public static int toBitField(@NonNull Error... enums) {
            int bitField = 0;
            for (Error e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Error(int value) {
            this.value = value;
        }

        private static final SparseArray<Error> MAP;

        static {
            MAP = new SparseArray<>();
            for (Error e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Calibration state.
     */
    public enum CalibrationState {

        /**
         * Calibration is required.
         */
        REQUIRED(0),

        /**
         * Calibration is in progress.
         */
        IN_PROGRESS(1),

        /**
         * Calibration is ok.
         */
        OK(2);

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
        public static CalibrationState fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationState(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationState> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Calibration result.
     */
    public enum CalibrationResult {

        /**
         * Calibration completed successfully.
         */
        SUCCESS(0),

        /**
         * Calibration failed.
         */
        FAILURE(1),

        /**
         * Calibration canceled, with command `cancel_calibration`.
         */
        CANCELED(2);

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
        public static CalibrationResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationResult(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Axis.
     */
    public enum Axis {

        /**
         * Yaw axis.
         */
        YAW(0),

        /**
         * Pitch axis.
         */
        PITCH(1),

        /**
         * Roll axis.
         */
        ROLL(2);

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
        public static Axis fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Axis> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported Axis bitfield value " + value);
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
        public static EnumSet<Axis> fromBitfield(int bitfield) {
            EnumSet<Axis> enums = EnumSet.noneOf(Axis.class);
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
        public static int toBitField(@NonNull Axis... enums) {
            int bitField = 0;
            for (Axis e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Axis(int value) {
            this.value = value;
        }

        private static final SparseArray<Axis> MAP;

        static {
            MAP = new SparseArray<>();
            for (Axis e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Gimbal control mode.
     */
    public enum ControlMode {

        /**
         * Attitude is set by giving a position.
         */
        POSITION(0),

        /**
         * Attitude is set by giving a velocity.
         */
        VELOCITY(1);

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
        public static ControlMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private ControlMode(int value) {
            this.value = value;
        }

        private static final SparseArray<ControlMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (ControlMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Frame of reference.
     */
    public enum FrameOfReference {

        /**
         * None.
         */
        NONE(0),

        /**
         * Euler angles define gimbal attitude in the drone frame of reference, where, as seen from the drone: - roll
         * axis points forward - pitch axis points on the right hand side - yaw axis points downward.
         */
        RELATIVE(1),

        /**
         * Euler angles define gimbal attitude in the Earth frame of reference, where: - roll axis points North - pitch
         * axis points East - yaw axis points toward the center of the Earth.
         */
        ABSOLUTE(2);

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
        public static FrameOfReference fromValue(int value) {
            return MAP.get(value, null);
        }

        private FrameOfReference(int value) {
            this.value = value;
        }

        private static final SparseArray<FrameOfReference> MAP;

        static {
            MAP = new SparseArray<>();
            for (FrameOfReference e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9400;

    /** Uid of gimbal_capabilities event. */;
    public static final int GIMBAL_CAPABILITIES_UID = 0x0001;

    /** Uid of relative_attitude_bounds event. */;
    public static final int RELATIVE_ATTITUDE_BOUNDS_UID = 0x0002;

    /** Uid of max_speed event. */;
    public static final int MAX_SPEED_UID = 0x0003;

    /** Uid of attitude event. */;
    public static final int ATTITUDE_UID = 0x0006;

    /** Uid of axis_lock_state event. */;
    public static final int AXIS_LOCK_STATE_UID = 0x0007;

    /** Uid of offsets event. */;
    public static final int OFFSETS_UID = 0x0008;

    /** Uid of absolute_attitude_bounds event. */;
    public static final int ABSOLUTE_ATTITUDE_BOUNDS_UID = 0x000A;

    /** Uid of calibration_state event. */;
    public static final int CALIBRATION_STATE_UID = 0x000E;

    /** Uid of calibration_result event. */;
    public static final int CALIBRATION_RESULT_UID = 0x0010;

    /** Uid of alert event. */;
    public static final int ALERT_UID = 0x0011;

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
         * @param gimbalId: id of the gimbal. Gimbal id is unique and persistent: the same gimbal model on a same drone
         * model always has the same id. Main/Built-in gimbal has id zero.
         * @param model: Camera model.
         * @param axesBitField: Bitfield of axes that can be controlled. If bit is set to 1, it means that the axis can
         * be controlled.
         */
        public default void onGimbalCapabilities(int gimbalId, @Nullable Model model, int axesBitField) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param minYaw: Minimum yaw value, in degrees, in the relative frame of reference
         * @param maxYaw: Maximum yaw value, in degrees, in the relative frame of reference
         * @param minPitch: Minimum pitch value, in degrees, in the relative frame of reference
         * @param maxPitch: Maximum pitch value, in degrees, in the relative frame of reference
         * @param minRoll: Minimum roll value, in degrees, in the relative frame of reference
         * @param maxRoll: Maximum roll value, in degrees, in the relative frame of reference
         */
        public default void onRelativeAttitudeBounds(int gimbalId, float minYaw, float maxYaw, float minPitch, float maxPitch, float minRoll, float maxRoll) {}

        /**

         * 
         * @param gimbalId: id of the gimbal. Gimbal id is unique and persistent: the same gimbal model on a same drone
         * model always has the same id. Main/Built-in gimbal has id zero.
         * @param minBoundYaw: Lower bound of the max yaw speed, in degrees per seconds
         * @param maxBoundYaw: Upper bound of the max yaw speed, in degrees per seconds
         * @param currentYaw: Maximum yaw speed value, in degrees per seconds
         * @param minBoundPitch: Lower bound of the max pitch speed, in degrees per seconds
         * @param maxBoundPitch: Upper bound of the max pitch speed, in degrees per seconds
         * @param currentPitch: Maximum pitch speed value, in degrees per seconds
         * @param minBoundRoll: Lower bound of the max roll speed, in degrees per seconds
         * @param maxBoundRoll: Upper bound of the max roll speed, in degrees per seconds
         * @param currentRoll: Maximum roll speed value, in degrees per seconds
         */
        public default void onMaxSpeed(int gimbalId, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param yawFrameOfReference: Current frame of reference used for the yaw axis.
         * @param pitchFrameOfReference: Current frame of reference used for the pitch axis.
         * @param rollFrameOfReference: Current frame of reference used for the roll axis.
         * @param yawRelative: Yaw orientation of the gimbal in the relative frame of reference, in degrees.
         * @param pitchRelative: Pitch orientation of the gimbal in the relative frame of reference, in degrees.
         * @param rollRelative: Roll orientation of the gimbal in the relative frame of reference, in degrees.
         * @param yawAbsolute: Yaw orientation of the gimbal in the absolute frame of reference, in degrees.
         * @param pitchAbsolute: Pitch orientation of the gimbal in the absolute frame of reference, in degrees.
         * @param rollAbsolute: Roll orientation of the gimbal in the absolute frame of reference, in degrees.
         */
        public default void onAttitude(int gimbalId, @Nullable FrameOfReference yawFrameOfReference, @Nullable FrameOfReference pitchFrameOfReference, @Nullable FrameOfReference rollFrameOfReference, float yawRelative, float pitchRelative, float rollRelative, float yawAbsolute, float pitchAbsolute, float rollAbsolute) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param lockedBitField: Bitfield indicating if each axis is temporarily locked. Bits set to 1 are locked.
         */
        public default void onAxisLockState(int gimbalId, int lockedBitField) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param updateState: offset update states.
         * @param minBoundYaw: Lower bound of the offset that can be set on the yaw axis, in degrees
         * @param maxBoundYaw: Upper bound of the offset that can be set on the yaw axis, in degrees
         * @param currentYaw: Current offset applied to the yaw axis, in degrees
         * @param minBoundPitch: Lower bound of the offset that can be set on the pitch axis, in degrees
         * @param maxBoundPitch: Upper bound of the offset that can be set on the pitch axis, in degrees
         * @param currentPitch: Current offset applied to the pitch axis, in degrees
         * @param minBoundRoll: Lower bound of the offset that can be set on the roll axis, in degrees
         * @param maxBoundRoll: Upper bound of the offset that can be set on the roll axis, in degrees
         * @param currentRoll: Current offset applied to the roll axis, in degrees
         */
        public default void onOffsets(int gimbalId, @Nullable State updateState, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param minYaw: Minimum yaw value, in degrees, in the absolute frame of reference
         * @param maxYaw: Maximum yaw value, in degrees, in the absolute frame of reference
         * @param minPitch: Minimum pitch value, in degrees, in the absolute frame of reference
         * @param maxPitch: Maximum pitch value, in degrees, in the absolute frame of reference
         * @param minRoll: Minimum roll value, in degrees, in the absolute frame of reference
         * @param maxRoll: Maximum roll value, in degrees, in the absolute frame of reference
         */
        public default void onAbsoluteAttitudeBounds(int gimbalId, float minYaw, float maxYaw, float minPitch, float maxPitch, float minRoll, float maxRoll) {}

        /**

         * 
         * @param state: Current state
         * @param gimbalId: id of the gimbal.
         */
        public default void onCalibrationState(@Nullable CalibrationState state, int gimbalId) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param result: Calibration result.
         */
        public default void onCalibrationResult(int gimbalId, @Nullable CalibrationResult result) {}

        /**

         * 
         * @param gimbalId: id of the gimbal.
         * @param errorBitField: Error bitfield.
         */
        public default void onAlert(int gimbalId, int errorBitField) {}
    }

    private static void gimbalCapabilities(Callback cb, int gimbalId, int model, int axesBitField) {
        ArsdkFeatureGimbal.Model enumModel = ArsdkFeatureGimbal.Model.fromValue(model);
        if (enumModel == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.Model value " + model);
        try {
            cb.onGimbalCapabilities(gimbalId, enumModel, axesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.gimbal_capabilities [gimbal_id: " + gimbalId + ", model: " + model + ", axes: " + axesBitField + "]", e);
        }
    }

    private static void relativeAttitudeBounds(Callback cb, int gimbalId, float minYaw, float maxYaw, float minPitch, float maxPitch, float minRoll, float maxRoll) {
        try {
            cb.onRelativeAttitudeBounds(gimbalId, minYaw, maxYaw, minPitch, maxPitch, minRoll, maxRoll);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.relative_attitude_bounds [gimbal_id: " + gimbalId + ", min_yaw: " + minYaw + ", max_yaw: " + maxYaw + ", min_pitch: " + minPitch + ", max_pitch: " + maxPitch + ", min_roll: " + minRoll + ", max_roll: " + maxRoll + "]", e);
        }
    }

    private static void maxSpeed(Callback cb, int gimbalId, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {
        try {
            cb.onMaxSpeed(gimbalId, minBoundYaw, maxBoundYaw, currentYaw, minBoundPitch, maxBoundPitch, currentPitch, minBoundRoll, maxBoundRoll, currentRoll);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.max_speed [gimbal_id: " + gimbalId + ", min_bound_yaw: " + minBoundYaw + ", max_bound_yaw: " + maxBoundYaw + ", current_yaw: " + currentYaw + ", min_bound_pitch: " + minBoundPitch + ", max_bound_pitch: " + maxBoundPitch + ", current_pitch: " + currentPitch + ", min_bound_roll: " + minBoundRoll + ", max_bound_roll: " + maxBoundRoll + ", current_roll: " + currentRoll + "]", e);
        }
    }

    private static void attitude(Callback cb, int gimbalId, int yawFrameOfReference, int pitchFrameOfReference, int rollFrameOfReference, float yawRelative, float pitchRelative, float rollRelative, float yawAbsolute, float pitchAbsolute, float rollAbsolute) {
        ArsdkFeatureGimbal.FrameOfReference enumYawframeofreference = ArsdkFeatureGimbal.FrameOfReference.fromValue(yawFrameOfReference);
        if (enumYawframeofreference == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.FrameOfReference value " + yawFrameOfReference);
        ArsdkFeatureGimbal.FrameOfReference enumPitchframeofreference = ArsdkFeatureGimbal.FrameOfReference.fromValue(pitchFrameOfReference);
        if (enumPitchframeofreference == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.FrameOfReference value " + pitchFrameOfReference);
        ArsdkFeatureGimbal.FrameOfReference enumRollframeofreference = ArsdkFeatureGimbal.FrameOfReference.fromValue(rollFrameOfReference);
        if (enumRollframeofreference == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.FrameOfReference value " + rollFrameOfReference);
        try {
            cb.onAttitude(gimbalId, enumYawframeofreference, enumPitchframeofreference, enumRollframeofreference, yawRelative, pitchRelative, rollRelative, yawAbsolute, pitchAbsolute, rollAbsolute);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.attitude [gimbal_id: " + gimbalId + ", yaw_frame_of_reference: " + yawFrameOfReference + ", pitch_frame_of_reference: " + pitchFrameOfReference + ", roll_frame_of_reference: " + rollFrameOfReference + ", yaw_relative: " + yawRelative + ", pitch_relative: " + pitchRelative + ", roll_relative: " + rollRelative + ", yaw_absolute: " + yawAbsolute + ", pitch_absolute: " + pitchAbsolute + ", roll_absolute: " + rollAbsolute + "]", e);
        }
    }

    private static void axisLockState(Callback cb, int gimbalId, int lockedBitField) {
        try {
            cb.onAxisLockState(gimbalId, lockedBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.axis_lock_state [gimbal_id: " + gimbalId + ", locked: " + lockedBitField + "]", e);
        }
    }

    private static void offsets(Callback cb, int gimbalId, int updateState, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {
        ArsdkFeatureGimbal.State enumUpdatestate = ArsdkFeatureGimbal.State.fromValue(updateState);
        if (enumUpdatestate == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.State value " + updateState);
        try {
            cb.onOffsets(gimbalId, enumUpdatestate, minBoundYaw, maxBoundYaw, currentYaw, minBoundPitch, maxBoundPitch, currentPitch, minBoundRoll, maxBoundRoll, currentRoll);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.offsets [gimbal_id: " + gimbalId + ", update_state: " + updateState + ", min_bound_yaw: " + minBoundYaw + ", max_bound_yaw: " + maxBoundYaw + ", current_yaw: " + currentYaw + ", min_bound_pitch: " + minBoundPitch + ", max_bound_pitch: " + maxBoundPitch + ", current_pitch: " + currentPitch + ", min_bound_roll: " + minBoundRoll + ", max_bound_roll: " + maxBoundRoll + ", current_roll: " + currentRoll + "]", e);
        }
    }

    private static void absoluteAttitudeBounds(Callback cb, int gimbalId, float minYaw, float maxYaw, float minPitch, float maxPitch, float minRoll, float maxRoll) {
        try {
            cb.onAbsoluteAttitudeBounds(gimbalId, minYaw, maxYaw, minPitch, maxPitch, minRoll, maxRoll);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.absolute_attitude_bounds [gimbal_id: " + gimbalId + ", min_yaw: " + minYaw + ", max_yaw: " + maxYaw + ", min_pitch: " + minPitch + ", max_pitch: " + maxPitch + ", min_roll: " + minRoll + ", max_roll: " + maxRoll + "]", e);
        }
    }

    private static void calibrationState(Callback cb, int state, int gimbalId) {
        ArsdkFeatureGimbal.CalibrationState enumState = ArsdkFeatureGimbal.CalibrationState.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.CalibrationState value " + state);
        try {
            cb.onCalibrationState(enumState, gimbalId);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.calibration_state [state: " + state + ", gimbal_id: " + gimbalId + "]", e);
        }
    }

    private static void calibrationResult(Callback cb, int gimbalId, int result) {
        ArsdkFeatureGimbal.CalibrationResult enumResult = ArsdkFeatureGimbal.CalibrationResult.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureGimbal.CalibrationResult value " + result);
        try {
            cb.onCalibrationResult(gimbalId, enumResult);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.calibration_result [gimbal_id: " + gimbalId + ", result: " + result + "]", e);
        }
    }

    private static void alert(Callback cb, int gimbalId, int errorBitField) {
        try {
            cb.onAlert(gimbalId, errorBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: gimbal.alert [gimbal_id: " + gimbalId + ", error: " + errorBitField + "]", e);
        }
    }
    
    /**

     * 
     * @param gimbalId: id of the gimbal.
     * @param yaw: Maximum yaw speed value, in degrees per seconds This value will be clamped between [MaxSpeed](#148-3)
     * min_bound_yaw and max_bound_yaw
     * @param pitch: Maximum pitch speed value, in degrees per seconds This value will be clamped between
     * [MaxSpeed](#148-3) min_bound_pitch and max_bound_pitch
     * @param roll: Maximum roll speed value, in degrees per seconds This value will be clamped between
     * [MaxSpeed](#148-3) min_bound_roll and max_bound_roll
     */
    public static ArsdkCommand encodeSetMaxSpeed(int gimbalId, float yaw, float pitch, float roll) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetMaxSpeed(cmd.getNativePtr(), gimbalId, yaw, pitch, roll);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     * @param controlMode: Mode of changing the gimbal attitude. This parameter will caracterize following parameters
     * units.
     * @param yawFrameOfReference: Frame of reference of the yaw value. `none` if the yaw target should not be changed
     * @param yaw: Yaw value. Frame of reference is given by the value of `yaw_frame_of_reference`. Units depend on the
     * `control_mode` value: - `position`: value is in degrees - `velocity`, value is in signed ratio (from -1 to 1) of
     * the [MaxSpeed](#148-3) `current_yaw` parameter
     * @param pitchFrameOfReference: Frame of reference of the pitch value `none` if the pitch target should not be
     * changed
     * @param pitch: Pitch value. Frame of reference is given by the value of `pitch_frame_of_reference`. Units depend
     * on the `control_mode` value: - `position`: value is in degrees - `velocity`, value is in signed ratio (from -1 to
     * 1) of the [MaxSpeed](#148-3) `current_pitch` parameter
     * @param rollFrameOfReference: Frame of reference of the roll value `none` if the roll target should not be changed
     * @param roll: Roll value. Frame of reference is given by the value of `roll_frame_of_reference`. Units depend on
     * the `control_mode` value: - `position`: value is in degrees - `velocity`, value is in signed ratio (from -1 to 1)
     * of the [MaxSpeed](#148-3) `current_roll` parameter
     */
    public static ArsdkCommand encodeSetTarget(int gimbalId, @NonNull ControlMode controlMode, @NonNull FrameOfReference yawFrameOfReference, float yaw, @NonNull FrameOfReference pitchFrameOfReference, float pitch, @NonNull FrameOfReference rollFrameOfReference, float roll) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetTarget(cmd.getNativePtr(), gimbalId, controlMode.value, yawFrameOfReference.value, yaw, pitchFrameOfReference.value, pitch, rollFrameOfReference.value, roll);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     * @param yaw: Offset, in degrees, that should be applied to the yaw axis. This value will be clamped between
     * [Offsets](#148-8) min_bound_yaw and max_bound_yaw
     * @param pitch: Offset, in degrees, that should be applied to the pitch axis. This value will be clamped between
     * [[Offsets](#148-8) min_bound_pitch and max_bound_pitch
     * @param roll: Offset, in degrees, that should be applied to the roll axis. This value will be clamped between
     * [Offsets](#148-8) min_bound_roll and max_bound_roll
     */
    public static ArsdkCommand encodeSetOffsets(int gimbalId, float yaw, float pitch, float roll) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetOffsets(cmd.getNativePtr(), gimbalId, yaw, pitch, roll);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     */
    public static ArsdkCommand encodeResetOrientation(int gimbalId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetOrientation(cmd.getNativePtr(), gimbalId);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     */
    public static ArsdkCommand encodeStartOffsetsUpdate(int gimbalId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartOffsetsUpdate(cmd.getNativePtr(), gimbalId);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     */
    public static ArsdkCommand encodeStopOffsetsUpdate(int gimbalId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStopOffsetsUpdate(cmd.getNativePtr(), gimbalId);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     */
    public static ArsdkCommand encodeCalibrate(int gimbalId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeCalibrate(cmd.getNativePtr(), gimbalId);
        return cmd;
    }

    /**

     * 
     * @param gimbalId: id of the gimbal.
     */
    public static ArsdkCommand encodeCancelCalibration(int gimbalId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeCancelCalibration(cmd.getNativePtr(), gimbalId);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetMaxSpeed(long nativeCmd, int gimbal_id, float yaw, float pitch, float roll);
    private static native int nativeEncodeSetTarget(long nativeCmd, int gimbal_id, int control_mode, int yaw_frame_of_reference, float yaw, int pitch_frame_of_reference, float pitch, int roll_frame_of_reference, float roll);
    private static native int nativeEncodeSetOffsets(long nativeCmd, int gimbal_id, float yaw, float pitch, float roll);
    private static native int nativeEncodeResetOrientation(long nativeCmd, int gimbal_id);
    private static native int nativeEncodeStartOffsetsUpdate(long nativeCmd, int gimbal_id);
    private static native int nativeEncodeStopOffsetsUpdate(long nativeCmd, int gimbal_id);
    private static native int nativeEncodeCalibrate(long nativeCmd, int gimbal_id);
    private static native int nativeEncodeCancelCalibration(long nativeCmd, int gimbal_id);
}
