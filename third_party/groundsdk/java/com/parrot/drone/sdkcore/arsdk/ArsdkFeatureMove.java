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
 * Move feature command/event interface.
 */
public class ArsdkFeatureMove {

    /**
     * Orientation mode of the move to.
     */
    public enum OrientationMode {

        /**
         * The drone won't change its orientation.
         */
        NONE(0),

        /**
         * The drone will make a rotation to look in direction of the given location.
         */
        TO_TARGET(1),

        /**
         * The drone will orientate itself to the given heading before moving to the location.
         */
        HEADING_START(2),

        /**
         * The drone will orientate itself to the given heading while moving to the location.
         */
        HEADING_DURING(3);

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
        public static OrientationMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private OrientationMode(int value) {
            this.value = value;
        }

        private static final SparseArray<OrientationMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (OrientationMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Indicators needed to start a move.
     */
    public enum Indicator {

        /**
         * Drone gps is not fixed.
         */
        DRONE_GPS(0),

        /**
         * Drone magneto is not valid.
         */
        DRONE_MAGNETO(1),

        /**
         * Drone is out of geofence.
         */
        DRONE_GEOFENCE(2),

        /**
         * Drone is under min altitude.
         */
        DRONE_MIN_ALTITUDE(3),

        /**
         * Drone is above max altitude.
         */
        DRONE_MAX_ALTITUDE(4),

        /**
         * Drone is not flying.
         */
        DRONE_FLYING(5),

        /**
         * Target position has a bad accuracy. Not applicable to Move.
         */
        TARGET_POSITION_ACCURACY(6),

        /**
         * Target image detection is not working. Not applicable to Move.
         */
        TARGET_IMAGE_DETECTION(7),

        /**
         * Drone is too close to target. Not applicable to Move.
         */
        DRONE_TARGET_DISTANCE_MIN(8),

        /**
         * Drone is too far from target. Not applicable to Move.
         */
        DRONE_TARGET_DISTANCE_MAX(9),

        /**
         * Target horizontal speed is too high. Not applicable to Move.
         */
        TARGET_HORIZ_SPEED(10),

        /**
         * Target vertical speed is too high. Not applicable to Move.
         */
        TARGET_VERT_SPEED(11),

        /**
         * Target altitude has a bad accuracy. Not applicable to Move.
         */
        TARGET_ALTITUDE_ACCURACY(12);

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
        public static Indicator fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Indicator> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 13) {
                    ULog.e(TAG, "Unsupported Indicator bitfield value " + value);
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
        public static EnumSet<Indicator> fromBitfield(int bitfield) {
            EnumSet<Indicator> enums = EnumSet.noneOf(Indicator.class);
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
        public static int toBitField(@NonNull Indicator... enums) {
            int bitField = 0;
            for (Indicator e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Indicator(int value) {
            this.value = value;
        }

        private static final SparseArray<Indicator> MAP;

        static {
            MAP = new SparseArray<>();
            for (Indicator e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0xAA00;

    /** Uid of info event. */;
    public static final int INFO_UID = 0x0003;

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
         * Describes the missing requirements to start a move.
         * 
         * @param missingInputsBitField: List of missing requirements to start a move. If at least one input is missing,
         * drone won't be able to start a move.
         */
        public default void onInfo(int missingInputsBitField) {}
    }

    private static void info(Callback cb, int missingInputsBitField) {
        try {
            cb.onInfo(missingInputsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: move.info [missing_inputs: " + missingInputsBitField + "]", e);
        }
    }
    
    /**
     * Move the drone to a specified location with speeds limited by the value given. If a new command
     * moveTo/ExtendedMoveTo is sent, the drone will immediatly run it (no cancel will be issued). If a
     * [CancelMoveTo](#1-0-11) command is sent, the ExtendedMoveTo is stopped. During the ExtendedMoveTo, all pitch,
     * roll and gaz values of the piloting command will be ignored by the drone. However, the yaw value can be used. If
     * all speeds are zero the command will result in event [MoveToChanged](#1-4-12) status ERROR
     * 
     * @param latitude: Latitude of the location (in degrees) to reach
     * @param longitude: Longitude of the location (in degrees) to reach
     * @param altitude: Altitude above take off point (in m) to reach
     * @param orientationMode: Orientation mode of the move to
     * @param heading: Heading (relative to the North in degrees). This value is only used if the orientation mode is
     * 'heading_start' or 'heading_during'
     * @param maxHorizontalSpeed: Maximum horizontal speed in m/s.
     * @param maxVerticalSpeed: Maximum vertical speed in m/s.
     * @param maxYawRotationSpeed: Maximum vertical speed in degrees/s.
     */
    public static ArsdkCommand encodeExtendedMoveTo(double latitude, double longitude, double altitude, @NonNull OrientationMode orientationMode, float heading, float maxHorizontalSpeed, float maxVerticalSpeed, float maxYawRotationSpeed) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeExtendedMoveTo(cmd.getNativePtr(), latitude, longitude, altitude, orientationMode.value, heading, maxHorizontalSpeed, maxVerticalSpeed, maxYawRotationSpeed);
        return cmd;
    }

    /**
     * Move the drone to a relative position and rotate heading by a given angle, with maximum associated speed
     * settings. Moves are relative to the current drone orientation, (drone's reference). Also note that the given
     * rotation will not modify the move (i.e. moves are always rectilinear). If all speeds are zero or are incoherent
     * with asked d_x/d_y/d_z/d_psi the command will result with event [RelativeMoveEnded](#1-34-0) status ERROR
     * 
     * @param dX: Wanted displacement along the front axis [m]
     * @param dY: Wanted displacement along the right axis [m]
     * @param dZ: Wanted displacement along the down axis [m]
     * @param dPsi: Wanted rotation of heading [rad]
     * @param maxHorizontalSpeed: Maximum horizontal speed in m/s.
     * @param maxVerticalSpeed: Maximum vertical speed in m/s.
     * @param maxYawRotationSpeed: Maximum vertical speed in degrees/s.
     */
    public static ArsdkCommand encodeExtendedMoveBy(float dX, float dY, float dZ, float dPsi, float maxHorizontalSpeed, float maxVerticalSpeed, float maxYawRotationSpeed) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeExtendedMoveBy(cmd.getNativePtr(), dX, dY, dZ, dPsi, maxHorizontalSpeed, maxVerticalSpeed, maxYawRotationSpeed);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeExtendedMoveTo(long nativeCmd, double latitude, double longitude, double altitude, int orientation_mode, float heading, float max_horizontal_speed, float max_vertical_speed, float max_yaw_rotation_speed);
    private static native int nativeEncodeExtendedMoveBy(long nativeCmd, float d_x, float d_y, float d_z, float d_psi, float max_horizontal_speed, float max_vertical_speed, float max_yaw_rotation_speed);
}
