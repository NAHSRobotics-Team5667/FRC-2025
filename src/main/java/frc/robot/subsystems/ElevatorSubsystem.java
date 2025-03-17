package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
/**
 * ElevatorSubsystem.java
 * 
 * Refers to the elevator that adjusts the height of the end effector
 *   
 * MOTORS ===========
 * Kraken X60 (2x)
 * 
 */
public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX m_left; //Moves the left chain
    private TalonFX m_right; //Moves the right chain
    private MotionMagicConfigs motionMagicLeft;
    private MotionMagicConfigs motionMagicRight;
    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // CONSTRUCTOR ----------------------------------------------

    public ElevatorSubsystem() {
        m_left = new TalonFX(ElevatorConstants.LEFT_MOTOR_ID); //Moves the left chain
        m_right = new TalonFX(ElevatorConstants.RIGHT_MOTOR_ID); //Moves the right chain
        motionMagicLeft = new MotionMagicConfigs();
        motionMagicRight = new MotionMagicConfigs();
    }

    // SINGLETON ----------------------------------------------

    private static ElevatorSubsystem instance = null;

    public static ElevatorSubsystem getInstance() {
        if (instance == null)
            instance = new ElevatorSubsystem();

        return instance;
    }

 
    public void moveDown(double targetRotations) {
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicLeft.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        motionMagicRight.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicRight.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        
        //Apply Motion Magic Configurations
        m_left.getConfigurator().apply(motionMagicLeft);
        m_right.getConfigurator().apply(motionMagicRight);

        //Create Request to Motors
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_left.setControl(m_request.withPosition(targetRotations)); 
        m_right.setControl(m_request.withPosition(targetRotations));
    }

    public void moveUp(double targetRotations) {
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicLeft.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        motionMagicRight.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicRight.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 

        //Apply Motion Magic Configurations
        m_left.getConfigurator().apply(motionMagicLeft);
        m_right.getConfigurator().apply(motionMagicRight);

        //Create Request to Motors
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_left.setControl(m_request.withPosition(targetRotations));
        m_right.setControl(m_request.withPosition(targetRotations));
        
    }

    public double calcRotations(double currentLevel, double nextLevel) {
        return (nextLevel - currentLevel)/(Math.PI * 2 * ElevatorConstants.WHEEL_RADIUS)*ElevatorConstants.GEAR_RATIO;
    }
}