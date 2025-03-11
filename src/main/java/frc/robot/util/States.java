package frc.robot.util;

public class States {
    public static enum ElevatorStates {
        /* State for when Elevator is at its lowest point */
        BASE,
        /* State for when Elevator is at Level 1 */
        LEVEL_1,
        /*  State for when Elevator is at Level 2 */
        LEVEL_2,
        /*  State for when Elevator is at Level 3 */
        LEVEL_3,
        /*  State for when Elevator is at Level 4 */
        LEVEL_4,
        /*  State for when Elevator is Moving */
        MOVING
    }

    public static enum RobotStates {
        IDLE,
        REEF,
        CAGE,
        PROCESSOSR,
        CORAL_STATION,
        HANGING
    }
}
