package frc.robot;

/**
 * CAN IDs IN ORDER:
 * 
 *  10 - Front Left Steer
 *  11 - Front Left Drive
 *  12 - Front Right Steer
 *  13 - Front Right Drive
 *  14 - Back Right Steer
 *  15 - Back Right Drive
 *  16 - Back Left Steer
 *  17 - Back Left Drive
 * 
 *  20 - Intake Roller Motor
 *  21 - Indexer Motor
 *  22 - Elevator Left Drive
 *  23 - Elevator Right Drive
 *  24 - EndEffector Wrist Motor
 *  25 - EndEffector Wheel Motor
 *  26 - Intake Pivot Motor
 *  
 *  30 - Front Left CANCoder
 *  31 - Front Right CANCoder
 *  32 - Back Right CANCoder
 *  33 - Back Left CANCoder
 * 
 *  50 - Pigeon 2.0 IMU
 */

/**
 * LEVELS:
 * 
 * 1 - Level 1 Coral
 * 2 - Level 2 Coral
 * 3 - Level 3 Coral
 * 
 */


 public class Constants {
    public class OperatorConstants {
        public static final double LEFT_Y_DEADBAND = 0.1;
        public static final double RIGHT_Y_DEADBAND = 0.1;
        public static final double LEFT_X_DEADBAND = 0.1;
        public static final double RIGHT_X_DEADBAND = 0.1;
        public static final double GENERAL_DEADBAND = 0.1;
    }

    public static class ElevatorConstants {
        //==============================================================================
        //=============================== MOTOR IDS ====================================
        public static final int LEFT_MOTOR_ID = 22;
        public static final int RIGHT_MOTOR_ID = 23;
        //==============================================================================
        //======================== MOTION MAGIC VALUES =================================
        public static final double MAX_VELOCITY = 0.35;
        public static final double MAX_ACCELERATION = 0.15;
        public static final double MAX_JERK = 0.05;
        //==============================================================================
        //=============================== GEAR VALUES ==================================
        public static final double WHEEL_RADIUS = 0.827;
        public static final double GEAR_RATIO = 12;
        //==============================================================================
        //=============================== HEIGHT VALUES ================================
        public static final double HEIGHT_OFFSET = 0;
        public static final double BASE = 0;
        public static final double LEVEL_1 = 0.46;
        public static final double LEVEL_2 = 0.81;
        public static final double LEVEL_3 = 1.21;
        public static final double LEVEL_4 = 1.83;

    }

    public static class EndEffectorConstants {
        //==============================================================================
        //=============================== MOTOR IDS ====================================
        public static final int WRIST_ID = 24;
        public static final int WHEELS_ID = 25;
        //==============================================================================
        //========================== MOTION MAGIC VALUES ===============================
        public static final double MAX_VELOCITY = 0.2;
        public static final double MAX_ACCELERATION = 0.06;
        public static final double MAX_JERK = 0.01;
        //==============================================================================
        //======================= WRIST ANGLES (IN ROTATIONS) ==========================
        public static final double INTAKE_ANGLE = 0; //0 Degrees
        public static final double REEF_ANGLE = 0.34722222222; //125 Degrees
        public static final double PROCESSOR_ANGLE = 0.5; //180 Degrees
        public static final double BARGE_ANGLE = 0.20833333333; //75 Degrees

        //==============================================================================
        //=============================== WHEEL SPEEDS =================================
        public static final double WHEEL_SPEED = 1;

    }

    public static class IndexerConstants {
        //==============================================================================
        //=============================== MOTOR IDS ====================================
        public static final int INDEXER_ID = 21;
        //==============================================================================
        //=============================== BEAM BREAK ===================================
        public static final int BEAM_BREAK_PORT_ID = 9;
        //==============================================================================
        //=============================== INDEXER VALUES ===============================
        public static final double INDEXER_SPEED = 0;
        public static final double INDEXER_POSITION = 0;
    }

    public static class IntakeConstants {
        //==============================================================================
        //=============================== MOTOR IDS ====================================
        public static final int ROLLER_MOTOR_ID = 20;
        public static final int PIVOT_MOTOR_ID = 26;
        //==============================================================================
        //=============================== INTAKE VALUES ================================
        public static final double ROLLER_SPEED = 0;
        public static final double PIVOT_SPEED = 0;
    }
}