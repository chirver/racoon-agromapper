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
 * Ardrone3 feature command/event interface.
 */
public class ArsdkFeatureArdrone3 {

    /**
     * The circling direction.
     */
    public enum PilotingCircleDirection {

        /**
         * Circling ClockWise.
         */
        CW(0),

        /**
         * Circling Counter ClockWise.
         */
        CCW(1),

        /**
         * Use drone default Circling direction set by CirclingDirection cmd.
         */
        DEFAULT(2);

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
        public static PilotingCircleDirection fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingCircleDirection(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingCircleDirection> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingCircleDirection e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Orientation mode of the move to.
     */
    public enum PilotingMovetoOrientationMode {

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
        public static PilotingMovetoOrientationMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingMovetoOrientationMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingMovetoOrientationMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingMovetoOrientationMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * POI mode.
     */
    public enum PilotingStartpilotedpoiv2Mode {

        /**
         * Gimbal is locked on the POI.
         */
        LOCKED_GIMBAL(0),

        /**
         * Gimbal is freely controllable.
         */
        FREE_GIMBAL(1);

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
        public static PilotingStartpilotedpoiv2Mode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingStartpilotedpoiv2Mode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingStartpilotedpoiv2Mode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingStartpilotedpoiv2Mode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Direction for the flip.
     */
    public enum AnimationsFlipDirection {

        /**
         * Flip direction front.
         */
        FRONT(0),

        /**
         * Flip direction back.
         */
        BACK(1),

        /**
         * Flip direction right.
         */
        RIGHT(2),

        /**
         * Flip direction left.
         */
        LEFT(3);

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
        public static AnimationsFlipDirection fromValue(int value) {
            return MAP.get(value, null);
        }

        private AnimationsFlipDirection(int value) {
            this.value = value;
        }

        private static final SparseArray<AnimationsFlipDirection> MAP;

        static {
            MAP = new SparseArray<>();
            for (AnimationsFlipDirection e: values())
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
        FAILED(2),

        /**
         * Video was auto stopped.
         */
        AUTOSTOPPED(3);

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
     * Streaming resolution.
     */
    public enum MediarecordstateVideoresolutionstateStreaming {

        /**
         * 360p resolution.
         */
        RES360P(0),

        /**
         * 480p resolution.
         */
        RES480P(1),

        /**
         * 720p resolution.
         */
        RES720P(2),

        /**
         * 1080p resolution.
         */
        RES1080P(3);

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
        public static MediarecordstateVideoresolutionstateStreaming fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstateVideoresolutionstateStreaming(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstateVideoresolutionstateStreaming> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstateVideoresolutionstateStreaming e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Recording resolution.
     */
    public enum MediarecordstateVideoresolutionstateRecording {

        /**
         * 360p resolution.
         */
        RES360P(0),

        /**
         * 480p resolution.
         */
        RES480P(1),

        /**
         * 720p resolution.
         */
        RES720P(2),

        /**
         * 1080p resolution.
         */
        RES1080P(3);

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
        public static MediarecordstateVideoresolutionstateRecording fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediarecordstateVideoresolutionstateRecording(int value) {
            this.value = value;
        }

        private static final SparseArray<MediarecordstateVideoresolutionstateRecording> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediarecordstateVideoresolutionstateRecording e: values())
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
         * Hovering / Circling (for fixed wings) state.
         */
        HOVERING(2),

        /**
         * Flying state.
         */
        FLYING(3),

        /**
         * Landing state.
         */
        LANDING(4),

        /**
         * Emergency state.
         */
        EMERGENCY(5),

        /**
         * User take off state. Waiting for user action to take off.
         */
        USERTAKEOFF(6),

        /**
         * Motor ramping state.
         */
        MOTOR_RAMPING(7),

        /**
         * Emergency landing state. Drone autopilot has detected defective sensor(s). Only Yaw argument in PCMD is taken
         * into account. All others flying commands are ignored.
         */
        EMERGENCY_LANDING(8);

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
     * Drone alert state.
     */
    public enum PilotingstateAlertstatechangedState {

        /**
         * No alert.
         */
        NONE(0),

        /**
         * User emergency alert.
         */
        USER(1),

        /**
         * Cut out alert.
         */
        CUT_OUT(2),

        /**
         * Critical battery alert.
         */
        CRITICAL_BATTERY(3),

        /**
         * Low battery alert.
         */
        LOW_BATTERY(4),

        /**
         * The angle of the drone is too high.
         */
        TOO_MUCH_ANGLE(5),

        /**
         * Almost empty battery alert.
         */
        ALMOST_EMPTY_BATTERY(6),

        /**
         * Magnetometer is disturbed by a magnetic element.
         */
        MAGNETO_PERTUBATION(7),

        /**
         * Local terrestrial magnetic field is too weak.
         */
        MAGNETO_LOW_EARTH_FIELD(8);

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
     * State of navigate home.
     */
    public enum PilotingstateNavigatehomestatechangedState {

        /**
         * Navigate home is available.
         */
        AVAILABLE(0),

        /**
         * Navigate home is in progress.
         */
        INPROGRESS(1),

        /**
         * Navigate home is not available.
         */
        UNAVAILABLE(2),

        /**
         * Navigate home has been received, but its process is pending.
         */
        PENDING(3);

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
        public static PilotingstateNavigatehomestatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateNavigatehomestatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateNavigatehomestatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateNavigatehomestatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Reason of the state.
     */
    public enum PilotingstateNavigatehomestatechangedReason {

        /**
         * User requested a navigate home (available-&gt;inProgress).
         */
        USERREQUEST(0),

        /**
         * Connection between controller and product lost (available-&gt;inProgress).
         */
        CONNECTIONLOST(1),

        /**
         * Low battery occurred (available-&gt;inProgress).
         */
        LOWBATTERY(2),

        /**
         * Navigate home is finished (inProgress-&gt;available).
         */
        FINISHED(3),

        /**
         * Navigate home has been stopped (inProgress-&gt;available).
         */
        STOPPED(4),

        /**
         * Navigate home disabled by product (inProgress-&gt;unavailable or available-&gt;unavailable).
         */
        DISABLED(5),

        /**
         * Navigate home enabled by product (unavailable-&gt;available).
         */
        ENABLED(6);

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
        public static PilotingstateNavigatehomestatechangedReason fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateNavigatehomestatechangedReason(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateNavigatehomestatechangedReason> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateNavigatehomestatechangedReason e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Drone landing state.
     */
    public enum PilotingstateLandingstatechangedState {

        /**
         * Linear landing.
         */
        LINEAR(0),

        /**
         * Spiral landing.
         */
        SPIRAL(1);

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
        public static PilotingstateLandingstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateLandingstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateLandingstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateLandingstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Orientation mode of the move to.
     */
    public enum PilotingstateMovetochangedOrientationMode {

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
        public static PilotingstateMovetochangedOrientationMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateMovetochangedOrientationMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateMovetochangedOrientationMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateMovetochangedOrientationMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Status of the move to.
     */
    public enum PilotingstateMovetochangedStatus {

        /**
         * The drone is actually flying to the given position.
         */
        RUNNING(0),

        /**
         * The drone has reached the target.
         */
        DONE(1),

        /**
         * The move to has been canceled, either by a CancelMoveTo command or when a disconnection appears.
         */
        CANCELED(2),

        /**
         * The move to has not been finished or started because of an error.
         */
        ERROR(3);

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
        public static PilotingstateMovetochangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateMovetochangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateMovetochangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateMovetochangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Motion state.
     */
    public enum PilotingstateMotionstateState {

        /**
         * Drone is steady.
         */
        STEADY(0),

        /**
         * Drone is moving.
         */
        MOVING(1);

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
        public static PilotingstateMotionstateState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateMotionstateState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateMotionstateState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateMotionstateState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Status of the Piloted POI.
     */
    public enum PilotingstatePilotedpoiStatus {

        /**
         * The piloted POI is not available.
         */
        UNAVAILABLE(0),

        /**
         * The piloted POI is available.
         */
        AVAILABLE(1),

        /**
         * Piloted POI has been requested. Waiting to be in state that allow the piloted POI to start.
         */
        PENDING(2),

        /**
         * Piloted POI is running.
         */
        RUNNING(3);

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
        public static PilotingstatePilotedpoiStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePilotedpoiStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePilotedpoiStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePilotedpoiStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * POI mode. This information is only valid when the state is pending or running.
     */
    public enum PilotingstatePilotedpoiv2Mode {

        /**
         * Gimbal is locked on the POI.
         */
        LOCKED_GIMBAL(0),

        /**
         * Gimbal is freely controllable.
         */
        FREE_GIMBAL(1);

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
        public static PilotingstatePilotedpoiv2Mode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePilotedpoiv2Mode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePilotedpoiv2Mode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePilotedpoiv2Mode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Status of the Piloted POI.
     */
    public enum PilotingstatePilotedpoiv2Status {

        /**
         * The piloted POI is not available.
         */
        UNAVAILABLE(0),

        /**
         * The piloted POI is available.
         */
        AVAILABLE(1),

        /**
         * Piloted POI has been requested. Waiting to be in state that allows the piloted POI to start.
         */
        PENDING(2),

        /**
         * Piloted POI is running.
         */
        RUNNING(3);

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
        public static PilotingstatePilotedpoiv2Status fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePilotedpoiv2Status(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePilotedpoiv2Status> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePilotedpoiv2Status e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Status of battery to return home.
     */
    public enum PilotingstateReturnhomebatterycapacityStatus {

        /**
         * The battery is full enough to do a return home.
         */
        OK(0),

        /**
         * The battery is about to be too discharged to do a return home.
         */
        WARNING(1),

        /**
         * The battery level is too low to return to the home position.
         */
        CRITICAL(2),

        /**
         * Battery capacity to do a return home is unknown. This can be either because the home is unknown or the
         * position of the drone is unknown, or the drone has not enough information to determine how long it takes to
         * fly home.
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
        public static PilotingstateReturnhomebatterycapacityStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateReturnhomebatterycapacityStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateReturnhomebatterycapacityStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateReturnhomebatterycapacityStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Status of the relative move.
     */
    public enum PilotingstateMovebychangedStatus {

        /**
         * The drone is actually flying to the relative position.
         */
        RUNNING(0),

        /**
         * The drone has reached the target.
         */
        DONE(1),

        /**
         * The relative move has been canceled, either by a CancelMoveBy command or when a disconnection appears.
         */
        CANCELED(2),

        /**
         * The relative move has not been finished or started because of an error.
         */
        ERROR(3);

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
        public static PilotingstateMovebychangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateMovebychangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateMovebychangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateMovebychangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Reason of the forced landing.
     */
    public enum PilotingstateForcedlandingautotriggerReason {

        /**
         * There is no forced landing auto trigger planned.
         */
        NONE(0),

        /**
         * Battery will soon be critical, so forced landing auto trigger is planned.
         */
        BATTERY_CRITICAL_SOON(1);

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
        public static PilotingstateForcedlandingautotriggerReason fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateForcedlandingautotriggerReason(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateForcedlandingautotriggerReason> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateForcedlandingautotriggerReason e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Drone wind state.
     */
    public enum PilotingstateWindstatechangedState {

        /**
         * The wind strength can be handled properly by the drone.
         */
        OK(0),

        /**
         * The wind strength begins to be too strong for the drone to fly correctly.
         */
        WARNING(1),

        /**
         * The wind strength is too strong for the drone to fly correctly.
         */
        CRITICAL(2);

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
        public static PilotingstateWindstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateWindstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateWindstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateWindstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Drone vibration level.
     */
    public enum PilotingstateVibrationlevelchangedState {

        /**
         * The level of vibration can be handled properly by the drone.
         */
        OK(0),

        /**
         * The level of vibration begins to be too strong for the drone to fly correctly.
         */
        WARNING(1),

        /**
         * The level of vibration is too strong for the drone to fly correctly.
         */
        CRITICAL(2);

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
        public static PilotingstateVibrationlevelchangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateVibrationlevelchangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateVibrationlevelchangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateVibrationlevelchangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Magneto state.
     */
    public enum PilotingstateHeadinglockedstatechangedState {

        /**
         * Magnetometer state allows heading lock.
         */
        OK(0),

        /**
         * Magnetometer detects a weak magnetic field (close to Earth pole), or a pertubated local magnetic field.
         * Magnetometer has not lost heading lock yet.
         */
        WARNING(1),

        /**
         * Magnetometer lost heading lock.
         */
        CRITICAL(2);

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
        public static PilotingstateHeadinglockedstatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateHeadinglockedstatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateHeadinglockedstatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateHeadinglockedstatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the event.
     */
    public enum PilotingeventMovebyendError {

        /**
         * No Error ; The relative displacement.
         */
        OK(0),

        /**
         * Unknown generic error.
         */
        UNKNOWN(1),

        /**
         * The Device is busy ; command moveBy ignored.
         */
        BUSY(2),

        /**
         * Command moveBy is not available ; command moveBy ignored.
         */
        NOTAVAILABLE(3),

        /**
         * Command moveBy interrupted.
         */
        INTERRUPTED(4);

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
        public static PilotingeventMovebyendError fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingeventMovebyendError(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingeventMovebyendError> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingeventMovebyendError e: values())
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
     * The circling direction.
     */
    public enum PilotingsettingsCirclingdirectionValue {

        /**
         * Circling ClockWise.
         */
        CW(0),

        /**
         * Circling Counter ClockWise.
         */
        CCW(1);

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
        public static PilotingsettingsCirclingdirectionValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsCirclingdirectionValue(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsCirclingdirectionValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsCirclingdirectionValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The Pitch mode.
     */
    public enum PilotingsettingsPitchmodeValue {

        /**
         * Positive pitch values will make the drone lower its nose. Negative pitch values will make the drone raise its
         * nose.
         */
        NORMAL(0),

        /**
         * Pitch commands are inverted. Positive pitch values will make the drone raise its nose. Negative pitch values
         * will make the drone lower its nose.
         */
        INVERTED(1);

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
        public static PilotingsettingsPitchmodeValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsPitchmodeValue(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsPitchmodeValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsPitchmodeValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The circling direction.
     */
    public enum PilotingsettingsstateCirclingdirectionchangedValue {

        /**
         * Circling ClockWise.
         */
        CW(0),

        /**
         * Circling Counter ClockWise.
         */
        CCW(1);

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
        public static PilotingsettingsstateCirclingdirectionchangedValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsstateCirclingdirectionchangedValue(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsstateCirclingdirectionchangedValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsstateCirclingdirectionchangedValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The Pitch mode.
     */
    public enum PilotingsettingsstatePitchmodechangedValue {

        /**
         * Positive pitch values will make the drone lower its nose. Negative pitch values will make the drone raise its
         * nose.
         */
        NORMAL(0),

        /**
         * Pitch commands are inverted. Positive pitch values will make the drone raise its nose. Negative pitch values
         * will make the drone lower its nose.
         */
        INVERTED(1);

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
        public static PilotingsettingsstatePitchmodechangedValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsstatePitchmodechangedValue(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsstatePitchmodechangedValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsstatePitchmodechangedValue e: values())
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
     * The type of wifi security (open, wpa2).
     */
    public enum NetworksettingsWifisecurityType {

        /**
         * Wifi is not protected by any security (default).
         */
        OPEN(0),

        /**
         * Wifi is protected by wpa2.
         */
        WPA2(1);

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
        public static NetworksettingsWifisecurityType fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsWifisecurityType(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsWifisecurityType> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsWifisecurityType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the key.
     */
    public enum NetworksettingsWifisecurityKeytype {

        /**
         * Key is plain text, not encrypted.
         */
        PLAIN(0);

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
        public static NetworksettingsWifisecurityKeytype fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsWifisecurityKeytype(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsWifisecurityKeytype> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsWifisecurityKeytype e: values())
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
     * The type of wifi security (open, wpa2).
     */
    public enum NetworksettingsstateWifisecuritychangedType {

        /**
         * Wifi is not protected by any security (default).
         */
        OPEN(0),

        /**
         * Wifi is protected by wpa2.
         */
        WPA2(1);

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
        public static NetworksettingsstateWifisecuritychangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsstateWifisecuritychangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsstateWifisecuritychangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsstateWifisecuritychangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi security (open, wpa2).
     */
    public enum NetworksettingsstateWifisecurityType {

        /**
         * Wifi is not protected by any security (default).
         */
        OPEN(0),

        /**
         * Wifi is protected by wpa2.
         */
        WPA2(1);

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
        public static NetworksettingsstateWifisecurityType fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsstateWifisecurityType(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsstateWifisecurityType> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsstateWifisecurityType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the key.
     */
    public enum NetworksettingsstateWifisecurityKeytype {

        /**
         * Key is plain text, not encrypted.
         */
        PLAIN(0);

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
        public static NetworksettingsstateWifisecurityKeytype fromValue(int value) {
            return MAP.get(value, null);
        }

        private NetworksettingsstateWifisecurityKeytype(int value) {
            this.value = value;
        }

        private static final SparseArray<NetworksettingsstateWifisecurityKeytype> MAP;

        static {
            MAP = new SparseArray<>();
            for (NetworksettingsstateWifisecurityKeytype e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Enumeration of the motor error.
     */
    public enum SettingsstateMotorerrorstatechangedMotorerror {

        /**
         * No error detected.
         */
        NOERROR(0),

        /**
         * EEPROM access failure.
         */
        ERROREEPROM(1),

        /**
         * Motor stalled.
         */
        ERRORMOTORSTALLED(2),

        /**
         * Propeller cutout security triggered.
         */
        ERRORPROPELLERSECURITY(3),

        /**
         * Communication with motor failed by timeout.
         */
        ERRORCOMMLOST(4),

        /**
         * RC emergency stop.
         */
        ERRORRCEMERGENCYSTOP(5),

        /**
         * Motor controler scheduler real-time out of bounds.
         */
        ERRORREALTIME(6),

        /**
         * One or several incorrect values in motor settings.
         */
        ERRORMOTORSETTING(7),

        /**
         * Too hot or too cold Cypress temperature.
         */
        ERRORTEMPERATURE(8),

        /**
         * Battery voltage out of bounds.
         */
        ERRORBATTERYVOLTAGE(9),

        /**
         * Incorrect number of LIPO cells.
         */
        ERRORLIPOCELLS(10),

        /**
         * Defectuous MOSFET or broken motor phases.
         */
        ERRORMOSFET(11),

        /**
         * Not use for BLDC but useful for HAL.
         */
        ERRORBOOTLOADER(12),

        /**
         * Error Made by BLDC_ASSERT().
         */
        ERRORASSERT(13);

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
        public static SettingsstateMotorerrorstatechangedMotorerror fromValue(int value) {
            return MAP.get(value, null);
        }

        private SettingsstateMotorerrorstatechangedMotorerror(int value) {
            this.value = value;
        }

        private static final SparseArray<SettingsstateMotorerrorstatechangedMotorerror> MAP;

        static {
            MAP = new SparseArray<>();
            for (SettingsstateMotorerrorstatechangedMotorerror e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Enumeration of the motor error.
     */
    public enum SettingsstateMotorerrorlasterrorchangedMotorerror {

        /**
         * No error detected.
         */
        NOERROR(0),

        /**
         * EEPROM access failure.
         */
        ERROREEPROM(1),

        /**
         * Motor stalled.
         */
        ERRORMOTORSTALLED(2),

        /**
         * Propeller cutout security triggered.
         */
        ERRORPROPELLERSECURITY(3),

        /**
         * Communication with motor failed by timeout.
         */
        ERRORCOMMLOST(4),

        /**
         * RC emergency stop.
         */
        ERRORRCEMERGENCYSTOP(5),

        /**
         * Motor controler scheduler real-time out of bounds.
         */
        ERRORREALTIME(6),

        /**
         * One or several incorrect values in motor settings.
         */
        ERRORMOTORSETTING(7),

        /**
         * Battery voltage out of bounds.
         */
        ERRORBATTERYVOLTAGE(8),

        /**
         * Incorrect number of LIPO cells.
         */
        ERRORLIPOCELLS(9),

        /**
         * Defectuous MOSFET or broken motor phases.
         */
        ERRORMOSFET(10),

        /**
         * Too hot or too cold Cypress temperature.
         */
        ERRORTEMPERATURE(11),

        /**
         * Not use for BLDC but useful for HAL.
         */
        ERRORBOOTLOADER(12),

        /**
         * Error Made by BLDC_ASSERT().
         */
        ERRORASSERT(13);

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
        public static SettingsstateMotorerrorlasterrorchangedMotorerror fromValue(int value) {
            return MAP.get(value, null);
        }

        private SettingsstateMotorerrorlasterrorchangedMotorerror(int value) {
            this.value = value;
        }

        private static final SparseArray<SettingsstateMotorerrorlasterrorchangedMotorerror> MAP;

        static {
            MAP = new SparseArray<>();
            for (SettingsstateMotorerrorlasterrorchangedMotorerror e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of photo format.
     */
    public enum PicturesettingsPictureformatselectionType {

        /**
         * Take raw image.
         */
        RAW(0),

        /**
         * Take a 4:3 jpeg photo.
         */
        JPEG(1),

        /**
         * Take a 16:9 snapshot from camera.
         */
        SNAPSHOT(2),

        /**
         * Take jpeg fisheye image only.
         */
        JPEG_FISHEYE(3);

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
        public static PicturesettingsPictureformatselectionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsPictureformatselectionType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsPictureformatselectionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsPictureformatselectionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type auto white balance.
     */
    public enum PicturesettingsAutowhitebalanceselectionType {

        /**
         * Auto guess of best white balance params.
         */
        AUTO(0),

        /**
         * Tungsten white balance.
         */
        TUNGSTEN(1),

        /**
         * Daylight white balance.
         */
        DAYLIGHT(2),

        /**
         * Cloudy white balance.
         */
        CLOUDY(3),

        /**
         * White balance for a flash.
         */
        COOL_WHITE(4);

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
        public static PicturesettingsAutowhitebalanceselectionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsAutowhitebalanceselectionType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsAutowhitebalanceselectionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsAutowhitebalanceselectionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video stabilization mode.
     */
    public enum PicturesettingsVideostabilizationmodeMode {

        /**
         * Video flat on roll and pitch.
         */
        ROLL_PITCH(0),

        /**
         * Video flat on pitch only.
         */
        PITCH(1),

        /**
         * Video flat on roll only.
         */
        ROLL(2),

        /**
         * Video follows drone angles.
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
        public static PicturesettingsVideostabilizationmodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsVideostabilizationmodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsVideostabilizationmodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsVideostabilizationmodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video recording mode.
     */
    public enum PicturesettingsVideorecordingmodeMode {

        /**
         * Maximize recording quality.
         */
        QUALITY(0),

        /**
         * Maximize recording time.
         */
        TIME(1);

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
        public static PicturesettingsVideorecordingmodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsVideorecordingmodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsVideorecordingmodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsVideorecordingmodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video framerate.
     */
    public enum PicturesettingsVideoframerateFramerate {

        /**
         * 23.976 frames per second.
         */
        E24_FPS(0),

        /**
         * 25 frames per second.
         */
        E25_FPS(1),

        /**
         * 29.97 frames per second.
         */
        E30_FPS(2);

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
        public static PicturesettingsVideoframerateFramerate fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsVideoframerateFramerate(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsVideoframerateFramerate> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsVideoframerateFramerate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video streaming and recording resolutions.
     */
    public enum PicturesettingsVideoresolutionsType {

        /**
         * 1080p recording, 480p streaming.
         */
        REC1080_STREAM480(0),

        /**
         * 720p recording, 720p streaming.
         */
        REC720_STREAM720(1);

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
        public static PicturesettingsVideoresolutionsType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsVideoresolutionsType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsVideoresolutionsType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsVideoresolutionsType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of photo format.
     */
    public enum PicturesettingsstatePictureformatchangedType {

        /**
         * Take raw image.
         */
        RAW(0),

        /**
         * Take a 4:3 jpeg photo.
         */
        JPEG(1),

        /**
         * Take a 16:9 snapshot from camera.
         */
        SNAPSHOT(2),

        /**
         * Take jpeg fisheye image only.
         */
        JPEG_FISHEYE(3);

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
        public static PicturesettingsstatePictureformatchangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstatePictureformatchangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstatePictureformatchangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstatePictureformatchangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type auto white balance.
     */
    public enum PicturesettingsstateAutowhitebalancechangedType {

        /**
         * Auto guess of best white balance params.
         */
        AUTO(0),

        /**
         * Tungsten white balance.
         */
        TUNGSTEN(1),

        /**
         * Daylight white balance.
         */
        DAYLIGHT(2),

        /**
         * Cloudy white balance.
         */
        CLOUDY(3),

        /**
         * White balance for a flash.
         */
        COOL_WHITE(4);

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
        public static PicturesettingsstateAutowhitebalancechangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstateAutowhitebalancechangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstateAutowhitebalancechangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstateAutowhitebalancechangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video stabilization mode.
     */
    public enum PicturesettingsstateVideostabilizationmodechangedMode {

        /**
         * Video flat on roll and pitch.
         */
        ROLL_PITCH(0),

        /**
         * Video flat on pitch only.
         */
        PITCH(1),

        /**
         * Video flat on roll only.
         */
        ROLL(2),

        /**
         * Video follows drone angles.
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
        public static PicturesettingsstateVideostabilizationmodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstateVideostabilizationmodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstateVideostabilizationmodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstateVideostabilizationmodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video recording mode.
     */
    public enum PicturesettingsstateVideorecordingmodechangedMode {

        /**
         * Maximize recording quality.
         */
        QUALITY(0),

        /**
         * Maximize recording time.
         */
        TIME(1);

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
        public static PicturesettingsstateVideorecordingmodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstateVideorecordingmodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstateVideorecordingmodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstateVideorecordingmodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video framerate.
     */
    public enum PicturesettingsstateVideoframeratechangedFramerate {

        /**
         * 23.976 frames per second.
         */
        E24_FPS(0),

        /**
         * 25 frames per second.
         */
        E25_FPS(1),

        /**
         * 29.97 frames per second.
         */
        E30_FPS(2);

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
        public static PicturesettingsstateVideoframeratechangedFramerate fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstateVideoframeratechangedFramerate(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstateVideoframeratechangedFramerate> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstateVideoframeratechangedFramerate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video resolution type.
     */
    public enum PicturesettingsstateVideoresolutionschangedType {

        /**
         * 1080p recording, 480p streaming.
         */
        REC1080_STREAM480(0),

        /**
         * 720p recording, 720p streaming.
         */
        REC720_STREAM720(1);

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
        public static PicturesettingsstateVideoresolutionschangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private PicturesettingsstateVideoresolutionschangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<PicturesettingsstateVideoresolutionschangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (PicturesettingsstateVideoresolutionschangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * stream mode.
     */
    public enum MediastreamingVideostreammodeMode {

        /**
         * Minimize latency with average reliability (best for piloting).
         */
        LOW_LATENCY(0),

        /**
         * Maximize the reliability with an average latency (best when streaming quality is important but not the
         * latency).
         */
        HIGH_RELIABILITY(1),

        /**
         * Maximize the reliability using a framerate decimation with an average latency (best when streaming quality is
         * important but not the latency).
         */
        HIGH_RELIABILITY_LOW_FRAMERATE(2);

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
        public static MediastreamingVideostreammodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediastreamingVideostreammodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<MediastreamingVideostreammodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediastreamingVideostreammodeMode e: values())
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
     * stream mode.
     */
    public enum MediastreamingstateVideostreammodechangedMode {

        /**
         * Minimize latency with average reliability (best for piloting).
         */
        LOW_LATENCY(0),

        /**
         * Maximize the reliability with an average latency (best when streaming quality is important but not the
         * latency).
         */
        HIGH_RELIABILITY(1),

        /**
         * Maximize the reliability using a framerate decimation with an average latency (best when streaming quality is
         * important but not the latency).
         */
        HIGH_RELIABILITY_LOW_FRAMERATE(2);

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
        public static MediastreamingstateVideostreammodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private MediastreamingstateVideostreammodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<MediastreamingstateVideostreammodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (MediastreamingstateVideostreammodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of the home position.
     */
    public enum GpssettingsHometypeType {

        /**
         * The drone will try to return to the take off position.
         */
        TAKEOFF(0),

        /**
         * The drone will try to return to the pilot position.
         */
        PILOT(1),

        /**
         * The drone will try to return to the target of the current (or last) follow me.
         */
        FOLLOWEE(2);

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
        public static GpssettingsHometypeType fromValue(int value) {
            return MAP.get(value, null);
        }

        private GpssettingsHometypeType(int value) {
            this.value = value;
        }

        private static final SparseArray<GpssettingsHometypeType> MAP;

        static {
            MAP = new SparseArray<>();
            for (GpssettingsHometypeType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The state of the gps update.
     */
    public enum GpssettingsstateGpsupdatestatechangedState {

        /**
         * Drone GPS update succeed.
         */
        UPDATED(0),

        /**
         * Drone GPS update In progress.
         */
        INPROGRESS(1),

        /**
         * Drone GPS update failed.
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
        public static GpssettingsstateGpsupdatestatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private GpssettingsstateGpsupdatestatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<GpssettingsstateGpsupdatestatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (GpssettingsstateGpsupdatestatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of the home position.
     */
    public enum GpssettingsstateHometypechangedType {

        /**
         * The drone will try to return to the take off position.
         */
        TAKEOFF(0),

        /**
         * The drone will try to return to the pilot position.
         */
        PILOT(1),

        /**
         * The drone will try to return to the target of the current (or last) follow me.
         */
        FOLLOWEE(2);

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
        public static GpssettingsstateHometypechangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private GpssettingsstateHometypechangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<GpssettingsstateHometypechangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (GpssettingsstateHometypechangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the electric frequency.
     */
    public enum AntiflickeringElectricfrequencyFrequency {

        /**
         * Electric frequency of the country is 50hz.
         */
        FIFTYHERTZ(0),

        /**
         * Electric frequency of the country is 60hz.
         */
        SIXTYHERTZ(1);

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
        public static AntiflickeringElectricfrequencyFrequency fromValue(int value) {
            return MAP.get(value, null);
        }

        private AntiflickeringElectricfrequencyFrequency(int value) {
            this.value = value;
        }

        private static final SparseArray<AntiflickeringElectricfrequencyFrequency> MAP;

        static {
            MAP = new SparseArray<>();
            for (AntiflickeringElectricfrequencyFrequency e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Mode of the anti flickering functionnality.
     */
    public enum AntiflickeringSetmodeMode {

        /**
         * Anti flickering based on the electric frequency previously sent.
         */
        AUTO(0),

        /**
         * Anti flickering based on a fixed frequency of 50Hz.
         */
        FIXEDFIFTYHERTZ(1),

        /**
         * Anti flickering based on a fixed frequency of 60Hz.
         */
        FIXEDSIXTYHERTZ(2);

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
        public static AntiflickeringSetmodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private AntiflickeringSetmodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<AntiflickeringSetmodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (AntiflickeringSetmodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the electric frequency.
     */
    public enum AntiflickeringstateElectricfrequencychangedFrequency {

        /**
         * Electric frequency of the country is 50hz.
         */
        FIFTYHERTZ(0),

        /**
         * Electric frequency of the country is 60hz.
         */
        SIXTYHERTZ(1);

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
        public static AntiflickeringstateElectricfrequencychangedFrequency fromValue(int value) {
            return MAP.get(value, null);
        }

        private AntiflickeringstateElectricfrequencychangedFrequency(int value) {
            this.value = value;
        }

        private static final SparseArray<AntiflickeringstateElectricfrequencychangedFrequency> MAP;

        static {
            MAP = new SparseArray<>();
            for (AntiflickeringstateElectricfrequencychangedFrequency e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Mode of the anti flickering functionnality.
     */
    public enum AntiflickeringstateModechangedMode {

        /**
         * Anti flickering based on the electric frequency previously sent.
         */
        AUTO(0),

        /**
         * Anti flickering based on a fixed frequency of 50Hz.
         */
        FIXEDFIFTYHERTZ(1),

        /**
         * Anti flickering based on a fixed frequency of 60Hz.
         */
        FIXEDSIXTYHERTZ(2);

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
        public static AntiflickeringstateModechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private AntiflickeringstateModechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<AntiflickeringstateModechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (AntiflickeringstateModechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of the return home.
     */
    public enum GpsstateHometypeavailabilitychangedType {

        /**
         * The drone has enough information to return to the take off position.
         */
        TAKEOFF(0),

        /**
         * The drone has enough information to return to the pilot position.
         */
        PILOT(1),

        /**
         * The drone has not enough information, it will return to the first GPS fix.
         */
        FIRST_FIX(2),

        /**
         * The drone has enough information to return to the target of the current (or last) follow me.
         */
        FOLLOWEE(3);

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
        public static GpsstateHometypeavailabilitychangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private GpsstateHometypeavailabilitychangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<GpsstateHometypeavailabilitychangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (GpsstateHometypeavailabilitychangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of the return home chosen.
     */
    public enum GpsstateHometypechosenchangedType {

        /**
         * The drone will return to the take off position.
         */
        TAKEOFF(0),

        /**
         * The drone will return to the pilot position In this case, the drone will use the position given by
         * ARDrone3-SendControllerGPS.
         */
        PILOT(1),

        /**
         * The drone has not enough information, it will return to the first GPS fix.
         */
        FIRST_FIX(2),

        /**
         * The drone will return to the target of the current (or last) follow me In this case, the drone will use the
         * position of the target of the followMe (given by ControllerInfo-GPS).
         */
        FOLLOWEE(3);

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
        public static GpsstateHometypechosenchangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private GpsstateHometypechosenchangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<GpsstateHometypechosenchangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (GpsstateHometypechosenchangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Accessory type.
     */
    public enum AccessorystateConnectedaccessoriesAccessoryType {

        /**
         * Parrot Sequoia (multispectral camera for agriculture).
         */
        SEQUOIA(0),

        /**
         * FLIR camera (thermal+rgb camera).
         */
        FLIR(1);

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
        public static AccessorystateConnectedaccessoriesAccessoryType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccessorystateConnectedaccessoriesAccessoryType(int value) {
            this.value = value;
        }

        private static final SparseArray<AccessorystateConnectedaccessoriesAccessoryType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccessorystateConnectedaccessoriesAccessoryType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of the alert sound.
     */
    public enum SoundstateAlertsoundState {

        /**
         * Alert sound is not playing.
         */
        STOPPED(0),

        /**
         * Alert sound is playing.
         */
        PLAYING(1);

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
        public static SoundstateAlertsoundState fromValue(int value) {
            return MAP.get(value, null);
        }

        private SoundstateAlertsoundState(int value) {
            this.value = value;
        }

        private static final SparseArray<SoundstateAlertsoundState> MAP;

        static {
            MAP = new SparseArray<>();
            for (SoundstateAlertsoundState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * All commands related to piloting the drone 
     */
    public static class Piloting {

        /** Feature uid. */
        public static final int UID = 0x0100;

        /**
         * Ask the drone to take off. On the fixed wings (such as Disco): not used except to cancel a land.
         */
        public static ArsdkCommand encodeTakeOff() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeTakeOff(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Move the drone. The libARController is sending the command each 50ms.  **Please note that you should call
         * setPilotingPCMD and not sendPilotingPCMD because the libARController is handling the periodicity and the
         * buffer on which it is sent.**
         * 
         * @param flag: Boolean flag: 1 if the roll and pitch values should be taken in consideration. 0 otherwise
         * @param roll: Roll angle as signed percentage. On copters: Roll angle expressed as signed percentage of the
         * max pitch/roll setting, in range [-100, 100] -100 corresponds to a roll angle of max pitch/roll to the left
         * (drone will fly left) 100 corresponds to a roll angle of max pitch/roll to the right (drone will fly right)
         * This value may be clamped if necessary, in order to respect the maximum supported physical tilt of the
         * copter.  On fixed wings: Roll angle expressed as signed percentage of the physical max roll of the wing, in
         * range [-100, 100] Negative value makes the plane fly to the left Positive value makes the plane fly to the
         * right
         * @param pitch: Pitch angle as signed percentage. On copters: Expressed as signed percentage of the max
         * pitch/roll setting, in range [-100, 100] -100 corresponds to a pitch angle of max pitch/roll towards sky
         * (drone will fly backward) 100 corresponds to a pitch angle of max pitch/roll towards ground (drone will fly
         * forward) This value may be clamped if necessary, in order to respect the maximum supported physical tilt of
         * the copter.  On fixed wings: Expressed as signed percentage of the physical max pitch of the wing, in range
         * [-100, 100] Negative value makes the plane fly in direction of the sky Positive value makes the plane fly in
         * direction of the ground
         * @param yaw: Yaw rotation speed as signed percentage. On copters: Expressed as signed percentage of the max
         * yaw rotation speed setting, in range [-100, 100]. -100 corresponds to a counter-clockwise rotation of max yaw
         * rotation speed 100 corresponds to a clockwise rotation of max yaw rotation speed This value may be clamped if
         * necessary, in order to respect the maximum supported physical tilt of the copter.  On fixed wings: Giving
         * more than a fixed value (75% for the moment) triggers a circle. Positive value will trigger a clockwise
         * circling Negative value will trigger a counter-clockwise circling
         * @param gaz: Throttle as signed percentage. On copters: Expressed as signed percentage of the max vertical
         * speed setting, in range [-100, 100] -100 corresponds to a max vertical speed towards ground 100 corresponds
         * to a max vertical speed towards sky This value may be clamped if necessary, in order to respect the maximum
         * supported physical tilt of the copter. During the landing phase, putting some positive gaz will cancel the
         * land.  On fixed wings: Expressed as signed percentage of the physical max throttle, in range [-100, 100]
         * Negative value makes the plane fly slower Positive value makes the plane fly faster
         * @param timestampandseqnum: Command timestamp in milliseconds (low 24 bits) + command sequence number (high 8
         * bits) [0;255].
         */
        public static ArsdkCommand encodePCMD(int flag, int roll, int pitch, int yaw, int gaz, long timestampandseqnum) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePCMD(cmd.getNativePtr(), flag, roll, pitch, yaw, gaz, timestampandseqnum);
            return cmd;
        }

        /**
         * Land. Please note that on copters, if you put some positive gaz (in the [PilotingCommand](#1-0-2)) during the
         * landing, it will cancel it.
         */
        public static ArsdkCommand encodeLanding() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeLanding(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Cut out the motors. This cuts immediatly the motors. The drone will fall. This command is sent on a dedicated
         * high priority buffer which will infinitely retry to send it if the command is not delivered.
         */
        public static ArsdkCommand encodeEmergency() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeEmergency(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Return home. Ask the drone to fly to its [HomePosition](#1-24-0). The availability of the return home can be
         * get from [ReturnHomeState](#1-4-3). Please note that the drone will wait to be hovering to start its return
         * home. This means that it will wait to have a [flag](#1-0-2) set at 0.
         * 
         * @param start: 1 to start the navigate home, 0 to stop it
         */
        public static ArsdkCommand encodeNavigateHome(int start) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeNavigateHome(cmd.getNativePtr(), start);
            return cmd;
        }

        /**
         * Move the drone to a relative position and rotate heading by a given angle. Moves are relative to the current
         * drone orientation, (drone's reference). Also note that the given rotation will not modify the move (i.e.
         * moves are always rectilinear).
         * 
         * @param dx: Wanted displacement along the front axis [m]
         * @param dy: Wanted displacement along the right axis [m]
         * @param dz: Wanted displacement along the down axis [m]
         * @param dpsi: Wanted rotation of heading [rad]
         */
        public static ArsdkCommand encodeMoveBy(float dx, float dy, float dz, float dpsi) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMoveBy(cmd.getNativePtr(), dx, dy, dz, dpsi);
            return cmd;
        }

        /**
         * Prepare the drone to take off. On copters: initiates the thrown takeoff. Note that the drone will do the
         * thrown take off even if it is steady. On fixed wings: initiates the take off process on the fixed wings.
         * Setting the state to 0 will cancel the preparation. You can cancel it before that the drone takes off.
         * 
         * @param state: State of user take off mode - 1 to enter in user take off. - 0 to exit from user take off.
         */
        public static ArsdkCommand encodeUserTakeOff(int state) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeUserTakeOff(cmd.getNativePtr(), state);
            return cmd;
        }

        /**
         * Make the fixed wing circle. The circle will use the [CirclingAltitude](#1-6-14) and the
         * [CirclingRadius](#1-6-13)
         * 
         * @param direction:
         */
        public static ArsdkCommand encodeCircle(@NonNull PilotingCircleDirection direction) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCircle(cmd.getNativePtr(), direction.value);
            return cmd;
        }

        /**
         * Move the drone to a specified location. If a new command moveTo is sent, the drone will immediatly run it (no
         * cancel will be issued). If a [CancelMoveTo](#1-0-11) command is sent, the moveTo is stopped. During the
         * moveTo, all pitch, roll and gaz values of the piloting command will be ignored by the drone. However, the yaw
         * value can be used.
         * 
         * @param latitude: Latitude of the location (in degrees) to reach
         * @param longitude: Longitude of the location (in degrees) to reach
         * @param altitude: Altitude above take off point (in m) to reach
         * @param orientationMode:
         * @param heading: Heading (relative to the North in degrees). This value is only used if the orientation mode
         * is HEADING_START or HEADING_DURING
         */
        public static ArsdkCommand encodeMoveTo(double latitude, double longitude, double altitude, @NonNull PilotingMovetoOrientationMode orientationMode, float heading) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMoveTo(cmd.getNativePtr(), latitude, longitude, altitude, orientationMode.value, heading);
            return cmd;
        }

        /**
         * Cancel the current moveTo. If there is no current moveTo, this command has no effect.
         */
        public static ArsdkCommand encodeCancelMoveTo() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCancelMoveTo(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Start a piloted Point Of Interest. During a piloted POI, the drone will always look at the given POI but can
         * be piloted normally. However, yaw value is ignored. Camera tilt and pan command is also ignored. Ignored if
         * [PilotedPOI](#1-4-14) state is UNAVAILABLE.
         * 
         * @param latitude: Latitude of the location (in degrees) to look at
         * @param longitude: Longitude of the location (in degrees) to look at
         * @param altitude: Altitude above take off point (in m) to look at
         */
        @Deprecated
        public static ArsdkCommand encodeStartPilotedPOI(double latitude, double longitude, double altitude) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStartPilotedPOI(cmd.getNativePtr(), latitude, longitude, altitude);
            return cmd;
        }

        /**
         * Stop the piloted Point Of Interest (with or without gimbal control). If [PilotedPOI](#1-4-14) or
         * [PilotedPOIV2](#1-4-22) state is RUNNING or PENDING, stop it.
         */
        public static ArsdkCommand encodeStopPilotedPOI() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStopPilotedPOI(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Cancel the current relative move. If there is no current relative move, this command has no effect.
         */
        public static ArsdkCommand encodeCancelMoveBy() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCancelMoveBy(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Start a piloted Point Of Interest. During a piloted POI, the drone always points towards the given POI but
         * can be piloted normally. However, yaw value is ignored. The gimbal behavior depends on the mode argument: -
         * in locked gimbal mode, the gimbal always looks at the POI, and gimbal control command is ignored by the
         * drone, - in free gimbal mode, the gimbal initially looks at the POI, and is then freely controllable by the
         * gimbal command. Ignored if [PilotedPOIV2](#1-4-22) state is UNAVAILABLE.
         * 
         * @param latitude: Latitude of the location (in degrees) to look at
         * @param longitude: Longitude of the location (in degrees) to look at
         * @param altitude: Altitude above take off point (in m) to look at
         * @param mode:
         */
        public static ArsdkCommand encodeStartPilotedPOIV2(double latitude, double longitude, double altitude, @NonNull PilotingStartpilotedpoiv2Mode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStartPilotedPOIV2(cmd.getNativePtr(), latitude, longitude, altitude, mode.value);
            return cmd;
        }

        private static native int nativeEncodeTakeOff(long nativeCmd);
        private static native int nativeEncodePCMD(long nativeCmd, int flag, int roll, int pitch, int yaw, int gaz, long timestampAndSeqNum);
        private static native int nativeEncodeLanding(long nativeCmd);
        private static native int nativeEncodeEmergency(long nativeCmd);
        private static native int nativeEncodeNavigateHome(long nativeCmd, int start);
        private static native int nativeEncodeMoveBy(long nativeCmd, float dX, float dY, float dZ, float dPsi);
        private static native int nativeEncodeUserTakeOff(long nativeCmd, int state);
        private static native int nativeEncodeCircle(long nativeCmd, int direction);
        private static native int nativeEncodeMoveTo(long nativeCmd, double latitude, double longitude, double altitude, int orientation_mode, float heading);
        private static native int nativeEncodeCancelMoveTo(long nativeCmd);
        private static native int nativeEncodeStartPilotedPOI(long nativeCmd, double latitude, double longitude, double altitude);
        private static native int nativeEncodeStopPilotedPOI(long nativeCmd);
        private static native int nativeEncodeCancelMoveBy(long nativeCmd);
        private static native int nativeEncodeStartPilotedPOIV2(long nativeCmd, double latitude, double longitude, double altitude, int mode);
    }

    /**
     * Ask the drone to move camera 
     */
    public static class Camera {

        /** Feature uid. */
        public static final int UID = 0x0101;

        /**
         * Move the camera. You can get min and max values for tilt and pan using [CameraInfo](#0-15-0).
         * 
         * @param tilt: Tilt camera consign for the drone (in degree) The value is saturated by the drone. Saturation
         * value is sent by thre drone through CameraSettingsChanged command.
         * @param pan: Pan camera consign for the drone (in degree) The value is saturated by the drone. Saturation
         * value is sent by thre drone through CameraSettingsChanged command.
         */
        @Deprecated
        public static ArsdkCommand encodeOrientation(int tilt, int pan) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeOrientation(cmd.getNativePtr(), tilt, pan);
            return cmd;
        }

        /**
         * Move the camera. You can get min and max values for tilt and pan using [CameraInfo](#0-15-0).
         * 
         * @param tilt: Tilt camera consign for the drone (in degree) The value is saturated by the drone. Saturation
         * value is sent by thre drone through CameraSettingsChanged command.
         * @param pan: Pan camera consign for the drone (in degree) The value is saturated by the drone. Saturation
         * value is sent by thre drone through CameraSettingsChanged command.
         */
        public static ArsdkCommand encodeOrientationV2(float tilt, float pan) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeOrientationV2(cmd.getNativePtr(), tilt, pan);
            return cmd;
        }

        /**
         * Move the camera given velocity consign. You can get min and max values for tilt and pan using
         * [CameraVelocityRange](#1-25-4).
         * 
         * @param tilt: Tilt camera velocity consign [deg/s] Negative tilt velocity move camera to bottom Positive tilt
         * velocity move camera to top
         * @param pan: Pan camera velocity consign [deg/s] Negative pan velocity move camera to left Positive pan
         * velocity move camera to right
         */
        public static ArsdkCommand encodeVelocity(float tilt, float pan) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVelocity(cmd.getNativePtr(), tilt, pan);
            return cmd;
        }

        private static native int nativeEncodeOrientation(long nativeCmd, int tilt, int pan);
        private static native int nativeEncodeOrientationV2(long nativeCmd, float tilt, float pan);
        private static native int nativeEncodeVelocity(long nativeCmd, float tilt, float pan);
    }

    /**
     * Piloting Settings commands 
     */
    public static class PilotingSettings {

        /** Feature uid. */
        public static final int UID = 0x0102;

        /**
         * Set max altitude. The drone will not fly over this max altitude when it is in manual piloting. Please note
         * that if you set a max altitude which is below the current drone altitude, the drone will not go to given max
         * altitude. You can get the bounds in the event [MaxAltitude](#1-6-0).
         * 
         * @param current: Current altitude max in m
         */
        public static ArsdkCommand encodeMaxAltitude(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxAltitude(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set max pitch/roll. This represent the max inclination allowed by the drone. You can get the bounds with the
         * commands [MaxPitchRoll](#1-6-1).
         * 
         * @param current: Current tilt max in degree
         */
        public static ArsdkCommand encodeMaxTilt(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxTilt(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set absolut control.
         * 
         * @param on: 1 to enable, 0 to disable
         */
        @Deprecated
        public static ArsdkCommand encodeAbsolutControl(int on) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAbsolutControl(cmd.getNativePtr(), on);
            return cmd;
        }

        /**
         * Set max distance. You can get the bounds from the event [MaxDistance](#1-6-3).  If [Geofence](#1-6-4) is
         * activated, the drone won't fly over the given max distance.
         * 
         * @param value: Current max distance in meter
         */
        public static ArsdkCommand encodeMaxDistance(float value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxDistance(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Enable geofence. If geofence is enabled, the drone won't fly over the given max distance. You can get the max
         * distance from the event [MaxDistance](#1-6-3). For copters: the distance is computed from the controller
         * position, if this position is not known, it will use the take off. For fixed wings: the distance is computed
         * from the take off position.
         * 
         * @param shouldnotflyover: 1 if the drone can't fly further than max distance, 0 if no limitation on the drone
         * should be done
         */
        public static ArsdkCommand encodeNoFlyOverMaxDistance(int shouldnotflyover) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeNoFlyOverMaxDistance(cmd.getNativePtr(), shouldnotflyover);
            return cmd;
        }

        /**
         * Set banked turn mode. When banked turn mode is enabled, the drone will use yaw values from the piloting
         * command to infer with roll and pitch on the drone when its horizontal speed is not null.
         * 
         * @param value: 1 to enable, 0 to disable
         */
        public static ArsdkCommand encodeBankedTurn(int value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeBankedTurn(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Set minimum altitude. Only available for fixed wings.
         * 
         * @param current: Current altitude min in m
         */
        public static ArsdkCommand encodeMinAltitude(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMinAltitude(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set default circling direction. This direction will be used when the drone use an automatic circling or when
         * [CIRCLE](#1-0-9) is sent with direction *default*. Only available for fixed wings.
         * 
         * @param value:
         */
        public static ArsdkCommand encodeCirclingDirection(@NonNull PilotingsettingsCirclingdirectionValue value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCirclingDirection(cmd.getNativePtr(), value.value);
            return cmd;
        }

        /**
         * Set circling radius. Only available for fixed wings.
         * 
         * @param value: The circling radius in meter
         */
        @Deprecated
        public static ArsdkCommand encodeCirclingRadius(int value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCirclingRadius(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Set min circling altitude (not used during take off). Only available for fixed wings.
         * 
         * @param value: The circling altitude in meter
         */
        public static ArsdkCommand encodeCirclingAltitude(int value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCirclingAltitude(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Set pitch mode. Only available for fixed wings.
         * 
         * @param value:
         */
        public static ArsdkCommand encodePitchMode(@NonNull PilotingsettingsPitchmodeValue value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePitchMode(cmd.getNativePtr(), value.value);
            return cmd;
        }

        /**
         * Enable/disable the motion detection. If the motion detection is enabled, the drone will send its
         * [MotionState](#1-4-13) when its [FlyingState](#1-4-1) is landed. If the motion detection is disabled,
         * [MotionState](#1-4-13) is steady.
         * 
         * @param enable: 1 to enable the motion detection, 0 to disable it.
         */
        public static ArsdkCommand encodeSetMotionDetectionMode(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetMotionDetectionMode(cmd.getNativePtr(), enable);
            return cmd;
        }

        private static native int nativeEncodeMaxAltitude(long nativeCmd, float current);
        private static native int nativeEncodeMaxTilt(long nativeCmd, float current);
        private static native int nativeEncodeAbsolutControl(long nativeCmd, int on);
        private static native int nativeEncodeMaxDistance(long nativeCmd, float value);
        private static native int nativeEncodeNoFlyOverMaxDistance(long nativeCmd, int shouldNotFlyOver);
        private static native int nativeEncodeBankedTurn(long nativeCmd, int value);
        private static native int nativeEncodeMinAltitude(long nativeCmd, float current);
        private static native int nativeEncodeCirclingDirection(long nativeCmd, int value);
        private static native int nativeEncodeCirclingRadius(long nativeCmd, int value);
        private static native int nativeEncodeCirclingAltitude(long nativeCmd, int value);
        private static native int nativeEncodePitchMode(long nativeCmd, int value);
        private static native int nativeEncodeSetMotionDetectionMode(long nativeCmd, int enable);
    }

    /**
     * Events of media recording 
     */
    public static class MediaRecordEvent {

        /** Feature uid. */
        public static final int UID = 0x0103;

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
             * Picture taken.  **This event is a notification, you can't retrieve it in the cache of the device
             * controller.**
             * 
             * @param event:
             * @param error:
             */
            public default void onPictureEventChanged(@Nullable MediarecordeventPictureeventchangedEvent event, @Nullable MediarecordeventPictureeventchangedError error) {}

            /**
             * Video record notification.  **This event is a notification, you can't retrieve it in the cache of the
             * device controller.**
             * 
             * @param event:
             * @param error:
             */
            public default void onVideoEventChanged(@Nullable MediarecordeventVideoeventchangedEvent event, @Nullable MediarecordeventVideoeventchangedError error) {}
        }

        private static void pictureeventchanged(Callback cb, int event, int error) {
            ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedEvent enumEvent = ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedEvent value " + event);
            ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedError enumError = ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordeventPictureeventchangedError value " + error);
            try {
                cb.onPictureEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordEvent.PictureEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }

        private static void videoeventchanged(Callback cb, int event, int error) {
            ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedEvent enumEvent = ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedEvent value " + event);
            ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedError enumError = ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordeventVideoeventchangedError value " + error);
            try {
                cb.onVideoEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordEvent.VideoEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * State from drone 
     */
    public static class PilotingState {

        /** Feature uid. */
        public static final int UID = 0x0104;

        /** Uid of FlyingStateChanged event. */;
        public static final int FLYINGSTATECHANGED_UID = 0x0001;

        /** Uid of AlertStateChanged event. */;
        public static final int ALERTSTATECHANGED_UID = 0x0002;

        /** Uid of NavigateHomeStateChanged event. */;
        public static final int NAVIGATEHOMESTATECHANGED_UID = 0x0003;

        /** Uid of PositionChanged event. */;
        public static final int POSITIONCHANGED_UID = 0x0004;

        /** Uid of SpeedChanged event. */;
        public static final int SPEEDCHANGED_UID = 0x0005;

        /** Uid of AttitudeChanged event. */;
        public static final int ATTITUDECHANGED_UID = 0x0006;

        /** Uid of AltitudeChanged event. */;
        public static final int ALTITUDECHANGED_UID = 0x0008;

        /** Uid of GpsLocationChanged event. */;
        public static final int GPSLOCATIONCHANGED_UID = 0x0009;

        /** Uid of LandingStateChanged event. */;
        public static final int LANDINGSTATECHANGED_UID = 0x000A;

        /** Uid of AirSpeedChanged event. */;
        public static final int AIRSPEEDCHANGED_UID = 0x000B;

        /** Uid of moveToChanged event. */;
        public static final int MOVETOCHANGED_UID = 0x000C;

        /** Uid of MotionState event. */;
        public static final int MOTIONSTATE_UID = 0x000D;

        /** Uid of PilotedPOI event. */;
        public static final int PILOTEDPOI_UID = 0x000E;

        /** Uid of ReturnHomeBatteryCapacity event. */;
        public static final int RETURNHOMEBATTERYCAPACITY_UID = 0x000F;

        /** Uid of moveByChanged event. */;
        public static final int MOVEBYCHANGED_UID = 0x0010;

        /** Uid of HoveringWarning event. */;
        public static final int HOVERINGWARNING_UID = 0x0011;

        /** Uid of ForcedLandingAutoTrigger event. */;
        public static final int FORCEDLANDINGAUTOTRIGGER_UID = 0x0012;

        /** Uid of WindStateChanged event. */;
        public static final int WINDSTATECHANGED_UID = 0x0013;

        /** Uid of VibrationLevelChanged event. */;
        public static final int VIBRATIONLEVELCHANGED_UID = 0x0014;

        /** Uid of AltitudeAboveGroundChanged event. */;
        public static final int ALTITUDEABOVEGROUNDCHANGED_UID = 0x0015;

        /** Uid of PilotedPOIV2 event. */;
        public static final int PILOTEDPOIV2_UID = 0x0016;

        /** Uid of HeadingLockedStateChanged event. */;
        public static final int HEADINGLOCKEDSTATECHANGED_UID = 0x0017;

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
             * Flying state.
             * 
             * @param state:
             */
            public default void onFlyingStateChanged(@Nullable PilotingstateFlyingstatechangedState state) {}

            /**
             * Alert state.
             * 
             * @param state:
             */
            public default void onAlertStateChanged(@Nullable PilotingstateAlertstatechangedState state) {}

            /**
             * Return home state. Availability is related to gps fix, magnetometer calibration.
             * 
             * @param state:
             * @param reason:
             */
            public default void onNavigateHomeStateChanged(@Nullable PilotingstateNavigatehomestatechangedState state, @Nullable PilotingstateNavigatehomestatechangedReason reason) {}

            /**
             * Drone's position changed.
             * 
             * @param latitude: Latitude position in decimal degrees (500.0 if not available)
             * @param longitude: Longitude position in decimal degrees (500.0 if not available)
             * @param altitude: Altitude in meters (from GPS)
             */
            public default void onPositionChanged(double latitude, double longitude, double altitude) {}

            /**
             * Drone's speed changed. Expressed in the NED referential (North-East-Down).
             * 
             * @param speedx: Speed relative to the North (when drone moves to the north, speed is &gt; 0) (in m/s)
             * @param speedy: Speed relative to the East (when drone moves to the east, speed is &gt; 0) (in m/s)
             * @param speedz: Speed on the z axis (when drone moves down, speed is &gt; 0) (in m/s)
             */
            public default void onSpeedChanged(float speedx, float speedy, float speedz) {}

            /**
             * Drone's attitude changed.
             * 
             * @param roll: roll value (in radian)
             * @param pitch: Pitch value (in radian)
             * @param yaw: Yaw value (in radian)
             */
            public default void onAttitudeChanged(float roll, float pitch, float yaw) {}

            /**
             * Drone's altitude changed. The altitude reported is the altitude above the take off point. To get the
             * altitude above sea level, see [PositionChanged](#1-4-4).
             * 
             * @param altitude: Altitude in meters
             */
            public default void onAltitudeChanged(double altitude) {}

            /**
             * Drone's location changed. This event is meant to replace [PositionChanged](#1-4-4).
             * 
             * @param latitude: Latitude location in decimal degrees (500.0 if not available)
             * @param longitude: Longitude location in decimal degrees (500.0 if not available)
             * @param altitude: Altitude location in meters.
             * @param latitudeAccuracy: Latitude location error in meters (1 sigma/standard deviation) -1 if not
             * available.
             * @param longitudeAccuracy: Longitude location error in meters (1 sigma/standard deviation) -1 if not
             * available.
             * @param altitudeAccuracy: Altitude location error in meters (1 sigma/standard deviation) -1 if not
             * available.
             */
            public default void onGpsLocationChanged(double latitude, double longitude, double altitude, int latitudeAccuracy, int longitudeAccuracy, int altitudeAccuracy) {}

            /**
             * Landing state. Only available for fixed wings (which have two landing modes).
             * 
             * @param state:
             */
            public default void onLandingStateChanged(@Nullable PilotingstateLandingstatechangedState state) {}

            /**
             * Drone's air speed changed Expressed in the drone's referential.
             * 
             * @param airspeed: Speed relative to air on x axis (speed is always &gt; 0) (in m/s)
             */
            public default void onAirSpeedChanged(float airspeed) {}

            /**
             * The drone moves or moved to a given location.
             * 
             * @param latitude: Latitude of the location (in degrees) to reach
             * @param longitude: Longitude of the location (in degrees) to reach
             * @param altitude: Altitude above take off point (in m) to reach
             * @param orientationMode:
             * @param heading: Heading (relative to the North in degrees). This value is only used if the orientation
             * mode is HEADING_START or HEADING_DURING
             * @param status:
             */
            public default void onMoveToChanged(double latitude, double longitude, double altitude, @Nullable PilotingstateMovetochangedOrientationMode orientationMode, float heading, @Nullable PilotingstateMovetochangedStatus status) {}

            /**
             * Motion state. If [MotionDetection](#1-6-16) is disabled, motion is steady. This information is only valid
             * when the drone is not flying.
             * 
             * @param state:
             */
            public default void onMotionState(@Nullable PilotingstateMotionstateState state) {}

            /**
             * Piloted POI state.
             * 
             * @param latitude: Latitude of the location (in degrees) to look at. This information is only valid when
             * the state is pending or running.
             * @param longitude: Longitude of the location (in degrees) to look at. This information is only valid when
             * the state is pending or running.
             * @param altitude: Altitude above take off point (in m) to look at. This information is only valid when the
             * state is pending or running.
             * @param status:
             */
            @Deprecated
            public default void onPilotedPOI(double latitude, double longitude, double altitude, @Nullable PilotingstatePilotedpoiStatus status) {}

            /**
             * Battery capacity status to return home.
             * 
             * @param status:
             */
            public default void onReturnHomeBatteryCapacity(@Nullable PilotingstateReturnhomebatterycapacityStatus status) {}

            /**
             * Relative move changed.
             * 
             * @param dxasked: Distance asked to be traveled along the front axis [m]
             * @param dyasked: Distance asked to be traveled along the right axis [m]
             * @param dzasked: Distance asked to be traveled along the down axis [m]
             * @param dpsiasked: Relative angle asked to be applied on heading [rad]
             * @param dx: Actual distance traveled along the front axis [m]. This information is only valid when the
             * state is DONE or CANCELED.
             * @param dy: Actual distance traveled along the right axis [m]. This information is only valid when the
             * state is DONE or CANCELED.
             * @param dz: Actual distance traveled along the down axis [m]. This information is only valid when the
             * state is DONE or CANCELED.
             * @param dpsi: Actual applied angle on heading [rad]. This information is only valid when the state is DONE
             * or CANCELED.
             * @param status:
             */
            public default void onMoveByChanged(float dxasked, float dyasked, float dzasked, float dpsiasked, float dx, float dy, float dz, float dpsi, @Nullable PilotingstateMovebychangedStatus status) {}

            /**
             * Indicate that the drone may have difficulties to maintain a fix position when hovering.
             * 
             * @param noGpsTooDark: 1 if the drone doesn't have a GPS fix and there is not enough light.
             * @param noGpsTooHigh: 1 if the drone doesn't have a GPS fix and is flying too high.
             */
            public default void onHoveringWarning(int noGpsTooDark, int noGpsTooHigh) {}

            /**
             * Forced landing auto trigger information.
             * 
             * @param reason:
             * @param delay: Delay until the landing is automatically triggered by the drone, in seconds. If reason is
             * `none` this information has no meaning.
             */
            public default void onForcedLandingAutoTrigger(@Nullable PilotingstateForcedlandingautotriggerReason reason, long delay) {}

            /**
             * Wind state.
             * 
             * @param state:
             */
            public default void onWindStateChanged(@Nullable PilotingstateWindstatechangedState state) {}

            /**
             * Vibration level.
             * 
             * @param state:
             */
            public default void onVibrationLevelChanged(@Nullable PilotingstateVibrationlevelchangedState state) {}

            /**
             * Drone's altitude above ground changed.
             * 
             * @param altitude: Altitude in meters
             */
            public default void onAltitudeAboveGroundChanged(float altitude) {}

            /**
             * Piloted POI state.
             * 
             * @param latitude: Latitude of the location (in degrees) to look at. This information is only valid when
             * the state is pending or running.
             * @param longitude: Longitude of the location (in degrees) to look at. This information is only valid when
             * the state is pending or running.
             * @param altitude: Altitude above take off point (in m) to look at. This information is only valid when the
             * state is pending or running.
             * @param mode:
             * @param status:
             */
            public default void onPilotedPOIV2(double latitude, double longitude, double altitude, @Nullable PilotingstatePilotedpoiv2Mode mode, @Nullable PilotingstatePilotedpoiv2Status status) {}

            /**
             * Drone's heading locked state.
             * 
             * @param state:
             */
            public default void onHeadingLockedStateChanged(@Nullable PilotingstateHeadinglockedstatechangedState state) {}
        }

        private static void flyingstatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateFlyingstatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateFlyingstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateFlyingstatechangedState value " + state);
            try {
                cb.onFlyingStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.FlyingStateChanged [state: " + state + "]", e);
            }
        }

        private static void alertstatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateAlertstatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateAlertstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateAlertstatechangedState value " + state);
            try {
                cb.onAlertStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.AlertStateChanged [state: " + state + "]", e);
            }
        }

        private static void navigatehomestatechanged(Callback cb, int state, int reason) {
            ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedState value " + state);
            ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedReason enumReason = ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedReason.fromValue(reason);
            if (enumReason == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateNavigatehomestatechangedReason value " + reason);
            try {
                cb.onNavigateHomeStateChanged(enumState, enumReason);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.NavigateHomeStateChanged [state: " + state + ", reason: " + reason + "]", e);
            }
        }

        private static void positionchanged(Callback cb, double latitude, double longitude, double altitude) {
            try {
                cb.onPositionChanged(latitude, longitude, altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.PositionChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + "]", e);
            }
        }

        private static void speedchanged(Callback cb, float speedx, float speedy, float speedz) {
            try {
                cb.onSpeedChanged(speedx, speedy, speedz);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.SpeedChanged [speedX: " + speedx + ", speedY: " + speedy + ", speedZ: " + speedz + "]", e);
            }
        }

        private static void attitudechanged(Callback cb, float roll, float pitch, float yaw) {
            try {
                cb.onAttitudeChanged(roll, pitch, yaw);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.AttitudeChanged [roll: " + roll + ", pitch: " + pitch + ", yaw: " + yaw + "]", e);
            }
        }

        private static void altitudechanged(Callback cb, double altitude) {
            try {
                cb.onAltitudeChanged(altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.AltitudeChanged [altitude: " + altitude + "]", e);
            }
        }

        private static void gpslocationchanged(Callback cb, double latitude, double longitude, double altitude, int latitudeAccuracy, int longitudeAccuracy, int altitudeAccuracy) {
            try {
                cb.onGpsLocationChanged(latitude, longitude, altitude, latitudeAccuracy, longitudeAccuracy, altitudeAccuracy);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.GpsLocationChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", latitude_accuracy: " + latitudeAccuracy + ", longitude_accuracy: " + longitudeAccuracy + ", altitude_accuracy: " + altitudeAccuracy + "]", e);
            }
        }

        private static void landingstatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateLandingstatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateLandingstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateLandingstatechangedState value " + state);
            try {
                cb.onLandingStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.LandingStateChanged [state: " + state + "]", e);
            }
        }

        private static void airspeedchanged(Callback cb, float airspeed) {
            try {
                cb.onAirSpeedChanged(airspeed);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.AirSpeedChanged [airSpeed: " + airspeed + "]", e);
            }
        }

        private static void movetochanged(Callback cb, double latitude, double longitude, double altitude, int orientationMode, float heading, int status) {
            ArsdkFeatureArdrone3.PilotingstateMovetochangedOrientationMode enumOrientationmode = ArsdkFeatureArdrone3.PilotingstateMovetochangedOrientationMode.fromValue(orientationMode);
            if (enumOrientationmode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateMovetochangedOrientationMode value " + orientationMode);
            ArsdkFeatureArdrone3.PilotingstateMovetochangedStatus enumStatus = ArsdkFeatureArdrone3.PilotingstateMovetochangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateMovetochangedStatus value " + status);
            try {
                cb.onMoveToChanged(latitude, longitude, altitude, enumOrientationmode, heading, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.moveToChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", orientation_mode: " + orientationMode + ", heading: " + heading + ", status: " + status + "]", e);
            }
        }

        private static void motionstate(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateMotionstateState enumState = ArsdkFeatureArdrone3.PilotingstateMotionstateState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateMotionstateState value " + state);
            try {
                cb.onMotionState(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.MotionState [state: " + state + "]", e);
            }
        }

        private static void pilotedpoi(Callback cb, double latitude, double longitude, double altitude, int status) {
            ArsdkFeatureArdrone3.PilotingstatePilotedpoiStatus enumStatus = ArsdkFeatureArdrone3.PilotingstatePilotedpoiStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstatePilotedpoiStatus value " + status);
            try {
                cb.onPilotedPOI(latitude, longitude, altitude, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.PilotedPOI [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", status: " + status + "]", e);
            }
        }

        private static void returnhomebatterycapacity(Callback cb, int status) {
            ArsdkFeatureArdrone3.PilotingstateReturnhomebatterycapacityStatus enumStatus = ArsdkFeatureArdrone3.PilotingstateReturnhomebatterycapacityStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateReturnhomebatterycapacityStatus value " + status);
            try {
                cb.onReturnHomeBatteryCapacity(enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.ReturnHomeBatteryCapacity [status: " + status + "]", e);
            }
        }

        private static void movebychanged(Callback cb, float dxasked, float dyasked, float dzasked, float dpsiasked, float dx, float dy, float dz, float dpsi, int status) {
            ArsdkFeatureArdrone3.PilotingstateMovebychangedStatus enumStatus = ArsdkFeatureArdrone3.PilotingstateMovebychangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateMovebychangedStatus value " + status);
            try {
                cb.onMoveByChanged(dxasked, dyasked, dzasked, dpsiasked, dx, dy, dz, dpsi, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.moveByChanged [dXAsked: " + dxasked + ", dYAsked: " + dyasked + ", dZAsked: " + dzasked + ", dPsiAsked: " + dpsiasked + ", dX: " + dx + ", dY: " + dy + ", dZ: " + dz + ", dPsi: " + dpsi + ", status: " + status + "]", e);
            }
        }

        private static void hoveringwarning(Callback cb, int noGpsTooDark, int noGpsTooHigh) {
            try {
                cb.onHoveringWarning(noGpsTooDark, noGpsTooHigh);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.HoveringWarning [no_gps_too_dark: " + noGpsTooDark + ", no_gps_too_high: " + noGpsTooHigh + "]", e);
            }
        }

        private static void forcedlandingautotrigger(Callback cb, int reason, long delay) {
            ArsdkFeatureArdrone3.PilotingstateForcedlandingautotriggerReason enumReason = ArsdkFeatureArdrone3.PilotingstateForcedlandingautotriggerReason.fromValue(reason);
            if (enumReason == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateForcedlandingautotriggerReason value " + reason);
            try {
                cb.onForcedLandingAutoTrigger(enumReason, delay);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.ForcedLandingAutoTrigger [reason: " + reason + ", delay: " + delay + "]", e);
            }
        }

        private static void windstatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateWindstatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateWindstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateWindstatechangedState value " + state);
            try {
                cb.onWindStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.WindStateChanged [state: " + state + "]", e);
            }
        }

        private static void vibrationlevelchanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateVibrationlevelchangedState enumState = ArsdkFeatureArdrone3.PilotingstateVibrationlevelchangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateVibrationlevelchangedState value " + state);
            try {
                cb.onVibrationLevelChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.VibrationLevelChanged [state: " + state + "]", e);
            }
        }

        private static void altitudeabovegroundchanged(Callback cb, float altitude) {
            try {
                cb.onAltitudeAboveGroundChanged(altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.AltitudeAboveGroundChanged [altitude: " + altitude + "]", e);
            }
        }

        private static void pilotedpoiv2(Callback cb, double latitude, double longitude, double altitude, int mode, int status) {
            ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Mode enumMode = ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Mode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Mode value " + mode);
            ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Status enumStatus = ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Status.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstatePilotedpoiv2Status value " + status);
            try {
                cb.onPilotedPOIV2(latitude, longitude, altitude, enumMode, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.PilotedPOIV2 [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", mode: " + mode + ", status: " + status + "]", e);
            }
        }

        private static void headinglockedstatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.PilotingstateHeadinglockedstatechangedState enumState = ArsdkFeatureArdrone3.PilotingstateHeadinglockedstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingstateHeadinglockedstatechangedState value " + state);
            try {
                cb.onHeadingLockedStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingState.HeadingLockedStateChanged [state: " + state + "]", e);
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
        public static final int UID = 0x0105;

        /**
         * Make a flip.
         * 
         * @param direction:
         */
        public static ArsdkCommand encodeFlip(@NonNull AnimationsFlipDirection direction) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeFlip(cmd.getNativePtr(), direction.value);
            return cmd;
        }

        private static native int nativeEncodeFlip(long nativeCmd, int direction);
    }

    /**
     * Piloting Settings state from product 
     */
    public static class PilotingSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0106;

        /** Uid of MaxAltitudeChanged event. */;
        public static final int MAXALTITUDECHANGED_UID = 0x0000;

        /** Uid of MaxTiltChanged event. */;
        public static final int MAXTILTCHANGED_UID = 0x0001;

        /** Uid of AbsolutControlChanged event. */;
        public static final int ABSOLUTCONTROLCHANGED_UID = 0x0002;

        /** Uid of MaxDistanceChanged event. */;
        public static final int MAXDISTANCECHANGED_UID = 0x0003;

        /** Uid of NoFlyOverMaxDistanceChanged event. */;
        public static final int NOFLYOVERMAXDISTANCECHANGED_UID = 0x0004;

        /** Uid of BankedTurnChanged event. */;
        public static final int BANKEDTURNCHANGED_UID = 0x000A;

        /** Uid of MinAltitudeChanged event. */;
        public static final int MINALTITUDECHANGED_UID = 0x000B;

        /** Uid of CirclingDirectionChanged event. */;
        public static final int CIRCLINGDIRECTIONCHANGED_UID = 0x000C;

        /** Uid of CirclingRadiusChanged event. */;
        public static final int CIRCLINGRADIUSCHANGED_UID = 0x000D;

        /** Uid of CirclingAltitudeChanged event. */;
        public static final int CIRCLINGALTITUDECHANGED_UID = 0x000E;

        /** Uid of PitchModeChanged event. */;
        public static final int PITCHMODECHANGED_UID = 0x000F;

        /** Uid of MotionDetection event. */;
        public static final int MOTIONDETECTION_UID = 0x0010;

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
             * Max altitude. The drone will not fly higher than this altitude (above take off point).
             * 
             * @param current: Current altitude max
             * @param min: Range min of altitude
             * @param max: Range max of altitude
             */
            public default void onMaxAltitudeChanged(float current, float min, float max) {}

            /**
             * Max pitch/roll. The drone will not fly higher than this altitude (above take off point).
             * 
             * @param current: Current max tilt
             * @param min: Range min of tilt
             * @param max: Range max of tilt
             */
            public default void onMaxTiltChanged(float current, float min, float max) {}

            /**
             * Absolut control.
             * 
             * @param on: 1 if enabled, 0 if disabled
             */
            @Deprecated
            public default void onAbsolutControlChanged(int on) {}

            /**
             * Max distance.
             * 
             * @param current: Current max distance in meter
             * @param min: Minimal possible max distance
             * @param max: Maximal possible max distance
             */
            public default void onMaxDistanceChanged(float current, float min, float max) {}

            /**
             * Geofencing. If set, the drone won't fly over the [MaxDistance](#1-6-3).
             * 
             * @param shouldnotflyover: 1 if the drone won't fly further than max distance, 0 if no limitation on the
             * drone will be done
             */
            public default void onNoFlyOverMaxDistanceChanged(int shouldnotflyover) {}

            /**
             * Banked Turn mode. If banked turn mode is enabled, the drone will use yaw values from the piloting command
             * to infer with roll and pitch on the drone when its horizontal speed is not null.
             * 
             * @param state: 1 if enabled, 0 if disabled
             */
            public default void onBankedTurnChanged(int state) {}

            /**
             * Min altitude. Only sent by fixed wings.
             * 
             * @param current: Current altitude min
             * @param min: Range min of altitude min
             * @param max: Range max of altitude min
             */
            public default void onMinAltitudeChanged(float current, float min, float max) {}

            /**
             * Circling direction. Only sent by fixed wings.
             * 
             * @param value:
             */
            public default void onCirclingDirectionChanged(@Nullable PilotingsettingsstateCirclingdirectionchangedValue value) {}

            /**
             * Circling radius. Only sent by fixed wings.
             * 
             * @param current: The current circling radius in meter
             * @param min: Range min of circling radius in meter
             * @param max: Range max of circling radius in meter
             */
            @Deprecated
            public default void onCirclingRadiusChanged(int current, int min, int max) {}

            /**
             * Circling altitude. Bounds will be automatically adjusted according to the [MaxAltitude](#1-6-0). Only
             * sent by fixed wings.
             * 
             * @param current: The current circling altitude in meter
             * @param min: Range min of circling altitude in meter
             * @param max: Range max of circling altitude in meter
             */
            public default void onCirclingAltitudeChanged(int current, int min, int max) {}

            /**
             * Pitch mode.
             * 
             * @param value:
             */
            public default void onPitchModeChanged(@Nullable PilotingsettingsstatePitchmodechangedValue value) {}

            /**
             * State of the motion detection.
             * 
             * @param enabled: 1 if motion detection is enabled, 0 otherwise.
             */
            public default void onMotionDetection(int enabled) {}
        }

        private static void maxaltitudechanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxAltitudeChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.MaxAltitudeChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void maxtiltchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxTiltChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.MaxTiltChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void absolutcontrolchanged(Callback cb, int on) {
            try {
                cb.onAbsolutControlChanged(on);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.AbsolutControlChanged [on: " + on + "]", e);
            }
        }

        private static void maxdistancechanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxDistanceChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.MaxDistanceChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void noflyovermaxdistancechanged(Callback cb, int shouldnotflyover) {
            try {
                cb.onNoFlyOverMaxDistanceChanged(shouldnotflyover);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.NoFlyOverMaxDistanceChanged [shouldNotFlyOver: " + shouldnotflyover + "]", e);
            }
        }

        private static void bankedturnchanged(Callback cb, int state) {
            try {
                cb.onBankedTurnChanged(state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.BankedTurnChanged [state: " + state + "]", e);
            }
        }

        private static void minaltitudechanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMinAltitudeChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.MinAltitudeChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void circlingdirectionchanged(Callback cb, int value) {
            ArsdkFeatureArdrone3.PilotingsettingsstateCirclingdirectionchangedValue enumValue = ArsdkFeatureArdrone3.PilotingsettingsstateCirclingdirectionchangedValue.fromValue(value);
            if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingsettingsstateCirclingdirectionchangedValue value " + value);
            try {
                cb.onCirclingDirectionChanged(enumValue);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.CirclingDirectionChanged [value: " + value + "]", e);
            }
        }

        private static void circlingradiuschanged(Callback cb, int current, int min, int max) {
            try {
                cb.onCirclingRadiusChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.CirclingRadiusChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void circlingaltitudechanged(Callback cb, int current, int min, int max) {
            try {
                cb.onCirclingAltitudeChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.CirclingAltitudeChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void pitchmodechanged(Callback cb, int value) {
            ArsdkFeatureArdrone3.PilotingsettingsstatePitchmodechangedValue enumValue = ArsdkFeatureArdrone3.PilotingsettingsstatePitchmodechangedValue.fromValue(value);
            if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingsettingsstatePitchmodechangedValue value " + value);
            try {
                cb.onPitchModeChanged(enumValue);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.PitchModeChanged [value: " + value + "]", e);
            }
        }

        private static void motiondetection(Callback cb, int enabled) {
            try {
                cb.onMotionDetection(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingSettingsState.MotionDetection [enabled: " + enabled + "]", e);
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
        public static final int UID = 0x0107;

        /**
         * Take a picture.
         * 
         * @param massStorageId: Mass storage id to take picture
         */
        @Deprecated
        public static ArsdkCommand encodePicture(int massStorageId) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePicture(cmd.getNativePtr(), massStorageId);
            return cmd;
        }

        /**
         * Record a video.
         * 
         * @param record:
         * @param massStorageId: Mass storage id to record
         */
        @Deprecated
        public static ArsdkCommand encodeVideo(@NonNull MediarecordVideoRecord record, int massStorageId) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideo(cmd.getNativePtr(), record.value, massStorageId);
            return cmd;
        }

        /**
         * Take a picture. The type of picture taken is related to the picture setting. You can set the picture format
         * by sending the command [SetPictureFormat](#1-19-0). You can also get the current picture format with
         * [PictureFormat](#1-20-0). Please note that the time required to take the picture is highly related to this
         * format.  You can check if the picture taking is available with [PictureState](#1-8-2). Also, please note that
         * if your picture format is different from snapshot, picture taking will stop video recording (it will restart
         * after that the picture has been taken).
         */
        public static ArsdkCommand encodePictureV2() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePictureV2(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Record a video (or start timelapse). You can check if the video recording is available with
         * [VideoState](#1-8-3). This command can start a video (obvious huh?), but also a timelapse if the timelapse
         * mode is set. You can check if the timelapse mode is set with the event [TimelapseMode](#1-20-4). Also, please
         * note that if your picture format is different from snapshot, picture taking will stop video recording (it
         * will restart after the picture has been taken).
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
        public static final int UID = 0x0108;

        /** Uid of PictureStateChanged event. */;
        public static final int PICTURESTATECHANGED_UID = 0x0000;

        /** Uid of VideoStateChanged event. */;
        public static final int VIDEOSTATECHANGED_UID = 0x0001;

        /** Uid of PictureStateChangedV2 event. */;
        public static final int PICTURESTATECHANGEDV2_UID = 0x0002;

        /** Uid of VideoStateChangedV2 event. */;
        public static final int VIDEOSTATECHANGEDV2_UID = 0x0003;

        /** Uid of VideoResolutionState event. */;
        public static final int VIDEORESOLUTIONSTATE_UID = 0x0004;

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
             * Picture state.
             * 
             * @param state: 1 if picture has been taken, 0 otherwise
             * @param massStorageId: Mass storage id where the picture was recorded
             */
            @Deprecated
            public default void onPictureStateChanged(int state, int massStorageId) {}

            /**
             * Picture record state.
             * 
             * @param state:
             * @param massStorageId: Mass storage id where the video was recorded
             */
            @Deprecated
            public default void onVideoStateChanged(@Nullable MediarecordstateVideostatechangedState state, int massStorageId) {}

            /**
             * Picture state.
             * 
             * @param state:
             * @param error:
             */
            public default void onPictureStateChangedV2(@Nullable MediarecordstatePicturestatechangedv2State state, @Nullable MediarecordstatePicturestatechangedv2Error error) {}

            /**
             * Video record state.
             * 
             * @param state:
             * @param error:
             */
            public default void onVideoStateChangedV2(@Nullable MediarecordstateVideostatechangedv2State state, @Nullable MediarecordstateVideostatechangedv2Error error) {}

            /**
             * Video resolution. Informs about streaming and recording video resolutions. Note that this is only an
             * indication about what the resolution should be. To know the real resolution, you should get it from the
             * frame.
             * 
             * @param streaming:
             * @param recording:
             */
            @Deprecated
            public default void onVideoResolutionState(@Nullable MediarecordstateVideoresolutionstateStreaming streaming, @Nullable MediarecordstateVideoresolutionstateRecording recording) {}
        }

        private static void picturestatechanged(Callback cb, int state, int massStorageId) {
            try {
                cb.onPictureStateChanged(state, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordState.PictureStateChanged [state: " + state + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void videostatechanged(Callback cb, int state, int massStorageId) {
            ArsdkFeatureArdrone3.MediarecordstateVideostatechangedState enumState = ArsdkFeatureArdrone3.MediarecordstateVideostatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstateVideostatechangedState value " + state);
            try {
                cb.onVideoStateChanged(enumState, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordState.VideoStateChanged [state: " + state + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void picturestatechangedv2(Callback cb, int state, int error) {
            ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2State enumState = ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2State value " + state);
            ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2Error enumError = ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstatePicturestatechangedv2Error value " + error);
            try {
                cb.onPictureStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordState.PictureStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }

        private static void videostatechangedv2(Callback cb, int state, int error) {
            ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2State enumState = ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2State value " + state);
            ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2Error enumError = ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstateVideostatechangedv2Error value " + error);
            try {
                cb.onVideoStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordState.VideoStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }

        private static void videoresolutionstate(Callback cb, int streaming, int recording) {
            ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateStreaming enumStreaming = ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateStreaming.fromValue(streaming);
            if (enumStreaming == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateStreaming value " + streaming);
            ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateRecording enumRecording = ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateRecording.fromValue(recording);
            if (enumRecording == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediarecordstateVideoresolutionstateRecording value " + recording);
            try {
                cb.onVideoResolutionState(enumStreaming, enumRecording);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaRecordState.VideoResolutionState [streaming: " + streaming + ", recording: " + recording + "]", e);
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
        public static final int UID = 0x0109;

        /**
         * Select or auto-select channel of choosen band.
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

        /**
         * Set wifi security type. The security will be changed on the next restart
         * 
         * @param type:
         * @param key: The key to secure the network (empty if type is open)
         * @param keytype:
         */
        public static ArsdkCommand encodeWifiSecurity(@NonNull NetworksettingsWifisecurityType type, String key, @NonNull NetworksettingsWifisecurityKeytype keytype) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiSecurity(cmd.getNativePtr(), type.value, key, keytype.value);
            return cmd;
        }

        private static native int nativeEncodeWifiSelection(long nativeCmd, int type, int band, int channel);
        private static native int nativeEncodeWifiSecurity(long nativeCmd, int type, String key, int keyType);
    }

    /**
     * Network settings state from product 
     */
    public static class NetworkSettingsState {

        /** Feature uid. */
        public static final int UID = 0x010A;

        /** Uid of WifiSelectionChanged event. */;
        public static final int WIFISELECTIONCHANGED_UID = 0x0000;

        /** Uid of wifiSecurityChanged event. */;
        public static final int WIFISECURITYCHANGED_UID = 0x0001;

        /** Uid of wifiSecurity event. */;
        public static final int WIFISECURITY_UID = 0x0002;

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
             * Wifi selection.
             * 
             * @param type:
             * @param band:
             * @param channel: The channel (depends of the band)
             */
            public default void onWifiSelectionChanged(@Nullable NetworksettingsstateWifiselectionchangedType type, @Nullable NetworksettingsstateWifiselectionchangedBand band, int channel) {}

            /**
             * Wifi security type.
             * 
             * @param type:
             */
            @Deprecated
            public default void onWifiSecurityChanged(@Nullable NetworksettingsstateWifisecuritychangedType type) {}

            /**
             * Wifi security type.
             * 
             * @param type:
             * @param key: The key used to secure the network (empty if type is open)
             * @param keytype:
             */
            public default void onWifiSecurity(@Nullable NetworksettingsstateWifisecurityType type, String key, @Nullable NetworksettingsstateWifisecurityKeytype keytype) {}
        }

        private static void wifiselectionchanged(Callback cb, int type, int band, int channel) {
            ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedType enumType = ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedType value " + type);
            ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedBand enumBand = ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworksettingsstateWifiselectionchangedBand value " + band);
            try {
                cb.onWifiSelectionChanged(enumType, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkSettingsState.WifiSelectionChanged [type: " + type + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }

        private static void wifisecuritychanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.NetworksettingsstateWifisecuritychangedType enumType = ArsdkFeatureArdrone3.NetworksettingsstateWifisecuritychangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworksettingsstateWifisecuritychangedType value " + type);
            try {
                cb.onWifiSecurityChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkSettingsState.wifiSecurityChanged [type: " + type + "]", e);
            }
        }

        private static void wifisecurity(Callback cb, int type, String key, int keytype) {
            ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityType enumType = ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityType value " + type);
            ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityKeytype enumKeytype = ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityKeytype.fromValue(keytype);
            if (enumKeytype == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworksettingsstateWifisecurityKeytype value " + keytype);
            try {
                cb.onWifiSecurity(enumType, key, enumKeytype);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkSettingsState.wifiSecurity [type: " + type + ", key: " + key + ", keyType: " + keytype + "]", e);
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
        public static final int UID = 0x010B;

        /**
         * Set max vertical speed.
         * 
         * @param current: Current max vertical speed in m/s
         */
        public static ArsdkCommand encodeMaxVerticalSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxVerticalSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set max rotation speed.
         * 
         * @param current: Current max yaw rotation speed in degree/s
         */
        public static ArsdkCommand encodeMaxRotationSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxRotationSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set the presence of hull protection.
         * 
         * @param present: 1 if present, 0 if not present
         */
        public static ArsdkCommand encodeHullProtection(int present) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeHullProtection(cmd.getNativePtr(), present);
            return cmd;
        }

        /**
         * Set outdoor mode.
         * 
         * @param outdoor: 1 if outdoor flight, 0 if indoor flight
         */
        @Deprecated
        public static ArsdkCommand encodeOutdoor(int outdoor) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeOutdoor(cmd.getNativePtr(), outdoor);
            return cmd;
        }

        /**
         * Set max pitch/roll rotation speed.
         * 
         * @param current: Current max pitch/roll rotation speed in degree/s
         */
        public static ArsdkCommand encodeMaxPitchRollRotationSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxPitchRollRotationSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        private static native int nativeEncodeMaxVerticalSpeed(long nativeCmd, float current);
        private static native int nativeEncodeMaxRotationSpeed(long nativeCmd, float current);
        private static native int nativeEncodeHullProtection(long nativeCmd, int present);
        private static native int nativeEncodeOutdoor(long nativeCmd, int outdoor);
        private static native int nativeEncodeMaxPitchRollRotationSpeed(long nativeCmd, float current);
    }

    /**
     * Speed Settings state from product 
     */
    public static class SpeedSettingsState {

        /** Feature uid. */
        public static final int UID = 0x010C;

        /** Uid of MaxVerticalSpeedChanged event. */;
        public static final int MAXVERTICALSPEEDCHANGED_UID = 0x0000;

        /** Uid of MaxRotationSpeedChanged event. */;
        public static final int MAXROTATIONSPEEDCHANGED_UID = 0x0001;

        /** Uid of HullProtectionChanged event. */;
        public static final int HULLPROTECTIONCHANGED_UID = 0x0002;

        /** Uid of OutdoorChanged event. */;
        public static final int OUTDOORCHANGED_UID = 0x0003;

        /** Uid of MaxPitchRollRotationSpeedChanged event. */;
        public static final int MAXPITCHROLLROTATIONSPEEDCHANGED_UID = 0x0004;

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
             * Max vertical speed.
             * 
             * @param current: Current max vertical speed in m/s
             * @param min: Range min of vertical speed
             * @param max: Range max of vertical speed
             */
            public default void onMaxVerticalSpeedChanged(float current, float min, float max) {}

            /**
             * Max rotation speed.
             * 
             * @param current: Current max yaw rotation speed in degree/s
             * @param min: Range min of yaw rotation speed
             * @param max: Range max of yaw rotation speed
             */
            public default void onMaxRotationSpeedChanged(float current, float min, float max) {}

            /**
             * Presence of hull protection.
             * 
             * @param present: 1 if present, 0 if not present
             */
            public default void onHullProtectionChanged(int present) {}

            /**
             * Outdoor mode.
             * 
             * @param outdoor: 1 if outdoor flight, 0 if indoor flight
             */
            @Deprecated
            public default void onOutdoorChanged(int outdoor) {}

            /**
             * Max pitch/roll rotation speed.
             * 
             * @param current: Current max pitch/roll rotation speed in degree/s
             * @param min: Range min of pitch/roll rotation speed
             * @param max: Range max of pitch/roll rotation speed
             */
            public default void onMaxPitchRollRotationSpeedChanged(float current, float min, float max) {}
        }

        private static void maxverticalspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxVerticalSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SpeedSettingsState.MaxVerticalSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void maxrotationspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxRotationSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SpeedSettingsState.MaxRotationSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void hullprotectionchanged(Callback cb, int present) {
            try {
                cb.onHullProtectionChanged(present);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SpeedSettingsState.HullProtectionChanged [present: " + present + "]", e);
            }
        }

        private static void outdoorchanged(Callback cb, int outdoor) {
            try {
                cb.onOutdoorChanged(outdoor);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SpeedSettingsState.OutdoorChanged [outdoor: " + outdoor + "]", e);
            }
        }

        private static void maxpitchrollrotationspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxPitchRollRotationSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SpeedSettingsState.MaxPitchRollRotationSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
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
        public static final int UID = 0x010D;

        /**
         * Scan wifi network to get a list of all networks found by the drone
         * 
         * @param band:
         */
        public static ArsdkCommand encodeWifiScan(@NonNull NetworkWifiscanBand band) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiScan(cmd.getNativePtr(), band.value);
            return cmd;
        }

        /**
         * Ask for available wifi channels. The list of available Wifi channels is related to the country of the drone.
         * You can get this country from the event [CountryChanged](#0-3-6).
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
        public static final int UID = 0x010E;

        /** Uid of WifiScanListChanged event. */;
        public static final int WIFISCANLISTCHANGED_UID = 0x0000;

        /** Uid of AllWifiScanChanged event. */;
        public static final int ALLWIFISCANCHANGED_UID = 0x0001;

        /** Uid of WifiAuthChannelListChanged event. */;
        public static final int WIFIAUTHCHANNELLISTCHANGED_UID = 0x0002;

        /** Uid of AllWifiAuthChannelChanged event. */;
        public static final int ALLWIFIAUTHCHANNELCHANGED_UID = 0x0003;

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
             * Wifi scan results. Please note that the list is not complete until you receive the event
             * [WifiScanEnded](#1-14-1).
             * 
             * @param ssid: SSID of the AP
             * @param rssi: RSSI of the AP in dbm (negative value)
             * @param band:
             * @param channel: Channel of the AP
             */
            public default void onWifiScanListChanged(String ssid, int rssi, @Nullable NetworkstateWifiscanlistchangedBand band, int channel) {}

            /**
             * Wifi scan ended. When receiving this event, the list of [WifiScanResults](#1-14-0) is complete.
             */
            public default void onAllWifiScanChanged() {}

            /**
             * Available wifi channels. Please note that the list is not complete until you receive the event
             * [AvailableWifiChannelsCompleted](#1-14-3).
             * 
             * @param band:
             * @param channel: The authorized channel.
             * @param inOrOut: Bit 0 is 1 if channel is authorized outside (0 otherwise) ; Bit 1 is 1 if channel is
             * authorized inside (0 otherwise)
             */
            public default void onWifiAuthChannelListChanged(@Nullable NetworkstateWifiauthchannellistchangedBand band, int channel, int inOrOut) {}

            /**
             * Available wifi channels completed. When receiving this event, the list of
             * [AvailableWifiChannels](#1-14-2) is complete.
             */
            public default void onAllWifiAuthChannelChanged() {}
        }

        private static void wifiscanlistchanged(Callback cb, String ssid, int rssi, int band, int channel) {
            ArsdkFeatureArdrone3.NetworkstateWifiscanlistchangedBand enumBand = ArsdkFeatureArdrone3.NetworkstateWifiscanlistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworkstateWifiscanlistchangedBand value " + band);
            try {
                cb.onWifiScanListChanged(ssid, rssi, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkState.WifiScanListChanged [ssid: " + ssid + ", rssi: " + rssi + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }

        private static void allwifiscanchanged(Callback cb) {
            try {
                cb.onAllWifiScanChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkState.AllWifiScanChanged", e);
            }
        }

        private static void wifiauthchannellistchanged(Callback cb, int band, int channel, int inOrOut) {
            ArsdkFeatureArdrone3.NetworkstateWifiauthchannellistchangedBand enumBand = ArsdkFeatureArdrone3.NetworkstateWifiauthchannellistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.NetworkstateWifiauthchannellistchangedBand value " + band);
            try {
                cb.onWifiAuthChannelListChanged(enumBand, channel, inOrOut);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkState.WifiAuthChannelListChanged [band: " + band + ", channel: " + channel + ", in_or_out: " + inOrOut + "]", e);
            }
        }

        private static void allwifiauthchannelchanged(Callback cb) {
            try {
                cb.onAllWifiAuthChannelChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.NetworkState.AllWifiAuthChannelChanged", e);
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
        public static final int UID = 0x0110;

        /** Uid of ProductMotorVersionListChanged event. */;
        public static final int PRODUCTMOTORVERSIONLISTCHANGED_UID = 0x0000;

        /** Uid of ProductGPSVersionChanged event. */;
        public static final int PRODUCTGPSVERSIONCHANGED_UID = 0x0001;

        /** Uid of MotorErrorStateChanged event. */;
        public static final int MOTORERRORSTATECHANGED_UID = 0x0002;

        /** Uid of MotorSoftwareVersionChanged event. */;
        public static final int MOTORSOFTWAREVERSIONCHANGED_UID = 0x0003;

        /** Uid of MotorFlightsStatusChanged event. */;
        public static final int MOTORFLIGHTSSTATUSCHANGED_UID = 0x0004;

        /** Uid of MotorErrorLastErrorChanged event. */;
        public static final int MOTORERRORLASTERRORCHANGED_UID = 0x0005;

        /** Uid of P7ID event. */;
        public static final int P7ID_UID = 0x0006;

        /** Uid of CPUID event. */;
        public static final int CPUID_UID = 0x0007;

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
             * Motor version.
             * 
             * @param motorNumber: Product Motor number
             * @param type: Product Motor type
             * @param software: Product Motors software version
             * @param hardware: Product Motors hardware version
             */
            @Deprecated
            public default void onProductMotorVersionListChanged(int motorNumber, String type, String software, String hardware) {}

            /**
             * GPS version.
             * 
             * @param software: Product GPS software version
             * @param hardware: Product GPS hardware version
             */
            public default void onProductGPSVersionChanged(String software, String hardware) {}

            /**
             * Motor error. This event is sent back to *noError* as soon as the motor error disappear. To get the last
             * motor error, see [LastMotorError](#1-16-5)
             * 
             * @param motorids: Bit field for concerned motor. If bit 0 = 1, motor 1 is affected by this error. Same
             * with bit 1, 2 and 3. Motor 1: front left Motor 2: front right Motor 3: back right Motor 4: back left
             * @param motorerror:
             */
            public default void onMotorErrorStateChanged(int motorids, @Nullable SettingsstateMotorerrorstatechangedMotorerror motorerror) {}

            /**
             * Motor version.
             * 
             * @param version: name of the version : dot separated fields (major version - minor version - firmware type
             * - nb motors handled). Firmware types : Release, Debug, Alpha, Test-bench
             */
            @Deprecated
            public default void onMotorSoftwareVersionChanged(String version) {}

            /**
             * Motor flight status.
             * 
             * @param nbflights: total number of flights
             * @param lastflightduration: Duration of the last flight (in seconds)
             * @param totalflightduration: Duration of all flights (in seconds)
             */
            public default void onMotorFlightsStatusChanged(int nbflights, int lastflightduration, long totalflightduration) {}

            /**
             * Last motor error. This is a reminder of the last error. To know if a motor error is currently happening,
             * see [MotorError](#1-16-2).
             * 
             * @param motorerror:
             */
            public default void onMotorErrorLastErrorChanged(@Nullable SettingsstateMotorerrorlasterrorchangedMotorerror motorerror) {}

            /**
             * P7ID.
             * 
             * @param serialid: Product P7ID
             */
            @Deprecated
            public default void onP7ID(String serialid) {}

            /**
             * Product main cpu id
             * 
             * @param id: Product main cpu id
             */
            public default void onCPUID(String id) {}
        }

        private static void productmotorversionlistchanged(Callback cb, int motorNumber, String type, String software, String hardware) {
            try {
                cb.onProductMotorVersionListChanged(motorNumber, type, software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.ProductMotorVersionListChanged [motor_number: " + motorNumber + ", type: " + type + ", software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void productgpsversionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onProductGPSVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.ProductGPSVersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void motorerrorstatechanged(Callback cb, int motorids, int motorerror) {
            ArsdkFeatureArdrone3.SettingsstateMotorerrorstatechangedMotorerror enumMotorerror = ArsdkFeatureArdrone3.SettingsstateMotorerrorstatechangedMotorerror.fromValue(motorerror);
            if (enumMotorerror == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.SettingsstateMotorerrorstatechangedMotorerror value " + motorerror);
            try {
                cb.onMotorErrorStateChanged(motorids, enumMotorerror);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.MotorErrorStateChanged [motorIds: " + motorids + ", motorError: " + motorerror + "]", e);
            }
        }

        private static void motorsoftwareversionchanged(Callback cb, String version) {
            try {
                cb.onMotorSoftwareVersionChanged(version);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.MotorSoftwareVersionChanged [version: " + version + "]", e);
            }
        }

        private static void motorflightsstatuschanged(Callback cb, int nbflights, int lastflightduration, long totalflightduration) {
            try {
                cb.onMotorFlightsStatusChanged(nbflights, lastflightduration, totalflightduration);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.MotorFlightsStatusChanged [nbFlights: " + nbflights + ", lastFlightDuration: " + lastflightduration + ", totalFlightDuration: " + totalflightduration + "]", e);
            }
        }

        private static void motorerrorlasterrorchanged(Callback cb, int motorerror) {
            ArsdkFeatureArdrone3.SettingsstateMotorerrorlasterrorchangedMotorerror enumMotorerror = ArsdkFeatureArdrone3.SettingsstateMotorerrorlasterrorchangedMotorerror.fromValue(motorerror);
            if (enumMotorerror == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.SettingsstateMotorerrorlasterrorchangedMotorerror value " + motorerror);
            try {
                cb.onMotorErrorLastErrorChanged(enumMotorerror);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.MotorErrorLastErrorChanged [motorError: " + motorerror + "]", e);
            }
        }

        private static void p7id(Callback cb, String serialid) {
            try {
                cb.onP7ID(serialid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.P7ID [serialID: " + serialid + "]", e);
            }
        }

        private static void cpuid(Callback cb, String id) {
            try {
                cb.onCPUID(id);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SettingsState.CPUID [id: " + id + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Photo settings chosen by the user 
     */
    public static class PictureSettings {

        /** Feature uid. */
        public static final int UID = 0x0113;

        /**
         * Set picture format. Please note that the time required to take the picture is highly related to this format.
         * Also, please note that if your picture format is different from snapshot, picture taking will stop video
         * recording (it will restart after the picture has been taken).
         * 
         * @param type:
         */
        public static ArsdkCommand encodePictureFormatSelection(@NonNull PicturesettingsPictureformatselectionType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePictureFormatSelection(cmd.getNativePtr(), type.value);
            return cmd;
        }

        /**
         * Set White Balance mode.
         * 
         * @param type:
         */
        public static ArsdkCommand encodeAutoWhiteBalanceSelection(@NonNull PicturesettingsAutowhitebalanceselectionType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutoWhiteBalanceSelection(cmd.getNativePtr(), type.value);
            return cmd;
        }

        /**
         * Set image exposure.
         * 
         * @param value: Exposition value (bounds given by ExpositionChanged arg min and max, by default [-3:3])
         */
        public static ArsdkCommand encodeExpositionSelection(float value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeExpositionSelection(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Set image saturation.
         * 
         * @param value: Saturation value (bounds given by SaturationChanged arg min and max, by default [-100:100])
         */
        public static ArsdkCommand encodeSaturationSelection(float value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSaturationSelection(cmd.getNativePtr(), value);
            return cmd;
        }

        /**
         * Set timelapse mode. If timelapse mode is set, instead of taking a video, the drone will take picture
         * regularly. Watch out, this command only configure the timelapse mode. Once it is configured, you can
         * start/stop the timelapse with the [RecordVideo](#1-7-3) command.
         * 
         * @param enabled: 1 if timelapse is enabled, 0 otherwise
         * @param interval: interval in seconds for taking pictures
         */
        public static ArsdkCommand encodeTimelapseSelection(int enabled, float interval) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeTimelapseSelection(cmd.getNativePtr(), enabled, interval);
            return cmd;
        }

        /**
         * Set video autorecord mode. If autorecord is set, video record will be automatically started when the drone
         * takes off and stopped slightly after landing.
         * 
         * @param enabled: 1 if video autorecord is enabled, 0 otherwise
         * @param massStorageId: Mass storage id to take video
         */
        public static ArsdkCommand encodeVideoAutorecordSelection(int enabled, int massStorageId) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoAutorecordSelection(cmd.getNativePtr(), enabled, massStorageId);
            return cmd;
        }

        /**
         * Set video stabilization mode.
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeVideoStabilizationMode(@NonNull PicturesettingsVideostabilizationmodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoStabilizationMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        /**
         * Set video recording mode.
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeVideoRecordingMode(@NonNull PicturesettingsVideorecordingmodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoRecordingMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        /**
         * Set video framerate.
         * 
         * @param framerate:
         */
        public static ArsdkCommand encodeVideoFramerate(@NonNull PicturesettingsVideoframerateFramerate framerate) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoFramerate(cmd.getNativePtr(), framerate.value);
            return cmd;
        }

        /**
         * Set video streaming and recording resolutions.
         * 
         * @param type:
         */
        public static ArsdkCommand encodeVideoResolutions(@NonNull PicturesettingsVideoresolutionsType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoResolutions(cmd.getNativePtr(), type.value);
            return cmd;
        }

        private static native int nativeEncodePictureFormatSelection(long nativeCmd, int type);
        private static native int nativeEncodeAutoWhiteBalanceSelection(long nativeCmd, int type);
        private static native int nativeEncodeExpositionSelection(long nativeCmd, float value);
        private static native int nativeEncodeSaturationSelection(long nativeCmd, float value);
        private static native int nativeEncodeTimelapseSelection(long nativeCmd, int enabled, float interval);
        private static native int nativeEncodeVideoAutorecordSelection(long nativeCmd, int enabled, int mass_storage_id);
        private static native int nativeEncodeVideoStabilizationMode(long nativeCmd, int mode);
        private static native int nativeEncodeVideoRecordingMode(long nativeCmd, int mode);
        private static native int nativeEncodeVideoFramerate(long nativeCmd, int framerate);
        private static native int nativeEncodeVideoResolutions(long nativeCmd, int type);
    }

    /**
     * Photo settings state from product 
     */
    public static class PictureSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0114;

        /** Uid of PictureFormatChanged event. */;
        public static final int PICTUREFORMATCHANGED_UID = 0x0000;

        /** Uid of AutoWhiteBalanceChanged event. */;
        public static final int AUTOWHITEBALANCECHANGED_UID = 0x0001;

        /** Uid of ExpositionChanged event. */;
        public static final int EXPOSITIONCHANGED_UID = 0x0002;

        /** Uid of SaturationChanged event. */;
        public static final int SATURATIONCHANGED_UID = 0x0003;

        /** Uid of TimelapseChanged event. */;
        public static final int TIMELAPSECHANGED_UID = 0x0004;

        /** Uid of VideoAutorecordChanged event. */;
        public static final int VIDEOAUTORECORDCHANGED_UID = 0x0005;

        /** Uid of VideoStabilizationModeChanged event. */;
        public static final int VIDEOSTABILIZATIONMODECHANGED_UID = 0x0006;

        /** Uid of VideoRecordingModeChanged event. */;
        public static final int VIDEORECORDINGMODECHANGED_UID = 0x0007;

        /** Uid of VideoFramerateChanged event. */;
        public static final int VIDEOFRAMERATECHANGED_UID = 0x0008;

        /** Uid of VideoResolutionsChanged event. */;
        public static final int VIDEORESOLUTIONSCHANGED_UID = 0x0009;

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
             * Picture format.
             * 
             * @param type:
             */
            public default void onPictureFormatChanged(@Nullable PicturesettingsstatePictureformatchangedType type) {}

            /**
             * White balance mode.
             * 
             * @param type:
             */
            public default void onAutoWhiteBalanceChanged(@Nullable PicturesettingsstateAutowhitebalancechangedType type) {}

            /**
             * Image exposure.
             * 
             * @param value: Exposure value
             * @param min: Min exposure value
             * @param max: Max exposure value
             */
            public default void onExpositionChanged(float value, float min, float max) {}

            /**
             * Image saturation.
             * 
             * @param value: Saturation value
             * @param min: Min saturation value
             * @param max: Max saturation value
             */
            public default void onSaturationChanged(float value, float min, float max) {}

            /**
             * Timelapse mode.
             * 
             * @param enabled: 1 if timelapse is enabled, 0 otherwise
             * @param interval: interval in seconds for taking pictures
             * @param mininterval: Minimal interval for taking pictures
             * @param maxinterval: Maximal interval for taking pictures
             */
            public default void onTimelapseChanged(int enabled, float interval, float mininterval, float maxinterval) {}

            /**
             * Video Autorecord mode.
             * 
             * @param enabled: 1 if video autorecord is enabled, 0 otherwise
             * @param massStorageId: Mass storage id for the taken video
             */
            public default void onVideoAutorecordChanged(int enabled, int massStorageId) {}

            /**
             * Video stabilization mode.
             * 
             * @param mode:
             */
            public default void onVideoStabilizationModeChanged(@Nullable PicturesettingsstateVideostabilizationmodechangedMode mode) {}

            /**
             * Video recording mode.
             * 
             * @param mode:
             */
            public default void onVideoRecordingModeChanged(@Nullable PicturesettingsstateVideorecordingmodechangedMode mode) {}

            /**
             * Video framerate.
             * 
             * @param framerate:
             */
            public default void onVideoFramerateChanged(@Nullable PicturesettingsstateVideoframeratechangedFramerate framerate) {}

            /**
             * Video resolutions. This event informs about the recording AND streaming resolutions.
             * 
             * @param type:
             */
            public default void onVideoResolutionsChanged(@Nullable PicturesettingsstateVideoresolutionschangedType type) {}
        }

        private static void pictureformatchanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.PicturesettingsstatePictureformatchangedType enumType = ArsdkFeatureArdrone3.PicturesettingsstatePictureformatchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstatePictureformatchangedType value " + type);
            try {
                cb.onPictureFormatChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.PictureFormatChanged [type: " + type + "]", e);
            }
        }

        private static void autowhitebalancechanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.PicturesettingsstateAutowhitebalancechangedType enumType = ArsdkFeatureArdrone3.PicturesettingsstateAutowhitebalancechangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstateAutowhitebalancechangedType value " + type);
            try {
                cb.onAutoWhiteBalanceChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.AutoWhiteBalanceChanged [type: " + type + "]", e);
            }
        }

        private static void expositionchanged(Callback cb, float value, float min, float max) {
            try {
                cb.onExpositionChanged(value, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.ExpositionChanged [value: " + value + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void saturationchanged(Callback cb, float value, float min, float max) {
            try {
                cb.onSaturationChanged(value, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.SaturationChanged [value: " + value + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void timelapsechanged(Callback cb, int enabled, float interval, float mininterval, float maxinterval) {
            try {
                cb.onTimelapseChanged(enabled, interval, mininterval, maxinterval);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.TimelapseChanged [enabled: " + enabled + ", interval: " + interval + ", minInterval: " + mininterval + ", maxInterval: " + maxinterval + "]", e);
            }
        }

        private static void videoautorecordchanged(Callback cb, int enabled, int massStorageId) {
            try {
                cb.onVideoAutorecordChanged(enabled, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.VideoAutorecordChanged [enabled: " + enabled + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void videostabilizationmodechanged(Callback cb, int mode) {
            ArsdkFeatureArdrone3.PicturesettingsstateVideostabilizationmodechangedMode enumMode = ArsdkFeatureArdrone3.PicturesettingsstateVideostabilizationmodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstateVideostabilizationmodechangedMode value " + mode);
            try {
                cb.onVideoStabilizationModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.VideoStabilizationModeChanged [mode: " + mode + "]", e);
            }
        }

        private static void videorecordingmodechanged(Callback cb, int mode) {
            ArsdkFeatureArdrone3.PicturesettingsstateVideorecordingmodechangedMode enumMode = ArsdkFeatureArdrone3.PicturesettingsstateVideorecordingmodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstateVideorecordingmodechangedMode value " + mode);
            try {
                cb.onVideoRecordingModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.VideoRecordingModeChanged [mode: " + mode + "]", e);
            }
        }

        private static void videoframeratechanged(Callback cb, int framerate) {
            ArsdkFeatureArdrone3.PicturesettingsstateVideoframeratechangedFramerate enumFramerate = ArsdkFeatureArdrone3.PicturesettingsstateVideoframeratechangedFramerate.fromValue(framerate);
            if (enumFramerate == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstateVideoframeratechangedFramerate value " + framerate);
            try {
                cb.onVideoFramerateChanged(enumFramerate);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.VideoFramerateChanged [framerate: " + framerate + "]", e);
            }
        }

        private static void videoresolutionschanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.PicturesettingsstateVideoresolutionschangedType enumType = ArsdkFeatureArdrone3.PicturesettingsstateVideoresolutionschangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PicturesettingsstateVideoresolutionschangedType value " + type);
            try {
                cb.onVideoResolutionsChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PictureSettingsState.VideoResolutionsChanged [type: " + type + "]", e);
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
        public static final int UID = 0x0115;

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

        /**
         * Set the stream mode.
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeVideoStreamMode(@NonNull MediastreamingVideostreammodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoStreamMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        private static native int nativeEncodeVideoEnable(long nativeCmd, int enable);
        private static native int nativeEncodeVideoStreamMode(long nativeCmd, int mode);
    }

    /**
     * Media streaming status. 
     */
    public static class MediaStreamingState {

        /** Feature uid. */
        public static final int UID = 0x0116;

        /** Uid of VideoEnableChanged event. */;
        public static final int VIDEOENABLECHANGED_UID = 0x0000;

        /** Uid of VideoStreamModeChanged event. */;
        public static final int VIDEOSTREAMMODECHANGED_UID = 0x0001;

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
             * Video stream state.
             * 
             * @param enabled:
             */
            public default void onVideoEnableChanged(@Nullable MediastreamingstateVideoenablechangedEnabled enabled) {}

            /**
             * Video stream mode state
             * 
             * @param mode:
             */
            public default void onVideoStreamModeChanged(@Nullable MediastreamingstateVideostreammodechangedMode mode) {}
        }

        private static void videoenablechanged(Callback cb, int enabled) {
            ArsdkFeatureArdrone3.MediastreamingstateVideoenablechangedEnabled enumEnabled = ArsdkFeatureArdrone3.MediastreamingstateVideoenablechangedEnabled.fromValue(enabled);
            if (enumEnabled == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediastreamingstateVideoenablechangedEnabled value " + enabled);
            try {
                cb.onVideoEnableChanged(enumEnabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaStreamingState.VideoEnableChanged [enabled: " + enabled + "]", e);
            }
        }

        private static void videostreammodechanged(Callback cb, int mode) {
            ArsdkFeatureArdrone3.MediastreamingstateVideostreammodechangedMode enumMode = ArsdkFeatureArdrone3.MediastreamingstateVideostreammodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.MediastreamingstateVideostreammodechangedMode value " + mode);
            try {
                cb.onVideoStreamModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.MediaStreamingState.VideoStreamModeChanged [mode: " + mode + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * GPS settings 
     */
    public static class GPSSettings {

        /** Feature uid. */
        public static final int UID = 0x0117;

        /**
         * Set home position.
         * 
         * @param latitude: Home latitude in decimal degrees
         * @param longitude: Home longitude in decimal degrees
         * @param altitude: Home altitude in meters
         */
        @Deprecated
        public static ArsdkCommand encodeSetHome(double latitude, double longitude, double altitude) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetHome(cmd.getNativePtr(), latitude, longitude, altitude);
            return cmd;
        }

        /**
         * Reset home position.
         */
        @Deprecated
        public static ArsdkCommand encodeResetHome() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeResetHome(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set controller gps location. The user location might be used in case of return home, according to the home
         * type and the accuracy of the given position. You can get the current home type with the event
         * [HomeType](#1-24-4).
         * 
         * @param latitude: GPS latitude in decimal degrees
         * @param longitude: GPS longitude in decimal degrees
         * @param altitude: GPS altitude in meters
         * @param horizontalaccuracy: Horizontal Accuracy in meter ; equal -1 if no horizontal Accuracy
         * @param verticalaccuracy: Vertical Accuracy in meter ; equal -1 if no vertical Accuracy
         */
        public static ArsdkCommand encodeSendControllerGPS(double latitude, double longitude, double altitude, double horizontalaccuracy, double verticalaccuracy) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSendControllerGPS(cmd.getNativePtr(), latitude, longitude, altitude, horizontalaccuracy, verticalaccuracy);
            return cmd;
        }

        /**
         * Set the preferred home type. Please note that this is only a preference. The actual type chosen is given by
         * the event [HomeType](#1-31-2). You can get the currently available types with the event
         * [HomeTypeAvailability](#1-31-1).
         * 
         * @param type:
         */
        @Deprecated
        public static ArsdkCommand encodeHomeType(@NonNull GpssettingsHometypeType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeHomeType(cmd.getNativePtr(), type.value);
            return cmd;
        }

        /**
         * Set the delay after which the drone will automatically try to return home after a disconnection.
         * 
         * @param delay: Delay in second
         */
        @Deprecated
        public static ArsdkCommand encodeReturnHomeDelay(int delay) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReturnHomeDelay(cmd.getNativePtr(), delay);
            return cmd;
        }

        /**
         * Set the return home minimum altitude. If the drone is below this altitude when starting its return home, it
         * will first reach the minimum altitude. If it is higher than this minimum altitude, it will operate its return
         * home at its actual altitude.
         * 
         * @param value: Altitude in meters, relative to the take off point. Bounds are given by the event
         * [ReturnHomeMinAltitude](#1-24-7).
         */
        @Deprecated
        public static ArsdkCommand encodeReturnHomeMinAltitude(float value) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReturnHomeMinAltitude(cmd.getNativePtr(), value);
            return cmd;
        }

        private static native int nativeEncodeSetHome(long nativeCmd, double latitude, double longitude, double altitude);
        private static native int nativeEncodeResetHome(long nativeCmd);
        private static native int nativeEncodeSendControllerGPS(long nativeCmd, double latitude, double longitude, double altitude, double horizontalAccuracy, double verticalAccuracy);
        private static native int nativeEncodeHomeType(long nativeCmd, int type);
        private static native int nativeEncodeReturnHomeDelay(long nativeCmd, int delay);
        private static native int nativeEncodeReturnHomeMinAltitude(long nativeCmd, float value);
    }

    /**
     * GPS settings state 
     */
    public static class GPSSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0118;

        /** Uid of HomeChanged event. */;
        public static final int HOMECHANGED_UID = 0x0000;

        /** Uid of ResetHomeChanged event. */;
        public static final int RESETHOMECHANGED_UID = 0x0001;

        /** Uid of GPSFixStateChanged event. */;
        public static final int GPSFIXSTATECHANGED_UID = 0x0002;

        /** Uid of GPSUpdateStateChanged event. */;
        public static final int GPSUPDATESTATECHANGED_UID = 0x0003;

        /** Uid of HomeTypeChanged event. */;
        public static final int HOMETYPECHANGED_UID = 0x0004;

        /** Uid of ReturnHomeDelayChanged event. */;
        public static final int RETURNHOMEDELAYCHANGED_UID = 0x0005;

        /** Uid of GeofenceCenterChanged event. */;
        public static final int GEOFENCECENTERCHANGED_UID = 0x0006;

        /** Uid of ReturnHomeMinAltitudeChanged event. */;
        public static final int RETURNHOMEMINALTITUDECHANGED_UID = 0x0007;

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
             * Home location.
             * 
             * @param latitude: Home latitude in decimal degrees
             * @param longitude: Home longitude in decimal degrees
             * @param altitude: Home altitude in meters
             */
            public default void onHomeChanged(double latitude, double longitude, double altitude) {}

            /**
             * Home location has been reset.
             * 
             * @param latitude: Home latitude in decimal degrees
             * @param longitude: Home longitude in decimal degrees
             * @param altitude: Home altitude in meters
             */
            @Deprecated
            public default void onResetHomeChanged(double latitude, double longitude, double altitude) {}

            /**
             * Gps fix info.
             * 
             * @param fixed: 1 if gps on drone is fixed, 0 otherwise
             */
            public default void onGPSFixStateChanged(int fixed) {}

            /**
             * Gps update state.
             * 
             * @param state:
             */
            @Deprecated
            public default void onGPSUpdateStateChanged(@Nullable GpssettingsstateGpsupdatestatechangedState state) {}

            /**
             * User preference for the home type. See [HomeType](#1-31-2) to get the drone actual home type.
             * 
             * @param type:
             */
            @Deprecated
            public default void onHomeTypeChanged(@Nullable GpssettingsstateHometypechangedType type) {}

            /**
             * Return home trigger delay. This delay represents the time after which the return home is automatically
             * triggered after a disconnection.
             * 
             * @param delay: Delay in second
             */
            @Deprecated
            public default void onReturnHomeDelayChanged(int delay) {}

            /**
             * Geofence center location. This location represents the center of the geofence zone. This is updated at a
             * maximum frequency of 1 Hz.
             * 
             * @param latitude: GPS latitude in decimal degrees
             * @param longitude: GPS longitude in decimal degrees
             */
            public default void onGeofenceCenterChanged(double latitude, double longitude) {}

            /**
             * Minumum altitude for return home changed.
             * 
             * @param value: Current value in meters, relative to take off altitude.
             * @param min: Lower bound in meters, relative to take off altitude.
             * @param max: Upper bound in meters, relative to take off altitude.
             */
            @Deprecated
            public default void onReturnHomeMinAltitudeChanged(float value, float min, float max) {}
        }

        private static void homechanged(Callback cb, double latitude, double longitude, double altitude) {
            try {
                cb.onHomeChanged(latitude, longitude, altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.HomeChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + "]", e);
            }
        }

        private static void resethomechanged(Callback cb, double latitude, double longitude, double altitude) {
            try {
                cb.onResetHomeChanged(latitude, longitude, altitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.ResetHomeChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + "]", e);
            }
        }

        private static void gpsfixstatechanged(Callback cb, int fixed) {
            try {
                cb.onGPSFixStateChanged(fixed);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.GPSFixStateChanged [fixed: " + fixed + "]", e);
            }
        }

        private static void gpsupdatestatechanged(Callback cb, int state) {
            ArsdkFeatureArdrone3.GpssettingsstateGpsupdatestatechangedState enumState = ArsdkFeatureArdrone3.GpssettingsstateGpsupdatestatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.GpssettingsstateGpsupdatestatechangedState value " + state);
            try {
                cb.onGPSUpdateStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.GPSUpdateStateChanged [state: " + state + "]", e);
            }
        }

        private static void hometypechanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.GpssettingsstateHometypechangedType enumType = ArsdkFeatureArdrone3.GpssettingsstateHometypechangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.GpssettingsstateHometypechangedType value " + type);
            try {
                cb.onHomeTypeChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.HomeTypeChanged [type: " + type + "]", e);
            }
        }

        private static void returnhomedelaychanged(Callback cb, int delay) {
            try {
                cb.onReturnHomeDelayChanged(delay);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.ReturnHomeDelayChanged [delay: " + delay + "]", e);
            }
        }

        private static void geofencecenterchanged(Callback cb, double latitude, double longitude) {
            try {
                cb.onGeofenceCenterChanged(latitude, longitude);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.GeofenceCenterChanged [latitude: " + latitude + ", longitude: " + longitude + "]", e);
            }
        }

        private static void returnhomeminaltitudechanged(Callback cb, float value, float min, float max) {
            try {
                cb.onReturnHomeMinAltitudeChanged(value, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSSettingsState.ReturnHomeMinAltitudeChanged [value: " + value + ", min: " + min + ", max: " + max + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Camera state 
     */
    public static class CameraState {

        /** Feature uid. */
        public static final int UID = 0x0119;

        /** Uid of Orientation event. */;
        public static final int ORIENTATION_UID = 0x0000;

        /** Uid of defaultCameraOrientation event. */;
        public static final int DEFAULTCAMERAORIENTATION_UID = 0x0001;

        /** Uid of OrientationV2 event. */;
        public static final int ORIENTATIONV2_UID = 0x0002;

        /** Uid of defaultCameraOrientationV2 event. */;
        public static final int DEFAULTCAMERAORIENTATIONV2_UID = 0x0003;

        /** Uid of VelocityRange event. */;
        public static final int VELOCITYRANGE_UID = 0x0004;

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
             * Camera orientation.
             * 
             * @param tilt: Tilt camera consign for the drone [-100;100]
             * @param pan: Pan camera consign for the drone [-100;100]
             */
            @Deprecated
            public default void onOrientation(int tilt, int pan) {}

            /**
             * Orientation of the center of the camera. This is the value to send when you want to center the camera.
             * 
             * @param tilt: Tilt value (in degree)
             * @param pan: Pan value (in degree)
             */
            @Deprecated
            public default void onDefaultCameraOrientation(int tilt, int pan) {}

            /**
             * Camera orientation with float arguments.
             * 
             * @param tilt: Tilt camera consign for the drone [deg]
             * @param pan: Pan camera consign for the drone [deg]
             */
            public default void onOrientationV2(float tilt, float pan) {}

            /**
             * Orientation of the center of the camera. This is the value to send when you want to center the camera.
             * 
             * @param tilt: Tilt value [deg]
             * @param pan: Pan value [deg]
             */
            public default void onDefaultCameraOrientationV2(float tilt, float pan) {}

            /**
             * Camera Orientation velocity limits.
             * 
             * @param maxTilt: Absolute max tilt velocity [deg/s]
             * @param maxPan: Absolute max pan velocity [deg/s]
             */
            public default void onVelocityRange(float maxTilt, float maxPan) {}
        }

        private static void orientation(Callback cb, int tilt, int pan) {
            try {
                cb.onOrientation(tilt, pan);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.CameraState.Orientation [tilt: " + tilt + ", pan: " + pan + "]", e);
            }
        }

        private static void defaultcameraorientation(Callback cb, int tilt, int pan) {
            try {
                cb.onDefaultCameraOrientation(tilt, pan);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.CameraState.defaultCameraOrientation [tilt: " + tilt + ", pan: " + pan + "]", e);
            }
        }

        private static void orientationv2(Callback cb, float tilt, float pan) {
            try {
                cb.onOrientationV2(tilt, pan);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.CameraState.OrientationV2 [tilt: " + tilt + ", pan: " + pan + "]", e);
            }
        }

        private static void defaultcameraorientationv2(Callback cb, float tilt, float pan) {
            try {
                cb.onDefaultCameraOrientationV2(tilt, pan);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.CameraState.defaultCameraOrientationV2 [tilt: " + tilt + ", pan: " + pan + "]", e);
            }
        }

        private static void velocityrange(Callback cb, float maxTilt, float maxPan) {
            try {
                cb.onVelocityRange(maxTilt, maxPan);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.CameraState.VelocityRange [max_tilt: " + maxTilt + ", max_pan: " + maxPan + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Anti-flickering related commands 
     */
    public static class Antiflickering {

        /** Feature uid. */
        public static final int UID = 0x011D;

        /**
         * Set the electric frequency of the surrounding lights. This is used to avoid the video flickering in auto
         * mode. You can get the current antiflickering mode with the event [AntiflickeringModeChanged](#1-30-1).
         * 
         * @param frequency:
         */
        public static ArsdkCommand encodeElectricFrequency(@NonNull AntiflickeringElectricfrequencyFrequency frequency) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeElectricFrequency(cmd.getNativePtr(), frequency.value);
            return cmd;
        }

        /**
         * Set the antiflickering mode. If auto, the drone will detect when flickers appears on the video and trigger
         * the antiflickering. In this case, this electric frequency it will use will be the one specified in the event
         * [ElectricFrequency](#1-29-0). Forcing the antiflickering (FixedFiftyHertz or FixedFiftyHertz) can reduce
         * luminosity of the video.
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeSetMode(@NonNull AntiflickeringSetmodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        private static native int nativeEncodeElectricFrequency(long nativeCmd, int frequency);
        private static native int nativeEncodeSetMode(long nativeCmd, int mode);
    }

    /**
     * Anti-flickering related states 
     */
    public static class AntiflickeringState {

        /** Feature uid. */
        public static final int UID = 0x011E;

        /** Uid of electricFrequencyChanged event. */;
        public static final int ELECTRICFREQUENCYCHANGED_UID = 0x0000;

        /** Uid of modeChanged event. */;
        public static final int MODECHANGED_UID = 0x0001;

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
             * Electric frequency. This piece of information is used for the antiflickering when the
             * [AntiflickeringMode](#1-30-1) is set to *auto*.
             * 
             * @param frequency:
             */
            public default void onElectricFrequencyChanged(@Nullable AntiflickeringstateElectricfrequencychangedFrequency frequency) {}

            /**
             * Antiflickering mode.
             * 
             * @param mode:
             */
            public default void onModeChanged(@Nullable AntiflickeringstateModechangedMode mode) {}
        }

        private static void electricfrequencychanged(Callback cb, int frequency) {
            ArsdkFeatureArdrone3.AntiflickeringstateElectricfrequencychangedFrequency enumFrequency = ArsdkFeatureArdrone3.AntiflickeringstateElectricfrequencychangedFrequency.fromValue(frequency);
            if (enumFrequency == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.AntiflickeringstateElectricfrequencychangedFrequency value " + frequency);
            try {
                cb.onElectricFrequencyChanged(enumFrequency);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.AntiflickeringState.electricFrequencyChanged [frequency: " + frequency + "]", e);
            }
        }

        private static void modechanged(Callback cb, int mode) {
            ArsdkFeatureArdrone3.AntiflickeringstateModechangedMode enumMode = ArsdkFeatureArdrone3.AntiflickeringstateModechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.AntiflickeringstateModechangedMode value " + mode);
            try {
                cb.onModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.AntiflickeringState.modeChanged [mode: " + mode + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * GPS related States 
     */
    public static class GPSState {

        /** Feature uid. */
        public static final int UID = 0x011F;

        /** Uid of NumberOfSatelliteChanged event. */;
        public static final int NUMBEROFSATELLITECHANGED_UID = 0x0000;

        /** Uid of HomeTypeAvailabilityChanged event. */;
        public static final int HOMETYPEAVAILABILITYCHANGED_UID = 0x0001;

        /** Uid of HomeTypeChosenChanged event. */;
        public static final int HOMETYPECHOSENCHANGED_UID = 0x0002;

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
             * Number of GPS satellites.
             * 
             * @param numberofsatellite: The number of satellite
             */
            public default void onNumberOfSatelliteChanged(int numberofsatellite) {}

            /**
             * Home type availability.
             * 
             * @param type:
             * @param available: 1 if this type is available, 0 otherwise
             */
            @Deprecated
            public default void onHomeTypeAvailabilityChanged(@Nullable GpsstateHometypeavailabilitychangedType type, int available) {}

            /**
             * Home type. This choice is made by the drone, according to the [PreferredHomeType](#1-24-4) and the
             * [HomeTypeAvailability](#1-31-1). The drone will choose the type matching with the user preference only if
             * this type is available. If not, it will chose a type in this order: FOLLOWEE ; TAKEOFF ; PILOT ;
             * FIRST_FIX
             * 
             * @param type:
             */
            @Deprecated
            public default void onHomeTypeChosenChanged(@Nullable GpsstateHometypechosenchangedType type) {}
        }

        private static void numberofsatellitechanged(Callback cb, int numberofsatellite) {
            try {
                cb.onNumberOfSatelliteChanged(numberofsatellite);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSState.NumberOfSatelliteChanged [numberOfSatellite: " + numberofsatellite + "]", e);
            }
        }

        private static void hometypeavailabilitychanged(Callback cb, int type, int available) {
            ArsdkFeatureArdrone3.GpsstateHometypeavailabilitychangedType enumType = ArsdkFeatureArdrone3.GpsstateHometypeavailabilitychangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.GpsstateHometypeavailabilitychangedType value " + type);
            try {
                cb.onHomeTypeAvailabilityChanged(enumType, available);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSState.HomeTypeAvailabilityChanged [type: " + type + ", available: " + available + "]", e);
            }
        }

        private static void hometypechosenchanged(Callback cb, int type) {
            ArsdkFeatureArdrone3.GpsstateHometypechosenchangedType enumType = ArsdkFeatureArdrone3.GpsstateHometypechosenchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.GpsstateHometypechosenchangedType value " + type);
            try {
                cb.onHomeTypeChosenChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.GPSState.HomeTypeChosenChanged [type: " + type + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Pro features enabled on the Bebop 
     */
    public static class PROState {

        /** Feature uid. */
        public static final int UID = 0x0120;

        /** Uid of Features event. */;
        public static final int FEATURES_UID = 0x0000;

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
             * Pro features.
             * 
             * @param features: Bitfield representing enabled features.
             */
            @Deprecated
            public default void onFeatures(long features) {}
        }

        private static void features(Callback cb, long features) {
            try {
                cb.onFeatures(features);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PROState.Features [features: " + features + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Information about the connected accessories 
     */
    public static class AccessoryState {

        /** Feature uid. */
        public static final int UID = 0x0121;

        /** Uid of ConnectedAccessories event. */;
        public static final int CONNECTEDACCESSORIES_UID = 0x0000;

        /** Uid of Battery event. */;
        public static final int BATTERY_UID = 0x0001;

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
             * List of all connected accessories. This event presents the list of all connected accessories. To actually
             * use the component, use the component dedicated feature.
             * 
             * @param id: Id of the accessory for the session.
             * @param accessoryType:
             * @param uid: Unique Id of the accessory. This id is unique by accessory_type.
             * @param swversion: Software Version of the accessory.
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onConnectedAccessories(int id, @Nullable AccessorystateConnectedaccessoriesAccessoryType accessoryType, String uid, String swversion, int listFlags) {}

            /**
             * Connected accessories battery.
             * 
             * @param id: Id of the accessory for the session.
             * @param batterylevel: Battery level in percentage.
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onBattery(int id, int batterylevel, int listFlags) {}
        }

        private static void connectedaccessories(Callback cb, int id, int accessoryType, String uid, String swversion, int listFlags) {
            ArsdkFeatureArdrone3.AccessorystateConnectedaccessoriesAccessoryType enumAccessorytype = ArsdkFeatureArdrone3.AccessorystateConnectedaccessoriesAccessoryType.fromValue(accessoryType);
            if (enumAccessorytype == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.AccessorystateConnectedaccessoriesAccessoryType value " + accessoryType);
            try {
                cb.onConnectedAccessories(id, enumAccessorytype, uid, swversion, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.AccessoryState.ConnectedAccessories [id: " + id + ", accessory_type: " + accessoryType + ", uid: " + uid + ", swVersion: " + swversion + ", list_flags: " + listFlags + "]", e);
            }
        }

        private static void battery(Callback cb, int id, int batterylevel, int listFlags) {
            try {
                cb.onBattery(id, batterylevel, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.AccessoryState.Battery [id: " + id + ", batteryLevel: " + batterylevel + ", list_flags: " + listFlags + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Events of Piloting 
     */
    public static class PilotingEvent {

        /** Feature uid. */
        public static final int UID = 0x0122;

        /** Uid of moveByEnd event. */;
        public static final int MOVEBYEND_UID = 0x0000;

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
             * Relative move ended. Informs about the move that the drone managed to do and why it stopped.
             * 
             * @param dx: Distance traveled along the front axis [m]
             * @param dy: Distance traveled along the right axis [m]
             * @param dz: Distance traveled along the down axis [m]
             * @param dpsi: Applied angle on heading [rad]
             * @param error:
             */
            public default void onMoveByEnd(float dx, float dy, float dz, float dpsi, @Nullable PilotingeventMovebyendError error) {}
        }

        private static void movebyend(Callback cb, float dx, float dy, float dz, float dpsi, int error) {
            ArsdkFeatureArdrone3.PilotingeventMovebyendError enumError = ArsdkFeatureArdrone3.PilotingeventMovebyendError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.PilotingeventMovebyendError value " + error);
            try {
                cb.onMoveByEnd(dx, dy, dz, dpsi, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.PilotingEvent.moveByEnd [dX: " + dx + ", dY: " + dy + ", dZ: " + dz + ", dPsi: " + dpsi + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Sounds related commands 
     */
    public static class Sound {

        /** Feature uid. */
        public static final int UID = 0x0123;

        /**
         * Start the alert sound. The alert sound can only be started when the drone is not flying.
         */
        public static ArsdkCommand encodeStartAlertSound() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStartAlertSound(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Stop the alert sound.
         */
        public static ArsdkCommand encodeStopAlertSound() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStopAlertSound(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeStartAlertSound(long nativeCmd);
        private static native int nativeEncodeStopAlertSound(long nativeCmd);
    }

    /**
     * Sounds related events 
     */
    public static class SoundState {

        /** Feature uid. */
        public static final int UID = 0x0124;

        /** Uid of AlertSound event. */;
        public static final int ALERTSOUND_UID = 0x0000;

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
             * Alert sound state.
             * 
             * @param state:
             */
            public default void onAlertSound(@Nullable SoundstateAlertsoundState state) {}
        }

        private static void alertsound(Callback cb, int state) {
            ArsdkFeatureArdrone3.SoundstateAlertsoundState enumState = ArsdkFeatureArdrone3.SoundstateAlertsoundState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureArdrone3.SoundstateAlertsoundState value " + state);
            try {
                cb.onAlertSound(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: ardrone3.SoundState.AlertSound [state: " + state + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
