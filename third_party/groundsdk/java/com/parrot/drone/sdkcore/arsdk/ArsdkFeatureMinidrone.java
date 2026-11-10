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
 * Minidrone feature command/event interface.
 */
public class ArsdkFeatureMinidrone {

    /**
     * Drone Flying Mode.
     */
    public enum PilotingFlyingmodeMode {

        /**
         * Fly as a quadrictopter.
         */
        QUADRICOPTER(0),

        /**
         * Fly as a plane in forward mode.
         */
        PLANE_FORWARD(1),

        /**
         * Fly as a plane in backward mode.
         */
        PLANE_BACKWARD(2);

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
        public static PilotingFlyingmodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingFlyingmodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingFlyingmodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingFlyingmodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Plane Gear Box.
     */
    public enum PilotingPlanegearboxState {

        /**
         * Gear 1. Low speed.
         */
        GEAR_1(0),

        /**
         * Gear 2. Middle speed.
         */
        GEAR_2(1),

        /**
         * Gear 3. High speed.
         */
        GEAR_3(2);

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
        public static PilotingPlanegearboxState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingPlanegearboxState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingPlanegearboxState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingPlanegearboxState e: values())
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
         * Hovering state.
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
         * Rolling state.
         */
        ROLLING(6),

        /**
         * Initializing state (user should let the drone steady for a while).
         */
        INIT(7);

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
        LOW_BATTERY(4);

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
     * Drone Flying Mode.
     */
    public enum PilotingstateFlyingmodechangedMode {

        /**
         * Fly as a quadrictopter.
         */
        QUADRICOPTER(0),

        /**
         * Fly as a plane in forward mode.
         */
        PLANE_FORWARD(1),

        /**
         * Fly as a plane in backward mode.
         */
        PLANE_BACKWARD(2);

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
        public static PilotingstateFlyingmodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstateFlyingmodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstateFlyingmodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstateFlyingmodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Plane Gear Box.
     */
    public enum PilotingstatePlanegearboxchangedState {

        /**
         * Gear 1. Low speed.
         */
        GEAR_1(0),

        /**
         * Gear 2. Middle speed.
         */
        GEAR_2(1),

        /**
         * Gear 3. High speed.
         */
        GEAR_3(2);

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
        public static PilotingstatePlanegearboxchangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePlanegearboxchangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePlanegearboxchangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePlanegearboxchangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PilotingstatePilotingmodechangedMode {

        /**
         * The flight envelope of Mambo FPV has been divided in three piloting modes. The first one corresponds to the
         * well-known flying mode currently used for Mambo, which is based in an horizontal stabilisation (performed via
         * the vertical camera and the acceleration information) and a vertical acceleration (by means of the
         * ultrasound, the barometer and the vertical accelerometer) in order for the drone to stay in fixed point
         * position when no piloting commands are sent by the user.
         */
        EASY(0),

        /**
         * The second piloting mode consists of deactivating the horizontal stabilisation. Thus, in this flying mode,
         * when no piloting command is received, the drone will try to stay at 0° tilt angle instead of responding to a
         * 0 m/s horizontal speed reference. This behaviour will result in a slight horizontal drift.
         */
        MEDIUM(1),

        /**
         * The third piloting mode also adds the vertical stabilisation deactivation and, therefore, a slight vertical
         * drift. When flying in the third mode, a closed loop height control is no longer performed in order for the
         * drone to keep a certain height and vertical speed. Instead, the vertical command coming from the user will
         * directly generate an open loop acceleration command.
         */
        DIFFICULT(2);

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
        public static PilotingstatePilotingmodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingstatePilotingmodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingstatePilotingmodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingstatePilotingmodechangedMode e: values())
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
     * Piloting modes.
     */
    public enum PilotingsettingsPreferredpilotingmodeMode {

        /**
         * The flight envelope of Mambo FPV has been divided in three piloting modes. The first one corresponds to the
         * well-known flying mode currently used for Mambo, which is based in an horizontal stabilisation (performed via
         * the vertical camera and the acceleration information) and a vertical acceleration (by means of the
         * ultrasound, the barometer and the vertical accelerometer) in order for the drone to stay in fixed point
         * position when no piloting commands are sent by the user.
         */
        EASY(0),

        /**
         * The second piloting mode consists of deactivating the horizontal stabilisation. Thus, in this flying mode,
         * when no piloting command is received, the drone will try to stay at 0° tilt angle instead of responding to a
         * 0 m/s horizontal speed reference. This behaviour will result in a slight horizontal drift.
         */
        MEDIUM(1),

