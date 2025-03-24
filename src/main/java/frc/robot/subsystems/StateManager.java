package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.util.States.ElevatorState;
import frc.robot.util.States.RobotState;
import frc.robot.util.States.EndEffectorWheelState;
import frc.robot.util.States.EndEffectorWristState;

public class StateManager extends SubsystemBase {
    //=========================================================================
    //======================== STATE MANAGERS =================================

    //Robot
    private RobotState robotState;

    //Elevator
    private ElevatorState elevatorState;
    private ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();

    //End Effector
    private EndEffectorWristState wristState;
    private EndEffectorWheelState wheelState;
    private EndEffectorSubsystem endEffector = EndEffectorSubsystem.getInstance();



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
    }
    
    //=========================================================================
    //============================= STATE UPDATERS ============================

    public void updateElevatorState() {
        
    }

    public void updateWristState() {

    }

    public void updateWheelState() {
        if (endEffector.getWheelSpeed() > 0) {
            wheelState = EndEffectorWheelState.INTAKING;
        } else if (endEffector.getWheelSpeed() < 0) {
            wheelState = EndEffectorWheelState.OUTTAKING;
        } else {
            wheelState = EndEffectorWheelState.IDLE;
        }
    }
}