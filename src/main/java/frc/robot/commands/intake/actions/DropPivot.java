package frc.robot.commands.intake.actions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class DropPivot extends Command {
    private final IntakeSubsystem intake;

    public DropPivot() {
        // Retrieve the singleton instance of IntakeSubsystem
        intake = IntakeSubsystem.getInstance();

        // Declare subsystem dependencies
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Stop the intake motor to ensure no interference during pivot movement
        intake.stopPivotMotor();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Rotate the pivot to the downward position (90 degrees)
        intake.rotatePivotDown();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stop the pivot motor to hold its position.
        intake.stopPivotMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // Determine if the pivot has reached the desired position
        return intake.isPivotAtPosition(90.0);
    }
}
