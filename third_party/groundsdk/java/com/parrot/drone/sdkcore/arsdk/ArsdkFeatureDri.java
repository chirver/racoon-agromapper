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
 * Dri feature command/event interface.
 */
public class ArsdkFeatureDri {

    /**
     * The DRI mode.
     */
    public enum Mode {

        /**
         * DRI mode is disabled.
         */
        DISABLED(0),

        /**
         * DRI mode is enabled.
         */
        ENABLED(1);

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
        public static Mode fromValue(int value) {
            return MAP.get(value, null);
        }

        private Mode(int value) {
            this.value = value;
        }

        private static final SparseArray<Mode> MAP;

        static {
            MAP = new SparseArray<>();
            for (Mode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Supported capabilities.
     */
    public enum SupportedCapabilities {

        /**
         * Possibility to enable or disable the DRI Mode.
         */
        ON_OFF(0),

        /**
         * French DRI regulation is supported.
         */
        FRENCH_REGULATION(1),

        /**
         * EN 4709-002 DRI regulation is supported.
         */
        EN4709_002_REGULATION(2);

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
        public static SupportedCapabilities fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SupportedCapabilities> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported SupportedCapabilities bitfield value " + value);
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
        public static EnumSet<SupportedCapabilities> fromBitfield(int bitfield) {
            EnumSet<SupportedCapabilities> enums = EnumSet.noneOf(SupportedCapabilities.class);
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
        public static int toBitField(@NonNull SupportedCapabilities... enums) {
            int bitField = 0;
            for (SupportedCapabilities e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SupportedCapabilities(int value) {
            this.value = value;
        }

        private static final SparseArray<SupportedCapabilities> MAP;

        static {
            MAP = new SparseArray<>();
            for (SupportedCapabilities e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * drone ID type.
     */
    public enum IdType {

        /**
         * French 30 bytes format.
         */
        FR_30_OCTETS(0),

        /**
         * ANSI CTA 2063 format on 40 bytes.
         */
        ANSI_CTA_2063(1);

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
        public static IdType fromValue(int value) {
            return MAP.get(value, null);
        }

        private IdType(int value) {
            this.value = value;
        }

        private static final SparseArray<IdType> MAP;

        static {
            MAP = new SparseArray<>();
            for (IdType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * DRI Type.
     */
    public enum DriType {

        /**
         * DRI wifi beacon respects the french regulation.
         */
        FRENCH(0),

        /**
         * DRI wifi beacon respects the EN4709-002 european regulation.
         */
        EN4709_002(1);

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
        public static DriType fromValue(int value) {
            return MAP.get(value, null);
        }

        private DriType(int value) {
            this.value = value;
        }

        private static final SparseArray<DriType> MAP;

        static {
            MAP = new SparseArray<>();
            for (DriType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum Status {

        /**
         * A failure happened during the command execution.
         */
        FAILURE(0),

        /**
         * The related command successfully completed.
         */
        SUCCESS(1),

        /**
         * DRI id is missing or was malformed.
         */
        INVALID_ID(2);

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
        public static Status fromValue(int value) {
            return MAP.get(value, null);
        }

        private Status(int value) {
            this.value = value;
        }

        private static final SparseArray<Status> MAP;

        static {
            MAP = new SparseArray<>();
            for (Status e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0xA400;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0001;

    /** Uid of dri_state event. */;
    public static final int DRI_STATE_UID = 0x0003;

    /** Uid of drone_id event. */;
    public static final int DRONE_ID_UID = 0x0004;

    /** Uid of dri_type event. */;
    public static final int DRI_TYPE_UID = 0x0006;

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
         * @param supportedCapabilitiesBitField: Supported capabilities.
         */
        public default void onCapabilities(int supportedCapabilitiesBitField) {}

        /**

         * 
         * @param mode:
         */
        public default void onDriState(@Nullable Mode mode) {}

        /**

         * 
         * @param type:
         * @param value:
         */
        public default void onDroneId(@Nullable IdType type, String value) {}

        /**

         * 
         * @param id: The ID related to the DRI type as stored by the drone. For EN4709-002 type, it will not include
         * the hyphen and the 3 secure characters which are only used to verify the ID consistency.
         * @param type: The DRI type the DRI system is configured with.
         * @param status: The current status of the DRI configuration.
         */
        public default void onDriType(String id, @Nullable DriType type, @Nullable Status status) {}
    }

    private static void capabilities(Callback cb, int supportedCapabilitiesBitField) {
        try {
            cb.onCapabilities(supportedCapabilitiesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: dri.capabilities [supported_capabilities: " + supportedCapabilitiesBitField + "]", e);
        }
    }

    private static void driState(Callback cb, int mode) {
        ArsdkFeatureDri.Mode enumMode = ArsdkFeatureDri.Mode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureDri.Mode value " + mode);
        try {
            cb.onDriState(enumMode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: dri.dri_state [mode: " + mode + "]", e);
        }
    }

    private static void droneId(Callback cb, int type, String value) {
        ArsdkFeatureDri.IdType enumType = ArsdkFeatureDri.IdType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureDri.IdType value " + type);
        try {
            cb.onDroneId(enumType, value);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: dri.drone_id [type: " + type + ", value: " + value + "]", e);
        }
    }

    private static void driType(Callback cb, String id, int type, int status) {
        ArsdkFeatureDri.DriType enumType = ArsdkFeatureDri.DriType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureDri.DriType value " + type);
        ArsdkFeatureDri.Status enumStatus = ArsdkFeatureDri.Status.fromValue(status);
        if (enumStatus == null) ULog.e(TAG, "Unsupported ArsdkFeatureDri.Status value " + status);
        try {
            cb.onDriType(id, enumType, enumStatus);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: dri.dri_type [id: " + id + ", type: " + type + ", status: " + status + "]", e);
        }
    }
    
    /**
     * Set DRI mode.
     * 
     * @param mode: Mode asked by user
     */
    public static ArsdkCommand encodeDriMode(@NonNull Mode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeDriMode(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    /**
     * Set DRI type
     * 
     * @param type: DRI type.
     * @param id: Complete ID related to the DRI type. It can be empty if not required. For EN4709 type, it should
     * include the 3 secure characters (and the hyphen).
     */
    public static ArsdkCommand encodeSetDriType(@NonNull DriType type, String id) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetDriType(cmd.getNativePtr(), type.value, id);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeDriMode(long nativeCmd, int mode);
    private static native int nativeEncodeSetDriType(long nativeCmd, int type, String id);
}
