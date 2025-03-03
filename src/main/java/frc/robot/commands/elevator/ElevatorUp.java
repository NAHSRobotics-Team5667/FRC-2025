package frc.robot.commands.elevator;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorUp extends Command {
    private ElevatorSubsystem elevator;
    private StateManager states;
    double targetRotations;

    public ElevatorUp() {
        elevator = ElevatorSubsystem.getInstance(); 
        states = StateManager.getInstance(); // DO NOT add to addRequirements()

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(elevator);
    }

     // Called when the command is initially scheduled.
     @Override
     public void initialize() {
        States.ElevatorStates currentLevel = states.getElevatorStates();

        //TODO: Calculate rotations needed to reach the next level
        if (currentLevel.equals(States.ElevatorStates.LEVEL_4)) {
            targetRotations = 0; //The elevator should NOT move when it is at its maximum height.
        } else if (currentLevel.equals(States.ElevatorStates.LEVEL_3)) {

        } else if (currentLevel.equals(States.ElevatorStates.LEVEL_2)) {

        } else {

        }
     }

     // Called every time the scheduler runs while the command is scheduled.
     @Override
     public void execute() {
        elevator.moveUp(targetRotations);
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
