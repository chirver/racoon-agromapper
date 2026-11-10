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
 * Skyctrl feature command/event interface.
 */
public class ArsdkFeatureSkyctrl {

    /**
     * Wifi status.
     */
    public enum WifistateConnexionchangedStatus {

        /**
         * Connected.
         */
        CONNECTED(0),

        /**
         * Error.
         */
        ERROR(1),

        /**
         * Disconnected.
         */
        DISCONNECTED(2);

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
        public static WifistateConnexionchangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private WifistateConnexionchangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<WifistateConnexionchangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (WifistateConnexionchangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The band of this channel : 2.4 GHz or 5 GHz.
     */
    public enum WifistateWifiauthchannellistchangedBand {

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
        public static WifistateWifiauthchannellistchangedBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private WifistateWifiauthchannellistchangedBand(int value) {
            this.value = value;
        }

        private static final SparseArray<WifistateWifiauthchannellistchangedBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (WifistateWifiauthchannellistchangedBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The band of this channel : 2.4 GHz or 5 GHz.
     */
    public enum WifistateWifiauthchannellistchangedv2Band {

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
        public static WifistateWifiauthchannellistchangedv2Band fromValue(int value) {
            return MAP.get(value, null);
        }

        private WifistateWifiauthchannellistchangedv2Band(int value) {
            this.value = value;
        }

        private static final SparseArray<WifistateWifiauthchannellistchangedv2Band> MAP;

        static {
            MAP = new SparseArray<>();
            for (WifistateWifiauthchannellistchangedv2Band e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of environment.
     */
    public enum WifistateWifienvironmentchangedEnvironment {

        /**
         * indoor environment.
         */
        INDOOR(0),

        /**
         * outdoor environment.
         */
        OUTDOOR(1);

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
        public static WifistateWifienvironmentchangedEnvironment fromValue(int value) {
            return MAP.get(value, null);
        }

        private WifistateWifienvironmentchangedEnvironment(int value) {
            this.value = value;
        }

        private static final SparseArray<WifistateWifienvironmentchangedEnvironment> MAP;

        static {
            MAP = new SparseArray<>();
            for (WifistateWifienvironmentchangedEnvironment e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Connection status.
     */
    public enum DevicestateConnexionchangedStatus {

        /**
         * Not Connected.
         */
        NOTCONNECTED(0),

        /**
         * Connecting to Drone.
         */
        CONNECTING(1),

        /**
         * Connected to Drone.
         */
        CONNECTED(2),

        /**
         * Disconnecting from Drone.
         */
        DISCONNECTING(3);

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
        public static DevicestateConnexionchangedStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private DevicestateConnexionchangedStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<DevicestateConnexionchangedStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (DevicestateConnexionchangedStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Variant of the product.
     */
    public enum SettingsstateProductvariantchangedVariant {

        /**
         * SkyController of the bebop generation. (Bebop battery, original key layout, red/blue/yellow).
         */
        BEBOP(0),

        /**
         * SkyController of the bebop2 generation. (Bebop2 battery, updated key layout, black).
         */
        BEBOP2(1);

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
        public static SettingsstateProductvariantchangedVariant fromValue(int value) {
            return MAP.get(value, null);
        }

        private SettingsstateProductvariantchangedVariant(int value) {
            this.value = value;
        }

        private static final SparseArray<SettingsstateProductvariantchangedVariant> MAP;

        static {
            MAP = new SparseArray<>();
            for (SettingsstateProductvariantchangedVariant e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Current battery state.
     */
    public enum SkycontrollerstateBatterystateState {

        /**
         * Battery is charging.
         */
        CHARGING(0),

        /**
         * Battery is fully charged.
         */
        CHARGED(1),

        /**
         * Battery is discharging (normal case when on and unplugged).
         */
        DISCHARGING(2),

        /**
         * Battery is low (Can still work for a few minutes).
         */
        DISCHARGING_LOW(3),

        /**
         * Battery is critically low (the product will automatically shut down if not plugged).
         */
        DISCHARGING_CRITICAL(4);

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
        public static SkycontrollerstateBatterystateState fromValue(int value) {
            return MAP.get(value, null);
        }

        private SkycontrollerstateBatterystateState(int value) {
            this.value = value;
        }

        private static final SparseArray<SkycontrollerstateBatterystateState> MAP;

        static {
            MAP = new SparseArray<>();
            for (SkycontrollerstateBatterystateState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi selection (only manual at the moment).
     */
    public enum AccesspointsettingsWifiselectionType {

        /**
         * Manual selection.
         */
        MANUAL(0);

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
        public static AccesspointsettingsWifiselectionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsWifiselectionType(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsWifiselectionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsWifiselectionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The allowed band : 2.4 Ghz or 5 Ghz.
     */
    public enum AccesspointsettingsWifiselectionBand {

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
        public static AccesspointsettingsWifiselectionBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsWifiselectionBand(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsWifiselectionBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsWifiselectionBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of security for the network.
     */
    public enum AccesspointsettingsWifisecuritySecurityType {

        /**
         * Wifi is not protected (default).
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
        public static AccesspointsettingsWifisecuritySecurityType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsWifisecuritySecurityType(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsWifisecuritySecurityType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsWifisecuritySecurityType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi selection (only manual at the moment).
     */
    public enum AccesspointsettingsstateWifiselectionchangedType {

        /**
         * Manual selection.
         */
        MANUAL(0);

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
        public static AccesspointsettingsstateWifiselectionchangedType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsstateWifiselectionchangedType(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsstateWifiselectionchangedType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsstateWifiselectionchangedType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The allowed band : 2.4 Ghz or 5 Ghz.
     */
    public enum AccesspointsettingsstateWifiselectionchangedBand {

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
        public static AccesspointsettingsstateWifiselectionchangedBand fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsstateWifiselectionchangedBand(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsstateWifiselectionchangedBand> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsstateWifiselectionchangedBand e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of security for the network.
     */
    public enum AccesspointsettingsstateWifisecuritychangedSecurityType {

        /**
         * Wifi is not protected (default).
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
        public static AccesspointsettingsstateWifisecuritychangedSecurityType fromValue(int value) {
            return MAP.get(value, null);
        }

        private AccesspointsettingsstateWifisecuritychangedSecurityType(int value) {
            this.value = value;
        }

        private static final SparseArray<AccesspointsettingsstateWifisecuritychangedSecurityType> MAP;

        static {
            MAP = new SparseArray<>();
            for (AccesspointsettingsstateWifisecuritychangedSecurityType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type (axis/button) of the control.
     */
    public enum GamepadinfosstateGamepadcontrolType {

        /**
         * An analog axis (one of the 4 joysticks).
         */
        AXIS(0),

        /**
         * A button (including small joystick clicks).
         */
        BUTTON(1);

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
        public static GamepadinfosstateGamepadcontrolType fromValue(int value) {
            return MAP.get(value, null);
        }

        private GamepadinfosstateGamepadcontrolType(int value) {
            this.value = value;
        }

        private static final SparseArray<GamepadinfosstateGamepadcontrolType> MAP;

        static {
            MAP = new SparseArray<>();
            for (GamepadinfosstateGamepadcontrolType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The new piloting source.
     */
    public enum CopilotingSetpilotingsourceSource {

        /**
         * Use the SkyController joysticks.
         */
        SKYCONTROLLER(0),

        /**
         * Use the Tablet (or smartphone, or whatever) controls Disables the SkyController joysticks.
         */
        CONTROLLER(1);

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
        public static CopilotingSetpilotingsourceSource fromValue(int value) {
            return MAP.get(value, null);
        }

        private CopilotingSetpilotingsourceSource(int value) {
            this.value = value;
        }

        private static final SparseArray<CopilotingSetpilotingsourceSource> MAP;

        static {
            MAP = new SparseArray<>();
            for (CopilotingSetpilotingsourceSource e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The source.
     */
    public enum CopilotingstatePilotingsourceSource {

        /**
         * Use the SkyController joysticks.
         */
        SKYCONTROLLER(0),

        /**
         * Use the Tablet (or smartphone, or whatever) controls Disables the SkyController joysticks.
         */
        CONTROLLER(1);

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
        public static CopilotingstatePilotingsourceSource fromValue(int value) {
            return MAP.get(value, null);
        }

        private CopilotingstatePilotingsourceSource(int value) {
            this.value = value;
        }

        private static final SparseArray<CopilotingstatePilotingsourceSource> MAP;

        static {
            MAP = new SparseArray<>();
            for (CopilotingstatePilotingsourceSource e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The global status of the calibration.
     */
    public enum CalibrationstateMagnetocalibrationstateStatus {

        /**
         * A calibration is needed.
         */
        UNRELIABLE(0),

        /**
         * A calibration is applied, but still need to be checked.
         */
        ASSESSING(1),

        /**
         * The sensor is properly calibrated.
         */
        CALIBRATED(2);

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
        public static CalibrationstateMagnetocalibrationstateStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationstateMagnetocalibrationstateStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationstateMagnetocalibrationstateStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationstateMagnetocalibrationstateStatus e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The calibration state.
     */
    public enum CalibrationstateMagnetocalibrationstatev2State {

        /**
         * A calibration is needed.
         */
        NOTCALIBRATED(0),

        /**
         * A calibration is in progress on the X axis.
         */
        CALIBRATINGX(1),

        /**
         * A calibration is in progress on the Y axis.
         */
        CALIBRATINGY(2),

        /**
         * A calibration is in progress on the Z axis.
         */
        CALIBRATINGZ(3),

        /**
         * The sensor is calibrated.
         */
        CALIBRATED(4);

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
        public static CalibrationstateMagnetocalibrationstatev2State fromValue(int value) {
            return MAP.get(value, null);
        }

        private CalibrationstateMagnetocalibrationstatev2State(int value) {
            this.value = value;
        }

        private static final SparseArray<CalibrationstateMagnetocalibrationstatev2State> MAP;

        static {
            MAP = new SparseArray<>();
            for (CalibrationstateMagnetocalibrationstatev2State e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Reason of the shutdown of the product.
     */
    public enum CommoneventstateShutdownReason {

        /**
         * The power off button has been pressed.
         */
        POWEROFF_BUTTON(0),

        /**
         * The product is going to be updated.
         */
        UPDATE(1),

        /**
         * The product battery is too low.
         */
        LOW_BATTERY(2),

        /**
         * The product is going to be factory reset.
         */
        FACTORY_RESET(3);

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
        public static CommoneventstateShutdownReason fromValue(int value) {
            return MAP.get(value, null);
        }

        private CommoneventstateShutdownReason(int value) {
            this.value = value;
        }

        private static final SparseArray<CommoneventstateShutdownReason> MAP;

        static {
            MAP = new SparseArray<>();
            for (CommoneventstateShutdownReason e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Wifi state 
     */
    public static class WifiState {

        /** Feature uid. */
        public static final int UID = 0x0400;

        /** Uid of WifiList event. */;
        public static final int WIFILIST_UID = 0x0000;

        /** Uid of ConnexionChanged event. */;
        public static final int CONNEXIONCHANGED_UID = 0x0001;

        /** Uid of WifiAuthChannelListChanged event. */;
        public static final int WIFIAUTHCHANNELLISTCHANGED_UID = 0x0002;

        /** Uid of AllWifiAuthChannelChanged event. */;
        public static final int ALLWIFIAUTHCHANNELCHANGED_UID = 0x0003;

        /** Uid of WifiSignalChanged event. */;
        public static final int WIFISIGNALCHANGED_UID = 0x0004;

        /** Uid of WifiAuthChannelListChangedV2 event. */;
        public static final int WIFIAUTHCHANNELLISTCHANGEDV2_UID = 0x0005;

        /** Uid of WifiCountryChanged event. */;
        public static final int WIFICOUNTRYCHANGED_UID = 0x0006;

        /** Uid of WifiEnvironmentChanged event. */;
        public static final int WIFIENVIRONMENTCHANGED_UID = 0x0007;

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
             * List of visible wifi networks. The application must clear the list before sending the
             * [RequestWifiList](#4-1-0) command.
             * 
             * @param bssid: Wifi bssid
             * @param ssid: Wifi ssid
             * @param secured: Is wifi secured by passphrase
             * @param saved: Is wifi saved in terminal
             * @param rssi: Wifi rssi
             * @param frequency: Wifi frequency
             */
            public default void onWifiList(String bssid, String ssid, int secured, int saved, int rssi, int frequency) {}

            /**
             * Describes the current wifi connection status of the SkyController. The connection to a wifi network does
             * not guarantee a connection to a drone. To get the drone connection state, use the [device
             * ConnectionChanged](#4-3-1) event.
             * 
             * @param ssid: Wifi ssid
             * @param status:
             */
            public default void onConnexionChanged(String ssid, @Nullable WifistateConnexionchangedStatus status) {}

            /**
             * Each element represent an authorized wifi channel for the current country regulatory domain. Note that
             * some channels might be only authorized for indoor or outdoor use. The list is complete when the
             * [AllWifiAuthChannelChanged](#4-0-3) event is recieved.
             * 
             * @param band:
             * @param channel: The authorized channel
             * @param inOrOut: Bit 0 is 1 if channel is authorized outside (0 otherwise) Bit 1 is 1 if channel is
             * authorized inside (0 otherwise)
             */
            public default void onWifiAuthChannelListChanged(@Nullable WifistateWifiauthchannellistchangedBand band, int channel, int inOrOut) {}

            /**
             * This event closes the [WifiAuthChannel](#4-1-4) command response. No more
             * [WifiAuthChannelListChanged](#4-0-2) event will be sent after this event.
             */
            public default void onAllWifiAuthChannelChanged() {}

            /**
             * This event describes the signal strength for the long range wifi. A zero value is used when the
             * SkyController is not connected to a wifi network.
             * 
             * @param level: Level of the signal. Levels are from 0 to 5. 0 is an unknown value. 1 is a weak wifi
             * signal, 5 is the best.
             */
            public default void onWifiSignalChanged(int level) {}

            /**
             * Each element represent an authorized wifi channel for the current country regulatory domain. Note that
             * some channels might be only authorized for indoor or outdoor use.
             * 
             * @param band:
             * @param channel: The authorized channel
             * @param inOrOut: Bit 0 is 1 if channel is authorized outside (0 otherwise) Bit 1 is 1 if channel is
             * authorized inside (0 otherwise)
             * @param listFlags: List entry attribute Bitfield. 0x01: First: indicate it's the first element of the
             * list. 0x02: Last: indicate it's the last element of the list. 0x04: Empty: indicate the list is empty
             * (implies First/Last). All other arguments should be ignored. 0x08: Remove: This value should be removed
             * from the existing list.
             */
            public default void onWifiAuthChannelListChangedV2(@Nullable WifistateWifiauthchannellistchangedv2Band band, int channel, int inOrOut, int listFlags) {}

            /**
             * The wifi country of the SkyController will follow the wifi country of the currently connected drone,
             * except for country-locked SkyControllers.
             * 
             * @param code: Country code with ISO 3166 format, empty string means unknown country.
             */
            public default void onWifiCountryChanged(String code) {}

            /**
             * The wifi environment of the SkyController will follow the wifi environment of the currently connected
             * drone
             * 
             * @param environment:
             */
            public default void onWifiEnvironmentChanged(@Nullable WifistateWifienvironmentchangedEnvironment environment) {}
        }

        private static void wifilist(Callback cb, String bssid, String ssid, int secured, int saved, int rssi, int frequency) {
            try {
                cb.onWifiList(bssid, ssid, secured, saved, rssi, frequency);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiList [bssid: " + bssid + ", ssid: " + ssid + ", secured: " + secured + ", saved: " + saved + ", rssi: " + rssi + ", frequency: " + frequency + "]", e);
            }
        }

        private static void connexionchanged(Callback cb, String ssid, int status) {
            ArsdkFeatureSkyctrl.WifistateConnexionchangedStatus enumStatus = ArsdkFeatureSkyctrl.WifistateConnexionchangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.WifistateConnexionchangedStatus value " + status);
            try {
                cb.onConnexionChanged(ssid, enumStatus);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.ConnexionChanged [ssid: " + ssid + ", status: " + status + "]", e);
            }
        }

        private static void wifiauthchannellistchanged(Callback cb, int band, int channel, int inOrOut) {
            ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedBand enumBand = ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedBand value " + band);
            try {
                cb.onWifiAuthChannelListChanged(enumBand, channel, inOrOut);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiAuthChannelListChanged [band: " + band + ", channel: " + channel + ", in_or_out: " + inOrOut + "]", e);
            }
        }

        private static void allwifiauthchannelchanged(Callback cb) {
            try {
                cb.onAllWifiAuthChannelChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.AllWifiAuthChannelChanged", e);
            }
        }

        private static void wifisignalchanged(Callback cb, int level) {
            try {
                cb.onWifiSignalChanged(level);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiSignalChanged [level: " + level + "]", e);
            }
        }

        private static void wifiauthchannellistchangedv2(Callback cb, int band, int channel, int inOrOut, int listFlags) {
            ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedv2Band enumBand = ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedv2Band.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.WifistateWifiauthchannellistchangedv2Band value " + band);
            try {
                cb.onWifiAuthChannelListChangedV2(enumBand, channel, inOrOut, listFlags);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiAuthChannelListChangedV2 [band: " + band + ", channel: " + channel + ", in_or_out: " + inOrOut + ", list_flags: " + listFlags + "]", e);
            }
        }

        private static void wificountrychanged(Callback cb, String code) {
            try {
                cb.onWifiCountryChanged(code);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiCountryChanged [code: " + code + "]", e);
            }
        }

        private static void wifienvironmentchanged(Callback cb, int environment) {
            ArsdkFeatureSkyctrl.WifistateWifienvironmentchangedEnvironment enumEnvironment = ArsdkFeatureSkyctrl.WifistateWifienvironmentchangedEnvironment.fromValue(environment);
            if (enumEnvironment == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.WifistateWifienvironmentchangedEnvironment value " + environment);
            try {
                cb.onWifiEnvironmentChanged(enumEnvironment);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.WifiState.WifiEnvironmentChanged [environment: " + environment + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Wifi 
     */
    public static class Wifi {

        /** Feature uid. */
        public static final int UID = 0x0401;

        /**
         * After recieving this command, the SkyController will do a network scan to get the wifi list. Communication
         * with the drone is stopped during the network scan, so the controller should avoid sending this command during
         * flight. The controller should clear the local wifi list before sending this command.
         */
        public static ArsdkCommand encodeRequestWifiList() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeRequestWifiList(cmd.getNativePtr());
            return cmd;
        }

        /**
         * This is a synchronization command. The SkyController will automatically send its current wifi info when any
         * data changes, so this command should only be used when connecting, in order to get an initial state.
         */
        public static ArsdkCommand encodeRequestCurrentWifi() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeRequestCurrentWifi(cmd.getNativePtr());
            return cmd;
        }

        /**
         * The network should be a visible network retrieved from the [WifiList](#4-0-0) event. If the network is
         * secured, then the passphrase must be set. For non-secure network, the passphrase argument is ignored.
         * 
         * @param bssid: Wifi bssid
         * @param ssid: Wifi ssid
         * @param passphrase: Wifi passphrase
         */
        public static ArsdkCommand encodeConnectToWifi(String bssid, String ssid, String passphrase) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeConnectToWifi(cmd.getNativePtr(), bssid, ssid, passphrase);
            return cmd;
        }

        /**
         * Removes the network from the saved network list. If the network is the current network, then the
         * SkyController will be disconnected first.
         * 
         * @param ssid: Wifi ssid
         */
        public static ArsdkCommand encodeForgetWifi(String ssid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeForgetWifi(cmd.getNativePtr(), ssid);
            return cmd;
        }

        /**
         * Requests the list of authorized wifi channels for the current country/regulatory domain. These channels are
         * valid for the [AccessPointChannel](#4-9-1) command.
         */
        public static ArsdkCommand encodeWifiAuthChannel() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiAuthChannel(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeRequestWifiList(long nativeCmd);
        private static native int nativeEncodeRequestCurrentWifi(long nativeCmd);
        private static native int nativeEncodeConnectToWifi(long nativeCmd, String bssid, String ssid, String passphrase);
        private static native int nativeEncodeForgetWifi(long nativeCmd, String ssid);
        private static native int nativeEncodeWifiAuthChannel(long nativeCmd);
    }

    /**
     * Device Connection commands 
     */
    public static class Device {

        /** Feature uid. */
        public static final int UID = 0x0402;

        /**
         * This command is deprecated (The SkyController can only see one device at a time, so a device list is not
         * required), and should not be used.
         */
        @Deprecated
        public static ArsdkCommand encodeRequestDeviceList() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeRequestDeviceList(cmd.getNativePtr());
            return cmd;
        }

        /**
         * This command is deprecated and should not be used.
         */
        @Deprecated
        public static ArsdkCommand encodeRequestCurrentDevice() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeRequestCurrentDevice(cmd.getNativePtr());
            return cmd;
        }

        /**
         * This command is deprecated and should not be used. The SkyController will automatically connect to the first
         * visible device on the current wifi network.
         * 
         * @param devicename: Device name
         */
        @Deprecated
        public static ArsdkCommand encodeConnectToDevice(String devicename) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeConnectToDevice(cmd.getNativePtr(), devicename);
            return cmd;
        }

        private static native int nativeEncodeRequestDeviceList(long nativeCmd);
        private static native int nativeEncodeRequestCurrentDevice(long nativeCmd);
        private static native int nativeEncodeConnectToDevice(long nativeCmd, String deviceName);
    }

    /**
     * Device state 
     */
    public static class DeviceState {

        /** Feature uid. */
        public static final int UID = 0x0403;

        /** Uid of DeviceList event. */;
        public static final int DEVICELIST_UID = 0x0000;

        /** Uid of ConnexionChanged event. */;
        public static final int CONNEXIONCHANGED_UID = 0x0001;

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
             * List of visible ARDiscoveryDevices. This event is deprecated and will never be sent by a SkyController
             * 
             * @param name: Device name
             */
            @Deprecated
            public default void onDeviceList(String name) {}

            /**
             * Status of the connection to a drone.
             * 
             * @param status:
             * @param devicename: Drone name
             * @param deviceproductid: Drone product IDentifier
             */
            public default void onConnexionChanged(@Nullable DevicestateConnexionchangedStatus status, String devicename, int deviceproductid) {}
        }

        private static void devicelist(Callback cb, String name) {
            try {
                cb.onDeviceList(name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.DeviceState.DeviceList [name: " + name + "]", e);
            }
        }

        private static void connexionchanged(Callback cb, int status, String devicename, int deviceproductid) {
            ArsdkFeatureSkyctrl.DevicestateConnexionchangedStatus enumStatus = ArsdkFeatureSkyctrl.DevicestateConnexionchangedStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.DevicestateConnexionchangedStatus value " + status);
            try {
                cb.onConnexionChanged(enumStatus, devicename, deviceproductid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.DeviceState.ConnexionChanged [status: " + status + ", deviceName: " + devicename + ", deviceProductID: " + deviceproductid + "]", e);
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
        public static final int UID = 0x0404;

        /**
         * Request the controller to send all its settings.
         */
        public static ArsdkCommand encodeAllSettings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAllSettings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Reset all settings (i.e. everything except drone pairing).
         */
        public static ArsdkCommand encodeReset() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReset(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeAllSettings(long nativeCmd);
        private static native int nativeEncodeReset(long nativeCmd);
    }

    /**
     * Settings state from the controller 
     */
    public static class SettingsState {

        /** Feature uid. */
        public static final int UID = 0x0405;

        /** Uid of AllSettingsChanged event. */;
        public static final int ALLSETTINGSCHANGED_UID = 0x0000;

        /** Uid of ResetChanged event. */;
        public static final int RESETCHANGED_UID = 0x0001;

        /** Uid of ProductSerialChanged event. */;
        public static final int PRODUCTSERIALCHANGED_UID = 0x0002;

        /** Uid of ProductVariantChanged event. */;
        public static final int PRODUCTVARIANTCHANGED_UID = 0x0003;

        /** Uid of ProductVersionChanged event. */;
        public static final int PRODUCTVERSIONCHANGED_UID = 0x0004;

        /** Uid of CPUID event. */;
        public static final int CPUID_UID = 0x0005;

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
             * All settings have been sent by the controller.
             */
            public default void onAllSettingsChanged() {}

            /**
             * This command is not implemented.
             */
            @Deprecated
            public default void onResetChanged() {}

            /**
             * The product serial of the controller.
             * 
             * @param serialnumber: Serial number (hexadecimal value)
             */
            public default void onProductSerialChanged(String serialnumber) {}

            /**
             * This event allow differentiation between original (red/blue/yellow) SkyControllers, and the Black Edition
             * SkyControllers.
             * 
             * @param variant:
             */
            public default void onProductVariantChanged(@Nullable SettingsstateProductvariantchangedVariant variant) {}

            /**
             * Software and hardware versions of the controller.
             * 
             * @param software: Product software version
             * @param hardware: Product hardware version
             */
            public default void onProductVersionChanged(String software, String hardware) {}

            /**
             * The serial number (or any other UID) for the main CPU.
             * 
             * @param id: Product main cpu id
             */
            public default void onCPUID(String id) {}
        }

        private static void allsettingschanged(Callback cb) {
            try {
                cb.onAllSettingsChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.AllSettingsChanged", e);
            }
        }

        private static void resetchanged(Callback cb) {
            try {
                cb.onResetChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.ResetChanged", e);
            }
        }

        private static void productserialchanged(Callback cb, String serialnumber) {
            try {
                cb.onProductSerialChanged(serialnumber);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.ProductSerialChanged [serialNumber: " + serialnumber + "]", e);
            }
        }

        private static void productvariantchanged(Callback cb, int variant) {
            ArsdkFeatureSkyctrl.SettingsstateProductvariantchangedVariant enumVariant = ArsdkFeatureSkyctrl.SettingsstateProductvariantchangedVariant.fromValue(variant);
            if (enumVariant == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.SettingsstateProductvariantchangedVariant value " + variant);
            try {
                cb.onProductVariantChanged(enumVariant);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.ProductVariantChanged [variant: " + variant + "]", e);
            }
        }

        private static void productversionchanged(Callback cb, String software, String hardware) {
            try {
                cb.onProductVersionChanged(software, hardware);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.ProductVersionChanged [software: " + software + ", hardware: " + hardware + "]", e);
            }
        }

        private static void cpuid(Callback cb, String id) {
            try {
                cb.onCPUID(id);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SettingsState.CPUID [id: " + id + "]", e);
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
        public static final int UID = 0x0406;

        /**
         * Request the controller to send all its states.
         */
        public static ArsdkCommand encodeAllStates() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAllStates(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Set both the date and the time with only one command. If using this command, do not use [CurrentDate](#0-4-1)
         * and [CurrentTime](#0-4-2) commands. This datetime is taken by the remote controller as its own datetime. So
         * medias and other files will be dated from this datetime  **Please note that you should not send this command
         * if you are using the libARController API as this library is handling the connection process for you.**
         * 
         * @param datetime: DateTime with the ISO-8601 complete short format: "%Y%m%dT%H%M%S%z"
         */
        public static ArsdkCommand encodeCurrentDateTime(String datetime) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeCurrentDateTime(cmd.getNativePtr(), datetime);
            return cmd;
        }

        private static native int nativeEncodeAllStates(long nativeCmd);
        private static native int nativeEncodeCurrentDateTime(long nativeCmd, String datetime);
    }

    /**
     * Common state from product 
     */
    public static class CommonState {

        /** Feature uid. */
        public static final int UID = 0x0407;

        /** Uid of AllStatesChanged event. */;
        public static final int ALLSTATESCHANGED_UID = 0x0000;

        /** Uid of CurrentDateTimeChanged event. */;
        public static final int CURRENTDATETIMECHANGED_UID = 0x0001;

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
             * All states have been sent by the controller.
             */
            public default void onAllStatesChanged() {}

            /**
             * Both date and time changed. Corresponds to the latest datetime set on the drone.  **Please note that you
             * should not care about this event if you are using the libARController API as this library is handling the
             * connection process for you.**
             * 
             * @param datetime: Datetime with the ISO-8601 complete short format: "%Y%m%dT%H%M%S%z"
             */
            public default void onCurrentDateTimeChanged(String datetime) {}
        }

        private static void allstateschanged(Callback cb) {
            try {
                cb.onAllStatesChanged();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CommonState.AllStatesChanged", e);
            }
        }

        private static void currentdatetimechanged(Callback cb, String datetime) {
            try {
                cb.onCurrentDateTimeChanged(datetime);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CommonState.CurrentDateTimeChanged [datetime: " + datetime + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Sky Controller states 
     */
    public static class SkyControllerState {

        /** Feature uid. */
        public static final int UID = 0x0408;

        /** Uid of BatteryChanged event. */;
        public static final int BATTERYCHANGED_UID = 0x0000;

        /** Uid of GpsFixChanged event. */;
        public static final int GPSFIXCHANGED_UID = 0x0001;

        /** Uid of GpsPositionChanged event. */;
        public static final int GPSPOSITIONCHANGED_UID = 0x0002;

        /** Uid of BatteryState event. */;
        public static final int BATTERYSTATE_UID = 0x0003;

        /** Uid of AttitudeChanged event. */;
        public static final int ATTITUDECHANGED_UID = 0x0004;

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
             * The battery percentage has changed.
             * 
             * @param percent: Controller battery: from 0 (empty) to 100 (full charge). Value of 255 when charging.
             */
            public default void onBatteryChanged(int percent) {}

            /**
             * The SkyController GPS has gained or lost the fix. If the fix is lost, thent the
             * [GpsPositionChanged](#4-8-2) event will contain invalid values for the position.
             * 
             * @param fixed: SkyController fixed
             */
            public default void onGpsFixChanged(int fixed) {}

            /**
             * The SkyController position or heading values changed. Some of the values can be invalid and should be
             * ignored.
             * 
             * @param latitude: SkyController latitude (500. if not available)
             * @param longitude: SkyController longiture (500. if not available)
             * @param altitude: Altitude (in meters) above sea level. Only meaningful if latitude and longiture are
             * available
             * @param heading: SkyController heading relative to magnetic north (500.f if not available)
             */
            public default void onGpsPositionChanged(double latitude, double longitude, double altitude, float heading) {}

            /**
             * The state of the controller battery
             * 
             * @param state:
             */
            public default void onBatteryState(@Nullable SkycontrollerstateBatterystateState state) {}

            /**
             * SkyController Attitude in north-east-down (NED) coordinate system. Attitude is provided as a quaternion.
             * 
             * @param q0: SkyController Attitude q0 (quaternion scalar part)
             * @param q1: SkyController Attitude q1 (quaternion vector part)
             * @param q2: SkyController Attitude q2 (quaternion vector part)
             * @param q3: SkyController Attitude q3 (quaternion vector part)
             */
            public default void onAttitudeChanged(float q0, float q1, float q2, float q3) {}
        }

        private static void batterychanged(Callback cb, int percent) {
            try {
                cb.onBatteryChanged(percent);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SkyControllerState.BatteryChanged [percent: " + percent + "]", e);
            }
        }

        private static void gpsfixchanged(Callback cb, int fixed) {
            try {
                cb.onGpsFixChanged(fixed);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SkyControllerState.GpsFixChanged [fixed: " + fixed + "]", e);
            }
        }

        private static void gpspositionchanged(Callback cb, double latitude, double longitude, double altitude, float heading) {
            try {
                cb.onGpsPositionChanged(latitude, longitude, altitude, heading);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SkyControllerState.GpsPositionChanged [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", heading: " + heading + "]", e);
            }
        }

        private static void batterystate(Callback cb, int state) {
            ArsdkFeatureSkyctrl.SkycontrollerstateBatterystateState enumState = ArsdkFeatureSkyctrl.SkycontrollerstateBatterystateState.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.SkycontrollerstateBatterystateState value " + state);
            try {
                cb.onBatteryState(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SkyControllerState.BatteryState [state: " + state + "]", e);
            }
        }

        private static void attitudechanged(Callback cb, float q0, float q1, float q2, float q3) {
            try {
                cb.onAttitudeChanged(q0, q1, q2, q3);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.SkyControllerState.AttitudeChanged [q0: " + q0 + ", q1: " + q1 + ", q2: " + q2 + ", q3: " + q3 + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * AccessPoint settings commands 
     */
    public static class AccessPointSettings {

        /** Feature uid. */
        public static final int UID = 0x0409;

        /**
         * Set the SkyController access point SSID. The name will be checked, and can be modified before application.
         * Use the [AccessPointSSIDChanged](#4-10-0) event to get the applied network name.
         * 
         * @param ssid: AccessPoint SSID
         */
        public static ArsdkCommand encodeAccessPointSSID(String ssid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAccessPointSSID(cmd.getNativePtr(), ssid);
            return cmd;
        }

        /**
         * Set the SkyController access point channel. The channel will be checked, and can be modified before
         * application. Use the [AccessPointChannelChanged](#4-10-1) event to get the applied channel. The list of
         * authorized channels for the current country can be retrived with the [WifiAuthChannel](#4-1-4) command. This
         * command is deprecated. Use the [WifiSelection](#4-9-2) command instead.
         * 
         * @param channel: AccessPoint Channel
         */
        @Deprecated
        public static ArsdkCommand encodeAccessPointChannel(int channel) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAccessPointChannel(cmd.getNativePtr(), channel);
            return cmd;
        }

        /**
         * Set the SkyController access point channel. The channel will be checked, and can be modified before
         * application. Use the [WifiSelectionChanged](#4-10-2) event to get the applied channel/band. The list of
         * authorized channels for the current country can be retrived with the [WifiAuthChannel](#4-1-4) command.
         * 
         * @param type:
         * @param band:
         * @param channel: The channel
         */
        public static ArsdkCommand encodeWifiSelection(@NonNull AccesspointsettingsWifiselectionType type, @NonNull AccesspointsettingsWifiselectionBand band, int channel) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiSelection(cmd.getNativePtr(), type.value, band.value, channel);
            return cmd;
        }

        /**
         * Set the SkyController access point security. The key will be checked, and can be refused by the product. In
         * this case, the security will not be changed. Use the [WifiSecurityChanged](#4-10-3) event to get the applied
         * security settings.
         * 
         * @param securityType:
         * @param key: The security key (ignored if security_type is open)
         */
        public static ArsdkCommand encodeWifiSecurity(@NonNull AccesspointsettingsWifisecuritySecurityType securityType, String key) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeWifiSecurity(cmd.getNativePtr(), securityType.value, key);
            return cmd;
        }

        private static native int nativeEncodeAccessPointSSID(long nativeCmd, String ssid);
        private static native int nativeEncodeAccessPointChannel(long nativeCmd, int channel);
        private static native int nativeEncodeWifiSelection(long nativeCmd, int type, int band, int channel);
        private static native int nativeEncodeWifiSecurity(long nativeCmd, int security_type, String key);
    }

    /**
     * AccessPoint settings state from product 
     */
    public static class AccessPointSettingsState {

        /** Feature uid. */
        public static final int UID = 0x040A;

        /** Uid of AccessPointSSIDChanged event. */;
        public static final int ACCESSPOINTSSIDCHANGED_UID = 0x0000;

        /** Uid of AccessPointChannelChanged event. */;
        public static final int ACCESSPOINTCHANNELCHANGED_UID = 0x0001;

        /** Uid of WifiSelectionChanged event. */;
        public static final int WIFISELECTIONCHANGED_UID = 0x0002;

        /** Uid of WifiSecurityChanged event. */;
        public static final int WIFISECURITYCHANGED_UID = 0x0003;

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
             * Changing the SSID will often (if not always) trigger a disconnection of the controller, so this event
             * will only be recieved during the initial connexion phase.
             * 
             * @param ssid: AccessPoint SSID
             */
            public default void onAccessPointSSIDChanged(String ssid) {}

            /**
             * This command is deprecated, as the returned channel number does not contain information about the wifi
             * band (2.4GHz or 5GHz). Use the [WifiSelectionChanged](#4-10-2) event instead
             * 
             * @param channel: AccessPoint Channel
             */
            @Deprecated
            public default void onAccessPointChannelChanged(int channel) {}

            /**
             * Changing the channel will often (if not always) trigger a disconnection of the controller, so this event
             * will only be recieved during the initial connexion phase.
             * 
             * @param type:
             * @param band:
             * @param channel: The channel
             */
            public default void onWifiSelectionChanged(@Nullable AccesspointsettingsstateWifiselectionchangedType type, @Nullable AccesspointsettingsstateWifiselectionchangedBand band, int channel) {}

            /**
             * Changing the security will often (if not always) trigger a disconnection of the controller, so this event
             * will only be recieved during the initial connexion phase.
             * 
             * @param securityType:
             * @param key: The security key (ignored if security_type is open)
             */
            public default void onWifiSecurityChanged(@Nullable AccesspointsettingsstateWifisecuritychangedSecurityType securityType, String key) {}
        }

        private static void accesspointssidchanged(Callback cb, String ssid) {
            try {
                cb.onAccessPointSSIDChanged(ssid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AccessPointSettingsState.AccessPointSSIDChanged [ssid: " + ssid + "]", e);
            }
        }

        private static void accesspointchannelchanged(Callback cb, int channel) {
            try {
                cb.onAccessPointChannelChanged(channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AccessPointSettingsState.AccessPointChannelChanged [channel: " + channel + "]", e);
            }
        }

        private static void wifiselectionchanged(Callback cb, int type, int band, int channel) {
            ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedType enumType = ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedType value " + type);
            ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedBand enumBand = ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedBand.fromValue(band);
            if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.AccesspointsettingsstateWifiselectionchangedBand value " + band);
            try {
                cb.onWifiSelectionChanged(enumType, enumBand, channel);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AccessPointSettingsState.WifiSelectionChanged [type: " + type + ", band: " + band + ", channel: " + channel + "]", e);
            }
        }

        private static void wifisecuritychanged(Callback cb, int securityType, String key) {
            ArsdkFeatureSkyctrl.AccesspointsettingsstateWifisecuritychangedSecurityType enumSecuritytype = ArsdkFeatureSkyctrl.AccesspointsettingsstateWifisecuritychangedSecurityType.fromValue(securityType);
            if (enumSecuritytype == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.AccesspointsettingsstateWifisecuritychangedSecurityType value " + securityType);
            try {
                cb.onWifiSecurityChanged(enumSecuritytype, key);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AccessPointSettingsState.WifiSecurityChanged [security_type: " + securityType + ", key: " + key + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Ask the drone to move camera 
     */
    public static class Camera {

        /** Feature uid. */
        public static final int UID = 0x040B;

        /**
         * This command is deprecated. The same effect can be achieved by sending a [CameraOrientation](#1-1-0) command
         * with values retrieved from the [defaultCameraOrientation](#1-25-1) event.
         */
        @Deprecated
        public static ArsdkCommand encodeResetOrientation() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeResetOrientation(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeResetOrientation(long nativeCmd);
    }

    /**
     * Controls the button mappings of the SkyController 
     */
    public static class ButtonMappings {

        /** Feature uid. */
        public static final int UID = 0x040C;

        /**
         * The SkyController will send its full button mapping. This command is mainly useful for initial
         * synchronization, as every change to the button mapping (via the [setButtonMapping](#4-12-2) command) will
         * trigger [currentButtonMappings](#4-13-0) events.
         */
        public static ArsdkCommand encodeGetCurrentButtonMappings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetCurrentButtonMappings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * The SkyController will send all the available action that can be mapped on buttons. As this list is static,
         * the controller only need to request this information once.
         */
        public static ArsdkCommand encodeGetAvailableButtonMappings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetAvailableButtonMappings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Any previous mapping for the given button will be removed, as a button can only be mapped to one action. To
         * unmap a button, a NO_ACTION mapping can be used (see the [availableButtonMappings](#4-13-2) event). Some
         * actions can not be mapped to two different buttons at the same time. In this case, the first button will
         * automatically be set to NO_ACTION, and the corresponding [currentButtonMappings](#4-13-0) event will be
         * fired.
         * 
         * @param keyId: The keycode to map
         * @param mappingUid: The mapping to associate with the key
         */
        public static ArsdkCommand encodeSetButtonMapping(int keyId, String mappingUid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetButtonMapping(cmd.getNativePtr(), keyId, mappingUid);
            return cmd;
        }

        /**
         * The default values can change between software versions. The default values are different for Black Edition
         * SkyControllers
         */
        public static ArsdkCommand encodeDefaultButtonMapping() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeDefaultButtonMapping(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeGetCurrentButtonMappings(long nativeCmd);
        private static native int nativeEncodeGetAvailableButtonMappings(long nativeCmd);
        private static native int nativeEncodeSetButtonMapping(long nativeCmd, int key_id, String mapping_uid);
        private static native int nativeEncodeDefaultButtonMapping(long nativeCmd);
    }

    /**
     * State of the button mappings of the SkyController 
     */
    public static class ButtonMappingsState {

        /** Feature uid. */
        public static final int UID = 0x040D;

        /** Uid of currentButtonMappings event. */;
        public static final int CURRENTBUTTONMAPPINGS_UID = 0x0000;

        /** Uid of allCurrentButtonMappingsSent event. */;
        public static final int ALLCURRENTBUTTONMAPPINGSSENT_UID = 0x0001;

        /** Uid of availableButtonMappings event. */;
        public static final int AVAILABLEBUTTONMAPPINGS_UID = 0x0002;

        /** Uid of allAvailableButtonsMappingsSent event. */;
        public static final int ALLAVAILABLEBUTTONSMAPPINGSSENT_UID = 0x0003;

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
             * The mapping maps a key_id (as found in [gamepadControl](#4-19-0) events) to a mapping_uid (as found in
             * the [availableButtonMappings](#4-13-2) events). A special mapping (NO_ACTION) is attached to unmapped
             * buttons.
             * 
             * @param keyId: The keycode mapped
             * @param mappingUid: The mapping associated
             */
            public default void onCurrentButtonMappings(int keyId, String mappingUid) {}

            /**
             * Sent by the SkyController to notify the end of a [currentButtonMappings](#4-13-0) events list. If the
             * list is empty (e.g. the controller sent a [setButtonMapping](#4-12-2) command which made no change to the
             * mapping table), then this command will be sent without any [currentButtonMappings](#4-13-0) event
             * preceding it. This gives the controller a reliable synchronization point when editing mappings.
             */
            public default void onAllCurrentButtonMappingsSent() {}

            /**
             * Each action that can be mapped on a button is identified by its mapping_uid, which will be used in the
             * [setButtonMapping](#4-12-2) and [currentButtonMappings](#4-13-0) commands. The name is a human readable
             * string, in english, describing the action. A special action named NO_ACTION serves as the unmap action.
             * This action can be bound to multiple buttons to disable them. An
             * [allAvailableButtonsMappingsSent](#4-13-3) event is sent at the end of the list.
             * 
             * @param mappingUid: The mapping UID (used in communication with the SkyController)
             * @param name: Display name for the user
             */
            public default void onAvailableButtonMappings(String mappingUid, String name) {}

            /**
             * Sent by the SkyController to notify the end of a [availableButtonMappings](#4-13-2) events list.
             */
            public default void onAllAvailableButtonsMappingsSent() {}
        }

        private static void currentbuttonmappings(Callback cb, int keyId, String mappingUid) {
            try {
                cb.onCurrentButtonMappings(keyId, mappingUid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.ButtonMappingsState.currentButtonMappings [key_id: " + keyId + ", mapping_uid: " + mappingUid + "]", e);
            }
        }

        private static void allcurrentbuttonmappingssent(Callback cb) {
            try {
                cb.onAllCurrentButtonMappingsSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.ButtonMappingsState.allCurrentButtonMappingsSent", e);
            }
        }

        private static void availablebuttonmappings(Callback cb, String mappingUid, String name) {
            try {
                cb.onAvailableButtonMappings(mappingUid, name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.ButtonMappingsState.availableButtonMappings [mapping_uid: " + mappingUid + ", name: " + name + "]", e);
            }
        }

        private static void allavailablebuttonsmappingssent(Callback cb) {
            try {
                cb.onAllAvailableButtonsMappingsSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.ButtonMappingsState.allAvailableButtonsMappingsSent", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Controls the axis mappings of the SkyController 
     */
    public static class AxisMappings {

        /** Feature uid. */
        public static final int UID = 0x040E;

        /**
         * The SkyController will send its full axis mapping. This command is mainly useful for initial synchronization,
         * as every change to the axis mapping (via the [setAxisMapping](#4-14-2) command) will trigger
         * [currentAxisMappings](#4-15-0) events.
         */
        public static ArsdkCommand encodeGetCurrentAxisMappings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetCurrentAxisMappings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * The SkyController will send all the available action that can be mapped on axes. As this list is static, the
         * controller only need to request this information once.
         */
        public static ArsdkCommand encodeGetAvailableAxisMappings() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetAvailableAxisMappings(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Any previous mapping for the given axis will be removed, as a axis can only be mapped to one action. To unmap
         * a axis, a NO_ACTION mapping can be used (see the [availableAxisMappings](#4-15-2) event). Some actions can
         * not be mapped to two different axes at the same time. In this case, the first axis will automatically be set
         * to NO_ACTION, and the corresponding [currentAxisMappings](#4-15-0) event will be fired.
         * 
         * @param axisId: The axiscode to map
         * @param mappingUid: The mapping to associate with the axis
         */
        public static ArsdkCommand encodeSetAxisMapping(int axisId, String mappingUid) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetAxisMapping(cmd.getNativePtr(), axisId, mappingUid);
            return cmd;
        }

        /**
         * The default values can change between software versions.
         */
        public static ArsdkCommand encodeDefaultAxisMapping() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeDefaultAxisMapping(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeGetCurrentAxisMappings(long nativeCmd);
        private static native int nativeEncodeGetAvailableAxisMappings(long nativeCmd);
        private static native int nativeEncodeSetAxisMapping(long nativeCmd, int axis_id, String mapping_uid);
        private static native int nativeEncodeDefaultAxisMapping(long nativeCmd);
    }

    /**
     * State of the axis mappings of the SkyController 
     */
    public static class AxisMappingsState {

        /** Feature uid. */
        public static final int UID = 0x040F;

        /** Uid of currentAxisMappings event. */;
        public static final int CURRENTAXISMAPPINGS_UID = 0x0000;

        /** Uid of allCurrentAxisMappingsSent event. */;
        public static final int ALLCURRENTAXISMAPPINGSSENT_UID = 0x0001;

        /** Uid of availableAxisMappings event. */;
        public static final int AVAILABLEAXISMAPPINGS_UID = 0x0002;

        /** Uid of allAvailableAxisMappingsSent event. */;
        public static final int ALLAVAILABLEAXISMAPPINGSSENT_UID = 0x0003;

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
             * The mapping maps an axis_id (as found in [gamepadControl](#4-19-0) events) to a mapping_uid (as found in
             * the [availableAxisMappings](#4-15-2) events). A special mapping (NO_ACTION) is attached to unmapped axes.
             * 
             * @param axisId: The axiscode mapped
             * @param mappingUid: The mapping associated
             */
            public default void onCurrentAxisMappings(int axisId, String mappingUid) {}

            /**
             * Sent by the SkyController to notify the end of a [currentAxisMappings](#4-15-0) events list. If the list
             * is empty (e.g. the controller sent a [setAxisMapping](#4-14-2) command which made no change to the
             * mapping table), then this command will be sent without any [currentAxisMappings](#4-15-0) event preceding
             * it. This gives the controller a reliable synchronization point when editing mappings.
             */
            public default void onAllCurrentAxisMappingsSent() {}

            /**
             * Each action that can be mapped on an axis is identified by its mapping_uid, which will be used in the
             * [setAxisMapping](#4-14-2) and [currentAxisMappings](#4-15-0) commands. The name is a human readable
             * string, in english, describing the action. A special action named NO_ACTION serves as the unmap action.
             * This action can be bound to multiple axes to disable them. An [allAvailableAxissMappingsSent](#4-15-3)
             * event is sent at the end of the list.
             * 
             * @param mappingUid: The mapping UID (used in communication with the SkyController)
             * @param name: Display name for the user
             */
            public default void onAvailableAxisMappings(String mappingUid, String name) {}

            /**
             * Sent by the SkyController to notify the end of a [availableAxisMappings](#4-15-2) events list.
             */
            public default void onAllAvailableAxisMappingsSent() {}
        }

        private static void currentaxismappings(Callback cb, int axisId, String mappingUid) {
            try {
                cb.onCurrentAxisMappings(axisId, mappingUid);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisMappingsState.currentAxisMappings [axis_id: " + axisId + ", mapping_uid: " + mappingUid + "]", e);
            }
        }

        private static void allcurrentaxismappingssent(Callback cb) {
            try {
                cb.onAllCurrentAxisMappingsSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisMappingsState.allCurrentAxisMappingsSent", e);
            }
        }

        private static void availableaxismappings(Callback cb, String mappingUid, String name) {
            try {
                cb.onAvailableAxisMappings(mappingUid, name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisMappingsState.availableAxisMappings [mapping_uid: " + mappingUid + ", name: " + name + "]", e);
            }
        }

        private static void allavailableaxismappingssent(Callback cb) {
            try {
                cb.onAllAvailableAxisMappingsSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisMappingsState.allAvailableAxisMappingsSent", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Controls the axis filters of the SkyController 
     */
    public static class AxisFilters {

        /** Feature uid. */
        public static final int UID = 0x0410;

        /**
         * The SkyController will send its full axis filters map. This command is mainly useful for initial
         * synchronization, as every change to the filters map (via the [setAxisFilter](#4-16-2) command) will trigger
         * [currentAxisFilters](#4-17-0) events.
         */
        public static ArsdkCommand encodeGetCurrentAxisFilters() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetCurrentAxisFilters(cmd.getNativePtr());
            return cmd;
        }

        /**
         * The preset list is empty and will never be filled, so this command is flagged as deprecated.
         */
        @Deprecated
        public static ArsdkCommand encodeGetPresetAxisFilters() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetPresetAxisFilters(cmd.getNativePtr());
            return cmd;
        }

        /**
         * A filter modifies the response curve of an axis. As the preset filters list is empty, all filters are to be
         * sent using the builder syntax.  The builder syntax supports two types of filters: Multilinear and
         * Exponential.  Multilinear filters create response curves made of multiple linear segments: * The default
         * filter (ARMF;) is purely linear and create a single segment from the two implicit `[-1; -1]` and `[1; 1]`
         * points. * Additionnal points can be added to the filter with the following syntax:
         * `ARMF;x1&gt;y1;...;xN&gt;yN;`, where all numbers are floating point numbers in range `[-1; 1]`. * Additionnal
         * points **must** respect the following constraints : `x(N)&gt;x(N-1)` and `y(N)&gt;=y(N-1)`.  Exponential
         * filters: * The syntax is `ARXF;CPx;CPy;`, where CPx and CPy are floating point numbers in range `[0; 1]`. *
         * Best results are achieved when `CPx + CPy == 1` and `CPx &gt; CPy`. * If the control point is on the diagonal
         * (i.e. `CPx == CPy`), then the resulting filter will be linear.
         * 
         * @param axisId: The axiscode to filter
         * @param filterUidOrBuilder: The mapping preset to associate with the axis (Or a string to build a new one)
         */
        public static ArsdkCommand encodeSetAxisFilter(int axisId, String filterUidOrBuilder) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetAxisFilter(cmd.getNativePtr(), axisId, filterUidOrBuilder);
            return cmd;
        }

        /**
         * The default values can change between software versions.
         */
        public static ArsdkCommand encodeDefaultAxisFilters() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeDefaultAxisFilters(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeGetCurrentAxisFilters(long nativeCmd);
        private static native int nativeEncodeGetPresetAxisFilters(long nativeCmd);
        private static native int nativeEncodeSetAxisFilter(long nativeCmd, int axis_id, String filter_uid_or_builder);
        private static native int nativeEncodeDefaultAxisFilters(long nativeCmd);
    }

    /**
     * State of the axis filters of the SkyController 
     */
    public static class AxisFiltersState {

        /** Feature uid. */
        public static final int UID = 0x0411;

        /** Uid of currentAxisFilters event. */;
        public static final int CURRENTAXISFILTERS_UID = 0x0000;

        /** Uid of allCurrentFiltersSent event. */;
        public static final int ALLCURRENTFILTERSSENT_UID = 0x0001;

        /** Uid of presetAxisFilters event. */;
        public static final int PRESETAXISFILTERS_UID = 0x0002;

        /** Uid of allPresetFiltersSent event. */;
        public static final int ALLPRESETFILTERSSENT_UID = 0x0003;

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
             * As the preset filters list is empty, all the filters are transmitted using the builder syntax. See the
             * [setAxisFilter](#4-16-2) command documentation for details about the builder syntax.
             * 
             * @param axisId: The axiscode filtered
             * @param filterUidOrBuilder: The filter associated
             */
            public default void onCurrentAxisFilters(int axisId, String filterUidOrBuilder) {}

            /**
             * Sent by the SkyController to notify the end of a [currentAxisFilters](#4-17-0) events list. If the list
             * is empty (e.g. the controller sent a [setAxisFilter](#4-16-2) command which made no change to the filters
             * table), then this command will be sent without any [currentAxisFilters](#4-17-0) event preceding it. This
             * gives the controller a reliable synchronization point when editing mappings.
             */
            public default void onAllCurrentFiltersSent() {}

            /**
             * No preset axis filter is defined on the SkyController, so this command will never be sent by the
             * firmware.
             * 
             * @param filterUid: The filter UID (used in communication with the SkyController)
             * @param name: Display name for the user
             */
            @Deprecated
            public default void onPresetAxisFilters(String filterUid, String name) {}

            /**
             * As the SkyController will never send a [presetAxisFilters](#4-17-2) event, this is the only event sent
             * when the deprecated [getPresetAxisFilters](#4-16-1) command is recieved.
             */
            @Deprecated
            public default void onAllPresetFiltersSent() {}
        }

        private static void currentaxisfilters(Callback cb, int axisId, String filterUidOrBuilder) {
            try {
                cb.onCurrentAxisFilters(axisId, filterUidOrBuilder);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisFiltersState.currentAxisFilters [axis_id: " + axisId + ", filter_uid_or_builder: " + filterUidOrBuilder + "]", e);
            }
        }

        private static void allcurrentfilterssent(Callback cb) {
            try {
                cb.onAllCurrentFiltersSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisFiltersState.allCurrentFiltersSent", e);
            }
        }

        private static void presetaxisfilters(Callback cb, String filterUid, String name) {
            try {
                cb.onPresetAxisFilters(filterUid, name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisFiltersState.presetAxisFilters [filter_uid: " + filterUid + ", name: " + name + "]", e);
            }
        }

        private static void allpresetfilterssent(Callback cb) {
            try {
                cb.onAllPresetFiltersSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.AxisFiltersState.allPresetFiltersSent", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Request infos about the gamepad of the SkyController 
     */
    public static class GamepadInfos {

        /** Feature uid. */
        public static final int UID = 0x0412;

        /**
         * This commands allow the application to get a representation of all the mappable controls on the
         * SkyController. Some physical controls might be absent from this list because their function can not be
         * changed.
         */
        public static ArsdkCommand encodeGetGamepadControls() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeGetGamepadControls(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeGetGamepadControls(long nativeCmd);
    }

    /**
     * Informations about the gamepad of the SkyController 
     */
    public static class GamepadInfosState {

        /** Feature uid. */
        public static final int UID = 0x0413;

        /** Uid of gamepadControl event. */;
        public static final int GAMEPADCONTROL_UID = 0x0000;

        /** Uid of allGamepadControlsSent event. */;
        public static final int ALLGAMEPADCONTROLSSENT_UID = 0x0001;

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
             * Each gamepad control element represents a mappable control on the SkyController. The control can be
             * either a button or an analog axis. Each control have a human-readable english name describing its
             * physical position on the SkyController.
             * 
             * @param type:
             * @param id: The button or axis id A button and an axis can have the same ID, but their type is different
             * @param name: Display name for the control
             */
            public default void onGamepadControl(@Nullable GamepadinfosstateGamepadcontrolType type, int id, String name) {}

            /**
             * This event marks the end of the GamepadControl list
             */
            public default void onAllGamepadControlsSent() {}
        }

        private static void gamepadcontrol(Callback cb, int type, int id, String name) {
            ArsdkFeatureSkyctrl.GamepadinfosstateGamepadcontrolType enumType = ArsdkFeatureSkyctrl.GamepadinfosstateGamepadcontrolType.fromValue(type);
            if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.GamepadinfosstateGamepadcontrolType value " + type);
            try {
                cb.onGamepadControl(enumType, id, name);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.GamepadInfosState.gamepadControl [type: " + type + ", id: " + id + ", name: " + name + "]", e);
            }
        }

        private static void allgamepadcontrolssent(Callback cb) {
            try {
                cb.onAllGamepadControlsSent();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.GamepadInfosState.allGamepadControlsSent", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Configuration of the co-piloting feature 
     */
    public static class CoPiloting {

        /** Feature uid. */
        public static final int UID = 0x0414;

        /**
         * Change who is piloting the drone. By default, the SkyController is the source of piloting commands, and any
         * connected application (i.e. FreeFlight) can not send [piloting commands](#1-0-2) commands directly to the
         * drone. When the piloting source is set to Controller, the SkyController will forward the controller commands
         * to the drone, and won't send any commands itself. The piloting source is automatically reset to SkyController
         * when the controller is disconnected.
         * 
         * @param source:
         */
        public static ArsdkCommand encodeSetPilotingSource(@NonNull CopilotingSetpilotingsourceSource source) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeSetPilotingSource(cmd.getNativePtr(), source.value);
            return cmd;
        }

        private static native int nativeEncodeSetPilotingSource(long nativeCmd, int source);
    }

    /**
     * State of the co-piloting feature 
     */
    public static class CoPilotingState {

        /** Feature uid. */
        public static final int UID = 0x0415;

        /** Uid of pilotingSource event. */;
        public static final int PILOTINGSOURCE_UID = 0x0000;

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
             * Define who is piloting the drone. The piloting source is reset to SkyController when the controller
             * disconnects.
             * 
             * @param source:
             */
            public default void onPilotingSource(@Nullable CopilotingstatePilotingsourceSource source) {}
        }

        private static void pilotingsource(Callback cb, int source) {
            ArsdkFeatureSkyctrl.CopilotingstatePilotingsourceSource enumSource = ArsdkFeatureSkyctrl.CopilotingstatePilotingsourceSource.fromValue(source);
            if (enumSource == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.CopilotingstatePilotingsourceSource value " + source);
            try {
                cb.onPilotingSource(enumSource);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CoPilotingState.pilotingSource [source: " + source + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Commands related to the SkyController sensors calibration 
     */
    public static class Calibration {

        /** Feature uid. */
        public static final int UID = 0x0416;

        /**
         * Asks the SkyController to send (or not) the magneto calibration quality updates. The
         * [MagnetoCalibrationState](#4-23-0) event will always be sent when the status parameters changes, regardless
         * of this setting.
         * 
         * @param enable: Flag to enable the feature: 1 = Enable quality updates 0 = Disable quality updates
         */
        public static ArsdkCommand encodeEnableMagnetoCalibrationQualityUpdates(int enable) {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeEnableMagnetoCalibrationQualityUpdates(cmd.getNativePtr(), enable);
            return cmd;
        }

        /**
         * Asks the SkyController to start a magneto calibration. If the calibration is already started, this command
         * has no effect.
         */
        public static ArsdkCommand encodeStartCalibration() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeStartCalibration(cmd.getNativePtr());
            return cmd;
        }

        /**
         * Asks the SkyController to abort an in-progress magneto calibration. If no calibration is in progress, this
         * command has no effect.
         */
        public static ArsdkCommand encodeAbortCalibration() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeAbortCalibration(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeEnableMagnetoCalibrationQualityUpdates(long nativeCmd, int enable);
        private static native int nativeEncodeStartCalibration(long nativeCmd);
        private static native int nativeEncodeAbortCalibration(long nativeCmd);
    }

    /**
     * State of the SkyController calibration 
     */
    public static class CalibrationState {

        /** Feature uid. */
        public static final int UID = 0x0417;

        /** Uid of MagnetoCalibrationState event. */;
        public static final int MAGNETOCALIBRATIONSTATE_UID = 0x0000;

        /** Uid of MagnetoCalibrationQualityUpdatesState event. */;
        public static final int MAGNETOCALIBRATIONQUALITYUPDATESSTATE_UID = 0x0001;

        /** Uid of MagnetoCalibrationStateV2 event. */;
        public static final int MAGNETOCALIBRATIONSTATEV2_UID = 0x0002;

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
             * The current state of the magnetometer calibration. If the calibration quality updates are enabled, this
             * event is sent at every calibration quality update. This is useful for calibration screens, but creates a
             * lot of traffic on network.\ When the calibration quality updates are disabled, this event is only sent
             * when the status parameter changes.
             * 
             * @param status:
             * @param xQuality: Calibration quality on X axis. 0 is bad, 255 is perfect
             * @param yQuality: Calibration quality on Y axis. 0 is bad, 255 is perfect
             * @param zQuality: Calibration quality on Z axis. 0 is bad, 255 is perfect
             */
            @Deprecated
            public default void onMagnetoCalibrationState(@Nullable CalibrationstateMagnetocalibrationstateStatus status, int xQuality, int yQuality, int zQuality) {}

            /**
             * State of the magnetometer calibration quality sender. This determines the trigger of the
             * [MagnetoCalibrationState](#4-23-0) event.
             * 
             * @param enabled: Flag (is the feature enabled). 1 = The skycontroller sends updated when quality is
             * updated 0 = The skycontroller only sent updated when state is updated
             */
            public default void onMagnetoCalibrationQualityUpdatesState(int enabled) {}

            /**
             * The current state of magnetometer calibration. This event is used by products which does require a step-
             * by-step calibration. When calibrating, the product will go, in order, through X, Y and Z axis
             * calibration, then into calibrated state. If at any point it goes back to not-calibrated, it means that
             * the calibration process failed and should be manually restarted with the [StartCalibration](#4-22-1)
             * command.
             * 
             * @param state:
             */
            public default void onMagnetoCalibrationStateV2(@Nullable CalibrationstateMagnetocalibrationstatev2State state) {}
        }

        private static void magnetocalibrationstate(Callback cb, int status, int xQuality, int yQuality, int zQuality) {
            ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstateStatus enumStatus = ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstateStatus.fromValue(status);
            if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstateStatus value " + status);
            try {
                cb.onMagnetoCalibrationState(enumStatus, xQuality, yQuality, zQuality);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CalibrationState.MagnetoCalibrationState [status: " + status + ", X_Quality: " + xQuality + ", Y_Quality: " + yQuality + ", Z_Quality: " + zQuality + "]", e);
            }
        }

        private static void magnetocalibrationqualityupdatesstate(Callback cb, int enabled) {
            try {
                cb.onMagnetoCalibrationQualityUpdatesState(enabled);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CalibrationState.MagnetoCalibrationQualityUpdatesState [enabled: " + enabled + "]", e);
            }
        }

        private static void magnetocalibrationstatev2(Callback cb, int state) {
            ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstatev2State enumState = ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstatev2State.fromValue(state);
            if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.CalibrationstateMagnetocalibrationstatev2State value " + state);
            try {
                cb.onMagnetoCalibrationStateV2(enumState);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CalibrationState.MagnetoCalibrationStateV2 [state: " + state + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

    /**
     * Events sent on SkyController button presses.
These events are sent under certain conditions only. 
     */
    public static class ButtonEvents {

        /** Feature uid. */
        public static final int UID = 0x0418;

        /** Uid of Settings event. */;
        public static final int SETTINGS_UID = 0x0000;

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
             * This event notifies the application that the settings button was pressed on the device. This allow a
             * connected application to open/close the settings screen from a physical button. This event is only sent
             * when the SkyController is connected to a drone
             */
            public default void onSettings() {}
        }

        private static void settings(Callback cb) {
            try {
                cb.onSettings();
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.ButtonEvents.Settings", e);
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
        public static final int UID = 0x0419;

        /**
         * This command will request a factory reset from the SkyController. *The factory reset procedure implies an
         * automatic reboot*, which will be done immediately after recieving this command.
         */
        public static ArsdkCommand encodeReset() {
            ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
            nativeEncodeReset(cmd.getNativePtr());
            return cmd;
        }

        private static native int nativeEncodeReset(long nativeCmd);
    }

    /**
     * Common skycontroller events 
     */
    public static class CommonEventState {

        /** Feature uid. */
        public static final int UID = 0x041A;

        /** Uid of Shutdown event. */;
        public static final int SHUTDOWN_UID = 0x0000;

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
             * Skycontroller will disconnect. This event is triggered when the user presses on the power button of the
             * product.  **This event is a notification, you can't retrieve it in the cache of the device controller.**
             * 
             * @param reason:
             */
            public default void onShutdown(@Nullable CommoneventstateShutdownReason reason) {}
        }

        private static void shutdown(Callback cb, int reason) {
            ArsdkFeatureSkyctrl.CommoneventstateShutdownReason enumReason = ArsdkFeatureSkyctrl.CommoneventstateShutdownReason.fromValue(reason);
            if (enumReason == null) ULog.e(TAG, "Unsupported ArsdkFeatureSkyctrl.CommoneventstateShutdownReason value " + reason);
            try {
                cb.onShutdown(enumReason);
            } catch (ArsdkCommand.RejectedEventException e) {
                ULog.e(TAG, "Rejected event: skyctrl.CommonEventState.Shutdown [reason: " + reason + "]", e);
            }
        }
        
        private static native int nativeDecode(long nativeCmd, Callback callback);
        private static native void nativeClassInit();

        static { nativeClassInit(); }
    }

}
