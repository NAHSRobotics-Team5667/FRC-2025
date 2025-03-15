package frc.robot.commands.endeffector;

import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;

public class wristToProcessor extends Command {
    private EndEffectorSubsystem end_effector;
    private StateManager states;
    double targetRotations;

    public wristToProcessor() {
        end_effector = EndEffectorSubsystem.getInstance();
        states = StateManager.getInstance();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        States.EndEffectorStates currentPosition = states.getEndEffectorStates();

        targetRotations = switch (currentPosition) {
            case AT_PROCESSOR, IS_MOVING -> 0;

            case AT_BARGE -> EndEffectorConstants.PROCESSOR_ANGLE - EndEffectorConstants.BARGE_ANGLE;

            case AT_REEF -> EndEffectorConstants.PROCESSOR_ANGLE - EndEffectorConstants.REEF_ANGLE;

            default -> EndEffectorConstants.PROCESSOR_ANGLE;
        };
    }

         // Called every time the scheduler runs while the command is scheduled.
         @Override
         public void execute() {
            end_effector.setWristAngle(targetRotations);
            states.updateEndEffectorStates(States.EndEffectorStates.IS_MOVING);
         }
    
        // Called once the command ends or is interrupted.
        @Override
        public void end(boolean interrupted) {
            states.updateEndEffectorStates(States.EndEffectorStates.AT_PROCESSOR);
        }
    
        // Returns true when the command should end.
        @Override
        public boolean isFinished() {
            return false;
        }
}
