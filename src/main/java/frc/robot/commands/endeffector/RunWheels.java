package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States.EndEffectorWristState;

public class RunWheels extends Command {
    private final EndEffectorSubsystem endEffector;
    private final StateManager stateManager;

    public RunWheels() {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(endEffector);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        
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