// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
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
 * Kraken X44 - Wheels
 * 
 * SENSORS ==========
 * 
 **/

public class EndEffectorSubsystem extends SubsystemBase {
  private TalonFX m_wrist;
  private TalonFX m_wheels;
  private static MotionMagicConfigs motionMagicWrist;

    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    private static EndEffectorSubsystem instance = null;

  public EndEffectorSubsystem() {
    m_wrist = new TalonFX(EndEffectorConstants.WRIST_ID);
    m_wheels = new TalonFX(EndEffectorConstants.WHEELS_ID);
    motionMagicWrist = new MotionMagicConfigs();
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

    public double getWristSpeed() {
      return m_wrist.get();
    }

    public double getWristPosition() {
      return m_wrist.getPosition().getValueAsDouble()/360;
    }

    public void setWheelSpeed(double speed) {
      double motorSpeed = speed/100;
      m_wheels.set(motorSpeed);
    }
    
    public double getWheelSpeed() {
      return m_wheels.get();
    }

    public void stopWheels() {
      m_wheels.set(0);
    }
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
