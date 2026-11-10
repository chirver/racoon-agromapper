/** Generated, do not edit ! */
package com.parrot.drone.sdkcore.arsdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.parrot.drone.sdkcore.arsdk.command.ArsdkCommand;
import com.parrot.drone.sdkcore.ulog.ULog;

import static com.parrot.drone.sdkcore.arsdk.Logging.TAG;

import java.nio.ByteBuffer;
/**
 * Controller_info feature command/event interface.
 */
public class ArsdkFeatureControllerInfo {


    /** Feature uid. */
    public static final int UID = 0x8C00;

    /** Uid of validity_from_drone event. */;
    public static final int VALIDITY_FROM_DRONE_UID = 0x0003;

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
         * @param isValid: Boolean. Whether the gps is valid (1) or not (0), as seen by the drone
         */
        public default void onValidityFromDrone(int isValid) {}
    }

    private static void validityFromDrone(Callback cb, int isValid) {
        try {
            cb.onValidityFromDrone(isValid);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: controller_info.validity_from_drone [is_valid: " + isValid + "]", e);
        }
    }
    
    /**
     * Controller gps info. This command is not acknowledged by the drone.
     * 
     * @param latitude: Latitude of the controller (in deg)
     * @param longitude: Longitude of the controller (in deg)
     * @param altitude: Altitude of the controller (in meters, according to sea level)
     * @param horizontalAccuracy: Horizontal accuracy (in meter)
     * @param verticalAccuracy: Vertical accuracy (in meter)
     * @param northSpeed: North speed (in meter per second)
     * @param eastSpeed: East speed (in meter per second)
     * @param downSpeed: Vertical speed (in meter per second) (down is positive)
     * @param timestamp: Timestamp of the gps info (in milliseconds), from a monotonic clock (not necessarily linked to
     * utc clock)
     */
    public static ArsdkCommand encodeGps(double latitude, double longitude, float altitude, float horizontalAccuracy, float verticalAccuracy, float northSpeed, float eastSpeed, float downSpeed, double timestamp) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeGps(cmd.getNativePtr(), latitude, longitude, altitude, horizontalAccuracy, verticalAccuracy, northSpeed, eastSpeed, downSpeed, timestamp);
        return cmd;
    }

    /**

     * 
     * @param pressure: Atmospheric pressure in Pa
     * @param timestamp: Timestamp of the barometer info (in milliseconds), from a monotonic clock (not necessarily
     * linked to utc clock)
     */
    public static ArsdkCommand encodeBarometer(float pressure, double timestamp) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeBarometer(cmd.getNativePtr(), pressure, timestamp);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeGps(long nativeCmd, double latitude, double longitude, float altitude, float horizontal_accuracy, float vertical_accuracy, float north_speed, float east_speed, float down_speed, double timestamp);
    private static native int nativeEncodeBarometer(long nativeCmd, float pressure, double timestamp);
}
