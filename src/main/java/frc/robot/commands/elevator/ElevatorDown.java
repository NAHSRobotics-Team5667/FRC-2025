package frc.robot.commands.elevator;

// Local Imports
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.util.States.ElevatorState;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorDown extends Command {
    private ElevatorSubsystem elevator;
    private StateManager stateManager;
    private ElevatorState initState;
    private double initPosition;
    private double distance; //In terms of meters and gets converted to rotations

     public ElevatorDown() {
        elevator = ElevatorSubsystem.getInstance();
        stateManager = StateManager.getInstance();
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        initState = stateManager.getElevatorState();
        initPosition = elevator.getElevatorPosition();
        if (initState.equals(ElevatorState.MOVING) || initState.equals(ElevatorState.LEVEL_1)) {
            distance = 0;
        } else if (initState.equals(ElevatorState.LEVEL_4)) {
            distance = ElevatorConstants.LEVEL_3 - initPosition;
        } else if (initState.equals(ElevatorState.LEVEL_3)) {
            distance = ElevatorConstants.LEVEL_2 - initPosition;
        } else {
            distance = ElevatorConstants.LEVEL_1 - initPosition;
        }
    }

    @Override
    public void execute() {
        final double targetRotations = elevator.metersToRotations(distance);
        elevator.moveElevator(1); //TEST VALUE
    }

    @Override
    public boolean isFinished() {
        return elevator.getElevatorPosition() <= (initPosition - distance);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.stop();
    }
}