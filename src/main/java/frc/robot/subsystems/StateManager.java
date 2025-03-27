package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.ElevatorState;
import frc.robot.util.States.WristState;
import frc.robot.util.States.WheelState;
import frc.robot.util.States.IntakeState;
import frc.robot.util.States.IndexerState;
import frc.robot.util.States.BeamBreakState;

public class StateManager extends SubsystemBase {
    //=========================================================================
    //======================== STATE MANAGERS =================================
    //Elevator
    private ElevatorState elevatorState;

    //End Effector
    private WristState wristState;
    private WheelState wheelState;

    //Intake
    private IntakeState intakeState;

    //Indexer
    private IndexerState indexerState;

    //Beam Break
    private BeamBreakState beamBreakState;

    //=========================================================================
    //===================== SINGLETON AND CONSTRUCTOR =========================
    private static StateManager instance = null;

    public static StateManager getInstance() {
        if (instance == null)
            instance = new StateManager();

        return instance;
    }

    private StateManager() {
        
        //Elevator ------------------------------
        elevatorState = ElevatorState.LEVEL_1;

        //End Effector ------------------------------
        wristState = WristState.AT_INTAKE;
        wheelState = WheelState.IDLE;

        //Intake ------------------------------
        intakeState = IntakeState.IDLE;

        //Indexer ------------------------------
        indexerState = IndexerState.DISABLED;

        //Beam Break ------------------------------
        beamBreakState = BeamBreakState.NOT_BROKEN;
    }
    
    //=========================================================================
    //=========================== STATE UPDATERS ==============================

    public void updateElevatorState() {
        if (ElevatorSubsystem.getInstance().isMoving()){
            elevatorState = ElevatorState.MOVING;
        } else if (ElevatorSubsystem.getInstance().getElevatorPosition() <= ElevatorConstants.LEVEL_1 + 0.1 && ElevatorSubsystem.getInstance().getElevatorPosition() >= ElevatorConstants.LEVEL_1 - 0.1) {
            elevatorState = ElevatorState.LEVEL_1;
        } else if (ElevatorSubsystem.getInstance().getElevatorPosition() <= ElevatorConstants.LEVEL_2 + 0.1 && ElevatorSubsystem.getInstance().getElevatorPosition() >= ElevatorConstants.LEVEL_2 - 0.1) {
            elevatorState = ElevatorState.LEVEL_2;
        } else if (ElevatorSubsystem.getInstance().getElevatorPosition() <= ElevatorConstants.LEVEL_3 + 0.1 && ElevatorSubsystem.getInstance().getElevatorPosition() >= ElevatorConstants.LEVEL_3 - 0.1) {
            elevatorState = ElevatorState.LEVEL_3;
        } else {
            elevatorState = ElevatorState.LEVEL_4;
        }
    }

    public void updateWristState() {
        if (EndEffectorSubsystem.getInstance().getWristPosition() <= EndEffectorConstants.BARGE_ANGLE + 0.1 && EndEffectorSubsystem.getInstance().getWristPosition() >= EndEffectorConstants.BARGE_ANGLE - 0.1) {
            wristState = WristState.AT_BARGE;
        } else if (EndEffectorSubsystem.getInstance().getWristPosition() <= EndEffectorConstants.REEF_ANGLE + 0.1 && EndEffectorSubsystem.getInstance().getWristPosition() >= EndEffectorConstants.REEF_ANGLE - 0.1) {
            wristState = WristState.AT_REEF;
        } if (EndEffectorSubsystem.getInstance().getWristPosition() <= EndEffectorConstants.PROCESSOR_ANGLE + 0.1 && EndEffectorSubsystem.getInstance().getWristPosition() >= EndEffectorConstants.PROCESSOR_ANGLE - 0.1) {
            wristState = WristState.AT_PROCESSOR;
        } else {
            wristState = WristState.AT_INTAKE;
        }
    }

    public void updateWheelState() {
        if (EndEffectorSubsystem.getInstance().getWheelSpeed() > 0) {
            wheelState = WheelState.INTAKE;
        } else if (EndEffectorSubsystem.getInstance().getWheelSpeed() < 0) {
            wheelState = WheelState.OUTTAKE;
        } else {
            wheelState = WheelState.IDLE;
        }
    }

    public void updateIntakeState() {

    }

    public void updateIndexerState() {
        if (IndexerSubsystem.getInstance().getSpeed() > 0) {
            indexerState = IndexerState.ENABLED;
        } else {
            indexerState = IndexerState.DISABLED;
        }
    }

    public void updateBeamBreakState() {
        if (BeamBreakSubsystem.getInstance().isBeamBroken()) {
            beamBreakState = BeamBreakState.BROKEN;
        } else {
            beamBreakState = BeamBreakState.NOT_BROKEN;
        }
    }

    //=========================================================================
    //=============================== GETTERS =================================

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public WristState getWristState() {
        return wristState;
    }

    public WheelState getWheelState() {
        return wheelState;
    }

    public IntakeState getIntakeState() {
        return intakeState;
    }

    public IndexerState getIndexerState() {
        return indexerState;
    }

    public BeamBreakState getBeamBreakState() {
        return beamBreakState;
    }

}