package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.Constants.IndexerConstants;;

public class IndexerCommand extends Command {
    private IndexerSubsystem indexer;

    public IndexerCommand() {
        indexer = IndexerSubsystem.getInstance();

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(indexer);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {
        indexer.set(0);
    }

    @Override
    public void execute() {
        indexer.set(IndexerConstants.INTAKE_SPEED);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        indexer.stopMotor();
    }
             
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //TODO: Figure out when the coral has passed through the indexer
        return false;
    }
}
