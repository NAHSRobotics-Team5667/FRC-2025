package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.util.States.ElevatorState;
import frc.robot.util.States.RobotState;
import frc.robot.util.States.EndEffectorWheelState;
import frc.robot.util.States.EndEffectorWristState;
import frc.robot.util.States.IntakeState;
import frc.robot.util.States.IndexerState;

public class StateManager extends SubsystemBase {
    private static final DigitalInput m_beamBreak = new DigitalInput(IndexerConstants.BEAM_BREAK_PORT_ID);
    //=========================================================================
    //======================== STATE MANAGERS =================================

    //Robot
    private RobotState robotState;

    //Elevator
    private ElevatorState elevatorState;

    //End Effector
    private EndEffectorWristState wristState;
    private EndEffectorWheelState wheelState;

    //Intake
    private IntakeState intakeState;

    //Indexer
    private IndexerState indexerState;



    //=========================================================================
    //===================== SINGLETON AND CONSTRUCTOR =========================
    private static StateManager instance = null;

    public static StateManager getInstance() {
        if (instance == null)
            instance = new StateManager();

        return instance;
    }

    private StateManager() {
        //Robot ------------------------------
        robotState = RobotState.IDLE;
        
        //Elevator ------------------------------
        elevatorState = ElevatorState.LEVEL_1;

        //End Effector ------------------------------
        wristState = EndEffectorWristState.INTAKE;
        wheelState = EndEffectorWheelState.IDLE;

        //Intake ------------------------------
        intakeState = IntakeState.IDLE;

        //Indexer ------------------------------
        indexerState = IndexerState.DISABLED;
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
            wristState = EndEffectorWristState.BARGE;
        } else if (EndEffectorSubsystem.getInstance().getWristPosition() <= EndEffectorConstants.REEF_ANGLE + 0.1 && EndEffectorSubsystem.getInstance().getWristPosition() >= EndEffectorConstants.REEF_ANGLE - 0.1) {
            wristState = EndEffectorWristState.REEF;
        } if (EndEffectorSubsystem.getInstance().getWristPosition() <= EndEffectorConstants.PROCESSOR_ANGLE + 0.1 && EndEffectorSubsystem.getInstance().getWristPosition() >= EndEffectorConstants.PROCESSOR_ANGLE - 0.1) {
            wristState = EndEffectorWristState.PROCESSOR;
        } else {
            wristState = EndEffectorWristState.INTAKE;
        }
    }

    public void updateWheelState() {
        if (EndEffectorSubsystem.getInstance().getWheelSpeed() > 0) {
            wheelState = EndEffectorWheelState.INTAKING;
        } else if (EndEffectorSubsystem.getInstance().getWheelSpeed() < 0) {
            wheelState = EndEffectorWheelState.OUTTAKING;
        } else {
            wheelState = EndEffectorWheelState.IDLE;
        }
    }

    public void updateIntakeState() {

    }

    public void updateIndexerState() {
        if (IndexerSubsystem.getInstance(m_beamBreak).isBeamBroken()) {
            indexerState = IndexerState.HAS_CORAL;
        } else if (IndexerSubsystem.getInstance(m_beamBreak).getSpeed() > 0) {
            indexerState = IndexerState.ENABLED;
        } else {
            indexerState = IndexerState.DISABLED;
        }
    }

    //=========================================================================
    //=============================== GETTERS =================================

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public EndEffectorWristState getWristState() {
        return wristState;
    }

    public EndEffectorWheelState getWheelState() {
        return wheelState;
    }

    public IntakeState getIntakeState() {
        return intakeState;
    }

    public IndexerState getIndexerState() {
        return indexerState;
    }

}