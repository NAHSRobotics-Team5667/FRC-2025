// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//WPILib imports.
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Native file imports.
import frc.robot.Constants.ClimberConstants;

//CTRE imports.
import com.ctre.phoenix6.hardware.TalonFX;

/**
 * ClimberSubsystem.java
 * 
 * Refers to the robot's climbing mechanism
 * 
 * MOTORS ===========
 * 
 * Kraken X60
 * 
 * SENSORS ==========
 * 
 **/

public class ClimberSubsystem extends SubsystemBase {
  private TalonFX m_climbMotor; //Operates the climber wintch.

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    m_climbMotor = new TalonFX(ClimberConstants.CLIMBER_ID);
  }

  private static ClimberSubsystem instance = null;

  public static ClimberSubsystem getInstance() {
    if (instance == null) {
      instance = new ClimberSubsystem();
    }
    return instance;
  }

  public double getSpeed() {
    return m_climbMotor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
