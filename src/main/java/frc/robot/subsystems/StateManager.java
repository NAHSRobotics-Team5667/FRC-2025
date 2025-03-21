package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.States.*;
import frc.robot.Constants;

public class StateManager extends SubsystemBase {
    //Elevator
    private ElevatorStates elevatorState;
    private ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();

    //Elevator
    private EndEffectorStates endEffectorState;
    private EndEffectorSubsystem endEffector = EndEffectorSubsystem.getInstance();

    //Indexer
    private IndexerStates indexerState;
    private IndexerSubsystem indexer = IndexerSubsystem.getInstance();

    //Intake
    private IntakeStates intakeState;
    private IntakeSubsystem intake = IntakeSubsystem.getInstance();

    //Robot State
    private RobotStates desiredRobotState;

    //Climber
    private ClimberStates climberState;
    private ClimberSubsystem climber = ClimberSubsystem.getInstance();

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

    public void updateElevatorStates() {
        if (elevator.isMoving()) {
            elevatorState = ElevatorStates.MOVING;
        } else if (elevator.getPosition() == Constants.ElevatorConstants.LEVEL_1) {
            elevatorState = ElevatorStates.LEVEL_1;
        } else if (elevator.getPosition() == Constants.ElevatorConstants.LEVEL_2) {
            elevatorState = ElevatorStates.LEVEL_2;
        } else if (elevator.getPosition() == Constants.ElevatorConstants.LEVEL_3) {
            elevatorState = ElevatorStates.LEVEL_3;
        } else if (elevator.getPosition() == Constants.ElevatorConstants.LEVEL_4) {
            elevatorState = ElevatorStates.LEVEL_4;
        } else {
            elevatorState = ElevatorStates.BASE;
        }
    }

    public void updateEndEffectorStates() {
        if (endEffector.getWheelSpeed() > 0 || endEffector.getWristSpeed() > 0) {
            endEffectorState = EndEffectorStates.IS_MOVING;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.REEF_ANGLE) {  
            endEffectorState = EndEffectorStates.AT_REEF;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.PROCESSOR_ANGLE) {
            endEffectorState = EndEffectorStates.AT_PROCESSOR;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.BARGE_ANGLE) {
            endEffectorState = EndEffectorStates.AT_BARGE;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.INTAKE_ANGLE) {
            endEffectorState = EndEffectorStates.AT_INTAKE;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.HOLD_CORAL) {
            endEffectorState = EndEffectorStates.HOLDING_CORAL;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.HOLD_ALGAE) {
            endEffectorState = EndEffectorStates.HOLDING_ALGAE;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.READY_FOR_CORAL) {
            endEffectorState = EndEffectorStates.READY_FOR_CORAL;
        } else if (endEffector.getWristPosition() == Constants.EndEffectorConstants.READY_FOR_ALGAE) {
            endEffectorState = EndEffectorStates.READY_FOR_ALGAE;
        } else {

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
        if (intake.getWheelSpeed() > 0) {
            intakeState = IntakeStates.INTAKE_ON;
        } else {
            intakeState = IntakeStates.INTAKE_OFF;
        }
    }

    public void updateClimberStates() {
        if (climber.getSpeed() > 0) {
            climberState = ClimberStates.CLIMBING;
        } else if (climber.getSpeed() == 0 && climber.getAngle() == Constants.ClimberConstants.ANGLE) {
            climberState = ClimberStates.HANGING;
        } else {
            climberState = ClimberStates.CLIMBER_OFF;
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

    /**
     * @return climber state
     */
    public ClimberStates getClimberStates() {
        return climberState;
    }

    // ========================================================
    // ======================= LEDS ===========================

    private void updateLights() {

    }

    // ========================================================
    // ===================== PERIODIC =========================

    @Override
    public void periodic() {
        updateElevatorStates();
        updateIndexerStates();
        updateIntakeStates();
        updateClimberStates();
        updateLights();
    }

}

