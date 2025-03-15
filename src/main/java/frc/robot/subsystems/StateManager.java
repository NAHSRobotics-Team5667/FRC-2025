package frc.robot.subsystems;

import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.States.*;

public class StateManager extends SubsystemBase {
    //Elevator
    private ElevatorStates elevatorState;

    //Elevator
    private EndEffectorStates endEffectorState;

    //Indexer
    private IndexerStates indexerState;
    private IndexerSubsystem indexer = IndexerSubsystem.getInstance();

    //Intake
    private IntakeStates intakeState;
    private IntakeSubsystem intake = IntakeSubsystem.getInstance();

    //Robot State
    private RobotStates desiredRobotState;

    //Singleton
    private static StateManager instance = null;

    // ========================================================
    // =================== CONSTRUCTOR ========================

    /** Creates a new StateSubsystem. */
     private StateManager() {
        // Robot ----------------------------------------------
        desiredRobotState = RobotStates.IDLE;

        // Elevator -------------------------------------------
        elevatorState = ElevatorStates.LEVEL_1;

        // End Effector ---------------------------------------
        endEffectorState = EndEffectorStates.AT_INTAKE;

        // Indexer --------------------------------------------
        indexerState = IndexerStates.INDEXER_OFF;

        // Intake ---------------------------------------------
        intakeState = IntakeStates.INTAKE_OFF;
    }

    /**
    * @return singleton instance of the State subsystem.
    */
    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }

        return instance;
    }

    public void updateElevatorStates(ElevatorStates newState) {
        if (newState.equals(ElevatorStates.MOVING)) {
            elevatorState = ElevatorStates.MOVING;
        } else if (newState.equals(ElevatorStates.LEVEL_1)) {
            elevatorState = ElevatorStates.LEVEL_1;
        } else if (newState.equals(ElevatorStates.LEVEL_2)) {
            elevatorState = ElevatorStates.LEVEL_2;
        } else if (newState.equals(ElevatorStates.LEVEL_3)) {
            elevatorState = ElevatorStates.LEVEL_3;
        } else if (newState.equals(ElevatorStates.LEVEL_4)) {
            elevatorState = ElevatorStates.LEVEL_4;
        } else if (newState.equals(ElevatorStates.BASE)) {
            elevatorState = ElevatorStates.BASE;
        }
    }

    public void updateEndEffectorStates(EndEffectorStates newState) {
        if (newState.equals(EndEffectorStates.AT_REEF)) {
            endEffectorState = EndEffectorStates.AT_REEF;
        } else if (newState.equals(EndEffectorStates.AT_BARGE)) {
            endEffectorState = EndEffectorStates.AT_BARGE;
        } else if (newState.equals(EndEffectorStates.AT_PROCESSOR)) {
            endEffectorState = EndEffectorStates.AT_PROCESSOR;
        } else if (newState.equals(EndEffectorStates.AT_INTAKE)) {
            endEffectorState = EndEffectorStates.AT_INTAKE;
        } else if (newState.equals(EndEffectorStates.IS_MOVING)) {
            endEffectorState = EndEffectorStates.IS_MOVING;
        } 
    }

    public void updateIndexerStates() {
        if (indexer.getSpeed() > 0) {
            indexerState = IndexerStates.INDEXER_ON;
        } else {
            indexerState = IndexerStates.INDEXER_OFF;
        }
    }

    public void updateIntakeStates() {
        if (intake.getSpeed() > 0) {
            intakeState =IntakeStates.INTAKE_ON;
        } else {
            intakeState =IntakeStates.INTAKE_OFF;
        }
    }

    /**
     * @return elevator state.
     */
    public ElevatorStates getElevatorStates() {
        return elevatorState;
    }

    /**
     * @return end effector state.
     */
    public EndEffectorStates getEndEffectorStates() {
        return endEffectorState;
    }

    /**
     * @return indexer state.
     */
    public IndexerStates getIndexerStates() {
        return indexerState;
    }

    /**
     * @return indexer state.
     */
    public IntakeStates getIntakeStates() {
        return intakeState;
    }

    // ========================================================
    // ======================= LEDS ===========================

    private void updateLights() {
        if (DriverStation.isEnabled()) {

        }
    }

}

