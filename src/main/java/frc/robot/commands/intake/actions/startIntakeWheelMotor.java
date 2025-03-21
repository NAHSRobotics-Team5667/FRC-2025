package frc.robot.commands.intake.actions;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class startIntakeWheelMotor extends Command {
    private final IntakeSubsystem intake;

    public startIntakeWheelMotor() {
        // Retrieve the singleton instance of IntakeSubsystem
        intake = IntakeSubsystem.getInstance();

        // Declare subsystem dependencies
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Stop the intake motor to ensure no interference during pivot movement
        intake.stopWheelMotor();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Rotate the pivot to the downward position (90 degrees)
        intake.setWheelMotor(0.5);

        //TODO: Figure out what speed this needs to be set.
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Do nothing as this wheel needs to continously spin.
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (intake.getWheelSpeed() == 0.5); //This means the wheel is spinning at 50%.
    }
}
