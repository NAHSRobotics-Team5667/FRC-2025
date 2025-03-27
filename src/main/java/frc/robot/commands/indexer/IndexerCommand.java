package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.subsystems.BeamBreakSubsystem;
import frc.robot.util.States.IndexerState;
import frc.robot.Constants.IndexerConstants;

public class IndexerCommand extends Command {
    private IndexerSubsystem indexer;
    private StateManager stateManager;
    private static BeamBreakSubsystem m_beamBreak;

    public IndexerCommand() {
        m_beamBreak = BeamBreakSubsystem.getInstance();
        indexer = IndexerSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
       
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
