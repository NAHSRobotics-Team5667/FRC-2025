// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
      public static class OperatorConstants {
        // =======================================================
        // ==================== DRIVER ===========================
        public static final double LEFT_X_DEADBAND = 0.1;
        public static final double LEFT_Y_DEADBAND = 0.1;
        public static final double RIGHT_X_DEADBAND = 0.1;
        public static final double RIGHT_Y_DEADBAND = 0.1;
    }
    public static class DriveConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        //DRIVE MOTOR IDS
        public static final int UPPER_LEFT_DRIVE_ID = 1;
        public static final int UPPER_RIGHT_DRIVE_ID = 2;
        public static final int BOTTOM_LEFT_DRIVE_ID = 3;
        public static final int BOTTOM_RIGHT_DRIVE_ID = 4;

        //STEER MOTORS  IDS
        public static final int UPPER_LEFT_STEER_ID = 5;
        public static final int UPPER_RIGHT_STEER_ID = 6;
        public static final int BOTTOM_LEFT_STEER_ID = 7;
        public static final int BOTTOM_RIGHT_STEER_ID = 8;

        // =======================================================
        // ====================== PID ============================

        public static final double AUTO_DRIVE_P = 0;
        public static final double AUTO_DRIVE_I = 0;
        public static final double AUTO_DRIVE_D = 0;
        public static final double AUTO_DRIVE_F = 0;

        public static final double ALIGN_P = 0;
        public static final double ALIGN_I = 0;
        public static final double ALIGN_D = 0;

        public static final double INTAKE_P = 0;
        public static final double INTAKE_I = 0;
        public static final double INTAKE_D = 0;

        // =======================================================
        // ======================= GENERAL =======================

        public static final double MAX_VELOCITY_FEET = 0;
        public static final double MAX_VELOCITY_METERS = 0;

        public static final double MAX_ACCELERATION_METERS = 0;
    }
    public static class ElevatorConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        public static final int LEFT_MOTOR_ID = 0;
        public static final int RIGHT_MOTOR_ID = 0;

        // =========================================================================
        // ================= MOTION MAGIC CONFIGURATIONS ===========================
        public static final double VELOCITY = 0;
        public static final double ACCELERATION = 0;
        public static final double JERK = 0;
        // =========================================================================
        // ================= CORAL LEVEL HEIGHTS ===================================
        public static final double BASE = 0;
        public static final double LEVEL_1 = 0.46;
        public static final double LEVEL_2 = 0.81;
        public static final double LEVEL_3 = 1.21;
        public static final double LEVEL_4 = 1.83;
        // =========================================================================
        // ============================ MISC =======================================
        public static final double GEAR_RATIO = 12;
        public static final double WHEEL_RADIUS = 0.827;
    }

    public static class IntakeConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        public static final int INTAKE_ID = 0;
        public static final int INTAKE_SPEED = 0;
    }

    public static class IndexerConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        public static final int INDEX_ID_A = 0;
        public static final int INDEX_ID_B = 0;
        public static final int INTAKE_SPEED = 0;
    }

    public static class ClimberConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        public static final int CLIMBER_ID = 0;
        public static final int GEAR_RATIO = 125;

        // =======================================================
        // ================= PID VALUES ==========================
        public static final double CLIMBER_kP = 0;
        public static final double CLIMBER_kI = 0;
        public static final double CLIMBER_kD = 0;

        // =======================================================
        // ============== MOTION VALUES ==========================
        public static final double CLIMBER_MAX_VELOCITY = 0;
        public static final double CLIMBER_MAX_ACCELERATION = 0;
        
    }

    public static class EndEffectorConstants {
        // =======================================================
        // ================= MOTOR IDS ===========================
        public static final int CLAMP_ID = 0;
        public static final int WRIST_ID = 0;

        // =======================================================
        // ================= ANGULAR POSITIONS ===================
        public static final double PROCESSOR_ANGLE = 0;
        public static final double BARGE_ANGLE = 0;
        public static final double REEF_ANGLE = 0;
        public static final double INTAKE_ANGLE = 0;
        public static final double HOLD_CORAL = 0;
        public static final double HOLD_ALGAE = 0;
        public static final double READY_FOR_CORAL = 0;
        public static final double READY_FOR_ALGAE = 0;
        
        public static final double WRIST_SPEED = 0;
        public static final double CLAMP_SPEED = 0;
    }

    public static class LimelightConstants {
        public static final double kCamHeight = 0; // Height of the limelight from the ground
        public static final double kCamAngle = 0; // Pitch angle of direction the limelight is pointed in
    }



}
