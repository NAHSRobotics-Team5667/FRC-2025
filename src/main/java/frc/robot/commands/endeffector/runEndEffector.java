// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.endeffector;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.EndEffectorConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.EndEffectorSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class runEndEffector extends Command {
  /** Creates a new runEndEffector. */
  private EndEffectorSubsystem endEffector;

  public runEndEffector() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(endEffector);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endEffector.stopWheels();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    endEffector.setWheelSpeed(EndEffectorConstants.WHEEL_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    endEffector.stopWheels();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    // current draw analysis to-do before MVR
  }
}
