package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.WheelState;

public class RunWheels extends Command {
    private final EndEffectorSubsystem endEffector;
    private final StateManager stateManager;
    private WheelState currentState;
    private double desiredSpeed;

    public RunWheels() {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();
        addRequirements(endEffector);
    }

    @Override
    public void initialize() {
        currentState = stateManager.getWheelState();
        if (currentState.equals(WheelState.OUTTAKE)) {
            desiredSpeed = 0.0;
        } else if (currentState.equals(WheelState.IDLE)) {
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
        return false;
    }
}