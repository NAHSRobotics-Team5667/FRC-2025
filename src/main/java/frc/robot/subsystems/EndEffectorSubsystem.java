// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EndEffectorConstants;


/**
 * EndEffectorSubsystem.java
 * 
 * Refers to the robot's game piece manipulator
 * 
 * MOTORS ===========
 * 
 * Kraken X60 - Wrist
 * 
 * Kraken X44 - Clamp
 * 
 * SENSORS ==========
 * 
 **/

public class EndEffectorSubsystem extends SubsystemBase {
  private TalonFX m_wrist;
  private TalonFX m_clamp;
  private static MotionMagicConfigs motionMagicWrist;
  private static MotionMagicConfigs motionMagicClamp;

    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    private static EndEffectorSubsystem instance = null;

  public EndEffectorSubsystem() {
    m_wrist = new TalonFX(EndEffectorConstants.WRIST_ID);
    m_clamp = new TalonFX(EndEffectorConstants.CLAMP_ID);
    motionMagicWrist = new MotionMagicConfigs();
    motionMagicClamp = new MotionMagicConfigs();
  }

  public static EndEffectorSubsystem getInstance() {
    if (instance == null)
        instance = new EndEffectorSubsystem();

    return instance;
}

    // ========================================================
    // ================== MOTOR ACTIONS ======================-

    public void setWristAngle(double rotations) {
      motionMagicWrist.MotionMagicCruiseVelocity = EndEffectorConstants.WRIST_SPEED;
      m_wrist.getConfigurator().apply(motionMagicWrist);
      final MotionMagicVoltage m_request = new MotionMagicVoltage(rotations);
      m_wrist.setControl(m_request.withPosition(rotations));
    }

    public void adjustClamp(double rotations) {
      motionMagicClamp.MotionMagicCruiseVelocity = EndEffectorConstants.CLAMP_SPEED; 
      m_wrist.getConfigurator().apply(motionMagicClamp);
      final MotionMagicVoltage m_request = new MotionMagicVoltage(rotations);
      m_clamp.setControl(m_request.withPosition(rotations));
    }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
