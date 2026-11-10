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
 * Powerup feature command/event interface.
 */
public class ArsdkFeaturePowerup {

    /**
     * .
     */
    public enum PilotingMotormodeMode {

        /**
         * The motors will only start on user action after being in state user take off.
         */
        NORMAL(0),

        /**
         * Motors will use the current pcmd values without considering the flying state.
         */
        FORCED(1);

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
        public static PilotingMotormodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingMotormodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingMotormodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingMotormodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * JS alert state.
     */
    public enum PilotingstateAlertstatechangedState {

        /**
         * No alert.
         */
        NONE(0),

        /**
         * Critical battery alert.
         */
        CRITICAL_BATTERY(1),

        /**
         * Low battery alert.
         */
        LOW_BATTERY(2);

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
        public static PilotingstateAlertstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateAlertstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateAlertstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateAlertstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Drone flying state.
     */
    public enum PilotingstateFlyingstatechangedState {

        /**
         * Landed state.
         */
        LANDED(0),

        /**
         * Taking off state.
         */
        TAKINGOFF(1),

        /**
         * Flying state.
         */
        FLYING(2),

        /**
         * Recovery state.
         */
        RECOVERY(3),

        /**
         * Emergency state.
         */
        EMERGENCY(4),

        /**
         * User take off state. Waiting for user action to take off.
         */
        USERTAKEOFF(5),

