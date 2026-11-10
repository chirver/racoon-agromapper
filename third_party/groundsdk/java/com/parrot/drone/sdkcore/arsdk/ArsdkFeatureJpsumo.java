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
 * Jpsumo feature command/event interface.
 */
public class ArsdkFeatureJpsumo {

    /**
     * Type of Posture.
     */
    public enum PilotingPostureType {

        /**
         * Standing type.
         */
        STANDING(0),

        /**
         * Jumper type.
         */
        JUMPER(1),

        /**
         * Kicker type.
         */
        KICKER(2);

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
        public static PilotingPostureType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingPostureType(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingPostureType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingPostureType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of posture.
     */
    public enum PilotingstatePosturechangedState {

        /**
         * Standing state.
         */
        STANDING(0),

        /**
         * Jumper state.
         */
        JUMPER(1),

        /**
         * Kicker state.
         */
        KICKER(2),

        /**
         * Stuck state.
         */
        STUCK(3),

        /**
         * Unknown state.
         */
        UNKNOWN(4);

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
        public static PilotingstatePosturechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePosturechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePosturechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePosturechangedState e: values())
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
     * Type of jump.
     */
    public enum AnimationsJumpType {

        /**
         * Long jump.
         */
        LONG(0),

        /**
         * High jump.
         */
        HIGH(1);

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
        public static AnimationsJumpType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsJumpType(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsJumpType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsJumpType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation ID.
     */
    public enum AnimationsSimpleanimationId {

        /**
         * Stop ongoing animation.
         */
        STOP(0),

        /**
         * Start a spin animation.
         */
        SPIN(1),

        /**
         * Start a tap animation.
         */
        TAP(2),

        /**
         * Start a slow shake animation.
         */
        SLOWSHAKE(3),

        /**
         * Start a Metronome animation.
         */
        METRONOME(4),

        /**
         * Start a standing dance animation.
         */
        ONDULATION(5),

        /**
         * Start a spin jump animation.
         */
        SPINJUMP(6),

        /**
         * Start a spin that end in standing posture, or in jumper if it was standing animation.
         */
        SPINTOPOSTURE(7),

        /**
         * Start a spiral animation.
         */
        SPIRAL(8),

        /**
         * Start a slalom animation.
         */
        SLALOM(9);

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
        public static AnimationsSimpleanimationId fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsSimpleanimationId(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsSimpleanimationId> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsSimpleanimationId e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of jump load.
     */
    public enum AnimationsstateJumploadchangedState {

        /**
         * Unknown state (obsolete).
         */
        UNKNOWN(0),

        /**
         * Unloaded state.
         */
        UNLOADED(1),

        /**
         * Loaded state.
         */
        LOADED(2),

        /**
         * Unknown state (obsolete).
         */
        BUSY(3),

        /**
         * Unloaded state and low battery.
         */
        LOW_BATTERY_UNLOADED(4),

        /**
         * Loaded state and low battery.
         */
        LOW_BATTERY_LOADED(5);

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
        public static AnimationsstateJumploadchangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateJumploadchangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateJumploadchangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateJumploadchangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of jump type.
     */
    public enum AnimationsstateJumptypechangedState {

        /**
         * None.
         */
        NONE(0),

        /**
         * Long jump type.
         */
        LONG(1),

        /**
         * High jump type.
         */
        HIGH(2);

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
        public static AnimationsstateJumptypechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateJumptypechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateJumptypechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateJumptypechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Enum describing the problem of the motor.
     */
    public enum AnimationsstateJumpmotorproblemchangedError {

        /**
         * None.
         */
        NONE(0),

        /**
         * Motor blocked.
         */
        BLOCKED(1),

        /**
         * Motor over heated.
         */
        OVER_HEATED(2);

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
        public static AnimationsstateJumpmotorproblemchangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsstateJumpmotorproblemchangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsstateJumpmotorproblemchangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsstateJumpmotorproblemchangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Command to record video.
     */
    public enum MediarecordVideoRecord {

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
        public static MediarecordVideoRecord fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordVideoRecord(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordVideoRecord> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordVideoRecord e: values())
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
     * State of video.
     */
    public enum MediarecordstateVideostatechangedState {

        /**
         * Video was stopped.
         */
        STOPPED(0),

        /**
         * Video was started.
         */
        STARTED(1),

        /**
         * Video was failed.
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
        public static MediarecordstateVideostatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstateVideostatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstateVideostatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstateVideostatechangedState e: values())
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
     * The audio theme to set.
     */
    public enum AudiosettingsThemeTheme {

        /**
         * Default audio theme (depends on the product color).
         */
        DEFAULT(0),

        /**
         * Robot audio theme.
         */
        ROBOT(1),

        /**
         * Insect audio theme.
         */
        INSECT(2),

        /**
         * Monster audio theme.
         */
        MONSTER(3);

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
        public static AudiosettingsThemeTheme fromValue(int value) {
            return MAP.get(value, null);
        }

        private AudiosettingsThemeTheme(int value) {
            this.value = value;
        }

        private static final SparseArray<AudiosettingsThemeTheme> MAP;

        static {
            MAP = new SparseArray<>();
            for (AudiosettingsThemeTheme e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The audio theme to set.
     */
    public enum AudiosettingsstateThemechangedTheme {

        /**
         * Default audio theme (depends on the product color).
         */
        DEFAULT(0),

        /**
         * Robot audio theme.
         */
        ROBOT(1),

        /**
         * Insect audio theme.
         */
        INSECT(2),

        /**
         * Monster audio theme.
         */
        MONSTER(3);

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
        public static AudiosettingsstateThemechangedTheme fromValue(int value) {
            return MAP.get(value, null);
        }

        private AudiosettingsstateThemechangedTheme(int value) {
            this.value = value;
        }

        private static final SparseArray<AudiosettingsstateThemechangedTheme> MAP;

        static {
            MAP = new SparseArray<>();
            for (AudiosettingsstateThemechangedTheme e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error code.
     */
    public enum RoadplanstateScriptuploadchangedResultcode {

        /**
         * The script was parsed successfully.
         */
        ERROR_OK(0),

        /**
         * The MD5 hash codes are different or file is unreadable.
         */
        ERROR_FILE_CORRUPTED(1),

        /**
         * The parser is not well formed or can not be parsed.
         */
        ERROR_INVALID_FORMAT(2),

        /**
         * The file is larger than maximum allowed size.
         */
        ERROR_FILE_TOO_LARGE(3),

        /**
         * Script version is not supported by device.
         */
        ERROR_UNSUPPORTED(4);

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
        public static RoadplanstateScriptuploadchangedResultcode fromValue(int value) {
            return MAP.get(value, null);
        }

        private RoadplanstateScriptuploadchangedResultcode(int value) {
            this.value = value;
        }

        private static final SparseArray<RoadplanstateScriptuploadchangedResultcode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RoadplanstateScriptuploadchangedResultcode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error code.
     */
    public enum RoadplanstateScriptdeletechangedResultcode {

        /**
         * The script was deleted successfully.
         */
        ERROR_OK(0),

        /**
         * No script with this uuid exists.
         */
        ERROR_NO_SUCH_SCRIPT(1),

        /**
         * An internal error occured while attempting to delete the script.
         */
        ERROR_INTERNAL_FAILURE(2);

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
        public static RoadplanstateScriptdeletechangedResultcode fromValue(int value) {
            return MAP.get(value, null);
        }

        private RoadplanstateScriptdeletechangedResultcode(int value) {
            this.value = value;
        }

        private static final SparseArray<RoadplanstateScriptdeletechangedResultcode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RoadplanstateScriptdeletechangedResultcode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error code.
     */
    public enum RoadplanstatePlayscriptchangedResultcode {

        /**
         * The script started playing successfully.
         */
        SCRIPT_STARTED(0),

        /**
         * The script finished successfully.
         */
        SCRIPT_FINISHED(1),

        /**
         * No script with this uuid exists.
         */
        SCRIPT_NO_SUCH_SCRIPT(2),

        /**
         * An error occured while playing the script.
         */
        SCRIPT_ERROR(3);

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
        public static RoadplanstatePlayscriptchangedResultcode fromValue(int value) {
            return MAP.get(value, null);
        }

        private RoadplanstatePlayscriptchangedResultcode(int value) {
            this.value = value;
        }

        private static final SparseArray<RoadplanstatePlayscriptchangedResultcode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RoadplanstatePlayscriptchangedResultcode e: values())
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
     * All commands related to piloting the JumpingSumo 
     */
    public static class Piloting {

        /** Feature uid. */
        public static final int UID = 0x0300;

        /**
         * Ask the JS speed and turn ratio.
         * 
         * @param flag: Boolean for "touch screen".
         * @param speed: Speed value [-100:100].
         * @param turn: Turn value. [-100:100]
         */
        public static ArsdkCommand encodePCMD(int flag, int speed, int turn) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePCMD(cmd.getNativePtr(), flag, speed, turn);
            return cmd;
        }

        /**
         * Request a posture
         * 
         * @param type:
         */
        public static ArsdkCommand encodePosture(@NonNull PilotingPostureType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePosture(cmd.getNativePtr(), type.value);
            return cmd;
        }

        /**
         * Add the specified offset to the current cap.
         * 
         * @param offset: Offset value in radians.
         */
        public static ArsdkCommand encodeAddCapOffset(float offset) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAddCapOffset(cmd.getNativePtr(), offset);
            return cmd;
        }

        private static native int nativeEncodePCMD(long nativeCmd, int flag, int speed, int turn);
        private static native int nativeEncodePosture(long nativeCmd, int type);
        private static native int nativeEncodeAddCapOffset(long nativeCmd, float offset);
    }

    /**
     * Animations state from JS. 
     */
    public static class PilotingState {

        /** Feature uid. */
        public static final int UID = 0x0301;

        /** Uid of PostureChanged event. */;
        public static final int POSTURECHANGED_UID = 0x0000;

        /** Uid of AlertStateChanged event. */;
        public static final int ALERTSTATECHANGED_UID = 0x0001;

        /** Uid of SpeedChanged event. */;
        public static final int SPEEDCHANGED_UID = 0x0002;

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
             * State of posture changed.
             * 
             * @param state:
             */
            public default void onPostureChanged(@Nullable PilotingstatePosturechangedState state) {}

            /**
             * JS alert state changed
             * 
             * @param state:
             */
            public default void onAlertStateChanged(@Nullable PilotingstateAlertstatechangedState state) {}

            /**
             * Notification sent when JS speed changes.
             * 
             * @param speed: Speed command applied to motors in range [-100;100].
             * @param realspeed: Actual real-world speed in cm/s. Value -32768 returned if not available.
             */
            public default void onSpeedChanged(int speed, int realspeed) {}
        }

        private static void posturechanged(Callback cb, int state) {
            ArsdkFeatureJpsumo.PilotingstatePosturechangedState enumState = ArsdkFeatureJpsumo.PilotingstatePosturechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.PilotingstatePosturechangedState value " + state);
            try {
                cb.onPostureChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.PilotingState.PostureChanged [state: " + state + "]", e);
            }
        }

        private static void alertstatechanged(Callback cb, int state) {
            ArsdkFeatureJpsumo.PilotingstateAlertstatechangedState enumState = ArsdkFeatureJpsumo.PilotingstateAlertstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.PilotingstateAlertstatechangedState value " + state);
            try {
                cb.onAlertStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.PilotingState.AlertStateChanged [state: " + state + "]", e);
            }
        }

        private static void speedchanged(Callback cb, int speed, int realspeed) {
            try {
                cb.onSpeedChanged(speed, realspeed);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.PilotingState.SpeedChanged [speed: " + speed + ", realSpeed: " + realspeed + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Animation commands 
     */
    public static class Animations {

        /** Feature uid. */
        public static final int UID = 0x0302;

        /**
         * Stop jump, emergency jump stop, stop jump motor and stay there.
         */
        public static ArsdkCommand encodeJumpStop() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeJumpStop(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Cancel jump and come back to previous state (if possible).
         */
        public static ArsdkCommand encodeJumpCancel() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeJumpCancel(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Request jump loading
         */
        public static ArsdkCommand encodeJumpLoad() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeJumpLoad(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Request a jump
         * 
         * @param type:
         */
        public static ArsdkCommand encodeJump(@NonNull AnimationsJumpType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeJump(cmd.getNativePtr(), type.value);
            return cmd;
        }

        /**
         * Play a parameterless animation.
         * 
         * @param id:
         */
        public static ArsdkCommand encodeSimpleAnimation(@NonNull AnimationsSimpleanimationId id) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSimpleAnimation(cmd.getNativePtr(), id.value);
            return cmd;
        }

        private static native int nativeEncodeJumpStop(long nativeCmd);
        private static native int nativeEncodeJumpCancel(long nativeCmd);
        private static native int nativeEncodeJumpLoad(long nativeCmd);
        private static native int nativeEncodeJump(long nativeCmd, int type);
        private static native int nativeEncodeSimpleAnimation(long nativeCmd, int id);
    }

    /**
     * Animations state from JS. 
     */
    public static class AnimationsState {

        /** Feature uid. */
        public static final int UID = 0x0303;

        /** Uid of JumpLoadChanged event. */;
        public static final int JUMPLOADCHANGED_UID = 0x0000;

        /** Uid of JumpTypeChanged event. */;
        public static final int JUMPTYPECHANGED_UID = 0x0001;

        /** Uid of JumpMotorProblemChanged event. */;
        public static final int JUMPMOTORPROBLEMCHANGED_UID = 0x0002;

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
             * State of jump load changed
             * 
             * @param state:
             */
            public default void onJumpLoadChanged(@Nullable AnimationsstateJumploadchangedState state) {}

            /**
             * State of jump type changed.
             * 
             * @param state:
             */
            public default void onJumpTypeChanged(@Nullable AnimationsstateJumptypechangedState state) {}

            /**
             * State about the jump motor problem
             * 
             * @param error:
             */
            public default void onJumpMotorProblemChanged(@Nullable AnimationsstateJumpmotorproblemchangedError error) {}
        }

        private static void jumploadchanged(Callback cb, int state) {
            ArsdkFeatureJpsumo.AnimationsstateJumploadchangedState enumState = ArsdkFeatureJpsumo.AnimationsstateJumploadchangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.AnimationsstateJumploadchangedState value " + state);
            try {
                cb.onJumpLoadChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.AnimationsState.JumpLoadChanged [state: " + state + "]", e);
            }
        }

        private static void jumptypechanged(Callback cb, int state) {
            ArsdkFeatureJpsumo.AnimationsstateJumptypechangedState enumState = ArsdkFeatureJpsumo.AnimationsstateJumptypechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.AnimationsstateJumptypechangedState value " + state);
            try {
                cb.onJumpTypeChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.AnimationsState.JumpTypeChanged [state: " + state + "]", e);
            }
        }

        private static void jumpmotorproblemchanged(Callback cb, int error) {
            ArsdkFeatureJpsumo.AnimationsstateJumpmotorproblemchangedError enumError = ArsdkFeatureJpsumo.AnimationsstateJumpmotorproblemchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.AnimationsstateJumpmotorproblemchangedError value " + error);
            try {
                cb.onJumpMotorProblemChanged(enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.AnimationsState.JumpMotorProblemChanged [error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Settings state from product 
     */
    public static class SettingsState {

        /** Feature uid. */
        public static final int UID = 0x0305;

        /** Uid of ProductGPSVersionChanged event. */;
        public static final int PRODUCTGPSVERSIONCHANGED_UID = 0x0000;

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
             * @deprecated Product GPS versions
             * 
             * @param software: Product GPS software version
             * @param hardware: Product GPS hardware version
             */
            public default void onProductGPSVersionChanged(String software, String hardware) {}
        }

        private static void productgpsversionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onProductGPSVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.SettingsState.ProductGPSVersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
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
        public static final int UID = 0x0306;

        /**
         * @deprecated Take picture
         * 
         * @param massStorageId: Mass storage id to take picture
         */
        public static ArsdkCommand encodePicture(int massStorageId) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePicture(cmd.getNativePtr(), massStorageId);
            return cmd;
        }

        /**
         * @deprecated Video record
         * 
         * @param record:
         * @param massStorageId: Mass storage id to record
         */
        public static ArsdkCommand encodeVideo(@NonNull MediarecordVideoRecord record, int massStorageId) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideo(cmd.getNativePtr(), record.value, massStorageId);
            return cmd;
        }

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

        private static native int nativeEncodePicture(long nativeCmd, int mass_storage_id);
        private static native int nativeEncodeVideo(long nativeCmd, int record, int mass_storage_id);
        private static native int nativeEncodePictureV2(long nativeCmd);
        private static native int nativeEncodeVideoV2(long nativeCmd, int record);
    }

    /**
     * State of media recording 
     */
    public static class MediaRecordState {

        /** Feature uid. */
        public static final int UID = 0x0307;

        /** Uid of PictureStateChanged event. */;
        public static final int PICTURESTATECHANGED_UID = 0x0000;

        /** Uid of VideoStateChanged event. */;
        public static final int VIDEOSTATECHANGED_UID = 0x0001;

        /** Uid of PictureStateChangedV2 event. */;
        public static final int PICTURESTATECHANGEDV2_UID = 0x0002;

        /** Uid of VideoStateChangedV2 event. */;
        public static final int VIDEOSTATECHANGEDV2_UID = 0x0003;

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
             * @deprecated State of picture recording
             * 
             * @param state: 1 if picture has been taken, 0 otherwise
             * @param massStorageId: Mass storage id where the picture was recorded
             */
            public default void onPictureStateChanged(int state, int massStorageId) {}

            /**
             * @deprecated State of video recording
             * 
             * @param state:
             * @param massStorageId: Mass storage id where the video was recorded
             */
            public default void onVideoStateChanged(@Nullable MediarecordstateVideostatechangedState state, int massStorageId) {}

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

        private static void picturestatechanged(Callback cb, int state, int massStorageId) {
            try {
                cb.onPictureStateChanged(state, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordState.PictureStateChanged [state: " + state + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void videostatechanged(Callback cb, int state, int massStorageId) {
            ArsdkFeatureJpsumo.MediarecordstateVideostatechangedState enumState = ArsdkFeatureJpsumo.MediarecordstateVideostatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordstateVideostatechangedState value " + state);
            try {
                cb.onVideoStateChanged(enumState, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordState.VideoStateChanged [state: " + state + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void picturestatechangedv2(Callback cb, int state, int error) {
            ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2State enumState = ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2State value " + state);
            ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2Error enumError = ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordstatePicturestatechangedv2Error value " + error);
            try {
                cb.onPictureStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordState.PictureStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }

        private static void videostatechangedv2(Callback cb, int state, int error) {
            ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2State enumState = ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2State value " + state);
            ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2Error enumError = ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordstateVideostatechangedv2Error value " + error);
            try {
                cb.onVideoStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordState.VideoStateChangedV2 [state: " + state + ", error: " + error + "]", e);
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
        public static final int UID = 0x0308;

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
        public static final int UID = 0x0309;

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
            ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedType enumType = ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedType value " + type);
            ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedBand enumBand = ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.NetworksettingsstateWifiselectionchangedBand value " + band);
            try {
                cb.onWifiSelectionChanged(enumType, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkSettingsState.WifiSelectionChanged [type: " + type + ", band: " + band + ", channel: " + channel + "]", e);
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
        public static final int UID = 0x030A;

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
        public static final int UID = 0x030B;

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
            ArsdkFeatureJpsumo.NetworkstateWifiscanlistchangedBand enumBand = ArsdkFeatureJpsumo.NetworkstateWifiscanlistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.NetworkstateWifiscanlistchangedBand value " + band);
            try {
                cb.onWifiScanListChanged(ssid, rssi, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkState.WifiScanListChanged [ssid: " + ssid + ", rssi: " + rssi + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }

        private static void allwifiscanchanged(Callback cb) {
            try {
                cb.onAllWifiScanChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkState.AllWifiScanChanged", e);
            }
        }

        private static void wifiauthchannellistchanged(Callback cb, int band, int channel, int inOrOut) {
            ArsdkFeatureJpsumo.NetworkstateWifiauthchannellistchangedBand enumBand = ArsdkFeatureJpsumo.NetworkstateWifiauthchannellistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.NetworkstateWifiauthchannellistchangedBand value " + band);
            try {
                cb.onWifiAuthChannelListChanged(enumBand, channel, inOrOut);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkState.WifiAuthChannelListChanged [band: " + band + ", channel: " + channel + ", in_or_out: " + inOrOut + "]", e);
            }
        }

        private static void allwifiauthchannelchanged(Callback cb) {
            try {
                cb.onAllWifiAuthChannelChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkState.AllWifiAuthChannelChanged", e);
            }
        }

        private static void linkqualitychanged(Callback cb, int quality) {
            try {
                cb.onLinkQualityChanged(quality);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.NetworkState.LinkQualityChanged [quality: " + quality + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Audio settings. 
     */
    public static class AudioSettings {

        /** Feature uid. */
        public static final int UID = 0x030C;

        /**
         * Master volume control.
         * 
         * @param volume: Master audio volume [0:100].
         */
        public static ArsdkCommand encodeMasterVolume(int volume) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMasterVolume(cmd.getNativePtr(), volume);
            return cmd;
        }

        /**
         * Audio Theme.
         * 
         * @param theme:
         */
        public static ArsdkCommand encodeTheme(@NonNull AudiosettingsThemeTheme theme) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeTheme(cmd.getNativePtr(), theme.value);
            return cmd;
        }

        private static native int nativeEncodeMasterVolume(long nativeCmd, int volume);
        private static native int nativeEncodeTheme(long nativeCmd, int theme);
    }

    /**
     * Audio settings state. 
     */
    public static class AudioSettingsState {

        /** Feature uid. */
        public static final int UID = 0x030D;

        /** Uid of MasterVolumeChanged event. */;
        public static final int MASTERVOLUMECHANGED_UID = 0x0000;

        /** Uid of ThemeChanged event. */;
        public static final int THEMECHANGED_UID = 0x0001;

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
             * Master volume control.
             * 
             * @param volume: Master audio volume [0:100].
             */
            public default void onMasterVolumeChanged(int volume) {}

            /**
             * Command to notify controller of new Audio Theme.
             * 
             * @param theme:
             */
            public default void onThemeChanged(@Nullable AudiosettingsstateThemechangedTheme theme) {}
        }

        private static void mastervolumechanged(Callback cb, int volume) {
            try {
                cb.onMasterVolumeChanged(volume);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.AudioSettingsState.MasterVolumeChanged [volume: " + volume + "]", e);
            }
        }

        private static void themechanged(Callback cb, int theme) {
            ArsdkFeatureJpsumo.AudiosettingsstateThemechangedTheme enumTheme = ArsdkFeatureJpsumo.AudiosettingsstateThemechangedTheme.fromValue(theme);
            if (enumTheme == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.AudiosettingsstateThemechangedTheme value " + theme);
            try {
                cb.onThemeChanged(enumTheme);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.AudioSettingsState.ThemeChanged [theme: " + theme + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * RoadPlan commands. 
     */
    public static class RoadPlan {

        /** Feature uid. */
        public static final int UID = 0x030E;

        /**
         * Command to ask device all metadata scripts.
         */
        public static ArsdkCommand encodeAllScriptsMetadata() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAllScriptsMetadata(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Notify device that a new file has been uploaded.
         * 
         * @param uuid: UUID of uploaded file.
         * @param md5hash: MD5 hash code computed over file.
         */
        public static ArsdkCommand encodeScriptUploaded(String uuid, String md5hash) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeScriptUploaded(cmd.getNativePtr(), uuid, md5hash);
            return cmd;
        }

        /**
         * Ask the device to delete a script.
         * 
         * @param uuid: UUID of the file to delete.
         */
        public static ArsdkCommand encodeScriptDelete(String uuid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeScriptDelete(cmd.getNativePtr(), uuid);
            return cmd;
        }

        /**
         * Ask the device to play a script.
         * 
         * @param uuid: UUID of the file to play.
         */
        public static ArsdkCommand encodePlayScript(String uuid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePlayScript(cmd.getNativePtr(), uuid);
            return cmd;
        }

        private static native int nativeEncodeAllScriptsMetadata(long nativeCmd);
        private static native int nativeEncodeScriptUploaded(long nativeCmd, String uuid, String md5Hash);
        private static native int nativeEncodeScriptDelete(long nativeCmd, String uuid);
        private static native int nativeEncodePlayScript(long nativeCmd, String uuid);
    }

    /**
     * RoadPlan command responses. 
     */
    public static class RoadPlanState {

        /** Feature uid. */
        public static final int UID = 0x030F;

        /** Uid of ScriptMetadataListChanged event. */;
        public static final int SCRIPTMETADATALISTCHANGED_UID = 0x0000;

        /** Uid of AllScriptsMetadataChanged event. */;
        public static final int ALLSCRIPTSMETADATACHANGED_UID = 0x0001;

        /** Uid of ScriptUploadChanged event. */;
        public static final int SCRIPTUPLOADCHANGED_UID = 0x0002;

        /** Uid of ScriptDeleteChanged event. */;
        public static final int SCRIPTDELETECHANGED_UID = 0x0003;

        /** Uid of PlayScriptChanged event. */;
        public static final int PLAYSCRIPTCHANGED_UID = 0x0004;

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
             * Update the controller with metadata.
             * 
             * @param uuid: Script uuid for which metadata changed.
             * @param version: Version number for this script.
             * @param product: Product targeted by script.
             * @param name: Display name of the script.
             * @param lastmodified: Timestamp relative to the UNIX epoch of the last time the file was modified.
             */
            public default void onScriptMetadataListChanged(String uuid, int version, String product, String name, long lastmodified) {}

            /**
             * Notify controller that all script metadatas are updated.
             */
            public default void onAllScriptsMetadataChanged() {}

            /**
             * Device response to ScriptUploaded command.
             * 
             * @param resultcode:
             */
            public default void onScriptUploadChanged(@Nullable RoadplanstateScriptuploadchangedResultcode resultcode) {}

            /**
             * Device response to ScriptDelete command.
             * 
             * @param resultcode:
             */
            public default void onScriptDeleteChanged(@Nullable RoadplanstateScriptdeletechangedResultcode resultcode) {}

            /**
             * Device response to PlayScript command.
             * 
             * @param resultcode:
             */
            public default void onPlayScriptChanged(@Nullable RoadplanstatePlayscriptchangedResultcode resultcode) {}
        }

        private static void scriptmetadatalistchanged(Callback cb, String uuid, int version, String product, String name, long lastmodified) {
            try {
                cb.onScriptMetadataListChanged(uuid, version, product, name, lastmodified);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.RoadPlanState.ScriptMetadataListChanged [uuid: " + uuid + ", version: " + version + ", product: " + product + ", name: " + name + ", lastModified: " + lastmodified + "]", e);
            }
        }

        private static void allscriptsmetadatachanged(Callback cb) {
            try {
                cb.onAllScriptsMetadataChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.RoadPlanState.AllScriptsMetadataChanged", e);
            }
        }

        private static void scriptuploadchanged(Callback cb, int resultcode) {
            ArsdkFeatureJpsumo.RoadplanstateScriptuploadchangedResultcode enumResultcode = ArsdkFeatureJpsumo.RoadplanstateScriptuploadchangedResultcode.fromValue(resultcode);
            if (enumResultcode == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.RoadplanstateScriptuploadchangedResultcode value " + resultcode);
            try {
                cb.onScriptUploadChanged(enumResultcode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.RoadPlanState.ScriptUploadChanged [resultCode: " + resultcode + "]", e);
            }
        }

        private static void scriptdeletechanged(Callback cb, int resultcode) {
            ArsdkFeatureJpsumo.RoadplanstateScriptdeletechangedResultcode enumResultcode = ArsdkFeatureJpsumo.RoadplanstateScriptdeletechangedResultcode.fromValue(resultcode);
            if (enumResultcode == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.RoadplanstateScriptdeletechangedResultcode value " + resultcode);
            try {
                cb.onScriptDeleteChanged(enumResultcode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.RoadPlanState.ScriptDeleteChanged [resultCode: " + resultcode + "]", e);
            }
        }

        private static void playscriptchanged(Callback cb, int resultcode) {
            ArsdkFeatureJpsumo.RoadplanstatePlayscriptchangedResultcode enumResultcode = ArsdkFeatureJpsumo.RoadplanstatePlayscriptchangedResultcode.fromValue(resultcode);
            if (enumResultcode == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.RoadplanstatePlayscriptchangedResultcode value " + resultcode);
            try {
                cb.onPlayScriptChanged(enumResultcode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.RoadPlanState.PlayScriptChanged [resultCode: " + resultcode + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Speed Settings commands 
     */
    public static class SpeedSettings {

        /** Feature uid. */
        public static final int UID = 0x0310;

        /**
         * @deprecated Outdoor property
         * 
         * @param outdoor: 1 if outdoor, 0 if indoor
         */
        public static ArsdkCommand encodeOutdoor(int outdoor) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeOutdoor(cmd.getNativePtr(), outdoor);
            return cmd;
        }

        private static native int nativeEncodeOutdoor(long nativeCmd, int outdoor);
    }

    /**
     * Speed Settings state from product 
     */
    public static class SpeedSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0311;

        /** Uid of OutdoorChanged event. */;
        public static final int OUTDOORCHANGED_UID = 0x0000;

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
             * @deprecated Outdoor property sent by product
             * 
             * @param outdoor: 1 if outdoor, 0 if indoor
             */
            public default void onOutdoorChanged(int outdoor) {}
        }

        private static void outdoorchanged(Callback cb, int outdoor) {
            try {
                cb.onOutdoorChanged(outdoor);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.SpeedSettingsState.OutdoorChanged [outdoor: " + outdoor + "]", e);
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
        public static final int UID = 0x0312;

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
        public static final int UID = 0x0313;

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
            ArsdkFeatureJpsumo.MediastreamingstateVideoenablechangedEnabled enumEnabled = ArsdkFeatureJpsumo.MediastreamingstateVideoenablechangedEnabled.fromValue(enabled);
            if (enumEnabled == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediastreamingstateVideoenablechangedEnabled value " + enabled);
            try {
                cb.onVideoEnableChanged(enumEnabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaStreamingState.VideoEnableChanged [enabled: " + enabled + "]", e);
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
        public static final int UID = 0x0314;

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
            ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedEvent enumEvent = ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedEvent value " + event);
            ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedError enumError = ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordeventPictureeventchangedError value " + error);
            try {
                cb.onPictureEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordEvent.PictureEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }

        private static void videoeventchanged(Callback cb, int event, int error) {
            ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedEvent enumEvent = ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedEvent value " + event);
            ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedError enumError = ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureJpsumo.MediarecordeventVideoeventchangedError value " + error);
            try {
                cb.onVideoEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.MediaRecordEvent.VideoEventChanged [event: " + event + ", error: " + error + "]", e);
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
        public static final int UID = 0x0315;

        /**
         * Set video automatic recording state.
         * 
         * @param enabled: 0: Disabled 1: Enabled.
         */
        public static ArsdkCommand encodeAutorecord(int enabled) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutorecord(cmd.getNativePtr(), enabled);
            return cmd;
        }

        private static native int nativeEncodeAutorecord(long nativeCmd, int enabled);
    }

    /**
     * Video settings state. 
     */
    public static class VideoSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0316;

        /** Uid of AutorecordChanged event. */;
        public static final int AUTORECORDCHANGED_UID = 0x0000;

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
        }

        private static void autorecordchanged(Callback cb, int enabled) {
            try {
                cb.onAutorecordChanged(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: jpsumo.VideoSettingsState.AutorecordChanged [enabled: " + enabled + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
