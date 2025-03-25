package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States.EndEffectorWristState;

public class RunWheels extends Command {
    private final EndEffectorSubsystem endEffector;
    private final StateManager stateManager;
    private boolean isRunning;

    public RunWheels() {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(endEffector);
    }

    @Override
    public void initialize() { 
        if (endEffector.getWheelSpeed() > 0) {
            isRunning = true;
        } else {
            isRunning = false;
        }
    }

    @Override
    public void execute() {
        if (!isRunning) {
            endEffector.moveWheels(1);
        } else {
            endEffector.moveWheels(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        endEffector.stopWheels();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}