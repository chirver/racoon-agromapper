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
 * Generic feature command/event interface.
 */
public class ArsdkFeatureGeneric {

    /**
     * Flags use by maps and lists.
     */
    public enum ListFlags {

        /**
         * indicate it's the first element of the list.
         */
        FIRST(0),

        /**
         * indicate it's the last element of the list.
         */
        LAST(1),

        /**
         * indicate the list is empty (implies First/Last). All other arguments should be ignored.
         */
        EMPTY(2),

        /**
         * This value should be removed from the existing list.
         */
        REMOVE(3);

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
        public static ListFlags fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<ListFlags> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported ListFlags bitfield value " + value);
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
        public static EnumSet<ListFlags> fromBitfield(int bitfield) {
            EnumSet<ListFlags> enums = EnumSet.noneOf(ListFlags.class);
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
        public static int toBitField(@NonNull ListFlags... enums) {
            int bitField = 0;
            for (ListFlags e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private ListFlags(int value) {
            this.value = value;
        }

        private static final SparseArray<ListFlags> MAP;

        static {
            MAP = new SparseArray<>();
            for (ListFlags e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8500;

    /**
     * default
     */
    public static ArsdkCommand encodeDefault() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeDefault(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeEncodeDefault(long nativeCmd);
}
