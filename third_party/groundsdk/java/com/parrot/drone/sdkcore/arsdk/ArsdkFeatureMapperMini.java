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
 * Mapper_mini feature command/event interface.
 */
public class ArsdkFeatureMapperMini {

    /**
     * The action (mapped on a button).
     */
    public enum ButtonAction {

        /**
         * Take off or land.
         */
        TAKEOFF_LAND(0),

        /**
         * Take a picture.
         */
        TAKE_PICTURE(1),

        /**
         * Flip left.
         */
        FLIP_LEFT(2),

        /**
         * Flip right.
         */
        FLIP_RIGHT(3),

        /**
         * Flip front.
         */
        FLIP_FRONT(4),

        /**
         * Flip back.
         */
        FLIP_BACK(5),

        /**
         * Emergency motors shutdown.
         */
        EMERGENCY(6),

        /**
         * Launch USB accessory gun action (shoot).
         */
        ACCESSORY_GUN(7),

        /**
         * Thrown take off.
         */
        THROWN_TAKEOFF(8),

        /**
         * 90 deg clockwise swipe.
         */
        CW_90_SWIPE(9),

        /**
         * 90 deg counter clockwise swipe.
         */
        CCW_90_SWIPE(10),

        /**
         * 180 deg clockwise swipe.
         */
        CW_180_SWIPE(11),

        /**
         * 180 deg counter clockwise swipe.
         */
        CCW_180_SWIPE(12),

        /**
         * increase gear.
         */
        GEAR_UP(13),

        /**
         * decrease gear.
         */
        GEAR_DOWN(14),

        /**
         * in plane mode make a 180 deg anticlockwise swipe on roll axis.
         */
        PLANE_MODE_HALF_BAREL_ROLL_RIGHT(15),

        /**
         * in plane mode make a 180 deg clockwise swipe on roll axis.
         */
        PLANE_MODE_HALF_BAREL_ROLL_LEFT(16),

        /**
         * in plane mode make a 180 deg clockwise swipe on pitch axis.
         */
        PLANE_MODE_BACKSWAP(17),

        /**
         * vertical circular loop in plane mode.
         */
        PLANE_MODE_LOOPING(18),

        /**
         * switch between plane mode and quad mode.
         */
        PLANE_MODE_TOGGLE(19),

        /**
         * Launch USB accessory claw action (open/close).
         */
        ACCESSORY_CLAW(20),

        /**
         * switch continuous light (ON/OFF).
         */
        LIGHT_CONTINUOUS(21),

        /**
         * switch blink light (ON/OFF).
         */
        LIGHT_BLINK(22),

        /**
         * switch sinus light (ON/OFF).
         */
        LIGHT_SINUS(23),

        /**
         * toggle between light animations (OFF-continuous-blink-sinus-OFF).
         */
        LIGHT_TOGGLE(24),

        /**
         * toggle between easy and preferred piloting mode.
         */
        PILOTING_MODE_TOGGLE(25),

        /**
         * start or stop video.
         */
        VIDEO_RECORD_TOGGLE(26);

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
        public static ButtonAction fromValue(int value) {
            return MAP.get(value, null);
        }

        private ButtonAction(int value) {
            this.value = value;
        }

