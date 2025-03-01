package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
/**
 * ElevatorSubsystem.java
 * 
 * Refers to the elevator that adjusts the height of the arm
 *   
 * MOTORS ===========
 * Kraken X60 (2x)
 * 
 * SENSORS ==========
 * 
 * 
 * 
 */
public class ElevatorSubsystem extends SubsystemBase {
    private static TalonFX m_left = new TalonFX(ElevatorConstants.LEFT_MOTOR_ID); //Moves the left chain
    private static TalonFX m_right = new TalonFX(ElevatorConstants.RIGHT_MOTOR_ID); //Moves the right chain
    private static MotionMagicConfigs motionMagicLeft;
    private static MotionMagicConfigs motionMagicRight;
    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------

    public ElevatorSubsystem() {
        TalonFXConfiguration m_left_config = new TalonFXConfiguration();
        TalonFXConfiguration m_right_config = new TalonFXConfiguration();

        var leftSlot0Config = m_left_config.Slot0;
        var rightSlot0Config = m_right_config.Slot0;

        leftSlot0Config.kS = ElevatorConstants.LEFT_kS; 
        leftSlot0Config.kV = ElevatorConstants.LEFT_kV;
        leftSlot0Config.kA = ElevatorConstants.LEFT_kA; 
        leftSlot0Config.kP = ElevatorConstants.LEFT_kP; 
        leftSlot0Config.kI = ElevatorConstants.LEFT_kI; 
        leftSlot0Config.kD = ElevatorConstants.LEFT_kD;

        rightSlot0Config.kS = ElevatorConstants.RIGHT_kS; 
        rightSlot0Config.kV = ElevatorConstants.RIGHT_kV;
        rightSlot0Config.kA = ElevatorConstants.RIGHT_kA; 
        rightSlot0Config.kP = ElevatorConstants.RIGHT_kP; 
        rightSlot0Config.kI = ElevatorConstants.RIGHT_kI; 
        rightSlot0Config.kD = ElevatorConstants.RIGHT_kD;

        motionMagicLeft = m_left_config.MotionMagic;
        motionMagicRight = m_right_config.MotionMagic;

        m_left.getConfigurator().apply(m_left_config);
        m_right.getConfigurator().apply(m_right_config);
    }
    private static ElevatorSubsystem instance = null;

    public static ElevatorSubsystem getInstance() {
        if (instance == null)
            instance = new ElevatorSubsystem();

        return instance;
    }

 
    public static void moveDown() {
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicLeft.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        motionMagicRight.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicRight.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        
        //Calculate rotations needed to move up to the next level

        //Create Request to Motors
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);

        //TODO: 100 is a placeholder until we get calculated rotations.
        m_left.setControl(m_request.withPosition(100)); 
        m_right.setControl(m_request.withPosition(100));
    }

    public static void moveUp() {
        
        //Set Velocity and Acceleration
        motionMagicLeft.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicLeft.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 
        motionMagicRight.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY_DOWN; 
        motionMagicRight.MotionMagicAcceleration = ElevatorConstants.ACCELERATION_DOWN; 

        //Calculate rotations needed to move down to the next level

        //Create Request to Motors
        final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
        m_left.setControl(m_request.withPosition(-100));
        m_right.setControl(m_request.withPosition(-100));
        
    }
}