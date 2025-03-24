package frc.robot.util;

public class States {
    public static enum RobotState {
        IDLE,
        PROCESSOR,
        REEF,
        STATION,
        CAGE,
        CLIMB,
        HANGING,
        INTAKE,
        OUTTAKE
    }

    public static enum ElevatorState {
        /* State for when the elevator is at Level 1 */
        LEVEL_1,
        /* State for when the elevator is at Level 2 */
        LEVEL_2,
        /* State for when the elevator is at Level 3 */
        LEVEL_3,
        /* State for when the elevator is at Level 4 */
        LEVEL_4,
        /* State for when the elevator is moving */
        MOVING
    }

    public static enum EndEffectorWristState {
        /* State for when the End Effector is angled towards the intake */
        INTAKE,
        /* State for when the End Effector is angled towards the reef */
        REEF,
        /* State for when the End Effector is angled towards the processor */
        PROCESSOR,
        /* State for when the End Effector is angled in such a way that it can launch algae into the barge */
        BARGE
    }

    public static enum EndEffectorWheelState {
        /* State for when the wheels are intaking */
        INTAKING,
        /* State for when the wheels are idle */
        IDLE,
        /* State for when the wheels are outtaking */
        OUTTAKING
    }
}