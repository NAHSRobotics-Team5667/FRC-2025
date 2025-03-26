// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.Follower;
import static edu.wpi.first.units.Units.Rotations;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX m_left;
    private TalonFX m_right; //Right Motor acts as a Follower of Left Motor
    private TalonFXConfiguration config;


    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    private static ElevatorSubsystem instance;

    public ElevatorSubsystem() {
        m_left = new TalonFX(ElevatorConstants.LEFT_MOTOR_ID);
        m_right = new TalonFX(ElevatorConstants.RIGHT_MOTOR_ID);
        m_right.setControl(new Follower(ElevatorConstants.LEFT_MOTOR_ID, false));

        config = new TalonFXConfiguration();  
        config.MotionMagic.withMotionMagicCruiseVelocity(ElevatorConstants.MAX_VELOCITY)
        .withMotionMagicAcceleration(ElevatorConstants.MAX_ACCELERATION)
        .withMotionMagicJerk(ElevatorConstants.MAX_JERK); 
        config.Feedback.SensorToMechanismRatio = 12.0; // 12:1 Gear Ratio
        m_left.getConfigurator().apply(config);
    }

    public static ElevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSubsystem();
        }
        return instance;
    }

    //===============================================================
    //===================== MOTOR ACTIONS ===========================

    public void moveElevator(double newPosition) {
        double rotationsFromMeters = -(newPosition/(2*Math.PI*ElevatorConstants.WHEEL_RADIUS)*ElevatorConstants.GEAR_RATIO);
        m_left.setNeutralMode(NeutralModeValue.Brake);
        m_left.setControl(new MotionMagicVoltage(rotationsFromMeters));
    }

    public void stopElevator() {
        m_left.setControl(new MotionMagicVoltage(0));
    }

    //================================================================
    //========================== GETTERS =============================

    public double getElevatorPosition() {
        double rotations = m_left.getPosition().getValue().in(Rotations);
        return (rotations * ElevatorConstants.WHEEL_RADIUS * 2 * Math.PI)/ElevatorConstants.GEAR_RATIO;
    }

    public boolean isMoving() {
        if (m_left.get() > 0 || m_left.get() < 0) {
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