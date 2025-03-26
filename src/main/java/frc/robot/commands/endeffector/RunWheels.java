package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.EndEffectorSubsystem;

public class RunWheels extends Command {
    private final EndEffectorSubsystem endEffector;
    private boolean isRunning;

    public RunWheels() {
        endEffector = EndEffectorSubsystem.getInstance();

        addRequirements(endEffector);
    }

    @Override
    public void initialize() { 
        if (endEffector.isWheelEnabled()) {
            isRunning = true;
        } else {
            isRunning = false;
        }
    }

    @Override
    public void execute() {
        if (!isRunning) {
            endEffector.moveWheels();
        } else {
            endEffector.stopWheels();;
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