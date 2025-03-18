package frc.robot.commands.endeffector;

import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.EndEffectorStates;
import edu.wpi.first.wpilibj2.command.Command;

public class moveWrist extends Command {
    private EndEffectorSubsystem end_effector;
    private EndEffectorStates state;
    private double targetRotations;
    private double currentPosition;

    public moveWrist(EndEffectorStates state) {
        end_effector = EndEffectorSubsystem.getInstance();
        this.state = state;
        currentPosition = end_effector.getWristPosition();

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(end_effector);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(state.equals(EndEffectorStates.AT_BARGE)) {
            targetRotations = EndEffectorConstants.BARGE_ANGLE - currentPosition;
        } else if(state.equals(EndEffectorStates.AT_INTAKE)) {
            targetRotations = EndEffectorConstants.INTAKE_ANGLE - currentPosition;
        } else if(state.equals(EndEffectorStates.AT_REEF)) {
            targetRotations = EndEffectorConstants.REEF_ANGLE - currentPosition;
        } else if(state.equals(EndEffectorStates.AT_PROCESSOR)) {
            targetRotations = EndEffectorConstants.PROCESSOR_ANGLE - currentPosition;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        end_effector.setWristAngle(targetRotations);
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
