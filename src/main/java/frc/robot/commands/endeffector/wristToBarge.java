package frc.robot.commands.endeffector;

import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;

public class wristToBarge extends Command {
    private EndEffectorSubsystem end_effector;
    private StateManager states;
    double targetRotations;

    public wristToBarge() {
        end_effector = EndEffectorSubsystem.getInstance();
        states = StateManager.getInstance();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        States.EndEffectorStates currentPosition = states.getEndEffectorStates();

        targetRotations = switch (currentPosition) {
            case AT_BARGE -> 0;

            case AT_REEF -> EndEffectorConstants.BARGE_ANGLE - EndEffectorConstants.REEF_ANGLE;

            case AT_PROCESSOR -> EndEffectorConstants.BARGE_ANGLE - EndEffectorConstants.PROCESSOR_ANGLE;

            default -> EndEffectorConstants.BARGE_ANGLE;
        };
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
