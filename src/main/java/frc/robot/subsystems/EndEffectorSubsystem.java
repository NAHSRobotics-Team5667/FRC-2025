package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants.EndEffectorConstants;
import frc.robot.util.States.EndEffectorWristState;;

/**
 * EndEffectorSubsystem.java
 * 
 * Refers to the robot's game piece manipulator.
 * 
 * MOTORS ===========
 * 
 * Kraken X60 - Wrist - Turns actual angle of end effector.
 * 
 * Kraken X44 - Wheels - Turns on the green wheels that takes in game pieces.
 * 
 * SENSORS ==========
 * 
 **/
public class EndEffectorSubsystem implements Subsystem {
    private TalonFX m_wristMotor; //This motor will control the wrist on our end effector.
    private TalonFX m_wheelMotor; //This motor will control the wheels on ou end effector.
    private static DigitalInput m_beamBreak; //This is the beam break sensor that will be used to detect if a game piece is in the end effector.
    private MotionMagicConfigs MM_Wrist; //This will be used to set the motion magic values for the wrist motor.

    //Singleton
    private static EndEffectorSubsystem instance = null;

    public static EndEffectorSubsystem getInstance() {
        if (instance == null)
            instance = new EndEffectorSubsystem(m_beamBreak);

        return instance;
    }

    //Constructor
    public EndEffectorSubsystem(DigitalInput beamBreak) {
        m_wristMotor = new TalonFX(EndEffectorConstants.WRIST_ID);
        m_wheelMotor = new TalonFX(EndEffectorConstants.WHEELS_ID);
        beamBreak = m_beamBreak;
        MM_Wrist = new MotionMagicConfigs().
        withMotionMagicCruiseVelocity(EndEffectorConstants.VELOCITY).
        withMotionMagicAcceleration(EndEffectorConstants.ACCELERATION);
    }

    //=========================================================================
    //============================= WRIST =====================================

    /**
     * This method will move the end effector to a desired angle.
     * @param targetAngle - The angle to move the end effector to.
     */
    public void moveWristToSetPoint(double targetAngle) {
        MotionMagicVoltage wristRequest = new MotionMagicVoltage(0);
        m_wristMotor.setControl(wristRequest.withPosition(targetAngle));
    }

    /**
     * This method will return the current position of the wrist motor.
     * @return The current position of the wrist motor.
     */
    public double getWristPosition() {
        return m_wristMotor.getPosition().getValueAsDouble();
    }

    /**
     * This method will stop the wrist motor
     */
    public void stopWrist() {
        m_wristMotor.set(0);
    }

    //=========================================================================
    //============================= WHEELS ====================================

    /**
     * This method will move the wheels on the end effector.
     * @param speed - The speed to move the wheels at.
     */
    public void moveWheels(double speed) {
        m_wheelMotor.set(speed);
    }

    /**
     * This method will return the speed of the wheels on the end effector.
     * @return The speed of the wheels on the end effector.
     */
    public double getWheelSpeed() {
        return m_wheelMotor.get();
    }

    /**
     * This method will stop the wheels on the end effector.
     */
    public void stopWheels() {
        m_wheelMotor.set(0);
    }

    //=========================================================================
    //============================= BEAM BREAK ================================
    public boolean isBeamBroken() {
        return !m_beamBreak.get(); // Typically beam breaks return false when broken
    }    

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Wrist Position (in Rotations)", getWristPosition());
        SmartDashboard.putNumber("Wheel Speed", getWheelSpeed());
    }
    
}