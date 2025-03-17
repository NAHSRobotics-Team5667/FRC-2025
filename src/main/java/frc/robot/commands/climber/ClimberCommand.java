package frc.robot.commands.climber;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberCommand extends Command {
  private ClimberSubsystem climber;
  private StateManager states;
    
  //TODO: Finish the command file
    public ClimberCommand() {
        climber = ClimberSubsystem.getInstance(); 
        states = StateManager.getInstance(); // DO NOT add to addRequirements()
    
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber);
    }
}
