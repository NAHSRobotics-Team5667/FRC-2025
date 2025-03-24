package frc.robot.commands.elevator;

// Local Imports
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.ElevatorConstants;

import edu.wpi.first.wpilibj2.command.Command;

//==============================================================================
//====== THIS IS A TEST COMMAND USED TO CHECK IF THE SUBYSTEM WORKS!!!!! =======
public class ElevatorUp extends Command {
    private ElevatorSubsystem elevator;
    private double targetPosition = 1; //Test Value

    public ElevatorUp() {
        elevator = ElevatorSubsystem.getInstance();
        addRequirements(elevator);
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        elevator.moveElevator(targetPosition);
    }

    @Override
    public boolean isFinished() {
        return elevator.getElevatorPosition() >= targetPosition;
    }

    
}