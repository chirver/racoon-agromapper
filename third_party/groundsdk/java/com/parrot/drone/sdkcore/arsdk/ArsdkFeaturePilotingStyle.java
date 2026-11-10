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
 * Piloting_style feature command/event interface.
 */
public class ArsdkFeaturePilotingStyle {

    /**
     * Piloting style.
     */
    public enum Style {

        /**
         * Piloting style is standard.
         */
        STANDARD(0),

        /**
         * Piloting style is camera operated, commands are relative to camera pitch.
         */
        CAMERA_OPERATED(1);

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
        public static Style fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Style> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Style bitfield value " + value);
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
        public static EnumSet<Style> fromBitfield(int bitfield) {
            EnumSet<Style> enums = EnumSet.noneOf(Style.class);
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
        public static int toBitField(@NonNull Style... enums) {
            int bitField = 0;
            for (Style e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Style(int value) {
            this.value = value;
        }

        private static final SparseArray<Style> MAP;

        static {
            MAP = new SparseArray<>();
            for (Style e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9B00;

    /** Uid of style event. */;
    public static final int STYLE_UID = 0x0001;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0003;

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
         * @param style: Current Piloting style
         */
        public default void onStyle(@Nullable Style style) {}

        /**

         * 
         * @param stylesBitField: Supported piloting styles.
         */
        public default void onCapabilities(int stylesBitField) {}
    }

    private static void style(Callback cb, int style) {
        ArsdkFeaturePilotingStyle.Style enumStyle = ArsdkFeaturePilotingStyle.Style.fromValue(style);
        if (enumStyle == null) ULog.e(TAG, "Unsupported ArsdkFeaturePilotingStyle.Style value " + style);
        try {
            cb.onStyle(enumStyle);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: piloting_style.style [style: " + style + "]", e);
        }
    }

    private static void capabilities(Callback cb, int stylesBitField) {
        try {
            cb.onCapabilities(stylesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: piloting_style.capabilities [styles: " + stylesBitField + "]", e);
        }
    }
    
    /**
     * Configures piloting style.
     * 
     * @param style: Requested piloting style.
     */
    public static ArsdkCommand encodeSetStyle(@NonNull Style style) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetStyle(cmd.getNativePtr(), style.value);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetStyle(long nativeCmd, int style);
}
