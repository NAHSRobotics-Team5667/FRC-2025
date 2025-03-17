package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.IntakeConstants;

public class IntakeCommand extends Command {
    private IntakeSubsystem intake;
    private StateManager states;

    public IntakeCommand() {
        intake = IntakeSubsystem.getInstance();
        states = StateManager.getInstance();
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(intake);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intake.set(IntakeConstants.INTAKE_SPEED);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.stopMotor();
    }
             
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
