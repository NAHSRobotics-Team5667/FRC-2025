package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.States.*;

public class StateManager extends SubsystemBase {
    //Elevator
    private ElevatorStates elevatorState;

    //Elevator
    private EndEffectorStates endEffectorState;

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

    
    /**
     * @return elevator state.
     */
    public ElevatorStates getElevatorStates() {
        return elevatorState;
    }

        /**
     * @return elevator state.
     */
    public EndEffectorStates getEndEffectorStates() {
        return endEffectorState;
    }

    // ========================================================
    // ======================= LEDS ===========================

    private void updateLights() {
        if (DriverStation.isEnabled()) {

        }
    }

}

