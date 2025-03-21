package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private DigitalInput bottomSwitch;
    private DigitalInput topSwitch;

    private boolean topWasHit; //This is incase the robot does hit, it will stay permanent, as opposed to reseting every periodic run.
    private boolean bottomWasHit;

    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // CONSTRUCTOR ----------------------------------------------

    public ElevatorSubsystem() {

        m_left = new TalonFX(ElevatorConstants.LEFT_MOTOR_ID); //Sets the motor ID for the left motor.
        m_right = new TalonFX(ElevatorConstants.RIGHT_MOTOR_ID); //Sets the motor ID for the right motor.

        motionMagicLeft = new MotionMagicConfigs(); //Creates a new Motion Magic Config for the left motor.
        motionMagicRight = new MotionMagicConfigs(); //Creates a new Motion Magic Config for the right motor.

        bottomSwitch = new DigitalInput(ElevatorConstants.BOTTOM_SWITCH_ID); //Creates a hard limit switch for the elevator.
        topSwitch = new DigitalInput(ElevatorConstants.TOP_SWITCH_ID); //Creates a hard limit switch for the elevator.
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
     * Resets the elevator back down to the starting position.
     */
    public void reset() {
        //TODO: Implement reset functionality.
        // There's no "soft limit" sensor to get this data, so this will need to be solved.
    }


    public boolean isBottomSwitchHit() {
        return !bottomSwitch.get();
    }

    public boolean isTopSwitchHit() {
        return !topSwitch.get();
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
        double angle = m_left.getPosition().getValueAsDouble(); //Only get the position of one motor - Both motors SHOULD have the same position
        return (Units.degreesToRotations(angle)*ElevatorConstants.WHEEL_RADIUS)/ElevatorConstants.GEAR_RATIO;
    }

    public boolean isMoving() {
        if (m_left.get() > 0 || m_right.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Stops all motors.
     */
    public void stopAllMotors() {
        m_left.set(0);
        m_right.set(0);
    }

    public void periodic() {
        SmartDashboard.putBoolean("Bottom Switch", isBottomSwitchHit());
        SmartDashboard.putBoolean("Top Switch", isTopSwitchHit());

        SmartDashboard.putNumber("Elevator Position", getPosition());
        SmartDashboard.putBoolean("Is Elevator Moving", isMoving());

        SmartDashboard.putBoolean("Was Bottom Hit?", bottomWasHit);
        SmartDashboard.putBoolean("Was Top Hit?", topWasHit);

        // These hard limits are in place to prevent damage to the elevator.
        if (isBottomSwitchHit() && bottomWasHit == false) {
            bottomWasHit = true;
        }

        if (isTopSwitchHit() && topWasHit == false) {
            topWasHit = true;
        }
    }
}