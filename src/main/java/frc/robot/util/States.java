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
        PROCESSOR,
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
        /*  State for when End Effector is angled towards the indexer and intake to retrieve part */
        AT_INTAKE,
        /*  State for when End Effector is moving */
        IS_MOVING
    }

    public static enum IndexerStates {
        /* State for when the index is turned off */
        INDEXER_OFF,
        /* State for when the index is turned on */
        INDEXER_ON
    }

    public static enum IntakeStates {
        /* State for when the index is turned off */
        INTAKE_ON,
        /* State for when the index is turned on */
        INTAKE_OFF
    }
    
    public static enum ClimberStates {
        /* State for when the climber is running */
        CLIMBER_ON,
        /* State for when the climber is not running */
        CLIMBER_OFF
    }
}
