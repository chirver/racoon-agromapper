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
 * Camera feature command/event interface.
 */
public class ArsdkFeatureCamera {

    /**
     * Camera model.
     */
    public enum Model {

        /**
         * Main camera, for photo and/or video.
         */
        MAIN(0),

        /**
         * Thermal camera, for photo and/or video.
         */
        THERMAL(1),

        /**
         * Thermal-blended camera, Visible and Thermal stream are blended, for photo and/or video.
         */
        THERMAL_BLENDED(2);

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
        public static Model fromValue(int value) {
            return MAP.get(value, null);
        }

        private Model(int value) {
            this.value = value;
        }

        private static final SparseArray<Model> MAP;

        static {
            MAP = new SparseArray<>();
            for (Model e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Indicate if a feature is supported by the drone.
     */
    public enum Supported {

        /**
         * Not Supported.
         */
        NOT_SUPPORTED(0),

        /**
         * Supported.
         */
        SUPPORTED(1);

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
        public static Supported fromValue(int value) {
            return MAP.get(value, null);
        }

        private Supported(int value) {
            this.value = value;
        }

        private static final SparseArray<Supported> MAP;

        static {
            MAP = new SparseArray<>();
            for (Supported e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Indicate if a feature is available in current mode/configuration.
     */
    public enum Availability {

        /**
         * Not Available.
         */
        NOT_AVAILABLE(0),

        /**
         * Available.
         */
        AVAILABLE(1);

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
        public static Availability fromValue(int value) {
            return MAP.get(value, null);
        }

        private Availability(int value) {
            this.value = value;
        }

        private static final SparseArray<Availability> MAP;

        static {
            MAP = new SparseArray<>();
            for (Availability e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Feature current state.
     */
    public enum State {

        /**
         * Feature is not currently active.
         */
        INACTIVE(0),

        /**
         * Feature is currently active.
         */
        ACTIVE(1);

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
        public static State fromValue(int value) {
            return MAP.get(value, null);
        }

        private State(int value) {
            this.value = value;
        }

        private static final SparseArray<State> MAP;

        static {
            MAP = new SparseArray<>();
            for (State e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Exposure mode.
     */
    public enum ExposureMode {

        /**
         * Automatic shutter speed and iso, balanced.
         */
        AUTOMATIC(0),

        /**
         * Automatic shutter speed and iso, prefer increasing iso sensitivity over using low shutter speed. This mode
         * provides better results when the drone is moving dynamically.
         */
        AUTOMATIC_PREFER_ISO_SENSITIVITY(1),

        /**
         * Automatic shutter speed and iso, prefer reducing shutter speed over using high iso sensitivity. This mode
         * provides better results when the drone is moving slowly.
         */
        AUTOMATIC_PREFER_SHUTTER_SPEED(2),

        /**
         * Manual iso sensitivity, automatic shutter speed.
         */
        MANUAL_ISO_SENSITIVITY(3),

        /**
         * Manual shutter speed, automatic iso.
         */
        MANUAL_SHUTTER_SPEED(4),

        /**
         * Manual iso sensitivity and shutter speed.
         */
        MANUAL(5);

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
        public static ExposureMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<ExposureMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 6) {
                    ULog.e(TAG, "Unsupported ExposureMode bitfield value " + value);
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
        public static EnumSet<ExposureMode> fromBitfield(int bitfield) {
            EnumSet<ExposureMode> enums = EnumSet.noneOf(ExposureMode.class);
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
        public static int toBitField(@NonNull ExposureMode... enums) {
            int bitField = 0;
            for (ExposureMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private ExposureMode(int value) {
            this.value = value;
        }

        private static final SparseArray<ExposureMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (ExposureMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The shutter speed in seconds.
     */
    public enum ShutterSpeed {

        /**
         * 1/10000 sec.
         */
        SHUTTER_1_OVER_10000(0),

        /**
         * 1/8000 sec.
         */
        SHUTTER_1_OVER_8000(1),

        /**
         * 1/6400 sec.
         */
        SHUTTER_1_OVER_6400(2),

        /**
         * 1/5000 sec.
         */
        SHUTTER_1_OVER_5000(3),

        /**
         * 1/4000 sec.
         */
        SHUTTER_1_OVER_4000(4),

        /**
         * 1/3200 sec.
         */
        SHUTTER_1_OVER_3200(5),

        /**
         * 1/2500 sec.
         */
        SHUTTER_1_OVER_2500(6),

        /**
         * 1/2000 sec.
         */
        SHUTTER_1_OVER_2000(7),

        /**
         * 1/1600 sec.
         */
        SHUTTER_1_OVER_1600(8),

        /**
         * 1/1250 sec.
         */
        SHUTTER_1_OVER_1250(9),

        /**
         * 1/1000 sec.
         */
        SHUTTER_1_OVER_1000(10),

        /**
         * 1/800 sec.
         */
        SHUTTER_1_OVER_800(11),

        /**
         * 1/640 sec.
         */
        SHUTTER_1_OVER_640(12),

        /**
         * 1/500 sec.
         */
        SHUTTER_1_OVER_500(13),

        /**
         * 1/400 sec.
         */
        SHUTTER_1_OVER_400(14),

        /**
         * 1/320 sec.
         */
        SHUTTER_1_OVER_320(15),

        /**
         * 1/240 sec.
         */
        SHUTTER_1_OVER_240(16),

        /**
         * 1/200 sec.
         */
        SHUTTER_1_OVER_200(17),

        /**
         * 1/160 sec.
         */
        SHUTTER_1_OVER_160(18),

        /**
         * 1/120 sec.
         */
        SHUTTER_1_OVER_120(19),

        /**
         * 1/100 sec.
         */
        SHUTTER_1_OVER_100(20),

        /**
         * 1/80 sec.
         */
        SHUTTER_1_OVER_80(21),

        /**
         * 1/60 sec.
         */
        SHUTTER_1_OVER_60(22),

        /**
         * 1/50 sec.
         */
        SHUTTER_1_OVER_50(23),

        /**
         * 1/40 sec.
         */
        SHUTTER_1_OVER_40(24),

        /**
         * 1/30 sec.
         */
        SHUTTER_1_OVER_30(25),

        /**
         * 1/25 sec.
         */
        SHUTTER_1_OVER_25(26),

        /**
         * 1/15 sec.
         */
        SHUTTER_1_OVER_15(27),

        /**
         * 1/10 sec.
         */
        SHUTTER_1_OVER_10(28),

        /**
         * 1/8 sec.
         */
        SHUTTER_1_OVER_8(29),

        /**
         * 1/6 sec.
         */
        SHUTTER_1_OVER_6(30),

        /**
         * 1/4 sec.
         */
        SHUTTER_1_OVER_4(31),

        /**
         * 1/3 sec.
         */
        SHUTTER_1_OVER_3(32),

        /**
         * 1/2 sec.
         */
        SHUTTER_1_OVER_2(33),

        /**
         * 1/1.5 sec.
         */
        SHUTTER_1_OVER_1_5(34),

        /**
         * 1 sec.
         */
        SHUTTER_1(35);

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
        public static ShutterSpeed fromValue(int value) {
            return MAP.get(value, null);
        }

        /**
         * Tells whether this enum value is armed in a given bitfield.
         *
         * @param bitfield bitfield to process
         *
         * @returns {@code true} if this enum is in specified bitfield, otherwise {@code false}
         */
        public boolean inBitField(long bitfield) {
            return (bitfield & (1L << value)) != 0;
        }

        /**
         * Applies a function to each armed enum value in a given bitfield.
         *
         * @param bitfield bitfield to process
         * @param func     function to call with each armed enum value
         */
        public static void each(long bitfield, @NonNull Consumer<ShutterSpeed> func) {
            while (bitfield != 0) {
                int value = Long.numberOfTrailingZeros(bitfield);
                if (value >= 36) {
                    ULog.e(TAG, "Unsupported ShutterSpeed bitfield value " + value);
                    break;
                }
                func.accept(fromValue(value));
                bitfield ^= 1L << value;
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
        public static EnumSet<ShutterSpeed> fromBitfield(long bitfield) {
            EnumSet<ShutterSpeed> enums = EnumSet.noneOf(ShutterSpeed.class);
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
        public static long toBitField(@NonNull ShutterSpeed... enums) {
            long bitField = 0;
            for (ShutterSpeed e : enums)
                 bitField |= 1L << e.value;
            return bitField;
        }

        private ShutterSpeed(int value) {
            this.value = value;
        }

        private static final SparseArray<ShutterSpeed> MAP;

        static {
            MAP = new SparseArray<>();
            for (ShutterSpeed e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * ISO Sensitivity levels.
     */
    public enum IsoSensitivity {

        /**
         * ISO 50.
         */
        ISO_50(0),

        /**
         * ISO 64.
         */
        ISO_64(1),

        /**
         * ISO 80.
         */
        ISO_80(2),

        /**
         * ISO 100.
         */
        ISO_100(3),

        /**
         * ISO 125.
         */
        ISO_125(4),

        /**
         * ISO 160.
         */
        ISO_160(5),

        /**
         * ISO 200.
         */
        ISO_200(6),

        /**
         * ISO 250.
         */
        ISO_250(7),

        /**
         * ISO 320.
         */
        ISO_320(8),

        /**
         * ISO 400.
         */
        ISO_400(9),

        /**
         * ISO 500.
         */
        ISO_500(10),

        /**
         * ISO 640.
         */
        ISO_640(11),

        /**
         * ISO 800.
         */
        ISO_800(12),

        /**
         * ISO 1200.
         */
        ISO_1200(13),

        /**
         * ISO 1600.
         */
        ISO_1600(14),

        /**
         * ISO 2500.
         */
        ISO_2500(15),

        /**
         * ISO 3200.
         */
        ISO_3200(16);

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
        public static IsoSensitivity fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<IsoSensitivity> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 17) {
                    ULog.e(TAG, "Unsupported IsoSensitivity bitfield value " + value);
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
        public static EnumSet<IsoSensitivity> fromBitfield(int bitfield) {
            EnumSet<IsoSensitivity> enums = EnumSet.noneOf(IsoSensitivity.class);
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
        public static int toBitField(@NonNull IsoSensitivity... enums) {
            int bitField = 0;
            for (IsoSensitivity e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private IsoSensitivity(int value) {
            this.value = value;
        }

        private static final SparseArray<IsoSensitivity> MAP;

        static {
            MAP = new SparseArray<>();
            for (IsoSensitivity e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Exposure compensation.
     */
    public enum EvCompensation {

        /**
         * -3.00 EV.
         */
        EV_MINUS_3_00(0),

        /**
         * -2.67 EV.
         */
        EV_MINUS_2_67(1),

        /**
         * -2.33 EV.
         */
        EV_MINUS_2_33(2),

        /**
         * -2.00 EV.
         */
        EV_MINUS_2_00(3),

        /**
         * -1.67 EV.
         */
        EV_MINUS_1_67(4),

        /**
         * -1.33 EV.
         */
        EV_MINUS_1_33(5),

        /**
         * -1.00 EV.
         */
        EV_MINUS_1_00(6),

        /**
         * -0.67 EV.
         */
        EV_MINUS_0_67(7),

        /**
         * -0.33 EV.
         */
        EV_MINUS_0_33(8),

        /**
         * 0.00 EV.
         */
        EV_0_00(9),

        /**
         * 0.33 EV.
         */
        EV_0_33(10),

        /**
         * 0.67 EV.
         */
        EV_0_67(11),

        /**
         * 1.00 EV.
         */
        EV_1_00(12),

        /**
         * 1.33 EV.
         */
        EV_1_33(13),

        /**
         * 1.67 EV.
         */
        EV_1_67(14),

        /**
         * 2.00 EV.
         */
        EV_2_00(15),

        /**
         * 2.33 EV.
         */
        EV_2_33(16),

        /**
         * 2.67 EV.
         */
        EV_2_67(17),

        /**
         * 3.00 EV.
         */
        EV_3_00(18);

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
        public static EvCompensation fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<EvCompensation> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 19) {
                    ULog.e(TAG, "Unsupported EvCompensation bitfield value " + value);
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
        public static EnumSet<EvCompensation> fromBitfield(int bitfield) {
            EnumSet<EvCompensation> enums = EnumSet.noneOf(EvCompensation.class);
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
        public static int toBitField(@NonNull EvCompensation... enums) {
            int bitField = 0;
            for (EvCompensation e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private EvCompensation(int value) {
            this.value = value;
        }

        private static final SparseArray<EvCompensation> MAP;

        static {
            MAP = new SparseArray<>();
            for (EvCompensation e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The white balance mode.
     */
    public enum WhiteBalanceMode {

        /**
         * Automatic Estimation of White Balance scales.
         */
        AUTOMATIC(0),

        /**
         * Candle preset.
         */
        CANDLE(1),

        /**
         * Sunset preset.
         */
        SUNSET(2),

        /**
         * Incandescent light preset.
         */
        INCANDESCENT(3),

        /**
         * Warm white fluorescent light preset.
         */
        WARM_WHITE_FLUORESCENT(4),

        /**
         * Halogen light preset.
         */
        HALOGEN(5),

        /**
         * Fluorescent light preset.
         */
        FLUORESCENT(6),

        /**
         * Cool white fluorescent light preset.
         */
        COOL_WHITE_FLUORESCENT(7),

        /**
         * Flash light preset.
         */
        FLASH(8),

        /**
         * Daylight preset.
         */
        DAYLIGHT(9),

        /**
         * Sunny preset.
         */
        SUNNY(10),

        /**
         * Cloudy preset.
         */
        CLOUDY(11),

        /**
         * Snow preset.
         */
        SNOW(12),

        /**
         * Hazy preset.
         */
        HAZY(13),

        /**
         * Shaded preset.
         */
        SHADED(14),

        /**
         * Green foliage preset.
         */
        GREEN_FOLIAGE(15),

        /**
         * Blue sky preset.
         */
        BLUE_SKY(16),

        /**
         * Custom white balance value.
         */
        CUSTOM(17);

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
        public static WhiteBalanceMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<WhiteBalanceMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 18) {
                    ULog.e(TAG, "Unsupported WhiteBalanceMode bitfield value " + value);
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
        public static EnumSet<WhiteBalanceMode> fromBitfield(int bitfield) {
            EnumSet<WhiteBalanceMode> enums = EnumSet.noneOf(WhiteBalanceMode.class);
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
        public static int toBitField(@NonNull WhiteBalanceMode... enums) {
            int bitField = 0;
            for (WhiteBalanceMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private WhiteBalanceMode(int value) {
            this.value = value;
        }

        private static final SparseArray<WhiteBalanceMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (WhiteBalanceMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The white balance temperature.
     */
    public enum WhiteBalanceTemperature {

        /**
         * 1500 K.
         */
        T_1500(0),

        /**
         * 1750 K.
         */
        T_1750(1),

        /**
         * 2000 K.
         */
        T_2000(2),

        /**
         * 2250 K.
         */
        T_2250(3),

        /**
         * 2500 K.
         */
        T_2500(4),

        /**
         * 2750 K.
         */
        T_2750(5),

        /**
         * 3000 K.
         */
        T_3000(6),

        /**
         * 3250 K.
         */
        T_3250(7),

        /**
         * 3500 K.
         */
        T_3500(8),

        /**
         * 3750 K.
         */
        T_3750(9),

        /**
         * 4000 K.
         */
        T_4000(10),

        /**
         * 4250 K.
         */
        T_4250(11),

        /**
         * 4500 K.
         */
        T_4500(12),

        /**
         * 4750 K.
         */
        T_4750(13),

        /**
         * 5000 K.
         */
        T_5000(14),

        /**
         * 5250 K.
         */
        T_5250(15),

        /**
         * 5500 K.
         */
        T_5500(16),

        /**
         * 5750 K.
         */
        T_5750(17),

        /**
         * 6000 K.
         */
        T_6000(18),

        /**
         * 6250 K.
         */
        T_6250(19),

        /**
         * 6500 K.
         */
        T_6500(20),

        /**
         * 6750 K.
         */
        T_6750(21),

        /**
         * 7000 K.
         */
        T_7000(22),

        /**
         * 7250 K.
         */
        T_7250(23),

        /**
         * 7500 K.
         */
        T_7500(24),

        /**
         * 7750 K.
         */
        T_7750(25),

        /**
         * 8000 K.
         */
        T_8000(26),

        /**
         * 8250 K.
         */
        T_8250(27),

        /**
         * 8500 K.
         */
        T_8500(28),

        /**
         * 8750 K.
         */
        T_8750(29),

        /**
         * 9000 K.
         */
        T_9000(30),

        /**
         * 9250 K.
         */
        T_9250(31),

        /**
         * 9500 K.
         */
        T_9500(32),

        /**
         * 9750 K.
         */
        T_9750(33),

        /**
         * 10000 K.
         */
        T_10000(34),

        /**
         * 10250 K.
         */
        T_10250(35),

        /**
         * 10500 K.
         */
        T_10500(36),

        /**
         * 10750 K.
         */
        T_10750(37),

        /**
         * 11000 K.
         */
        T_11000(38),

        /**
         * 11250 K.
         */
        T_11250(39),

        /**
         * 11500 K.
         */
        T_11500(40),

        /**
         * 11750 K.
         */
        T_11750(41),

        /**
         * 12000 K.
         */
        T_12000(42),

        /**
         * 12250 K.
         */
        T_12250(43),

        /**
         * 12500 K.
         */
        T_12500(44),

        /**
         * 12750 K.
         */
        T_12750(45),

        /**
         * 13000 K.
         */
        T_13000(46),

        /**
         * 13250 K.
         */
        T_13250(47),

        /**
         * 13500 K.
         */
        T_13500(48),

        /**
         * 13750 K.
         */
        T_13750(49),

        /**
         * 14000 K.
         */
        T_14000(50),

        /**
         * 14250 K.
         */
        T_14250(51),

        /**
         * 14500 K.
         */
        T_14500(52),

        /**
         * 14750 K.
         */
        T_14750(53),

        /**
         * 15000 K.
         */
        T_15000(54);

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
        public static WhiteBalanceTemperature fromValue(int value) {
            return MAP.get(value, null);
        }

        /**
         * Tells whether this enum value is armed in a given bitfield.
         *
         * @param bitfield bitfield to process
         *
         * @returns {@code true} if this enum is in specified bitfield, otherwise {@code false}
         */
        public boolean inBitField(long bitfield) {
            return (bitfield & (1L << value)) != 0;
        }

        /**
         * Applies a function to each armed enum value in a given bitfield.
         *
         * @param bitfield bitfield to process
         * @param func     function to call with each armed enum value
         */
        public static void each(long bitfield, @NonNull Consumer<WhiteBalanceTemperature> func) {
            while (bitfield != 0) {
                int value = Long.numberOfTrailingZeros(bitfield);
                if (value >= 55) {
                    ULog.e(TAG, "Unsupported WhiteBalanceTemperature bitfield value " + value);
                    break;
                }
                func.accept(fromValue(value));
                bitfield ^= 1L << value;
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
        public static EnumSet<WhiteBalanceTemperature> fromBitfield(long bitfield) {
            EnumSet<WhiteBalanceTemperature> enums = EnumSet.noneOf(WhiteBalanceTemperature.class);
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
        public static long toBitField(@NonNull WhiteBalanceTemperature... enums) {
            long bitField = 0;
            for (WhiteBalanceTemperature e : enums)
                 bitField |= 1L << e.value;
            return bitField;
        }

        private WhiteBalanceTemperature(int value) {
            this.value = value;
        }

        private static final SparseArray<WhiteBalanceTemperature> MAP;

        static {
            MAP = new SparseArray<>();
            for (WhiteBalanceTemperature e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Images style.
     */
    public enum Style {

        /**
         * Natural look style.
         */
        STANDARD(0),

        /**
         * Parrot Log, produce flat and desaturated images, best for post-processing.
         */
        PLOG(1),

        /**
         * Intense style: bright colors, warm shade, high contrast.
         */
        INTENSE(2),

        /**
         * Pastel style: soft colors, cold shade, low contrast.
         */
        PASTEL(3);

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
                if (value >= 4) {
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

    /**
     * Camera mode.
     */
    public enum CameraMode {

        /**
         * Camera is in recording mode.
         */
        RECORDING(0),

        /**
         * Camera is in photo mode.
         */
        PHOTO(1);

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
        public static CameraMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<CameraMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported CameraMode bitfield value " + value);
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
        public static EnumSet<CameraMode> fromBitfield(int bitfield) {
            EnumSet<CameraMode> enums = EnumSet.noneOf(CameraMode.class);
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
        public static int toBitField(@NonNull CameraMode... enums) {
            int bitField = 0;
            for (CameraMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private CameraMode(int value) {
            this.value = value;
        }

        private static final SparseArray<CameraMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (CameraMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum RecordingMode {

        /**
         * Standard mode.
         */
        STANDARD(0),

        /**
         * Create an accelerated video by dropping some frame at a user specified rate define by `hyperlapse_value`.
         */
        HYPERLAPSE(1),

        /**
         * Record x2 or x4 slowed-down videos.
         */
        SLOW_MOTION(2),

        /**
         * Record high-framerate videos (playback speed is x1).
         */
        HIGH_FRAMERATE(3);

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
        public static RecordingMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<RecordingMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported RecordingMode bitfield value " + value);
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
        public static EnumSet<RecordingMode> fromBitfield(int bitfield) {
            EnumSet<RecordingMode> enums = EnumSet.noneOf(RecordingMode.class);
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
        public static int toBitField(@NonNull RecordingMode... enums) {
            int bitField = 0;
            for (RecordingMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private RecordingMode(int value) {
            this.value = value;
        }

        private static final SparseArray<RecordingMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (RecordingMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * .
     */
    public enum PhotoMode {

        /**
         * Single shot mode.
         */
        SINGLE(0),

        /**
         * Bracketing mode. Takes a burst of 3 or 5 frames with a different exposure.
         */
        BRACKETING(1),

        /**
         * Burst mode. Takes burst of frames.
         */
        BURST(2),

        /**
         * Time-lapse mode. Takes frames at a regular time interval.
         */
        TIME_LAPSE(3),

        /**
         * GPS-lapse mode. Takse frames at a regular GPS position interval.
         */
        GPS_LAPSE(4);

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
        public static PhotoMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<PhotoMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported PhotoMode bitfield value " + value);
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
        public static EnumSet<PhotoMode> fromBitfield(int bitfield) {
            EnumSet<PhotoMode> enums = EnumSet.noneOf(PhotoMode.class);
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
        public static int toBitField(@NonNull PhotoMode... enums) {
            int bitField = 0;
            for (PhotoMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private PhotoMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PhotoMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PhotoMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video resolution.
     */
    public enum Resolution {

        /**
         * 4096x2160 pixels (4k cinema).
         */
        RES_DCI_4K(0),

        /**
         * 3840x2160 pixels (UHD).
         */
        RES_UHD_4K(1),

        /**
         * 2704x1524 pixels.
         */
        RES_2_7K(2),

        /**
         * 1920x1080 pixels (Full HD).
         */
        RES_1080P(3),

        /**
         * 1280x720 pixels (HD).
         */
        RES_720P(4),

        /**
         * 856x480 pixels.
         */
        RES_480P(5),

        /**
         * 1440x1080 pixels (SD).
         */
        RES_1080P_SD(6),

        /**
         * 960x720 pixels (SD).
         */
        RES_720P_SD(7),

        /**
         * 7680x4320 pixels (UHD).
         */
        RES_UHD_8K(8),

        /**
         * 5120x2880 pixels.
         */
        RES_5K(9);

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
        public static Resolution fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Resolution> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 10) {
                    ULog.e(TAG, "Unsupported Resolution bitfield value " + value);
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
        public static EnumSet<Resolution> fromBitfield(int bitfield) {
            EnumSet<Resolution> enums = EnumSet.noneOf(Resolution.class);
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
        public static int toBitField(@NonNull Resolution... enums) {
            int bitField = 0;
            for (Resolution e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Resolution(int value) {
            this.value = value;
        }

        private static final SparseArray<Resolution> MAP;

        static {
            MAP = new SparseArray<>();
            for (Resolution e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video recording frame rate.
     */
    public enum Framerate {

        /**
         * 23.97 fps.
         */
        FPS_24(0),

        /**
         * 25 fps.
         */
        FPS_25(1),

        /**
         * 29.97 fps.
         */
        FPS_30(2),

        /**
         * 47.952 fps.
         */
        FPS_48(3),

        /**
         * 50 fps.
         */
        FPS_50(4),

        /**
         * 59.94 fps.
         */
        FPS_60(5),

        /**
         * 95.88 fps.
         */
        FPS_96(6),

        /**
         * 100 fps.
         */
        FPS_100(7),

        /**
         * 119.88 fps.
         */
        FPS_120(8),

        /**
         * 9 fps. For thermal only, capture triggered by thermal sensor.
         */
        FPS_9(9),

        /**
         * 15 fps.
         */
        FPS_15(10),

        /**
         * 20 fps.
         */
        FPS_20(11),

        /**
         * 191.81 fps.
         */
        FPS_192(12),

        /**
         * 200 fps.
         */
        FPS_200(13),

        /**
         * 239.76 fps.
         */
        FPS_240(14),

        /**
         * 10 fps. For thermal only, capture triggered by thermal sensor.
         */
        FPS_10(15),

        /**
         * 8.57 fps. For thermal only, capture triggered by thermal sensor.
         */
        FPS_8_6(16);

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
        public static Framerate fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Framerate> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 17) {
                    ULog.e(TAG, "Unsupported Framerate bitfield value " + value);
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
        public static EnumSet<Framerate> fromBitfield(int bitfield) {
            EnumSet<Framerate> enums = EnumSet.noneOf(Framerate.class);
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
        public static int toBitField(@NonNull Framerate... enums) {
            int bitField = 0;
            for (Framerate e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Framerate(int value) {
            this.value = value;
        }

        private static final SparseArray<Framerate> MAP;

        static {
            MAP = new SparseArray<>();
            for (Framerate e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The photo format.
     */
    public enum PhotoFormat {

        /**
         * Sensor full resolution, not dewarped.
         */
        FULL_FRAME(0),

        /**
         * Rectilinear projection, dewarped.
         */
        RECTILINEAR(1);

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
        public static PhotoFormat fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<PhotoFormat> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported PhotoFormat bitfield value " + value);
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
        public static EnumSet<PhotoFormat> fromBitfield(int bitfield) {
            EnumSet<PhotoFormat> enums = EnumSet.noneOf(PhotoFormat.class);
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
        public static int toBitField(@NonNull PhotoFormat... enums) {
            int bitField = 0;
            for (PhotoFormat e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private PhotoFormat(int value) {
            this.value = value;
        }

        private static final SparseArray<PhotoFormat> MAP;

        static {
            MAP = new SparseArray<>();
            for (PhotoFormat e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * The photo format.
     */
    public enum PhotoFileFormat {

        /**
         * photo recorded in JPEG format.
         */
        JPEG(0),

        /**
         * photo recorded in DNG format.
         */
        DNG(1),

        /**
         * photo recorded in both DNG and JPEG format.
         */
        DNG_JPEG(2);

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
        public static PhotoFileFormat fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<PhotoFileFormat> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported PhotoFileFormat bitfield value " + value);
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
        public static EnumSet<PhotoFileFormat> fromBitfield(int bitfield) {
            EnumSet<PhotoFileFormat> enums = EnumSet.noneOf(PhotoFileFormat.class);
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
        public static int toBitField(@NonNull PhotoFileFormat... enums) {
            int bitField = 0;
            for (PhotoFileFormat e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private PhotoFileFormat(int value) {
            this.value = value;
        }

        private static final SparseArray<PhotoFileFormat> MAP;

        static {
            MAP = new SparseArray<>();
            for (PhotoFileFormat e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Anti-flicker mode.
     */
    public enum AntiflickerMode {

        /**
         * Anti-flicker off.
         */
        OFF(0),

        /**
         * Auto detect.
         */
        AUTO(1),

        /**
         * force the exposure time to be an integer multiple of 10ms.
         */
        MODE_50HZ(2),

        /**
         * force the exposure time to be an integer multiple of 8.33ms.
         */
        MODE_60HZ(3);

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
        public static AntiflickerMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<AntiflickerMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported AntiflickerMode bitfield value " + value);
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
        public static EnumSet<AntiflickerMode> fromBitfield(int bitfield) {
            EnumSet<AntiflickerMode> enums = EnumSet.noneOf(AntiflickerMode.class);
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
        public static int toBitField(@NonNull AntiflickerMode... enums) {
            int bitField = 0;
            for (AntiflickerMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private AntiflickerMode(int value) {
            this.value = value;
        }

        private static final SparseArray<AntiflickerMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (AntiflickerMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Values for hyperlapse mode.
     */
    public enum HyperlapseValue {

        /**
         * Record 1 of 15 frames.
         */
        RATIO_15(0),

        /**
         * Record 1 of 30 frames.
         */
        RATIO_30(1),

        /**
         * Record 1 of 60 frames.
         */
        RATIO_60(2),

        /**
         * Record 1 of 120 frames.
         */
        RATIO_120(3),

        /**
         * Record 1 of 240 frames.
         */
        RATIO_240(4);

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
        public static HyperlapseValue fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<HyperlapseValue> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported HyperlapseValue bitfield value " + value);
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
        public static EnumSet<HyperlapseValue> fromBitfield(int bitfield) {
            EnumSet<HyperlapseValue> enums = EnumSet.noneOf(HyperlapseValue.class);
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
        public static int toBitField(@NonNull HyperlapseValue... enums) {
            int bitField = 0;
            for (HyperlapseValue e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private HyperlapseValue(int value) {
            this.value = value;
        }

        private static final SparseArray<HyperlapseValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (HyperlapseValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Values for burst photo mode.
     */
    public enum BurstValue {

        /**
         * Record 14 picture over 4 second.
         */
        BURST_14_OVER_4S(0),

        /**
         * Record 14 picture over 2 second.
         */
        BURST_14_OVER_2S(1),

        /**
         * Record 14 picture over 1 second.
         */
        BURST_14_OVER_1S(2),

        /**
         * Record 10 picture over 4 second.
         */
        BURST_10_OVER_4S(3),

        /**
         * Record 10 picture over 2 second.
         */
        BURST_10_OVER_2S(4),

        /**
         * Record 10 picture over 1 second.
         */
        BURST_10_OVER_1S(5),

        /**
         * Record 4 picture over 4 second.
         */
        BURST_4_OVER_4S(6),

        /**
         * Record 4 picture over 2 second.
         */
        BURST_4_OVER_2S(7),

        /**
         * Record 4 picture over 1 second.
         */
        BURST_4_OVER_1S(8);

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
        public static BurstValue fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<BurstValue> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 9) {
                    ULog.e(TAG, "Unsupported BurstValue bitfield value " + value);
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
        public static EnumSet<BurstValue> fromBitfield(int bitfield) {
            EnumSet<BurstValue> enums = EnumSet.noneOf(BurstValue.class);
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
        public static int toBitField(@NonNull BurstValue... enums) {
            int bitField = 0;
            for (BurstValue e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private BurstValue(int value) {
            this.value = value;
        }

        private static final SparseArray<BurstValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (BurstValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Bracketing mode preset.
     */
    public enum BracketingPreset {

        /**
         * 3 frames, with EV compensation of [-1 EV, 0 EV, +1 EV].
         */
        PRESET_1EV(0),

        /**
         * 3 frames, with EV compensation of [-2 EV, 0 EV, +2 EV].
         */
        PRESET_2EV(1),

        /**
         * 3 frames, with EV compensation of [-3 EV, 0 EV, +3 EV].
         */
        PRESET_3EV(2),

        /**
         * 5 frames, with EV compensation of [-2 EV, -1 EV, 0 EV, +1 EV, +2 EV].
         */
        PRESET_1EV_2EV(3),

        /**
         * 5 frames, with EV compensation of [-3 EV, -1 EV, 0 EV, +1 EV, +3 EV].
         */
        PRESET_1EV_3EV(4),

        /**
         * 5 frames, with EV compensation of [-3 EV, -2 EV, 0 EV, +2 EV, +3 EV].
         */
        PRESET_2EV_3EV(5),

        /**
         * 7 frames, with EV compensation of [-3 EV, -2 EV, -1 EV, 0 EV, +1 EV, +2 EV, +3 EV].
         */
        PRESET_1EV_2EV_3EV(6);

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
        public static BracketingPreset fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<BracketingPreset> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 7) {
                    ULog.e(TAG, "Unsupported BracketingPreset bitfield value " + value);
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
        public static EnumSet<BracketingPreset> fromBitfield(int bitfield) {
            EnumSet<BracketingPreset> enums = EnumSet.noneOf(BracketingPreset.class);
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
        public static int toBitField(@NonNull BracketingPreset... enums) {
            int bitField = 0;
            for (BracketingPreset e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private BracketingPreset(int value) {
            this.value = value;
        }

        private static final SparseArray<BracketingPreset> MAP;

        static {
            MAP = new SparseArray<>();
            for (BracketingPreset e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Video stream mode.
     */
    public enum StreamingMode {

        /**
         * Minimize latency with average reliability (best for piloting).
         */
        LOW_LATENCY(0),

        /**
         * Maximize the reliability with an average latency (best when streaming quality is important but not the
         * latency).
         */
        HIGH_RELIABILITY(1),

        /**
         * Maximize the reliability using a framerate decimation with an average latency (best when streaming quality is
         * important but not the latency).
         */
        HIGH_RELIABILITY_LOW_FRAMERATE(2);

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
        public static StreamingMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<StreamingMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported StreamingMode bitfield value " + value);
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
        public static EnumSet<StreamingMode> fromBitfield(int bitfield) {
            EnumSet<StreamingMode> enums = EnumSet.noneOf(StreamingMode.class);
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
        public static int toBitField(@NonNull StreamingMode... enums) {
            int bitField = 0;
            for (StreamingMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private StreamingMode(int value) {
            this.value = value;
        }

        private static final SparseArray<StreamingMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (StreamingMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Result for command `take_photo`.
     */
    public enum PhotoResult {

        /**
         * Taking a new photo.
         */
        TAKING_PHOTO(0),

        /**
         * A photo has been taken.
         */
        PHOTO_TAKEN(1),

        /**
         * A photo has been saved to the file system.
         */
        PHOTO_SAVED(2),

        /**
         * Error taking photo: not enough space in storage.
         */
        ERROR_NO_STORAGE_SPACE(3),

        /**
         * Error taking photo: wrong state.
         */
        ERROR_BAD_STATE(4),

        /**
         * Error taking photo: generic error.
         */
        ERROR(5);

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
        public static PhotoResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private PhotoResult(int value) {
            this.value = value;
        }

        private static final SparseArray<PhotoResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (PhotoResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Start/Stop recording result.
     */
    public enum RecordingResult {

        /**
         * Recording started.
         */
        STARTED(0),

        /**
         * Recording stopped.
         */
        STOPPED(1),

        /**
         * Recording stopped because storage is full.
         */
        STOPPED_NO_STORAGE_SPACE(2),

        /**
         * Recording stopped because storage write speed is too slow.
         */
        STOPPED_STORAGE_TOO_SLOW(3),

        /**
         * Error starting recording: wrong state.
         */
        ERROR_BAD_STATE(4),

        /**
         * Error starting or during recording.
         */
        ERROR(5),

        /**
         * Recording stopped because of internal reconfiguration.
         */
        STOPPED_RECONFIGURED(6);

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
        public static RecordingResult fromValue(int value) {
            return MAP.get(value, null);
        }

        private RecordingResult(int value) {
            this.value = value;
        }

        private static final SparseArray<RecordingResult> MAP;

        static {
            MAP = new SparseArray<>();
            for (RecordingResult e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Zoom control mode.
     */
    public enum ZoomControlMode {

        /**
         * Zoom is set by giving a level.
         */
        LEVEL(0),

        /**
         * Zoom is set by giving a velocity.
         */
        VELOCITY(1);

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
        public static ZoomControlMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private ZoomControlMode(int value) {
            this.value = value;
        }

        private static final SparseArray<ZoomControlMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (ZoomControlMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Auto Exposure metering mode.
     */
    public enum AutoExposureMeteringMode {

        /**
         * Default Auto Exposure metering mode.
         */
        STANDARD(0),

        /**
         * Auto Exposure metering mode which favours the center top of the matrix.
         */
        CENTER_TOP(1);

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
        public static AutoExposureMeteringMode fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<AutoExposureMeteringMode> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported AutoExposureMeteringMode bitfield value " + value);
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
        public static EnumSet<AutoExposureMeteringMode> fromBitfield(int bitfield) {
            EnumSet<AutoExposureMeteringMode> enums = EnumSet.noneOf(AutoExposureMeteringMode.class);
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
        public static int toBitField(@NonNull AutoExposureMeteringMode... enums) {
            int bitField = 0;
            for (AutoExposureMeteringMode e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private AutoExposureMeteringMode(int value) {
            this.value = value;
        }

        private static final SparseArray<AutoExposureMeteringMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (AutoExposureMeteringMode e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8F00;

    /** Uid of camera_capabilities event. */;
    public static final int CAMERA_CAPABILITIES_UID = 0x0001;

    /** Uid of recording_capabilities event. */;
    public static final int RECORDING_CAPABILITIES_UID = 0x0002;

    /** Uid of photo_capabilities event. */;
    public static final int PHOTO_CAPABILITIES_UID = 0x0003;

    /** Uid of antiflicker_capabilities event. */;
    public static final int ANTIFLICKER_CAPABILITIES_UID = 0x0004;

    /** Uid of exposure_settings event. */;
    public static final int EXPOSURE_SETTINGS_UID = 0x0009;

    /** Uid of exposure event. */;
    public static final int EXPOSURE_UID = 0x000A;

    /** Uid of white_balance event. */;
    public static final int WHITE_BALANCE_UID = 0x000D;

    /** Uid of ev_compensation event. */;
    public static final int EV_COMPENSATION_UID = 0x000F;

    /** Uid of antiflicker_mode event. */;
    public static final int ANTIFLICKER_MODE_UID = 0x0011;

    /** Uid of style event. */;
    public static final int STYLE_UID = 0x0014;

    /** Uid of zoom_level event. */;
    public static final int ZOOM_LEVEL_UID = 0x0016;

    /** Uid of zoom_info event. */;
    public static final int ZOOM_INFO_UID = 0x0017;

    /** Uid of max_zoom_speed event. */;
    public static final int MAX_ZOOM_SPEED_UID = 0x0018;

    /** Uid of zoom_velocity_quality_degradation event. */;
    public static final int ZOOM_VELOCITY_QUALITY_DEGRADATION_UID = 0x001A;

    /** Uid of hdr_setting event. */;
    public static final int HDR_SETTING_UID = 0x001D;

    /** Uid of hdr event. */;
    public static final int HDR_UID = 0x001E;

    /** Uid of camera_mode event. */;
    public static final int CAMERA_MODE_UID = 0x0020;

    /** Uid of recording_mode event. */;
    public static final int RECORDING_MODE_UID = 0x0022;

    /** Uid of photo_mode event. */;
    public static final int PHOTO_MODE_UID = 0x0024;

    /** Uid of streaming_mode event. */;
    public static final int STREAMING_MODE_UID = 0x0026;

    /** Uid of photo_progress event. */;
    public static final int PHOTO_PROGRESS_UID = 0x0028;

    /** Uid of photo_state event. */;
    public static final int PHOTO_STATE_UID = 0x0029;

    /** Uid of recording_progress event. */;
    public static final int RECORDING_PROGRESS_UID = 0x002C;

    /** Uid of recording_state event. */;
    public static final int RECORDING_STATE_UID = 0x002D;

    /** Uid of autorecord event. */;
    public static final int AUTORECORD_UID = 0x002F;

    /** Uid of camera_states event. */;
    public static final int CAMERA_STATES_UID = 0x0031;

    /** Uid of next_photo_delay event. */;
    public static final int NEXT_PHOTO_DELAY_UID = 0x0033;

    /** Uid of alignment_offsets event. */;
    public static final int ALIGNMENT_OFFSETS_UID = 0x0034;

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
         * Describes camera supported capabilities.
         * 
         * @param camId: id of the camera. Camera id is unique and persistent: the same camera model on a same drone
         * model always has the same id. Main/Built-in camera has id zero.
         * @param model: Camera model.
         * @param exposureModesBitField: Supported exposure modes.
         * @param exposureLockSupported: Exposure lock support.
         * @param exposureRoiLockSupported: Exposure lock on ROI support.
         * @param evCompensationsBitField: Supported ev compensation values. Empty if ev_compensation is not supported.
         * @param whiteBalanceModesBitField: Supported white balances modes.
         * @param customWhiteBalanceTemperaturesBitField: Supported white balance temperature for "custom" white balance
         * mode. Empty if "custom" mode is not supported.
         * @param whiteBalanceLockSupported: White balance lock support.
         * @param stylesBitField: Supported image styles.
         * @param cameraModesBitField: Supported camera modes.
         * @param hyperlapseValuesBitField: Supported values for hyperlapse recording mode. Empty of hyperlapse
         * recording mode is not supported.
         * @param bracketingPresetsBitField: Supported values for bracketing photo mode. Empty of bracketing photo mode
         * is not supported.
         * @param burstValuesBitField: Supported values for burst photo mode. Empty of burst photo mode is not
         * supported.
         * @param streamingModesBitField: Supported streaming modes, Empty if streaming is not supported.
         * @param timelapseIntervalMin: Minimal time-lapse capture interval, in seconds.
         * @param gpslapseIntervalMin: Minimal GPS-lapse capture interval, in meters.
         * @param autoExposureMeteringModesBitField: Supported auto exposure metering modes
         */
        public default void onCameraCapabilities(int camId, @Nullable Model model, int exposureModesBitField, @Nullable Supported exposureLockSupported, @Nullable Supported exposureRoiLockSupported, long evCompensationsBitField, int whiteBalanceModesBitField, long customWhiteBalanceTemperaturesBitField, @Nullable Supported whiteBalanceLockSupported, int stylesBitField, int cameraModesBitField, int hyperlapseValuesBitField, int bracketingPresetsBitField, int burstValuesBitField, int streamingModesBitField, float timelapseIntervalMin, float gpslapseIntervalMin, int autoExposureMeteringModesBitField) {}

        /**
         * Describe recording capabilities. Each entry of this list gives valid resolutions/framerates pair for the
         * listed modes and if HDR is supported in this configuration. The same mode can be in multiple entries.
         * 
         * @param id: Setting id. U8 msb is cam_id of the related camera.
         * @param recordingModesBitField: Recording modes this capability applies to.
         * @param resolutionsBitField: Supported resolutions in specified modes and framerates.
         * @param frameratesBitField: Supported framerates in specified modes and resolutions.
         * @param hdr: Indicate if hdr is supported in this configuration.
         * @param listFlagsBitField: List flags.
         */
        public default void onRecordingCapabilities(int id, int recordingModesBitField, int resolutionsBitField, int frameratesBitField, @Nullable Supported hdr, int listFlagsBitField) {}

        /**
         * Describe photo capabilities. Each entry of this list gives a valid format/fileformat pair for the listed
         * modes and if HDR is supported in this configuration. The same mode can be in multiple entries.
         * 
         * @param id: Setting id. U8 msb is cam_id of the related camera.
         * @param photoModesBitField: Photo modes this capability applies to.
         * @param photoFormatsBitField: Supported photo formats in specified modes and file formats (DNG file will
         * always be full-frame, regardless of this setting).
         * @param photoFileFormatsBitField: Supported photo file formats in specified modes and formats.
         * @param hdr: Indicate if hdr is supported in this configuration.
         * @param listFlagsBitField: List flags.
         */
        public default void onPhotoCapabilities(int id, int photoModesBitField, int photoFormatsBitField, int photoFileFormatsBitField, @Nullable Supported hdr, int listFlagsBitField) {}

        /**
         * Describe anti-flickering. Antiflickering is global for all cameras
         * 
         * @param supportedModesBitField: Supported anti-flicker mode.
         */
        public default void onAntiflickerCapabilities(int supportedModesBitField) {}

        /**
         * Notify current exposure settings. This can be different from the actually used exposure values notified by
         * event [exposure](#143-10) if the mode is not `manual`.
         * 
         * @param camId: Id of the camera.
         * @param mode: Exposure mode as set by command "set_exposure_mode".
         * @param manualShutterSpeed: Shutter speed as set by command "set_manual_shutter_speed".
         * @param manualShutterSpeedCapabilitiesBitField: Supported shutter speeds for current photo or recording
         * configuration. Empty if "manual" or "manual_shutter_speed" exposure modes are not supported.
         * @param manualIsoSensitivity: ISO sensitivity level as set by command "set_manual_iso_sensitivity".
         * @param manualIsoSensitivityCapabilitiesBitField: Supported manual iso sensitivity for current photo or
         * recording configuration. Empty if "manual" or "manual_iso_sensitivity" exposure modes are not supported.
         * @param maxIsoSensitivity: Maximum ISO sensitivity level as set by command "set_max_iso_sensitivity".
         * @param maxIsoSensitivitiesCapabilitiesBitField: Supported max iso sensitivity for current photo or recording
         * configuration. Empty if setting max iso sensitivity is not supported.
         * @param meteringMode: Auto Exposure metering mode.
         */
        public default void onExposureSettings(int camId, @Nullable ExposureMode mode, @Nullable ShutterSpeed manualShutterSpeed, long manualShutterSpeedCapabilitiesBitField, @Nullable IsoSensitivity manualIsoSensitivity, long manualIsoSensitivityCapabilitiesBitField, @Nullable IsoSensitivity maxIsoSensitivity, long maxIsoSensitivitiesCapabilitiesBitField, @Nullable AutoExposureMeteringMode meteringMode) {}

        /**
         * Notify of actual exposure values (different from [exposure_settings](#143-9) values when one of the setting
         * is in automatic mode).
         * 
         * @param camId: Id of the camera.
         * @param shutterSpeed: Effective shutter speed.
         * @param isoSensitivity: Effective ISO sensitivity level.
         * @param lock: Auto exposure lock state.
         * @param lockRoiX: Auto exposure lock ROI center on x axis, between 0 and 1, relative to streaming image width,
         * less than 0 if exposure is not locked with on a ROI.
         * @param lockRoiY: Auto exposure lock ROI center on y axis, between 0 and 1, relative to streaming image
         * height, less than if exposure is not locked with on a ROI.
         * @param lockRoiWidth: Auto exposure lock ROI width, between 0 and 1, relative to streaming image width, less
         * than if exposure is not locked with on a ROI.
         * @param lockRoiHeight: Auto exposure lock ROI height, between 0 and 1, relative to streaming image height less
         * than if exposure is not locked with on a ROI.
         */
        public default void onExposure(int camId, @Nullable ShutterSpeed shutterSpeed, @Nullable IsoSensitivity isoSensitivity, @Nullable State lock, float lockRoiX, float lockRoiY, float lockRoiWidth, float lockRoiHeight) {}

        /**
         * Notify of actual white balance mode
         * 
         * @param camId: Id of the camera.
         * @param mode: Actual white balance mode.
         * @param temperature: Actual white balance temperature if the mode `custom`, invalid else.
         * @param lock: White balance lock state.
         */
        public default void onWhiteBalance(int camId, @Nullable WhiteBalanceMode mode, @Nullable WhiteBalanceTemperature temperature, @Nullable State lock) {}

        /**
         * Notify of actual EV compensation
         * 
         * @param camId: Id of the camera.
         * @param value: Actual EV compensation value.
         */
        public default void onEvCompensation(int camId, @Nullable EvCompensation value) {}

        /**
         * Notify of actual anti-flicker mode
         * 
         * @param mode: Anti-flicker mode as set by [set_antiflicker_mode](#143-16).
         * @param value: Actual anti-flicker value selected by the drone. When `mode` is `auto`, indicate the actual
         * anti-flicker value selected by the drone. (50hz or 60hz) In all other modes, this is the same that `mode`
         */
        public default void onAntiflickerMode(@Nullable AntiflickerMode mode, @Nullable AntiflickerMode value) {}

        /**
         * Notify current style and its saturation, contrast and sharpness values.
         * 
         * @param camId: Id of the camera.
         * @param style: Active style.
         * @param saturation: Actual saturation value for this style.
         * @param saturationMin: Minimum supported value for style saturation.
         * @param saturationMax: Maximum supported value for style saturation.
         * @param contrast: Actual contrast value for this style.
         * @param contrastMin: Minimum supported value for style contrast.
         * @param contrastMax: Maximum supported value for style contrast.
         * @param sharpness: Actual sharpness value for this style.
         * @param sharpnessMin: Minimum supported value for style sharpness.
         * @param sharpnessMax: Maximum supported value for style sharpness.
         */
        public default void onStyle(int camId, @Nullable Style style, int saturation, int saturationMin, int saturationMax, int contrast, int contrastMin, int contrastMax, int sharpness, int sharpnessMin, int sharpnessMax) {}

        /**
         * Current camera zoom level.
         * 
         * @param camId: Id of the camera.
         * @param level: Actual zoom level. Ignored if `available` is `not_available`.
         */
        public default void onZoomLevel(int camId, float level) {}

        /**
         * Zoom information. This event is never sent if the device doesn't have a zoom.
         * 
         * @param camId: Id of the camera.
         * @param available: Tells if zoom is available in the current configuration.
         * @param highQualityMaximumLevel: Maximum zoom level without degrading image quality. Ignored if `available` is
         * `not_available`.
         * @param maximumLevel: Maximum zoom level with image quality degradation. Ignored if `available` is
         * `not_available`. Same value than `high_quality_maximum_level` if there is no digital zoom with quality
         * degradation.
         */
        public default void onZoomInfo(int camId, @Nullable Availability available, float highQualityMaximumLevel, float maximumLevel) {}

        /**
         * Max zoom speed setting. This setting contains the range and the current value. All values are expressed as
         * the tangent of the angle in degrees per seconds.
         * 
         * @param camId: Id of the camera.
         * @param min: Minimal bound of the max zoom speed range. Expressed as a tan(deg) / sec.
         * @param max: Maximal bound of the max zoom speed range Expressed as a tan(deg) / sec.
         * @param current: Current max zoom speed. Expressed as a tan(deg) / sec.
         */
        public default void onMaxZoomSpeed(int camId, float min, float max, float current) {}

        /**
         * Whether zoom change by indicating a velocity is allowed to go on a zoom level that degrades video quality. If
         * not allowed, zoom level will stop at the level given by the `high_quality_maximum_level` of the
         * [Zoom](143-20) event.
         * 
         * @param camId: Id of the camera.
         * @param allowed: 1 if quality degradation is allowed, 0 otherwise.
         */
        public default void onZoomVelocityQualityDegradation(int camId, int allowed) {}

        /**
         * Notify of camera HDR setting.
         * 
         * @param camId: Id of the camera.
         * @param value: Actual HDR setting value.
         */
        public default void onHdrSetting(int camId, @Nullable State value) {}

        /**
         * Tells if HDR is available and if it's currently active.
         * 
         * @param camId: Id of the camera.
         * @param available: Tells if HDR is available in current configuration.
         * @param state: Actual HDR state.
         */
        public default void onHdr(int camId, @Nullable Availability available, @Nullable State state) {}

        /**
         * Notify of camera mode
         * 
         * @param camId: Id of the camera.
         * @param mode: Camera mode.
         */
        public default void onCameraMode(int camId, @Nullable CameraMode mode) {}

        /**
         * Notify of camera recording mode
         * 
         * @param camId: Id of the camera.
         * @param mode: Camera camera recording mode.
         * @param resolution: Recording resolution.
         * @param framerate: Recording framerate.
         * @param hyperlapse: Hyperlapse value when the recording mode is hyperlapse. Invalid in other modes.
         * @param bitrate: Recording bitrate for current configuration (bits/s). Zero if unavailable.
         */
        public default void onRecordingMode(int camId, @Nullable RecordingMode mode, @Nullable Resolution resolution, @Nullable Framerate framerate, @Nullable HyperlapseValue hyperlapse, long bitrate) {}

        /**
         * Notify of camera photo mode
         * 
         * @param camId: Id of the camera.
         * @param mode: Camera photo mode.
         * @param format: Actual format.
         * @param fileFormat: Actual photo file format.
         * @param burst: Actual burst value when the photo mode is burst. Invalid in other modes.
         * @param bracketing: Actual bracketing value when the photo mode is bracketing. Invalid in other modes.
         * @param captureInterval: Actual time-lapse interval value (in seconds) when the photo mode is time_lapse.
         * Actual GPS-lapse interval value (in meters) when the photo mode is gps_lapse. Ignored in other modes.
         */
        public default void onPhotoMode(int camId, @Nullable PhotoMode mode, @Nullable PhotoFormat format, @Nullable PhotoFileFormat fileFormat, @Nullable BurstValue burst, @Nullable BracketingPreset bracketing, float captureInterval) {}

        /**
         * Notify of actual streaming mode setting.
         * 
         * @param camId: Id of the camera.
         * @param value: Actual streaming mode setting.
         */
        public default void onStreamingMode(int camId, @Nullable StreamingMode value) {}

        /**
         * Sent as progress and result of [take_photo](#143-39) command. This event is not sent during the connection.
         * 
         * @param camId: Id of the camera.
         * @param result: Progress or result value: - `taking_photo` indicate that the camera starts taking photo (or
         * multiple photos when mode is `burst` or `bracketing`). - `photo_taken` indicate that one photo has been taken
         * and is about be saved to disk. In `bracketing` mode, this event is sent when the last photo of the bracketing
         * sequence has been taken. In `burst` mode this event is sent after each photo but maximum every 100ms. -
         * `photo_saved` indicate the media containing the photo has been saved to disk. In `burst` or `bracketing`
         * mode, indicate that all photos of the burst or bracketing sequence have been saved to disk. Other results are
         * errors.
         * @param photoCount: Only valid when result is `photo_taken`, indicate the number of photo taken in the
         * sequence.
         * @param mediaId: Only valid when result is `photo_saved`, indicate the media id containing taken photo(s).
         */
        public default void onPhotoProgress(int camId, @Nullable PhotoResult result, int photoCount, String mediaId) {}

        /**
         * Current photo camera state. Indicates if the camera is ready to take a photo.
         * 
         * @param camId: Id of the camera.
         * @param available: Tell if photo feature is available in current mode.
         * @param state: Tell if photo feature is currently active.
         */
        public default void onPhotoState(int camId, @Nullable Availability available, @Nullable State state) {}

        /**
         * Sent when recording state change. This event is not sent during the connection.
         * 
         * @param camId: Id of the camera.
         * @param result: Current recording result. Indicate if recording has started/stopped.
         * @param mediaId: Recorded media_id. Only valid when result is `stopped` or `stopped_no_storage_space`.
         */
        public default void onRecordingProgress(int camId, @Nullable RecordingResult result, String mediaId) {}

        /**
         * Current recording state. Indicates if the camera is currently recording.
         * 
         * @param camId: Id of the camera.
         * @param available: Tell if recording feature is available in current mode.
         * @param state: Current recording state.
         * @param startTimestamp: If state is `active`, the timestamp if the start of the recording, in milliseconds
         * since 00:00:00 UTC on 1 January 1970.
         */
        public default void onRecordingState(int camId, @Nullable Availability available, @Nullable State state, long startTimestamp) {}

        /**

         * 
         * @param camId: Id of the camera.
         * @param state: Auto-record state.
         */
        public default void onAutorecord(int camId, @Nullable State state) {}

        /**
         * Current camera state. Indicates which cameras are currently active.
         * 
         * @param activeCameras: Bitfield showing which cameras are active. A camera is active   when the bit
         * corresponding to its cam_id is 1. A camera is inactive when the bit corresponding to its cam_id is 0.
         */
        public default void onCameraStates(long activeCameras) {}

        /**
         * Remaining time or distance before next photo.
         * 
         * @param mode: Selected mode: only `time_lapse` and `gps_lapse` are supported
         * @param remaining: In time_lapse photo_mode: remaining time in seconds before next photo In gps_lapse
         * photo_mode: remaining distance in meters before next photo
         */
        public default void onNextPhotoDelay(@Nullable PhotoMode mode, float remaining) {}

        /**

         * 
         * @param camId: Id of the camera.
         * @param minBoundYaw: Lower bound of the alignment offset that can be set on the yaw axis, in degrees
         * @param maxBoundYaw: Upper bound of the alignment offset that can be set on the yaw axis, in degrees
         * @param currentYaw: Current alignment offset applied to the yaw axis, in degrees
         * @param minBoundPitch: Lower bound of the alignment offset that can be set on the pitch axis, in degrees
         * @param maxBoundPitch: Upper bound of the alignment offset that can be set on the pitch axis, in degrees
         * @param currentPitch: Current alignment offset applied to the pitch axis, in degrees
         * @param minBoundRoll: Lower bound of the alignment offset that can be set on the roll axis, in degrees
         * @param maxBoundRoll: Upper bound of the alignment offset that can be set on the roll axis, in degrees
         * @param currentRoll: Current alignment offset applied to the roll axis, in degrees
         */
        public default void onAlignmentOffsets(int camId, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {}
    }

    private static void cameraCapabilities(Callback cb, int camId, int model, int exposureModesBitField, int exposureLockSupported, int exposureRoiLockSupported, long evCompensationsBitField, int whiteBalanceModesBitField, long customWhiteBalanceTemperaturesBitField, int whiteBalanceLockSupported, int stylesBitField, int cameraModesBitField, int hyperlapseValuesBitField, int bracketingPresetsBitField, int burstValuesBitField, int streamingModesBitField, float timelapseIntervalMin, float gpslapseIntervalMin, int autoExposureMeteringModesBitField) {
        ArsdkFeatureCamera.Model enumModel = ArsdkFeatureCamera.Model.fromValue(model);
        if (enumModel == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Model value " + model);
        ArsdkFeatureCamera.Supported enumExposurelocksupported = ArsdkFeatureCamera.Supported.fromValue(exposureLockSupported);
        if (enumExposurelocksupported == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Supported value " + exposureLockSupported);
        ArsdkFeatureCamera.Supported enumExposureroilocksupported = ArsdkFeatureCamera.Supported.fromValue(exposureRoiLockSupported);
        if (enumExposureroilocksupported == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Supported value " + exposureRoiLockSupported);
        ArsdkFeatureCamera.Supported enumWhitebalancelocksupported = ArsdkFeatureCamera.Supported.fromValue(whiteBalanceLockSupported);
        if (enumWhitebalancelocksupported == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Supported value " + whiteBalanceLockSupported);
        try {
            cb.onCameraCapabilities(camId, enumModel, exposureModesBitField, enumExposurelocksupported, enumExposureroilocksupported, evCompensationsBitField, whiteBalanceModesBitField, customWhiteBalanceTemperaturesBitField, enumWhitebalancelocksupported, stylesBitField, cameraModesBitField, hyperlapseValuesBitField, bracketingPresetsBitField, burstValuesBitField, streamingModesBitField, timelapseIntervalMin, gpslapseIntervalMin, autoExposureMeteringModesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.camera_capabilities [cam_id: " + camId + ", model: " + model + ", exposure_modes: " + exposureModesBitField + ", exposure_lock_supported: " + exposureLockSupported + ", exposure_roi_lock_supported: " + exposureRoiLockSupported + ", ev_compensations: " + evCompensationsBitField + ", white_balance_modes: " + whiteBalanceModesBitField + ", custom_white_balance_temperatures: " + customWhiteBalanceTemperaturesBitField + ", white_balance_lock_supported: " + whiteBalanceLockSupported + ", styles: " + stylesBitField + ", camera_modes: " + cameraModesBitField + ", hyperlapse_values: " + hyperlapseValuesBitField + ", bracketing_presets: " + bracketingPresetsBitField + ", burst_values: " + burstValuesBitField + ", streaming_modes: " + streamingModesBitField + ", timelapse_interval_min: " + timelapseIntervalMin + ", gpslapse_interval_min: " + gpslapseIntervalMin + ", auto_exposure_metering_modes: " + autoExposureMeteringModesBitField + "]", e);
        }
    }

    private static void recordingCapabilities(Callback cb, int id, int recordingModesBitField, int resolutionsBitField, int frameratesBitField, int hdr, int listFlagsBitField) {
        ArsdkFeatureCamera.Supported enumHdr = ArsdkFeatureCamera.Supported.fromValue(hdr);
        if (enumHdr == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Supported value " + hdr);
        try {
            cb.onRecordingCapabilities(id, recordingModesBitField, resolutionsBitField, frameratesBitField, enumHdr, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.recording_capabilities [id: " + id + ", recording_modes: " + recordingModesBitField + ", resolutions: " + resolutionsBitField + ", framerates: " + frameratesBitField + ", hdr: " + hdr + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void photoCapabilities(Callback cb, int id, int photoModesBitField, int photoFormatsBitField, int photoFileFormatsBitField, int hdr, int listFlagsBitField) {
        ArsdkFeatureCamera.Supported enumHdr = ArsdkFeatureCamera.Supported.fromValue(hdr);
        if (enumHdr == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Supported value " + hdr);
        try {
            cb.onPhotoCapabilities(id, photoModesBitField, photoFormatsBitField, photoFileFormatsBitField, enumHdr, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.photo_capabilities [id: " + id + ", photo_modes: " + photoModesBitField + ", photo_formats: " + photoFormatsBitField + ", photo_file_formats: " + photoFileFormatsBitField + ", hdr: " + hdr + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void antiflickerCapabilities(Callback cb, int supportedModesBitField) {
        try {
            cb.onAntiflickerCapabilities(supportedModesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.antiflicker_capabilities [supported_modes: " + supportedModesBitField + "]", e);
        }
    }

    private static void exposureSettings(Callback cb, int camId, int mode, int manualShutterSpeed, long manualShutterSpeedCapabilitiesBitField, int manualIsoSensitivity, long manualIsoSensitivityCapabilitiesBitField, int maxIsoSensitivity, long maxIsoSensitivitiesCapabilitiesBitField, int meteringMode) {
        ArsdkFeatureCamera.ExposureMode enumMode = ArsdkFeatureCamera.ExposureMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.ExposureMode value " + mode);
        ArsdkFeatureCamera.ShutterSpeed enumManualshutterspeed = ArsdkFeatureCamera.ShutterSpeed.fromValue(manualShutterSpeed);
        if (enumManualshutterspeed == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.ShutterSpeed value " + manualShutterSpeed);
        ArsdkFeatureCamera.IsoSensitivity enumManualisosensitivity = ArsdkFeatureCamera.IsoSensitivity.fromValue(manualIsoSensitivity);
        if (enumManualisosensitivity == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.IsoSensitivity value " + manualIsoSensitivity);
        ArsdkFeatureCamera.IsoSensitivity enumMaxisosensitivity = ArsdkFeatureCamera.IsoSensitivity.fromValue(maxIsoSensitivity);
        if (enumMaxisosensitivity == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.IsoSensitivity value " + maxIsoSensitivity);
        ArsdkFeatureCamera.AutoExposureMeteringMode enumMeteringmode = ArsdkFeatureCamera.AutoExposureMeteringMode.fromValue(meteringMode);
        if (enumMeteringmode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.AutoExposureMeteringMode value " + meteringMode);
        try {
            cb.onExposureSettings(camId, enumMode, enumManualshutterspeed, manualShutterSpeedCapabilitiesBitField, enumManualisosensitivity, manualIsoSensitivityCapabilitiesBitField, enumMaxisosensitivity, maxIsoSensitivitiesCapabilitiesBitField, enumMeteringmode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.exposure_settings [cam_id: " + camId + ", mode: " + mode + ", manual_shutter_speed: " + manualShutterSpeed + ", manual_shutter_speed_capabilities: " + manualShutterSpeedCapabilitiesBitField + ", manual_iso_sensitivity: " + manualIsoSensitivity + ", manual_iso_sensitivity_capabilities: " + manualIsoSensitivityCapabilitiesBitField + ", max_iso_sensitivity: " + maxIsoSensitivity + ", max_iso_sensitivities_capabilities: " + maxIsoSensitivitiesCapabilitiesBitField + ", metering_mode: " + meteringMode + "]", e);
        }
    }

    private static void exposure(Callback cb, int camId, int shutterSpeed, int isoSensitivity, int lock, float lockRoiX, float lockRoiY, float lockRoiWidth, float lockRoiHeight) {
        ArsdkFeatureCamera.ShutterSpeed enumShutterspeed = ArsdkFeatureCamera.ShutterSpeed.fromValue(shutterSpeed);
        if (enumShutterspeed == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.ShutterSpeed value " + shutterSpeed);
        ArsdkFeatureCamera.IsoSensitivity enumIsosensitivity = ArsdkFeatureCamera.IsoSensitivity.fromValue(isoSensitivity);
        if (enumIsosensitivity == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.IsoSensitivity value " + isoSensitivity);
        ArsdkFeatureCamera.State enumLock = ArsdkFeatureCamera.State.fromValue(lock);
        if (enumLock == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + lock);
        try {
            cb.onExposure(camId, enumShutterspeed, enumIsosensitivity, enumLock, lockRoiX, lockRoiY, lockRoiWidth, lockRoiHeight);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.exposure [cam_id: " + camId + ", shutter_speed: " + shutterSpeed + ", iso_sensitivity: " + isoSensitivity + ", lock: " + lock + ", lock_roi_x: " + lockRoiX + ", lock_roi_y: " + lockRoiY + ", lock_roi_width: " + lockRoiWidth + ", lock_roi_height: " + lockRoiHeight + "]", e);
        }
    }

    private static void whiteBalance(Callback cb, int camId, int mode, int temperature, int lock) {
        ArsdkFeatureCamera.WhiteBalanceMode enumMode = ArsdkFeatureCamera.WhiteBalanceMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.WhiteBalanceMode value " + mode);
        ArsdkFeatureCamera.WhiteBalanceTemperature enumTemperature = ArsdkFeatureCamera.WhiteBalanceTemperature.fromValue(temperature);
        if (enumTemperature == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.WhiteBalanceTemperature value " + temperature);
        ArsdkFeatureCamera.State enumLock = ArsdkFeatureCamera.State.fromValue(lock);
        if (enumLock == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + lock);
        try {
            cb.onWhiteBalance(camId, enumMode, enumTemperature, enumLock);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.white_balance [cam_id: " + camId + ", mode: " + mode + ", temperature: " + temperature + ", lock: " + lock + "]", e);
        }
    }

    private static void evCompensation(Callback cb, int camId, int value) {
        ArsdkFeatureCamera.EvCompensation enumValue = ArsdkFeatureCamera.EvCompensation.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.EvCompensation value " + value);
        try {
            cb.onEvCompensation(camId, enumValue);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.ev_compensation [cam_id: " + camId + ", value: " + value + "]", e);
        }
    }

    private static void antiflickerMode(Callback cb, int mode, int value) {
        ArsdkFeatureCamera.AntiflickerMode enumMode = ArsdkFeatureCamera.AntiflickerMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.AntiflickerMode value " + mode);
        ArsdkFeatureCamera.AntiflickerMode enumValue = ArsdkFeatureCamera.AntiflickerMode.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.AntiflickerMode value " + value);
        try {
            cb.onAntiflickerMode(enumMode, enumValue);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.antiflicker_mode [mode: " + mode + ", value: " + value + "]", e);
        }
    }

    private static void style(Callback cb, int camId, int style, int saturation, int saturationMin, int saturationMax, int contrast, int contrastMin, int contrastMax, int sharpness, int sharpnessMin, int sharpnessMax) {
        ArsdkFeatureCamera.Style enumStyle = ArsdkFeatureCamera.Style.fromValue(style);
        if (enumStyle == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Style value " + style);
        try {
            cb.onStyle(camId, enumStyle, saturation, saturationMin, saturationMax, contrast, contrastMin, contrastMax, sharpness, sharpnessMin, sharpnessMax);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.style [cam_id: " + camId + ", style: " + style + ", saturation: " + saturation + ", saturation_min: " + saturationMin + ", saturation_max: " + saturationMax + ", contrast: " + contrast + ", contrast_min: " + contrastMin + ", contrast_max: " + contrastMax + ", sharpness: " + sharpness + ", sharpness_min: " + sharpnessMin + ", sharpness_max: " + sharpnessMax + "]", e);
        }
    }

    private static void zoomLevel(Callback cb, int camId, float level) {
        try {
            cb.onZoomLevel(camId, level);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.zoom_level [cam_id: " + camId + ", level: " + level + "]", e);
        }
    }

    private static void zoomInfo(Callback cb, int camId, int available, float highQualityMaximumLevel, float maximumLevel) {
        ArsdkFeatureCamera.Availability enumAvailable = ArsdkFeatureCamera.Availability.fromValue(available);
        if (enumAvailable == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Availability value " + available);
        try {
            cb.onZoomInfo(camId, enumAvailable, highQualityMaximumLevel, maximumLevel);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.zoom_info [cam_id: " + camId + ", available: " + available + ", high_quality_maximum_level: " + highQualityMaximumLevel + ", maximum_level: " + maximumLevel + "]", e);
        }
    }

    private static void maxZoomSpeed(Callback cb, int camId, float min, float max, float current) {
        try {
            cb.onMaxZoomSpeed(camId, min, max, current);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.max_zoom_speed [cam_id: " + camId + ", min: " + min + ", max: " + max + ", current: " + current + "]", e);
        }
    }

    private static void zoomVelocityQualityDegradation(Callback cb, int camId, int allowed) {
        try {
            cb.onZoomVelocityQualityDegradation(camId, allowed);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.zoom_velocity_quality_degradation [cam_id: " + camId + ", allowed: " + allowed + "]", e);
        }
    }

    private static void hdrSetting(Callback cb, int camId, int value) {
        ArsdkFeatureCamera.State enumValue = ArsdkFeatureCamera.State.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + value);
        try {
            cb.onHdrSetting(camId, enumValue);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.hdr_setting [cam_id: " + camId + ", value: " + value + "]", e);
        }
    }

    private static void hdr(Callback cb, int camId, int available, int state) {
        ArsdkFeatureCamera.Availability enumAvailable = ArsdkFeatureCamera.Availability.fromValue(available);
        if (enumAvailable == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Availability value " + available);
        ArsdkFeatureCamera.State enumState = ArsdkFeatureCamera.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + state);
        try {
            cb.onHdr(camId, enumAvailable, enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.hdr [cam_id: " + camId + ", available: " + available + ", state: " + state + "]", e);
        }
    }

    private static void cameraMode(Callback cb, int camId, int mode) {
        ArsdkFeatureCamera.CameraMode enumMode = ArsdkFeatureCamera.CameraMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.CameraMode value " + mode);
        try {
            cb.onCameraMode(camId, enumMode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.camera_mode [cam_id: " + camId + ", mode: " + mode + "]", e);
        }
    }

    private static void recordingMode(Callback cb, int camId, int mode, int resolution, int framerate, int hyperlapse, long bitrate) {
        ArsdkFeatureCamera.RecordingMode enumMode = ArsdkFeatureCamera.RecordingMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.RecordingMode value " + mode);
        ArsdkFeatureCamera.Resolution enumResolution = ArsdkFeatureCamera.Resolution.fromValue(resolution);
        if (enumResolution == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Resolution value " + resolution);
        ArsdkFeatureCamera.Framerate enumFramerate = ArsdkFeatureCamera.Framerate.fromValue(framerate);
        if (enumFramerate == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Framerate value " + framerate);
        ArsdkFeatureCamera.HyperlapseValue enumHyperlapse = ArsdkFeatureCamera.HyperlapseValue.fromValue(hyperlapse);
        if (enumHyperlapse == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.HyperlapseValue value " + hyperlapse);
        try {
            cb.onRecordingMode(camId, enumMode, enumResolution, enumFramerate, enumHyperlapse, bitrate);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.recording_mode [cam_id: " + camId + ", mode: " + mode + ", resolution: " + resolution + ", framerate: " + framerate + ", hyperlapse: " + hyperlapse + ", bitrate: " + bitrate + "]", e);
        }
    }

    private static void photoMode(Callback cb, int camId, int mode, int format, int fileFormat, int burst, int bracketing, float captureInterval) {
        ArsdkFeatureCamera.PhotoMode enumMode = ArsdkFeatureCamera.PhotoMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.PhotoMode value " + mode);
        ArsdkFeatureCamera.PhotoFormat enumFormat = ArsdkFeatureCamera.PhotoFormat.fromValue(format);
        if (enumFormat == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.PhotoFormat value " + format);
        ArsdkFeatureCamera.PhotoFileFormat enumFileformat = ArsdkFeatureCamera.PhotoFileFormat.fromValue(fileFormat);
        if (enumFileformat == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.PhotoFileFormat value " + fileFormat);
        ArsdkFeatureCamera.BurstValue enumBurst = ArsdkFeatureCamera.BurstValue.fromValue(burst);
        if (enumBurst == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.BurstValue value " + burst);
        ArsdkFeatureCamera.BracketingPreset enumBracketing = ArsdkFeatureCamera.BracketingPreset.fromValue(bracketing);
        if (enumBracketing == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.BracketingPreset value " + bracketing);
        try {
            cb.onPhotoMode(camId, enumMode, enumFormat, enumFileformat, enumBurst, enumBracketing, captureInterval);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.photo_mode [cam_id: " + camId + ", mode: " + mode + ", format: " + format + ", file_format: " + fileFormat + ", burst: " + burst + ", bracketing: " + bracketing + ", capture_interval: " + captureInterval + "]", e);
        }
    }

    private static void streamingMode(Callback cb, int camId, int value) {
        ArsdkFeatureCamera.StreamingMode enumValue = ArsdkFeatureCamera.StreamingMode.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.StreamingMode value " + value);
        try {
            cb.onStreamingMode(camId, enumValue);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.streaming_mode [cam_id: " + camId + ", value: " + value + "]", e);
        }
    }

    private static void photoProgress(Callback cb, int camId, int result, int photoCount, String mediaId) {
        ArsdkFeatureCamera.PhotoResult enumResult = ArsdkFeatureCamera.PhotoResult.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.PhotoResult value " + result);
        try {
            cb.onPhotoProgress(camId, enumResult, photoCount, mediaId);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.photo_progress [cam_id: " + camId + ", result: " + result + ", photo_count: " + photoCount + ", media_id: " + mediaId + "]", e);
        }
    }

    private static void photoState(Callback cb, int camId, int available, int state) {
        ArsdkFeatureCamera.Availability enumAvailable = ArsdkFeatureCamera.Availability.fromValue(available);
        if (enumAvailable == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Availability value " + available);
        ArsdkFeatureCamera.State enumState = ArsdkFeatureCamera.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + state);
        try {
            cb.onPhotoState(camId, enumAvailable, enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.photo_state [cam_id: " + camId + ", available: " + available + ", state: " + state + "]", e);
        }
    }

    private static void recordingProgress(Callback cb, int camId, int result, String mediaId) {
        ArsdkFeatureCamera.RecordingResult enumResult = ArsdkFeatureCamera.RecordingResult.fromValue(result);
        if (enumResult == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.RecordingResult value " + result);
        try {
            cb.onRecordingProgress(camId, enumResult, mediaId);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.recording_progress [cam_id: " + camId + ", result: " + result + ", media_id: " + mediaId + "]", e);
        }
    }

    private static void recordingState(Callback cb, int camId, int available, int state, long startTimestamp) {
        ArsdkFeatureCamera.Availability enumAvailable = ArsdkFeatureCamera.Availability.fromValue(available);
        if (enumAvailable == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.Availability value " + available);
        ArsdkFeatureCamera.State enumState = ArsdkFeatureCamera.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + state);
        try {
            cb.onRecordingState(camId, enumAvailable, enumState, startTimestamp);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.recording_state [cam_id: " + camId + ", available: " + available + ", state: " + state + ", start_timestamp: " + startTimestamp + "]", e);
        }
    }

    private static void autorecord(Callback cb, int camId, int state) {
        ArsdkFeatureCamera.State enumState = ArsdkFeatureCamera.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.State value " + state);
        try {
            cb.onAutorecord(camId, enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.autorecord [cam_id: " + camId + ", state: " + state + "]", e);
        }
    }

    private static void cameraStates(Callback cb, long activeCameras) {
        try {
            cb.onCameraStates(activeCameras);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.camera_states [active_cameras: " + activeCameras + "]", e);
        }
    }

    private static void nextPhotoDelay(Callback cb, int mode, float remaining) {
        ArsdkFeatureCamera.PhotoMode enumMode = ArsdkFeatureCamera.PhotoMode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureCamera.PhotoMode value " + mode);
        try {
            cb.onNextPhotoDelay(enumMode, remaining);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.next_photo_delay [mode: " + mode + ", remaining: " + remaining + "]", e);
        }
    }

    private static void alignmentOffsets(Callback cb, int camId, float minBoundYaw, float maxBoundYaw, float currentYaw, float minBoundPitch, float maxBoundPitch, float currentPitch, float minBoundRoll, float maxBoundRoll, float currentRoll) {
        try {
            cb.onAlignmentOffsets(camId, minBoundYaw, maxBoundYaw, currentYaw, minBoundPitch, maxBoundPitch, currentPitch, minBoundRoll, maxBoundRoll, currentRoll);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: camera.alignment_offsets [cam_id: " + camId + ", min_bound_yaw: " + minBoundYaw + ", max_bound_yaw: " + maxBoundYaw + ", current_yaw: " + currentYaw + ", min_bound_pitch: " + minBoundPitch + ", max_bound_pitch: " + maxBoundPitch + ", current_pitch: " + currentPitch + ", min_bound_roll: " + minBoundRoll + ", max_bound_roll: " + maxBoundRoll + ", current_roll: " + currentRoll + "]", e);
        }
    }
    
    /**
     * Sets exposure mode, shutter speed, iso sensitivity and maximum iso sensitivity.
     * 
     * @param camId: Id of the camera.
     * @param mode: Requested exposure mode: Auto, Manual Shutter Speed, Manual ISO or Manual.
     * @param shutterSpeed: Requested shutter speed, ignored if mode is not Manual Shutter Speed or Manual.
     * @param isoSensitivity: Requested ISO sensitivity level, ignored if mode is not Manual ISO or Manual.
     * @param maxIsoSensitivity: Requested maximum ISO sensitivity level, ignored is not Auto. This value define the
     * maximum iso sensitivity the autoexposure engine can use to adjust exposure.
     * @param meteringMode: Auto Exposure metering mode, ignored if mode is Manual
     */
    public static ArsdkCommand encodeSetExposureSettings(int camId, @NonNull ExposureMode mode, @NonNull ShutterSpeed shutterSpeed, @NonNull IsoSensitivity isoSensitivity, @NonNull IsoSensitivity maxIsoSensitivity, @NonNull AutoExposureMeteringMode meteringMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetExposureSettings(cmd.getNativePtr(), camId, mode.value, shutterSpeed.value, isoSensitivity.value, maxIsoSensitivity.value, meteringMode.value);
        return cmd;
    }

    /**
     * Lock shutter speed and iso sensitivity to current values. Valid for all exposure modes exepted `manual`. Auto
     * exposure lock is automatically removed when the exposure mode is changed.
     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeLockExposure(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeLockExposure(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Lock shutter speed and iso sensitivity optimized on a specific region. Valid for all exposure modes exepted
     * `manual` Auto exposure lock is automatically removed when the exposure mode is changed.
     * 
     * @param camId: Id of the camera.
     * @param roiCenterX: ROI center on x axis. between 0 and 1, relative to streaming image width.
     * @param roiCenterY: ROI center on y axis. between 0 and 1, relative to streaming image height.
     */
    public static ArsdkCommand encodeLockExposureOnRoi(int camId, float roiCenterX, float roiCenterY) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeLockExposureOnRoi(cmd.getNativePtr(), camId, roiCenterX, roiCenterY);
        return cmd;
    }

    /**
     * Valid when exposure is locked.
     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeUnlockExposure(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeUnlockExposure(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Change the white balance mode and custom temperature.
     * 
     * @param camId: Id of the camera.
     * @param mode: Requested white balance mode.
     * @param temperature: Requested white balance temperature when mode is `custom`. Ignored else.
     */
    public static ArsdkCommand encodeSetWhiteBalance(int camId, @NonNull WhiteBalanceMode mode, @NonNull WhiteBalanceTemperature temperature) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetWhiteBalance(cmd.getNativePtr(), camId, mode.value, temperature.value);
        return cmd;
    }

    /**
     * Lock/unlock white balance to current value. Valid when white balance mode not `custom`. White balance lock is
     * automatically removed when the white balance mode is changed.
     * 
     * @param camId: Id of the camera.
     * @param state: Requested lock state.
     */
    public static ArsdkCommand encodeSetWhiteBalanceLock(int camId, @NonNull State state) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetWhiteBalanceLock(cmd.getNativePtr(), camId, state.value);
        return cmd;
    }

    /**
     * Change the EV compensation value.
     * 
     * @param camId: Id of the camera.
     * @param value: Requested EV compensation value.
     */
    public static ArsdkCommand encodeSetEvCompensation(int camId, @NonNull EvCompensation value) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetEvCompensation(cmd.getNativePtr(), camId, value.value);
        return cmd;
    }

    /**
     * Change the anti-flicker mode.
     * 
     * @param mode: Requested anti-flicker mode.
     */
    public static ArsdkCommand encodeSetAntiflickerMode(@NonNull AntiflickerMode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetAntiflickerMode(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    /**
     * Change the active style.
     * 
     * @param camId: Id of the camera.
     * @param style: Style to activate.
     */
    public static ArsdkCommand encodeSetStyle(int camId, @NonNull Style style) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetStyle(cmd.getNativePtr(), camId, style.value);
        return cmd;
    }

    /**
     * Change style saturation, contrast and sharpness of the current active style.
     * 
     * @param camId: Id of the camera.
     * @param saturation: Requested saturation value for this style.
     * @param contrast: Requested contrast value for this style.
     * @param sharpness: Requested sharpness value for this style.
     */
    public static ArsdkCommand encodeSetStyleParams(int camId, int saturation, int contrast, int sharpness) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetStyleParams(cmd.getNativePtr(), camId, saturation, contrast, sharpness);
        return cmd;
    }

    /**

     * 
     * @param camId: id of the camera.
     * @param controlMode: Mode of changing the zoom. This parameter will characterize following parameters units.
     * @param target: Zoom target. Units depend on the `control_mode` value: - `level`: value is in zoom level -
     * `velocity`, value is in signed ratio (from -1 to 1) of the [MaxZoomSpeed](#143-24) `current` parameter. Negative
     * values will produce a zoom out, positive values produce a zoom in.
     */
    public static ArsdkCommand encodeSetZoomTarget(int camId, @NonNull ZoomControlMode controlMode, float target) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetZoomTarget(cmd.getNativePtr(), camId, controlMode.value, target);
        return cmd;
    }

    /**
     * Set the max zoom speed setting. You can get bounds using [MaxZoomSpeed](#143-25).
     * 
     * @param camId: Id of the camera.
     * @param max: Desired current max zoom speed. Should lay between the bounds given by [MaxZoomSpeed](#143-25).
     * Expressed as a tan(deg) / sec.
     */
    public static ArsdkCommand encodeSetMaxZoomSpeed(int camId, float max) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetMaxZoomSpeed(cmd.getNativePtr(), camId, max);
        return cmd;
    }

    /**
     * Set the max zoom velocity.
     * 
     * @param camId: Id of the camera.
     * @param allow: 1 to allow quality degradation, 0 otherwise.
     */
    public static ArsdkCommand encodeSetZoomVelocityQualityDegradation(int camId, int allow) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetZoomVelocityQualityDegradation(cmd.getNativePtr(), camId, allow);
        return cmd;
    }

    /**
     * Change HDR setting. if HDR setting is `active`, HDR will be used when supported in active configuration.
     * 
     * @param camId: Id of the camera.
     * @param value: Requested HDR setting value.
     */
    public static ArsdkCommand encodeSetHdrSetting(int camId, @NonNull State value) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetHdrSetting(cmd.getNativePtr(), camId, value.value);
        return cmd;
    }

    /**
     * Change camera mode.
     * 
     * @param camId: Id of the camera.
     * @param value: Requested camera mode.
     */
    public static ArsdkCommand encodeSetCameraMode(int camId, @NonNull CameraMode value) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetCameraMode(cmd.getNativePtr(), camId, value.value);
        return cmd;
    }

    /**
     * Change recording mode and parameters to be used when the camera is in mode recording. Note that if the camera is
     * in photo modes, actual camera mode is not changed, new recording mode and parameters are saved and are apply when
     * the camera mode is changed to `recording`.
     * 
     * @param camId: Id of the camera.
     * @param mode: Requested camera recording mode.
     * @param resolution: Requested recording resolution.
     * @param framerate: Requested recording framerate.
     * @param hyperlapse: Requested hyperlapse value when the recording mode is hyperlapse. Ignored in other modes
     */
    public static ArsdkCommand encodeSetRecordingMode(int camId, @NonNull RecordingMode mode, @NonNull Resolution resolution, @NonNull Framerate framerate, @NonNull HyperlapseValue hyperlapse) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetRecordingMode(cmd.getNativePtr(), camId, mode.value, resolution.value, framerate.value, hyperlapse.value);
        return cmd;
    }

    /**
     * Change photo mode and parameters to be used when the camera is in mode photo. Note that if the camera is in
     * recording modes, actual camera mode is not changed, new photo mode and parameters are saved and are apply when
     * the camera mode is changed to `photo`.
     * 
     * @param camId: Id of the camera.
     * @param mode: Requested camera photo mode.
     * @param format: Requested photo format.
     * @param fileFormat: Requested photo file format.
     * @param burst: Requested burst value when the photo mode is burst. Ignored in other modes.
     * @param bracketing: Requested bracketing value when the photo mode is bracketing. Ignored in other modes.
     * @param captureInterval: Requested time-lapse interval value (in seconds) when the photo mode is time_lapse.
     * Requested GPS-lapse interval value (in meters) when the photo mode is gps_lapse. Ignored in other modes.
     */
    public static ArsdkCommand encodeSetPhotoMode(int camId, @NonNull PhotoMode mode, @NonNull PhotoFormat format, @NonNull PhotoFileFormat fileFormat, @NonNull BurstValue burst, @NonNull BracketingPreset bracketing, float captureInterval) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetPhotoMode(cmd.getNativePtr(), camId, mode.value, format.value, fileFormat.value, burst.value, bracketing.value, captureInterval);
        return cmd;
    }

    /**
     * Change streaming mode setting.
     * 
     * @param camId: Id of the camera.
     * @param value: New streaming mode.
     */
    public static ArsdkCommand encodeSetStreamingMode(int camId, @NonNull StreamingMode value) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetStreamingMode(cmd.getNativePtr(), camId, value.value);
        return cmd;
    }

    /**
     * Take a photo. Can be sent when `photo_state` is `available`.
     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeTakePhoto(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeTakePhoto(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**

     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeStartRecording(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartRecording(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**

     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeStopRecording(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStopRecording(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * When auto-record is enabled, if the drone is in recording mode, recording starts when taking-off and stops after
     * landing.
     * 
     * @param camId: Id of the camera.
     * @param state: Requested auto-record state.
     */
    public static ArsdkCommand encodeSetAutorecord(int camId, @NonNull State state) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetAutorecord(cmd.getNativePtr(), camId, state.value);
        return cmd;
    }

    /**

     * 
     * @param camId: id of the camera.
     */
    public static ArsdkCommand encodeResetZoom(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetZoom(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**
     * Stops an ongoing photos capture. Only for `time_lapse` and `gps_lapse` `photo_mode`. Only when `photo_state` is
     * `available` and `active`.
     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeStopPhoto(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStopPhoto(cmd.getNativePtr(), camId);
        return cmd;
    }

    /**

     * 
     * @param camId: Id of the camera.
     * @param yaw: Alignment offset, in degrees, that should be applied to the yaw axis. This value will be clamped
     * between [alignment_offsets](#143-52) min_bound_yaw and max_bound_yaw
     * @param pitch: Alignment offset, in degrees, that should be applied to the pitch axis. This value will be clamped
     * between [alignment_offsets](#143-52) min_bound_pitch and max_bound_pitch
     * @param roll: Alignment offset, in degrees, that should be applied to the roll axis. This value will be clamped
     * between [alignment_offsets](#143-52) min_bound_roll and max_bound_roll
     */
    public static ArsdkCommand encodeSetAlignmentOffsets(int camId, float yaw, float pitch, float roll) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetAlignmentOffsets(cmd.getNativePtr(), camId, yaw, pitch, roll);
        return cmd;
    }

    /**

     * 
     * @param camId: Id of the camera.
     */
    public static ArsdkCommand encodeResetAlignmentOffsets(int camId) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeResetAlignmentOffsets(cmd.getNativePtr(), camId);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeSetExposureSettings(long nativeCmd, int cam_id, int mode, int shutter_speed, int iso_sensitivity, int max_iso_sensitivity, int metering_mode);
    private static native int nativeEncodeLockExposure(long nativeCmd, int cam_id);
    private static native int nativeEncodeLockExposureOnRoi(long nativeCmd, int cam_id, float roi_center_x, float roi_center_y);
    private static native int nativeEncodeUnlockExposure(long nativeCmd, int cam_id);
    private static native int nativeEncodeSetWhiteBalance(long nativeCmd, int cam_id, int mode, int temperature);
    private static native int nativeEncodeSetWhiteBalanceLock(long nativeCmd, int cam_id, int state);
    private static native int nativeEncodeSetEvCompensation(long nativeCmd, int cam_id, int value);
    private static native int nativeEncodeSetAntiflickerMode(long nativeCmd, int mode);
    private static native int nativeEncodeSetStyle(long nativeCmd, int cam_id, int style);
    private static native int nativeEncodeSetStyleParams(long nativeCmd, int cam_id, int saturation, int contrast, int sharpness);
    private static native int nativeEncodeSetZoomTarget(long nativeCmd, int cam_id, int control_mode, float target);
    private static native int nativeEncodeSetMaxZoomSpeed(long nativeCmd, int cam_id, float max);
    private static native int nativeEncodeSetZoomVelocityQualityDegradation(long nativeCmd, int cam_id, int allow);
    private static native int nativeEncodeSetHdrSetting(long nativeCmd, int cam_id, int value);
    private static native int nativeEncodeSetCameraMode(long nativeCmd, int cam_id, int value);
    private static native int nativeEncodeSetRecordingMode(long nativeCmd, int cam_id, int mode, int resolution, int framerate, int hyperlapse);
    private static native int nativeEncodeSetPhotoMode(long nativeCmd, int cam_id, int mode, int format, int file_format, int burst, int bracketing, float capture_interval);
    private static native int nativeEncodeSetStreamingMode(long nativeCmd, int cam_id, int value);
    private static native int nativeEncodeTakePhoto(long nativeCmd, int cam_id);
    private static native int nativeEncodeStartRecording(long nativeCmd, int cam_id);
    private static native int nativeEncodeStopRecording(long nativeCmd, int cam_id);
    private static native int nativeEncodeSetAutorecord(long nativeCmd, int cam_id, int state);
    private static native int nativeEncodeResetZoom(long nativeCmd, int cam_id);
    private static native int nativeEncodeStopPhoto(long nativeCmd, int cam_id);
    private static native int nativeEncodeSetAlignmentOffsets(long nativeCmd, int cam_id, float yaw, float pitch, float roll);
    private static native int nativeEncodeResetAlignmentOffsets(long nativeCmd, int cam_id);
}
