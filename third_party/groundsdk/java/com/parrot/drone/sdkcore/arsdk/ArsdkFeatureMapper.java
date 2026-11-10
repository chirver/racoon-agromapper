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
 * Mapper feature command/event interface.
 */
public class ArsdkFeatureMapper {

    /**
     * The action (mapped on a button).
     */
    public enum ButtonAction {

        /**
         * Action handled by the application.
         */
        APP_0(0),

        /**
         * Action handled by the application.
         */
        APP_1(1),

        /**
         * Action handled by the application.
         */
        APP_2(2),

        /**
         * Action handled by the application.
         */
        APP_3(3),

        /**
         * Action handled by the application.
         */
        APP_4(4),

        /**
         * Action handled by the application.
         */
        APP_5(5),

        /**
         * Action handled by the application.
         */
        APP_6(6),

        /**
         * Action handled by the application.
         */
        APP_7(7),

        /**
         * Action handled by the application.
         */
        APP_8(8),

        /**
         * Action handled by the application.
         */
        APP_9(9),

        /**
         * Action handled by the application.
         */
        APP_10(10),

        /**
         * Action handled by the application.
         */
        APP_11(11),

        /**
         * Action handled by the application.
         */
        APP_12(12),

        /**
         * Action handled by the application.
         */
        APP_13(13),

        /**
         * Action handled by the application.
         */
        APP_14(14),

        /**
         * Action handled by the application.
         */
        APP_15(15),

        /**
         * Return to home.
         */
        RETURN_HOME(16),

        /**
         * Take off or land.
         */
        TAKEOFF_LAND(17),

        /**
         * Start/stop video record.
         */
        VIDEO_RECORD(18),

        /**
         * Take a picture.
         */
        TAKE_PICTURE(19),

        /**
         * Increment camera exposition.
         */
        CAMERA_EXPOSITION_INC(20),

        /**
         * Decrement camera exposition.
         */
        CAMERA_EXPOSITION_DEC(21),

        /**
         * Flip left.
         */
        FLIP_LEFT(22),

        /**
         * Flip right.
         */
        FLIP_RIGHT(23),

        /**
         * Flip front.
         */
        FLIP_FRONT(24),

        /**
         * Flip back.
         */
        FLIP_BACK(25),

        /**
         * Emergency motors shutdown.
         */
        EMERGENCY(26),

        /**
         * Reset camera to its default position.
         */
        CENTER_CAMERA(27),

        /**
         * Cycle between different hud configurations on HDMI (Skycontroller 1 only).
         */
        CYCLE_HUD(28),

        /**
         * In picture mode: take a picture In record mode: start/stop record.
         */
        CAMERA_AUTO(29);

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
         * Action handled by the application.
         */
        APP_0(0),

        /**
         * Action handled by the application.
         */
        APP_1(1),

        /**
         * Action handled by the application.
         */
        APP_2(2),

        /**
         * Action handled by the application.
         */
        APP_3(3),

        /**
         * Action handled by the application.
         */
        APP_4(4),

        /**
         * Action handled by the application.
         */
        APP_5(5),

        /**
         * Action handled by the application.
         */
        APP_6(6),

        /**
         * Action handled by the application.
         */
        APP_7(7),

        /**
         * Action handled by the application.
         */
        APP_8(8),

        /**
         * Action handled by the application.
         */
        APP_9(9),

        /**
         * Action handled by the application.
         */
        APP_10(10),

        /**
         * Action handled by the application.
         */
        APP_11(11),

        /**
         * Action handled by the application.
         */
        APP_12(12),

        /**
         * Action handled by the application.
         */
        APP_13(13),

        /**
         * Action handled by the application.
         */
        APP_14(14),

        /**
         * Action handled by the application.
         */
        APP_15(15),

        /**
         * roll.
         */
        ROLL(16),

        /**
         * pitch.
         */
        PITCH(17),

        /**
         * yaw.
         */
        YAW(18),

        /**
         * gaz.
         */
        GAZ(19),

        /**
         * camera pan.
         */
        CAMERA_PAN(20),

        /**
         * camera tilt.
         */
        CAMERA_TILT(21),

        /**
         * camera zoom.
         */
        CAMERA_ZOOM(22);

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
     * Button event.
     */
    public enum ButtonEvent {

        /**
         * button released.
         */
        RELEASE(0),

        /**
         * button pressed.
         */
        PRESS(1);

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
        public static ButtonEvent fromValue(int value) {
            return MAP.get(value, null);
        }

        private ButtonEvent(int value) {
            this.value = value;
        }

        private static final SparseArray<ButtonEvent> MAP;

