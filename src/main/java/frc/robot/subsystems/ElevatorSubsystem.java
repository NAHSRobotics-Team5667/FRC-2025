package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
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

    /**
     * Moves the elevator down to the target rotations
     * @param targetRotations
     */
    public void moveDown(double targetRotations) {
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = -ElevatorConstants.VELOCITY; 
        motionMagicLeft.MotionMagicAcceleration = -ElevatorConstants.ACCELERATION; 
        motionMagicRight.MotionMagicCruiseVelocity = -ElevatorConstants.VELOCITY; 
        motionMagicRight.MotionMagicAcceleration = -ElevatorConstants.ACCELERATION; 
        
        //Apply Motion Magic Configurations
        m_left.getConfigurator().apply(motionMagicLeft);
        m_right.getConfigurator().apply(motionMagicRight);

        //Create Request to Motors
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_left.setControl(m_request.withPosition(targetRotations)); 
        m_right.setControl(m_request.withPosition(targetRotations));
    }

    /**
     * Moves the elevator up to the target rotations
     * @param targetRotations
     */
    public void moveUp(double targetRotations) {
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY; 
        motionMagicLeft.MotionMagicAcceleration = ElevatorConstants.ACCELERATION; 
        motionMagicRight.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY; 
        motionMagicRight.MotionMagicAcceleration = ElevatorConstants.ACCELERATION; 

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
    /**
     * Returns the current position of the elevator
     * @return the current position of the elevator
     */
    public double getPosition() {
        Angle angle = m_left.getPosition().getValue(); //Only get the position of one motor - Both motors SHOULD have the same position
        double rotations = Double.parseDouble(angle.toString())/360;
        return (rotations * (2*Math.PI)*ElevatorConstants.WHEEL_RADIUS)/ElevatorConstants.GEAR_RATIO;
    }

    public boolean isMoving() {
        if (m_left.get() > 0 || m_right.get() > 0) {
            return true;
        } else {
            return false;
        }
    }
}