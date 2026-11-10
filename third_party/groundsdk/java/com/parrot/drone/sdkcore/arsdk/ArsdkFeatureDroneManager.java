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
 * Drone_manager feature command/event interface.
 */
public class ArsdkFeatureDroneManager {

    /**
     * The security of the drone network.
     */
    public enum Security {

        /**
         * The drone is not protected.
         */
        NONE(0),

        /**
         * The drone is protected with Wpa2 (passphrase).
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
        public static Security fromValue(int value) {
            return MAP.get(value, null);
        }

        private Security(int value) {
            this.value = value;
        }

        private static final SparseArray<Security> MAP;

        static {
            MAP = new SparseArray<>();
            for (Security e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The state of the connection to a drone.
     */
    public enum ConnectionState {

        /**
         * The drone manager do nothing (wait for command).
         */
        IDLE(0),

        /**
         * The drone manager is searching for a known drone.
         */
        SEARCHING(1),

        /**
         * The drone manager is connecting to a drone.
         */
        CONNECTING(2),

        /**
         * The drone manager is connected to a drone.
         */
        CONNECTED(3),

        /**
         * The drone manager is finishing the connection with the drone before taking further action.
         */
        DISCONNECTING(4);

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
        public static ConnectionState fromValue(int value) {
            return MAP.get(value, null);
        }

        private ConnectionState(int value) {
            this.value = value;
        }

        private static final SparseArray<ConnectionState> MAP;

