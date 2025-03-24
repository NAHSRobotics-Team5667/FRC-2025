package frc.robot.commands.endeffector;

// Local Imports
import frc.robot.subsystems.EndEffectorSubsystem;
import frc.robot.Constants.EndEffectorConstants;

import edu.wpi.first.wpilibj2.command.Command;

//==============================================================================
//====== THIS IS A TEST COMMAND USED TO CHECK IF THE SUBYSTEM WORKS!!!!! =======
public class WristTest extends Command {
    private EndEffectorSubsystem endEffector;
    private double testPosition = 0.5; //Test Value

    public WristTest() {
        endEffector = EndEffectorSubsystem.getInstance();
        addRequirements(endEffector);
    }
    
    @Override
    public void initialize() {
        
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