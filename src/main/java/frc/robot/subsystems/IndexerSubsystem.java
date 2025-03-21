// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.IndexerConstants;

/**
 * InderSubsystem.java
 * 
 * Refers to the robot's index
 * 
 * MOTORS ===========
 * 
 * Kraken X60 (1x)
 * 
 * SENSORS ==========
 * 
 * Beam Break (1x)
 * TODO: ACTUALLY ADD ONTO ROBOT
 * 
 **/

public class IndexerSubsystem extends SubsystemBase {

  TalonFX m_indexer;
  DigitalInput m_beamBreak;
  // ========================================================
  // ============= CLASS & SINGLETON SETUP ==================

  // SINGLETON ----------------------------------------------
  private static IndexerSubsystem instance = null;

  public static IndexerSubsystem getInstance() {
    if (instance == null)
      instance = new IndexerSubsystem();

      return instance;
  }

  /** Creates a new IndexerSubsystem. */
  public IndexerSubsystem() {
    m_indexer = new TalonFX(IndexerConstants.INDEX_ID_A);
    m_beamBreak = new DigitalInput(IndexerConstants.BEAM_BREAK_PORT);
  }

  // ========================================================
  // =================== MOTOR ACTIONS ======================

  /**
    * Sets index velocity.
    * 
    * @param speed percent output of index. Positive value is toward shooter. 0-100
    *              scale.
    */
  public void set(double speed) {
    speed/= 100;
    m_indexer.set(speed);
  }

  public double getSpeed() {
    return m_indexer.get();
  }

  public void stopMotor() {
    m_indexer.set(0);
  }

  /**
   * Returns if the beam break is triggered.
   * @return
   */
  public boolean getBeamBreak() {
    return m_beamBreak.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Beam Break Triggered:", getBeamBreak());
    
  }
}