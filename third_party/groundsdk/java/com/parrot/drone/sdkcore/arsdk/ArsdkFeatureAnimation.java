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
 * Animation feature command/event interface.
 */
public class ArsdkFeatureAnimation {

    /**
     * Animation type.
     */
    public enum Type {

        /**
         * No animation.
         */
        NONE(0),

        /**
         * The drone makes a flip.
         */
        FLIP(1),

        /**
         * The drone horizontaly rotates on itself.
         */
        HORIZONTAL_PANORAMA(2),

        /**
         * The drone flies away on a given distance with a computed angle.
         */
        DRONIE(3),

        /**
         * The drone starts looking down, then moves forward while slowly looking at the horizon.
         */
        HORIZONTAL_REVEAL(4),

        /**
         * The drone starts looking down, then moves up while slowly looking at the horizon. When it reaches its target
         * altitude, it rotates on itself to do a panorama.
         */
        VERTICAL_REVEAL(5),

        /**
         * The drone circles around its target.
         */
        SPIRAL(6),

        /**
         * The drone makes a parabola on top of its target and ends on the other side of it.
         */
        PARABOLA(7),

        /**
         * The drone flies horizontally in direction of the target then flies up.
         */
        CANDLE(8),

        /**
         * The drone slides horizontally.
         */
        DOLLY_SLIDE(9),

        /**
         * Zoom in on the subject, while the drone moves away from it.
         */
        VERTIGO(10),

        /**
         * The drone moves up while rotating slowly on itself.
         */
        TWIST_UP(11),

        /**
         * The drone place itself above the target, then moves up while rotating slowly on itself.
         */
        POSITION_TWIST_UP(12),

        /**
         * The drone performs a 180 degrees rotation on the yaw axis while taking photos at various angles. The
         * resulting set of photos can then be retrieved as a single media that may be post-processed to make panoramic
         * images.
         */
        HORIZONTAL_180_PHOTO_PANORAMA(13),

        /**
         * The drone camera performs a 180 degrees rotation on the tilt axis while taking photos at various angles. The
         * resulting set of photos can then be retrieved as a single media that may be post-processed to make panoramic
         * images.
         */
        VERTICAL_180_PHOTO_PANORAMA(14),

