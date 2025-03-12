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

    public static enum EndEffectorStates {
        /*  State for when End Effector is holding coral */
        HOLDING_CORAL,
        /*  State for when End Effector is holding algae */
        HOLDING_ALGAE,
         /*  State for when End Effector is ready to grab coral */
        READY_FOR_CORAL,
         /*  State for when End Effector is ready to grab algae */
        READY_FOR_ALGAE,
         /*  State for when End Effector is angled at the reef */
        AT_REEF,
        /*  State for when End Effector is angled at processor */
        AT_PROCESSOR,
        /*  State for when End Effector is angled at barge */
        AT_BARGE,
        /*  State for when End Effector is angled towards the indexer and intake to feeda part */
        AT_INTAKE
    }
}
