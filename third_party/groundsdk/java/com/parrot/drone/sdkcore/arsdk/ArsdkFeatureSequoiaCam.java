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
 * Sequoia_cam feature command/event interface.
 */
public class ArsdkFeatureSequoiaCam {

    /**
     * Radiometric calibration status.
     */
    public enum RadiometricCalibStatusValue {

        /**
         * Radiometric calibration is available.
         */
        AVAILABLE(0),

        /**
         * Radiometric calibration is not available.
         */
        UNAVAILABLE(1),

        /**
         * Radiometric calibration In progress.
         */
        IN_PROGRESS(2);

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
        public static RadiometricCalibStatusValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private RadiometricCalibStatusValue(int value) {
            this.value = value;
        }

        private static final SparseArray<RadiometricCalibStatusValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (RadiometricCalibStatusValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Last radiometric calibration result.
     */
    public enum RadiometricCalibResultValue {

        /**
         * Radiometric calibration is successfull.
         */
        SUCCESS(0),

        /**
         * Radiometric calibration failed.
         */
        ERROR(1);

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
        public static RadiometricCalibResultValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private RadiometricCalibResultValue(int value) {
            this.value = value;
        }

        private static final SparseArray<RadiometricCalibResultValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (RadiometricCalibResultValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Last session state.
     */
    public enum SessionStatusValue {

        /**
         * A session is currently opened.
         */
        OPENED(0),

        /**
         * No session is currently opened.
         */
        CLOSED(1);

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
        public static SessionStatusValue fromValue(int value) {
            return MAP.get(value, null);
        }

        private SessionStatusValue(int value) {
            this.value = value;
        }

        private static final SparseArray<SessionStatusValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (SessionStatusValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Sensor to use.
     */
    public enum SensorValue {

        /**
         * near infra red.
         */
        NEAR_INFRA_RED(0),

        /**
         * green.
         */
        GREEN(1),

        /**
         * red.
         */
        RED(2),

        /**
         * red edge.
         */
        RED_EDGE(3);

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
        public static SensorValue fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SensorValue> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 4) {
                    ULog.e(TAG, "Unsupported SensorValue bitfield value " + value);
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
        public static EnumSet<SensorValue> fromBitfield(int bitfield) {
            EnumSet<SensorValue> enums = EnumSet.noneOf(SensorValue.class);
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
        public static int toBitField(@NonNull SensorValue... enums) {
            int bitField = 0;
            for (SensorValue e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SensorValue(int value) {
            this.value = value;
        }

        private static final SparseArray<SensorValue> MAP;

        static {
            MAP = new SparseArray<>();
            for (SensorValue e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Storage selection.
     */
    public enum StorageInfosSelected {

        /**
         * sd is selected.
         */
        SD(0),

        /**
         * internal is selected.
         */
        INTERNAL(1);

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
        public static StorageInfosSelected fromValue(int value) {
            return MAP.get(value, null);
        }

        private StorageInfosSelected(int value) {
            this.value = value;
        }

        private static final SparseArray<StorageInfosSelected> MAP;

        static {
            MAP = new SparseArray<>();
            for (StorageInfosSelected e: values())
                MAP.put(e.value, e);
        }
    }

    /**
     * Errors bits.
     */
    public enum SystemError {

        /**
         * UPDATE ERR.
         */
        UPDATE_ERR(0),

        /**
         * TEMP ERR.
         */
        TEMP_ERR(1),

        /**
         * NO SUNSHINE ERR.
         */
        NO_SUNSHINE_ERR(2);

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
        public static SystemError fromValue(int value) {
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
        public static void each(int bitfield, @NonNull Consumer<SystemError> func) {
            while (bitfield != 0) {
                int value = Integer.numberOfTrailingZeros(bitfield);
                if (value >= 3) {
                    ULog.e(TAG, "Unsupported SystemError bitfield value " + value);
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
        public static EnumSet<SystemError> fromBitfield(int bitfield) {
            EnumSet<SystemError> enums = EnumSet.noneOf(SystemError.class);
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
        public static int toBitField(@NonNull SystemError... enums) {
            int bitField = 0;
            for (SystemError e : enums)
                 bitField |= 1 << e.value;
            return bitField;
        }

        private SystemError(int value) {
            this.value = value;
        }

        private static final SparseArray<SystemError> MAP;

        static {
            MAP = new SparseArray<>();
            for (SystemError e: values())
                MAP.put(e.value, e);
        }
    }


    /** Feature uid. */
    public static final int UID = 0x9300;

    /** Uid of radiometric_calib_status event. */;
    public static final int RADIOMETRIC_CALIB_STATUS_UID = 0x0001;

    /** Uid of radiometric_calib_result event. */;
    public static final int RADIOMETRIC_CALIB_RESULT_UID = 0x0002;

    /** Uid of session_state_changed event. */;
    public static final int SESSION_STATE_CHANGED_UID = 0x0006;

    /** Uid of live_stitch_mask_changed event. */;
    public static final int LIVE_STITCH_MASK_CHANGED_UID = 0x0008;

    /** Uid of live_stitch_last_picture_id_changed event. */;
    public static final int LIVE_STITCH_LAST_PICTURE_ID_CHANGED_UID = 0x0009;

    /** Uid of storage_infos event. */;
    public static final int STORAGE_INFOS_UID = 0x000A;

    /** Uid of system_status event. */;
    public static final int SYSTEM_STATUS_UID = 0x000B;

    /** Uid of live_stitch_pictures_in_progress_number_changed event. */;
    public static final int LIVE_STITCH_PICTURES_IN_PROGRESS_NUMBER_CHANGED_UID = 0x000E;

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
         * Is radiometric calibration available ?
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param value:
         * @param listFlagsBitField:
         */
        public default void onRadiometricCalibStatus(int camId, @Nullable RadiometricCalibStatusValue value, int listFlagsBitField) {}

        /**
         * Last radiometric calibration result.
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param value:
         * @param listFlagsBitField:
         */
        public default void onRadiometricCalibResult(int camId, @Nullable RadiometricCalibResultValue value, int listFlagsBitField) {}

        /**
         * Session state changed
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param state:
         * @param sessionPath:
         * @param liveStitchPath:
         * @param listFlagsBitField:
         */
        public default void onSessionStateChanged(int camId, @Nullable SessionStatusValue state, String sessionPath, String liveStitchPath, int listFlagsBitField) {}

        /**
         * Current live stitch mask value.
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param maskBitField:
         * @param listFlagsBitField:
         */
        public default void onLiveStitchMaskChanged(int camId, int maskBitField, int listFlagsBitField) {}

        /**
         * Last picture id available for live stitch.
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param pictureId:
         * @param listFlagsBitField:
         */
        public default void onLiveStitchLastPictureIdChanged(int camId, long pictureId, int listFlagsBitField) {}

        /**
         * Storage infos
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param selected:
         * @param internalAvailable: Boolean. 1 if ro, 0 if rw
         * @param internalTotalSize: Total size in bytes of internal
         * @param internalFreeSize: Free bytes of the internal
         * @param internalIsRo: Boolean. 1 if ro, 0 if rw
         * @param internalIsCorrupted: Boolean. 1 if corrupted, 0 otherwise
         * @param sdAvailable: Boolean. 1 if ro, 0 if rw
         * @param sdTotalSize: Total size in bytes of the sd
         * @param sdFreeSize: Free bytes of the sd
         * @param sdIsRo: Boolean. 1 if ro, 0 if rw
         * @param sdIsCorrupted: Boolean. 1 if corrupted, 0 otherwise
         * @param listFlagsBitField:
         */
        public default void onStorageInfos(int camId, @Nullable StorageInfosSelected selected, int internalAvailable, long internalTotalSize, long internalFreeSize, int internalIsRo, int internalIsCorrupted, int sdAvailable, long sdTotalSize, long sdFreeSize, int sdIsRo, int sdIsCorrupted, int listFlagsBitField) {}

        /**
         * System status, 0x00 for all ok
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param errorsBitField: System errors bitfield. 0 is no error.
         * @param listFlagsBitField:
         */
        public default void onSystemStatus(int camId, int errorsBitField, int listFlagsBitField) {}

        /**
         * Number of pictures that are currently processed.
         * 
         * @param camId: Camera id, as given in the [connected accessories](#1-33-0) event.
         * @param nbPictures:
         * @param listFlagsBitField:
         */
        public default void onLiveStitchPicturesInProgressNumberChanged(int camId, long nbPictures, int listFlagsBitField) {}
    }

    private static void radiometricCalibStatus(Callback cb, int camId, int value, int listFlagsBitField) {
        ArsdkFeatureSequoiaCam.RadiometricCalibStatusValue enumValue = ArsdkFeatureSequoiaCam.RadiometricCalibStatusValue.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureSequoiaCam.RadiometricCalibStatusValue value " + value);
        try {
            cb.onRadiometricCalibStatus(camId, enumValue, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.radiometric_calib_status [cam_id: " + camId + ", value: " + value + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void radiometricCalibResult(Callback cb, int camId, int value, int listFlagsBitField) {
        ArsdkFeatureSequoiaCam.RadiometricCalibResultValue enumValue = ArsdkFeatureSequoiaCam.RadiometricCalibResultValue.fromValue(value);
        if (enumValue == null) ULog.e(TAG, "Unsupported ArsdkFeatureSequoiaCam.RadiometricCalibResultValue value " + value);
        try {
            cb.onRadiometricCalibResult(camId, enumValue, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.radiometric_calib_result [cam_id: " + camId + ", value: " + value + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void sessionStateChanged(Callback cb, int camId, int state, String sessionPath, String liveStitchPath, int listFlagsBitField) {
        ArsdkFeatureSequoiaCam.SessionStatusValue enumState = ArsdkFeatureSequoiaCam.SessionStatusValue.fromValue(state);
        if (enumState == null) ULog.e(TAG, "Unsupported ArsdkFeatureSequoiaCam.SessionStatusValue value " + state);
        try {
            cb.onSessionStateChanged(camId, enumState, sessionPath, liveStitchPath, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.session_state_changed [cam_id: " + camId + ", state: " + state + ", session_path: " + sessionPath + ", live_stitch_path: " + liveStitchPath + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void liveStitchMaskChanged(Callback cb, int camId, int maskBitField, int listFlagsBitField) {
        try {
            cb.onLiveStitchMaskChanged(camId, maskBitField, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.live_stitch_mask_changed [cam_id: " + camId + ", mask: " + maskBitField + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void liveStitchLastPictureIdChanged(Callback cb, int camId, long pictureId, int listFlagsBitField) {
        try {
            cb.onLiveStitchLastPictureIdChanged(camId, pictureId, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.live_stitch_last_picture_id_changed [cam_id: " + camId + ", picture_id: " + pictureId + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void storageInfos(Callback cb, int camId, int selected, int internalAvailable, long internalTotalSize, long internalFreeSize, int internalIsRo, int internalIsCorrupted, int sdAvailable, long sdTotalSize, long sdFreeSize, int sdIsRo, int sdIsCorrupted, int listFlagsBitField) {
        ArsdkFeatureSequoiaCam.StorageInfosSelected enumSelected = ArsdkFeatureSequoiaCam.StorageInfosSelected.fromValue(selected);
        if (enumSelected == null) ULog.e(TAG, "Unsupported ArsdkFeatureSequoiaCam.StorageInfosSelected value " + selected);
        try {
            cb.onStorageInfos(camId, enumSelected, internalAvailable, internalTotalSize, internalFreeSize, internalIsRo, internalIsCorrupted, sdAvailable, sdTotalSize, sdFreeSize, sdIsRo, sdIsCorrupted, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.storage_infos [cam_id: " + camId + ", selected: " + selected + ", internal_available: " + internalAvailable + ", internal_total_size: " + internalTotalSize + ", internal_free_size: " + internalFreeSize + ", internal_is_ro: " + internalIsRo + ", internal_is_corrupted: " + internalIsCorrupted + ", sd_available: " + sdAvailable + ", sd_total_size: " + sdTotalSize + ", sd_free_size: " + sdFreeSize + ", sd_is_ro: " + sdIsRo + ", sd_is_corrupted: " + sdIsCorrupted + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void systemStatus(Callback cb, int camId, int errorsBitField, int listFlagsBitField) {
        try {
            cb.onSystemStatus(camId, errorsBitField, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.system_status [cam_id: " + camId + ", errors: " + errorsBitField + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }

    private static void liveStitchPicturesInProgressNumberChanged(Callback cb, int camId, long nbPictures, int listFlagsBitField) {
        try {
            cb.onLiveStitchPicturesInProgressNumberChanged(camId, nbPictures, listFlagsBitField);
        } catch (ArsdkCommand.RejectedEventException e) {
            ULog.e(TAG, "Rejected event: sequoia_cam.live_stitch_pictures_in_progress_number_changed [cam_id: " + camId + ", nb_pictures: " + nbPictures + ", list_flags: " + listFlagsBitField + "]", e);
        }
    }
    
    /**
     * Launch capture on radiometric calibration mode. This PTP mode is 0x8004
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeRadiometricCalibStart(int id) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeRadiometricCalibStart(cmd.getNativePtr(), id);
        return cmd;
    }

    /**
     * Changes sequoia path for future media
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     * @param name: Name of the session, only letters, numbers, '-' and '_' are allowed.
     */
    public static ArsdkCommand encodeOpenSession(int id, String name) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeOpenSession(cmd.getNativePtr(), id, name);
        return cmd;
    }

    /**
     * Close current mission session.
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeCloseSession(int id) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeCloseSession(cmd.getNativePtr(), id);
        return cmd;
    }

    /**
     * Configure live stitch mode
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     * @param sensorsMaskBitField: Sensors to use for live stitch
     */
    public static ArsdkCommand encodeSetLiveStitchMask(int id, int sensorsMaskBitField) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeSetLiveStitchMask(cmd.getNativePtr(), id, sensorsMaskBitField);
        return cmd;
    }

    /**
     * Ask to send back a storage_event
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeGetStorageInfos(int id) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeGetStorageInfos(cmd.getNativePtr(), id);
        return cmd;
    }

    /**
     * This command will request a factory reset from the Sequoia. *The factory reset procedure implies an automatic
     * reboot the sequoia*, which will be done immediately after recieving this command.
     * 
     * @param id: Camera id, as given in the [connected accessories](#1-33-0) event.
     */
    public static ArsdkCommand encodeFactoryReset(int id) {
        ArsdkCommand cmd = ArsdkCommand.Pool.DEFAULT.obtain();
        nativeEncodeFactoryReset(cmd.getNativePtr(), id);
        return cmd;
    }

    private static native int nativeDecode(long nativeCmd, Callback callback);
    private static native void nativeClassInit();

    static { nativeClassInit(); }
    private static native int nativeEncodeRadiometricCalibStart(long nativeCmd, int id);
    private static native int nativeEncodeOpenSession(long nativeCmd, int id, String name);
    private static native int nativeEncodeCloseSession(long nativeCmd, int id);
    private static native int nativeEncodeSetLiveStitchMask(long nativeCmd, int id, int sensors_mask);
    private static native int nativeEncodeGetStorageInfos(long nativeCmd, int id);
    private static native int nativeEncodeFactoryReset(long nativeCmd, int id);
}
