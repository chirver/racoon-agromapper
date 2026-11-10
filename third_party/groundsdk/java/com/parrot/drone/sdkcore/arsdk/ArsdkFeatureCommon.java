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
 * Common feature command/event interface.
 */
public class ArsdkFeatureCommon {

    /**
     * Cause of the disconnection of the product.
     */
    public enum NetworkeventDisconnectionCause {

        /**
         * The button off has been pressed.
         */
        OFF_BUTTON(0),

        /**
         * Unknown generic cause.
         */
        UNKNOWN(1),

        /**
         * The reset factory procedure has been requested.
         */
        RESET_FACTORY(2);

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
        public static NetworkeventDisconnectionCause fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworkeventDisconnectionCause(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworkeventDisconnectionCause> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworkeventDisconnectionCause e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Sensor name.
     */
    public enum CommonstateSensorsstateslistchangedSensorname {

        /**
         * Inertial Measurement Unit sensor.
         */
        IMU(0),

        /**
         * Barometer sensor.
         */
        BAROMETER(1),

        /**
         * Ultrasonic sensor.
         */
        ULTRASOUND(2),

        /**
         * GPS sensor.
         */
        GPS(3),

        /**
         * Magnetometer sensor.
         */
        MAGNETOMETER(4),

        /**
         * Vertical Camera sensor.
         */
        VERTICAL_CAMERA(5);

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
        public static CommonstateSensorsstateslistchangedSensorname fromValue(int value) {
            return MAP.get(value, null);
        }

        private CommonstateSensorsstateslistchangedSensorname(int value) {
            this.value = value;
        }

        private static final SparseArray<CommonstateSensorsstateslistchangedSensorname> MAP;

        static {
            MAP = new SparseArray<>();
            for (CommonstateSensorsstateslistchangedSensorname e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The Model of the product.
     */
    public enum CommonstateProductmodelModel {

        /**
         * Travis (RS taxi) model.
         */
        RS_TRAVIS(0),

        /**
         * Mars (RS space) model.
         */
        RS_MARS(1),

        /**
         * SWAT (RS SWAT) model.
         */
        RS_SWAT(2),

        /**
         * Mc Lane (RS police) model.
         */
        RS_MCLANE(3),

        /**
         * Blaze (RS fire) model.
         */
        RS_BLAZE(4),

        /**
         * Orak (RS carbon hydrofoil) model.
         */
        RS_ORAK(5),

        /**
         * New Z (RS wooden hydrofoil) model.
         */
        RS_NEWZ(6),

        /**
         * Marshall (JS fire) model.
         */
        JS_MARSHALL(7),

        /**
         * Diesel (JS SWAT) model.
         */
        JS_DIESEL(8),

        /**
         * Buzz (JS space) model.
         */
        JS_BUZZ(9),

        /**
         * Max (JS F1) model.
         */
        JS_MAX(10),

        /**
         * Jett (JS flames) model.
         */
        JS_JETT(11),

        /**
         * Tuk-Tuk (JS taxi) model.
         */
        JS_TUKTUK(12),

        /**
         * Swing black model.
         */
        SW_BLACK(13),

        /**
         * Swing white model.
         */
        SW_WHITE(14);

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
        public static CommonstateProductmodelModel fromValue(int value) {
            return MAP.get(value, null);
        }

        private CommonstateProductmodelModel(int value) {
            this.value = value;
        }

        private static final SparseArray<CommonstateProductmodelModel> MAP;

        static {
            MAP = new SparseArray<>();
            for (CommonstateProductmodelModel e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Update status.
     */
    public enum ControllerPeerstatechangedState {

        /**
         * Peer connected to Skycontroller.
         */
        CONNECTED(0),

        /**
         * Peer disconnected from Skycontroller.
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
        public static ControllerPeerstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private ControllerPeerstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<ControllerPeerstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (ControllerPeerstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * SDK connection type.
     */
    public enum ControllerPeerstatechangedType {

        /**
         * Unknown.
         */
        UNKNOWN(0),

        /**
         * Net.
         */
        NET(1),

        /**
         * Mux.
         */
        MUX(2);

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
        public static ControllerPeerstatechangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private ControllerPeerstatechangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<ControllerPeerstatechangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (ControllerPeerstatechangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * type of the played mavlink file.
     */
    public enum MavlinkStartType {

        /**
         * Mavlink file for FlightPlan.
         */
        FLIGHTPLAN(0),

        /**
         * Mavlink file for MapMyHouse.
         */
        MAPMYHOUSE(1);

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
        public static MavlinkStartType fromValue(int value) {
            return MAP.get(value, null);
        }

        private MavlinkStartType(int value) {
            this.value = value;
        }

        private static final SparseArray<MavlinkStartType> MAP;

        static {
            MAP = new SparseArray<>();
            for (MavlinkStartType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of the mavlink.
     */
    public enum MavlinkstateMavlinkfileplayingstatechangedState {

        /**
         * Mavlink file is playing.
         */
        PLAYING(0),

        /**
         * Mavlink file is stopped (arg filepath and type are useless in this state).
         */
        STOPPED(1),

        /**
         * Mavlink file is paused.
         */
        PAUSED(2),

        /**
         * Mavlink file is loaded (it will be played at take-off).
         */
        LOADED(3);

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
        public static MavlinkstateMavlinkfileplayingstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private MavlinkstateMavlinkfileplayingstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<MavlinkstateMavlinkfileplayingstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (MavlinkstateMavlinkfileplayingstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * type of the played mavlink file.
     */
    public enum MavlinkstateMavlinkfileplayingstatechangedType {

        /**
         * Mavlink file for FlightPlan.
         */
        FLIGHTPLAN(0),

        /**
         * Mavlink file for MapMyHouse.
         */
        MAPMYHOUSE(1);

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
        public static MavlinkstateMavlinkfileplayingstatechangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private MavlinkstateMavlinkfileplayingstatechangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<MavlinkstateMavlinkfileplayingstatechangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (MavlinkstateMavlinkfileplayingstatechangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of play error.
     */
    public enum MavlinkstateMavlinkplayerrorstatechangedError {

        /**
         * There is no error.
         */
        NONE(0),

        /**
         * The drone is not in outdoor mode.
         */
        NOTINOUTDOORMODE(1),

        /**
         * The gps is not fixed.
         */
        GPSNOTFIXED(2),

        /**
         * The magnetometer of the drone is not calibrated.
         */
        NOTCALIBRATED(3);

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
        public static MavlinkstateMavlinkplayerrorstatechangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private MavlinkstateMavlinkplayerrorstatechangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<MavlinkstateMavlinkplayerrorstatechangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (MavlinkstateMavlinkplayerrorstatechangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The axis to calibrate.
     */
    public enum CalibrationstateMagnetocalibrationaxistocalibratechangedAxis {

        /**
         * If the current calibration axis should be the x axis.
         */
        XAXIS(0),

        /**
         * If the current calibration axis should be the y axis.
         */
        YAXIS(1),

        /**
         * If the current calibration axis should be the z axis.
         */
        ZAXIS(2),

        /**
         * If none of the axis should be calibrated.
         */
        NONE(3);

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
        public static CalibrationstateMagnetocalibrationaxistocalibratechangedAxis fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationstateMagnetocalibrationaxistocalibratechangedAxis(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationstateMagnetocalibrationaxistocalibratechangedAxis> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationstateMagnetocalibrationaxistocalibratechangedAxis e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of pitot calibration.
     */
    public enum CalibrationstatePitotcalibrationstatechangedState {

        /**
         * Calibration is ok.
         */
        DONE(0),

        /**
         * Calibration is started, waiting user action.
         */
        READY(1),

        /**
         * Calibration is in progress.
         */
        IN_PROGRESS(2),

        /**
         * Calibration is required.
         */
        REQUIRED(3);

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
        public static CalibrationstatePitotcalibrationstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationstatePitotcalibrationstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationstatePitotcalibrationstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationstatePitotcalibrationstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Drone FlightPlan component id (unique).
     */
    public enum FlightplanstateComponentstatelistchangedComponent {

        /**
         * Drone GPS component. State is 0 when the drone needs a GPS fix.
         */
        GPS(0),

        /**
         * Drone Calibration component. State is 0 when the sensors of the drone needs to be calibrated.
         */
        CALIBRATION(1),

        /**
         * Mavlink file component. State is 0 when the mavlink file is missing or contains error.
         */
        MAVLINK_FILE(2),

        /**
         * Drone Take off component. State is 0 when the drone cannot take-off.
         */
        TAKEOFF(3),

        /**
         * Component for waypoints beyond the geofence. State is 0 when one or more waypoints are beyond the geofence.
         */
        WAYPOINTSBEYONDGEOFENCE(4),

        /**
         * Drone camera component. State is 0 when the drone needs to wait camera availability.
         */
        CAMERAAVAILABLE(5);

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
        public static FlightplanstateComponentstatelistchangedComponent fromValue(int value) {
            return MAP.get(value, null);
        }

        private FlightplanstateComponentstatelistchangedComponent(int value) {
            this.value = value;
        }

        private static final SparseArray<FlightplanstateComponentstatelistchangedComponent> MAP;

        static {
            MAP = new SparseArray<>();
            for (FlightplanstateComponentstatelistchangedComponent e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation to start.
     */
    public enum AnimationsStartanimationAnim {

        /**
         * Flash headlights.
         */
        HEADLIGHTS_FLASH(0),

        /**
         * Blink headlights.
         */
        HEADLIGHTS_BLINK(1),

        /**
         * Oscillating headlights.
         */
        HEADLIGHTS_OSCILLATION(2),

        /**
         * Spin animation.
         */
        SPIN(3),

        /**
         * Tap animation.
         */
        TAP(4),

        /**
         * Slow shake animation.
         */
        SLOW_SHAKE(5),

        /**
         * Metronome animation.
         */
        METRONOME(6),

        /**
         * Standing dance animation.
         */
        ONDULATION(7),

        /**
         * Spin jump animation.
         */
        SPIN_JUMP(8),

        /**
         * Spin that end in standing posture, or in jumper if it was standing animation.
         */
        SPIN_TO_POSTURE(9),

        /**
         * Spiral animation.
         */
        SPIRAL(10),

        /**
         * Slalom animation.
         */
        SLALOM(11),

        /**
         * Boost animation.
         */
        BOOST(12),

        /**
         * Make a looping. (Only for WingX).
         */
        LOOPING(13),

        /**
         * Make a barrel roll of 180 degree turning on right. (Only for WingX).
         */
        BARREL_ROLL_180_RIGHT(14),

        /**
         * Make a barrel roll of 180 degree turning on left. (Only for WingX).
         */
        BARREL_ROLL_180_LEFT(15),

        /**
         * Put the drone upside down. (Only for WingX).
         */
        BACKSWAP(16);

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
        public static AnimationsStartanimationAnim fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsStartanimationAnim(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsStartanimationAnim> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsStartanimationAnim e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation to stop.
     */
    public enum AnimationsStopanimationAnim {

        /**
         * Flash headlights.
         */
        HEADLIGHTS_FLASH(0),

        /**
         * Blink headlights.
         */
        HEADLIGHTS_BLINK(1),

        /**
         * Oscillating headlights.
         */
        HEADLIGHTS_OSCILLATION(2),

        /**
         * Spin animation.
         */
        SPIN(3),

        /**
         * Tap animation.
         */
        TAP(4),

        /**
         * Slow shake animation.
         */
        SLOW_SHAKE(5),

        /**
         * Metronome animation.
         */
        METRONOME(6),

        /**
         * Standing dance animation.
         */
        ONDULATION(7),

        /**
         * Spin jump animation.
         */
        SPIN_JUMP(8),

        /**
         * Spin that end in standing posture, or in jumper if it was standing animation.
         */
        SPIN_TO_POSTURE(9),

        /**
         * Spiral animation.
         */
        SPIRAL(10),

        /**
         * Slalom animation.
         */
        SLALOM(11),

        /**
         * Boost animation.
         */
        BOOST(12),

        /**
         * Make a looping. (Only for WingX).
         */
        LOOPING(13),

        /**
         * Make a barrel roll of 180 degree turning on right. (Only for WingX).
         */
        BARREL_ROLL_180_RIGHT(14),

        /**
         * Make a barrel roll of 180 degree turning on left. (Only for WingX).
         */
        BARREL_ROLL_180_LEFT(15),

        /**
         * Put the drone upside down. (Only for WingX).
         */
        BACKSWAP(16);

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
        public static AnimationsStopanimationAnim fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsStopanimationAnim(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsStopanimationAnim> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsStopanimationAnim e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation type.
     */
    public enum AnimationsstateListAnim {

        /**
         * Flash headlights.
         */
        HEADLIGHTS_FLASH(0),

        /**
         * Blink headlights.
         */
        HEADLIGHTS_BLINK(1),

        /**
         * Oscillating headlights.
         */
        HEADLIGHTS_OSCILLATION(2),

        /**
         * Spin animation.
         */
        SPIN(3),

        /**
         * Tap animation.
         */
        TAP(4),

        /**
         * Slow shake animation.
         */
        SLOW_SHAKE(5),

        /**
         * Metronome animation.
         */
        METRONOME(6),

        /**
         * Standing dance animation.
         */
        ONDULATION(7),

        /**
         * Spin jump animation.
         */
        SPIN_JUMP(8),

        /**
         * Spin that end in standing posture, or in jumper if it was standing animation.
         */
        SPIN_TO_POSTURE(9),

        /**
         * Spiral animation.
         */
        SPIRAL(10),

        /**
         * Slalom animation.
         */
        SLALOM(11),

        /**
         * Boost animation.
         */
        BOOST(12),

        /**
         * Make a looping. (Only for WingX).
         */
        LOOPING(13),

        /**
         * Make a barrel roll of 180 degree turning on right. (Only for WingX).
         */
        BARREL_ROLL_180_RIGHT(14),

        /**
         * Make a barrel roll of 180 degree turning on left. (Only for WingX).
         */
        BARREL_ROLL_180_LEFT(15),

        /**
         * Put the drone upside down. (Only for WingX).
         */
        BACKSWAP(16);

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
        public static AnimationsstateListAnim fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateListAnim(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateListAnim> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateListAnim e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of the animation.
     */
    public enum AnimationsstateListState {

        /**
         * animation is stopped.
         */
        STOPPED(0),

        /**
         * animation is started.
         */
        STARTED(1),

        /**
         * The animation is not available.
         */
        NOTAVAILABLE(2);

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
        public static AnimationsstateListState fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateListState(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateListState> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateListState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the state.
     */
    public enum AnimationsstateListError {

        /**
         * No Error.
         */
        OK(0),

        /**
         * Unknown generic error.
         */
        UNKNOWN(1);

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
        public static AnimationsstateListError fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateListError(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateListError> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateListError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Accessory configuration to set.
     */
    public enum AccessoryConfigAccessory {

        /**
         * No accessory.
         */
        NO_ACCESSORY(0),

        /**
         * Standard wheels.
         */
        STD_WHEELS(1),

        /**
         * Truck wheels.
         */
        TRUCK_WHEELS(2),

        /**
         * Hull.
         */
        HULL(3),

        /**
         * Hydrofoil.
         */
        HYDROFOIL(4);

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
        public static AccessoryConfigAccessory fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccessoryConfigAccessory(int value) {
            this.value = value;
        }

        private static final SparseArray<AccessoryConfigAccessory> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccessoryConfigAccessory e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Accessory configurations supported by the product.
     */
    public enum AccessorystateSupportedaccessorieslistchangedAccessory {

        /**
         * No accessory.
         */
        NO_ACCESSORY(0),

        /**
         * Standard wheels.
         */
        STD_WHEELS(1),

        /**
         * Truck wheels.
         */
        TRUCK_WHEELS(2),

        /**
         * Hull.
         */
        HULL(3),

        /**
         * Hydrofoil.
         */
        HYDROFOIL(4);

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
        public static AccessorystateSupportedaccessorieslistchangedAccessory fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccessorystateSupportedaccessorieslistchangedAccessory(int value) {
            this.value = value;
        }

        private static final SparseArray<AccessorystateSupportedaccessorieslistchangedAccessory> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccessorystateSupportedaccessorieslistchangedAccessory e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Accessory configuration reported by firmware.
     */
    public enum AccessorystateAccessoryconfigchangedNewaccessory {

        /**
         * No accessory configuration set. Controller needs to set one.
         */
        UNCONFIGURED(0),

        /**
         * No accessory.
         */
        NO_ACCESSORY(1),

        /**
         * Standard wheels.
         */
        STD_WHEELS(2),

        /**
         * Truck wheels.
         */
        TRUCK_WHEELS(3),

        /**
         * Hull.
         */
        HULL(4),

        /**
         * Hydrofoil.
         */
        HYDROFOIL(5),

        /**
         * Configuration in progress.
         */
        IN_PROGRESS(6);

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
        public static AccessorystateAccessoryconfigchangedNewaccessory fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccessorystateAccessoryconfigchangedNewaccessory(int value) {
            this.value = value;
        }

        private static final SparseArray<AccessorystateAccessoryconfigchangedNewaccessory> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccessorystateAccessoryconfigchangedNewaccessory e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error code.
     */
    public enum AccessorystateAccessoryconfigchangedError {

        /**
         * No error. Accessory config change successful.
         */
        OK(0),

        /**
         * Cannot change accessory configuration for some reason.
         */
        UNKNOWN(1),

        /**
         * Cannot change accessory configuration while flying.
         */
        FLYING(2);

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
        public static AccessorystateAccessoryconfigchangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccessorystateAccessoryconfigchangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<AccessorystateAccessoryconfigchangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccessorystateAccessoryconfigchangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The new maximum charge rate.
     */
    public enum ChargerSetmaxchargerateRate {

        /**
         * Fully charge the battery at a slow rate. Typically limit max charge current to 512 mA.
         */
        SLOW(0),

        /**
         * Almost fully-charge the battery at moderate rate (&gt; 512mA) but slower than the fastest rate.
         */
        MODERATE(1),

        /**
         * Almost fully-charge the battery at the highest possible rate supported by the charger.
         */
        FAST(2);

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
        public static ChargerSetmaxchargerateRate fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerSetmaxchargerateRate(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerSetmaxchargerateRate> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerSetmaxchargerateRate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The current maximum charge rate.
     */
    public enum ChargerstateMaxchargeratechangedRate {

        /**
         * Fully charge the battery at a slow rate. Typically limit max charge current to 512 mA.
         */
        SLOW(0),

        /**
         * Almost fully-charge the battery at moderate rate (&gt; 512 mA) but slower than the fastest rate.
         */
        MODERATE(1),

        /**
         * Almost fully-charge the battery at the highest possible rate supported by the charger.
         */
        FAST(2);

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
        public static ChargerstateMaxchargeratechangedRate fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateMaxchargeratechangedRate(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateMaxchargeratechangedRate> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateMaxchargeratechangedRate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Charger status.
     */
    public enum ChargerstateCurrentchargestatechangedStatus {

        /**
         * The battery is discharging.
         */
        DISCHARGING(0),

        /**
         * The battery is charging at a slow rate about 512 mA.
         */
        CHARGING_SLOW(1),

        /**
         * The battery is charging at a moderate rate (&gt; 512 mA) but slower than the fastest rate.
         */
        CHARGING_MODERATE(2),

        /**
         * The battery is charging at a the fastest rate.
         */
        CHARGING_FAST(3),

        /**
         * The charger is plugged and the battery is fully charged.
         */
        BATTERY_FULL(4);

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
        public static ChargerstateCurrentchargestatechangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateCurrentchargestatechangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateCurrentchargestatechangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateCurrentchargestatechangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The current charging phase.
     */
    public enum ChargerstateCurrentchargestatechangedPhase {

        /**
         * The charge phase is unknown or irrelevant.
         */
        UNKNOWN(0),

        /**
         * First phase of the charging process. The battery is charging with constant current.
         */
        CONSTANT_CURRENT_1(1),

        /**
         * Second phase of the charging process. The battery is charging with constant current, with a higher voltage
         * than the first phase.
         */
        CONSTANT_CURRENT_2(2),

        /**
         * Last part of the charging process. The battery is charging with a constant voltage.
         */
        CONSTANT_VOLTAGE(3),

        /**
         * The battery is fully charged.
         */
        CHARGED(4);

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
        public static ChargerstateCurrentchargestatechangedPhase fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateCurrentchargestatechangedPhase(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateCurrentchargestatechangedPhase> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateCurrentchargestatechangedPhase e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The charge rate recorded by the firmware for the last charge.
     */
    public enum ChargerstateLastchargeratechangedRate {

        /**
         * The last charge rate is not known.
         */
        UNKNOWN(0),

        /**
         * Slow charge rate.
         */
        SLOW(1),

        /**
         * Moderate charge rate.
         */
        MODERATE(2),

        /**
         * Fast charge rate.
         */
        FAST(3);

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
        public static ChargerstateLastchargeratechangedRate fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateLastchargeratechangedRate(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateLastchargeratechangedRate> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateLastchargeratechangedRate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The current charging phase.
     */
    public enum ChargerstateCharginginfoPhase {

        /**
         * The charge phase is unknown or irrelevant.
         */
        UNKNOWN(0),

        /**
         * First phase of the charging process. The battery is charging with constant current.
         */
        CONSTANT_CURRENT_1(1),

        /**
         * Second phase of the charging process. The battery is charging with constant current, with a higher voltage
         * than the first phase.
         */
        CONSTANT_CURRENT_2(2),

        /**
         * Last part of the charging process. The battery is charging with a constant voltage.
         */
        CONSTANT_VOLTAGE(3),

        /**
         * The battery is fully charged.
         */
        CHARGED(4),

        /**
         * The battery is discharging; Other arguments refers to the last charge.
         */
        DISCHARGING(5);

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
        public static ChargerstateCharginginfoPhase fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateCharginginfoPhase(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateCharginginfoPhase> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateCharginginfoPhase e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The charge rate. If phase is DISCHARGING, refers to the last charge.
     */
    public enum ChargerstateCharginginfoRate {

        /**
         * The charge rate is not known.
         */
        UNKNOWN(0),

        /**
         * Slow charge rate.
         */
        SLOW(1),

        /**
         * Moderate charge rate.
         */
        MODERATE(2),

        /**
         * Fast charge rate.
         */
        FAST(3);

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
        public static ChargerstateCharginginfoRate fromValue(int value) {
            return MAP.get(value, null);
        }

        private ChargerstateCharginginfoRate(int value) {
            this.value = value;
        }

        private static final SparseArray<ChargerstateCharginginfoRate> MAP;

        static {
            MAP = new SparseArray<>();
            for (ChargerstateCharginginfoRate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Update status.
     */
    public enum UpdatestateUpdatestatechangedStatus {

        /**
         * Update completed successfully.
         */
        SUCCESS(0),

        /**
         * Failure, wrong or corrupted update file.
         */
        FAILURE_BAD_FILE(1),

        /**
         * Failure, battery level was too low.
         */
        FAILURE_BAT_LEVEL_TOO_LOW(2),

        /**
         * Generic Failure.
         */
        FAILURE(3);

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
        public static UpdatestateUpdatestatechangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private UpdatestateUpdatestatechangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<UpdatestateUpdatestatechangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (UpdatestateUpdatestatechangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Network related commands 
     */
    public static class Network {

        /** Feature uid. */
        public static final int UID = 0x0000;

        /**
         * Signals the remote that the host will disconnect.
         */
        @Deprecated
        public static ArsdkCommand encodeDisconnect() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeDisconnect(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeDisconnect(long nativeCmd);
    }

    /**
     * Network Event from product 
     */
    public static class NetworkEvent {

        /** Feature uid. */
        public static final int UID = 0x0001;

        /** Uid of Disconnection event. */;
        public static final int DISCONNECTION_UID = 0x0000;

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
             * Drone will disconnect. This event is mainly triggered when the user presses on the power button of the
             * product.  **This event is a notification, you can't retrieve it in the cache of the device controller.**
             * 
             * @param cause:
             */
            public default void onDisconnection(@Nullable NetworkeventDisconnectionCause cause) {}
        }

        private static void disconnection(Callback cb, int cause) {
            ArsdkFeatureCommon.NetworkeventDisconnectionCause enumCause = ArsdkFeatureCommon.NetworkeventDisconnectionCause.fromValue(cause);
            if (enumCause == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.NetworkeventDisconnectionCause value " + cause);
            try {
                cb.onDisconnection(enumCause);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.NetworkEvent.Disconnection [cause: " + cause + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Settings commands 
     */
    public static class Settings {

        /** Feature uid. */
        public static final int UID = 0x0002;

        /**
         * Ask for all settings.  **Please note that you should not send this command if you are using the
         * libARController API as this library is handling the connection process for you.**
         */
        public static ArsdkCommand encodeAllSettings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAllSettings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Reset all settings.
         */
        public static ArsdkCommand encodeReset() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReset(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set the product name. It also sets the name of the SSID for Wifi products and advertisement name for BLE
         * products (changed after a reboot of the product).
         * 
         * @param name: Product name
         */
        public static ArsdkCommand encodeProductName(String name) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeProductName(cmd.getNativePtr(), name);
            return cmd;
        }

        /**
         * Set the country for Wifi products. This can modify Wifi band and/or channel. **Please note that you might be
         * disconnected from the product after changing the country as it changes Wifi parameters.**
         * 
         * @param code: Country code with ISO 3166 format
         */
        public static ArsdkCommand encodeCountry(String code) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCountry(cmd.getNativePtr(), code);
            return cmd;
        }

        /**
         * Enable auto-country. If auto-country is set, the drone will guess its Wifi country by itself by checking
         * other Wifi country around it. **Please note that you might be disconnected from the product after changing
         * the country as it changes Wifi parameters.**
         * 
         * @param automatic: Boolean : 0 : Manual / 1 : Auto
         */
        public static ArsdkCommand encodeAutoCountry(int automatic) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutoCountry(cmd.getNativePtr(), automatic);
            return cmd;
        }

        private static native int nativeEncodeAllSettings(long nativeCmd);
        private static native int nativeEncodeReset(long nativeCmd);
        private static native int nativeEncodeProductName(long nativeCmd, String name);
        private static native int nativeEncodeCountry(long nativeCmd, String code);
        private static native int nativeEncodeAutoCountry(long nativeCmd, int automatic);
    }

    /**
     * Settings state from product 
     */
    public static class SettingsState {

        /** Feature uid. */
        public static final int UID = 0x0003;

        /** Uid of AllSettingsChanged event. */;
        public static final int ALLSETTINGSCHANGED_UID = 0x0000;

        /** Uid of ResetChanged event. */;
        public static final int RESETCHANGED_UID = 0x0001;

        /** Uid of ProductNameChanged event. */;
        public static final int PRODUCTNAMECHANGED_UID = 0x0002;

        /** Uid of ProductVersionChanged event. */;
        public static final int PRODUCTVERSIONCHANGED_UID = 0x0003;

        /** Uid of ProductSerialHighChanged event. */;
        public static final int PRODUCTSERIALHIGHCHANGED_UID = 0x0004;

        /** Uid of ProductSerialLowChanged event. */;
        public static final int PRODUCTSERIALLOWCHANGED_UID = 0x0005;

        /** Uid of CountryChanged event. */;
        public static final int COUNTRYCHANGED_UID = 0x0006;

        /** Uid of AutoCountryChanged event. */;
        public static final int AUTOCOUNTRYCHANGED_UID = 0x0007;

        /** Uid of BoardIdChanged event. */;
        public static final int BOARDIDCHANGED_UID = 0x0008;

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
             * All settings have been sent.  **Please note that you should not care about this event if you are using
             * the libARController API as this library is handling the connection process for you.**
             */
            public default void onAllSettingsChanged() {}

            /**
             * All settings have been reset.
             */
            public default void onResetChanged() {}

            /**
             * Product name changed.
             * 
             * @param name: Product name
             */
            public default void onProductNameChanged(String name) {}

            /**
             * Product version.
             * 
             * @param software: Product software version
             * @param hardware: Product hardware version
             */
            public default void onProductVersionChanged(String software, String hardware) {}

            /**
             * Product serial (1st part).
             * 
             * @param high: Serial high number (hexadecimal value)
             */
            public default void onProductSerialHighChanged(String high) {}

            /**
             * Product serial (2nd part).
             * 
             * @param low: Serial low number (hexadecimal value)
             */
            public default void onProductSerialLowChanged(String low) {}

            /**
             * Country changed.
             * 
             * @param code: Country code with ISO 3166 format, empty string means unknown country.
             */
            public default void onCountryChanged(String code) {}

            /**
             * Auto-country changed.
             * 
             * @param automatic: Boolean : 0 : Manual / 1 : Auto
             */
            public default void onAutoCountryChanged(int automatic) {}

            /**
             * Board id.
             * 
             * @param id: Board id
             */
            public default void onBoardIdChanged(String id) {}
        }

        private static void allsettingschanged(Callback cb) {
            try {
                cb.onAllSettingsChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.AllSettingsChanged", e);
            }
        }

        private static void resetchanged(Callback cb) {
            try {
                cb.onResetChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.ResetChanged", e);
            }
        }

        private static void productnamechanged(Callback cb, String name) {
            try {
                cb.onProductNameChanged(name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.ProductNameChanged [name: " + name + "]", e);
            }
        }

        private static void productversionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onProductVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.ProductVersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void productserialhighchanged(Callback cb, String high) {
            try {
                cb.onProductSerialHighChanged(high);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.ProductSerialHighChanged [high: " + high + "]", e);
            }
        }

        private static void productseriallowchanged(Callback cb, String low) {
            try {
                cb.onProductSerialLowChanged(low);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.ProductSerialLowChanged [low: " + low + "]", e);
            }
        }

        private static void countrychanged(Callback cb, String code) {
            try {
                cb.onCountryChanged(code);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.CountryChanged [code: " + code + "]", e);
            }
        }

        private static void autocountrychanged(Callback cb, int automatic) {
            try {
                cb.onAutoCountryChanged(automatic);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.AutoCountryChanged [automatic: " + automatic + "]", e);
            }
        }

        private static void boardidchanged(Callback cb, String id) {
            try {
                cb.onBoardIdChanged(id);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.SettingsState.BoardIdChanged [id: " + id + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Common commands 
     */
    public static class Common {

        /** Feature uid. */
        public static final int UID = 0x0004;

        /**
         * Ask for all states.  **Please note that you should not send this command if you are using the libARController
         * API as this library is handling the connection process for you.**
         */
        public static ArsdkCommand encodeAllStates() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAllStates(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set the date. This date is taken by the drone as its own date. So medias and other files will be dated from
         * this date  **Please note that you should not send this command if you are using the libARController API as
         * this library is handling the connection process for you.**
         * 
         * @param date: Date with ISO-8601 format
         */
        @Deprecated
        public static ArsdkCommand encodeCurrentDate(String date) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCurrentDate(cmd.getNativePtr(), date);
            return cmd;
        }

        /**
         * Set the time. This time is taken by the drone as its own time. So medias and other files will be dated from
         * this time  **Please note that you should not send this command if you are using the libARController API as
         * this library is handling the connection process for you.**
         * 
         * @param time: Time with ISO-8601 format
         */
        @Deprecated
        public static ArsdkCommand encodeCurrentTime(String time) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCurrentTime(cmd.getNativePtr(), time);
            return cmd;
        }

        /**
         * Reboot the product. The product will accept this command only if is not flying.
         */
        public static ArsdkCommand encodeReboot() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReboot(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set both the date and the time with only one command. If using this command, do not use [CurrentDate](#0-4-1)
         * and [CurrentTime](#0-4-2) commands. This datetime is taken by the drone as its own datetime. So medias and
         * other files will be dated from this datetime  **Please note that you should not send this command if you are
         * using the libARController API as this library is handling the connection process for you.**
         * 
         * @param datetime: DateTime with the ISO-8601 complete short format: "%Y%m%dT%H%M%S%z"
         */
        public static ArsdkCommand encodeCurrentDateTime(String datetime) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCurrentDateTime(cmd.getNativePtr(), datetime);
            return cmd;
        }

        private static native int nativeEncodeAllStates(long nativeCmd);
        private static native int nativeEncodeCurrentDate(long nativeCmd, String date);
        private static native int nativeEncodeCurrentTime(long nativeCmd, String time);
        private static native int nativeEncodeReboot(long nativeCmd);
        private static native int nativeEncodeCurrentDateTime(long nativeCmd, String datetime);
    }

    /**
     * Common state from product 
     */
    public static class CommonState {

        /** Feature uid. */
        public static final int UID = 0x0005;

        /** Uid of AllStatesChanged event. */;
        public static final int ALLSTATESCHANGED_UID = 0x0000;

        /** Uid of BatteryStateChanged event. */;
        public static final int BATTERYSTATECHANGED_UID = 0x0001;

        /** Uid of MassStorageStateListChanged event. */;
        public static final int MASSSTORAGESTATELISTCHANGED_UID = 0x0002;

        /** Uid of MassStorageInfoStateListChanged event. */;
        public static final int MASSSTORAGEINFOSTATELISTCHANGED_UID = 0x0003;

        /** Uid of CurrentDateChanged event. */;
        public static final int CURRENTDATECHANGED_UID = 0x0004;

        /** Uid of CurrentTimeChanged event. */;
        public static final int CURRENTTIMECHANGED_UID = 0x0005;

        /** Uid of MassStorageInfoRemainingListChanged event. */;
        public static final int MASSSTORAGEINFOREMAININGLISTCHANGED_UID = 0x0006;

        /** Uid of WifiSignalChanged event. */;
        public static final int WIFISIGNALCHANGED_UID = 0x0007;

        /** Uid of SensorsStatesListChanged event. */;
        public static final int SENSORSSTATESLISTCHANGED_UID = 0x0008;

        /** Uid of ProductModel event. */;
        public static final int PRODUCTMODEL_UID = 0x0009;

        /** Uid of CountryListKnown event. */;
        public static final int COUNTRYLISTKNOWN_UID = 0x000A;

        /** Uid of DeprecatedMassStorageContentChanged event. */;
        public static final int DEPRECATEDMASSSTORAGECONTENTCHANGED_UID = 0x000B;

        /** Uid of MassStorageContent event. */;
        public static final int MASSSTORAGECONTENT_UID = 0x000C;

        /** Uid of MassStorageContentForCurrentRun event. */;
        public static final int MASSSTORAGECONTENTFORCURRENTRUN_UID = 0x000D;

        /** Uid of VideoRecordingTimestamp event. */;
        public static final int VIDEORECORDINGTIMESTAMP_UID = 0x000E;

        /** Uid of CurrentDateTimeChanged event. */;
        public static final int CURRENTDATETIMECHANGED_UID = 0x000F;

        /** Uid of LinkSignalQuality event. */;
        public static final int LINKSIGNALQUALITY_UID = 0x0010;

        /** Uid of BootId event. */;
        public static final int BOOTID_UID = 0x0011;

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
             * All states have been sent.  **Please note that you should not care about this event if you are using the
             * libARController API as this library is handling the connection process for you.**
             */
            public default void onAllStatesChanged() {}

            /**
             * Battery state.
             * 
             * @param percent: Battery percentage
             */
            public default void onBatteryStateChanged(int percent) {}

            /**
             * Mass storage state list.
             * 
             * @param massStorageId: Mass storage id (unique)
             * @param name: Mass storage name
             */
            @Deprecated
            public default void onMassStorageStateListChanged(int massStorageId, String name) {}

            /**
             * Mass storage info state list.
             * 
             * @param massStorageId: Mass storage state id (unique)
             * @param size: Mass storage size in MBytes
             * @param usedSize: Mass storage used size in MBytes
             * @param plugged: Mass storage plugged (1 if mass storage is plugged, otherwise 0)
             * @param full: Mass storage full information state (1 if mass storage full, 0 otherwise).
             * @param internal: Mass storage internal type state (1 if mass storage is internal, 0 otherwise)
             */
            @Deprecated
            public default void onMassStorageInfoStateListChanged(int massStorageId, long size, long usedSize, int plugged, int full, int internal) {}

            /**
             * Date changed. Corresponds to the latest date set on the drone.  **Please note that you should not care
             * about this event if you are using the libARController API as this library is handling the connection
             * process for you.**
             * 
             * @param date: Date with ISO-8601 format
             */
            @Deprecated
            public default void onCurrentDateChanged(String date) {}

            /**
             * Time changed. Corresponds to the latest time set on the drone.  **Please note that you should not care
             * about this event if you are using the libARController API as this library is handling the connection
             * process for you.**
             * 
             * @param time: Time with ISO-8601 format
             */
            @Deprecated
            public default void onCurrentTimeChanged(String time) {}

            /**
             * Mass storage remaining data list.
             * 
             * @param freeSpace: Mass storage free space in MBytes
             * @param recTime: Mass storage record time reamining in minute
             * @param photoRemaining: Mass storage photo remaining
             */
            @Deprecated
            public default void onMassStorageInfoRemainingListChanged(long freeSpace, int recTime, long photoRemaining) {}

            /**
             * Rssi (Wifi Signal between controller and product) changed.
             * 
             * @param rssi: RSSI of the signal between controller and the product (in dbm)
             */
            public default void onWifiSignalChanged(int rssi) {}

            /**
             * Sensors state list.
             * 
             * @param sensorname:
             * @param sensorstate: Sensor state (1 if the sensor is OK, 0 if the sensor is NOT OK)
             */
            public default void onSensorsStatesListChanged(@Nullable CommonstateSensorsstateslistchangedSensorname sensorname, int sensorstate) {}

            /**
             * Product sub-model. This can be used to customize the UI depending on the product.
             * 
             * @param model:
             */
            public default void onProductModel(@Nullable CommonstateProductmodelModel model) {}

            /**
             * List of countries known by the drone.
             * 
             * @param listflags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored.
             * @param countrycodes: Following of country code with ISO 3166 format, separated by ";". Be careful of the
             * command size allowed by the network used. If necessary, split the list in several commands.
             */
            @Deprecated
            public default void onCountryListKnown(int listflags, String countrycodes) {}

            /**
             * Mass storage content changed.
             * 
             * @param massStorageId: Mass storage id (unique)
             * @param nbphotos: Number of photos (does not include raw photos)
             * @param nbvideos: Number of videos
             * @param nbpuds: Number of puds
             * @param nbcrashlogs: Number of crash logs
             */
            @Deprecated
            public default void onDeprecatedMassStorageContentChanged(int massStorageId, int nbphotos, int nbvideos, int nbpuds, int nbcrashlogs) {}

            /**
             * Mass storage content.
             * 
             * @param massStorageId: Mass storage id (unique)
             * @param nbphotos: Number of photos (does not include raw photos)
             * @param nbvideos: Number of videos
             * @param nbpuds: Number of puds
             * @param nbcrashlogs: Number of crash logs
             * @param nbrawphotos: Number of raw photos
             */
            @Deprecated
            public default void onMassStorageContent(int massStorageId, int nbphotos, int nbvideos, int nbpuds, int nbcrashlogs, int nbrawphotos) {}

            /**
             * Mass storage content for current run. Only counts the files related to the current run (see
             * [RunId](#0-30-0))
             * 
             * @param massStorageId: Mass storage id (unique)
             * @param nbphotos: Number of photos (does not include raw photos)
             * @param nbvideos: Number of videos
             * @param nbrawphotos: Number of raw photos
             */
            @Deprecated
            public default void onMassStorageContentForCurrentRun(int massStorageId, int nbphotos, int nbvideos, int nbrawphotos) {}

            /**
             * Current or last video recording timestamp. Timestamp in milliseconds since 00:00:00 UTC on 1 January
             * 1970. **Please note that values don't persist after drone reboot**
             * 
             * @param starttimestamp: Timestamp in milliseconds since 00:00:00 UTC on 1 January 1970.
             * @param stoptimestamp: Timestamp in milliseconds since 00:00:00 UTC on 1 January 1970. 0 mean that video
             * is still recording.
             */
            @Deprecated
            public default void onVideoRecordingTimestamp(long starttimestamp, long stoptimestamp) {}

            /**
             * Both date and time changed. Corresponds to the latest datetime set on the drone.  **Please note that you
             * should not care about this event if you are using the libARController API as this library is handling the
             * connection process for you.**
             * 
             * @param datetime: Datetime with the ISO-8601 complete short format: "%Y%m%dT%H%M%S%z"
             */
            public default void onCurrentDateTimeChanged(String datetime) {}

            /**
             * Link signal quality. Gives a overal indication of the radio link quality
             * 
             * @param value: Bits 0-3: indicate the Link signal quality. The quality varies from 1 to 5. 1 means that a
             * disconnection is highly probable, 5 means that the link signal quality is very good. Bit 6: 1 when there
             * is a probable 4G interference coming from the smartphone. Zero otherwise. Bit 7: 1 when the link signal
             * quality is low although the radio RSSI is good. This indicate that the radio link is perturbed by
             * external elements. Zero otherwise.
             */
            public default void onLinkSignalQuality(int value) {}

            /**
             * Current Drone Boot id. A Boot Id identifies a drone session and do not change between drone power on and
             * power off. Also, each medias contains the Boot Id.
             * 
             * @param bootid: Id of the boot
             */
            public default void onBootId(String bootid) {}
        }

        private static void allstateschanged(Callback cb) {
            try {
                cb.onAllStatesChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.AllStatesChanged", e);
            }
        }

        private static void batterystatechanged(Callback cb, int percent) {
            try {
                cb.onBatteryStateChanged(percent);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.BatteryStateChanged [percent: " + percent + "]", e);
            }
        }

        private static void massstoragestatelistchanged(Callback cb, int massStorageId, String name) {
            try {
                cb.onMassStorageStateListChanged(massStorageId, name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.MassStorageStateListChanged [mass_storage_id: " + massStorageId + ", name: " + name + "]", e);
            }
        }

        private static void massstorageinfostatelistchanged(Callback cb, int massStorageId, long size, long usedSize, int plugged, int full, int internal) {
            try {
                cb.onMassStorageInfoStateListChanged(massStorageId, size, usedSize, plugged, full, internal);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.MassStorageInfoStateListChanged [mass_storage_id: " + massStorageId + ", size: " + size + ", used_size: " + usedSize + ", plugged: " + plugged + ", full: " + full + ", internal: " + internal + "]", e);
            }
        }

        private static void currentdatechanged(Callback cb, String date) {
            try {
                cb.onCurrentDateChanged(date);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.CurrentDateChanged [date: " + date + "]", e);
            }
        }

        private static void currenttimechanged(Callback cb, String time) {
            try {
                cb.onCurrentTimeChanged(time);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.CurrentTimeChanged [time: " + time + "]", e);
            }
        }

        private static void massstorageinforemaininglistchanged(Callback cb, long freeSpace, int recTime, long photoRemaining) {
            try {
                cb.onMassStorageInfoRemainingListChanged(freeSpace, recTime, photoRemaining);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.MassStorageInfoRemainingListChanged [free_space: " + freeSpace + ", rec_time: " + recTime + ", photo_remaining: " + photoRemaining + "]", e);
            }
        }

        private static void wifisignalchanged(Callback cb, int rssi) {
            try {
                cb.onWifiSignalChanged(rssi);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.WifiSignalChanged [rssi: " + rssi + "]", e);
            }
        }

        private static void sensorsstateslistchanged(Callback cb, int sensorname, int sensorstate) {
            ArsdkFeatureCommon.CommonstateSensorsstateslistchangedSensorname enumSensorname = ArsdkFeatureCommon.CommonstateSensorsstateslistchangedSensorname.fromValue(sensorname);
            if (enumSensorname == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.CommonstateSensorsstateslistchangedSensorname value " + sensorname);
            try {
                cb.onSensorsStatesListChanged(enumSensorname, sensorstate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.SensorsStatesListChanged [sensorName: " + sensorname + ", sensorState: " + sensorstate + "]", e);
            }
        }

        private static void productmodel(Callback cb, int model) {
            ArsdkFeatureCommon.CommonstateProductmodelModel enumModel = ArsdkFeatureCommon.CommonstateProductmodelModel.fromValue(model);
            if (enumModel == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.CommonstateProductmodelModel value " + model);
            try {
                cb.onProductModel(enumModel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.ProductModel [model: " + model + "]", e);
            }
        }

        private static void countrylistknown(Callback cb, int listflags, String countrycodes) {
            try {
                cb.onCountryListKnown(listflags, countrycodes);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.CountryListKnown [listFlags: " + listflags + ", countryCodes: " + countrycodes + "]", e);
            }
        }

        private static void deprecatedmassstoragecontentchanged(Callback cb, int massStorageId, int nbphotos, int nbvideos, int nbpuds, int nbcrashlogs) {
            try {
                cb.onDeprecatedMassStorageContentChanged(massStorageId, nbphotos, nbvideos, nbpuds, nbcrashlogs);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.DeprecatedMassStorageContentChanged [mass_storage_id: " + massStorageId + ", nbPhotos: " + nbphotos + ", nbVideos: " + nbvideos + ", nbPuds: " + nbpuds + ", nbCrashLogs: " + nbcrashlogs + "]", e);
            }
        }

        private static void massstoragecontent(Callback cb, int massStorageId, int nbphotos, int nbvideos, int nbpuds, int nbcrashlogs, int nbrawphotos) {
            try {
                cb.onMassStorageContent(massStorageId, nbphotos, nbvideos, nbpuds, nbcrashlogs, nbrawphotos);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.MassStorageContent [mass_storage_id: " + massStorageId + ", nbPhotos: " + nbphotos + ", nbVideos: " + nbvideos + ", nbPuds: " + nbpuds + ", nbCrashLogs: " + nbcrashlogs + ", nbRawPhotos: " + nbrawphotos + "]", e);
            }
        }

        private static void massstoragecontentforcurrentrun(Callback cb, int massStorageId, int nbphotos, int nbvideos, int nbrawphotos) {
            try {
                cb.onMassStorageContentForCurrentRun(massStorageId, nbphotos, nbvideos, nbrawphotos);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.MassStorageContentForCurrentRun [mass_storage_id: " + massStorageId + ", nbPhotos: " + nbphotos + ", nbVideos: " + nbvideos + ", nbRawPhotos: " + nbrawphotos + "]", e);
            }
        }

        private static void videorecordingtimestamp(Callback cb, long starttimestamp, long stoptimestamp) {
            try {
                cb.onVideoRecordingTimestamp(starttimestamp, stoptimestamp);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.VideoRecordingTimestamp [startTimestamp: " + starttimestamp + ", stopTimestamp: " + stoptimestamp + "]", e);
            }
        }

        private static void currentdatetimechanged(Callback cb, String datetime) {
            try {
                cb.onCurrentDateTimeChanged(datetime);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.CurrentDateTimeChanged [datetime: " + datetime + "]", e);
            }
        }

        private static void linksignalquality(Callback cb, int value) {
            try {
                cb.onLinkSignalQuality(value);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.LinkSignalQuality [value: " + value + "]", e);
            }
        }

        private static void bootid(Callback cb, String bootid) {
            try {
                cb.onBootId(bootid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CommonState.BootId [bootId: " + bootid + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Over heat commands 
     */
    public static class OverHeat {

        /** Feature uid. */
        public static final int UID = 0x0006;

        /**
         * Switch off after an overheat.
         */
        @Deprecated
        public static ArsdkCommand encodeSwitchOff() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSwitchOff(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Ventilate after an overheat.
         */
        @Deprecated
        public static ArsdkCommand encodeVentilate() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVentilate(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeSwitchOff(long nativeCmd);
        private static native int nativeEncodeVentilate(long nativeCmd);
    }

    /**
     * Overheat state from product 
     */
    public static class OverHeatState {

        /** Feature uid. */
        public static final int UID = 0x0007;

        /** Uid of OverHeatChanged event. */;
        public static final int OVERHEATCHANGED_UID = 0x0000;

        /** Uid of OverHeatRegulationChanged event. */;
        public static final int OVERHEATREGULATIONCHANGED_UID = 0x0001;

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
             * Overheat temperature reached.
             */
            @Deprecated
            public default void onOverHeatChanged() {}

            /**
             * Overheat regulation type.
             * 
             * @param regulationtype: Type of overheat regulation : 0 for ventilation, 1 for switch off
             */
            public default void onOverHeatRegulationChanged(int regulationtype) {}
        }

        private static void overheatchanged(Callback cb) {
            try {
                cb.onOverHeatChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.OverHeatState.OverHeatChanged", e);
            }
        }

        private static void overheatregulationchanged(Callback cb, int regulationtype) {
            try {
                cb.onOverHeatRegulationChanged(regulationtype);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.OverHeatState.OverHeatRegulationChanged [regulationType: " + regulationtype + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Notify the device about the state of the controller application. 
     */
    public static class Controller {

        /** Feature uid. */
        public static final int UID = 0x0008;

        /**
         * Inform about hud entering. Tell the drone that the controller enters/leaves the piloting hud. On a non-flying
         * products it is used to know when a run begins.
         * 
         * @param piloting: 0 when the application is not in the piloting HUD, 1 when it enters the HUD.
         */
        @Deprecated
        public static ArsdkCommand encodeIsPiloting(int piloting) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeIsPiloting(cmd.getNativePtr(), piloting);
            return cmd;
        }

        /**
         * A SDK peer (ie FreeFlight) has connected/disconnected to the Skycontroller. This is only meant to be sent by
         * the Skycontroller, as it is acting as a proxy.
         * 
         * @param state:
         * @param type:
         * @param peername: Peer name. May not be available at disconnection.
         * @param peerid: Peer id. May not be available at disconnection.
         * @param peertype: Peer type. May not be available at disconnection.
         */
        public static ArsdkCommand encodePeerStateChanged(@NonNull ControllerPeerstatechangedState state, @NonNull ControllerPeerstatechangedType type, String peername, String peerid, String peertype) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePeerStateChanged(cmd.getNativePtr(), state.value, type.value, peername, peerid, peertype);
            return cmd;
        }

        private static native int nativeEncodeIsPiloting(long nativeCmd, int piloting);
        private static native int nativeEncodePeerStateChanged(long nativeCmd, int state, int type, String peerName, String peerId, String peerType);
    }

    /**
     * Wifi settings commands 
     */
    public static class WifiSettings {

        /** Feature uid. */
        public static final int UID = 0x0009;

        /**
         * Set wifi indoor/outdoor mode. **Please note that you might be disconnected from the product after changing
         * the indoor/outdoor setting as it changes Wifi parameters.**
         * 
         * @param outdoor: 1 if it should use outdoor wifi settings, 0 otherwise
         */
        public static ArsdkCommand encodeOutdoorSetting(int outdoor) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeOutdoorSetting(cmd.getNativePtr(), outdoor);
            return cmd;
        }

        private static native int nativeEncodeOutdoorSetting(long nativeCmd, int outdoor);
    }

    /**
     * Wifi settings state from product 
     */
    public static class WifiSettingsState {

        /** Feature uid. */
        public static final int UID = 0x000A;

        /** Uid of outdoorSettingsChanged event. */;
        public static final int OUTDOORSETTINGSCHANGED_UID = 0x0000;

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
             * Wifi outdoor mode.
             * 
             * @param outdoor: 1 if it should use outdoor wifi settings, 0 otherwise
             */
            public default void onOutdoorSettingsChanged(int outdoor) {}
        }

        private static void outdoorsettingschanged(Callback cb, int outdoor) {
            try {
                cb.onOutdoorSettingsChanged(outdoor);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.WifiSettingsState.outdoorSettingsChanged [outdoor: " + outdoor + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Mavlink flight plans commands 
     */
    public static class Mavlink {

        /** Feature uid. */
        public static final int UID = 0x000B;

        /**
         * Start a FlightPlan based on a mavlink file existing on the drone.  Requirements are: * Product is calibrated
         * * Product should be in outdoor mode * Product has fixed its GPS
         * 
         * @param filepath: flight plan file path from the mavlink ftp root
         * @param type:
         */
        public static ArsdkCommand encodeStart(String filepath, @NonNull MavlinkStartType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStart(cmd.getNativePtr(), filepath, type.value);
            return cmd;
        }

        /**
         * Pause a FlightPlan that was playing. To unpause a FlightPlan, see [StartFlightPlan](#0-11-0)
         */
        public static ArsdkCommand encodePause() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePause(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Stop a FlightPlan that was playing.
         */
        public static ArsdkCommand encodeStop() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStop(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeStart(long nativeCmd, String filepath, int type);
        private static native int nativeEncodePause(long nativeCmd);
        private static native int nativeEncodeStop(long nativeCmd);
    }

    /**
     * Mavlink flight plans states commands 
     */
    public static class MavlinkState {

        /** Feature uid. */
        public static final int UID = 0x000C;

        /** Uid of MavlinkFilePlayingStateChanged event. */;
        public static final int MAVLINKFILEPLAYINGSTATECHANGED_UID = 0x0000;

        /** Uid of MavlinkPlayErrorStateChanged event. */;
        public static final int MAVLINKPLAYERRORSTATECHANGED_UID = 0x0001;

        /** Uid of MissionItemExecuted event. */;
        public static final int MISSIONITEMEXECUTED_UID = 0x0002;

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
             * Playing state of a FlightPlan.
             * 
             * @param state:
             * @param filepath: flight plan file path from the mavlink ftp root
             * @param type:
             */
            public default void onMavlinkFilePlayingStateChanged(@Nullable MavlinkstateMavlinkfileplayingstatechangedState state, String filepath, @Nullable MavlinkstateMavlinkfileplayingstatechangedType type) {}

            /**
             * FlightPlan error.
             * 
             * @param error:
             */
            @Deprecated
            public default void onMavlinkPlayErrorStateChanged(@Nullable MavlinkstateMavlinkplayerrorstatechangedError error) {}

            /**
             * Mission item has been executed.
             * 
             * @param idx: Index of the mission item. This is the place of the mission item in the list of the items of
             * the mission. Begins at 0.
             */
            public default void onMissionItemExecuted(long idx) {}
        }

        private static void mavlinkfileplayingstatechanged(Callback cb, int state, String filepath, int type) {
            ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedState enumState = ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedState value " + state);
            ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedType enumType = ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.MavlinkstateMavlinkfileplayingstatechangedType value " + type);
            try {
                cb.onMavlinkFilePlayingStateChanged(enumState, filepath, enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.MavlinkState.MavlinkFilePlayingStateChanged [state: " + state + ", filepath: " + filepath + ", type: " + type + "]", e);
            }
        }

        private static void mavlinkplayerrorstatechanged(Callback cb, int error) {
            ArsdkFeatureCommon.MavlinkstateMavlinkplayerrorstatechangedError enumError = ArsdkFeatureCommon.MavlinkstateMavlinkplayerrorstatechangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.MavlinkstateMavlinkplayerrorstatechangedError value " + error);
            try {
                cb.onMavlinkPlayErrorStateChanged(enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.MavlinkState.MavlinkPlayErrorStateChanged [error: " + error + "]", e);
            }
        }

        private static void missionitemexecuted(Callback cb, long idx) {
            try {
                cb.onMissionItemExecuted(idx);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.MavlinkState.MissionItemExecuted [idx: " + idx + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Calibration commands 
     */
    public static class Calibration {

        /** Feature uid. */
        public static final int UID = 0x000D;

        /**
         * Start or abort magnetometer calibration process.
         * 
         * @param calibrate: 1 if the calibration should be started, 0 if it should be aborted
         */
        public static ArsdkCommand encodeMagnetoCalibration(int calibrate) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMagnetoCalibration(cmd.getNativePtr(), calibrate);
            return cmd;
        }

        /**
         * Start or abort Pitot tube calibration process.
         * 
         * @param calibrate: 1 if the calibration should be started, 0 if it should be aborted
         */
        public static ArsdkCommand encodePitotCalibration(int calibrate) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePitotCalibration(cmd.getNativePtr(), calibrate);
            return cmd;
        }

        private static native int nativeEncodeMagnetoCalibration(long nativeCmd, int calibrate);
        private static native int nativeEncodePitotCalibration(long nativeCmd, int calibrate);
    }

    /**
     * Status of the calibration 
     */
    public static class CalibrationState {

        /** Feature uid. */
        public static final int UID = 0x000E;

        /** Uid of MagnetoCalibrationStateChanged event. */;
        public static final int MAGNETOCALIBRATIONSTATECHANGED_UID = 0x0000;

        /** Uid of MagnetoCalibrationRequiredState event. */;
        public static final int MAGNETOCALIBRATIONREQUIREDSTATE_UID = 0x0001;

        /** Uid of MagnetoCalibrationAxisToCalibrateChanged event. */;
        public static final int MAGNETOCALIBRATIONAXISTOCALIBRATECHANGED_UID = 0x0002;

        /** Uid of MagnetoCalibrationStartedChanged event. */;
        public static final int MAGNETOCALIBRATIONSTARTEDCHANGED_UID = 0x0003;

        /** Uid of PitotCalibrationStateChanged event. */;
        public static final int PITOTCALIBRATIONSTATECHANGED_UID = 0x0004;

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
             * Magneto calib process axis state.
             * 
             * @param xaxiscalibration: State of the x axis (roll) calibration : 1 if calibration is done, 0 otherwise
             * @param yaxiscalibration: State of the y axis (pitch) calibration : 1 if calibration is done, 0 otherwise
             * @param zaxiscalibration: State of the z axis (yaw) calibration : 1 if calibration is done, 0 otherwise
             * @param calibrationfailed: 1 if calibration has failed, 0 otherwise. If this arg is 1, consider all
             * previous arg as 0
             */
            public default void onMagnetoCalibrationStateChanged(int xaxiscalibration, int yaxiscalibration, int zaxiscalibration, int calibrationfailed) {}

            /**
             * Calibration required.
             * 
             * @param required: 1 if calibration is required, 0 if current calibration is still valid
             */
            public default void onMagnetoCalibrationRequiredState(int required) {}

            /**
             * Axis to calibrate during calibration process.
             * 
             * @param axis:
             */
            public default void onMagnetoCalibrationAxisToCalibrateChanged(@Nullable CalibrationstateMagnetocalibrationaxistocalibratechangedAxis axis) {}

            /**
             * Calibration process state.
             * 
             * @param started: 1 if calibration has started, 0 otherwise
             */
            public default void onMagnetoCalibrationStartedChanged(int started) {}

            /**
             * Sent when the state of the pitot calibration has changed
             * 
             * @param state:
             * @param lasterror: lastError : 1 if an error occured and 0 if not
             */
            public default void onPitotCalibrationStateChanged(@Nullable CalibrationstatePitotcalibrationstatechangedState state, int lasterror) {}
        }

        private static void magnetocalibrationstatechanged(Callback cb, int xaxiscalibration, int yaxiscalibration, int zaxiscalibration, int calibrationfailed) {
            try {
                cb.onMagnetoCalibrationStateChanged(xaxiscalibration, yaxiscalibration, zaxiscalibration, calibrationfailed);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CalibrationState.MagnetoCalibrationStateChanged [xAxisCalibration: " + xaxiscalibration + ", yAxisCalibration: " + yaxiscalibration + ", zAxisCalibration: " + zaxiscalibration + ", calibrationFailed: " + calibrationfailed + "]", e);
            }
        }

        private static void magnetocalibrationrequiredstate(Callback cb, int required) {
            try {
                cb.onMagnetoCalibrationRequiredState(required);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CalibrationState.MagnetoCalibrationRequiredState [required: " + required + "]", e);
            }
        }

        private static void magnetocalibrationaxistocalibratechanged(Callback cb, int axis) {
            ArsdkFeatureCommon.CalibrationstateMagnetocalibrationaxistocalibratechangedAxis enumAxis = ArsdkFeatureCommon.CalibrationstateMagnetocalibrationaxistocalibratechangedAxis.fromValue(axis);
            if (enumAxis == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.CalibrationstateMagnetocalibrationaxistocalibratechangedAxis value " + axis);
            try {
                cb.onMagnetoCalibrationAxisToCalibrateChanged(enumAxis);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CalibrationState.MagnetoCalibrationAxisToCalibrateChanged [axis: " + axis + "]", e);
            }
        }

        private static void magnetocalibrationstartedchanged(Callback cb, int started) {
            try {
                cb.onMagnetoCalibrationStartedChanged(started);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CalibrationState.MagnetoCalibrationStartedChanged [started: " + started + "]", e);
            }
        }

        private static void pitotcalibrationstatechanged(Callback cb, int state, int lasterror) {
            ArsdkFeatureCommon.CalibrationstatePitotcalibrationstatechangedState enumState = ArsdkFeatureCommon.CalibrationstatePitotcalibrationstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.CalibrationstatePitotcalibrationstatechangedState value " + state);
            try {
                cb.onPitotCalibrationStateChanged(enumState, lasterror);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CalibrationState.PitotCalibrationStateChanged [state: " + state + ", lastError: " + lasterror + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Status of the camera settings 
     */
    public static class CameraSettingsState {

        /** Feature uid. */
        public static final int UID = 0x000F;

        /** Uid of CameraSettingsChanged event. */;
        public static final int CAMERASETTINGSCHANGED_UID = 0x0000;

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
             * Camera info.
             * 
             * @param fov: Value of the camera horizontal fov (in degree)
             * @param panmax: Value of max pan (right pan) (in degree)
             * @param panmin: Value of min pan (left pan) (in degree)
             * @param tiltmax: Value of max tilt (top tilt) (in degree)
             * @param tiltmin: Value of min tilt (bottom tilt) (in degree)
             */
            public default void onCameraSettingsChanged(float fov, float panmax, float panmin, float tiltmax, float tiltmin) {}
        }

        private static void camerasettingschanged(Callback cb, float fov, float panmax, float panmin, float tiltmax, float tiltmin) {
            try {
                cb.onCameraSettingsChanged(fov, panmax, panmin, tiltmax, tiltmin);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.CameraSettingsState.CameraSettingsChanged [fov: " + fov + ", panMax: " + panmax + ", panMin: " + panmin + ", tiltMax: " + tiltmax + ", tiltMin: " + tiltmin + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * GPS related commands 
     */
    public static class GPS {

        /** Feature uid. */
        public static final int UID = 0x0010;

        /**
         * Set the position of a run. This will let the product know the controller location for the flight/run. The
         * location is typically used to geotag medias. Only used on products that have no gps. Watch out, this command
         * is not used by BLE products.
         * 
         * @param latitude: Controller latitude in decimal degrees
         * @param longitude: Controller longitude in decimal degrees
         */
        public static ArsdkCommand encodeControllerPositionForRun(double latitude, double longitude) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerPositionForRun(cmd.getNativePtr(), latitude, longitude);
            return cmd;
        }

        private static native int nativeEncodeControllerPositionForRun(long nativeCmd, double latitude, double longitude);
    }

    /**
     * FlightPlan state commands 
     */
    public static class FlightPlanState {

        /** Feature uid. */
        public static final int UID = 0x0011;

        /** Uid of AvailabilityStateChanged event. */;
        public static final int AVAILABILITYSTATECHANGED_UID = 0x0000;

        /** Uid of ComponentStateListChanged event. */;
        public static final int COMPONENTSTATELISTCHANGED_UID = 0x0001;

        /** Uid of LockStateChanged event. */;
        public static final int LOCKSTATECHANGED_UID = 0x0002;

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
             * FlightPlan availability. Availability is linked to GPS fix, magnetometer calibration, sensor states...
             * 
             * @param availabilitystate: Running a flightPlan file is available (1 running a flightPlan file is
             * available, otherwise 0)
             */
            public default void onAvailabilityStateChanged(int availabilitystate) {}

            /**
             * FlightPlan components state list.
             * 
             * @param component:
             * @param state: State of the FlightPlan component (1 FlightPlan component OK, otherwise 0)
             */
            public default void onComponentStateListChanged(@Nullable FlightplanstateComponentstatelistchangedComponent component, int state) {}

            /**
             * FlightPlan lock state. Represents the fact that the controller is able or not to stop or pause a playing
             * FlightPlan
             * 
             * @param lockstate: 1 if FlightPlan is locked: can't pause or stop FlightPlan. 0 if FlightPlan is unlocked:
             * pause or stop available.
             */
            public default void onLockStateChanged(int lockstate) {}
        }

        private static void availabilitystatechanged(Callback cb, int availabilitystate) {
            try {
                cb.onAvailabilityStateChanged(availabilitystate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanState.AvailabilityStateChanged [AvailabilityState: " + availabilitystate + "]", e);
            }
        }

        private static void componentstatelistchanged(Callback cb, int component, int state) {
            ArsdkFeatureCommon.FlightplanstateComponentstatelistchangedComponent enumComponent = ArsdkFeatureCommon.FlightplanstateComponentstatelistchangedComponent.fromValue(component);
            if (enumComponent == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.FlightplanstateComponentstatelistchangedComponent value " + component);
            try {
                cb.onComponentStateListChanged(enumComponent, state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanState.ComponentStateListChanged [component: " + component + ", State: " + state + "]", e);
            }
        }

        private static void lockstatechanged(Callback cb, int lockstate) {
            try {
                cb.onLockStateChanged(lockstate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanState.LockStateChanged [LockState: " + lockstate + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * ARlibs Versions Commands 
     */
    public static class ARLibsVersionsState {

        /** Feature uid. */
        public static final int UID = 0x0012;

        /** Uid of ControllerLibARCommandsVersion event. */;
        public static final int CONTROLLERLIBARCOMMANDSVERSION_UID = 0x0000;

        /** Uid of SkyControllerLibARCommandsVersion event. */;
        public static final int SKYCONTROLLERLIBARCOMMANDSVERSION_UID = 0x0001;

        /** Uid of DeviceLibARCommandsVersion event. */;
        public static final int DEVICELIBARCOMMANDSVERSION_UID = 0x0002;

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
             * Controller libARCommands version
             * 
             * @param version: version of libARCommands ("1.2.3.4" format)
             */
            public default void onControllerLibARCommandsVersion(String version) {}

            /**
             * SkyController libARCommands version
             * 
             * @param version: version of libARCommands ("1.2.3.4" format)
             */
            public default void onSkyControllerLibARCommandsVersion(String version) {}

            /**
             * Device libARCommands version
             * 
             * @param version: version of libARCommands ("1.2.3.4" format)
             */
            public default void onDeviceLibARCommandsVersion(String version) {}
        }

        private static void controllerlibarcommandsversion(Callback cb, String version) {
            try {
                cb.onControllerLibARCommandsVersion(version);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ARLibsVersionsState.ControllerLibARCommandsVersion [version: " + version + "]", e);
            }
        }

        private static void skycontrollerlibarcommandsversion(Callback cb, String version) {
            try {
                cb.onSkyControllerLibARCommandsVersion(version);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ARLibsVersionsState.SkyControllerLibARCommandsVersion [version: " + version + "]", e);
            }
        }

        private static void devicelibarcommandsversion(Callback cb, String version) {
            try {
                cb.onDeviceLibARCommandsVersion(version);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ARLibsVersionsState.DeviceLibARCommandsVersion [version: " + version + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * FlightPlan Event commands 
     */
    public static class FlightPlanEvent {

        /** Feature uid. */
        public static final int UID = 0x0013;

        /** Uid of StartingErrorEvent event. */;
        public static final int STARTINGERROREVENT_UID = 0x0000;

        /** Uid of SpeedBridleEvent event. */;
        public static final int SPEEDBRIDLEEVENT_UID = 0x0001;

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
             * FlightPlan start error.  **This event is a notification, you can't retrieve it in the cache of the device
             * controller.**
             */
            public default void onStartingErrorEvent() {}

            /**
             * FlightPlan speed clamping. Sent when a speed specified in the FlightPlan file is considered too high by
             * the drone.  **This event is a notification, you can't retrieve it in the cache of the device
             * controller.**
             */
            public default void onSpeedBridleEvent() {}
        }

        private static void startingerrorevent(Callback cb) {
            try {
                cb.onStartingErrorEvent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanEvent.StartingErrorEvent", e);
            }
        }

        private static void speedbridleevent(Callback cb) {
            try {
                cb.onSpeedBridleEvent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanEvent.SpeedBridleEvent", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Audio-related commands. 
     */
    public static class Audio {

        /** Feature uid. */
        public static final int UID = 0x0014;

        /**
         * Set audio stream direction.
         * 
         * @param ready: Bit field for TX and RX ready. bit 0 is 1 if controller is ready and wants to receive sound
         * (Drone TX) bit 1 is 1 if controller is ready and wants to send sound (Drone RX)
         */
        public static ArsdkCommand encodeControllerReadyForStreaming(int ready) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerReadyForStreaming(cmd.getNativePtr(), ready);
            return cmd;
        }

        private static native int nativeEncodeControllerReadyForStreaming(long nativeCmd, int ready);
    }

    /**
     * Audio-related state updates. 
     */
    public static class AudioState {

        /** Feature uid. */
        public static final int UID = 0x0015;

        /** Uid of AudioStreamingRunning event. */;
        public static final int AUDIOSTREAMINGRUNNING_UID = 0x0000;

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
             * Audio stream direction.
             * 
             * @param running: Bit field for TX and RX running bit 0 is 1 if Drone TX is running bit 1 is 1 if Drone RX
             * is running
             */
            public default void onAudioStreamingRunning(int running) {}
        }

        private static void audiostreamingrunning(Callback cb, int running) {
            try {
                cb.onAudioStreamingRunning(running);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.AudioState.AudioStreamingRunning [running: " + running + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Controls the headlight LEDs of the Evo variants. 
     */
    public static class Headlights {

        /** Feature uid. */
        public static final int UID = 0x0016;

        /**
         * Set lighting LEDs intensity.
         * 
         * @param left: Set the left LED intensity value (0 through 255).
         * @param right: Set the right LED intensity value (0 through 255).
         */
        public static ArsdkCommand encodeIntensity(int left, int right) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeIntensity(cmd.getNativePtr(), left, right);
            return cmd;
        }

        private static native int nativeEncodeIntensity(long nativeCmd, int left, int right);
    }

    /**
     * Get information about the state of the Evo variants' LEDs. 
     */
    public static class HeadlightsState {

        /** Feature uid. */
        public static final int UID = 0x0017;

        /** Uid of intensityChanged event. */;
        public static final int INTENSITYCHANGED_UID = 0x0000;

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
             * Lighting LEDs intensity.
             * 
             * @param left: The intensity value for the left LED (0 through 255).
             * @param right: The intensity value for the right LED (0 through 255).
             */
            public default void onIntensityChanged(int left, int right) {}
        }

        private static void intensitychanged(Callback cb, int left, int right) {
            try {
                cb.onIntensityChanged(left, right);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.HeadlightsState.intensityChanged [left: " + left + ", right: " + right + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Animations-related commands. 
     */
    public static class Animations {

        /** Feature uid. */
        public static final int UID = 0x0018;

        /**
         * Start a paramaterless animation. List of available animations can be retrieved from
         * [AnimationsStateList](#0-25-0).
         * 
         * @param anim:
         */
        public static ArsdkCommand encodeStartAnimation(@NonNull AnimationsStartanimationAnim anim) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStartAnimation(cmd.getNativePtr(), anim.value);
            return cmd;
        }

        /**
         * Stop a paramaterless animation. List of running animations can be retrieved from
         * [AnimationsStateList](#0-25-0).
         * 
         * @param anim:
         */
        public static ArsdkCommand encodeStopAnimation(@NonNull AnimationsStopanimationAnim anim) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStopAnimation(cmd.getNativePtr(), anim.value);
            return cmd;
        }

        /**
         * Stop all running paramaterless animations. List of running animations can be retrieved from
         * [AnimationsStateList](#0-25-0).
         */
        public static ArsdkCommand encodeStopAllAnimations() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStopAllAnimations(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeStartAnimation(long nativeCmd, int anim);
        private static native int nativeEncodeStopAnimation(long nativeCmd, int anim);
        private static native int nativeEncodeStopAllAnimations(long nativeCmd);
    }

    /**
     * Animations-related notification/feedback commands. 
     */
    public static class AnimationsState {

        /** Feature uid. */
        public static final int UID = 0x0019;

        /** Uid of List event. */;
        public static final int LIST_UID = 0x0000;

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
             * Paramaterless animations state list.
             * 
             * @param anim:
             * @param state:
             * @param error:
             */
            public default void onList(@Nullable AnimationsstateListAnim anim, @Nullable AnimationsstateListState state, @Nullable AnimationsstateListError error) {}
        }

        private static void list(Callback cb, int anim, int state, int error) {
            ArsdkFeatureCommon.AnimationsstateListAnim enumAnim = ArsdkFeatureCommon.AnimationsstateListAnim.fromValue(anim);
            if (enumAnim == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AnimationsstateListAnim value " + anim);
            ArsdkFeatureCommon.AnimationsstateListState enumState = ArsdkFeatureCommon.AnimationsstateListState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AnimationsstateListState value " + state);
            ArsdkFeatureCommon.AnimationsstateListError enumError = ArsdkFeatureCommon.AnimationsstateListError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AnimationsstateListError value " + error);
            try {
                cb.onList(enumAnim, enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.AnimationsState.List [anim: " + anim + ", state: " + state + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Accessories-related commands. 
     */
    public static class Accessory {

        /** Feature uid. */
        public static final int UID = 0x001A;

        /**
         * Declare an accessory. You can choose the accessory between all accessible for this product. You can get this
         * list through event [SupportedAccessories](#0-27-0).  You can only set the accessory when the modification is
         * enabled. You can know if it possible with the event [AccessoryDeclarationAvailability](#0-27-2).
         * 
         * @param accessory:
         */
        public static ArsdkCommand encodeConfig(@NonNull AccessoryConfigAccessory accessory) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeConfig(cmd.getNativePtr(), accessory.value);
            return cmd;
        }

        private static native int nativeEncodeConfig(long nativeCmd, int accessory);
    }

    /**
     * Accessories-related commands. 
     */
    public static class AccessoryState {

        /** Feature uid. */
        public static final int UID = 0x001B;

        /** Uid of SupportedAccessoriesListChanged event. */;
        public static final int SUPPORTEDACCESSORIESLISTCHANGED_UID = 0x0000;

        /** Uid of AccessoryConfigChanged event. */;
        public static final int ACCESSORYCONFIGCHANGED_UID = 0x0001;

        /** Uid of AccessoryConfigModificationEnabled event. */;
        public static final int ACCESSORYCONFIGMODIFICATIONENABLED_UID = 0x0002;

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
             * Supported accessories list.
             * 
             * @param accessory:
             */
            public default void onSupportedAccessoriesListChanged(@Nullable AccessorystateSupportedaccessorieslistchangedAccessory accessory) {}

            /**
             * Accessory config.
             * 
             * @param newaccessory:
             * @param error:
             */
            public default void onAccessoryConfigChanged(@Nullable AccessorystateAccessoryconfigchangedNewaccessory newaccessory, @Nullable AccessorystateAccessoryconfigchangedError error) {}

            /**
             * Availability to declare or not an accessory.
             * 
             * @param enabled: 1 if the modification of the accessory Config is enabled, 0 otherwise
             */
            public default void onAccessoryConfigModificationEnabled(int enabled) {}
        }

        private static void supportedaccessorieslistchanged(Callback cb, int accessory) {
            ArsdkFeatureCommon.AccessorystateSupportedaccessorieslistchangedAccessory enumAccessory = ArsdkFeatureCommon.AccessorystateSupportedaccessorieslistchangedAccessory.fromValue(accessory);
            if (enumAccessory == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AccessorystateSupportedaccessorieslistchangedAccessory value " + accessory);
            try {
                cb.onSupportedAccessoriesListChanged(enumAccessory);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.AccessoryState.SupportedAccessoriesListChanged [accessory: " + accessory + "]", e);
            }
        }

        private static void accessoryconfigchanged(Callback cb, int newaccessory, int error) {
            ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedNewaccessory enumNewaccessory = ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedNewaccessory.fromValue(newaccessory);
            if (enumNewaccessory == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedNewaccessory value " + newaccessory);
            ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedError enumError = ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.AccessorystateAccessoryconfigchangedError value " + error);
            try {
                cb.onAccessoryConfigChanged(enumNewaccessory, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.AccessoryState.AccessoryConfigChanged [newAccessory: " + newaccessory + ", error: " + error + "]", e);
            }
        }

        private static void accessoryconfigmodificationenabled(Callback cb, int enabled) {
            try {
                cb.onAccessoryConfigModificationEnabled(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.AccessoryState.AccessoryConfigModificationEnabled [enabled: " + enabled + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Commands sent by the controller to set charger parameters. 
     */
    public static class Charger {

        /** Feature uid. */
        public static final int UID = 0x001C;

        /**
         * The product will inform itself the controller about its charging type (see [ChargingInfoChanged](#0-29-3)).
         * 
         * @param rate:
         */
        @Deprecated
        public static ArsdkCommand encodeSetMaxChargeRate(@NonNull ChargerSetmaxchargerateRate rate) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetMaxChargeRate(cmd.getNativePtr(), rate.value);
            return cmd;
        }

        private static native int nativeEncodeSetMaxChargeRate(long nativeCmd, int rate);
    }

    /**
     * Commands sent by the firmware to advertise the charger status. 
     */
    public static class ChargerState {

        /** Feature uid. */
        public static final int UID = 0x001D;

        /** Uid of MaxChargeRateChanged event. */;
        public static final int MAXCHARGERATECHANGED_UID = 0x0000;

        /** Uid of CurrentChargeStateChanged event. */;
        public static final int CURRENTCHARGESTATECHANGED_UID = 0x0001;

        /** Uid of LastChargeRateChanged event. */;
        public static final int LASTCHARGERATECHANGED_UID = 0x0002;

        /** Uid of ChargingInfo event. */;
        public static final int CHARGINGINFO_UID = 0x0003;

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
             * Max charge rate.
             * 
             * @param rate:
             */
            @Deprecated
            public default void onMaxChargeRateChanged(@Nullable ChargerstateMaxchargeratechangedRate rate) {}

            /**
             * Current charge state.
             * 
             * @param status:
             * @param phase:
             */
            @Deprecated
            public default void onCurrentChargeStateChanged(@Nullable ChargerstateCurrentchargestatechangedStatus status, @Nullable ChargerstateCurrentchargestatechangedPhase phase) {}

            /**
             * Last charge rate.
             * 
             * @param rate:
             */
            @Deprecated
            public default void onLastChargeRateChanged(@Nullable ChargerstateLastchargeratechangedRate rate) {}

            /**
             * Charging information.
             * 
             * @param phase:
             * @param rate:
             * @param intensity: The charging intensity, in dA. (12dA = 1,2A) ; If phase is DISCHARGING, refers to the
             * last charge. Equals to 0 if not known.
             * @param fullchargingtime: The full charging time estimated, in minute. If phase is DISCHARGING, refers to
             * the last charge. Equals to 0 if not known.
             */
            public default void onChargingInfo(@Nullable ChargerstateCharginginfoPhase phase, @Nullable ChargerstateCharginginfoRate rate, int intensity, int fullchargingtime) {}
        }

        private static void maxchargeratechanged(Callback cb, int rate) {
            ArsdkFeatureCommon.ChargerstateMaxchargeratechangedRate enumRate = ArsdkFeatureCommon.ChargerstateMaxchargeratechangedRate.fromValue(rate);
            if (enumRate == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateMaxchargeratechangedRate value " + rate);
            try {
                cb.onMaxChargeRateChanged(enumRate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ChargerState.MaxChargeRateChanged [rate: " + rate + "]", e);
            }
        }

        private static void currentchargestatechanged(Callback cb, int status, int phase) {
            ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedStatus enumStatus = ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedStatus value " + status);
            ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedPhase enumPhase = ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedPhase.fromValue(phase);
            if (enumPhase == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateCurrentchargestatechangedPhase value " + phase);
            try {
                cb.onCurrentChargeStateChanged(enumStatus, enumPhase);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ChargerState.CurrentChargeStateChanged [status: " + status + ", phase: " + phase + "]", e);
            }
        }

        private static void lastchargeratechanged(Callback cb, int rate) {
            ArsdkFeatureCommon.ChargerstateLastchargeratechangedRate enumRate = ArsdkFeatureCommon.ChargerstateLastchargeratechangedRate.fromValue(rate);
            if (enumRate == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateLastchargeratechangedRate value " + rate);
            try {
                cb.onLastChargeRateChanged(enumRate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ChargerState.LastChargeRateChanged [rate: " + rate + "]", e);
            }
        }

        private static void charginginfo(Callback cb, int phase, int rate, int intensity, int fullchargingtime) {
            ArsdkFeatureCommon.ChargerstateCharginginfoPhase enumPhase = ArsdkFeatureCommon.ChargerstateCharginginfoPhase.fromValue(phase);
            if (enumPhase == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateCharginginfoPhase value " + phase);
            ArsdkFeatureCommon.ChargerstateCharginginfoRate enumRate = ArsdkFeatureCommon.ChargerstateCharginginfoRate.fromValue(rate);
            if (enumRate == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.ChargerstateCharginginfoRate value " + rate);
            try {
                cb.onChargingInfo(enumPhase, enumRate, intensity, fullchargingtime);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.ChargerState.ChargingInfo [phase: " + phase + ", rate: " + rate + ", intensity: " + intensity + ", fullChargingTime: " + fullchargingtime + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Commands sent by the drone to inform about the run or flight state 
     */
    public static class RunState {

        /** Feature uid. */
        public static final int UID = 0x001E;

        /** Uid of RunIdChanged event. */;
        public static final int RUNIDCHANGED_UID = 0x0000;

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
             * Current run id. A run id is uniquely identifying a run or a flight. For each run is generated on the
             * drone a file which can be used by Academy to sum up the run. Also, each medias taken during a run has a
             * filename containing the run id.
             * 
             * @param runid: Id of the run
             */
            public default void onRunIdChanged(String runid) {}
        }

        private static void runidchanged(Callback cb, String runid) {
            try {
                cb.onRunIdChanged(runid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.RunState.RunIdChanged [runId: " + runid + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Factory reset commands 
     */
    public static class Factory {

        /** Feature uid. */
        public static final int UID = 0x001F;

        /**
         * This command will request a factory reset from the prodcut. *The factory reset procedure implies an automatic
         * reboot*, which will be done immediately after receiving this command.
         */
        public static ArsdkCommand encodeReset() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReset(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeReset(long nativeCmd);
    }

    /**
     *  
     */
    public static class FlightPlanSettings {

        /** Feature uid. */
        public static final int UID = 0x0020;

        /**
         * Set ReturnHome behavior during FlightPlan When set, drone will return home, after return home delay, if a
         * disconnection occurs during execution of FlightPlan
         * 
         * @param value: 1 to enable, 0 to disable
         */
        public static ArsdkCommand encodeReturnHomeOnDisconnect(int value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReturnHomeOnDisconnect(cmd.getNativePtr(), value);
            return cmd;
        }

        private static native int nativeEncodeReturnHomeOnDisconnect(long nativeCmd, int value);
    }

    /**
     *  
     */
    public static class FlightPlanSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0021;

        /** Uid of ReturnHomeOnDisconnectChanged event. */;
        public static final int RETURNHOMEONDISCONNECTCHANGED_UID = 0x0000;

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
             * Define behavior of drone when disconnection occurs during a flight plan
             * 
             * @param state: 1 if enabled, 0 if disabled
             * @param isreadonly: 1 if readOnly, 0 if writable
             */
            public default void onReturnHomeOnDisconnectChanged(int state, int isreadonly) {}
        }

        private static void returnhomeondisconnectchanged(Callback cb, int state, int isreadonly) {
            try {
                cb.onReturnHomeOnDisconnectChanged(state, isreadonly);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.FlightPlanSettingsState.ReturnHomeOnDisconnectChanged [state: " + state + ", isReadOnly: " + isreadonly + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Update related commands 
     */
    public static class UpdateState {

        /** Feature uid. */
        public static final int UID = 0x0022;

        /** Uid of UpdateStateChanged event. */;
        public static final int UPDATESTATECHANGED_UID = 0x0000;

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
             * Status of the latest software update
             * 
             * @param sourceversion: Version before the update.
             * @param targetversion: Target version of the latest update. This is the version after the update when
             * `status` is `SUCCESS` or the version of the update that failed when `status` is one of the failure value.
             * @param status:
             */
            public default void onUpdateStateChanged(String sourceversion, String targetversion, @Nullable UpdatestateUpdatestatechangedStatus status) {}
        }

        private static void updatestatechanged(Callback cb, String sourceversion, String targetversion, int status) {
            ArsdkFeatureCommon.UpdatestateUpdatestatechangedStatus enumStatus = ArsdkFeatureCommon.UpdatestateUpdatestatechangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureCommon.UpdatestateUpdatestatechangedStatus value " + status);
            try {
                cb.onUpdateStateChanged(sourceversion, targetversion, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: common.UpdateState.UpdateStateChanged [sourceVersion: " + sourceversion + ", targetVersion: " + targetversion + ", status: " + status + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