        static {
            MAP = new SparseArray<>();
            for (ConnectionState e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8900;

    /** Uid of drone_list_item event. */;
    public static final int DRONE_LIST_ITEM_UID = 0x0002;

    /** Uid of connection_state event. */;
    public static final int CONNECTION_STATE_UID = 0x0005;

    /** Uid of authentication_failed event. */;
    public static final int AUTHENTICATION_FAILED_UID = 0x0006;

    /** Uid of connection_refused event. */;
    public static final int CONNECTION_REFUSED_UID = 0x0007;

    /** Uid of known_drone_item event. */;
    public static final int KNOWN_DRONE_ITEM_UID = 0x0008;

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
         * Item describing a drone.
         * 
         * @param serial: Serial number of the drone.
         * @param model: Model id of the drone.
         * @param name: Name (SSID) of the drone.
         * @param connectionOrder: 0 if the drone is unknwon (never connected). Else, order of last connection (1 = most
         * recent)
         * @param active: 1 if the drone is active (the drone manager tries to connect or is connected to it) 0 if the
         * drone is not the active one.
         * @param visible: 1 if the drone is currently visible, 0 otherwise.
         * @param security: Security method used by the drone.
         * @param hasSavedKey: 1 if the drone manager has a saved security key for the drone, 0 otherwise. If security
         * method is not 'none', and this value is 0, then the controller should prompt the user for a passphrase before
         * sending a connect.
         * @param rssi: The drone rssi (wifi signal strength estimation). The value is meaningless if the drone is not
         * visible.
         * @param listFlagsBitField:
         */
        public default void onDroneListItem(String serial, int model, String name, int connectionOrder, int active, int visible, @Nullable Security security, int hasSavedKey, int rssi, int listFlagsBitField) {}

        /**
         * State of the connection.
         * 
         * @param state: Current state of the connection.
         * @param serial: Serial number of the drone.
         * @param model: Model id of the drone.
         * @param name: Name (SSID) of the drone.
         */
        public default void onConnectionState(@Nullable ConnectionState state, String serial, int model, String name) {}

        /**
         * Authentication failed because of a wrong key (passphrase).
         * 
         * @param serial: Serial number of the drone.
         * @param model: Model id of the drone.
         * @param name: Name (SSID) of the drone.
         */
        public default void onAuthenticationFailed(String serial, int model, String name) {}

        /**
         * Connection refused by the drone because another peer is already connected to.
         * 
         * @param serial: Serial number of the drone.
         * @param model: Model id of the drone.
         * @param name: Name (SSID) of the drone.
         */
        public default void onConnectionRefused(String serial, int model, String name) {}

        /**
         * Item describing a known drone (already connected).
         * 
         * @param serial: Serial number of the drone.
         * @param model: Model id of the drone.
         * @param name: Last visible Name (SSID) of the drone.
         * @param security: Security method used by the drone.
         * @param hasSavedKey: 1 if the drone manager has a saved security key for the drone, 0 otherwise. If security
         * method is not 'none', and this value is 0, then the controller should prompt the user for a passphrase before
         * sending a connect.
         * @param listFlagsBitField:
         */
        public default void onKnownDroneItem(String serial, int model, String name, @Nullable Security security, int hasSavedKey, int listFlagsBitField) {}
    }

    private static void droneListItem(Callback cb, String serial, int model, String name, int connectionOrder, int active, int visible, int security, int hasSavedKey, int rssi, int listFlagsBitField) {
        ArsdkFeatureDroneManager.Security enumSecurity = ArsdkFeatureDroneManager.Security.fromValue(security);
        if (enumSecurity == null) ULog.e(TAG, "Unsupported ArsdkFeatureDroneManager.Security value " + security);
        try {
            cb.onDroneListItem(serial, model, name, connectionOrder, active, visible, enumSecurity, hasSavedKey, rssi, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: drone_manager.drone_list_item [serial: " + serial + ", model: " + model + ", name: " + name + ", connection_order: " + connectionOrder + ", active: " + active + ", visible: " + visible + ", security: " + security + ", has_saved_key: " + hasSavedKey + ", rssi: " + rssi + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void connectionState(Callback cb, int state, String serial, int model, String name) {
        ArsdkFeatureDroneManager.ConnectionState enumState = ArsdkFeatureDroneManager.ConnectionState.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureDroneManager.ConnectionState value " + state);
        try {
            cb.onConnectionState(enumState, serial, model, name);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: drone_manager.connection_state [state: " + state + ", serial: " + serial + ", model: " + model + ", name: " + name + "]", e);
        }
    }

    private static void authenticationFailed(Callback cb, String serial, int model, String name) {
        try {
            cb.onAuthenticationFailed(serial, model, name);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: drone_manager.authentication_failed [serial: " + serial + ", model: " + model + ", name: " + name + "]", e);
        }
    }

    private static void connectionRefused(Callback cb, String serial, int model, String name) {
        try {
            cb.onConnectionRefused(serial, model, name);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: drone_manager.connection_refused [serial: " + serial + ", model: " + model + ", name: " + name + "]", e);
        }
    }

    private static void knownDroneItem(Callback cb, String serial, int model, String name, int security, int hasSavedKey, int listFlagsBitField) {
        ArsdkFeatureDroneManager.Security enumSecurity = ArsdkFeatureDroneManager.Security.fromValue(security);
        if (enumSecurity == null) ULog.e(TAG, "Unsupported ArsdkFeatureDroneManager.Security value " + security);
        try {
            cb.onKnownDroneItem(serial, model, name, enumSecurity, hasSavedKey, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: drone_manager.known_drone_item [serial: " + serial + ", model: " + model + ", name: " + name + ", security: " + security + ", has_saved_key: " + hasSavedKey + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }
    
    /**
     * The list will contain: * known drones not currently visible. * known drones currently visible. * unknown drones
     * currently visible.
     */
    public static ArsdkCommand encodeDiscoverDrones() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeDiscoverDrones(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Request connection to a specific drone. Override the auto-selected drone.
     * 
     * @param serial: Serial number of the drone.
     * @param key: Security key (passphrase) to use. This arg is ignored if the drone security is 'none'. If the drone
     * manager has a saved key for the drone, pass an empty string to use it
     */
    public static ArsdkCommand encodeConnect(String serial, String key) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeConnect(cmd.getNativePtr(), serial, key);
        return cmd;
    }

    /**
     * Forget the given drone. If the drone is the selected one, the auto-selection will run again.
     * 
     * @param serial: Serial number of the drone to forget.
     */
    public static ArsdkCommand encodeForget(String serial) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeForget(cmd.getNativePtr(), serial);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeDiscoverDrones(long nativeCmd);
    private static native int nativeEncodeConnect(long nativeCmd, String serial, String key);
    private static native int nativeEncodeForget(long nativeCmd, String serial);
}
