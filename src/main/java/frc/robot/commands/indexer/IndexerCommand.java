package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.IndexerConstants;;

public class IndexerCommand extends Command {
    private IndexerSubsystem indexer;
    private StateManager states;

    public IndexerCommand() {
        indexer = IndexerSubsystem.getInstance();
        states = StateManager.getInstance();
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        indexer.set(IndexerConstants.INTAKE_SPEED);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.stopAllMotors();
    }
             
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
