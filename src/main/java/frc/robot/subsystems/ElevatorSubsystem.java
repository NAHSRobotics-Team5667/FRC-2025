package frc.robot.subsystems;

/**
 * ElevatorSubsystem.java
 * 
 * Refers to the elevator that adjusts the height of the end effector
 *   
 * MOTORS ===========
 * Kraken X60 (2x)
 * 
 */
//CTRE Imports
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.Follower;

//WPILib Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static edu.wpi.first.units.Units.Rotations;

//Local Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;


public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX m_left;
    private TalonFX m_right;
    private Follower m_follower;
    private MotionMagicConfigs MMconfigs;

    public ElevatorSubsystem() {
        m_left = new TalonFX(ElevatorConstants.LEFT_MOTOR_ID);
        m_right = new TalonFX(ElevatorConstants.RIGHT_MOTOR_ID); //This will be the follower.
        
        MMconfigs = new MotionMagicConfigs();

        MMconfigs.MotionMagicCruiseVelocity = ElevatorConstants.VELOCITY;
        MMconfigs.MotionMagicAcceleration = ElevatorConstants.ACCELERATION;

        m_left.getConfigurator().apply(MMconfigs);

        //This will make the right motor follow the outputs of the left motor.
        m_follower = new Follower(ElevatorConstants.LEFT_MOTOR_ID, false);
        m_right.setControl(m_follower);
    }

    private static ElevatorSubsystem instance = null;

    public static ElevatorSubsystem getInstance() {
        if (instance == null)
            instance = new ElevatorSubsystem();

        return instance;
    }

    /**
     * This method will move the elevator to a desired position.
     * @param targetPosition
     */
    public void moveElevator(double targetPosition) {
        final MotionMagicVoltage request = new MotionMagicVoltage(0);
        m_left.setControl(request.withPosition(targetPosition));
    }

    /**
     * This method will get the elevator's position.
     * @param targetPosition
     */
    public double getElevatorPosition() {
        double rotations = m_left.getPosition().getValue().in(Rotations);
        return (rotations * ElevatorConstants.WHEEL_RADIUS * 2 * Math.PI)/ElevatorConstants.GEAR_RATIO;
    }

    public double metersToRotations(double meters) {
        return (meters/(2*Math.PI*ElevatorConstants.WHEEL_RADIUS)*ElevatorConstants.GEAR_RATIO);
    }

    /*
     * This method will check if the elevator is currently in motion.
     */
    public boolean isMoving() {
        if (m_left.get() > 0 && m_right.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method will stop the elevator motors.
     */
    public void stop() {
        m_left.set(0);
        m_right.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator Position", getElevatorPosition());
        SmartDashboard.putBoolean("Elevator Moving", isMoving());
    }
}