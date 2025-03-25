package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States.IntakeState;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeConstants;

public class IntakeCommand extends Command {
    private IntakeSubsystem intake;
    private StateManager stateManager;
    private static DigitalInput beamBreak;

    public IntakeCommand() {
        beamBreak = new DigitalInput(IndexerConstants.BEAM_BREAK_PORT_ID);
        intake = IntakeSubsystem.getInstance(beamBreak);
        stateManager = StateManager.getInstance();

        addRequirements(intake);
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
