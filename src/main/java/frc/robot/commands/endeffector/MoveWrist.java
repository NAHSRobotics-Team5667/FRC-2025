package frc.robot.commands.endeffector;

// Local Imports
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.EndEffectorWristState;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveWrist extends Command {
    private EndEffectorSubsystem endEffector;
    private StateManager stateManager;
    private EndEffectorWristState initState;
    private double initPosition;
    private double rotations;
    private double testPosition = 0.5; //Test Value

    public MoveWrist() {
        endEffector = EndEffectorSubsystem.getInstance();
        stateManager = StateManager.getInstance();

        addRequirements(endEffector);
    }
    
    @Override
    public void initialize() {
        initState = stateManager.getWristState();
        initPosition = endEffector.getWristPosition();
        //Intake -> Barge -> Processor -> Reef
        if (initState.equals(EndEffectorWristState.INTAKE)) {
            rotations = EndEffectorConstants.BARGE_ANGLE - initPosition;
        } else if (initState.equals(EndEffectorWristState.BARGE)) {
            rotations = EndEffectorConstants.PROCESSOR_ANGLE - initPosition;
        } else if (initState.equals(EndEffectorWristState.PROCESSOR)) {
            rotations = EndEffectorConstants.REEF_ANGLE - initPosition;
        } else {
            rotations = 1 - EndEffectorConstants.INTAKE_ANGLE;
        }
    }

    @Override
    public void execute() {
        endEffector.moveWristToSetPoint(testPosition);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    
}