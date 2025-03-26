package frc.robot.commands.endeffector;

// Local Imports
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.WristState;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveWrist extends Command {
    private EndEffectorSubsystem endEffector;
    private StateManager stateManager;
    private WristState initState;
    private double rotations;

    public MoveWrist() {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(endEffector);
    }
    
    @Override
    public void initialize() {
        initState = stateManager.getWristState();
        //Intake -> Barge -> Processor -> Reef
        if (initState.equals(WristState.AT_INTAKE)) {
            rotations = EndEffectorConstants.BARGE_ANGLE;
        } else if (initState.equals(WristState.AT_BARGE)) {
            rotations = EndEffectorConstants.PROCESSOR_ANGLE;
        } else if (initState.equals(WristState.AT_PROCESSOR)) {
            rotations = EndEffectorConstants.REEF_ANGLE;
        } else {
            rotations = 1 - EndEffectorConstants.INTAKE_ANGLE;
        }
    }

    @Override
    public void execute() {
        endEffector.moveWrist(rotations);
    }

    @Override
    public void end(boolean interrupted) {
        endEffector.stopWrist();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    
}