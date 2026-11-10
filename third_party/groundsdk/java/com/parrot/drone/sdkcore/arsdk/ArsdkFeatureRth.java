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
 * Rth feature command/event interface.
 */
public class ArsdkFeatureRth {

    /**
     * Home type.
     */
    public enum HomeType {

        /**
         * No home type. This might be because the drone does not have a gps fix.
         */
        NONE(0),

        /**
         * The drone will return to the last manual takeoff location.
         */
        TAKEOFF(1),

        /**
         * The drone will return to the followee position.
         */
        FOLLOWEE(2),

        /**
         * The drone will return to a user-set custom location.
         */
        CUSTOM(3),

        /**
         * The drone will return to the pilot position.
         */
        PILOT(4);

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
        public static HomeType fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<HomeType> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported HomeType bitfield value " + value);
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
        public static EnumSet<HomeType> fromBitfield(int bitfield) {
            EnumSet<HomeType> enums = EnumSet.noneOf(HomeType.class);
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
        public static int toBitField(@NonNull HomeType... enums) {
            int bitField = 0;
            for (HomeType e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private HomeType(int value) {
            this.value = value;
        }

        private static final SparseArray<HomeType> MAP;

        static {
            MAP = new SparseArray<>();
            for (HomeType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of return to home.
     */
    public enum State {

        /**
         * Return to home is available.
         */
        AVAILABLE(0),

        /**
         * Return to home is in progress.
         */
        IN_PROGRESS(1),

        /**
         * Return to home is not available.
         */
        UNAVAILABLE(2),

        /**
         * Return to home has been received, but its process is pending.
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
     * Reason of the state.
     */
    public enum StateReason {

        /**
         * User requested a return to home (available-&gt;in_progress).
         */
        USER_REQUEST(0),

        /**
         * Connection between controller and product lost (available-&gt;in_progress).
         */
        CONNECTION_LOST(1),

        /**
         * Low battery occurred (available-&gt;in_progress).
         */
        LOW_BATTERY(2),

        /**
         * Return to home is finished (in_progress-&gt;available).
         */
        FINISHED(3),

        /**
         * Return to home has been stopped (in_progress-&gt;available).
         */
        STOPPED(4),

        /**
         * Return to home disabled by product (in_progress-&gt;unavailable or available-&gt;unavailable).
         */
        DISABLED(5),

        /**
         * Return to home enabled by product (unavailable-&gt;available).
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
        public static StateReason fromValue(int value) {
            return MAP.get(value, null);
        }

        private StateReason(int value) {
            this.value = value;
        }

        private static final SparseArray<StateReason> MAP;

        static {
            MAP = new SparseArray<>();
            for (StateReason e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Home reachability.
     */
    public enum HomeReachability {

        /**
         * Home reachability is unknown.
         */
        UNKNOWN(0),

        /**
         * Home is reachable.
         */
        REACHABLE(1),

        /**
         * Home is still reachable but won't be if rth is not triggered now. If rth is running, cancelling it will
         * probably make the home not reachable.
         */
        CRITICAL(2),

        /**
         * Home is not reachable.
         */
        NOT_REACHABLE(3);

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
        public static HomeReachability fromValue(int value) {
            return MAP.get(value, null);
        }

        private HomeReachability(int value) {
            this.value = value;
        }

        private static final SparseArray<HomeReachability> MAP;

        static {
            MAP = new SparseArray<>();
            for (HomeReachability e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * RTH auto-trigger reason.
     */
    public enum AutoTriggerReason {

        /**
         * There is no return home auto trigger planned.
         */
        NONE(0),

        /**
         * Battery will soon be critical.
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
        public static AutoTriggerReason fromValue(int value) {
            return MAP.get(value, null);
        }

        private AutoTriggerReason(int value) {
            this.value = value;
        }

        private static final SparseArray<AutoTriggerReason> MAP;

        static {
            MAP = new SparseArray<>();
            for (AutoTriggerReason e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * RTH auto-trigger mode.
     */
    public enum AutoTriggerMode {

        /**
         * Auto trigger is off. RTH auto trigger will never occur.
         */
        OFF(0),

        /**
         * Auto trigger is on.
         */
        ON(1);

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
        public static AutoTriggerMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private AutoTriggerMode(int value) {
            this.value = value;
        }

        private static final SparseArray<AutoTriggerMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (AutoTriggerMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * RTH ending behavior action.
     */
    public enum EndingBehavior {

        /**
         * The RTH end behavior is landing.
         */
        LANDING(0),

        /**
         * The RTH end behavior is hovering.
         */
        HOVERING(1);

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
        public static EndingBehavior fromValue(int value) {
            return MAP.get(value, null);
        }

        private EndingBehavior(int value) {
            this.value = value;
        }

        private static final SparseArray<EndingBehavior> MAP;

        static {
            MAP = new SparseArray<>();
            for (EndingBehavior e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9200;

    /** Uid of home_type_capabilities event. */;
    public static final int HOME_TYPE_CAPABILITIES_UID = 0x0001;

    /** Uid of home_type event. */;
    public static final int HOME_TYPE_UID = 0x0002;

    /** Uid of preferred_home_type event. */;
    public static final int PREFERRED_HOME_TYPE_UID = 0x0003;

    /** Uid of takeoff_location event. */;
    public static final int TAKEOFF_LOCATION_UID = 0x0005;

    /** Uid of custom_location event. */;
    public static final int CUSTOM_LOCATION_UID = 0x0007;

    /** Uid of followee_location event. */;
    public static final int FOLLOWEE_LOCATION_UID = 0x0008;

    /** Uid of delay event. */;
    public static final int DELAY_UID = 0x000A;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x000D;

    /** Uid of home_reachability event. */;
    public static final int HOME_REACHABILITY_UID = 0x000E;

    /** Uid of rth_auto_trigger event. */;
    public static final int RTH_AUTO_TRIGGER_UID = 0x000F;

    /** Uid of min_altitude event. */;
    public static final int MIN_ALTITUDE_UID = 0x0012;

    /** Uid of auto_trigger_mode event. */;
    public static final int AUTO_TRIGGER_MODE_UID = 0x0014;

    /** Uid of ending_behavior event. */;
    public static final int ENDING_BEHAVIOR_UID = 0x0016;

    /** Uid of ending_hovering_altitude event. */;
    public static final int ENDING_HOVERING_ALTITUDE_UID = 0x0018;

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
         * @param valuesBitField: Bitfield of supported home types.
         */
        public default void onHomeTypeCapabilities(int valuesBitField) {}

        /**

         * 
         * @param type: Home type.
         */
        public default void onHomeType(@Nullable HomeType type) {}

        /**
         * Preferred home type. Please note that this is only a user preference. The actual type chosen is given by the
         * event [home_type](#146-2).
         * 
         * @param type: Preferred Home type.
         */
        public default void onPreferredHomeType(@Nullable HomeType type) {}

        /**

         * 
         * @param latitude: Latitude of the takeoff location
         * @param longitude: Longitude of the takeoff location
         * @param altitude: Altitude of the custom location above takeoff (ATO).
         * @param fixedBeforeTakeoff: 1 if the location was acquired before the takeoff. 0 if it was acquired during the
         * flight (i.e. is it the first fix location).
         */
        public default void onTakeoffLocation(double latitude, double longitude, float altitude, int fixedBeforeTakeoff) {}

        /**

         * 
         * @param latitude: Latitude of the custom location
         * @param longitude: Longitude of the custom location
         * @param altitude: Altitude of the custom location above takeoff (ATO).
         */
        public default void onCustomLocation(double latitude, double longitude, float altitude) {}

        /**

         * 
         * @param latitude: Latitude of the takeoff location
         * @param longitude: Longitude of the takeoff location
         * @param altitude: Altitude of the custom location above takeoff (ATO).
         */
        public default void onFolloweeLocation(double latitude, double longitude, float altitude) {}

        /**
         * Return home trigger delay. This delay represents the time after which the return home is automatically
         * triggered after a disconnection.
         * 
         * @param delay: Delay in second
         * @param min: Min delay in second
         * @param max: Max delay in second
         */
        public default void onDelay(int delay, int min, int max) {}

        /**
         * Return home state. Availability is related to gps fix, magnetometer calibration.
         * 
         * @param state: State of the return to home
         * @param reason: Reason of the state change
         */
        public default void onState(@Nullable State state, @Nullable StateReason reason) {}

        /**
         * Home reachability status.
         * 
         * @param status: State of the return to home
         */
        public default void onHomeReachability(@Nullable HomeReachability status) {}

        /**
         * Return Home auto trigger information.
         * 
         * @param reason: Reason of the auto trigger.
         * @param delay: Delay until the return home is automatically triggered by the drone, in seconds. If reason is
         * `none` this information has no meaning.
         */
        public default void onRthAutoTrigger(@Nullable AutoTriggerReason reason, long delay) {}

        /**
         * This altitude represents the minimum altitude used by the drone during the return home.
         * 
         * @param current: Minimum altitude used by the drone during RTH. This value is above takeoff (ATO)
         * @param min: Range min of altitude
         * @param max: Range max of altitude
         */
        public default void onMinAltitude(float current, float min, float max) {}

        /**

         * 
         * @param mode: RTH auto trigger mode.
         */
        public default void onAutoTriggerMode(@Nullable AutoTriggerMode mode) {}

        /**

         * 
         * @param endingBehavior: Ending behavior action
         */
        public default void onEndingBehavior(@Nullable EndingBehavior endingBehavior) {}

        /**
         * This altitude represents the altitude for the hovering at the end of rth. It is only used when
         * ending_behavior is set to hovering.
         * 
         * @param current: Altitude used by the drone when hovering at the end of return home. This end altitude is AGL
         * (above ground level).
         * @param min: Range min of altitude
         * @param max: Range max of altitude
         */
        public default void onEndingHoveringAltitude(float current, float min, float max) {}
    }

    private static void homeTypeCapabilities(Callback cb, int valuesBitField) {
        try {
            cb.onHomeTypeCapabilities(valuesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.home_type_capabilities [values: " + valuesBitField + "]", e);
        }
    }

    private static void homeType(Callback cb, int type) {
        ArsdkFeatureRth.HomeType enumType = ArsdkFeatureRth.HomeType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.HomeType value " + type);
        try {
            cb.onHomeType(enumType);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.home_type [type: " + type + "]", e);
        }
    }

    private static void preferredHomeType(Callback cb, int type) {
        ArsdkFeatureRth.HomeType enumType = ArsdkFeatureRth.HomeType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.HomeType value " + type);
        try {
            cb.onPreferredHomeType(enumType);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.preferred_home_type [type: " + type + "]", e);
        }
    }

    private static void takeoffLocation(Callback cb, double latitude, double longitude, float altitude, int fixedBeforeTakeoff) {
        try {
            cb.onTakeoffLocation(latitude, longitude, altitude, fixedBeforeTakeoff);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.takeoff_location [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", fixed_before_takeoff: " + fixedBeforeTakeoff + "]", e);
        }
    }

    private static void customLocation(Callback cb, double latitude, double longitude, float altitude) {
        try {
            cb.onCustomLocation(latitude, longitude, altitude);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.custom_location [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + "]", e);
        }
    }

    private static void followeeLocation(Callback cb, double latitude, double longitude, float altitude) {
        try {
            cb.onFolloweeLocation(latitude, longitude, altitude);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.followee_location [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + "]", e);
        }
    }

    private static void delay(Callback cb, int delay, int min, int max) {
        try {
            cb.onDelay(delay, min, max);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.delay [delay: " + delay + ", min: " + min + ", max: " + max + "]", e);
        }
    }

    private static void state(Callback cb, int state, int reason) {
        ArsdkFeatureRth.State enumState = ArsdkFeatureRth.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.State value " + state);
        ArsdkFeatureRth.StateReason enumReason = ArsdkFeatureRth.StateReason.fromValue(reason);
        if (enumReason == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.StateReason value " + reason);
        try {
            cb.onState(enumState, enumReason);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.state [state: " + state + ", reason: " + reason + "]", e);
        }
    }

    private static void homeReachability(Callback cb, int status) {
        ArsdkFeatureRth.HomeReachability enumStatus = ArsdkFeatureRth.HomeReachability.fromValue(status);
        if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.HomeReachability value " + status);
        try {
            cb.onHomeReachability(enumStatus);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.home_reachability [status: " + status + "]", e);
        }
    }

    private static void rthAutoTrigger(Callback cb, int reason, long delay) {
        ArsdkFeatureRth.AutoTriggerReason enumReason = ArsdkFeatureRth.AutoTriggerReason.fromValue(reason);
        if (enumReason == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.AutoTriggerReason value " + reason);
        try {
            cb.onRthAutoTrigger(enumReason, delay);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.rth_auto_trigger [reason: " + reason + ", delay: " + delay + "]", e);
        }
    }

    private static void minAltitude(Callback cb, float current, float min, float max) {
        try {
            cb.onMinAltitude(current, min, max);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.min_altitude [current: " + current + ", min: " + min + ", max: " + max + "]", e);
        }
    }

    private static void autoTriggerMode(Callback cb, int mode) {
        ArsdkFeatureRth.AutoTriggerMode enumMode = ArsdkFeatureRth.AutoTriggerMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.AutoTriggerMode value " + mode);
        try {
            cb.onAutoTriggerMode(enumMode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.auto_trigger_mode [mode: " + mode + "]", e);
        }
    }

    private static void endingBehavior(Callback cb, int endingBehavior) {
        ArsdkFeatureRth.EndingBehavior enumEndingbehavior = ArsdkFeatureRth.EndingBehavior.fromValue(endingBehavior);
        if (enumEndingbehavior == null) ULog.e(TAG, "Unsupported ArsdkFeatureRth.EndingBehavior value " + endingBehavior);
        try {
            cb.onEndingBehavior(enumEndingbehavior);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.ending_behavior [ending_behavior: " + endingBehavior + "]", e);
        }
    }

    private static void endingHoveringAltitude(Callback cb, float current, float min, float max) {
        try {
            cb.onEndingHoveringAltitude(current, min, max);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: rth.ending_hovering_altitude [current: " + current + ", min: " + min + ", max: " + max + "]", e);
        }
    }
    
    /**
     * Set the preferred home location type. The drone will always choose this home type when available.
     * 
     * @param type: Preferred home type.
     */
    public static ArsdkCommand encodeSetPreferredHomeType(@NonNull HomeType type) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetPreferredHomeType(cmd.getNativePtr(), type.value);
        return cmd;
    }

    /**
     * Set the custom location.
     * 
     * @param latitude: Latitude of the takeoff location
     * @param longitude: Longitude of the takeoff location
     * @param altitude: Altitude of the custom location above takeoff (ATO).
     */
    public static ArsdkCommand encodeSetCustomLocation(double latitude, double longitude, float altitude) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetCustomLocation(cmd.getNativePtr(), latitude, longitude, altitude);
        return cmd;
    }

    /**
     * Set the delay after which the drone will automatically try to return home after a disconnection.
     * 
     * @param delay: Delay in second
     */
    public static ArsdkCommand encodeSetDelay(int delay) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetDelay(cmd.getNativePtr(), delay);
        return cmd;
    }

    /**
     * Return home. Ask the drone to fly to its home position. Please note that the drone will wait to be hovering to
     * start its return home. This means that it will wait to have a [flag](#1-0-2) set at 0.
     */
    public static ArsdkCommand encodeReturnToHome() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeReturnToHome(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Abort a currently executing return to home.
     */
    public static ArsdkCommand encodeAbort() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeAbort(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Cancel any current return home auto trigger. This command has no effect if there is no auto trigger currently
     * planned (i.e. reason of [rth_auto_trigger](#146-15) is `none`).
     */
    public static ArsdkCommand encodeCancelAutoTrigger() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeCancelAutoTrigger(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Set the return home minimum altitude. If the drone is below this altitude when starting its return home, it will
     * first reach the minimum altitude. If it is higher than this minimum altitude, it will operate its return home at
     * its current altitude.
     * 
     * @param altitude: Return home min altitude above takeoff (ATO).
     */
    public static ArsdkCommand encodeSetMinAltitude(float altitude) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetMinAltitude(cmd.getNativePtr(), altitude);
        return cmd;
    }

    /**
     * Set mode for auto trigger return home
     * 
     * @param mode: Mode asked by user
     */
    public static ArsdkCommand encodeSetAutoTriggerMode(@NonNull AutoTriggerMode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetAutoTriggerMode(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    /**
     * Choose ending behavior action for RTH.
     * 
     * @param endingBehavior: Ending behavior action
     */
    public static ArsdkCommand encodeSetEndingBehavior(@NonNull EndingBehavior endingBehavior) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetEndingBehavior(cmd.getNativePtr(), endingBehavior.value);
        return cmd;
    }

    /**
     * Set the return home ending hovering altitude. If the ending behavior action is set to `hovering`, Use this
     * altitude.
     * 
     * @param altitude: Altitude used by the drone when hovering at the end of return home. This end altitude is AGL
     * (above ground level).
     */
    public static ArsdkCommand encodeSetEndingHoveringAltitude(float altitude) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetEndingHoveringAltitude(cmd.getNativePtr(), altitude);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetPreferredHomeType(long nativeCmd, int type);
    private static native int nativeEncodeSetCustomLocation(long nativeCmd, double latitude, double longitude, float altitude);
    private static native int nativeEncodeSetDelay(long nativeCmd, int delay);
    private static native int nativeEncodeReturnToHome(long nativeCmd);
    private static native int nativeEncodeAbort(long nativeCmd);
    private static native int nativeEncodeCancelAutoTrigger(long nativeCmd);
    private static native int nativeEncodeSetMinAltitude(long nativeCmd, float altitude);
    private static native int nativeEncodeSetAutoTriggerMode(long nativeCmd, int mode);
    private static native int nativeEncodeSetEndingBehavior(long nativeCmd, int ending_behavior);
    private static native int nativeEncodeSetEndingHoveringAltitude(long nativeCmd, float altitude);
}
