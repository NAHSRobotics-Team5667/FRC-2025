package frc.robot.commands.elevator;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorDown extends Command {
    private ElevatorSubsystem elevator;
    private StateManager states;
    private double targetRotations;
    private double elevatorPosition;
    private States.ElevatorStates currentState;

    public ElevatorDown() {
        elevator = ElevatorSubsystem.getInstance(); 
        states = StateManager.getInstance(); // DO NOT add to addRequirements()
        currentState = states.getElevatorStates(); // DO NOT add to addRequirements()

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(elevator);
    }

      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
         elevatorPosition = elevator.getPosition();
         if (currentState == States.ElevatorStates.BASE || currentState == States.ElevatorStates.MOVING) {
             targetRotations = 0;
         } else if (currentState == States.ElevatorStates.LEVEL_1) {
             targetRotations = elevator.calcRotations(elevatorPosition, 0);
         } else if (currentState == States.ElevatorStates.LEVEL_2) {
             targetRotations = elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_1);
         } else if (currentState == States.ElevatorStates.LEVEL_3){
             targetRotations = elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_2);
         } else {
            targetRotations = elevator.calcRotations(elevatorPosition, ElevatorConstants.LEVEL_3);
         }
      }
 
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
         elevator.moveUp(targetRotations);
         states.updateElevatorStates();
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