        /**
         * The third piloting mode also adds the vertical stabilisation deactivation and, therefore, a slight vertical
         * drift. When flying in the third mode, a closed loop height control is no longer performed in order for the
         * drone to keep a certain height and vertical speed. Instead, the vertical command coming from the user will
         * directly generate an open loop acceleration command.
         */
        DIFFICULT(2);

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
        public static PilotingsettingsPreferredpilotingmodeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsPreferredpilotingmodeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsPreferredpilotingmodeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsPreferredpilotingmodeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PilotingsettingsstatePreferredpilotingmodechangedMode {

        /**
         * The flight envelope of Mambo FPV has been divided in three piloting modes. The first one corresponds to the
         * well-known flying mode currently used for Mambo, which is based in an horizontal stabilisation (performed via
         * the vertical camera and the acceleration information) and a vertical acceleration (by means of the
         * ultrasound, the barometer and the vertical accelerometer) in order for the drone to stay in fixed point
         * position when no piloting commands are sent by the user.
         */
        EASY(0),

        /**
         * The second piloting mode consists of deactivating the horizontal stabilisation. Thus, in this flying mode,
         * when no piloting command is received, the drone will try to stay at 0° tilt angle instead of responding to a
         * 0 m/s horizontal speed reference. This behaviour will result in a slight horizontal drift.
         */
        MEDIUM(1),

        /**
         * The third piloting mode also adds the vertical stabilisation deactivation and, therefore, a slight vertical
         * drift. When flying in the third mode, a closed loop height control is no longer performed in order for the
         * drone to keep a certain height and vertical speed. Instead, the vertical command coming from the user will
         * directly generate an open loop acceleration command.
         */
        DIFFICULT(2);

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
        public static PilotingsettingsstatePreferredpilotingmodechangedMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PilotingsettingsstatePreferredpilotingmodechangedMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PilotingsettingsstatePreferredpilotingmodechangedMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PilotingsettingsstatePreferredpilotingmodechangedMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Usb Light state.
     */
    public enum UsbaccessorystateLightstateState {

        /**
         * Fixed state at given intensity.
         */
        FIXED(0),

        /**
         * Blinked state.
         */
        BLINKED(1),

        /**
         * Oscillated state.
         */
        OSCILLATED(2);

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
        public static UsbaccessorystateLightstateState fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessorystateLightstateState(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessorystateLightstateState> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessorystateLightstateState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Usb Claw state.
     */
    public enum UsbaccessorystateClawstateState {

        /**
         * Claw is fully opened.
         */
        OPENED(0),

        /**
         * Claw open in progress.
         */
        OPENING(1),

        /**
         * Claw is fully closed.
         */
        CLOSED(2),

        /**
         * Claw close in progress.
         */
        CLOSING(3);

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
        public static UsbaccessorystateClawstateState fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessorystateClawstateState(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessorystateClawstateState> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessorystateClawstateState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * USB Claw state.
     */
    public enum UsbaccessorystateGunstateState {

        /**
         * Gun is ready to fire.
         */
        READY(0),

        /**
         * Gun is busy (ie not ready to fire).
         */
        BUSY(1);

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
        public static UsbaccessorystateGunstateState fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessorystateGunstateState(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessorystateGunstateState> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessorystateGunstateState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Usb Light mode.
     */
    public enum UsbaccessoryLightcontrolMode {

        /**
         * Turn light in fixed state at a given intensity.
         */
        FIXED(0),

        /**
         * Turn light in blinked state.
         */
        BLINKED(1),

        /**
         * Turn light in oscillated state.
         */
        OSCILLATED(2);

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
        public static UsbaccessoryLightcontrolMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessoryLightcontrolMode(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessoryLightcontrolMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessoryLightcontrolMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * USB Claw action.
     */
    public enum UsbaccessoryClawcontrolAction {

        /**
         * Open Claw.
         */
        OPEN(0),

        /**
         * Close Claw.
         */
        CLOSE(1);

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
        public static UsbaccessoryClawcontrolAction fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessoryClawcontrolAction(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessoryClawcontrolAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessoryClawcontrolAction e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * USB Gun action.
     */
    public enum UsbaccessoryGuncontrolAction {

        /**
         * Fire.
         */
        FIRE(0);

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
        public static UsbaccessoryGuncontrolAction fromValue(int value) {
            return MAP.get(value, null);
        }

        private UsbaccessoryGuncontrolAction(int value) {
            this.value = value;
        }

        private static final SparseArray<UsbaccessoryGuncontrolAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (UsbaccessoryGuncontrolAction e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Power mode of the camera.
     */
    public enum MinicamstatePowermodechangedPowerMode {

        /**
         * Low power: most hardware is powered off, wake up via USB resume.  Used when charging.
         */
        LOW(0),

        /**
         * Medium power: video hardware is powered off.  Used when drone is not flying during more than 3 minutes. Note
         * that it can still stream the SD content.
         */
        MEDIUM(1),

        /**
         * Normal power: all features are available.  Used when flying.
         */
        NORMAL(2);

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
        public static MinicamstatePowermodechangedPowerMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstatePowermodechangedPowerMode(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstatePowermodechangedPowerMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstatePowermodechangedPowerMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of the camera.
     */
    public enum MinicamstateStatechangedState {

        /**
         * Minicam is unplugged.
         */
        UNPLUGGED(0),

        /**
         * Minicam is plugged, but not ready.
         */
        PLUGGED(1),

        /**
         * Minicam is ready.
         */
        READY(2);

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
        public static MinicamstateStatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstateStatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstateStatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstateStatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of device picture recording.
     */
    public enum MinicamstatePicturechangedState {

        /**
         * Picture recording is ready.
         */
        READY(0),

        /**
         * Picture recording is busy.
         */
        BUSY(1),

        /**
         * Picture recording is not available.
         */
        NOT_AVAILABLE(2);

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
        public static MinicamstatePicturechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstatePicturechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstatePicturechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstatePicturechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Result of device picture recording.
     */
    public enum MinicamstatePicturechangedResult {

        /**
         * Success.
         */
        SUCCESS(0),

        /**
         * Device is full.
         */
        FULL_DEVICE(1),

        /**
         * Continuous shooting is already running.
         */
        CONTINUOUS_SHOOTING(2),

        /**
         * Over snapshot max queue size.
         */
        FULL_QUEUE(3),

        /**
         * Couldn't take picture.
         */
        ERROR(4),

        /**
         * SD card doesn't exist.
         */
        NO_SD(5),

        /**
         * SD card format error.
         */
        SD_BAD_FORMAT(6),

        /**
         * SD card is formatting.
         */
        SD_FORMATTING(7);

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
        public static MinicamstatePicturechangedResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstatePicturechangedResult(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstatePicturechangedResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstatePicturechangedResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of device video recording.
     */
    public enum MinicamstateVideostatechangedState {

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
        public static MinicamstateVideostatechangedState fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstateVideostatechangedState(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstateVideostatechangedState> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstateVideostatechangedState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Error to explain the state.
     */
    public enum MinicamstateVideostatechangedError {

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
        LOWBATTERY(4),

        /**
         * SD card doesn't exist.
         */
        NO_SD(5);

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
        public static MinicamstateVideostatechangedError fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamstateVideostatechangedError(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamstateVideostatechangedError> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamstateVideostatechangedError e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the electric frequency.
     */
    public enum VideosettingsElectricfrequencyFrequency {

        /**
         * Electric frequency of the country is 50hz.
         */
        FIFTY_HERTZ(0),

        /**
         * Electric frequency of the country is 60hz.
         */
        SIXTY_HERTZ(1);

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
        public static VideosettingsElectricfrequencyFrequency fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsElectricfrequencyFrequency(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsElectricfrequencyFrequency> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsElectricfrequencyFrequency e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video resolution type.
     */
    public enum VideosettingsVideoresolutionType {

        /**
         * 16/9 VGA streaming (640 x 360).
         */
        VGA(0),

        /**
         * HD streaming (1280 x 720).
         */
        HD(1),

        /**
         * HQ streaming (864x480).
         */
        HQ(2);

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
        public static VideosettingsVideoresolutionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsVideoresolutionType(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsVideoresolutionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsVideoresolutionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the electric frequency.
     */
    public enum VideosettingsstateElectricfrequencychangedFrequency {

        /**
         * Electric frequency of the country is 50hz.
         */
        FIFTY_HERTZ(0),

        /**
         * Electric frequency of the country is 60hz.
         */
        SIXTY_HERTZ(1);

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
        public static VideosettingsstateElectricfrequencychangedFrequency fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsstateElectricfrequencychangedFrequency(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsstateElectricfrequencychangedFrequency> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsstateElectricfrequencychangedFrequency e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video resolution type.
     */
    public enum VideosettingsstateVideoresolutionchangedType {

        /**
         * 16/9 VGA streaming (640 x 360).
         */
        VGA(0),

        /**
         * HD streaming (1280 x 720).
         */
        HD(1),

        /**
         * HQ streaming (864x480).
         */
        HQ(2);

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
        public static VideosettingsstateVideoresolutionchangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private VideosettingsstateVideoresolutionchangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<VideosettingsstateVideoresolutionchangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (VideosettingsstateVideoresolutionchangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Command to record video.
     */
    public enum MinicamVideoRecord {

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
        public static MinicamVideoRecord fromValue(int value) {
            return MAP.get(value, null);
        }

        private MinicamVideoRecord(int value) {
            this.value = value;
        }

        private static final SparseArray<MinicamVideoRecord> MAP;

        static {
            MAP = new SparseArray<>();
            for (MinicamVideoRecord e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * All commands related to piloting the MiniDrone 
     */
    public static class Piloting {

        /** Feature uid. */
        public static final int UID = 0x0200;

        /**
         * Do a flat trim
         */
        public static ArsdkCommand encodeFlatTrim() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeFlatTrim(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Ask the drone to take off
         */
        public static ArsdkCommand encodeTakeOff() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeTakeOff(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Ask the drone to move around.
         * 
         * @param flag: Boolean flag to activate roll/pitch movement
         * @param roll: Roll consign for the MiniDrone [-100;100]
         * @param pitch: Pitch consign for the MiniDrone [-100;100]
         * @param yaw: Yaw consign for the MiniDrone [-100;100]
         * @param gaz: Gaz consign for the MiniDrone [-100;100]
         * @param timestamp: Timestamp in miliseconds. Not an absolute time. (Typically 0 = time of connexion).
         */
        public static ArsdkCommand encodePCMD(int flag, int roll, int pitch, int yaw, int gaz, long timestamp) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePCMD(cmd.getNativePtr(), flag, roll, pitch, yaw, gaz, timestamp);
            return cmd;
        }

        /**
         * Ask the MiniDrone to land
         */
        public static ArsdkCommand encodeLanding() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeLanding(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Put drone in emergency state
         */
        public static ArsdkCommand encodeEmergency() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeEmergency(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set MiniDrone automatic take off mode
         * 
         * @param state: State of automatic take off mode
         */
        public static ArsdkCommand encodeAutoTakeOffMode(int state) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutoTakeOffMode(cmd.getNativePtr(), state);
            return cmd;
        }

        /**
         * Set drone FlyingMode. Only supported by WingX
         * 
         * @param mode:
         */
        public static ArsdkCommand encodeFlyingMode(@NonNull PilotingFlyingmodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeFlyingMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        /**
         * Set Plane Gear Box. Only supported by WingX
         * 
         * @param state:
         */
        public static ArsdkCommand encodePlaneGearBox(@NonNull PilotingPlanegearboxState state) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePlaneGearBox(cmd.getNativePtr(), state.value);
            return cmd;
        }

        /**
         * Command to toggle between "easy" piloting mode and "preferred" piloting mode. This command only works while
         * the drone is flying.
         */
        public static ArsdkCommand encodeTogglePilotingMode() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeTogglePilotingMode(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeFlatTrim(long nativeCmd);
        private static native int nativeEncodeTakeOff(long nativeCmd);
        private static native int nativeEncodePCMD(long nativeCmd, int flag, int roll, int pitch, int yaw, int gaz, long timestamp);
        private static native int nativeEncodeLanding(long nativeCmd);
        private static native int nativeEncodeEmergency(long nativeCmd);
        private static native int nativeEncodeAutoTakeOffMode(long nativeCmd, int state);
        private static native int nativeEncodeFlyingMode(long nativeCmd, int mode);
        private static native int nativeEncodePlaneGearBox(long nativeCmd, int state);
        private static native int nativeEncodeTogglePilotingMode(long nativeCmd);
    }

    /**
     * Speed Settings commands 
     */
    public static class SpeedSettings {

        /** Feature uid. */
        public static final int UID = 0x0201;

        /**
         * Set Max Vertical speed
         * 
         * @param current: Current max vertical speed in m/s
         */
        public static ArsdkCommand encodeMaxVerticalSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxVerticalSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set Max Rotation speed
         * 
         * @param current: Current max rotation speed in degree/s
         */
        public static ArsdkCommand encodeMaxRotationSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxRotationSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Presence of wheels
         * 
         * @param present: 1 if present, 0 if not present
         */
        public static ArsdkCommand encodeWheels(int present) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWheels(cmd.getNativePtr(), present);
            return cmd;
        }

        /**
         * Set Max Horizontal speed (only used in case where PilotingSettings_MaxTilt is not used like in hydrofoil
         * mode)
         * 
         * @param current: Current max Horizontal speed in m/s
         */
        public static ArsdkCommand encodeMaxHorizontalSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxHorizontalSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set max plane mode rotation speed (only available for wing x)
         * 
         * @param current: Current max plane mode rotation speed in degree/s
         */
        public static ArsdkCommand encodeMaxPlaneModeRotationSpeed(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxPlaneModeRotationSpeed(cmd.getNativePtr(), current);
            return cmd;
        }

        private static native int nativeEncodeMaxVerticalSpeed(long nativeCmd, float current);
        private static native int nativeEncodeMaxRotationSpeed(long nativeCmd, float current);
        private static native int nativeEncodeWheels(long nativeCmd, int present);
        private static native int nativeEncodeMaxHorizontalSpeed(long nativeCmd, float current);
        private static native int nativeEncodeMaxPlaneModeRotationSpeed(long nativeCmd, float current);
    }

    /**
     * Events of media recording 
     */
    public static class MediaRecordEvent {

        /** Feature uid. */
        public static final int UID = 0x0202;

        /** Uid of PictureEventChanged event. */;
        public static final int PICTUREEVENTCHANGED_UID = 0x0000;

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
        }

        private static void pictureeventchanged(Callback cb, int event, int error) {
            ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedEvent enumEvent = ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedEvent.fromValue(event);
            if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedEvent value " + event);
            ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedError enumError = ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MediarecordeventPictureeventchangedError value " + error);
            try {
                cb.onPictureEventChanged(enumEvent, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MediaRecordEvent.PictureEventChanged [event: " + event + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Occasional information 
     */
    public static class PilotingState {

        /** Feature uid. */
        public static final int UID = 0x0203;

        /** Uid of FlatTrimChanged event. */;
        public static final int FLATTRIMCHANGED_UID = 0x0000;

        /** Uid of FlyingStateChanged event. */;
        public static final int FLYINGSTATECHANGED_UID = 0x0001;

        /** Uid of AlertStateChanged event. */;
        public static final int ALERTSTATECHANGED_UID = 0x0002;

        /** Uid of AutoTakeOffModeChanged event. */;
        public static final int AUTOTAKEOFFMODECHANGED_UID = 0x0003;

        /** Uid of FlyingModeChanged event. */;
        public static final int FLYINGMODECHANGED_UID = 0x0004;

        /** Uid of PlaneGearBoxChanged event. */;
        public static final int PLANEGEARBOXCHANGED_UID = 0x0005;

        /** Uid of PilotingModeChanged event. */;
        public static final int PILOTINGMODECHANGED_UID = 0x0006;

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
             * MiniDrone send flat trim was correctly processed
             */
            public default void onFlatTrimChanged() {}

            /**
             * Drone flying state changed
             * 
             * @param state:
             */
            public default void onFlyingStateChanged(@Nullable PilotingstateFlyingstatechangedState state) {}

            /**
             * Drone alert state changed
             * 
             * @param state:
             */
            public default void onAlertStateChanged(@Nullable PilotingstateAlertstatechangedState state) {}

            /**
             * Set MiniDrone automatic take off mode
             * 
             * @param state: State of automatic take off mode
             */
            public default void onAutoTakeOffModeChanged(int state) {}

            /**
             * FlyingMode changed. Only supported by WingX
             * 
             * @param mode:
             */
            public default void onFlyingModeChanged(@Nullable PilotingstateFlyingmodechangedMode mode) {}

            /**
             * Plane Gear Box changed. Only supported by WingX
             * 
             * @param state:
             */
            public default void onPlaneGearBoxChanged(@Nullable PilotingstatePlanegearboxchangedState state) {}

            /**
             * Event informing about the piloting mode.
             * 
             * @param mode:
             */
            public default void onPilotingModeChanged(@Nullable PilotingstatePilotingmodechangedMode mode) {}
        }

        private static void flattrimchanged(Callback cb) {
            try {
                cb.onFlatTrimChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.FlatTrimChanged", e);
            }
        }

        private static void flyingstatechanged(Callback cb, int state) {
            ArsdkFeatureMinidrone.PilotingstateFlyingstatechangedState enumState = ArsdkFeatureMinidrone.PilotingstateFlyingstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingstateFlyingstatechangedState value " + state);
            try {
                cb.onFlyingStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.FlyingStateChanged [state: " + state + "]", e);
            }
        }

        private static void alertstatechanged(Callback cb, int state) {
            ArsdkFeatureMinidrone.PilotingstateAlertstatechangedState enumState = ArsdkFeatureMinidrone.PilotingstateAlertstatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingstateAlertstatechangedState value " + state);
            try {
                cb.onAlertStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.AlertStateChanged [state: " + state + "]", e);
            }
        }

        private static void autotakeoffmodechanged(Callback cb, int state) {
            try {
                cb.onAutoTakeOffModeChanged(state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.AutoTakeOffModeChanged [state: " + state + "]", e);
            }
        }

        private static void flyingmodechanged(Callback cb, int mode) {
            ArsdkFeatureMinidrone.PilotingstateFlyingmodechangedMode enumMode = ArsdkFeatureMinidrone.PilotingstateFlyingmodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingstateFlyingmodechangedMode value " + mode);
            try {
                cb.onFlyingModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.FlyingModeChanged [mode: " + mode + "]", e);
            }
        }

        private static void planegearboxchanged(Callback cb, int state) {
            ArsdkFeatureMinidrone.PilotingstatePlanegearboxchangedState enumState = ArsdkFeatureMinidrone.PilotingstatePlanegearboxchangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingstatePlanegearboxchangedState value " + state);
            try {
                cb.onPlaneGearBoxChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.PlaneGearBoxChanged [state: " + state + "]", e);
            }
        }

        private static void pilotingmodechanged(Callback cb, int mode) {
            ArsdkFeatureMinidrone.PilotingstatePilotingmodechangedMode enumMode = ArsdkFeatureMinidrone.PilotingstatePilotingmodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingstatePilotingmodechangedMode value " + mode);
            try {
                cb.onPilotingModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingState.PilotingModeChanged [mode: " + mode + "]", e);
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
        public static final int UID = 0x0204;

        /**
         * Make a flip
         * 
         * @param direction:
         */
        public static ArsdkCommand encodeFlip(@NonNull AnimationsFlipDirection direction) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeFlip(cmd.getNativePtr(), direction.value);
            return cmd;
        }

        /**
         * Change the product cap
         * 
         * @param offset: Change the cap with offset angle [-180;180]
         */
        public static ArsdkCommand encodeCap(int offset) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCap(cmd.getNativePtr(), offset);
            return cmd;
        }

        private static native int nativeEncodeFlip(long nativeCmd, int direction);
        private static native int nativeEncodeCap(long nativeCmd, int offset);
    }

    /**
     * Speed Settings state from product 
     */
    public static class SpeedSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0205;

        /** Uid of MaxVerticalSpeedChanged event. */;
        public static final int MAXVERTICALSPEEDCHANGED_UID = 0x0000;

        /** Uid of MaxRotationSpeedChanged event. */;
        public static final int MAXROTATIONSPEEDCHANGED_UID = 0x0001;

        /** Uid of WheelsChanged event. */;
        public static final int WHEELSCHANGED_UID = 0x0002;

        /** Uid of MaxHorizontalSpeedChanged event. */;
        public static final int MAXHORIZONTALSPEEDCHANGED_UID = 0x0003;

        /** Uid of MaxPlaneModeRotationSpeedChanged event. */;
        public static final int MAXPLANEMODEROTATIONSPEEDCHANGED_UID = 0x0004;

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
             * Max vertical speed sent by product
             * 
             * @param current: Current max vertical speed in m/s
             * @param min: Range min of vertical speed
             * @param max: Range max of vertical speed
             */
            public default void onMaxVerticalSpeedChanged(float current, float min, float max) {}

            /**
             * Max rotation speed sent by product
             * 
             * @param current: Current max rotation speed in degree/s
             * @param min: Range min of rotation speed
             * @param max: Range max of rotation speed
             */
            public default void onMaxRotationSpeedChanged(float current, float min, float max) {}

            /**
             * Presence of wheels sent by product
             * 
             * @param present: 1 if present, 0 if not present
             */
            public default void onWheelsChanged(int present) {}

            /**
             * Max horizontal speed sent by product (only used in case where PilotingSettings_MaxTilt is not used like
             * in hydrofoil mode)
             * 
             * @param current: Current max horizontal speed in m/s
             * @param min: Range min of horizontal speed
             * @param max: Range max of horizontal speed
             */
            public default void onMaxHorizontalSpeedChanged(float current, float min, float max) {}

            /**
             * Max plane rotation speed sent by product (only available for wing x)
             * 
             * @param current: Current max plane mode rotation speed in degree/s
             * @param min: Range min of plane mode rotation speed
             * @param max: Range max of plane mode rotation speed
             */
            public default void onMaxPlaneModeRotationSpeedChanged(float current, float min, float max) {}
        }

        private static void maxverticalspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxVerticalSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SpeedSettingsState.MaxVerticalSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void maxrotationspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxRotationSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SpeedSettingsState.MaxRotationSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void wheelschanged(Callback cb, int present) {
            try {
                cb.onWheelsChanged(present);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SpeedSettingsState.WheelsChanged [present: " + present + "]", e);
            }
        }

        private static void maxhorizontalspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxHorizontalSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SpeedSettingsState.MaxHorizontalSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void maxplanemoderotationspeedchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxPlaneModeRotationSpeedChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SpeedSettingsState.MaxPlaneModeRotationSpeedChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
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
        public static final int UID = 0x0206;

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
         * Take picture
         */
        public static ArsdkCommand encodePictureV2() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePictureV2(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodePicture(long nativeCmd, int mass_storage_id);
        private static native int nativeEncodePictureV2(long nativeCmd);
    }

    /**
     * State of media recording 
     */
    public static class MediaRecordState {

        /** Feature uid. */
        public static final int UID = 0x0207;

        /** Uid of PictureStateChanged event. */;
        public static final int PICTURESTATECHANGED_UID = 0x0000;

        /** Uid of PictureStateChangedV2 event. */;
        public static final int PICTURESTATECHANGEDV2_UID = 0x0001;

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
             * @param massStorageId: Mass storage id to record
             */
            public default void onPictureStateChanged(int state, int massStorageId) {}

            /**
             * State of device picture recording changed
             * 
             * @param state:
             * @param error:
             */
            public default void onPictureStateChangedV2(@Nullable MediarecordstatePicturestatechangedv2State state, @Nullable MediarecordstatePicturestatechangedv2Error error) {}
        }

        private static void picturestatechanged(Callback cb, int state, int massStorageId) {
            try {
                cb.onPictureStateChanged(state, massStorageId);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MediaRecordState.PictureStateChanged [state: " + state + ", mass_storage_id: " + massStorageId + "]", e);
            }
        }

        private static void picturestatechangedv2(Callback cb, int state, int error) {
            ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2State enumState = ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2State value " + state);
            ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2Error enumError = ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2Error.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MediarecordstatePicturestatechangedv2Error value " + error);
            try {
                cb.onPictureStateChangedV2(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MediaRecordState.PictureStateChangedV2 [state: " + state + ", error: " + error + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Piloting Settings commands 
     */
    public static class PilotingSettings {

        /** Feature uid. */
        public static final int UID = 0x0208;

        /**
         * Set Max Altitude
         * 
         * @param current: Current altitude max in m
         */
        public static ArsdkCommand encodeMaxAltitude(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxAltitude(cmd.getNativePtr(), current);
            return cmd;
        }

        /**
         * Set Max Tilt
         * 
         * @param current: Current tilt max in degree
         */
        public static ArsdkCommand encodeMaxTilt(float current) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxTilt(cmd.getNativePtr(), current);
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
         * This setting represents the vertical acceleration command that will be sent to the drone when piloting in gaz
         * mode. In this case, closed loop height control is no longer performed; an open loop vertical acceleration
         * command is generated instead. This command results from multiplying the user command coming from the joystick
         * (float value between 0 and 1) by the MaxThrottle setting (also a value between 0 and 1) and by the gravity
         * constant. Thus, we obtain the corresponding value in [m/s^2] that will then be mixed with the attitude
         * commands and translated into rotation speeds. As an example, if the value of this setting is 0.5, the maximal
         * acceleration command that can be generated when the user command equals 1 is : acc_cmd_max = 1 * 0.5 * 9.81
         * m/s^2 = 4.905 m/s^2.
         * 
         * @param max: Max throttle, between 0 and 1.
         */
        public static ArsdkCommand encodeMaxThrottle(float max) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMaxThrottle(cmd.getNativePtr(), max);
            return cmd;
        }

        /**
         * The flight envelope of Mambo FPV has been divided in three piloting modes.
         * 
         * @param mode:
         */
        public static ArsdkCommand encodePreferredPilotingMode(@NonNull PilotingsettingsPreferredpilotingmodeMode mode) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePreferredPilotingMode(cmd.getNativePtr(), mode.value);
            return cmd;
        }

        private static native int nativeEncodeMaxAltitude(long nativeCmd, float current);
        private static native int nativeEncodeMaxTilt(long nativeCmd, float current);
        private static native int nativeEncodeBankedTurn(long nativeCmd, int value);
        private static native int nativeEncodeMaxThrottle(long nativeCmd, float max);
        private static native int nativeEncodePreferredPilotingMode(long nativeCmd, int mode);
    }

    /**
     * Piloting Settings state from product 
     */
    public static class PilotingSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0209;

        /** Uid of MaxAltitudeChanged event. */;
        public static final int MAXALTITUDECHANGED_UID = 0x0000;

        /** Uid of MaxTiltChanged event. */;
        public static final int MAXTILTCHANGED_UID = 0x0001;

        /** Uid of BankedTurnChanged event. */;
        public static final int BANKEDTURNCHANGED_UID = 0x0002;

        /** Uid of MaxThrottleChanged event. */;
        public static final int MAXTHROTTLECHANGED_UID = 0x0003;

        /** Uid of PreferredPilotingModeChanged event. */;
        public static final int PREFERREDPILOTINGMODECHANGED_UID = 0x0004;

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
             * Max Altitude sent by product
             * 
             * @param current: Current altitude max
             * @param min: Range min of altitude
             * @param max: Range max of altitude
             */
            public default void onMaxAltitudeChanged(float current, float min, float max) {}

            /**
             * Max tilt sent by product
             * 
             * @param current: Current max tilt
             * @param min: Range min of tilt
             * @param max: Range max of tilt
             */
            public default void onMaxTiltChanged(float current, float min, float max) {}

            /**
             * Banked Turn mode. If banked turn mode is enabled, the drone will use yaw values from the piloting command
             * to infer with roll and pitch on the drone when its horizontal speed is not null.
             * 
             * @param state: 1 if enabled, 0 if disabled
             */
            public default void onBankedTurnChanged(int state) {}

            /**
             * Event informing about the max throttle.
             * 
             * @param max: Max throttle, between 0 and 1.
             */
            public default void onMaxThrottleChanged(float max) {}

            /**
             * Event informing about the preferred piloting mode.
             * 
             * @param mode:
             */
            public default void onPreferredPilotingModeChanged(@Nullable PilotingsettingsstatePreferredpilotingmodechangedMode mode) {}
        }

        private static void maxaltitudechanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxAltitudeChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingSettingsState.MaxAltitudeChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void maxtiltchanged(Callback cb, float current, float min, float max) {
            try {
                cb.onMaxTiltChanged(current, min, max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingSettingsState.MaxTiltChanged [current: " + current + ", min: " + min + ", max: " + max + "]", e);
            }
        }

        private static void bankedturnchanged(Callback cb, int state) {
            try {
                cb.onBankedTurnChanged(state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingSettingsState.BankedTurnChanged [state: " + state + "]", e);
            }
        }

        private static void maxthrottlechanged(Callback cb, float max) {
            try {
                cb.onMaxThrottleChanged(max);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingSettingsState.MaxThrottleChanged [max: " + max + "]", e);
            }
        }

        private static void preferredpilotingmodechanged(Callback cb, int mode) {
            ArsdkFeatureMinidrone.PilotingsettingsstatePreferredpilotingmodechangedMode enumMode = ArsdkFeatureMinidrone.PilotingsettingsstatePreferredpilotingmodechangedMode.fromValue(mode);
            if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.PilotingsettingsstatePreferredpilotingmodechangedMode value " + mode);
            try {
                cb.onPreferredPilotingModeChanged(enumMode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.PilotingSettingsState.PreferredPilotingModeChanged [mode: " + mode + "]", e);
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
        public static final int UID = 0x020A;

        /**
         * Set MiniDrone cut out mode
         * 
         * @param enable: Enable cut out mode (1 if is activate, 0 otherwise)
         */
        public static ArsdkCommand encodeCutOutMode(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCutOutMode(cmd.getNativePtr(), enable);
            return cmd;
        }

        private static native int nativeEncodeCutOutMode(long nativeCmd, int enable);
    }

    /**
     * Settings state from product 
     */
    public static class SettingsState {

        /** Feature uid. */
        public static final int UID = 0x020B;

        /** Uid of ProductMotorsVersionChanged event. */;
        public static final int PRODUCTMOTORSVERSIONCHANGED_UID = 0x0000;

        /** Uid of ProductInertialVersionChanged event. */;
        public static final int PRODUCTINERTIALVERSIONCHANGED_UID = 0x0001;

        /** Uid of CutOutModeChanged event. */;
        public static final int CUTOUTMODECHANGED_UID = 0x0002;

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
             * @deprecated Product Motors versions
             * 
             * @param motor: Product Motor number [1 - 4]
             * @param type: Product Motor type
             * @param software: Product Motors software version
             * @param hardware: Product Motors hardware version
             */
            public default void onProductMotorsVersionChanged(int motor, String type, String software, String hardware) {}

            /**
             * @deprecated Product Inertial versions
             * 
             * @param software: Product Inertial software version
             * @param hardware: Product Inertial hardware version
             */
            public default void onProductInertialVersionChanged(String software, String hardware) {}

            /**
             * MiniDrone cut out mode
             * 
             * @param enable: State of cut out mode (1 if is activate, 0 otherwise)
             */
            public default void onCutOutModeChanged(int enable) {}
        }

        private static void productmotorsversionchanged(Callback cb, int motor, String type, String software, String hardware) {
            try {
                cb.onProductMotorsVersionChanged(motor, type, software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SettingsState.ProductMotorsVersionChanged [motor: " + motor + ", type: " + type + ", software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void productinertialversionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onProductInertialVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SettingsState.ProductInertialVersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void cutoutmodechanged(Callback cb, int enable) {
            try {
                cb.onCutOutModeChanged(enable);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.SettingsState.CutOutModeChanged [enable: " + enable + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Settings state from product 
     */
    public static class FloodControlState {

        /** Feature uid. */
        public static final int UID = 0x020C;

        /** Uid of FloodControlChanged event. */;
        public static final int FLOODCONTROLCHANGED_UID = 0x0000;

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
             * @deprecated Flood control regulation
             * 
             * @param delay: Delay (in ms) between two PCMD
             */
            public default void onFloodControlChanged(int delay) {}
        }

        private static void floodcontrolchanged(Callback cb, int delay) {
            try {
                cb.onFloodControlChanged(delay);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.FloodControlState.FloodControlChanged [delay: " + delay + "]", e);
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
        public static final int UID = 0x020D;

        /**
         * Set the controller latitude for a run.
         * 
         * @param latitude: Controller latitude in decimal degrees
         */
        public static ArsdkCommand encodeControllerLatitudeForRun(double latitude) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerLatitudeForRun(cmd.getNativePtr(), latitude);
            return cmd;
        }

        /**
         * Set the controller longitude for a run.
         * 
         * @param longitude: Controller longitude in decimal degrees
         */
        public static ArsdkCommand encodeControllerLongitudeForRun(double longitude) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerLongitudeForRun(cmd.getNativePtr(), longitude);
            return cmd;
        }

        private static native int nativeEncodeControllerLatitudeForRun(long nativeCmd, double latitude);
        private static native int nativeEncodeControllerLongitudeForRun(long nativeCmd, double longitude);
    }

    /**
     * Configuration related commands 
     */
    public static class Configuration {

        /** Feature uid. */
        public static final int UID = 0x020E;

        /**
         * Set the controller type.
         * 
         * @param type: Controller type like iOS or Android
         */
        public static ArsdkCommand encodeControllerType(String type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerType(cmd.getNativePtr(), type);
            return cmd;
        }

        /**
         * Set the controller name.
         * 
         * @param name: Controller name like com.parrot.freeflight3
         */
        public static ArsdkCommand encodeControllerName(String name) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeControllerName(cmd.getNativePtr(), name);
            return cmd;
        }

        private static native int nativeEncodeControllerType(long nativeCmd, String type);
        private static native int nativeEncodeControllerName(long nativeCmd, String name);
    }

    /**
     * USB Accessories state commands. 
     */
    public static class UsbAccessoryState {

        /** Feature uid. */
        public static final int UID = 0x020F;

        /** Uid of LightState event. */;
        public static final int LIGHTSTATE_UID = 0x0000;

        /** Uid of ClawState event. */;
        public static final int CLAWSTATE_UID = 0x0001;

        /** Uid of GunState event. */;
        public static final int GUNSTATE_UID = 0x0002;

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
             * USB Light accessory state cmd.
             * 
             * @param id: Usb accessory id
             * @param state:
             * @param intensity: Light intensity from 0 (OFF) to 100 (Max intensity). Only used in FIXED state.
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onLightState(int id, @Nullable UsbaccessorystateLightstateState state, int intensity, int listFlags) {}

            /**
             * USB Claw accessory state cmd.
             * 
             * @param id: Usb accessory id
             * @param state:
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onClawState(int id, @Nullable UsbaccessorystateClawstateState state, int listFlags) {}

            /**
             * USB Gun accessory state cmd.
             * 
             * @param id: Usb accessory id.
             * @param state:
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onGunState(int id, @Nullable UsbaccessorystateGunstateState state, int listFlags) {}
        }

        private static void lightstate(Callback cb, int id, int state, int intensity, int listFlags) {
            ArsdkFeatureMinidrone.UsbaccessorystateLightstateState enumState = ArsdkFeatureMinidrone.UsbaccessorystateLightstateState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.UsbaccessorystateLightstateState value " + state);
            try {
                cb.onLightState(id, enumState, intensity, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.UsbAccessoryState.LightState [id: " + id + ", state: " + state + ", intensity: " + intensity + ", list_flags: " + listFlags + "]", e);
            }
        }

        private static void clawstate(Callback cb, int id, int state, int listFlags) {
            ArsdkFeatureMinidrone.UsbaccessorystateClawstateState enumState = ArsdkFeatureMinidrone.UsbaccessorystateClawstateState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.UsbaccessorystateClawstateState value " + state);
            try {
                cb.onClawState(id, enumState, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.UsbAccessoryState.ClawState [id: " + id + ", state: " + state + ", list_flags: " + listFlags + "]", e);
            }
        }

        private static void gunstate(Callback cb, int id, int state, int listFlags) {
            ArsdkFeatureMinidrone.UsbaccessorystateGunstateState enumState = ArsdkFeatureMinidrone.UsbaccessorystateGunstateState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.UsbaccessorystateGunstateState value " + state);
            try {
                cb.onGunState(id, enumState, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.UsbAccessoryState.GunState [id: " + id + ", state: " + state + ", list_flags: " + listFlags + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * USB Accessories control commands. 
     */
    public static class UsbAccessory {

        /** Feature uid. */
        public static final int UID = 0x0210;

        /**
         * USB Light control cmd.
         * 
         * @param id: Usb accessory id
         * @param mode:
         * @param intensity: Light intensity from 0 (OFF) to 100 (Max intensity). Only used in FIXED mode.
         */
        public static ArsdkCommand encodeLightControl(int id, @NonNull UsbaccessoryLightcontrolMode mode, int intensity) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeLightControl(cmd.getNativePtr(), id, mode.value, intensity);
            return cmd;
        }

        /**
         * USB Claw control cmd.
         * 
         * @param id: Usb accessory id.
         * @param action:
         */
        public static ArsdkCommand encodeClawControl(int id, @NonNull UsbaccessoryClawcontrolAction action) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeClawControl(cmd.getNativePtr(), id, action.value);
            return cmd;
        }

        /**
         * USB Gun control cmd.
         * 
         * @param id: Usb accessory id
         * @param action:
         */
        public static ArsdkCommand encodeGunControl(int id, @NonNull UsbaccessoryGuncontrolAction action) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGunControl(cmd.getNativePtr(), id, action.value);
            return cmd;
        }

        private static native int nativeEncodeLightControl(long nativeCmd, int id, int mode, int intensity);
        private static native int nativeEncodeClawControl(long nativeCmd, int id, int action);
        private static native int nativeEncodeGunControl(long nativeCmd, int id, int action);
    }

    /**
     * Remote controller related commands. 
     */
    public static class RemoteController {

        /** Feature uid. */
        public static final int UID = 0x0211;

        /**
         * Send the address of the remote controller on which the drone should be paired This is used to pair a Tinos
         * controller Where mac address: MSB-MID-LSB.
         * 
         * @param msbMac: 2 most significant bytes of mac address
         * @param midMac: 2 middle bytes of mac address
         * @param lsbMac: 2 least significant bytes of mac address
         */
        public static ArsdkCommand encodeSetPairedRemote(int msbMac, int midMac, int lsbMac) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetPairedRemote(cmd.getNativePtr(), msbMac, midMac, lsbMac);
            return cmd;
        }

        /**
         * Enter/leave RC raw mode.  In raw mode, unfiltered RC commands are sent directly to the controller, and are
         * not processed by the drone.  This is useful if you need to access the joysticks and buttons state, in the RC
         * mapping settings screen for instance.
         * 
         * @param enabled: 1 to enable, 0 to disable.
         */
        public static ArsdkCommand encodeRawMode(int enabled) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeRawMode(cmd.getNativePtr(), enabled);
            return cmd;
        }

        private static native int nativeEncodeSetPairedRemote(long nativeCmd, int msb_mac, int mid_mac, int lsb_mac);
        private static native int nativeEncodeRawMode(long nativeCmd, int enabled);
    }

    /**
     * Navigation Data. 
     */
    public static class NavigationDataState {

        /** Feature uid. */
        public static final int UID = 0x0212;

        /** Uid of DronePosition event. */;
        public static final int DRONEPOSITION_UID = 0x0000;

        /** Uid of DroneSpeed event. */;
        public static final int DRONESPEED_UID = 0x0001;

        /** Uid of DroneAltitude event. */;
        public static final int DRONEALTITUDE_UID = 0x0002;

        /** Uid of DroneQuaternion event. */;
        public static final int DRONEQUATERNION_UID = 0x0004;

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
             * Get the drone position from takeoff point (0, 0, 0, 0). The orthonormal basis is fixed at this point. The
             * axis are oriented the following way : * X : From the rear of the drone to its front. * Y : From the right
             * of the drone to its left. * Z : Orthogonal to X and Y and oriented upward. * Psi : From 180 to -180 in
             * the clockwise direction,
             * 
             * @param posx: Position on X axis, relative to take off position (cm).
             * @param posy: Position on Y axis, relative to take off position (cm).
             * @param posz: Position on Z axis, relative to take off position (cm).
             * @param psi: Psi angle [-180; 180], relative to take off orientation.
             * @param ts: Time elapsed since last data send (ms).
             */
            public default void onDronePosition(float posx, float posy, int posz, int psi, int ts) {}

            /**
             * Event informing about the estimated drone speed in horizontal frame. It is similar to NED frame but with
             * drone heading. Down speed is positive when the drone is going down. Speed is in m/s.
             * 
             * @param speedX: Speed on the x axis (when drone moves forward, speed is &gt; 0).
             * @param speedY: Speed on the y axis (when drone moves right, speed is &gt; 0).
             * @param speedZ: Speed on the z axis (when drone moves down, speed is &gt; 0).
             * @param ts: Acquisition timestamp (ms).
             */
            public default void onDroneSpeed(float speedX, float speedY, float speedZ, int ts) {}

            /**
             * Event informing about the estimated altitude above takeoff level.
             * 
             * @param altitude: Altitude in meters.
             * @param ts: Acquisition timestamp (ms).
             */
            public default void onDroneAltitude(float altitude, int ts) {}

            /**
             * Event informing about the estimated quaternion. They represent the rotation from the NED frame
             * (determined at drone startup) to the estimated drone body frame. Its elements are between -1 and 1.
             * 
             * @param qW: Element w.
             * @param qX: Element x.
             * @param qY: Element y.
             * @param qZ: Element z.
             * @param ts: Acquisition timestamp (ms).
             */
            public default void onDroneQuaternion(float qW, float qX, float qY, float qZ, int ts) {}
        }

        private static void droneposition(Callback cb, float posx, float posy, int posz, int psi, int ts) {
            try {
                cb.onDronePosition(posx, posy, posz, psi, ts);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.NavigationDataState.DronePosition [posx: " + posx + ", posy: " + posy + ", posz: " + posz + ", psi: " + psi + ", ts: " + ts + "]", e);
            }
        }

        private static void dronespeed(Callback cb, float speedX, float speedY, float speedZ, int ts) {
            try {
                cb.onDroneSpeed(speedX, speedY, speedZ, ts);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.NavigationDataState.DroneSpeed [speed_x: " + speedX + ", speed_y: " + speedY + ", speed_z: " + speedZ + ", ts: " + ts + "]", e);
            }
        }

        private static void dronealtitude(Callback cb, float altitude, int ts) {
            try {
                cb.onDroneAltitude(altitude, ts);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.NavigationDataState.DroneAltitude [altitude: " + altitude + ", ts: " + ts + "]", e);
            }
        }

        private static void dronequaternion(Callback cb, float qW, float qX, float qY, float qZ, int ts) {
            try {
                cb.onDroneQuaternion(qW, qX, qY, qZ, ts);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.NavigationDataState.DroneQuaternion [q_w: " + qW + ", q_x: " + qX + ", q_y: " + qY + ", q_z: " + qZ + ", ts: " + ts + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Minicam related events. 
     */
    public static class MinicamState {

        /** Feature uid. */
        public static final int UID = 0x0213;

        /** Uid of PowerModeChanged event. */;
        public static final int POWERMODECHANGED_UID = 0x0000;

        /** Uid of ProductSerialChanged event. */;
        public static final int PRODUCTSERIALCHANGED_UID = 0x0001;

        /** Uid of StateChanged event. */;
        public static final int STATECHANGED_UID = 0x0002;

        /** Uid of VersionChanged event. */;
        public static final int VERSIONCHANGED_UID = 0x0003;

        /** Uid of PictureChanged event. */;
        public static final int PICTURECHANGED_UID = 0x0004;

        /** Uid of VideoStateChanged event. */;
        public static final int VIDEOSTATECHANGED_UID = 0x0005;

        /** Uid of MassStorageFormatChanged event. */;
        public static final int MASSSTORAGEFORMATCHANGED_UID = 0x0006;

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
             * Event informing about the minicam power mode.
             * 
             * @param powerMode:
             */
            public default void onPowerModeChanged(@Nullable MinicamstatePowermodechangedPowerMode powerMode) {}

            /**
             * Event informing about the minicam product serial number.
             * 
             * @param serialNumber: Serial number.
             */
            public default void onProductSerialChanged(String serialNumber) {}

            /**
             * Event informing about the state of the camera.
             * 
             * @param state:
             */
            public default void onStateChanged(@Nullable MinicamstateStatechangedState state) {}

            /**
             * Get the accessory Version.
             * 
             * @param software: Accessory software version.
             * @param hardware: Accessory hardware version.
             */
            public default void onVersionChanged(String software, String hardware) {}

            /**
             * Event informing that the picture has been taken.
             * 
             * @param state:
             * @param result:
             */
            public default void onPictureChanged(@Nullable MinicamstatePicturechangedState state, @Nullable MinicamstatePicturechangedResult result) {}

            /**
             * Event informing about the video recording state.
             * 
             * @param state:
             * @param error:
             */
            public default void onVideoStateChanged(@Nullable MinicamstateVideostatechangedState state, @Nullable MinicamstateVideostatechangedError error) {}

            /**
             * Event informing that the mass storage has been formatted.
             * 
             * @param state: 1 if Mass Storage has been formatted, 0 otherwise.
             */
            public default void onMassStorageFormatChanged(int state) {}
        }

        private static void powermodechanged(Callback cb, int powerMode) {
            ArsdkFeatureMinidrone.MinicamstatePowermodechangedPowerMode enumPowermode = ArsdkFeatureMinidrone.MinicamstatePowermodechangedPowerMode.fromValue(powerMode);
            if (enumPowermode == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstatePowermodechangedPowerMode value " + powerMode);
            try {
                cb.onPowerModeChanged(enumPowermode);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.PowerModeChanged [power_mode: " + powerMode + "]", e);
            }
        }

        private static void productserialchanged(Callback cb, String serialNumber) {
            try {
                cb.onProductSerialChanged(serialNumber);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.ProductSerialChanged [serial_number: " + serialNumber + "]", e);
            }
        }

        private static void statechanged(Callback cb, int state) {
            ArsdkFeatureMinidrone.MinicamstateStatechangedState enumState = ArsdkFeatureMinidrone.MinicamstateStatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstateStatechangedState value " + state);
            try {
                cb.onStateChanged(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.StateChanged [state: " + state + "]", e);
            }
        }

        private static void versionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.VersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void picturechanged(Callback cb, int state, int result) {
            ArsdkFeatureMinidrone.MinicamstatePicturechangedState enumState = ArsdkFeatureMinidrone.MinicamstatePicturechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstatePicturechangedState value " + state);
            ArsdkFeatureMinidrone.MinicamstatePicturechangedResult enumResult = ArsdkFeatureMinidrone.MinicamstatePicturechangedResult.fromValue(result);
            if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstatePicturechangedResult value " + result);
            try {
                cb.onPictureChanged(enumState, enumResult);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.PictureChanged [state: " + state + ", result: " + result + "]", e);
            }
        }

        private static void videostatechanged(Callback cb, int state, int error) {
            ArsdkFeatureMinidrone.MinicamstateVideostatechangedState enumState = ArsdkFeatureMinidrone.MinicamstateVideostatechangedState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstateVideostatechangedState value " + state);
            ArsdkFeatureMinidrone.MinicamstateVideostatechangedError enumError = ArsdkFeatureMinidrone.MinicamstateVideostatechangedError.fromValue(error);
            if (enumError == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.MinicamstateVideostatechangedError value " + error);
            try {
                cb.onVideoStateChanged(enumState, enumError);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.VideoStateChanged [state: " + state + ", error: " + error + "]", e);
            }
        }

        private static void massstorageformatchanged(Callback cb, int state) {
            try {
                cb.onMassStorageFormatChanged(state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.MinicamState.MassStorageFormatChanged [state: " + state + "]", e);
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
        public static final int UID = 0x0214;

        /**
         * Set video automatic recording state.
         * 
         * @param enable: 0: disabled 1: enabled
         */
        public static ArsdkCommand encodeAutorecord(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAutorecord(cmd.getNativePtr(), enable);
            return cmd;
        }

        /**
         * Set the electric frequency (Anti-flickering).
         * 
         * @param frequency:
         */
        public static ArsdkCommand encodeElectricFrequency(@NonNull VideosettingsElectricfrequencyFrequency frequency) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeElectricFrequency(cmd.getNativePtr(), frequency.value);
            return cmd;
        }

        /**
         * Set video streaming and recording resolution.
         * 
         * @param type:
         */
        public static ArsdkCommand encodeVideoResolution(@NonNull VideosettingsVideoresolutionType type) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideoResolution(cmd.getNativePtr(), type.value);
            return cmd;
        }

        private static native int nativeEncodeAutorecord(long nativeCmd, int enable);
        private static native int nativeEncodeElectricFrequency(long nativeCmd, int frequency);
        private static native int nativeEncodeVideoResolution(long nativeCmd, int type);
    }

    /**
     * Video settings state. 
     */
    public static class VideoSettingsState {

        /** Feature uid. */
        public static final int UID = 0x0215;

        /** Uid of AutorecordChanged event. */;
        public static final int AUTORECORDCHANGED_UID = 0x0000;

        /** Uid of ElectricFrequencyChanged event. */;
        public static final int ELECTRICFREQUENCYCHANGED_UID = 0x0001;

        /** Uid of VideoResolutionChanged event. */;
        public static final int VIDEORESOLUTIONCHANGED_UID = 0x0002;

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
             * Event informing about the video automatic recording status.
             * 
             * @param enabled: 0: disabled 1: enabled
             */
            public default void onAutorecordChanged(int enabled) {}

            /**
             * Event informing about the electric frequency (Anti-flickering).
             * 
             * @param frequency:
             */
            public default void onElectricFrequencyChanged(@Nullable VideosettingsstateElectricfrequencychangedFrequency frequency) {}

            /**
             * Event informing about the streaming resolution.
             * 
             * @param type:
             */
            public default void onVideoResolutionChanged(@Nullable VideosettingsstateVideoresolutionchangedType type) {}
        }

        private static void autorecordchanged(Callback cb, int enabled) {
            try {
                cb.onAutorecordChanged(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.VideoSettingsState.AutorecordChanged [enabled: " + enabled + "]", e);
            }
        }

        private static void electricfrequencychanged(Callback cb, int frequency) {
            ArsdkFeatureMinidrone.VideosettingsstateElectricfrequencychangedFrequency enumFrequency = ArsdkFeatureMinidrone.VideosettingsstateElectricfrequencychangedFrequency.fromValue(frequency);
            if (enumFrequency == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.VideosettingsstateElectricfrequencychangedFrequency value " + frequency);
            try {
                cb.onElectricFrequencyChanged(enumFrequency);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.VideoSettingsState.ElectricFrequencyChanged [frequency: " + frequency + "]", e);
            }
        }

        private static void videoresolutionchanged(Callback cb, int type) {
            ArsdkFeatureMinidrone.VideosettingsstateVideoresolutionchangedType enumType = ArsdkFeatureMinidrone.VideosettingsstateVideoresolutionchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureMinidrone.VideosettingsstateVideoresolutionchangedType value " + type);
            try {
                cb.onVideoResolutionChanged(enumType);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.VideoSettingsState.VideoResolutionChanged [type: " + type + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Minicam related commands. 
     */
    public static class Minicam {

        /** Feature uid. */
        public static final int UID = 0x0218;

        /**
         * Take picture.
         */
        public static ArsdkCommand encodePicture() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodePicture(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Start/Stop video recording.
         * 
         * @param record:
         */
        public static ArsdkCommand encodeVideo(@NonNull MinicamVideoRecord record) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeVideo(cmd.getNativePtr(), record.value);
            return cmd;
        }

        /**
         * Format mass storage.
         */
        public static ArsdkCommand encodeMassStorageFormat() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeMassStorageFormat(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodePicture(long nativeCmd);
        private static native int nativeEncodeVideo(long nativeCmd, int record);
        private static native int nativeEncodeMassStorageFormat(long nativeCmd);
    }

    /**
     * State changes related to the remote controller. 
     */
    public static class RemoteControllerState {

        /** Feature uid. */
        public static final int UID = 0x0219;

        /** Uid of ConnectionChanged event. */;
        public static final int CONNECTIONCHANGED_UID = 0x0000;

        /** Uid of RecordButtonPressed event. */;
        public static final int RECORDBUTTONPRESSED_UID = 0x0001;

        /** Uid of TakePictureButtonPressed event. */;
        public static final int TAKEPICTUREBUTTONPRESSED_UID = 0x0002;

        /** Uid of RawMode event. */;
        public static final int RAWMODE_UID = 0x0003;

        /** Uid of RawCommands event. */;
        public static final int RAWCOMMANDS_UID = 0x0004;

        /** Uid of BatteryLevel event. */;
        public static final int BATTERYLEVEL_UID = 0x0005;

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
             * State of the connection to the remote controller changed.
             * 
             * @param state: New connection state. 0=disconnected 1=connected
             */
            public default void onConnectionChanged(int state) {}

            /**
             * Notify the controller that the record button was pressed on the remote controller.
             */
            public default void onRecordButtonPressed() {}

            /**
             * Notify the controller that the take picture button was pressed on the remote controller.
             */
            public default void onTakePictureButtonPressed() {}

            /**
             * Notify the controller on raw mode state change.  In raw mode, unfiltered RC commands are sent directly to
             * the controller, and are not processed by the drone.  This is useful if you need to access the joysticks
             * and buttons state, in the RC mapping settings screen for instance.
             * 
             * @param enabled: 1 if raw mode is enabled, 0 if it is not.
             */
            public default void onRawMode(int enabled) {}

            /**
             * Raw RC commands data. Updated when a change is detected.
             * 
             * @param joy0x: Stick 0 X axis value between -127 and 127. 0 is neutral.
             * @param joy0y: Stick 0 Y axis value between -127 and 127. 0 is neutral.
             * @param joy1x: Stick 1 X axis value between -127 and 127. 0 is neutral.
             * @param joy1y: Stick 1 Y axis value between -127 and 127. 0 is neutral.
             * @param buttons: Bitfield reflecting the state of all the buttons. 0 means that the button is released, 1
             * if it is pushed. bit 0: Take off bit 1: 1 button bit 2: 2 button bit 3: B button bit 4: A button bit 5:
             * R1 button bit 6: R2 button bit 7: L1 button bit 8: L2 button bit 9: Left joystick bit 10: Right joystick
             */
            public default void onRawCommands(int joy0x, int joy0y, int joy1x, int joy1y, int buttons) {}

            /**
             * Battery level data. Updated as soon as it changes.
             * 
             * @param level: Battery level in percent. Special value -1 when the battery level is not known yet.
             */
            public default void onBatteryLevel(int level) {}
        }

        private static void connectionchanged(Callback cb, int state) {
            try {
                cb.onConnectionChanged(state);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.ConnectionChanged [state: " + state + "]", e);
            }
        }

        private static void recordbuttonpressed(Callback cb) {
            try {
                cb.onRecordButtonPressed();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.RecordButtonPressed", e);
            }
        }

        private static void takepicturebuttonpressed(Callback cb) {
            try {
                cb.onTakePictureButtonPressed();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.TakePictureButtonPressed", e);
            }
        }

        private static void rawmode(Callback cb, int enabled) {
            try {
                cb.onRawMode(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.RawMode [enabled: " + enabled + "]", e);
            }
        }

        private static void rawcommands(Callback cb, int joy0x, int joy0y, int joy1x, int joy1y, int buttons) {
            try {
                cb.onRawCommands(joy0x, joy0y, joy1x, joy1y, buttons);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.RawCommands [joy0x: " + joy0x + ", joy0y: " + joy0y + ", joy1x: " + joy1x + ", joy1y: " + joy1y + ", buttons: " + buttons + "]", e);
            }
        }

        private static void batterylevel(Callback cb, int level) {
            try {
                cb.onBatteryLevel(level);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: minidrone.RemoteControllerState.BatteryLevel [level: " + level + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