        private static final SparseArray<ButtonAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (ButtonAction e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The action (mapped on an axis).
     */
    public enum AxisAction {

        /**
         * roll.
         */
        ROLL(0),

        /**
         * pitch.
         */
        PITCH(1),

        /**
         * yaw.
         */
        YAW(2),

        /**
         * gaz.
         */
        GAZ(3);

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
        public static AxisAction fromValue(int value) {
            return MAP.get(value, null);
        }

        private AxisAction(int value) {
            this.value = value;
        }

        private static final SparseArray<AxisAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (AxisAction e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The piloting mode of the product.
     */
    public enum Mode {

        /**
         * Quadricopter mode.
         */
        QUAD(0),

        /**
         * Plane mode.
         */
        PLANE(1);

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
        public static void each(int bitfield, @NonNull Consumer<Mode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported Mode bitfield value " + value);
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
        public static EnumSet<Mode> fromBitfield(int bitfield) {
            EnumSet<Mode> enums = EnumSet.noneOf(Mode.class);
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
        public static int toBitField(@NonNull Mode... enums) {
            int bitField = 0;
            for (Mode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
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


    /** Feature uid. */
    public static final int UID = 0x8D00;

    /** Uid of button_mapping_item event. */;
    public static final int BUTTON_MAPPING_ITEM_UID = 0x0003;

    /** Uid of axis_mapping_item event. */;
    public static final int AXIS_MAPPING_ITEM_UID = 0x0004;

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
         * The resulting map describes all active button mappings of the product. A mapping can affect one or mode
         * modes, but each action can only be mapped once per mode.
         * 
         * @param uid: Unique ID of the mapping.
         * @param modesBitField: The modes on which the mapping is set
         * @param action:
         * @param buttons: Buttons combination mapped to the action (bitfield).
         * @param listFlagsBitField:
         */
        public default void onButtonMappingItem(int uid, int modesBitField, @Nullable ButtonAction action, long buttons, int listFlagsBitField) {}

        /**
         * The resulting map describes all active axis mappings of the product. A mapping can affect one or mode modes,
         * but each action can only be mapped once per mode.
         * 
         * @param uid: Unique ID of the mapping.
         * @param modesBitField: The modes on which the mapping is set
         * @param action:
         * @param axis: The axis number on which the action is mapped.
         * @param buttons: Buttons combination mapped to the action (bitfield).
         * @param listFlagsBitField:
         */
        public default void onAxisMappingItem(int uid, int modesBitField, @Nullable AxisAction action, int axis, long buttons, int listFlagsBitField) {}
    }

    private static void buttonMappingItem(Callback cb, int uid, int modesBitField, int action, long buttons, int listFlagsBitField) {
        ArsdkFeatureMapperMini.ButtonAction enumAction = ArsdkFeatureMapperMini.ButtonAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapperMini.ButtonAction value " + action);
        try {
            cb.onButtonMappingItem(uid, modesBitField, enumAction, buttons, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper_mini.button_mapping_item [uid: " + uid + ", modes: " + modesBitField + ", action: " + action + ", buttons: " + buttons + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void axisMappingItem(Callback cb, int uid, int modesBitField, int action, int axis, long buttons, int listFlagsBitField) {
        ArsdkFeatureMapperMini.AxisAction enumAction = ArsdkFeatureMapperMini.AxisAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapperMini.AxisAction value " + action);
        try {
            cb.onAxisMappingItem(uid, modesBitField, enumAction, axis, buttons, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper_mini.axis_mapping_item [uid: " + uid + ", modes: " + modesBitField + ", action: " + action + ", axis: " + axis + ", buttons: " + buttons + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }
    
    /**
     * An action can only be mapped to one button set. Each action can be mapped to different buttons for different
     * modes.
     * 
     * @param modesBitField: The modes on which the mapping will be set
     * @param action:
     * @param buttons: Buttons combination mapped to the action (bitfield). Set 0 (no button) to unmap an action
     */
    public static ArsdkCommand encodeMapButtonAction(int modesBitField, @NonNull ButtonAction action, long buttons) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeMapButtonAction(cmd.getNativePtr(), modesBitField, action.value, buttons);
        return cmd;
    }

    /**
     * An action can only be mapped to one axis/button set. Each action can be mapped to different axes/buttons for
     * different modes.
     * 
     * @param modesBitField: The modes on which the mapping will be set
     * @param action:
     * @param axis: The axis number on which the action will be mapped. Set a negative value to unmap the action.
     * @param buttons: Buttons combination mapped to the action (bitfield). Can be zero if no buttons are required.
     */
    public static ArsdkCommand encodeMapAxisAction(int modesBitField, @NonNull AxisAction action, int axis, long buttons) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeMapAxisAction(cmd.getNativePtr(), modesBitField, action.value, axis, buttons);
        return cmd;
    }

    /**
     * Resets the mappings for the given mode(s) to their default value.
     * 
     * @param modesBitField: The mode(s) to reset.
     */
    public static ArsdkCommand encodeResetMapping(int modesBitField) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetMapping(cmd.getNativePtr(), modesBitField);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeMapButtonAction(long nativeCmd, int modes, int action, long buttons);
    private static native int nativeEncodeMapAxisAction(long nativeCmd, int modes, int action, int axis, long buttons);
    private static native int nativeEncodeResetMapping(long nativeCmd, int modes);
}
