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
 * User_storage feature command/event interface.
 */
public class ArsdkFeatureUserStorage {

    /**
     * .
     */
    public enum PhyState {

        /**
         * The removable media is not detected.
         */
        UNDETECTED(0),

        /**
         * The removable media is too small for operation.
         */
        TOO_SMALL(1),

        /**
         * The removable media is too slow for operation.
         */
        TOO_SLOW(2),

        /**
         * The removable media is detected.
         */
        AVAILABLE(3),

        /**
         * The removable media is used by mass-storage gadget.
         */
        USB_MASS_STORAGE(4);

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
        public static PhyState fromValue(int value) {
            return MAP.get(value, null);
        }

        private PhyState(int value) {
            this.value = value;
        }

        private static final SparseArray<PhyState> MAP;

        static {
            MAP = new SparseArray<>();
            for (PhyState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum FsState {

        /**
         * The media file system state is not known (yet).
         */
        UNKNOWN(0),

        /**
         * The media file system is not supported, unformatted or capacity is too low.
         */
        FORMAT_NEEDED(1),

        /**
         * The media file system is getting formatted.
         */
        FORMATTING(2),

        /**
         * The media file system is ready to be used.
         */
        READY(3),

        /**
         * The media file system is not available.
         */
        ERROR(4),

        /**
         * The media file system needs a password for decryption.
         */
        PASSWORD_NEEDED(5),

        /**
         * The media file system is being checked.
         */
        CHECKING(6),

        /**
         * The media file system is not managed by the drone itself but accessible by external means.
         */
        EXTERNAL_ACCESS_OK(7);

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
        public static FsState fromValue(int value) {
            return MAP.get(value, null);
        }

        private FsState(int value) {
            this.value = value;
        }

        private static final SparseArray<FsState> MAP;

        static {
            MAP = new SparseArray<>();
            for (FsState e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum Attribute {

        /**
         * The removable media has low performance.
         */
        LOW_PERF(0),

        /**
         * The removable media is encrypted.
         */
        ENCRYPTED(1);

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
        public static Attribute fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Attribute> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Attribute bitfield value " + value);
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
        public static EnumSet<Attribute> fromBitfield(int bitfield) {
            EnumSet<Attribute> enums = EnumSet.noneOf(Attribute.class);
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
        public static int toBitField(@NonNull Attribute... enums) {
            int bitField = 0;
            for (Attribute e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Attribute(int value) {
            this.value = value;
        }

        private static final SparseArray<Attribute> MAP;

        static {
            MAP = new SparseArray<>();
            for (Attribute e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum FormattingResult {

        /**
         * The last formatting command failed.
         */
        ERROR(0),

        /**
         * The last formatting command was denied.
         */
        DENIED(1),

        /**
         * The last formatting command succeeded.
         */
        SUCCESS(2);

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
        public static FormattingResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private FormattingResult(int value) {
            this.value = value;
        }

        private static final SparseArray<FormattingResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (FormattingResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PasswordResult {

        /**
         * The transmitted password is wrong.
         */
        WRONG_PASSWORD(0),

        /**
         * The transmitted password is correct.
         */
        SUCCESS(1),

        /**
         * The usage specified with the password does not match with the current drone context (RECORD or MASS STORAGE).
         */
        WRONG_USAGE(2);

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
        public static PasswordResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private PasswordResult(int value) {
            this.value = value;
        }

        private static final SparseArray<PasswordResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (PasswordResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum Feature {

        /**
         * Format is available when state is `ready` (and also, as usual when state is `format_needed`).
         */
        FORMAT_WHEN_READY_ALLOWED(0),

        /**
         * The format result event is supported and will be sent after a format has been asked.
         */
        FORMAT_RESULT_EVT_SUPPORTED(1),

        /**
         * The format progress event is supported and will be sent during format operation.
         */
        FORMAT_PROGRESS_EVT_SUPPORTED(2),

        /**
         * SD card encryption is supported.
         */
        ENCRYPTION_SUPPORTED(3);

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
        public static Feature fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Feature> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported Feature bitfield value " + value);
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
        public static EnumSet<Feature> fromBitfield(int bitfield) {
            EnumSet<Feature> enums = EnumSet.noneOf(Feature.class);
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
        public static int toBitField(@NonNull Feature... enums) {
            int bitField = 0;
            for (Feature e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Feature(int value) {
            this.value = value;
        }

        private static final SparseArray<Feature> MAP;

        static {
            MAP = new SparseArray<>();
            for (Feature e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum FormattingType {

        /**
         * Formatting which includes deep format operation that is time consuming but optimizes performance.
         */
        FULL(0),

        /**
         * Formatting which removes content of the media.
         */
        QUICK(1);

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
        public static FormattingType fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<FormattingType> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported FormattingType bitfield value " + value);
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
        public static EnumSet<FormattingType> fromBitfield(int bitfield) {
            EnumSet<FormattingType> enums = EnumSet.noneOf(FormattingType.class);
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
        public static int toBitField(@NonNull FormattingType... enums) {
            int bitField = 0;
            for (FormattingType e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private FormattingType(int value) {
            this.value = value;
        }

        private static final SparseArray<FormattingType> MAP;

        static {
            MAP = new SparseArray<>();
            for (FormattingType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum FormattingStep {

        /**
         * The drone is currently partitioning the media.
         */
        PARTITIONING(0),

        /**
         * The drone is currently wiping data on the media in order to optimize performance.
         */
        CLEARING_DATA(1),

        /**
         * The drone is creating a file system on the media.
         */
        CREATING_FS(2);

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
        public static FormattingStep fromValue(int value) {
            return MAP.get(value, null);
        }

        private FormattingStep(int value) {
            this.value = value;
        }

        private static final SparseArray<FormattingStep> MAP;

        static {
            MAP = new SparseArray<>();
            for (FormattingStep e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PasswordUsage {

        /**
         * Send password for record requirement.
         */
        RECORD(0),

        /**
         * Send password for usb mass storage requirement.
         */
        USB(1);

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
        public static PasswordUsage fromValue(int value) {
            return MAP.get(value, null);
        }

        private PasswordUsage(int value) {
            this.value = value;
        }

        private static final SparseArray<PasswordUsage> MAP;

        static {
            MAP = new SparseArray<>();
            for (PasswordUsage e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9100;

    /** Uid of info event. */;
    public static final int INFO_UID = 0x0001;

    /** Uid of monitor event. */;
    public static final int MONITOR_UID = 0x0002;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x0003;

    /** Uid of format_result event. */;
    public static final int FORMAT_RESULT_UID = 0x0007;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0008;

    /** Uid of supported_formatting_types event. */;
    public static final int SUPPORTED_FORMATTING_TYPES_UID = 0x0009;

    /** Uid of format_progress event. */;
    public static final int FORMAT_PROGRESS_UID = 0x000B;

    /** Uid of decryption event. */;
    public static final int DECRYPTION_UID = 0x000E;

    /** Uid of sdcard_uuid event. */;
    public static final int SDCARD_UUID_UID = 0x000F;

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
         * @param name: The name of the media.
         * @param capacity: The capacity of the media in Bytes.
         */
        public default void onInfo(String name, long capacity) {}

        /**

         * 
         * @param availableBytes: The free space in Bytes on the media.
         */
        public default void onMonitor(long availableBytes) {}

        /**

         * 
         * @param physicalState: The state of the physical media.
         * @param fileSystemState: The state of the data stored on media.
         * @param attributeBitField: Details about removable storage.
         * @param monitorEnabled: Boolean. 1 if monitoring is enabled, 0 if not.
         * @param monitorPeriod: Current period of monitoring if monitoring is enabled, 0 if not enabled.
         */
        public default void onState(@Nullable PhyState physicalState, @Nullable FsState fileSystemState, int attributeBitField, int monitorEnabled, int monitorPeriod) {}

        /**

         * 
         * @param result: Result of the last formatting command
         */
        public default void onFormatResult(@Nullable FormattingResult result) {}

        /**
         * Describes user storage supported capabilities.
         * 
         * @param supportedFeaturesBitField: Supported features.
         */
        public default void onCapabilities(int supportedFeaturesBitField) {}

        /**
         * Describes user storage supported formatting types. When sent, it indicates that the `format_with_type`
         * command is supported. If not sent, the `format_with_type` command is not supported.
         * 
         * @param supportedTypesBitField: Supported formatting types.
         */
        public default void onSupportedFormattingTypes(int supportedTypesBitField) {}

        /**

         * 
         * @param step: The formatting step the format process is in.
         * @param percentage: The progress of the current step expressed in percent.
         */
        public default void onFormatProgress(@Nullable FormattingStep step, int percentage) {}

        /**

         * 
         * @param result: Result of the last transmitted password
         */
        public default void onDecryption(@Nullable PasswordResult result) {}

        /**

         * 
         * @param uuid: SDCard UUID
         */
        public default void onSdcardUuid(String uuid) {}
    }

    private static void info(Callback cb, String name, long capacity) {
        try {
            cb.onInfo(name, capacity);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.info [name: " + name + ", capacity: " + capacity + "]", e);
        }
    }

    private static void monitor(Callback cb, long availableBytes) {
        try {
            cb.onMonitor(availableBytes);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.monitor [available_bytes: " + availableBytes + "]", e);
        }
    }

    private static void state(Callback cb, int physicalState, int fileSystemState, int attributeBitField, int monitorEnabled, int monitorPeriod) {
        ArsdkFeatureUserStorage.PhyState enumPhysicalstate = ArsdkFeatureUserStorage.PhyState.fromValue(physicalState);
        if (enumPhysicalstate == null) ULog.e(TAG, "Unsupported ArsdkFeatureUserStorage.PhyState value " + physicalState);
        ArsdkFeatureUserStorage.FsState enumFilesystemstate = ArsdkFeatureUserStorage.FsState.fromValue(fileSystemState);
        if (enumFilesystemstate == null) ULog.e(TAG, "Unsupported ArsdkFeatureUserStorage.FsState value " + fileSystemState);
        try {
            cb.onState(enumPhysicalstate, enumFilesystemstate, attributeBitField, monitorEnabled, monitorPeriod);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.state [physical_state: " + physicalState + ", file_system_state: " + fileSystemState + ", attribute: " + attributeBitField + ", monitor_enabled: " + monitorEnabled + ", monitor_period: " + monitorPeriod + "]", e);
        }
    }

    private static void formatResult(Callback cb, int result) {
        ArsdkFeatureUserStorage.FormattingResult enumResult = ArsdkFeatureUserStorage.FormattingResult.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureUserStorage.FormattingResult value " + result);
        try {
            cb.onFormatResult(enumResult);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.format_result [result: " + result + "]", e);
        }
    }

    private static void capabilities(Callback cb, int supportedFeaturesBitField) {
        try {
            cb.onCapabilities(supportedFeaturesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.capabilities [supported_features: " + supportedFeaturesBitField + "]", e);
        }
    }

    private static void supportedFormattingTypes(Callback cb, int supportedTypesBitField) {
        try {
            cb.onSupportedFormattingTypes(supportedTypesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.supported_formatting_types [supported_types: " + supportedTypesBitField + "]", e);
        }
    }

    private static void formatProgress(Callback cb, int step, int percentage) {
        ArsdkFeatureUserStorage.FormattingStep enumStep = ArsdkFeatureUserStorage.FormattingStep.fromValue(step);
        if (enumStep == null) ULog.e(TAG, "Unsupported ArsdkFeatureUserStorage.FormattingStep value " + step);
        try {
            cb.onFormatProgress(enumStep, percentage);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.format_progress [step: " + step + ", percentage: " + percentage + "]", e);
        }
    }

    private static void decryption(Callback cb, int result) {
        ArsdkFeatureUserStorage.PasswordResult enumResult = ArsdkFeatureUserStorage.PasswordResult.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureUserStorage.PasswordResult value " + result);
        try {
            cb.onDecryption(enumResult);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.decryption [result: " + result + "]", e);
        }
    }

    private static void sdcardUuid(Callback cb, String uuid) {
        try {
            cb.onSdcardUuid(uuid);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: user_storage.sdcard_uuid [uuid: " + uuid + "]", e);
        }
    }
    
    /**
     * Start format operation on the removable media. This could imply its repartitioning if needed. Can be done in
     * state `format_needed` or, if [capabilities](#145-8) contains `format_when_ready_allowed` also in state `ready`.
     * 
     * @param label: Label to set to the file system. If empty, label is set to the product name.
     */
    public static ArsdkCommand encodeFormat(String label) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeFormat(cmd.getNativePtr(), label);
        return cmd;
    }

    /**
     * Start monitoring operation of the removable media.
     * 
     * @param period: Period in seconds to send monitor events. If set to 0, period is set to default value (one
     * second).
     */
    public static ArsdkCommand encodeStartMonitoring(int period) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartMonitoring(cmd.getNativePtr(), period);
        return cmd;
    }

    /**
     * Stop monitoring operation of the removable media.
     */
    public static ArsdkCommand encodeStopMonitoring() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStopMonitoring(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Start format operation on the removable media given a formatting type. This could imply its repartitioning if
     * needed. Can be done in state `format_needed` or, if [capabilities](#145-8) contains `format_when_ready_allowed`
     * also in state `ready`.
     * 
     * @param label: Label to set to the file system. If empty, label is set to the product name.
     * @param type: Formatting type.
     */
    public static ArsdkCommand encodeFormatWithType(String label, @NonNull FormattingType type) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeFormatWithType(cmd.getNativePtr(), label, type.value);
        return cmd;
    }

    /**
     * Transmit password when needed for unlocking encrypted file system
     * 
     * @param password: Password used for SD encryption
     * @param type: Reason of password requirement
     */
    public static ArsdkCommand encodeEncryptionPassword(String password, @NonNull PasswordUsage type) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeEncryptionPassword(cmd.getNativePtr(), password, type.value);
        return cmd;
    }

    /**
     * Start format operation for encryption of the removable media with the given password
     * 
     * @param label: Label to set to the file system. If empty, label is set to the product name.
     * @param password: Password used for SD card encryption
     * @param type: Formatting type.
     */
    public static ArsdkCommand encodeFormatWithEncryption(String label, String password, @NonNull FormattingType type) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeFormatWithEncryption(cmd.getNativePtr(), label, password, type.value);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeFormat(long nativeCmd, String label);
    private static native int nativeEncodeStartMonitoring(long nativeCmd, int period);
    private static native int nativeEncodeStopMonitoring(long nativeCmd);
    private static native int nativeEncodeFormatWithType(long nativeCmd, String label, int type);
    private static native int nativeEncodeEncryptionPassword(long nativeCmd, String password, int type);
    private static native int nativeEncodeFormatWithEncryption(long nativeCmd, String label, String password, int type);
}
