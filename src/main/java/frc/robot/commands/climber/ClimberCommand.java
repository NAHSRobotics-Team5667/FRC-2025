package frc.robot.commands.climber;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.util.States;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;

public class ClimberCommand extends Command {
  private ClimberSubsystem climber;
  private StateManager states;
  private States.ClimberStates currentState;
  private double angle;
    
  //TODO: Finish the command file
    public ClimberCommand() {
        climber = ClimberSubsystem.getInstance(); 
        states = StateManager.getInstance(); // DO NOT add to addRequirements()
    
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(climber);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      if (currentState.equals(States.ClimberStates.CLIMBER_OFF)) {
        climber.setConfigs(false);
        angle = ClimberConstants.ANGLE;
      } else if (currentState.equals(States.ClimberStates.HANGING)) {
        climber.setConfigs(true);
        angle = -ClimberConstants.ANGLE;
      } else {

      }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      climber.climb(angle);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      climber.stopMotor();
    }
    
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
}
