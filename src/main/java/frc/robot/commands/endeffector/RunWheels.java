package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.WheelState;

public class RunWheels extends Command {
    private final EndEffectorSubsystem endEffector;
    private final StateManager stateManager;
    private WheelState desiredState;
    private double desiredSpeed;

    public RunWheels(WheelState desiredState) {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();
        this.desiredState = desiredState;
        addRequirements(endEffector);
    }

    @Override
    public void initialize() {
        if (desiredState.equals(WheelState.IDLE)) {
            desiredSpeed = 0.0;
        } else if (desiredState.equals(WheelState.INTAKE)) {
            desiredSpeed = EndEffectorConstants.WHEEL_SPEED;
        } else {
            desiredSpeed = -EndEffectorConstants.WHEEL_SPEED;
        }
    }

    @Override
    public void execute() {
        endEffector.moveWheels(desiredSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
       if (stateManager.getWheelState().equals(desiredState)) {
        return true;
       } else {
        return false;
       }
    }
}