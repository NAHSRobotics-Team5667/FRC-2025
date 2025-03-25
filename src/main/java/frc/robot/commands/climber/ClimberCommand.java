package frc.robot.commands.climber;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.StateManager;
import frc.robot.Constants.ClimberConstants;
import frc.robot.util.States.ClimberState;
import edu.wpi.first.wpilibj2.command.Command;

public class ClimberCommand extends Command {
    private ClimberSubsystem climber;
    private StateManager stateManager;
    private ClimberState initState;
    private double targetRotations;

    public ClimberCommand() {
        climber = ClimberSubsystem.getInstance();
        stateManager = StateManager.getInstance();
        initState = stateManager.getClimberState();

        addRequirements(climber);
    }

    @Override
    public void initialize() {
        if (initState.equals(ClimberState.ZERO)) {
            targetRotations = ClimberConstants.ROTATIONS;
        } else if (initState.equals(ClimberState.CLIMBED)) {
            targetRotations = -ClimberConstants.ROTATIONS;
        } else {
            targetRotations = 0;
        }
    }

    @Override
    public void execute() {
        climber.moveClimber(targetRotations);
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimber();
    }

    @Override
    public boolean isFinished() {
       if (stateManager.getClimberState().equals(ClimberState.CLIMBED)) {
              return true;
         } else {
              return false;
       }
    }
    
}
