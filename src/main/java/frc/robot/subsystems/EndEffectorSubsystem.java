// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
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
  TalonFX m_wrist;
  TalonFX m_clamp;

    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    private static EndEffectorSubsystem instance = null;

  public EndEffectorSubsystem() {
    m_wrist = new TalonFX(EndEffectorConstants.WRIST_ID);
    m_clamp = new TalonFX(EndEffectorConstants.CLAMP_ID);

  }

  public static EndEffectorSubsystem getInstance() {
    if (instance == null)
        instance = new EndEffectorSubsystem();

    return instance;
}

    //TODO:Complete methods for End Effector motor actions
    // ========================================================
    // ============= MOTOR ACTIONS FOR WRIST ==================

    public void setWristToBarge() {}

    public void setWristToReef() {}

    public void setWristToProcessor() {}

    // ========================================================
    // ============= MOTOR ACTIONS FOR CLAMP ==================

    public void holdCoral() {}

    public void holdAlgae() {}

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
