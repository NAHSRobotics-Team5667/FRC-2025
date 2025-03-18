// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
//WPILib imports.
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Native file imports.
import frc.robot.Constants.ClimberConstants;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
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
  private TalonFXConfiguration climbConfig;
  private MotionMagicConfigs motionMagicClimb;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    m_climbMotor = new TalonFX(ClimberConstants.CLIMBER_ID);

    //Motor Configuration --------------------------------
    climbConfig = new TalonFXConfiguration();
    motionMagicClimb = new MotionMagicConfigs();
    // Set conversion rate from motor shaft rotations to climber rotations
    climbConfig.Feedback.SensorToMechanismRatio = ClimberConstants.GEAR_RATIO;
    m_climbMotor.getConfigurator().apply(climbConfig);

  }

  private static ClimberSubsystem instance = null;

  public static ClimberSubsystem getInstance() {
    if (instance == null) {
      instance = new ClimberSubsystem();
    }
    return instance;
  }
  
  public void setConfigs(boolean hasClimbed) {
    if (!hasClimbed) {
      motionMagicClimb.MotionMagicCruiseVelocity = ClimberConstants.MAX_VELOCITY; 
      motionMagicClimb.MotionMagicAcceleration = ClimberConstants.MAX_ACCELERATION; 
    } else {
      motionMagicClimb.MotionMagicCruiseVelocity = -ClimberConstants.MAX_VELOCITY; 
      motionMagicClimb.MotionMagicAcceleration = -ClimberConstants.MAX_ACCELERATION; 
    }
  }

  public void climb(double angle) {
    //Apply Motion Magic Configurations
    m_climbMotor.getConfigurator().apply(motionMagicClimb);
    m_climbMotor.set(Units.degreesToRotations(angle));
  }

  public double getSpeed() {
    return m_climbMotor.get();
  }

  public void stopMotor() {
    m_climbMotor.set(0);
  }

  public void setSpeed(double speed) {
    m_climbMotor.set(speed/100);
  }

  public double getAngle() {
    return Units.rotationsToDegrees(m_climbMotor.getPosition().getValueAsDouble());
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
