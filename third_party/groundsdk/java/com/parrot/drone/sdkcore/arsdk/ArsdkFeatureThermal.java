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
 * Thermal feature command/event interface.
 */
public class ArsdkFeatureThermal {

    /**
     * Thermal state.
     */
    public enum Mode {

        /**
         * Thermal is disabled.
         */
        DISABLED(0),

        /**
         * Standard thermal mode, streaming blended on controller side.
         */
        STANDARD(1),

        /**
         * Blended thermal mode, streaming blended on drone side.
         */
        BLENDED(2);

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
                if (value >= 3) {
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

    /**
     * Rendering mode.
     */
    public enum RenderingMode {

        /**
         * Visible image only.
         */
        VISIBLE(0),

        /**
         * Thermal image only.
         */
        THERMAL(1),

        /**
         * Blending between visible and thermal images.
         */
        BLENDED(2),

        /**
         * Visible image in black and white.
         */
        MONOCHROME(3);

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
        public static RenderingMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private RenderingMode(int value) {
            this.value = value;
        }

        private static final SparseArray<RenderingMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RenderingMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Palette mode.
     */
    public enum PaletteMode {

        /**
         * Absolute palette range.
         */
        ABSOLUTE(0),

        /**
         * Relative palette range.
         */
        RELATIVE(1),

        /**
         * Palette above or under relative threshold.
         */
        SPOT(2);

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
        public static PaletteMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PaletteMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PaletteMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PaletteMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Colorization mode.
     */
    public enum ColorizationMode {

        /**
         * Use black if temperature is outside of palette bounds.
         */
        LIMITED(0),

        /**
         * Use boundary color if temperature is outside of palette bounds.
         */
        EXTENDED(1);

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
        public static ColorizationMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private ColorizationMode(int value) {
            this.value = value;
        }

        private static final SparseArray<ColorizationMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (ColorizationMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Relative range mode.
     */
    public enum RelativeRangeMode {

        /**
         * Relative range is locked.
         */
        LOCKED(0),

        /**
         * Relative range is unlocked.
         */
        UNLOCKED(1);

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
        public static RelativeRangeMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private RelativeRangeMode(int value) {
            this.value = value;
        }

        private static final SparseArray<RelativeRangeMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RelativeRangeMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Spot type.
     */
    public enum SpotType {

        /**
         * Colorize only if temperature is below threshold.
         */
        COLD(0),

        /**
         * Colorize only if temperature is above threshold.
         */
        HOT(1);

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
        public static SpotType fromValue(int value) {
            return MAP.get(value, null);
        }

        private SpotType(int value) {
            this.value = value;
        }

        private static final SparseArray<SpotType> MAP;

        static {
            MAP = new SparseArray<>();
            for (SpotType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Thermal range.
     */
    public enum Range {

        /**
         * High range (from -10 to 400°C).
         */
        HIGH(0),

        /**
         * Low range (from -10 to 140°C).
         */
        LOW(1);

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
        public static Range fromValue(int value) {
            return MAP.get(value, null);
        }

        private Range(int value) {
            this.value = value;
        }

        private static final SparseArray<Range> MAP;

        static {
            MAP = new SparseArray<>();
            for (Range e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Thermal shutter trigger mode.
     */
    public enum ShutterTrigger {

        /**
         * Automatic trigger.
         */
        AUTO(0),

        /**
         * Manual trigger.
         */
        MANUAL(1);

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
        public static ShutterTrigger fromValue(int value) {
            return MAP.get(value, null);
        }

        private ShutterTrigger(int value) {
            this.value = value;
        }

        private static final SparseArray<ShutterTrigger> MAP;

        static {
            MAP = new SparseArray<>();
            for (ShutterTrigger e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9800;

    /** Uid of mode event. */;
    public static final int MODE_UID = 0x0002;

    /** Uid of capabilities event. */;
    public static final int CAPABILITIES_UID = 0x0003;

    /** Uid of palette_part event. */;
    public static final int PALETTE_PART_UID = 0x0005;

    /** Uid of palette_settings event. */;
    public static final int PALETTE_SETTINGS_UID = 0x0007;

    /** Uid of rendering event. */;
    public static final int RENDERING_UID = 0x0009;

    /** Uid of emissivity event. */;
    public static final int EMISSIVITY_UID = 0x000B;

    /** Uid of background_temperature event. */;
    public static final int BACKGROUND_TEMPERATURE_UID = 0x000D;

    /** Uid of sensitivity event. */;
    public static final int SENSITIVITY_UID = 0x000F;

    /** Uid of shutter_mode event. */;
    public static final int SHUTTER_MODE_UID = 0x0011;

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
         * Thermal mode.
         * 
         * @param mode: Current thermal mode.
         */
        public default void onMode(@Nullable Mode mode) {}

        /**

         * 
         * @param modesBitField: Supported thermal modes.
         */
        public default void onCapabilities(int modesBitField) {}

        /**
         * Palette part.
         * 
         * @param red: Red value. Normalized between 0 and 1.
         * @param green: Green value. Normalized between 0 and 1.
         * @param blue: Blue value. Normalized between 0 and 1.
         * @param index: Index where red, green and blue have been applied. Normalized between 0 and 1.
         * @param listFlagsBitField: List flags.
         */
        public default void onPalettePart(float red, float green, float blue, float index, int listFlagsBitField) {}

        /**
         * Used palette settings.
         * 
         * @param mode: Used palette mode.
         * @param lowestTemp: Used lowest temperature in the palette; in Kelvin.
         * @param highestTemp: Used highest temperature in the palette; in Kelvin.
         * @param outsideColorization: Used colorization mode outside of the palette bounds in `absolute` mode.
         * @param relativeRange: Used relative range. Valid only in `relative` mode. If set to `locked`, equivalent to
         * `absolute` mode with lowest_temp, highest_temp and `extended` outside_colorization.
         * @param spotType: Used spot type. Valid only in `spot` mode.
         * @param spotThreshold: Used threshold palette index of the spot. Normalized between 0 and 1. Valid only in
         * `spot` mode.
         */
        public default void onPaletteSettings(@Nullable PaletteMode mode, float lowestTemp, float highestTemp, @Nullable ColorizationMode outsideColorization, @Nullable RelativeRangeMode relativeRange, @Nullable SpotType spotType, float spotThreshold) {}

        /**
         * Rendering parameters for blending.
         * 
         * @param mode: Rendering mode.
         * @param blendingRate: Blending rate, normalized between 0 (visible image and thermal chrominance) and 1
         * (thermal with edges). Valid only if mode is `blended`.
         */
        public default void onRendering(@Nullable RenderingMode mode, float blendingRate) {}

        /**
         * Emissivity value.
         * 
         * @param emissivity: Emissivity. Normalized between 0 and 1.
         */
        public default void onEmissivity(float emissivity) {}

        /**
         * background temperature value.
         * 
         * @param backgroundTemperature: background temperature in Kelvin.
         */
        public default void onBackgroundTemperature(float backgroundTemperature) {}

        /**
         * Thermal cam sensitivity range.
         * 
         * @param currentRange: Current range of the thermal sensor
         */
        public default void onSensitivity(@Nullable Range currentRange) {}

        /**
         * Thermal cam shutter trigger mode.
         * 
         * @param currentTrigger: Current shutter trigger mode of the thermal sensor
         */
        public default void onShutterMode(@Nullable ShutterTrigger currentTrigger) {}
    }

    private static void mode(Callback cb, int mode) {
        ArsdkFeatureThermal.Mode enumMode = ArsdkFeatureThermal.Mode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.Mode value " + mode);
        try {
            cb.onMode(enumMode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.mode [mode: " + mode + "]", e);
        }
    }

    private static void capabilities(Callback cb, int modesBitField) {
        try {
            cb.onCapabilities(modesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.capabilities [modes: " + modesBitField + "]", e);
        }
    }

    private static void palettePart(Callback cb, float red, float green, float blue, float index, int listFlagsBitField) {
        try {
            cb.onPalettePart(red, green, blue, index, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.palette_part [red: " + red + ", green: " + green + ", blue: " + blue + ", index: " + index + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void paletteSettings(Callback cb, int mode, float lowestTemp, float highestTemp, int outsideColorization, int relativeRange, int spotType, float spotThreshold) {
        ArsdkFeatureThermal.PaletteMode enumMode = ArsdkFeatureThermal.PaletteMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.PaletteMode value " + mode);
        ArsdkFeatureThermal.ColorizationMode enumOutsidecolorization = ArsdkFeatureThermal.ColorizationMode.fromValue(outsideColorization);
        if (enumOutsidecolorization == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.ColorizationMode value " + outsideColorization);
        ArsdkFeatureThermal.RelativeRangeMode enumRelativerange = ArsdkFeatureThermal.RelativeRangeMode.fromValue(relativeRange);
        if (enumRelativerange == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.RelativeRangeMode value " + relativeRange);
        ArsdkFeatureThermal.SpotType enumSpottype = ArsdkFeatureThermal.SpotType.fromValue(spotType);
        if (enumSpottype == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.SpotType value " + spotType);
        try {
            cb.onPaletteSettings(enumMode, lowestTemp, highestTemp, enumOutsidecolorization, enumRelativerange, enumSpottype, spotThreshold);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.palette_settings [mode: " + mode + ", lowest_temp: " + lowestTemp + ", highest_temp: " + highestTemp + ", outside_colorization: " + outsideColorization + ", relative_range: " + relativeRange + ", spot_type: " + spotType + ", spot_threshold: " + spotThreshold + "]", e);
        }
    }

    private static void rendering(Callback cb, int mode, float blendingRate) {
        ArsdkFeatureThermal.RenderingMode enumMode = ArsdkFeatureThermal.RenderingMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.RenderingMode value " + mode);
        try {
            cb.onRendering(enumMode, blendingRate);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.rendering [mode: " + mode + ", blending_rate: " + blendingRate + "]", e);
        }
    }

    private static void emissivity(Callback cb, float emissivity) {
        try {
            cb.onEmissivity(emissivity);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.emissivity [emissivity: " + emissivity + "]", e);
        }
    }

    private static void backgroundTemperature(Callback cb, float backgroundTemperature) {
        try {
            cb.onBackgroundTemperature(backgroundTemperature);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.background_temperature [background_temperature: " + backgroundTemperature + "]", e);
        }
    }

    private static void sensitivity(Callback cb, int currentRange) {
        ArsdkFeatureThermal.Range enumCurrentrange = ArsdkFeatureThermal.Range.fromValue(currentRange);
        if (enumCurrentrange == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.Range value " + currentRange);
        try {
            cb.onSensitivity(enumCurrentrange);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.sensitivity [current_range: " + currentRange + "]", e);
        }
    }

    private static void shutterMode(Callback cb, int currentTrigger) {
        ArsdkFeatureThermal.ShutterTrigger enumCurrenttrigger = ArsdkFeatureThermal.ShutterTrigger.fromValue(currentTrigger);
        if (enumCurrenttrigger == null) ULog.e(TAG, "Unsupported ArsdkFeatureThermal.ShutterTrigger value " + currentTrigger);
        try {
            cb.onShutterMode(enumCurrenttrigger);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: thermal.shutter_mode [current_trigger: " + currentTrigger + "]", e);
        }
    }
    
    /**
     * Set thermal mode. Changing thermal mode may activate or deactivate some cameras on some drones.
     * 
     * @param mode: Requested thermal mode.
     */
    public static ArsdkCommand encodeSetMode(@NonNull Mode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetMode(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    /**
     * Set palette value for a given index.
     * 
     * @param red: Requested red value. Normalized between 0 and 1.
     * @param green: Requested green value. Normalized between 0 and 1.
     * @param blue: Requested blue value. Normalized between 0 and 1.
     * @param index: Index in the palette where given red, green and blue should be applied. Normalized between 0 and 1.
     * @param listFlagsBitField: List flags.
     */
    public static ArsdkCommand encodeSetPalettePart(float red, float green, float blue, float index, int listFlagsBitField) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetPalettePart(cmd.getNativePtr(), red, green, blue, index, listFlagsBitField);
        return cmd;
    }

    /**
     * Set palette settings.
     * 
     * @param mode: Requested palette mode.
     * @param lowestTemp: Requested lowest temperature in the palette to use in `absolute` mode. This value is also used
     * when the palette is `relative` and the relative_range is set to `locked`. Unit is Kelvin.
     * @param highestTemp: Requested highest temperature in the palette to use in `absolute` mode. This value is also
     * used when the palette is `relative` and the relative_range is set to `locked`. Unit is Kelvin.
     * @param outsideColorization: Requested colorization mode outside of the palette bounds in `absolute` mode.
     * @param relativeRange: Requested relative range. Valid only in `relative` mode. If set to `locked`, equivalent to
     * `absolute` mode with given lowest_temp, given highest_temp and `extended` outside_colorization.
     * @param spotType: Requested spot type. Valid only in `spot` mode.
     * @param spotThreshold: Requested threshold palette index of the spot. Normalized between 0 and 1. Valid only in
     * `spot` mode.
     */
    public static ArsdkCommand encodeSetPaletteSettings(@NonNull PaletteMode mode, float lowestTemp, float highestTemp, @NonNull ColorizationMode outsideColorization, @NonNull RelativeRangeMode relativeRange, @NonNull SpotType spotType, float spotThreshold) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetPaletteSettings(cmd.getNativePtr(), mode.value, lowestTemp, highestTemp, outsideColorization.value, relativeRange.value, spotType.value, spotThreshold);
        return cmd;
    }

    /**
     * Set rendering parameters for blending.
     * 
     * @param mode: Requested rendering mode.
     * @param blendingRate: Requested blending rate, normalized between 0 (visible image and thermal chrominance) and 1
     * (thermal with edges). Ignored if mode is not `blended`.
     */
    public static ArsdkCommand encodeSetRendering(@NonNull RenderingMode mode, float blendingRate) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetRendering(cmd.getNativePtr(), mode.value, blendingRate);
        return cmd;
    }

    /**
     * Set emissivity value.
     * 
     * @param emissivity: Requested emissivity. Normalized between 0 and 1.
     */
    public static ArsdkCommand encodeSetEmissivity(float emissivity) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetEmissivity(cmd.getNativePtr(), emissivity);
        return cmd;
    }

    /**
     * Set background temperature value.
     * 
     * @param backgroundTemperature: Requested background temperature in Kelvin.
     */
    public static ArsdkCommand encodeSetBackgroundTemperature(float backgroundTemperature) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetBackgroundTemperature(cmd.getNativePtr(), backgroundTemperature);
        return cmd;
    }

    /**
     * Set the thermal camera sensitivity range.
     * 
     * @param range: Desired range of the thermal sensor
     */
    public static ArsdkCommand encodeSetSensitivity(@NonNull Range range) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetSensitivity(cmd.getNativePtr(), range.value);
        return cmd;
    }

    /**
     * Set the thermal camera shutter mode.
     * 
     * @param trigger: Desired shutter trigger mode of the thermal sensor
     */
    public static ArsdkCommand encodeSetShutterMode(@NonNull ShutterTrigger trigger) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetShutterMode(cmd.getNativePtr(), trigger.value);
        return cmd;
    }

    /**
     * Trigg the thermal cam shutter.
     */
    public static ArsdkCommand encodeTriggShutter() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeTriggShutter(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetMode(long nativeCmd, int mode);
    private static native int nativeEncodeSetPalettePart(long nativeCmd, float red, float green, float blue, float index, int list_flags);
    private static native int nativeEncodeSetPaletteSettings(long nativeCmd, int mode, float lowest_temp, float highest_temp, int outside_colorization, int relative_range, int spot_type, float spot_threshold);
    private static native int nativeEncodeSetRendering(long nativeCmd, int mode, float blending_rate);
    private static native int nativeEncodeSetEmissivity(long nativeCmd, float emissivity);
    private static native int nativeEncodeSetBackgroundTemperature(long nativeCmd, float background_temperature);
    private static native int nativeEncodeSetSensitivity(long nativeCmd, int range);
    private static native int nativeEncodeSetShutterMode(long nativeCmd, int trigger);
    private static native int nativeEncodeTriggShutter(long nativeCmd);
}
