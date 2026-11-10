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
 * Wifi feature command/event interface.
 */
public class ArsdkFeatureWifi {

    /**
     * The band : 2.4 Ghz or 5 Ghz.
     */
    public enum Band {

        /**
         * 2.4 GHz band.
         */
        E2_4_GHZ(0),

        /**
         * 5 GHz band.
         */
        E5_GHZ(1);

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
        public static Band fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Band> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Band bitfield value " + value);
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
        public static EnumSet<Band> fromBitfield(int bitfield) {
            EnumSet<Band> enums = EnumSet.noneOf(Band.class);
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
        public static int toBitField(@NonNull Band... enums) {
            int bitField = 0;
            for (Band e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Band(int value) {
            this.value = value;
        }

        private static final SparseArray<Band> MAP;

        static {
            MAP = new SparseArray<>();
            for (Band e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The wifi selection type available.
     */
    public enum SelectionType {

        /**
         * Auto selection on all channels.
         */
        AUTO_ALL(0),

        /**
         * Auto selection 2.4ghz.
         */
        AUTO_2_4_GHZ(1),

        /**
         * Auto selection 5 ghz.
         */
        AUTO_5_GHZ(2),

        /**
         * manual selection.
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
        public static SelectionType fromValue(int value) {
            return MAP.get(value, null);
        }

        private SelectionType(int value) {
            this.value = value;
        }

        private static final SparseArray<SelectionType> MAP;

        static {
            MAP = new SparseArray<>();
            for (SelectionType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The type of wifi security (open, wpa2).
     */
    public enum SecurityType {

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
        public static SecurityType fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SecurityType> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported SecurityType bitfield value " + value);
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
        public static EnumSet<SecurityType> fromBitfield(int bitfield) {
            EnumSet<SecurityType> enums = EnumSet.noneOf(SecurityType.class);
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
        public static int toBitField(@NonNull SecurityType... enums) {
            int bitField = 0;
            for (SecurityType e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SecurityType(int value) {
            this.value = value;
        }

        private static final SparseArray<SecurityType> MAP;

        static {
            MAP = new SparseArray<>();
            for (SecurityType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of the key sent.
     */
    public enum SecurityKeyType {

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
        public static SecurityKeyType fromValue(int value) {
            return MAP.get(value, null);
        }

        private SecurityKeyType(int value) {
            this.value = value;
        }

        private static final SparseArray<SecurityKeyType> MAP;

        static {
            MAP = new SparseArray<>();
            for (SecurityKeyType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of environment.
     */
    public enum Environment {

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
        public static Environment fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Environment> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Environment bitfield value " + value);
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
        public static EnumSet<Environment> fromBitfield(int bitfield) {
            EnumSet<Environment> enums = EnumSet.noneOf(Environment.class);
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
        public static int toBitField(@NonNull Environment... enums) {
            int bitField = 0;
            for (Environment e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Environment(int value) {
            this.value = value;
        }

        private static final SparseArray<Environment> MAP;

        static {
            MAP = new SparseArray<>();
            for (Environment e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Type of country selection.
     */
    public enum CountrySelection {

        /**
         * Manual selection.
         */
        MANUAL(0),

        /**
         * Automatic selection.
         */
        AUTO(1);

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
        public static CountrySelection fromValue(int value) {
            return MAP.get(value, null);
        }

        private CountrySelection(int value) {
            this.value = value;
        }

        private static final SparseArray<CountrySelection> MAP;

        static {
            MAP = new SparseArray<>();
            for (CountrySelection e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8700;

    /** Uid of scanned_item event. */;
    public static final int SCANNED_ITEM_UID = 0x0002;

    /** Uid of authorized_channel event. */;
    public static final int AUTHORIZED_CHANNEL_UID = 0x0004;

    /** Uid of ap_channel_changed event. */;
    public static final int AP_CHANNEL_CHANGED_UID = 0x0006;

    /** Uid of security_changed event. */;
    public static final int SECURITY_CHANGED_UID = 0x0008;

    /** Uid of country_changed event. */;
    public static final int COUNTRY_CHANGED_UID = 0x000A;

    /** Uid of environment_changed event. */;
    public static final int ENVIRONMENT_CHANGED_UID = 0x000C;

    /** Uid of rssi_changed event. */;
    public static final int RSSI_CHANGED_UID = 0x000D;

    /** Uid of supported_countries event. */;
    public static final int SUPPORTED_COUNTRIES_UID = 0x000E;

    /** Uid of supported_security_types event. */;
    public static final int SUPPORTED_SECURITY_TYPES_UID = 0x000F;

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
         * Wifi scan results.
         * 
         * @param ssid: SSID of the AP
         * @param rssi: RSSI of the AP.
         * @param band:
         * @param channel: Channel of the AP
         * @param listFlagsBitField:
         */
        public default void onScannedItem(String ssid, int rssi, @Nullable Band band, int channel, int listFlagsBitField) {}

        /**
         * Available channel results.
         * 
         * @param band:
         * @param channel: The channel number
         * @param environmentBitField:
         * @param listFlagsBitField:
         */
        public default void onAuthorizedChannel(@Nullable Band band, int channel, int environmentBitField, int listFlagsBitField) {}

        /**
         * Wifi selection changed.
         * 
         * @param type:
         * @param band:
         * @param channel: The channel of the drone's access point
         */
        public default void onApChannelChanged(@Nullable SelectionType type, @Nullable Band band, int channel) {}

        /**

         * 
         * @param type:
         * @param key: The key to secure the network. Not used if type is open
         * @param keyType:
         */
        public default void onSecurityChanged(@Nullable SecurityType type, String key, @Nullable SecurityKeyType keyType) {}

        /**
         * Wifi country changed.
         * 
         * @param selectionMode:
         * @param code: Country code with ISO 3166 format, empty string means unknown country.
         */
        public default void onCountryChanged(@Nullable CountrySelection selectionMode, String code) {}

        /**
         * Status of the wifi config : either indoor or outdoor.
         * 
         * @param environment: 1 if it uses outdoor wifi settings, 0 otherwise
         */
        public default void onEnvironmentChanged(@Nullable Environment environment) {}

        /**
         * Rssi Changed. This is an information about the Wifi link quality.
         * 
         * @param rssi: Rssi on the connected wifi network. Rssi values are generally between -30 and -120dBm. The
         * nearest of 0 is the better.
         */
        public default void onRssiChanged(int rssi) {}

        /**
         * List of countries supported by the drone.
         * 
         * @param countries: List of country code in ISO 3166 format separated by ";"
         */
        public default void onSupportedCountries(String countries) {}

        /**
         * List of security types supported by the drone.
         * 
         * @param typesBitField: Bitfield of supported security types.
         */
        public default void onSupportedSecurityTypes(int typesBitField) {}
    }

    private static void scannedItem(Callback cb, String ssid, int rssi, int band, int channel, int listFlagsBitField) {
        ArsdkFeatureWifi.Band enumBand = ArsdkFeatureWifi.Band.fromValue(band);
        if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.Band value " + band);
        try {
            cb.onScannedItem(ssid, rssi, enumBand, channel, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.scanned_item [ssid: " + ssid + ", rssi: " + rssi + ", band: " + band + ", channel: " + channel + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void authorizedChannel(Callback cb, int band, int channel, int environmentBitField, int listFlagsBitField) {
        ArsdkFeatureWifi.Band enumBand = ArsdkFeatureWifi.Band.fromValue(band);
        if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.Band value " + band);
        try {
            cb.onAuthorizedChannel(enumBand, channel, environmentBitField, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.authorized_channel [band: " + band + ", channel: " + channel + ", environment: " + environmentBitField + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void apChannelChanged(Callback cb, int type, int band, int channel) {
        ArsdkFeatureWifi.SelectionType enumType = ArsdkFeatureWifi.SelectionType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.SelectionType value " + type);
        ArsdkFeatureWifi.Band enumBand = ArsdkFeatureWifi.Band.fromValue(band);
        if (enumBand == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.Band value " + band);
        try {
            cb.onApChannelChanged(enumType, enumBand, channel);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.ap_channel_changed [type: " + type + ", band: " + band + ", channel: " + channel + "]", e);
        }
    }

    private static void securityChanged(Callback cb, int type, String key, int keyType) {
        ArsdkFeatureWifi.SecurityType enumType = ArsdkFeatureWifi.SecurityType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.SecurityType value " + type);
        ArsdkFeatureWifi.SecurityKeyType enumKeytype = ArsdkFeatureWifi.SecurityKeyType.fromValue(keyType);
        if (enumKeytype == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.SecurityKeyType value " + keyType);
        try {
            cb.onSecurityChanged(enumType, key, enumKeytype);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.security_changed [type: " + type + ", key: " + key + ", key_type: " + keyType + "]", e);
        }
    }

    private static void countryChanged(Callback cb, int selectionMode, String code) {
        ArsdkFeatureWifi.CountrySelection enumSelectionmode = ArsdkFeatureWifi.CountrySelection.fromValue(selectionMode);
        if (enumSelectionmode == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.CountrySelection value " + selectionMode);
        try {
            cb.onCountryChanged(enumSelectionmode, code);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.country_changed [selection_mode: " + selectionMode + ", code: " + code + "]", e);
        }
    }

    private static void environmentChanged(Callback cb, int environment) {
        ArsdkFeatureWifi.Environment enumEnvironment = ArsdkFeatureWifi.Environment.fromValue(environment);
        if (enumEnvironment == null) ULog.e(TAG, "Unsupported ArsdkFeatureWifi.Environment value " + environment);
        try {
            cb.onEnvironmentChanged(enumEnvironment);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.environment_changed [environment: " + environment + "]", e);
        }
    }

    private static void rssiChanged(Callback cb, int rssi) {
        try {
            cb.onRssiChanged(rssi);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.rssi_changed [rssi: " + rssi + "]", e);
        }
    }

    private static void supportedCountries(Callback cb, String countries) {
        try {
            cb.onSupportedCountries(countries);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.supported_countries [countries: " + countries + "]", e);
        }
    }

    private static void supportedSecurityTypes(Callback cb, int typesBitField) {
        try {
            cb.onSupportedSecurityTypes(typesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: wifi.supported_security_types [types: " + typesBitField + "]", e);
        }
    }
    
    /**
     * Launches wifi network scan for a given band to get a list of all wifi networks found by the drone.
     * 
     * @param bandBitField:
     */
    public static ArsdkCommand encodeScan(int bandBitField) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeScan(cmd.getNativePtr(), bandBitField);
        return cmd;
    }

    /**
     * Get all available Wifi channels. The list of available Wifi channels is related to the country of the drone. You
     * can get this country with the event [WifiCountryChanged](#wifi-CountryChanged).
     */
    public static ArsdkCommand encodeUpdateAuthorizedChannels() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeUpdateAuthorizedChannels(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Select channel of choosen band to put the drone's access point on this channel.
     * 
     * @param type:
     * @param band:
     * @param channel: The channel you want to select. Used only when type is manual.
     */
    public static ArsdkCommand encodeSetApChannel(@NonNull SelectionType type, @NonNull Band band, int channel) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetApChannel(cmd.getNativePtr(), type.value, band.value, channel);
        return cmd;
    }

    /**
     * Set the wifi security. The security is changed on the next boot.
     * 
     * @param type:
     * @param key: The key to secure the network. Not used if type is open
     * @param keyType:
     */
    public static ArsdkCommand encodeSetSecurity(@NonNull SecurityType type, String key, @NonNull SecurityKeyType keyType) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetSecurity(cmd.getNativePtr(), type.value, key, keyType.value);
        return cmd;
    }

    /**
     * Set the wifi country.
     * 
     * @param selectionMode:
     * @param code: Country code with ISO 3166 format. Not used if automatic is 1.
     */
    public static ArsdkCommand encodeSetCountry(@NonNull CountrySelection selectionMode, String code) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetCountry(cmd.getNativePtr(), selectionMode.value, code);
        return cmd;
    }

    /**
     * Set indoor or outdoor wifi settings.
     * 
     * @param environment:
     */
    public static ArsdkCommand encodeSetEnvironment(@NonNull Environment environment) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetEnvironment(cmd.getNativePtr(), environment.value);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeScan(long nativeCmd, int band);
    private static native int nativeEncodeUpdateAuthorizedChannels(long nativeCmd);
    private static native int nativeEncodeSetApChannel(long nativeCmd, int type, int band, int channel);
    private static native int nativeEncodeSetSecurity(long nativeCmd, int type, String key, int key_type);
    private static native int nativeEncodeSetCountry(long nativeCmd, int selection_mode, String code);
    private static native int nativeEncodeSetEnvironment(long nativeCmd, int environment);
}
