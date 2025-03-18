package frc.robot.commands.endeffector;

import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.EndEffectorStates;
import edu.wpi.first.wpilibj2.command.Command;

public class adjustClampAlgae extends Command {
    private EndEffectorSubsystem end_effector;
    private EndEffectorStates state;
    private double targetRotations;
    private double currentPosition;

    public adjustClampAlgae(EndEffectorStates state) {
        end_effector = EndEffectorSubsystem.getInstance();
        this.state = state;
        currentPosition = end_effector.getWristPosition();

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(end_effector);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(state.equals(EndEffectorStates.HOLDING_ALGAE)) {
            targetRotations = EndEffectorConstants.READY_FOR_ALGAE - currentPosition;
        } else {
            targetRotations = EndEffectorConstants.HOLD_ALGAE - currentPosition;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        end_effector.adjustClamp(targetRotations);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
