// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import static edu.wpi.first.units.Units.Rotations;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EndEffectorConstants;

public class EndEffectorSubsystem extends SubsystemBase {
    private TalonFX m_wheels;
    private TalonFX m_wrist;
    private TalonFXConfiguration wristConfig;


    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    private static EndEffectorSubsystem instance;

    public EndEffectorSubsystem() {
        m_wheels = new TalonFX(EndEffectorConstants.WHEELS_ID);
        m_wrist = new TalonFX(EndEffectorConstants.WRIST_ID);
        wristConfig = new TalonFXConfiguration();

        wristConfig.Feedback.SensorToMechanismRatio = 1.0; // 1:1 Gear Ratio
        wristConfig.MotionMagic.withMotionMagicCruiseVelocity(EndEffectorConstants.MAX_VELOCITY)
        .withMotionMagicAcceleration(EndEffectorConstants.MAX_ACCELERATION);
        m_wrist.getConfigurator().apply(wristConfig);

    }

    public static EndEffectorSubsystem getInstance() {
        if (instance == null) {
            instance = new EndEffectorSubsystem();
        }
        return instance;
    }

    //===============================================================
    //===================== MOTOR ACTIONS ===========================

    public void moveWheels(double speed) {
        m_wheels.set(speed);
    }

    public void stopWheels() {
        m_wheels.set(0);
    }

    public void moveWrist(double position) {
        m_wrist.setControl(new MotionMagicVoltage(position));
    }

    public void stopWrist() {
        m_wrist.set(0);
    }

    //================================================================
    //========================== GETTERS =============================

    public double getWristPosition() {
        return m_wrist.getPosition().getValue().in(Rotations);
    }

    public boolean isWristMoving() {
        if (m_wrist.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWheelEnabled() {
        if (m_wheels.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

  
}