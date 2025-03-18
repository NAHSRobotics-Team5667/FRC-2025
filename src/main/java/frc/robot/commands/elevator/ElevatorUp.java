package frc.robot.commands.elevator;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorUp extends Command {
    private ElevatorSubsystem elevator;
    private StateManager states;
    private double targetRotations;
    private States.ElevatorStates currentLevel;
    private double elevatorPosition;

    public ElevatorUp() {
        elevator = ElevatorSubsystem.getInstance(); 
        states = StateManager.getInstance(); // DO NOT add to addRequirements()

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(elevator);
    }

     // Called when the command is initially scheduled.
     @Override
     public void initialize() {
        currentLevel = states.getElevatorStates();
        elevatorPosition = elevator.getPosition();
        targetRotations = switch (currentLevel) {
            case MOVING, LEVEL_4 -> 0;

            case LEVEL_3 -> elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_4);

            case LEVEL_2 -> elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_3);

            case LEVEL_1 -> elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_2);

            default -> elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_1);
        };
     }

     // Called every time the scheduler runs while the command is scheduled.
     @Override
     public void execute() {
        elevator.moveUp(targetRotations);
        states.updateElevatorStates(States.ElevatorStates.MOVING);
     }

         // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        States.ElevatorStates newLevel;
        if (currentLevel.equals(States.ElevatorStates.LEVEL_3)) {
            newLevel = States.ElevatorStates.LEVEL_4;
        } else if (currentLevel.equals(States.ElevatorStates.LEVEL_2)) {
            newLevel = States.ElevatorStates.LEVEL_3;
        } else if (currentLevel.equals(States.ElevatorStates.LEVEL_3)) {
            newLevel = States.ElevatorStates.LEVEL_4;
        } else {
            newLevel = States.ElevatorStates.LEVEL_1;
        }

        states.updateElevatorStates(newLevel);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