        /**
         * Initializing state (user should let the drone steady for a while).
         */
        INIT(6);

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
        public static PilotingstateFlyingstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateFlyingstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateFlyingstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateFlyingstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PilotingstateMotormodechangedMode {

        /**
         * The motors will only start on user action after being in state user take off.
         */
        NORMAL(0),

        /**
         * Motors will use the current pcmd values without considering the flying state.
         */
        FORCED(1);

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
        public static PilotingstateMotormodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateMotormodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateMotormodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateMotormodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Variety of setting that can be customized.
     */
    public enum PilotingsettingsSetSetting {

        /**
         * Max roll value. In degree.
         */
        MAX_ROLL(0),

        /**
         * How fast the plane reaches the desired roll angle. No unit.
         */
        ROLL_KP(1),

        /**
         * How fast the plane reaches the desired roll rate. No unit.
         */
        ROLL_RATE_KP(2),

        /**
         * Max pitch value. In degree.
         */
        MAX_PITCH(3),

        /**
         * Min pitch value. In degree.
         */
        MIN_PITCH(4),

        /**
         * How fast the plane reaches the desired pitch angle. No unit.
         */
        PITCH_KP(5),

        /**
         * How fast the plane reaches the desired pitch rate. No unit.
         */
        PITCH_RATE_KP(6),

        /**
         * How fast the plane reaches the desired yaw angle. No unit.
         */
        YAW_KP(7),

        /**
         * How fast the plane reaches the desired yaw rate. No unit.
         */
        YAW_RATE_KP(8),

        /**
         * Portion of thrust that is added to motors according to the roll/yaw command to compensate a dive during turn.
         * No unit.
         */
        ROLL_TO_THROTTLE_RATE(9),

        /**
         * Angle of attack of a plane needed horizontal flight.
         */
        ANGLE_OF_ATTACK(10),

        /**
         * Altitude hold during autopilot. 0 for false, other value for true.
         */
        ALT_HOLD(11),

        /**
         * Altitude hold throttle. Expressed in percentage divided by 100 (from 0 to 1).
         */
        ALT_HOLD_THROTTLE(12),

        /**
         * Rssi value that indicates that the airplane is close to the pilot.
         */
        DR_RSSI_EDGE(13),

        /**
         * Limit time for return home duration. In seconds.
         */
        RECOVERY_DURATION_LIMIT(14),

        /**
         * Wind speed in m/s. Should be sent before flight.
         */
        WIND_SPEED(15),

        /**
         * Target plane speed in m/s. Should be sent before flight.
         */
        PLANE_SPEED(16);

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
        public static PilotingsettingsSetSetting fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsSetSetting(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsSetSetting> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsSetSetting e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Variety of setting that can be customized.
     */
    public enum PilotingsettingsstateSettingchangedSetting {

        /**
         * Max roll value. In degree.
         */
        MAX_ROLL(0),

        /**
         * How fast the plane reaches the desired roll angle. No unit.
         */
        ROLL_KP(1),

        /**
         * How fast the plane reaches the desired roll rate. No unit.
         */
        ROLL_RATE_KP(2),

        /**
         * Max pitch value. In degree.
         */
        MAX_PITCH(3),

        /**
         * Min pitch value. In degree.
         */
        MIN_PITCH(4),

        /**
         * How fast the plane reaches the desired pitch angle. No unit.
         */
        PITCH_KP(5),

        /**
         * How fast the plane reaches the desired pitch rate. No unit.
         */
        PITCH_RATE_KP(6),

        /**
         * How fast the plane reaches the desired yaw angle. No unit.
         */
        YAW_KP(7),

        /**
         * How fast the plane reaches the desired yaw rate. No unit.
         */
        YAW_RATE_KP(8),

        /**
         * Portion of thrust that is added to motors according to the roll/yaw command to compensate a dive during turn.
         * No unit.
         */
        ROLL_TO_THROTTLE_RATE(9),

        /**
         * Angle of attack of a plane needed horizontal flight.
         */
        ANGLE_OF_ATTACK(10),

        /**
         * Altitude hold during autopilot. 0 for false, other value for true.
         */
        ALT_HOLD(11),

        /**
         * Altitude hold throttle. Expressed in percentage divided by 100 (from 0 to 1).
         */
        ALT_HOLD_THROTTLE(12),

        /**
         * Rssi value that indicates that the airplane is close to the pilot.
         */
        DR_RSSI_EDGE(13),

        /**
         * Limit time for return home duration. In seconds.
         */
        RECOVERY_DURATION_LIMIT(14),

        /**
         * Wind speed in m/s.
         */
        WIND_SPEED(15),

        /**
         * Target plane speed in m/s.
         */
        PLANE_SPEED(16);

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
        public static PilotingsettingsstateSettingchangedSetting fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsstateSettingchangedSetting(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsstateSettingchangedSetting> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsstateSettingchangedSetting e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Command to record video.
     */
    public enum MediarecordVideov2Record {

        /**
         * Stop the video recording.
         */
        STOP(0),

        /**
         * Start the video recording.
         */
        START(1);

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
        public static MediarecordVideov2Record fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordVideov2Record(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordVideov2Record> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordVideov2Record e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of device picture recording.
     */
    public enum MediarecordstatePicturestatechangedv2State {

        /**
         * The picture recording is ready.
         */
        READY(0),

        /**
         * The picture recording is busy.
         */
        BUSY(1),

        /**
         * The picture recording is not available.
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
        public static MediarecordstatePicturestatechangedv2State fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstatePicturestatechangedv2State(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstatePicturestatechangedv2State> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstatePicturestatechangedv2State e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the state.
     */
    public enum MediarecordstatePicturestatechangedv2Error {

        /**
         * No Error.
         */
        OK(0),

        /**
         * Unknown generic error.
         */
        UNKNOWN(1),

        /**
         * Picture camera is out of order.
         */
        CAMERA_KO(2),

        /**
         * Memory full ; cannot save one additional picture.
         */
        MEMORYFULL(3),

        /**
         * Battery is too low to start/keep recording.
         */
        LOWBATTERY(4);

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
        public static MediarecordstatePicturestatechangedv2Error fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstatePicturestatechangedv2Error(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstatePicturestatechangedv2Error> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstatePicturestatechangedv2Error e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of device video recording.
     */
    public enum MediarecordstateVideostatechangedv2State {

        /**
         * Video is stopped.
         */
        STOPPED(0),

        /**
         * Video is started.
         */
        STARTED(1),

        /**
         * The video recording is not available.
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
        public static MediarecordstateVideostatechangedv2State fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstateVideostatechangedv2State(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstateVideostatechangedv2State> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstateVideostatechangedv2State e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the state.
     */
    public enum MediarecordstateVideostatechangedv2Error {

        /**
         * No Error.
         */
        OK(0),

        /**
         * Unknown generic error.
         */
        UNKNOWN(1),

        /**
         * Video camera is out of order.
         */
        CAMERA_KO(2),

        /**
         * Memory full ; cannot save one additional video.
         */
        MEMORYFULL(3),

        /**
         * Battery is too low to start/keep recording.
         */
        LOWBATTERY(4);

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
        public static MediarecordstateVideostatechangedv2Error fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstateVideostatechangedv2Error(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstateVideostatechangedv2Error> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstateVideostatechangedv2Error e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Last event of picture recording.
     */
    public enum MediarecordeventPictureeventchangedEvent {

        /**
         * Picture taken and saved.
         */
        TAKEN(0),

        /**
         * Picture failed.
         */
        FAILED(1);

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
        public static MediarecordeventPictureeventchangedEvent fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordeventPictureeventchangedEvent(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordeventPictureeventchangedEvent> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordeventPictureeventchangedEvent e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the event.
     */
    public enum MediarecordeventPictureeventchangedError {

        /**
         * No Error.
         */
        OK(0),

        /**
         * Unknown generic error ; only when state is failed.
         */
        UNKNOWN(1),

        /**
         * Picture recording is busy ; only when state is failed.
         */
        BUSY(2),

        /**
         * Picture recording not available ; only when state is failed.
         */
        NOTAVAILABLE(3),

        /**
         * Memory full ; only when state is failed.
         */
        MEMORYFULL(4),

        /**
         * Battery is too low to record.
         */
        LOWBATTERY(5);

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
        public static MediarecordeventPictureeventchangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordeventPictureeventchangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordeventPictureeventchangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordeventPictureeventchangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Event of video recording.
     */
    public enum MediarecordeventVideoeventchangedEvent {

        /**
         * Video start.
         */
        START(0),

        /**
         * Video stop and saved.
         */
        STOP(1),

        /**
         * Video failed.
         */
        FAILED(2);

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
        public static MediarecordeventVideoeventchangedEvent fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordeventVideoeventchangedEvent(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordeventVideoeventchangedEvent> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordeventVideoeventchangedEvent e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the event.
     */
    public enum MediarecordeventVideoeventchangedError {

        /**
         * No Error.
         */
        OK(0),

        /**
         * Unknown generic error ; only when state is failed.
         */
        UNKNOWN(1),

        /**
         * Video recording is busy ; only when state is failed.
         */
        BUSY(2),

        /**
         * Video recording not available ; only when state is failed.
         */
        NOTAVAILABLE(3),

        /**
         * Memory full.
         */
        MEMORYFULL(4),

        /**
         * Battery is too low to record.
         */
        LOWBATTERY(5),

        /**
         * Video was auto stopped.
         */
        AUTOSTOPPED(6);

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
        public static MediarecordeventVideoeventchangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordeventVideoeventchangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordeventVideoeventchangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordeventVideoeventchangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi selection (auto, manual).
     */
    public enum NetworksettingsWifiselectionType {

        /**
         * Auto selection.
         */
        AUTO(0),

        /**
         * Manual selection.
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
        public static NetworksettingsWifiselectionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsWifiselectionType(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsWifiselectionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsWifiselectionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The allowed band(s) : 2.4 Ghz, 5 Ghz, or all.
     */
    public enum NetworksettingsWifiselectionBand {

        /**
         * 2.4 GHz band.
         */
        E2_4GHZ(0),

        /**
         * 5 GHz band.
         */
        E5GHZ(1),

        /**
         * Both 2.4 and 5 GHz bands.
         */
        ALL(2);

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
        public static NetworksettingsWifiselectionBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsWifiselectionBand(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsWifiselectionBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsWifiselectionBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi selection settings.
     */
    public enum NetworksettingsstateWifiselectionchangedType {

        /**
         * Auto selection.
         */
        AUTO_ALL(0),

        /**
         * Auto selection 2.4ghz.
         */
        AUTO_2_4GHZ(1),

        /**
         * Auto selection 5 ghz.
         */
        AUTO_5GHZ(2),

        /**
         * Manual selection.
         */
        MANUAL(3);

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
        public static NetworksettingsstateWifiselectionchangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsstateWifiselectionchangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsstateWifiselectionchangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsstateWifiselectionchangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The actual wifi band state.
     */
    public enum NetworksettingsstateWifiselectionchangedBand {

        /**
         * 2.4 GHz band.
         */
        E2_4GHZ(0),

        /**
         * 5 GHz band.
         */
        E5GHZ(1),

        /**
         * Both 2.4 and 5 GHz bands.
         */
        ALL(2);

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
        public static NetworksettingsstateWifiselectionchangedBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsstateWifiselectionchangedBand(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsstateWifiselectionchangedBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsstateWifiselectionchangedBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The band(s) : 2.4 Ghz, 5 Ghz, or both.
     */
    public enum NetworkWifiscanBand {

        /**
         * 2.4 GHz band.
         */
        E2_4GHZ(0),

        /**
         * 5 GHz band.
         */
        E5GHZ(1),

        /**
         * Both 2.4 and 5 GHz bands.
         */
        ALL(2);

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
        public static NetworkWifiscanBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworkWifiscanBand(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworkWifiscanBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworkWifiscanBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The band : 2.4 GHz or 5 GHz.
     */
    public enum NetworkstateWifiscanlistchangedBand {

        /**
         * 2.4 GHz band.
         */
        E2_4GHZ(0),

        /**
         * 5 GHz band.
         */
        E5GHZ(1);

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
        public static NetworkstateWifiscanlistchangedBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworkstateWifiscanlistchangedBand(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworkstateWifiscanlistchangedBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworkstateWifiscanlistchangedBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The band of this channel : 2.4 GHz or 5 GHz.
     */
    public enum NetworkstateWifiauthchannellistchangedBand {

        /**
         * 2.4 GHz band.
         */
        E2_4GHZ(0),

        /**
         * 5 GHz band.
         */
        E5GHZ(1);

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
        public static NetworkstateWifiauthchannellistchangedBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworkstateWifiauthchannellistchangedBand(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworkstateWifiauthchannellistchangedBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworkstateWifiauthchannellistchangedBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Current video streaming status.
     */
    public enum MediastreamingstateVideoenablechangedEnabled {

        /**
         * Video streaming is enabled.
         */
        ENABLED(0),

        /**
         * Video streaming is disabled.
         */
        DISABLED(1),

        /**
         * Video streaming failed to start.
         */
        ERROR(2);

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
        public static MediastreamingstateVideoenablechangedEnabled fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediastreamingstateVideoenablechangedEnabled(int value) {
            this.value = value;
        }

        private static final SparseArray<MediastreamingstateVideoenablechangedEnabled> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediastreamingstateVideoenablechangedEnabled e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video mode.
     */
    public enum VideosettingsVideomodeMode {

        /**
         * Maximize video quality (VGA 30fps).
         */
        QUALITY(0),

        /**
         * Maximize video performance (QVGA 24fps).
         */
        PERFORMANCE(1);

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
        public static VideosettingsVideomodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsVideomodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsVideomodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsVideomodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video mode.
     */
    public enum VideosettingsstateVideomodechangedMode {

        /**
         * Maximize video quality (VGA 30fps).
         */
        QUALITY(0),

        /**
         * Maximize video performance (QVGA 24fps).
         */
        PERFORMANCE(1);

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
        public static VideosettingsstateVideomodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsstateVideomodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsstateVideomodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsstateVideomodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * All commands related to piloting the PowerUp 
     */
    public static class Piloting {

        /** Feature uid. */
        public static final int UID = 0x0800;

        /**
         * Ask the Power Up speed and turn ratio.
         * 
         * @param flag: Boolean for "touch screen".
         * @param throttle: Throttle value [0:100].
         * @param roll: Yaw-roll value. [-100:100]
         */
        public static ArsdkCommand encodePCMD(int flag, int throttle, int roll) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePCMD(cmd.getNativePtr(), flag, throttle, roll);
            return cmd;
        }

        /**
         * Set drone in user take off state
         * 
         * @param state: State of user take off mode - 1 to enter in user take off. - 0 to exit from user take off.
         */
        public static ArsdkCommand encodeUserTakeOff(int state) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeUserTakeOff(cmd.getNativePtr(), state);
            return cmd;
        }

        /**
         * Motor mode
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeMotorMode(@NonNull PilotingMotormodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMotorMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        private static native int nativeEncodePCMD(long nativeCmd, int flag, int throttle, int roll);
        private static native int nativeEncodeUserTakeOff(long nativeCmd, int state);
        private static native int nativeEncodeMotorMode(long nativeCmd, int mode);
    }

    /**
     * Piloting state from Power Up. 
     */
    public static class PilotingState {

        /** Feature uid. */
        public static final int UID = 0x0801;

        /** Uid of AlertStateChanged event. */;
        public static final int ALERTSTATECHANGED_UID = 0x0000;

        /** Uid of FlyingStateChanged event. */;
        public static final int FLYINGSTATECHANGED_UID = 0x0001;

        /** Uid of MotorModeChanged event. */;
        public static final int MOTORMODECHANGED_UID = 0x0002;

        /** Uid of AttitudeChanged event. */;
        public static final int ATTITUDECHANGED_UID = 0x0003;

        /** Uid of AltitudeChanged event. */;
        public static final int ALTITUDECHANGED_UID = 0x0004;

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
             * JS alert state changed
             * 
             * @param state:
             */
            public default void onAlertStateChanged(@Nullable PilotingstateAlertstatechangedState state) {}

            /**
             * Drone flying state changed
             * 
             * @param state:
             */
            public default void onFlyingStateChanged(@Nullable PilotingstateFlyingstatechangedState state) {}

            /**
             * Motor mode changed
             * 
             * @param mode:
             */
            public default void onMotorModeChanged(@Nullable PilotingstateMotormodechangedMode mode) {}

            /**
             * Drone attitude changed
             * 
             * @param roll: roll value (in radian) (relative to horizontal)
             * @param pitch: Pitch value (in radian) (relative to horizontal)
             * @param yaw: Yaw value (in radian) (relative to North)
             */
            public default void onAttitudeChanged(float roll, float pitch, float yaw) {}

            /**
             * Drone altitude changed
             * 
             * @param altitude: Altitude in meters relative to take off altitude
             */
            public default void onAltitudeChanged(float altitude) {}
        }

        private static void alertstatechanged(Callback cb, int state) {
            ArsdkFeaturePowerup.PilotingstateAlertstatechangedState enumState = ArsdkFeaturePowerup.PilotingstateAlertstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.PilotingstateAlertstatechangedState value " + state);
            try {
                cb.onAlertStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingState.AlertStateChanged [state: " + state + "]", e);
            }
        }

        private static void flyingstatechanged(Callback cb, int state) {
            ArsdkFeaturePowerup.PilotingstateFlyingstatechangedState enumState = ArsdkFeaturePowerup.PilotingstateFlyingstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.PilotingstateFlyingstatechangedState value " + state);
            try {
                cb.onFlyingStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingState.FlyingStateChanged [state: " + state + "]", e);
            }
        }

        private static void motormodechanged(Callback cb, int mode) {
            ArsdkFeaturePowerup.PilotingstateMotormodechangedMode enumMode = ArsdkFeaturePowerup.PilotingstateMotormodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.PilotingstateMotormodechangedMode value " + mode);
            try {
                cb.onMotorModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingState.MotorModeChanged [mode: " + mode + "]", e);
            }
        }

        private static void attitudechanged(Callback cb, float roll, float pitch, float yaw) {
            try {
                cb.onAttitudeChanged(roll, pitch, yaw);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingState.AttitudeChanged [roll: " + roll + ", pitch: " + pitch + ", yaw: " + yaw + "]", e);
            }
        }

        private static void altitudechanged(Callback cb, float altitude) {
            try {
                cb.onAltitudeChanged(altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingState.AltitudeChanged [altitude: " + altitude + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Piloting settings 
     */
    public static class PilotingSettings {

        /** Feature uid. */
        public static final int UID = 0x0802;

        /**
         * Set the given setting
         * 
         * @param setting:
         * @param value: value of the given setting
         */
        public static ArsdkCommand encodeSet(@NonNull PilotingsettingsSetSetting setting, float value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSet(cmd.getNativePtr(), setting.value, value);
            return cmd;
        }

        private static native int nativeEncodeSet(long nativeCmd, int setting, float value);
    }

    /**
     * Piloting settings 
     */
    public static class PilotingSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0803;

        /** Uid of settingChanged event. */;
        public static final int SETTINGCHANGED_UID = 0x0000;

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
             * Fired when a setting has changed
             * 
             * @param setting:
             * @param current: Current value of the given setting
             * @param min: Minimal value of the given setting
             * @param max: Max value of the given setting
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onSettingChanged(@Nullable PilotingsettingsstateSettingchangedSetting setting, float current, float min, float max, int listFlags) {}
        }

        private static void settingchanged(Callback cb, int setting, float current, float min, float max, int listFlags) {
            ArsdkFeaturePowerup.PilotingsettingsstateSettingchangedSetting enumSetting = ArsdkFeaturePowerup.PilotingsettingsstateSettingchangedSetting.fromValue(setting);
            if (enumSetting == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.PilotingsettingsstateSettingchangedSetting value " + setting);
            try {
                cb.onSettingChanged(enumSetting, current, min, max, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.PilotingSettingsState.settingChanged [setting: " + setting + ", current: " + current + ", min: " + min + ", max: " + max + ", list_flags: " + listFlags + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Media recording management 
     */
    public static class MediaRecord {

        /** Feature uid. */
        public static final int UID = 0x0804;

        /**
         * Take picture
         */
        public static ArsdkCommand encodePictureV2() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePictureV2(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Video record
         * 
         * @param record:
         */
        public static ArsdkCommand encodeVideoV2(@NonNull MediarecordVideov2Record record) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoV2(cmd.getNativePtr(), record.value);
            return cmd;
        }

        private static native int nativeEncodePictureV2(long nativeCmd);
        private static native int nativeEncodeVideoV2(long nativeCmd, int record);
    }

    /**
     * State of media recording 
     */
    public static class MediaRecordState {

        /** Feature uid. */
        public static final int UID = 0x0805;

        /** Uid of PictureStateChangedV2 event. */;
        public static final int PICTURESTATECHANGEDV2_UID = 0x0000;

        /** Uid of VideoStateChangedV2 event. */;
        public static final int VIDEOSTATECHANGEDV2_UID = 0x0001;

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
             * State of device picture recording changed
             * 
             * @param state:
             * @param error:
             */
            public default void onPictureStateChangedV2(@Nullable MediarecordstatePicturestatechangedv2State state, @Nullable MediarecordstatePicturestatechangedv2Error error) {}

            /**
             * State of device video recording changed
             * 
             * @param state:
             * @param error:
             */
            public default void onVideoStateChangedV2(@Nullable MediarecordstateVideostatechangedv2State state, @Nullable MediarecordstateVideostatechangedv2Error error) {}
        }

        private static void picturestatechangedv2(Callback cb, int state, int error) {
            ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2State enumState = ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2State value " + state);
            ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2Error enumError = ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordstatePicturestatechangedv2Error value " + error);
            try {
                cb.onPictureStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.MediaRecordState.PictureStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }

        private static void videostatechangedv2(Callback cb, int state, int error) {
            ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2State enumState = ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2State value " + state);
            ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2Error enumError = ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordstateVideostatechangedv2Error value " + error);
            try {
                cb.onVideoStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.MediaRecordState.VideoStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Events of media recording 
     */
    public static class MediaRecordEvent {

        /** Feature uid. */
        public static final int UID = 0x0806;

        /** Uid of PictureEventChanged event. */;
        public static final int PICTUREEVENTCHANGED_UID = 0x0000;

        /** Uid of VideoEventChanged event. */;
        public static final int VIDEOEVENTCHANGED_UID = 0x0001;

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
             * Event of picture recording
             * 
             * @param event:
             * @param error:
             */
            public default void onPictureEventChanged(@Nullable MediarecordeventPictureeventchangedEvent event, @Nullable MediarecordeventPictureeventchangedError error) {}

            /**
             * Event of video recording
             * 
             * @param event:
             * @param error:
             */
            public default void onVideoEventChanged(@Nullable MediarecordeventVideoeventchangedEvent event, @Nullable MediarecordeventVideoeventchangedError error) {}
        }

        private static void pictureeventchanged(Callback cb, int event, int error) {
            ArsdkFeaturePowerup.MediarecordeventPictureeventchangedEvent enumEvent = ArsdkFeaturePowerup.MediarecordeventPictureeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordeventPictureeventchangedEvent value " + event);
            ArsdkFeaturePowerup.MediarecordeventPictureeventchangedError enumError = ArsdkFeaturePowerup.MediarecordeventPictureeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordeventPictureeventchangedError value " + error);
            try {
                cb.onPictureEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.MediaRecordEvent.PictureEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }

        private static void videoeventchanged(Callback cb, int event, int error) {
            ArsdkFeaturePowerup.MediarecordeventVideoeventchangedEvent enumEvent = ArsdkFeaturePowerup.MediarecordeventVideoeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordeventVideoeventchangedEvent value " + event);
            ArsdkFeaturePowerup.MediarecordeventVideoeventchangedError enumError = ArsdkFeaturePowerup.MediarecordeventVideoeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediarecordeventVideoeventchangedError value " + error);
            try {
                cb.onVideoEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.MediaRecordEvent.VideoEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Network settings commands 
     */
    public static class NetworkSettings {

        /** Feature uid. */
        public static final int UID = 0x0807;

        /**
         * Auto-select channel of choosen band
         * 
         * @param type:
         * @param band:
         * @param channel: The channel (not used in auto mode)
         */
        public static ArsdkCommand encodeWifiSelection(@NonNull NetworksettingsWifiselectionType type, @NonNull NetworksettingsWifiselectionBand band, int channel) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiSelection(cmd.getNativePtr(), type.value, band.value, channel);
            return cmd;
        }

        private static native int nativeEncodeWifiSelection(long nativeCmd, int type, int band, int channel);
    }

    /**
     * Network settings state from product 
     */
    public static class NetworkSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0808;

        /** Uid of WifiSelectionChanged event. */;
        public static final int WIFISELECTIONCHANGED_UID = 0x0000;

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
             * Wifi selection from product
             * 
             * @param type:
             * @param band:
             * @param channel: The channel (depends of the band)
             */
            public default void onWifiSelectionChanged(@Nullable NetworksettingsstateWifiselectionchangedType type, @Nullable NetworksettingsstateWifiselectionchangedBand band, int channel) {}
        }

        private static void wifiselectionchanged(Callback cb, int type, int band, int channel) {
            ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedType enumType = ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedType value " + type);
            ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedBand enumBand = ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.NetworksettingsstateWifiselectionchangedBand value " + band);
            try {
                cb.onWifiSelectionChanged(enumType, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkSettingsState.WifiSelectionChanged [type: " + type + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Network related commands 
     */
    public static class Network {

        /** Feature uid. */
        public static final int UID = 0x0809;

        /**
         * Launches wifi network scan
         * 
         * @param band:
         */
        public static ArsdkCommand encodeWifiScan(@NonNull NetworkWifiscanBand band) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiScan(cmd.getNativePtr(), band.value);
            return cmd;
        }

        /**
         * Controller inquire the list of authorized wifi channels.
         */
        public static ArsdkCommand encodeWifiAuthChannel() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiAuthChannel(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeWifiScan(long nativeCmd, int band);
        private static native int nativeEncodeWifiAuthChannel(long nativeCmd);
    }

    /**
     * Network state from Product 
     */
    public static class NetworkState {

        /** Feature uid. */
        public static final int UID = 0x080A;

        /** Uid of WifiScanListChanged event. */;
        public static final int WIFISCANLISTCHANGED_UID = 0x0000;

        /** Uid of AllWifiScanChanged event. */;
        public static final int ALLWIFISCANCHANGED_UID = 0x0001;

        /** Uid of WifiAuthChannelListChanged event. */;
        public static final int WIFIAUTHCHANNELLISTCHANGED_UID = 0x0002;

        /** Uid of AllWifiAuthChannelChanged event. */;
        public static final int ALLWIFIAUTHCHANNELCHANGED_UID = 0x0003;

        /** Uid of LinkQualityChanged event. */;
        public static final int LINKQUALITYCHANGED_UID = 0x0004;

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
             * One scanning result found
             * 
             * @param ssid: SSID of the AP
             * @param rssi: RSSI of the AP in dbm (negative value)
             * @param band:
             * @param channel: Channel of the AP
             */
            public default void onWifiScanListChanged(String ssid, int rssi, @Nullable NetworkstateWifiscanlistchangedBand band, int channel) {}

            /**
             * State sent when all scanning result sent
             */
            public default void onAllWifiScanChanged() {}

            /**
             * Notify of an Authorized Channel.
             * 
             * @param band:
             * @param channel: The authorized channel.
             * @param inOrOut: Bit 0 is 1 if channel is authorized outside (0 otherwise) ; Bit 1 is 1 if channel is
             * authorized inside (0 otherwise)
             */
            public default void onWifiAuthChannelListChanged(@Nullable NetworkstateWifiauthchannellistchangedBand band, int channel, int inOrOut) {}

            /**
             * Notify the end of the list of Authorized wifi Channel.
             */
            public default void onAllWifiAuthChannelChanged() {}

            /**
             * Notification sent by the firmware to give an indication of the WiFi link quality.
             * 
             * @param quality: The WiFi link quality in range 0-6, the higher the value, the higher the link quality.
             */
            public default void onLinkQualityChanged(int quality) {}
        }

        private static void wifiscanlistchanged(Callback cb, String ssid, int rssi, int band, int channel) {
            ArsdkFeaturePowerup.NetworkstateWifiscanlistchangedBand enumBand = ArsdkFeaturePowerup.NetworkstateWifiscanlistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.NetworkstateWifiscanlistchangedBand value " + band);
            try {
                cb.onWifiScanListChanged(ssid, rssi, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkState.WifiScanListChanged [ssid: " + ssid + ", rssi: " + rssi + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }

        private static void allwifiscanchanged(Callback cb) {
            try {
                cb.onAllWifiScanChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkState.AllWifiScanChanged", e);
            }
        }

        private static void wifiauthchannellistchanged(Callback cb, int band, int channel, int inOrOut) {
            ArsdkFeaturePowerup.NetworkstateWifiauthchannellistchangedBand enumBand = ArsdkFeaturePowerup.NetworkstateWifiauthchannellistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.NetworkstateWifiauthchannellistchangedBand value " + band);
            try {
                cb.onWifiAuthChannelListChanged(enumBand, channel, inOrOut);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkState.WifiAuthChannelListChanged [band: " + band + ", channel: " + channel + ", in_or_out: " + inOrOut + "]", e);
            }
        }

        private static void allwifiauthchannelchanged(Callback cb) {
            try {
                cb.onAllWifiAuthChannelChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkState.AllWifiAuthChannelChanged", e);
            }
        }

        private static void linkqualitychanged(Callback cb, int quality) {
            try {
                cb.onLinkQualityChanged(quality);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.NetworkState.LinkQualityChanged [quality: " + quality + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Control media streaming behavior. 
     */
    public static class MediaStreaming {

        /** Feature uid. */
        public static final int UID = 0x080B;

        /**
         * Enable/disable video streaming.
         * 
         * @param enable: 1 to enable, 0 to disable.
         */
        public static ArsdkCommand encodeVideoEnable(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoEnable(cmd.getNativePtr(), enable);
            return cmd;
        }

        private static native int nativeEncodeVideoEnable(long nativeCmd, int enable);
    }

    /**
     * Media streaming status. 
     */
    public static class MediaStreamingState {

        /** Feature uid. */
        public static final int UID = 0x080C;

        /** Uid of VideoEnableChanged event. */;
        public static final int VIDEOENABLECHANGED_UID = 0x0000;

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
             * Return video streaming status.
             * 
             * @param enabled:
             */
            public default void onVideoEnableChanged(@Nullable MediastreamingstateVideoenablechangedEnabled enabled) {}
        }

        private static void videoenablechanged(Callback cb, int enabled) {
            ArsdkFeaturePowerup.MediastreamingstateVideoenablechangedEnabled enumEnabled = ArsdkFeaturePowerup.MediastreamingstateVideoenablechangedEnabled.fromValue(enabled);
            if (enumEnabled == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.MediastreamingstateVideoenablechangedEnabled value " + enabled);
            try {
                cb.onVideoEnableChanged(enumEnabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.MediaStreamingState.VideoEnableChanged [enabled: " + enabled + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Video settings. 
     */
    public static class VideoSettings {

        /** Feature uid. */
        public static final int UID = 0x080D;

        /**
         * Set video automatic recording state.
         * 
         * @param enable: 0: Disabled 1: Enabled.
         */
        public static ArsdkCommand encodeAutorecord(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutorecord(cmd.getNativePtr(), enable);
            return cmd;
        }

        /**
         * Set video mode
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeVideoMode(@NonNull VideosettingsVideomodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        private static native int nativeEncodeAutorecord(long nativeCmd, int enable);
        private static native int nativeEncodeVideoMode(long nativeCmd, int mode);
    }

    /**
     * Video settings state. 
     */
    public static class VideoSettingsState {

        /** Feature uid. */
        public static final int UID = 0x080E;

        /** Uid of AutorecordChanged event. */;
        public static final int AUTORECORDCHANGED_UID = 0x0000;

        /** Uid of VideoModeChanged event. */;
        public static final int VIDEOMODECHANGED_UID = 0x0001;

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
             * Get video automatic recording status.
             * 
             * @param enabled: 0: Disabled 1: Enabled.
             */
            public default void onAutorecordChanged(int enabled) {}

            /**
             * Video mode
             * 
             * @param mode:
             */
            public default void onVideoModeChanged(@Nullable VideosettingsstateVideomodechangedMode mode) {}
        }

        private static void autorecordchanged(Callback cb, int enabled) {
            try {
                cb.onAutorecordChanged(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.VideoSettingsState.AutorecordChanged [enabled: " + enabled + "]", e);
            }
        }

        private static void videomodechanged(Callback cb, int mode) {
            ArsdkFeaturePowerup.VideosettingsstateVideomodechangedMode enumMode = ArsdkFeaturePowerup.VideosettingsstateVideomodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeaturePowerup.VideosettingsstateVideomodechangedMode value " + mode);
            try {
                cb.onVideoModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.VideoSettingsState.VideoModeChanged [mode: " + mode + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Sounds related commands. 
     */
    public static class Sounds {

        /** Feature uid. */
        public static final int UID = 0x080F;

        /**
         * Enable/disable the buzzer sound
         * 
         * @param enable: 0: Disabled 1: Enabled.
         */
        public static ArsdkCommand encodeBuzz(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeBuzz(cmd.getNativePtr(), enable);
            return cmd;
        }

        private static native int nativeEncodeBuzz(long nativeCmd, int enable);
    }

    /**
     * Sound related events. 
     */
    public static class SoundsState {

        /** Feature uid. */
        public static final int UID = 0x0810;

        /** Uid of buzzChanged event. */;
        public static final int BUZZCHANGED_UID = 0x0000;

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
             * State of the buzzer
             * 
             * @param enabled: 0: Disabled 1: Enabled.
             */
            public default void onBuzzChanged(int enabled) {}
        }

        private static void buzzchanged(Callback cb, int enabled) {
            try {
                cb.onBuzzChanged(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: powerup.SoundsState.buzzChanged [enabled: " + enabled + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
