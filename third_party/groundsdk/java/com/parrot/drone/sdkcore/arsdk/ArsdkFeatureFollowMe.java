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
 * Follow_me feature command/event interface.
 */
public class ArsdkFeatureFollowMe {

    /**
     * FollowMe mode.
     */
    public enum Mode {

        /**
         * No follow me.
         */
        NONE(0),

        /**
         * Look at the target without moving automatically.
         */
        LOOK_AT(1),

        /**
         * Follow the target keeping the same vector.
         */
        GEOGRAPHIC(2),

        /**
         * Follow the target keeping the same orientation to its direction.
         */
        RELATIVE(3),

        /**
         * Follow the target as it was held by a leash.
         */
        LEASH(4);

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
     * FollowMe behavior.
     */
    public enum Behavior {

        /**
         * Drone is not moving according to the target This means that at least one required input is missing.
         */
        IDLE(0),

        /**
         * Follow the target.
         */
        FOLLOW(1),

        /**
         * Look at the target without moving.
         */
        LOOK_AT(2);

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
        public static Behavior fromValue(int value) {
            return MAP.get(value, null);
        }

        private Behavior(int value) {
            this.value = value;
        }

        private static final SparseArray<Behavior> MAP;

        static {
            MAP = new SparseArray<>();
            for (Behavior e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Input values used by the FollowMe.
     */
    public enum Input {

        /**
         * Drone is calibrated.
         */
        DRONE_CALIBRATED(0),

        /**
         * Drone gps has fixed and has a good accuracy.
         */
        DRONE_GPS_GOOD_ACCURACY(1),

        /**
         * Target gps data is known and has a good accuracy.
         */
        TARGET_GPS_GOOD_ACCURACY(2),

        /**
         * Target barometer data is available.
         */
        TARGET_BAROMETER_OK(3),

        /**
         * Drone is far enough from the target.
         */
        DRONE_FAR_ENOUGH(4),

        /**
         * Drone is high enough from the ground.
         */
        DRONE_HIGH_ENOUGH(5),

        /**
         * Target detection is done by image detection among other things.
         */
        IMAGE_DETECTION(6),

        /**
         * Target is at a good speed.
         */
        TARGET_GOOD_SPEED(7),

        /**
         * Drone is close enough to the target.
         */
        DRONE_CLOSE_ENOUGH(8);

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
        public static Input fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Input> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 9) {
                    ULog.e(TAG, "Unsupported Input bitfield value " + value);
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
        public static EnumSet<Input> fromBitfield(int bitfield) {
            EnumSet<Input> enums = EnumSet.noneOf(Input.class);
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
        public static int toBitField(@NonNull Input... enums) {
            int bitField = 0;
            for (Input e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Input(int value) {
            this.value = value;
        }

        private static final SparseArray<Input> MAP;

        static {
            MAP = new SparseArray<>();
            for (Input e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Geographic and Relative follow me configuration parameters.
     */
    public enum GeoRelConfigureParam {

        /**
         * Distance configuration.
         */
        DISTANCE(0),

        /**
         * Elevation configuration.
         */
        ELEVATION(1),

        /**
         * Azimuth configuration.
         */
        AZIMUTH(2);

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
        public static GeoRelConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<GeoRelConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported GeoRelConfigureParam bitfield value " + value);
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
        public static EnumSet<GeoRelConfigureParam> fromBitfield(int bitfield) {
            EnumSet<GeoRelConfigureParam> enums = EnumSet.noneOf(GeoRelConfigureParam.class);
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
        public static int toBitField(@NonNull GeoRelConfigureParam... enums) {
            int bitField = 0;
            for (GeoRelConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private GeoRelConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<GeoRelConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (GeoRelConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * FollowMe animation type.
     */
    public enum Animation {

        /**
         * No animation.
         */
        NONE(0),

        /**
         * Turn around the target.
         */
        HELICOID(1),

        /**
         * Pass by the zenith and change of side.
         */
        SWING(2),

        /**
         * Fly far from the target and fly back.
         */
        BOOMERANG(3),

        /**
         * Move to the target and go high when it is near.
         */
        CANDLE(4),

        /**
         * Fly in line.
         */
        DOLLY_SLIDE(5);

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
        public static Animation fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Animation> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 6) {
                    ULog.e(TAG, "Unsupported Animation bitfield value " + value);
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
        public static EnumSet<Animation> fromBitfield(int bitfield) {
            EnumSet<Animation> enums = EnumSet.noneOf(Animation.class);
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
        public static int toBitField(@NonNull Animation... enums) {
            int bitField = 0;
            for (Animation e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Animation(int value) {
            this.value = value;
        }

        private static final SparseArray<Animation> MAP;

        static {
            MAP = new SparseArray<>();
            for (Animation e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Helicoid animation configuration parameters.
     */
    public enum HelicoidConfigureParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Number of turn.
         */
        REVOLUTION_NB(1),

        /**
         * Vertical distance.
         */
        VERTICAL_DISTANCE(2);

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
        public static HelicoidConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<HelicoidConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported HelicoidConfigureParam bitfield value " + value);
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
        public static EnumSet<HelicoidConfigureParam> fromBitfield(int bitfield) {
            EnumSet<HelicoidConfigureParam> enums = EnumSet.noneOf(HelicoidConfigureParam.class);
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
        public static int toBitField(@NonNull HelicoidConfigureParam... enums) {
            int bitField = 0;
            for (HelicoidConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private HelicoidConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<HelicoidConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (HelicoidConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Swing configure parameters.
     */
    public enum SwingConfigureParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Vertical distance.
         */
        VERTICAL_DISTANCE(1);

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
        public static SwingConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SwingConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported SwingConfigureParam bitfield value " + value);
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
        public static EnumSet<SwingConfigureParam> fromBitfield(int bitfield) {
            EnumSet<SwingConfigureParam> enums = EnumSet.noneOf(SwingConfigureParam.class);
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
        public static int toBitField(@NonNull SwingConfigureParam... enums) {
            int bitField = 0;
            for (SwingConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SwingConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<SwingConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (SwingConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Boomerang animation configure parameters.
     */
    public enum BoomerangConfigureParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Distance.
         */
        DISTANCE(1);

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
        public static BoomerangConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<BoomerangConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported BoomerangConfigureParam bitfield value " + value);
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
        public static EnumSet<BoomerangConfigureParam> fromBitfield(int bitfield) {
            EnumSet<BoomerangConfigureParam> enums = EnumSet.noneOf(BoomerangConfigureParam.class);
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
        public static int toBitField(@NonNull BoomerangConfigureParam... enums) {
            int bitField = 0;
            for (BoomerangConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private BoomerangConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<BoomerangConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (BoomerangConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Candle animation configure parameters.
     */
    public enum CandleConfigureParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Follow the target keeping the same vector.
         */
        VERTICAL_DISTANCE(1);

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
        public static CandleConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<CandleConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported CandleConfigureParam bitfield value " + value);
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
        public static EnumSet<CandleConfigureParam> fromBitfield(int bitfield) {
            EnumSet<CandleConfigureParam> enums = EnumSet.noneOf(CandleConfigureParam.class);
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
        public static int toBitField(@NonNull CandleConfigureParam... enums) {
            int bitField = 0;
            for (CandleConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private CandleConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<CandleConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (CandleConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Dolly slide animation configure parameters.
     */
    public enum DollySlideConfigureParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Angle.
         */
        ANGLE(1),

        /**
         * Horizontal distance.
         */
        HORIZONTAL_DISTANCE(2);

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
        public static DollySlideConfigureParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<DollySlideConfigureParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported DollySlideConfigureParam bitfield value " + value);
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
        public static EnumSet<DollySlideConfigureParam> fromBitfield(int bitfield) {
            EnumSet<DollySlideConfigureParam> enums = EnumSet.noneOf(DollySlideConfigureParam.class);
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
        public static int toBitField(@NonNull DollySlideConfigureParam... enums) {
            int bitField = 0;
            for (DollySlideConfigureParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private DollySlideConfigureParam(int value) {
            this.value = value;
        }

        private static final SparseArray<DollySlideConfigureParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (DollySlideConfigureParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * State of the image detection.
     */
    public enum ImageDetectionStatus {

        /**
         * No image detection.
         */
        NONE(0),

        /**
         * Image detection is considered ok by the drone.
         */
        OK(1),

        /**
         * Image detection is considered lost or in contradiction with gps value. This state will remain until a new
         * selection of the target is done.
         */
        LOST(2);

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
        public static ImageDetectionStatus fromValue(int value) {
            return MAP.get(value, null);
        }

        private ImageDetectionStatus(int value) {
            this.value = value;
        }

        private static final SparseArray<ImageDetectionStatus> MAP;

        static {
            MAP = new SparseArray<>();
            for (ImageDetectionStatus e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x8600;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x0003;

    /** Uid of mode_info event. */;
    public static final int MODE_INFO_UID = 0x0004;

    /** Uid of geographic_config event. */;
    public static final int GEOGRAPHIC_CONFIG_UID = 0x0007;

    /** Uid of relative_config event. */;
    public static final int RELATIVE_CONFIG_UID = 0x0009;

    /** Uid of target_trajectory event. */;
    public static final int TARGET_TRAJECTORY_UID = 0x000A;

    /** Uid of helicoid_anim_config event. */;
    public static final int HELICOID_ANIM_CONFIG_UID = 0x000D;

    /** Uid of swing_anim_config event. */;
    public static final int SWING_ANIM_CONFIG_UID = 0x000F;

    /** Uid of boomerang_anim_config event. */;
    public static final int BOOMERANG_ANIM_CONFIG_UID = 0x0011;

    /** Uid of candle_anim_config event. */;
    public static final int CANDLE_ANIM_CONFIG_UID = 0x0013;

    /** Uid of dolly_slide_anim_config event. */;
    public static final int DOLLY_SLIDE_ANIM_CONFIG_UID = 0x0015;

    /** Uid of target_framing_position_changed event. */;
    public static final int TARGET_FRAMING_POSITION_CHANGED_UID = 0x0017;

    /** Uid of target_image_detection_state event. */;
    public static final int TARGET_IMAGE_DETECTION_STATE_UID = 0x0019;

    /** Uid of target_is_controller event. */;
    public static final int TARGET_IS_CONTROLLER_UID = 0x001B;

    /** Uid of leash_config event. */;
    public static final int LEASH_CONFIG_UID = 0x001D;

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
         * @param mode: Mode asked by user
         * @param behavior: Behavior of the drone according to the asked mode
         * @param animation: Current animation. This parameter has been deprecated. Please use the animation feature.
         * @param animationAvailableBitField: List of available animations This parameter has been deprecated. Please
         * use the animation feature.
         */
        public default void onState(@Nullable Mode mode, @Nullable Behavior behavior, @Nullable Animation animation, int animationAvailableBitField) {}

        /**

         * 
         * @param mode:
         * @param missingRequirementsBitField: List of missing requirements to enter this mode on start. Bit is 0 if the
         * input is missing, 1 if the input is ok. For example, if the first bit is equal to 0, it means that the mode
         * is not available because the drone is not calibrated. If at least one input is missing, drone won't able to
         * follow the target. It won't use any fallback either
         * @param improvementsBitField: List of inputs that can improve the mode. Bit is 0 if the input is missing,
         * i.e.: the mode can be improved if this input would be fulfilled, 1 if the input is ok. For example, if the
         * first bit is equal to 0, it means that the mode would be improved if the drone would be calibrated. If at
         * least one input is missing, a downgraded mode will be used. See behavior
         */
        public default void onModeInfo(@Nullable Mode mode, int missingRequirementsBitField, int improvementsBitField) {}

        /**
         * Geographic configuration changed. This event is only valid when arg behavior in [state](#134-3) is equal to
         * Follow.
         * 
         * @param useDefaultBitField:
         * @param distance: The distance leader-follower in meter If distance is default, this value is the current
         * drone distance
         * @param elevation: The elevation leader-follower in rad If elevation is default, this value is the current
         * leader to drone elevation
         * @param azimuth: The azimuth north-leader-follower in rad If azimuth is default, this value is the current
         * leader to drone azimuth
         */
        public default void onGeographicConfig(int useDefaultBitField, float distance, float elevation, float azimuth) {}

        /**
         * Relative configuration changed. This event is only valid when arg behavior in [state](#134-3) is equal to
         * Follow.
         * 
         * @param useDefaultBitField:
         * @param distance: The distance leader-follower in meter If distance is default, this value is the current
         * drone distance
         * @param elevation: The elevation leader-follower in rad If elevation is default, this value is the current
         * leader to drone elevation
         * @param azimuth: The azimuth course-leader-follower in rad If azimuth is default, this value is the current
         * leader to drone azimuth
         */
        public default void onRelativeConfig(int useDefaultBitField, float distance, float elevation, float azimuth) {}

        /**

         * 
         * @param latitude: Target latitude (in degrees)
         * @param longitude: Target longitude (in degrees)
         * @param altitude: Target altitude (in meters, relative to sea level)
         * @param northSpeed: Target north speed (in m/s)
         * @param eastSpeed: Target east speed (in m/s)
         * @param downSpeed: Target down speed (in m/s)
         */
        public default void onTargetTrajectory(double latitude, double longitude, float altitude, float northSpeed, float eastSpeed, float downSpeed) {}

        /**
         * Helicoid animation configuration. This should only be taken in account if arg animation in [state](#134-3) is
         * equal to helicoid. This message has been deprecated. Please use the animation feature.
         * 
         * @param useDefaultBitField:
         * @param speed: The speed of the anim in m/s
         * @param revolutionNb: The number of revolution (in turn) Negative value is infinite
         * @param verticalDistance: Distance that will be made by the product to reach the top of the helicoid in m
         */
        @Deprecated
        public default void onHelicoidAnimConfig(int useDefaultBitField, float speed, float revolutionNb, float verticalDistance) {}

        /**
         * Swing animation configuration changed. This should only be taken in account if arg animation in
         * [state](#134-3) is equal to swing. This message has been deprecated. Please use the animation feature.
         * 
         * @param useDefaultBitField:
         * @param speed: The speed of the anim in m/s
         * @param verticalDistance: Distance that will be made by the product to reach the top of the swing in m
         */
        @Deprecated
        public default void onSwingAnimConfig(int useDefaultBitField, float speed, float verticalDistance) {}

        /**
         * Boomerang animation configuration changed. This should only be taken in account if arg animation in
         * [state](#134-3) is equal to boomerang. This message has been deprecated. Please use the animation feature.
         * 
         * @param useDefaultBitField:
         * @param speed: The speed of the anim in m/s
         * @param distance: Distance that will be made by the product to reach its return point in m
         */
        @Deprecated
        public default void onBoomerangAnimConfig(int useDefaultBitField, float speed, float distance) {}

        /**
         * Candle animation configuration changed. This should only be taken in account if arg animation in
         * [state](#134-3) is equal to candle. This message has been deprecated. Please use the animation feature.
         * 
         * @param useDefaultBitField:
         * @param speed: The speed of the anim in m/s
         * @param verticalDistance: Distance that will be made by the product to reach the top of the vertical zoom-out
         * in m
         */
        @Deprecated
        public default void onCandleAnimConfig(int useDefaultBitField, float speed, float verticalDistance) {}

        /**
         * DollySlide animation configuration changed. This should only be taken in account if arg animation in
         * [state](#134-3) is equal to dolly_slide. This message has been deprecated. Please use the animation feature.
         * 
         * @param useDefaultBitField:
         * @param speed: The speed of the anim in m/s
         * @param angle: Angle Product-User-Target in rad
         * @param horizontalDistance: Distance that will be made by the product to reach its target in m
         */
        @Deprecated
        public default void onDollySlideAnimConfig(int useDefaultBitField, float speed, float angle, float horizontalDistance) {}

        /**

         * 
         * @param horizontal: Horizontal position in the video (in %, from left to right)
         * @param vertical: Vertical position in the video (in %, from bottom to top)
         */
        public default void onTargetFramingPositionChanged(int horizontal, int vertical) {}

        /**

         * 
         * @param state:
         */
        public default void onTargetImageDetectionState(@Nullable ImageDetectionStatus state) {}

        /**

         * 
         * @param state: Boolean. 1 if the target is the controller, 0 otherwise
         */
        public default void onTargetIsController(int state) {}

        /**
         * Leash mode configuration changed. This event is only valid when arg behavior in [state](#134-3) is equal to
         * Follow.
         * 
         * @param useDefaultBitField:
         * @param distance: The distance leader-follower in meter If distance is default, this value is the current
         * drone distance
         * @param elevation: The elevation leader-follower in rad If elevation is default, this value is the current
         * leader to drone elevation
         * @param azimuth: The azimuth course-leader-follower in rad If azimuth is default, this value is the current
         * leader to drone azimuth
         */
        public default void onLeashConfig(int useDefaultBitField, float distance, float elevation, float azimuth) {}
    }

    private static void state(Callback cb, int mode, int behavior, int animation, int animationAvailableBitField) {
        ArsdkFeatureFollowMe.Mode enumMode = ArsdkFeatureFollowMe.Mode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureFollowMe.Mode value " + mode);
        ArsdkFeatureFollowMe.Behavior enumBehavior = ArsdkFeatureFollowMe.Behavior.fromValue(behavior);
        if (enumBehavior == null) ULog.e(TAG, "Unsupported ArsdkFeatureFollowMe.Behavior value " + behavior);
        ArsdkFeatureFollowMe.Animation enumAnimation = ArsdkFeatureFollowMe.Animation.fromValue(animation);
        if (enumAnimation == null) ULog.e(TAG, "Unsupported ArsdkFeatureFollowMe.Animation value " + animation);
        try {
            cb.onState(enumMode, enumBehavior, enumAnimation, animationAvailableBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.state [mode: " + mode + ", behavior: " + behavior + ", animation: " + animation + ", animation_available: " + animationAvailableBitField + "]", e);
        }
    }

    private static void modeInfo(Callback cb, int mode, int missingRequirementsBitField, int improvementsBitField) {
        ArsdkFeatureFollowMe.Mode enumMode = ArsdkFeatureFollowMe.Mode.fromValue(mode);
        if (enumMode == null) ULog.e(TAG, "Unsupported ArsdkFeatureFollowMe.Mode value " + mode);
        try {
            cb.onModeInfo(enumMode, missingRequirementsBitField, improvementsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.mode_info [mode: " + mode + ", missing_requirements: " + missingRequirementsBitField + ", improvements: " + improvementsBitField + "]", e);
        }
    }

    private static void geographicConfig(Callback cb, int useDefaultBitField, float distance, float elevation, float azimuth) {
        try {
            cb.onGeographicConfig(useDefaultBitField, distance, elevation, azimuth);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.geographic_config [use_default: " + useDefaultBitField + ", distance: " + distance + ", elevation: " + elevation + ", azimuth: " + azimuth + "]", e);
        }
    }

    private static void relativeConfig(Callback cb, int useDefaultBitField, float distance, float elevation, float azimuth) {
        try {
            cb.onRelativeConfig(useDefaultBitField, distance, elevation, azimuth);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.relative_config [use_default: " + useDefaultBitField + ", distance: " + distance + ", elevation: " + elevation + ", azimuth: " + azimuth + "]", e);
        }
    }

    private static void targetTrajectory(Callback cb, double latitude, double longitude, float altitude, float northSpeed, float eastSpeed, float downSpeed) {
        try {
            cb.onTargetTrajectory(latitude, longitude, altitude, northSpeed, eastSpeed, downSpeed);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.target_trajectory [latitude: " + latitude + ", longitude: " + longitude + ", altitude: " + altitude + ", north_speed: " + northSpeed + ", east_speed: " + eastSpeed + ", down_speed: " + downSpeed + "]", e);
        }
    }

    private static void helicoidAnimConfig(Callback cb, int useDefaultBitField, float speed, float revolutionNb, float verticalDistance) {
        try {
            cb.onHelicoidAnimConfig(useDefaultBitField, speed, revolutionNb, verticalDistance);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.helicoid_anim_config [use_default: " + useDefaultBitField + ", speed: " + speed + ", revolution_nb: " + revolutionNb + ", vertical_distance: " + verticalDistance + "]", e);
        }
    }

    private static void swingAnimConfig(Callback cb, int useDefaultBitField, float speed, float verticalDistance) {
        try {
            cb.onSwingAnimConfig(useDefaultBitField, speed, verticalDistance);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.swing_anim_config [use_default: " + useDefaultBitField + ", speed: " + speed + ", vertical_distance: " + verticalDistance + "]", e);
        }
    }

    private static void boomerangAnimConfig(Callback cb, int useDefaultBitField, float speed, float distance) {
        try {
            cb.onBoomerangAnimConfig(useDefaultBitField, speed, distance);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.boomerang_anim_config [use_default: " + useDefaultBitField + ", speed: " + speed + ", distance: " + distance + "]", e);
        }
    }

    private static void candleAnimConfig(Callback cb, int useDefaultBitField, float speed, float verticalDistance) {
        try {
            cb.onCandleAnimConfig(useDefaultBitField, speed, verticalDistance);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.candle_anim_config [use_default: " + useDefaultBitField + ", speed: " + speed + ", vertical_distance: " + verticalDistance + "]", e);
        }
    }

    private static void dollySlideAnimConfig(Callback cb, int useDefaultBitField, float speed, float angle, float horizontalDistance) {
        try {
            cb.onDollySlideAnimConfig(useDefaultBitField, speed, angle, horizontalDistance);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.dolly_slide_anim_config [use_default: " + useDefaultBitField + ", speed: " + speed + ", angle: " + angle + ", horizontal_distance: " + horizontalDistance + "]", e);
        }
    }

    private static void targetFramingPositionChanged(Callback cb, int horizontal, int vertical) {
        try {
            cb.onTargetFramingPositionChanged(horizontal, vertical);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.target_framing_position_changed [horizontal: " + horizontal + ", vertical: " + vertical + "]", e);
        }
    }

    private static void targetImageDetectionState(Callback cb, int state) {
        ArsdkFeatureFollowMe.ImageDetectionStatus enumState = ArsdkFeatureFollowMe.ImageDetectionStatus.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureFollowMe.ImageDetectionStatus value " + state);
        try {
            cb.onTargetImageDetectionState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.target_image_detection_state [state: " + state + "]", e);
        }
    }

    private static void targetIsController(Callback cb, int state) {
        try {
            cb.onTargetIsController(state);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.target_is_controller [state: " + state + "]", e);
        }
    }

    private static void leashConfig(Callback cb, int useDefaultBitField, float distance, float elevation, float azimuth) {
        try {
            cb.onLeashConfig(useDefaultBitField, distance, elevation, azimuth);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: follow_me.leash_config [use_default: " + useDefaultBitField + ", distance: " + distance + ", elevation: " + elevation + ", azimuth: " + azimuth + "]", e);
        }
    }
    
    /**
     * Start a FollowMe with all its params set to the default params. Sending this command will stop other running
     * followMe.
     * 
     * @param mode:
     */
    public static ArsdkCommand encodeStart(@NonNull Mode mode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStart(cmd.getNativePtr(), mode.value);
        return cmd;
    }

    /**
     * Stop current followMe.
     */
    public static ArsdkCommand encodeStop() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStop(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Configure the geographic FollowMe. This should only be taken in account if arg behavior in [state](#134-3) is
     * equal to Follow.
     * 
     * @param useDefaultBitField:
     * @param distance: The distance leader-follower in meter Not used when arg start is at 0
     * @param elevation: The elevation leader-follower in rad (not used when arg start is at 0)
     * @param azimuth: The azimuth north-leader-follower in rad (not used when arg start is at 0)
     */
    public static ArsdkCommand encodeConfigureGeographic(int useDefaultBitField, float distance, float elevation, float azimuth) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeConfigureGeographic(cmd.getNativePtr(), useDefaultBitField, distance, elevation, azimuth);
        return cmd;
    }

    /**
     * Configure the relative FollowMe. This should only be taken in account if arg behavior in [state](#134-3) is equal
     * to Follow
     * 
     * @param useDefaultBitField:
     * @param distance: The distance leader-follower in meter
     * @param elevation: The elevation leader-follower in rad
     * @param azimuth: The azimuth north-leader-follower in rad
     */
    public static ArsdkCommand encodeConfigureRelative(int useDefaultBitField, float distance, float elevation, float azimuth) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeConfigureRelative(cmd.getNativePtr(), useDefaultBitField, distance, elevation, azimuth);
        return cmd;
    }

    /**
     * Stop current followMe animation. This message has been deprecated. Please use the animation feature.
     */
    @Deprecated
    public static ArsdkCommand encodeStopAnimation() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStopAnimation(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Start a helicoid animation. The helicoid animation allows the drone to revolve around the target while going up,
     * with a fixed radius. This message has been deprecated. Please use the animation feature.
     * 
     * @param useDefaultBitField:
     * @param speed: The desired speed of the anim in m/s Not used when speed_is_default is 1
     * @param revolutionNumber: The number of revolution (in turn) Negative value is infinite Example: 1.5 makes an
     * entire turn plus half of a turn Not used when revolutionNb_is_default is 1
     * @param verticalDistance: Distance that should be made by the product to reach the top of the helicoid in m Not
     * used when verticalDistance_is_default is 1
     */
    @Deprecated
    public static ArsdkCommand encodeStartHelicoidAnim(int useDefaultBitField, float speed, float revolutionNumber, float verticalDistance) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartHelicoidAnim(cmd.getNativePtr(), useDefaultBitField, speed, revolutionNumber, verticalDistance);
        return cmd;
    }

    /**
     * Start a swing animation. The swing animation enables a vertical point of view while the drone passes over the
     * target. This message has been deprecated. Please use the animation feature.
     * 
     * @param useDefaultBitField:
     * @param speed: The desired speed of the anim in m/s Not used when speed_is_default is 1 Not used when start is 0
     * @param verticalDistance: Distance that should be made by the product to reach the top of the swing in m Not used
     * when verticalDistance_is_default is 1 Not used when start is 0
     */
    @Deprecated
    public static ArsdkCommand encodeStartSwingAnim(int useDefaultBitField, float speed, float verticalDistance) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartSwingAnim(cmd.getNativePtr(), useDefaultBitField, speed, verticalDistance);
        return cmd;
    }

    /**
     * Start a boomerang animation. The boomerang animation enables a zoom-out/zoom-in trajectory while preserving the
     * framing chosen by the user. This message has been deprecated. Please use the animation feature.
     * 
     * @param useDefaultBitField:
     * @param speed: The desired speed of the anim in m/s Not used when speed_is_default is 1 Not used when start is 0
     * @param distance: Distance that should be made by the product to reach its return point in m Not used when
     * distance_is_default is 1 Not used when start is 0
     */
    @Deprecated
    public static ArsdkCommand encodeStartBoomerangAnim(int useDefaultBitField, float speed, float distance) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartBoomerangAnim(cmd.getNativePtr(), useDefaultBitField, speed, distance);
        return cmd;
    }

    /**
     * Start a candle animation. The candle animation enables a zoom-in directly on the target followed by a vertical
     * zoom-out. This message has been deprecated. Please use the animation feature.
     * 
     * @param useDefaultBitField:
     * @param speed: The desired speed of the anim in m/s Not used when speed_is_default is 1 Not used when start is 0
     * @param verticalDistance: Distance that should be made by the product to reach the top of the vertical zoom-out in
     * m Not used when verticalDistance_is_default is 1 Not used when start is 0
     */
    @Deprecated
    public static ArsdkCommand encodeStartCandleAnim(int useDefaultBitField, float speed, float verticalDistance) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartCandleAnim(cmd.getNativePtr(), useDefaultBitField, speed, verticalDistance);
        return cmd;
    }

    /**
     * Start a dolly slide animation. Allows the drone to catch up to the target before flying past it, creating a zoom-
     * in/zoom_out effect without a curved path. This message has been deprecated. Please use the animation feature.
     * 
     * @param useDefaultBitField:
     * @param speed: The desired speed of the anim in m/s Not used when speed_is_default is 1 Not used when start is 0
     * @param angle: Desired angle Product-User-Target in rad Not used when angle_is_default is 1 Not used when start is
     * 0
     * @param horizontalDistance: Distance that should be made by the product to reach its target in m Not used when
     * horizontalDistance_is_default is 1 Not used when start is 0
     */
    @Deprecated
    public static ArsdkCommand encodeStartDollySlideAnim(int useDefaultBitField, float speed, float angle, float horizontalDistance) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartDollySlideAnim(cmd.getNativePtr(), useDefaultBitField, speed, angle, horizontalDistance);
        return cmd;
    }

    /**
     * Set the desired target framing in the video.
     * 
     * @param horizontal: Horizontal position in the video (in %, from left to right)
     * @param vertical: Vertical position in the video (in %, from bottom to top)
     */
    public static ArsdkCommand encodeTargetFramingPosition(int horizontal, int vertical) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeTargetFramingPosition(cmd.getNativePtr(), horizontal, vertical);
        return cmd;
    }

    /**
     * Send vision detection results.
     * 
     * @param targetAzimuth: Horizontal north-drone-target angle in radian
     * @param targetElevation: Vertical angle horizon-drone-target in radian
     * @param changeOfScale: Normalized relative radial speed in 1/second
     * @param confidenceIndex: Confidence index of the detection (from 0 to 255, the highest is the best)
     * @param isNewSelection: Boolean. 1 if the selection is new, 0 otherwise
     * @param timestamp: Acquisition time of processed picture in millisecond
     */
    public static ArsdkCommand encodeTargetImageDetection(float targetAzimuth, float targetElevation, float changeOfScale, int confidenceIndex, int isNewSelection, long timestamp) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeTargetImageDetection(cmd.getNativePtr(), targetAzimuth, targetElevation, changeOfScale, confidenceIndex, isNewSelection, timestamp);
        return cmd;
    }

    /**
     * Tells that the target to follow is the controller and drone can use controller gps sent by [controller_info
     * gps](#140-1) and barometer data sent by [controller_info barometer](#140-2) as valid to locate the target.
     * 
     * @param targetIsController: Boolean. 1 if the target is the controller, 0 otherwise
     */
    public static ArsdkCommand encodeSetTargetIsController(int targetIsController) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetTargetIsController(cmd.getNativePtr(), targetIsController);
        return cmd;
    }

    /**
     * Configure the leash mode of FollowMe. This should only be taken in account if arg behavior in [state](#134-3) is
     * equal to Follow
     * 
     * @param useDefaultBitField:
     * @param distance: The distance leader-follower in meter
     * @param elevation: The elevation leader-follower in rad
     * @param azimuth: The azimuth north-leader-follower in rad
     */
    public static ArsdkCommand encodeConfigureLeash(int useDefaultBitField, float distance, float elevation, float azimuth) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeConfigureLeash(cmd.getNativePtr(), useDefaultBitField, distance, elevation, azimuth);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeStart(long nativeCmd, int mode);
    private static native int nativeEncodeStop(long nativeCmd);
    private static native int nativeEncodeConfigureGeographic(long nativeCmd, int use_default, float distance, float elevation, float azimuth);
    private static native int nativeEncodeConfigureRelative(long nativeCmd, int use_default, float distance, float elevation, float azimuth);
    private static native int nativeEncodeStopAnimation(long nativeCmd);
    private static native int nativeEncodeStartHelicoidAnim(long nativeCmd, int use_default, float speed, float revolution_number, float vertical_distance);
    private static native int nativeEncodeStartSwingAnim(long nativeCmd, int use_default, float speed, float vertical_distance);
    private static native int nativeEncodeStartBoomerangAnim(long nativeCmd, int use_default, float speed, float distance);
    private static native int nativeEncodeStartCandleAnim(long nativeCmd, int use_default, float speed, float vertical_distance);
    private static native int nativeEncodeStartDollySlideAnim(long nativeCmd, int use_default, float speed, float angle, float horizontal_distance);
    private static native int nativeEncodeTargetFramingPosition(long nativeCmd, int horizontal, int vertical);
    private static native int nativeEncodeTargetImageDetection(long nativeCmd, float target_azimuth, float target_elevation, float change_of_scale, int confidence_index, int is_new_selection, long timestamp);
    private static native int nativeEncodeSetTargetIsController(long nativeCmd, int target_is_controller);
    private static native int nativeEncodeConfigureLeash(long nativeCmd, int use_default, float distance, float elevation, float azimuth);
}
