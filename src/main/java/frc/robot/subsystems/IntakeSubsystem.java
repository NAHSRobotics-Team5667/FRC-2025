package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.IntakeConstants;


/**
 * IntakeSubsystem.java
 * 
 * Controls the intake subsystem and it's features.
 * 
 * MOTORS ===========
 * 
 * Kraken X60 (1x)
 * 
 * SENSORS ==========
 * 
 * Beam Break (1x)
 * 
 **/

public class IntakeSubsystem extends SubsystemBase {
    //=========================================================================
    //============================= VARIABLES =================================
    private final TalonFX m_pivotMotor;     // This is to drop the actual intake.
    private final TalonFX m_rollerMotor;    // This is to spin the intake wheels.
    private static IntakeSubsystem instance = null;

    // Constants for pivot control
    private static final double kPivotGearRatio = 33.0; // 33:1 gear ratio
    private static final double kPivotTargetAngle = 90.0; // Target pivot angle in degrees

    // PID coefficients
    private static final double kP = 2.4; // Proportional gain
    private static final double kI = 0.0; // Integral gain
    private static final double kD = 0.1; // Derivative gain

    // Control request for position control
    private final PositionVoltage m_positionRequest;

    //=========================================================================
    //============================= CONSTRUCTOR ===============================
    private IntakeSubsystem() {
        m_pivotMotor = new TalonFX(IntakeConstants.PIVOT_MOTOR_ID);
        m_rollerMotor = new TalonFX(IntakeConstants.ROLLER_MOTOR_ID);

        // Configure the Pivot Motor.
        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.Slot0.kP = kP;
        configs.Slot0.kI = kI;
        configs.Slot0.kD = kD;
        configs.Feedback.SensorToMechanismRatio = kPivotGearRatio;
        m_pivotMotor.getConfigurator().apply(configs);

        // Initialize the position control request
        m_positionRequest = new PositionVoltage(0).withSlot(0);

        // Reset encoder position to zero
        m_pivotMotor.setPosition(0);
    }

    /**
     * This method will create an instance of the IntakeSubsystem.
     * @param beamBreak Digital input for the beam break sensor.
     * @return Instance of IntakeSubsystem.
     */
    public static IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    //=========================================================================
    //============================= METHODS ===================================

    /**
     * This method will set the speed of the intake roller motor.
     * @param speed Desired intake speed.
     */
    public void setIntakeSpeed(double speed) {
        m_rollerMotor.set(speed);
    }

    /**
     * This method commands the pivot to move to a position corresponding to a 90-degree rotation.
     */
    public void dropPivot() {
        // Calculate the target position in mechanism rotations
        double targetPosition = kPivotTargetAngle / 360.0;

        // Command the pivot motor to move to the target position
        m_pivotMotor.setControl(m_positionRequest.withPosition(targetPosition));
    }
}