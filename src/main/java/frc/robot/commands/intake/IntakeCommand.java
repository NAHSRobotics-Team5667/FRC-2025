package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States.IntakeState;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.IndexerConstants;

public class IntakeCommand extends Command {
    private IntakeSubsystem intake;
    private IndexerSubsystem indexer;
    private StateManager stateManager;
    private double speed;
    private double indexSpeed;

    public IntakeCommand() {
        intake = IntakeSubsystem.getInstance();
        indexer = IndexerSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        if (stateManager.getIntakeState().equals(IntakeState.INTAKING)) {
            speed = 0.0;
            indexSpeed = 0.0;
        } else {
            speed = IntakeConstants.ROLLER_SPEED;
            indexSpeed = IndexerConstants.INDEXER_SPEED;
        }
    }

    @Override
    public void execute() {
        intake.setIntakeSpeed(speed);
        indexer.setSpeed(indexSpeed);

    }

    @Override
    public void end(boolean interrupted) {
       
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
