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
 * Battery feature command/event interface.
 */
public class ArsdkFeatureBattery {

    /**
     * Battery alerts.
     */
    public enum Alert {

        /**
         * Power level is low.
         */
        POWER_LEVEL(0),

        /**
         * Battery is too hot.
         */
        TOO_HOT(1),

        /**
         * Battery is too cold.
         */
        TOO_COLD(2);

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
        public static Alert fromValue(int value) {
            return MAP.get(value, null);
        }

        private Alert(int value) {
            this.value = value;
        }

        private static final SparseArray<Alert> MAP;

        static {
            MAP = new SparseArray<>();
            for (Alert e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Alert level.
     */
    public enum AlertLevel {

        /**
         * Alert is not active.
         */
        NONE(0),

        /**
         * Alert level is warning.
         */
        WARNING(1),

        /**
         * Alert level is critical.
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
        public static AlertLevel fromValue(int value) {
            return MAP.get(value, null);
        }

        private AlertLevel(int value) {
            this.value = value;
        }

        private static final SparseArray<AlertLevel> MAP;

        static {
            MAP = new SparseArray<>();
            for (AlertLevel e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9500;

    /** Uid of alert event. */;
    public static final int ALERT_UID = 0x0001;

    /** Uid of health event. */;
    public static final int HEALTH_UID = 0x0002;

    /** Uid of voltage event. */;
    public static final int VOLTAGE_UID = 0x0003;

    /** Uid of cycle_count event. */;
    public static final int CYCLE_COUNT_UID = 0x0004;

    /** Uid of serial event. */;
    public static final int SERIAL_UID = 0x0005;

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
         * @param alert: Alert.
         * @param level: Alert level.
         * @param listFlagsBitField:
         */
        public default void onAlert(@Nullable Alert alert, @Nullable AlertLevel level, int listFlagsBitField) {}

        /**

         * 
         * @param stateOfHealth: Battery State-Of-Health (%)
         */
        public default void onHealth(int stateOfHealth) {}

        /**
         * Battery voltage.
         * 
         * @param voltage: Battery voltage in mV
         */
        public default void onVoltage(int voltage) {}

        /**

         * 
         * @param count: Battery Cycle Count
         */
        public default void onCycleCount(long count) {}

        /**

         * 
         * @param serial: Battery Serial
         */
        public default void onSerial(String serial) {}
    }

    private static void alert(Callback cb, int alert, int level, int listFlagsBitField) {
        ArsdkFeatureBattery.Alert enumAlert = ArsdkFeatureBattery.Alert.fromValue(alert);
        if (enumAlert == null) ULog.e(TAG, "Unsupported ArsdkFeatureBattery.Alert value " + alert);
        ArsdkFeatureBattery.AlertLevel enumLevel = ArsdkFeatureBattery.AlertLevel.fromValue(level);
        if (enumLevel == null) ULog.e(TAG, "Unsupported ArsdkFeatureBattery.AlertLevel value " + level);
        try {
            cb.onAlert(enumAlert, enumLevel, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: battery.alert [alert: " + alert + ", level: " + level + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void health(Callback cb, int stateOfHealth) {
        try {
            cb.onHealth(stateOfHealth);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: battery.health [state_of_health: " + stateOfHealth + "]", e);
        }
    }

    private static void voltage(Callback cb, int voltage) {
        try {
            cb.onVoltage(voltage);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: battery.voltage [voltage: " + voltage + "]", e);
        }
    }

    private static void cycleCount(Callback cb, long count) {
        try {
            cb.onCycleCount(count);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: battery.cycle_count [count: " + count + "]", e);
        }
    }

    private static void serial(Callback cb, String serial) {
        try {
            cb.onSerial(serial);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: battery.serial [serial: " + serial + "]", e);
        }
    }
    
    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
}