        static {
            MAP = new SparseArray<>();
            for (ButtonEvent e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Expo type.
     */
    public enum ExpoType {

        /**
         * No expo applied, axis is linear.
         */
        LINEAR(0),

        /**
         * Light exponential curve.
         */
        EXPO_0(1),

        /**
         * Medium exponential curve.
         */
        EXPO_1(2),

        /**
         * Heavy exponential curve.
         */
        EXPO_2(3),

        /**
         * Maximum exponential curve.
         */
        EXPO_4(4);

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
        public static ExpoType fromValue(int value) {
            return MAP.get(value, null);
        }

        private ExpoType(int value) {
            this.value = value;
        }

        private static final SparseArray<ExpoType> MAP;

        static {
            MAP = new SparseArray<>();
            for (ExpoType e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8A00;

    /** Uid of grab_state event. */;
    public static final int GRAB_STATE_UID = 0x0002;

    /** Uid of grab_button_event event. */;
    public static final int GRAB_BUTTON_EVENT_UID = 0x0003;

    /** Uid of grab_axis_event event. */;
    public static final int GRAB_AXIS_EVENT_UID = 0x0004;

    /** Uid of button_mapping_item event. */;
    public static final int BUTTON_MAPPING_ITEM_UID = 0x0007;

    /** Uid of axis_mapping_item event. */;
    public static final int AXIS_MAPPING_ITEM_UID = 0x0008;

    /** Uid of application_axis_event event. */;
    public static final int APPLICATION_AXIS_EVENT_UID = 0x0009;

    /** Uid of application_button_event event. */;
    public static final int APPLICATION_BUTTON_EVENT_UID = 0x000A;

    /** Uid of expo_map_item event. */;
    public static final int EXPO_MAP_ITEM_UID = 0x000D;

    /** Uid of inverted_map_item event. */;
    public static final int INVERTED_MAP_ITEM_UID = 0x000F;

    /** Uid of active_product event. */;
    public static final int ACTIVE_PRODUCT_UID = 0x0010;

    /** Uid of volatile_mapping_state event. */;
    public static final int VOLATILE_MAPPING_STATE_UID = 0x0013;

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
         * Grabbed buttons are sent to the app and are not handled by the mapper
         * 
         * @param buttons: Grabbed buttons (bitfield)
         * @param axes: Grabbed axes (bitfield)
         * @param buttonsState: For grabbed buttons only. State of the button when the grab starts (bitfield)
         */
        public default void onGrabState(long buttons, long axes, long buttonsState) {}

        /**
         * The state of a grabbed button changes
         * 
         * @param button: Button id
         * @param event:
         */
        public default void onGrabButtonEvent(long button, @Nullable ButtonEvent event) {}

        /**
         * The state of a grabbed axis changes
         * 
         * @param axis: Axis id
         * @param value: Value in range [-100; 100].
         */
        public default void onGrabAxisEvent(long axis, int value) {}

        /**
         * The resulting map describes all active button mappings of the mapper. Each action can only be mapped once per
         * product.
         * 
         * @param uid: Unique ID of the mapping.
         * @param product: Product (see libARDiscovery for list)
         * @param action:
         * @param buttons: Buttons combination mapped to the action (bitfield).
         * @param listFlagsBitField:
         */
        public default void onButtonMappingItem(long uid, int product, @Nullable ButtonAction action, long buttons, int listFlagsBitField) {}

        /**
         * The resulting map describes all active axis mappings of the mapper. Each action can only be mapped once per
         * product.
         * 
         * @param uid: Unique ID of the mapping.
         * @param product: Product (see libARDiscovery for list)
         * @param action:
         * @param axis: The axis number on which the action is mapped.
         * @param buttons: Buttons combination mapped to the action (bitfield).
         * @param listFlagsBitField:
         */
        public default void onAxisMappingItem(long uid, int product, @Nullable AxisAction action, int axis, long buttons, int listFlagsBitField) {}

        /**
         * This event signals the controller application when an application specific axis_event is triggered.
         * Application specific actions are typically used for UI interaction which does not involves the drone.
         * 
         * @param action:
         * @param value: The current value of the axis.
         */
        public default void onApplicationAxisEvent(@Nullable AxisAction action, int value) {}

        /**
         * This event signals the controller application when an application specific button_event is triggered.
         * Application specific actions are typically used for UI interaction which does not involves the drone.
         * 
         * @param action:
         */
        public default void onApplicationButtonEvent(@Nullable ButtonAction action) {}

        /**
         * By default, each axis can have a different expo value. For some products/mappings configuration, the expo
         * values of two axes belonging to the same physical joystick can be locked to the same value. In this case,
         * setting the value for one axis will automatically change both values.
         * 
         * @param uid: Unique ID (for MAP_ITEM type)
         * @param product: Product (see libARDiscovery for list)
         * @param axis: Axis number
         * @param expo:
         * @param listFlagsBitField:
         */
        public default void onExpoMapItem(long uid, int product, int axis, @Nullable ExpoType expo, int listFlagsBitField) {}

        /**
         * Axis inversion has no effect on grabbed axes, nor on virtual buttons that might be generated from axes.
         * 
         * @param uid: Unique ID (for MAP_ITEM type)
         * @param product: Product (see libARDiscovery for list)
         * @param axis: Axis number
         * @param inverted: 0 : Axis not inverted. 1 : Axis inverted
         * @param listFlagsBitField:
         */
        public default void onInvertedMapItem(long uid, int product, int axis, int inverted, int listFlagsBitField) {}

        /**
         * This event notifies the application about the currently active product, thus allowing the application to
         * diplay and edit the current mapping without having to guess from other sources.
         * 
         * @param product: Product (see libARDiscovery for list)
         */
        public default void onActiveProduct(int product) {}

        /**
         * Volatile mapping state
         * 
         * @param active: 0 : Volatile mapping is not active. 1 : Volatile mapping is active.
         */
        public default void onVolatileMappingState(int active) {}
    }

    private static void grabState(Callback cb, long buttons, long axes, long buttonsState) {
        try {
            cb.onGrabState(buttons, axes, buttonsState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.grab_state [buttons: " + buttons + ", axes: " + axes + ", buttons_state: " + buttonsState + "]", e);
        }
    }

    private static void grabButtonEvent(Callback cb, long button, int event) {
        ArsdkFeatureMapper.ButtonEvent enumEvent = ArsdkFeatureMapper.ButtonEvent.fromValue(event);
        if (enumEvent == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.ButtonEvent value " + event);
        try {
            cb.onGrabButtonEvent(button, enumEvent);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.grab_button_event [button: " + button + ", event: " + event + "]", e);
        }
    }

    private static void grabAxisEvent(Callback cb, long axis, int value) {
        try {
            cb.onGrabAxisEvent(axis, value);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.grab_axis_event [axis: " + axis + ", value: " + value + "]", e);
        }
    }

    private static void buttonMappingItem(Callback cb, long uid, int product, int action, long buttons, int listFlagsBitField) {
        ArsdkFeatureMapper.ButtonAction enumAction = ArsdkFeatureMapper.ButtonAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.ButtonAction value " + action);
        try {
            cb.onButtonMappingItem(uid, product, enumAction, buttons, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.button_mapping_item [uid: " + uid + ", product: " + product + ", action: " + action + ", buttons: " + buttons + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void axisMappingItem(Callback cb, long uid, int product, int action, int axis, long buttons, int listFlagsBitField) {
        ArsdkFeatureMapper.AxisAction enumAction = ArsdkFeatureMapper.AxisAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.AxisAction value " + action);
        try {
            cb.onAxisMappingItem(uid, product, enumAction, axis, buttons, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.axis_mapping_item [uid: " + uid + ", product: " + product + ", action: " + action + ", axis: " + axis + ", buttons: " + buttons + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void applicationAxisEvent(Callback cb, int action, int value) {
        ArsdkFeatureMapper.AxisAction enumAction = ArsdkFeatureMapper.AxisAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.AxisAction value " + action);
        try {
            cb.onApplicationAxisEvent(enumAction, value);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.application_axis_event [action: " + action + ", value: " + value + "]", e);
        }
    }

    private static void applicationButtonEvent(Callback cb, int action) {
        ArsdkFeatureMapper.ButtonAction enumAction = ArsdkFeatureMapper.ButtonAction.fromValue(action);
        if (enumAction == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.ButtonAction value " + action);
        try {
            cb.onApplicationButtonEvent(enumAction);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.application_button_event [action: " + action + "]", e);
        }
    }

    private static void expoMapItem(Callback cb, long uid, int product, int axis, int expo, int listFlagsBitField) {
        ArsdkFeatureMapper.ExpoType enumExpo = ArsdkFeatureMapper.ExpoType.fromValue(expo);
        if (enumExpo == null) ULog.e(TAG, "Unsupported ArsdkFeatureMapper.ExpoType value " + expo);
        try {
            cb.onExpoMapItem(uid, product, axis, enumExpo, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.expo_map_item [uid: " + uid + ", product: " + product + ", axis: " + axis + ", expo: " + expo + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void invertedMapItem(Callback cb, long uid, int product, int axis, int inverted, int listFlagsBitField) {
        try {
            cb.onInvertedMapItem(uid, product, axis, inverted, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.inverted_map_item [uid: " + uid + ", product: " + product + ", axis: " + axis + ", inverted: " + inverted + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void activeProduct(Callback cb, int product) {
        try {
            cb.onActiveProduct(product);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.active_product [product: " + product + "]", e);
        }
    }

    private static void volatileMappingState(Callback cb, int active) {
        try {
            cb.onVolatileMappingState(active);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: mapper.volatile_mapping_state [active: " + active + "]", e);
        }
    }
    
    /**
     * Grabbed buttons are sent to the app and are not handled by the mapper
     * 
     * @param buttons: Buttons to grab/ungrab (bitfield)
     * @param axes: Axes to grab/ungrab (bitfield)
     */
    public static ArsdkCommand encodeGrab(long buttons, long axes) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeGrab(cmd.getNativePtr(), buttons, axes);
        return cmd;
    }

    /**
     * An action can only be mapped to one button set. Each action can be mapped to different buttons for different
     * products.
     * 
     * @param product: Product (see libARDiscovery for list)
     * @param action:
     * @param buttons: Buttons combination mapped to the action (bitfield). Set 0 (no button) to unmap an action
     */
    public static ArsdkCommand encodeMapButtonAction(int product, @NonNull ButtonAction action, long buttons) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeMapButtonAction(cmd.getNativePtr(), product, action.value, buttons);
        return cmd;
    }

    /**
     * An action can only be mapped to one axis/button set. Each action can be mapped to different axes/buttons for
     * different products.
     * 
     * @param product: Product (see libARDiscovery for list)
     * @param action:
     * @param axis: The axis number on which the action will be mapped. Set a negative value to unmap the action.
     * @param buttons: Buttons combination mapped to the action (bitfield). Can be zero if no buttons are required.
     */
    public static ArsdkCommand encodeMapAxisAction(int product, @NonNull AxisAction action, int axis, long buttons) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeMapAxisAction(cmd.getNativePtr(), product, action.value, axis, buttons);
        return cmd;
    }

    /**
     * Resets the mappings, axis exponential parameters, and axis inversion for the given product. If the product is
     * given as 0 (zero), the all products are reset.
     * 
     * @param product: The product to reset, or 0 to reset all products.
     */
    public static ArsdkCommand encodeResetMapping(int product) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetMapping(cmd.getNativePtr(), product);
        return cmd;
    }

    /**
     * By default, each axis can have a different expo value. For some products/mappings configuration, the expo values
     * of two axes belonging to the same physical joystick can be locked to the same value. In this case, setting the
     * value for one axis will automatically change both values.
     * 
     * @param product: Product (see libARDiscovery for list). Set to 0 to apply to all products
     * @param axis: Axis number. Set to -1 to apply to all axes.
     * @param expo:
     */
    public static ArsdkCommand encodeSetExpo(int product, int axis, @NonNull ExpoType expo) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetExpo(cmd.getNativePtr(), product, axis, expo.value);
        return cmd;
    }

    /**
     * Axis inversion has no effect on grabbed axes, nor on virtual buttons that might be generated from axes.
     * 
     * @param product: Product (see libARDiscovery for list). Set to 0 to apply to all products
     * @param axis: Axis number
     * @param inverted: 0 : Axis not inverted. 1 : Axis inverted
     */
    public static ArsdkCommand encodeSetInverted(int product, int axis, int inverted) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetInverted(cmd.getNativePtr(), product, axis, inverted);
        return cmd;
    }

    /**
     * All mappings in this mode will disappear when exit will be called or when device is unplugged.
     */
    public static ArsdkCommand encodeEnterVolatileMapping() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeEnterVolatileMapping(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Destroy volatile mapping. Any current action is canceled, for example PCMD will become null even if PCMD axis are
     * pushed, the user has to release the axis to recover PCMD control.
     */
    public static ArsdkCommand encodeExitVolatileMapping() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeExitVolatileMapping(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeGrab(long nativeCmd, long buttons, long axes);
    private static native int nativeEncodeMapButtonAction(long nativeCmd, int product, int action, long buttons);
    private static native int nativeEncodeMapAxisAction(long nativeCmd, int product, int action, int axis, long buttons);
    private static native int nativeEncodeResetMapping(long nativeCmd, int product);
    private static native int nativeEncodeSetExpo(long nativeCmd, int product, int axis, int expo);
    private static native int nativeEncodeSetInverted(long nativeCmd, int product, int axis, int inverted);
    private static native int nativeEncodeEnterVolatileMapping(long nativeCmd);
    private static native int nativeEncodeExitVolatileMapping(long nativeCmd);
}