        /**
         * The drone performs a 360 degrees rotation on the yaw axis. At various angles, rotation pauses, drone camera
         * performs a 180 degrees rotation on the tilt axis while taking photos at various angles, then drone yaw
         * rotation resumes. The resulting set of photos can then be retrieved as a single media that may be post-
         * processed to make panoramic images.
         */
        SPHERICAL_PHOTO_PANORAMA(15);

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
        public static Type fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<Type> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 16) {
                    ULog.e(TAG, "Unsupported Type bitfield value " + value);
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
        public static EnumSet<Type> fromBitfield(int bitfield) {
            EnumSet<Type> enums = EnumSet.noneOf(Type.class);
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
        public static int toBitField(@NonNull Type... enums) {
            int bitField = 0;
            for (Type e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private Type(int value) {
            this.value = value;
        }

        private static final SparseArray<Type> MAP;

        static {
            MAP = new SparseArray<>();
            for (Type e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation state.
     */
    public enum State {

        /**
         * The animation is not running.
         */
        IDLE(0),

        /**
         * The animation is running.
         */
        RUNNING(1),

        /**
         * The current animation is canceling.
         */
        CANCELING(2);

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
     * Animation play mode.
     */
    public enum PlayMode {

        /**
         * Animation is played once, normally.
         */
        NORMAL(0),

        /**
         * Animation is played once and then the animation is played mirrored.
         */
        ONCE_THEN_MIRRORED(1);

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
        public static PlayMode fromValue(int value) {
            return MAP.get(value, null);
        }

        private PlayMode(int value) {
            this.value = value;
        }

        private static final SparseArray<PlayMode> MAP;

        static {
            MAP = new SparseArray<>();
            for (PlayMode e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Animation flip type.
     */
    public enum FlipType {

        /**
         * The drone makes a front flip.
         */
        FRONT(0),

        /**
         * The drone makes a back flip.
         */
        BACK(1),

        /**
         * The drone makes a left flip (its left side goes up).
         */
        LEFT(2),

        /**
         * The drone makes a right flip (its right side goes up).
         */
        RIGHT(3);

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
        public static FlipType fromValue(int value) {
            return MAP.get(value, null);
        }

        private FlipType(int value) {
            this.value = value;
        }

        private static final SparseArray<FlipType> MAP;

        static {
            MAP = new SparseArray<>();
            for (FlipType e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Horizontal panorama configuration parameter.
     */
    public enum HorizontalPanoramaConfigParam {

        /**
         * Rotation angle parameter.
         */
        ROTATION_ANGLE(0),

        /**
         * Rotation speed parameter.
         */
        ROTATION_SPEED(1);

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
        public static HorizontalPanoramaConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<HorizontalPanoramaConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 2) {
                    ULog.e(TAG, "Unsupported HorizontalPanoramaConfigParam bitfield value " + value);
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
        public static EnumSet<HorizontalPanoramaConfigParam> fromBitfield(int bitfield) {
            EnumSet<HorizontalPanoramaConfigParam> enums = EnumSet.noneOf(HorizontalPanoramaConfigParam.class);
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
        public static int toBitField(@NonNull HorizontalPanoramaConfigParam... enums) {
            int bitField = 0;
            for (HorizontalPanoramaConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private HorizontalPanoramaConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<HorizontalPanoramaConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (HorizontalPanoramaConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Dronie animation configuration parameter.
     */
    public enum DronieConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Distance parameter.
         */
        DISTANCE(1),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(2);

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
        public static DronieConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<DronieConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported DronieConfigParam bitfield value " + value);
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
        public static EnumSet<DronieConfigParam> fromBitfield(int bitfield) {
            EnumSet<DronieConfigParam> enums = EnumSet.noneOf(DronieConfigParam.class);
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
        public static int toBitField(@NonNull DronieConfigParam... enums) {
            int bitField = 0;
            for (DronieConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private DronieConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<DronieConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (DronieConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Horizontal reveal animation configuration parameter.
     */
    public enum HorizontalRevealConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Distance parameter.
         */
        DISTANCE(1),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(2);

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
        public static HorizontalRevealConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<HorizontalRevealConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported HorizontalRevealConfigParam bitfield value " + value);
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
        public static EnumSet<HorizontalRevealConfigParam> fromBitfield(int bitfield) {
            EnumSet<HorizontalRevealConfigParam> enums = EnumSet.noneOf(HorizontalRevealConfigParam.class);
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
        public static int toBitField(@NonNull HorizontalRevealConfigParam... enums) {
            int bitField = 0;
            for (HorizontalRevealConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private HorizontalRevealConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<HorizontalRevealConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (HorizontalRevealConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Vertical reveal animation configuration parameter.
     */
    public enum VerticalRevealConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Vertical distance parameter.
         */
        VERTICAL_DISTANCE(1),

        /**
         * Rotation angle parameter.
         */
        ROTATION_ANGLE(2),

        /**
         * Rotation speed parameter.
         */
        ROTATION_SPEED(3),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(4);

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
        public static VerticalRevealConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<VerticalRevealConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported VerticalRevealConfigParam bitfield value " + value);
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
        public static EnumSet<VerticalRevealConfigParam> fromBitfield(int bitfield) {
            EnumSet<VerticalRevealConfigParam> enums = EnumSet.noneOf(VerticalRevealConfigParam.class);
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
        public static int toBitField(@NonNull VerticalRevealConfigParam... enums) {
            int bitField = 0;
            for (VerticalRevealConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private VerticalRevealConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<VerticalRevealConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (VerticalRevealConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Spiral animation configuration parameter.
     */
    public enum SpiralConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Radius variation parameter.
         */
        RADIUS_VARIATION(1),

        /**
         * Vertical distance parameter.
         */
        VERTICAL_DISTANCE(2),

        /**
         * Revolution number parameter.
         */
        REVOLUTION_NB(3),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(4);

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
        public static SpiralConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SpiralConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported SpiralConfigParam bitfield value " + value);
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
        public static EnumSet<SpiralConfigParam> fromBitfield(int bitfield) {
            EnumSet<SpiralConfigParam> enums = EnumSet.noneOf(SpiralConfigParam.class);
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
        public static int toBitField(@NonNull SpiralConfigParam... enums) {
            int bitField = 0;
            for (SpiralConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SpiralConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<SpiralConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (SpiralConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Parabola animation configuration parameter.
     */
    public enum ParabolaConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Vertical distance parameter.
         */
        VERTICAL_DISTANCE(1),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(2);

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
        public static ParabolaConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<ParabolaConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported ParabolaConfigParam bitfield value " + value);
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
        public static EnumSet<ParabolaConfigParam> fromBitfield(int bitfield) {
            EnumSet<ParabolaConfigParam> enums = EnumSet.noneOf(ParabolaConfigParam.class);
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
        public static int toBitField(@NonNull ParabolaConfigParam... enums) {
            int bitField = 0;
            for (ParabolaConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private ParabolaConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<ParabolaConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (ParabolaConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Candle animation configuration parameter.
     */
    public enum CandleConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Vertical distance parameter.
         */
        VERTICAL_DISTANCE(1),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(2);

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
        public static CandleConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<CandleConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported CandleConfigParam bitfield value " + value);
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
        public static EnumSet<CandleConfigParam> fromBitfield(int bitfield) {
            EnumSet<CandleConfigParam> enums = EnumSet.noneOf(CandleConfigParam.class);
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
        public static int toBitField(@NonNull CandleConfigParam... enums) {
            int bitField = 0;
            for (CandleConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private CandleConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<CandleConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (CandleConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Dolly slide animation configuration parameter.
     */
    public enum DollySlideConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Angle parameter.
         */
        ANGLE(1),

        /**
         * Horizontal distance parameter.
         */
        HORIZONTAL_DISTANCE(2),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(3);

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
        public static DollySlideConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<DollySlideConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported DollySlideConfigParam bitfield value " + value);
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
        public static EnumSet<DollySlideConfigParam> fromBitfield(int bitfield) {
            EnumSet<DollySlideConfigParam> enums = EnumSet.noneOf(DollySlideConfigParam.class);
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
        public static int toBitField(@NonNull DollySlideConfigParam... enums) {
            int bitField = 0;
            for (DollySlideConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private DollySlideConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<DollySlideConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (DollySlideConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Vertigo animation configuration parameter.
     */
    public enum VertigoConfigParam {

        /**
         * Duration parameter.
         */
        DURATION(0),

        /**
         * Maximum zoom level parameter.
         */
        MAX_ZOOM_LEVEL(1),

        /**
         * Finish action parameter.
         */
        FINISH_ACTION(2),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(3);

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
        public static VertigoConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<VertigoConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported VertigoConfigParam bitfield value " + value);
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
        public static EnumSet<VertigoConfigParam> fromBitfield(int bitfield) {
            EnumSet<VertigoConfigParam> enums = EnumSet.noneOf(VertigoConfigParam.class);
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
        public static int toBitField(@NonNull VertigoConfigParam... enums) {
            int bitField = 0;
            for (VertigoConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private VertigoConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<VertigoConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (VertigoConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Twist-up animation configuration parameter.
     */
    public enum TwistUpConfigParam {

        /**
         * Speed parameter.
         */
        SPEED(0),

        /**
         * Vertical distance parameter.
         */
        VERTICAL_DISTANCE(1),

        /**
         * Rotation angle parameter.
         */
        ROTATION_ANGLE(2),

        /**
         * Rotation speed parameter.
         */
        ROTATION_SPEED(3),

        /**
         * Play mode parameter.
         */
        PLAY_MODE(4);

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
        public static TwistUpConfigParam fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<TwistUpConfigParam> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 5) {
                    ULog.e(TAG, "Unsupported TwistUpConfigParam bitfield value " + value);
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
        public static EnumSet<TwistUpConfigParam> fromBitfield(int bitfield) {
            EnumSet<TwistUpConfigParam> enums = EnumSet.noneOf(TwistUpConfigParam.class);
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
        public static int toBitField(@NonNull TwistUpConfigParam... enums) {
            int bitField = 0;
            for (TwistUpConfigParam e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private TwistUpConfigParam(int value) {
            this.value = value;
        }

        private static final SparseArray<TwistUpConfigParam> MAP;

        static {
            MAP = new SparseArray<>();
            for (TwistUpConfigParam e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Action to execute at the end of a Vertigo.
     */
    public enum VertigoFinishAction {

        /**
         * Do nothing special.
         */
        NONE(0),

        /**
         * Move zoom level back to x1.
         */
        UNZOOM(1);

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
        public static VertigoFinishAction fromValue(int value) {
            return MAP.get(value, null);
        }

        private VertigoFinishAction(int value) {
            this.value = value;
        }

        private static final SparseArray<VertigoFinishAction> MAP;

        static {
            MAP = new SparseArray<>();
            for (VertigoFinishAction e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9000;

    /** Uid of availability event. */;
    public static final int AVAILABILITY_UID = 0x0001;

    /** Uid of state event. */;
    public static final int STATE_UID = 0x0002;

    /** Uid of flip_state event. */;
    public static final int FLIP_STATE_UID = 0x0005;

    /** Uid of horizontal_panorama_state event. */;
    public static final int HORIZONTAL_PANORAMA_STATE_UID = 0x0007;

    /** Uid of dronie_state event. */;
    public static final int DRONIE_STATE_UID = 0x0009;

    /** Uid of horizontal_reveal_state event. */;
    public static final int HORIZONTAL_REVEAL_STATE_UID = 0x000B;

    /** Uid of vertical_reveal_state event. */;
    public static final int VERTICAL_REVEAL_STATE_UID = 0x000D;

    /** Uid of spiral_state event. */;
    public static final int SPIRAL_STATE_UID = 0x000F;

    /** Uid of parabola_state event. */;
    public static final int PARABOLA_STATE_UID = 0x0011;

    /** Uid of candle_state event. */;
    public static final int CANDLE_STATE_UID = 0x0013;

    /** Uid of dolly_slide_state event. */;
    public static final int DOLLY_SLIDE_STATE_UID = 0x0015;

    /** Uid of vertigo_state event. */;
    public static final int VERTIGO_STATE_UID = 0x0017;

    /** Uid of twist_up_state event. */;
    public static final int TWIST_UP_STATE_UID = 0x0019;

    /** Uid of position_twist_up_state event. */;
    public static final int POSITION_TWIST_UP_STATE_UID = 0x001B;

    /** Uid of horizontal_180_photo_panorama_state event. */;
    public static final int HORIZONTAL_180_PHOTO_PANORAMA_STATE_UID = 0x001D;

    /** Uid of vertical_180_photo_panorama_state event. */;
    public static final int VERTICAL_180_PHOTO_PANORAMA_STATE_UID = 0x001F;

    /** Uid of spherical_photo_panorama_state event. */;
    public static final int SPHERICAL_PHOTO_PANORAMA_STATE_UID = 0x0021;

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
         * @param valuesBitField: Bitfield of available animation types
         */
        public default void onAvailability(int valuesBitField) {}

        /**

         * 
         * @param type: Type of the animation. None if no animation is currently running or canceling.
         * @param percent: Percentage of the animation (only accurate if type is not none) (from 0 to 100).
         */
        public default void onState(@Nullable Type type, int percent) {}

        /**

         * 
         * @param state: State of the animation
         * @param type: Type of the flip (only accurate if state is not idle)
         */
        public default void onFlipState(@Nullable State state, @Nullable FlipType type) {}

        /**

         * 
         * @param state: State of the animation
         * @param rotationAngle: Rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
         * clockwise. (only accurate if state is not idle)
         * @param rotationSpeed: The rotation speed of the anim in rad/s (only accurate if state is not idle)
         */
        public default void onHorizontalPanoramaState(@Nullable State state, float rotationAngle, float rotationSpeed) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param distance: Dronie distance in m. (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onDronieState(@Nullable State state, float speed, float distance, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param distance: Distance in m. (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onHorizontalRevealState(@Nullable State state, float speed, float distance, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. (only accurate if state is not idle)
         * @param rotationAngle: Rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
         * clockwise. (only accurate if state is not idle)
         * @param rotationSpeed: The rotation speed of the anim in rad/s (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onVerticalRevealState(@Nullable State state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param radiusVariation: Relative radius variation in m. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. Negative value means the animation is directed toward the
         * ground. (only accurate if state is not idle)
         * @param revolutionNb: The number of revolution (in turn). (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onSpiralState(@Nullable State state, float speed, float radiusVariation, float verticalDistance, float revolutionNb, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onParabolaState(@Nullable State state, float speed, float verticalDistance, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onCandleState(@Nullable State state, float speed, float verticalDistance, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param angle: Drone-target-destination angle in rad. (only accurate if state is not idle)
         * @param horizontalDistance: Horizontal distance in m. (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onDollySlideState(@Nullable State state, float speed, float angle, float horizontalDistance, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation.
         * @param duration: Duration in seconds. (only accurate if state is not idle)
         * @param maxZoomLevel: Maximum zoom level. (only accurate if state is not idle)
         * @param finishAction: Action that will be executed at the end of the animation. (only accurate if state is not
         * idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onVertigoState(@Nullable State state, float duration, float maxZoomLevel, @Nullable VertigoFinishAction finishAction, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. (only accurate if state is not idle)
         * @param rotationAngle: Rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
         * clockwise. (only accurate if state is not idle)
         * @param rotationSpeed: The rotation speed of the anim in rad/s (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onTwistUpState(@Nullable State state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         * @param speed: Speed in m/s. (only accurate if state is not idle)
         * @param verticalDistance: Vertical distance in m. (only accurate if state is not idle)
         * @param rotationAngle: Rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
         * clockwise. (only accurate if state is not idle)
         * @param rotationSpeed: The rotation speed of the anim in rad/s (only accurate if state is not idle)
         * @param playMode: Play mode. (only accurate if state is not idle)
         */
        public default void onPositionTwistUpState(@Nullable State state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @Nullable PlayMode playMode) {}

        /**

         * 
         * @param state: State of the animation
         */
        public default void onHorizontal180PhotoPanoramaState(@Nullable State state) {}

        /**

         * 
         * @param state: State of the animation
         */
        public default void onVertical180PhotoPanoramaState(@Nullable State state) {}

        /**

         * 
         * @param state: State of the animation
         */
        public default void onSphericalPhotoPanoramaState(@Nullable State state) {}
    }

    private static void availability(Callback cb, int valuesBitField) {
        try {
            cb.onAvailability(valuesBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.availability [values: " + valuesBitField + "]", e);
        }
    }

    private static void state(Callback cb, int type, int percent) {
        ArsdkFeatureAnimation.Type enumType = ArsdkFeatureAnimation.Type.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.Type value " + type);
        try {
            cb.onState(enumType, percent);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.state [type: " + type + ", percent: " + percent + "]", e);
        }
    }

    private static void flipState(Callback cb, int state, int type) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.FlipType enumType = ArsdkFeatureAnimation.FlipType.fromValue(type);
        if (enumType == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.FlipType value " + type);
        try {
            cb.onFlipState(enumState, enumType);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.flip_state [state: " + state + ", type: " + type + "]", e);
        }
    }

    private static void horizontalPanoramaState(Callback cb, int state, float rotationAngle, float rotationSpeed) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        try {
            cb.onHorizontalPanoramaState(enumState, rotationAngle, rotationSpeed);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.horizontal_panorama_state [state: " + state + ", rotation_angle: " + rotationAngle + ", rotation_speed: " + rotationSpeed + "]", e);
        }
    }

    private static void dronieState(Callback cb, int state, float speed, float distance, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onDronieState(enumState, speed, distance, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.dronie_state [state: " + state + ", speed: " + speed + ", distance: " + distance + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void horizontalRevealState(Callback cb, int state, float speed, float distance, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onHorizontalRevealState(enumState, speed, distance, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.horizontal_reveal_state [state: " + state + ", speed: " + speed + ", distance: " + distance + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void verticalRevealState(Callback cb, int state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onVerticalRevealState(enumState, speed, verticalDistance, rotationAngle, rotationSpeed, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.vertical_reveal_state [state: " + state + ", speed: " + speed + ", vertical_distance: " + verticalDistance + ", rotation_angle: " + rotationAngle + ", rotation_speed: " + rotationSpeed + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void spiralState(Callback cb, int state, float speed, float radiusVariation, float verticalDistance, float revolutionNb, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onSpiralState(enumState, speed, radiusVariation, verticalDistance, revolutionNb, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.spiral_state [state: " + state + ", speed: " + speed + ", radius_variation: " + radiusVariation + ", vertical_distance: " + verticalDistance + ", revolution_nb: " + revolutionNb + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void parabolaState(Callback cb, int state, float speed, float verticalDistance, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onParabolaState(enumState, speed, verticalDistance, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.parabola_state [state: " + state + ", speed: " + speed + ", vertical_distance: " + verticalDistance + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void candleState(Callback cb, int state, float speed, float verticalDistance, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onCandleState(enumState, speed, verticalDistance, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.candle_state [state: " + state + ", speed: " + speed + ", vertical_distance: " + verticalDistance + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void dollySlideState(Callback cb, int state, float speed, float angle, float horizontalDistance, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onDollySlideState(enumState, speed, angle, horizontalDistance, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.dolly_slide_state [state: " + state + ", speed: " + speed + ", angle: " + angle + ", horizontal_distance: " + horizontalDistance + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void vertigoState(Callback cb, int state, float duration, float maxZoomLevel, int finishAction, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.VertigoFinishAction enumFinishaction = ArsdkFeatureAnimation.VertigoFinishAction.fromValue(finishAction);
        if (enumFinishaction == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.VertigoFinishAction value " + finishAction);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onVertigoState(enumState, duration, maxZoomLevel, enumFinishaction, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.vertigo_state [state: " + state + ", duration: " + duration + ", max_zoom_level: " + maxZoomLevel + ", finish_action: " + finishAction + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void twistUpState(Callback cb, int state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onTwistUpState(enumState, speed, verticalDistance, rotationAngle, rotationSpeed, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.twist_up_state [state: " + state + ", speed: " + speed + ", vertical_distance: " + verticalDistance + ", rotation_angle: " + rotationAngle + ", rotation_speed: " + rotationSpeed + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void positionTwistUpState(Callback cb, int state, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, int playMode) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        ArsdkFeatureAnimation.PlayMode enumPlaymode = ArsdkFeatureAnimation.PlayMode.fromValue(playMode);
        if (enumPlaymode == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.PlayMode value " + playMode);
        try {
            cb.onPositionTwistUpState(enumState, speed, verticalDistance, rotationAngle, rotationSpeed, enumPlaymode);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.position_twist_up_state [state: " + state + ", speed: " + speed + ", vertical_distance: " + verticalDistance + ", rotation_angle: " + rotationAngle + ", rotation_speed: " + rotationSpeed + ", play_mode: " + playMode + "]", e);
        }
    }

    private static void horizontal180PhotoPanoramaState(Callback cb, int state) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        try {
            cb.onHorizontal180PhotoPanoramaState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.horizontal_180_photo_panorama_state [state: " + state + "]", e);
        }
    }

    private static void vertical180PhotoPanoramaState(Callback cb, int state) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        try {
            cb.onVertical180PhotoPanoramaState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.vertical_180_photo_panorama_state [state: " + state + "]", e);
        }
    }

    private static void sphericalPhotoPanoramaState(Callback cb, int state) {
        ArsdkFeatureAnimation.State enumState = ArsdkFeatureAnimation.State.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureAnimation.State value " + state);
        try {
            cb.onSphericalPhotoPanoramaState(enumState);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: animation.spherical_photo_panorama_state [state: " + state + "]", e);
        }
    }
    
    /**
     * Cancel current animation.
     */
    public static ArsdkCommand encodeCancel() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeCancel(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Start a flip animation. Starting this animation when another animation is started (or canceling), will cancel the
     * current one to start this one.
     * 
     * @param type: Type of the flip
     */
    public static ArsdkCommand encodeStartFlip(@NonNull FlipType type) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartFlip(cmd.getNativePtr(), type.value);
        return cmd;
    }

    /**
     * Start an horizontal panorama animation. Starting this animation when another animation is started (or canceling),
     * will cancel the current one to start this one. This animation will make the drone horizontaly rotates on itself.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param rotationAngle: Desired rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
     * clockwise. Not used when rotation angle of provided_params param is 0.
     * @param rotationSpeed: The desired rotation speed of the anim in rad/s Not used when rotation speed of
     * provided_params param is 0.
     */
    public static ArsdkCommand encodeStartHorizontalPanorama(int providedParamsBitField, float rotationAngle, float rotationSpeed) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartHorizontalPanorama(cmd.getNativePtr(), providedParamsBitField, rotationAngle, rotationSpeed);
        return cmd;
    }

    /**
     * Start a dronie animation. Starting this animation when another animation is started (or canceling), will cancel
     * the current one to start this one. This animation will make the drone flies away on a given distance with a
     * computed angle.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param distance: Desired dronie distance in m (length of the hypotenuse). Not used when distance of
     * provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartDronie(int providedParamsBitField, float speed, float distance, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartDronie(cmd.getNativePtr(), providedParamsBitField, speed, distance, playMode.value);
        return cmd;
    }

    /**
     * Start an horizontal reveal animation. Starting this animation when another animation is started (or canceling),
     * will cancel the current one to start this one. This animation will make the drone starts looking down, then moves
     * forward while slowly looking at the horizon.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param distance: Desired distance in m. Not used when distance of provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartHorizontalReveal(int providedParamsBitField, float speed, float distance, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartHorizontalReveal(cmd.getNativePtr(), providedParamsBitField, speed, distance, playMode.value);
        return cmd;
    }

    /**
     * Start a vertical reveal animation. Starting this animation when another animation is started (or canceling), will
     * cancel the current one to start this one. This animation will make the drone starts looking down, then moves up
     * while slowly looking at the horizon. When it reaches its target altitude, it rotates on itself to do a panorama.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. Not used when vertical distance of provided_params param
     * is 0.
     * @param rotationAngle: Desired rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
     * clockwise. Not used when rotation angle of provided_params param is 0.
     * @param rotationSpeed: The desired rotation speed of the anim in rad/s Not used when rotation speed of
     * provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartVerticalReveal(int providedParamsBitField, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartVerticalReveal(cmd.getNativePtr(), providedParamsBitField, speed, verticalDistance, rotationAngle, rotationSpeed, playMode.value);
        return cmd;
    }

    /**
     * Start a spiral animation. Starting this animation when another animation is started (or canceling), will cancel
     * the current one to start this one. This animation will make the drone circles around its target.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param radiusVariation: Desired relative radius variation. A value of 2 means that the ending radius will be
     * twice as big as the starting radius. A value of -2 means that the ending radius will half of the size of the
     * starting radius. A value of 1 means that the radius will not change during the animation. Not used when radius
     * variation of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. If negative, the spiral will be directed to the ground.
     * Not used when vertical distance of provided_params param is 0.
     * @param revolutionNb: The number of revolution (in turn). Positive value makes a clockwise spiral, negative is
     * anti-clockwise. Example: 1.5 makes an entire turn plus half of a turn
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartSpiral(int providedParamsBitField, float speed, float radiusVariation, float verticalDistance, float revolutionNb, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartSpiral(cmd.getNativePtr(), providedParamsBitField, speed, radiusVariation, verticalDistance, revolutionNb, playMode.value);
        return cmd;
    }

    /**
     * Start a parabola animation. Starting this animation when another animation is started (or canceling), will cancel
     * the current one to start this one. This animation will make the drone makes a parabola on top of its target and
     * ends on the other side of it.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. Not used when vertical distance of provided_params param
     * is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartParabola(int providedParamsBitField, float speed, float verticalDistance, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartParabola(cmd.getNativePtr(), providedParamsBitField, speed, verticalDistance, playMode.value);
        return cmd;
    }

    /**
     * Start a candle animation. Starting this animation when another animation is started (or canceling), will cancel
     * the current one to start this one. This animation will make the drone flies horizontally in direction of the
     * target then flies up.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. Not used when vertical distance of provided_params param
     * is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartCandle(int providedParamsBitField, float speed, float verticalDistance, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartCandle(cmd.getNativePtr(), providedParamsBitField, speed, verticalDistance, playMode.value);
        return cmd;
    }

    /**
     * Start a dolly slide animation. Starting this animation when another animation is started (or canceling), will
     * cancel the current one to start this one. This animation will make the drone slides horizontally.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param angle: Desired drone-target-destination angle in rad. Not used when angle of provided_params param is 0.
     * @param horizontalDistance: Desired horizontal distance in m.. Not used when angle of provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartDollySlide(int providedParamsBitField, float speed, float angle, float horizontalDistance, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartDollySlide(cmd.getNativePtr(), providedParamsBitField, speed, angle, horizontalDistance, playMode.value);
        return cmd;
    }

    /**
     * Start a vertigo animation. Starting this animation when another animation is started (or canceling) will cancel
     * the current one to start this one. This animation will make the drone slides horizontally.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param duration: Desired duration in seconds. Not used when duration of provided_params param is 0.
     * @param maxZoomLevel: Desired maximum zoom level. Not used when max_zoom_level of provided_params param is 0.
     * @param finishAction: Desired action to execute at the end of the animation. Not used when finish_action of
     * provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartVertigo(int providedParamsBitField, float duration, float maxZoomLevel, @NonNull VertigoFinishAction finishAction, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartVertigo(cmd.getNativePtr(), providedParamsBitField, duration, maxZoomLevel, finishAction.value, playMode.value);
        return cmd;
    }

    /**
     * Starts a twist-up animation. Starting this animation when another animation is started (or canceling), will
     * cancel the current one to start this one. This animation will make the drone move up and rotate slowly on itself
     * until the end of the animation, first with the camera looking down and when it reaches its target altitude,
     * slowly looking up to the horizon.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. Not used when vertical distance of provided_params param
     * is 0.
     * @param rotationAngle: Desired rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
     * clockwise. Not used when rotation angle of provided_params param is 0.
     * @param rotationSpeed: The desired rotation speed of the anim in rad/s Not used when rotation speed of
     * provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartTwistUp(int providedParamsBitField, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartTwistUp(cmd.getNativePtr(), providedParamsBitField, speed, verticalDistance, rotationAngle, rotationSpeed, playMode.value);
        return cmd;
    }

    /**
     * Starts a positionned twist-up animation. Starting this animation when another animation is started (or
     * canceling), will cancel the current one to start this one. This animation needs a target. This animation will
     * make the drone move above the target then up and rotate slowly on itself until the end of the animation.
     * 
     * @param providedParamsBitField: Bitfield of the config parameters on which given values should be used. Setting a
     * bit to 1 means that the corresponding parameter should be used, otherwise default value should be used.
     * @param speed: Desired speed in m/s. Not used when speed of provided_params param is 0.
     * @param verticalDistance: Desired vertical distance in m. Not used when vertical distance of provided_params param
     * is 0.
     * @param rotationAngle: Desired rotation angle in rad. Positive value makes a clockwise panorama, negative is anti-
     * clockwise. Not used when rotation angle of provided_params param is 0.
     * @param rotationSpeed: The desired rotation speed of the anim in rad/s Not used when rotation speed of
     * provided_params param is 0.
     * @param playMode: Desired play mode. Not used when play mode of provided_params param is 0.
     */
    public static ArsdkCommand encodeStartPositionTwistUp(int providedParamsBitField, float speed, float verticalDistance, float rotationAngle, float rotationSpeed, @NonNull PlayMode playMode) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartPositionTwistUp(cmd.getNativePtr(), providedParamsBitField, speed, verticalDistance, rotationAngle, rotationSpeed, playMode.value);
        return cmd;
    }

    /**
     * Starts an horizontal 180 degrees photo panorama animation. Starting this animation when another animation is
     * started (or canceling) will cancel the current one to start this one. This animation will make the drone perform
     * a 180 degrees rotation on the yaw axis while take photos at various angles
     */
    public static ArsdkCommand encodeStartHorizontal180PhotoPanorama() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartHorizontal180PhotoPanorama(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Starts a vertical 180 degrees photo panorama animation. Starting this animation when another animation is started
     * (or canceling) will cancel the current one to start this one. This animation will make the the drone camera
     * perform a 180 degrees rotation on the tilt axis while taking photos at various angles.
     */
    public static ArsdkCommand encodeStartVertical180PhotoPanorama() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartVertical180PhotoPanorama(cmd.getNativePtr());
        return cmd;
    }

    /**
     * Starts a spherical photo panorama animation. Starting this animation when another animation is started (or
     * canceling) will cancel the current one to start this one. This animation will make the drone perform a 360
     * degrees rotation on the yaw axis. At various angles, rotation pauses, drone camera performs a 180 degrees
     * rotation on the tilt axis while taking photos at various angles, then drone yaw rotation resumes.
     */
    public static ArsdkCommand encodeStartSphericalPhotoPanorama() {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeStartSphericalPhotoPanorama(cmd.getNativePtr());
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeCancel(long nativeCmd);
    private static native int nativeEncodeStartFlip(long nativeCmd, int type);
    private static native int nativeEncodeStartHorizontalPanorama(long nativeCmd, int provided_params, float rotation_angle, float rotation_speed);
    private static native int nativeEncodeStartDronie(long nativeCmd, int provided_params, float speed, float distance, int play_mode);
    private static native int nativeEncodeStartHorizontalReveal(long nativeCmd, int provided_params, float speed, float distance, int play_mode);
    private static native int nativeEncodeStartVerticalReveal(long nativeCmd, int provided_params, float speed, float vertical_distance, float rotation_angle, float rotation_speed, int play_mode);
    private static native int nativeEncodeStartSpiral(long nativeCmd, int provided_params, float speed, float radius_variation, float vertical_distance, float revolution_nb, int play_mode);
    private static native int nativeEncodeStartParabola(long nativeCmd, int provided_params, float speed, float vertical_distance, int play_mode);
    private static native int nativeEncodeStartCandle(long nativeCmd, int provided_params, float speed, float vertical_distance, int play_mode);
    private static native int nativeEncodeStartDollySlide(long nativeCmd, int provided_params, float speed, float angle, float horizontal_distance, int play_mode);
    private static native int nativeEncodeStartVertigo(long nativeCmd, int provided_params, float duration, float max_zoom_level, int finish_action, int play_mode);
    private static native int nativeEncodeStartTwistUp(long nativeCmd, int provided_params, float speed, float vertical_distance, float rotation_angle, float rotation_speed, int play_mode);
    private static native int nativeEncodeStartPositionTwistUp(long nativeCmd, int provided_params, float speed, float vertical_distance, float rotation_angle, float rotation_speed, int play_mode);
    private static native int nativeEncodeStartHorizontal180PhotoPanorama(long nativeCmd);
    private static native int nativeEncodeStartVertical180PhotoPanorama(long nativeCmd);
    private static native int nativeEncodeStartSphericalPhotoPanorama(long nativeCmd);
}
