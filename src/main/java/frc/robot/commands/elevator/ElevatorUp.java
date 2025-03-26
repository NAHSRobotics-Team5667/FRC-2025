package frc.robot.commands.elevator;

// Local Imports
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.util.States.ElevatorState;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorUp extends Command {
    private ElevatorSubsystem elevator;
    private StateManager stateManager;
    private ElevatorState initState;
    private double distance; //In terms of meters and gets converted to rotations

    public ElevatorUp() {
        elevator = ElevatorSubsystem.getInstance();
        stateManager = StateManager.getInstance();
        addRequirements(elevator);
    }
    
    @Override
    public void initialize() {
        initState = stateManager.getElevatorState();
        if (initState.equals(ElevatorState.MOVING) || initState.equals(ElevatorState.LEVEL_4)) {
            distance = 0;
        } else if (initState.equals(ElevatorState.LEVEL_1)) {
            distance = ElevatorConstants.LEVEL_2;
        } else if (initState.equals(ElevatorState.LEVEL_2)) {
            distance = ElevatorConstants.LEVEL_3;
        } else {
            distance = ElevatorConstants.LEVEL_4;
        }
    }

    @Override
    public void execute() {
        elevator.moveElevator(1); //TEST VALUE
    }

    @Override
    public boolean isFinished() {
        return elevator.getElevatorPosition() >= (distance);
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    
}