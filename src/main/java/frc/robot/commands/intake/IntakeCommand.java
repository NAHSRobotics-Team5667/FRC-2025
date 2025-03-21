package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    private IntakeSubsystem intake;

    public IntakeCommand() {
        intake = IntakeSubsystem.getInstance();
        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(intake);
    }

    // Called when the command is initially scheduled. 
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
             
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //TODO: Figure out when the coral has passed entered the robot.
        return false;
    }
}
