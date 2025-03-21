package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/**
 * IntakeSubsystem.java
 * 
 * Refers to the robot's intake mechanism
 * 
 * MOTORS
 * ======
 * Kraken X44 (1x) - Wheels
 * Kraken X60 (1x) - Pivot - Gear RATIO: 9:1
 * 
 * TODO: There is a pinion gear, then a driven gear, followed by sprocket ratio (small then large).
 * 
 * Assuming 90 degree turn.
 */
public class IntakeSubsystem extends SubsystemBase {
    private TalonFX m_wheel; // This motor controls the intake wheels.
    private TalonFX m_pivot; // This motor controls the pivot that rotates.

    // Singleton setup
    private static IntakeSubsystem instance = null;

    public static IntakeSubsystem getInstance() {
        if (instance == null)
            instance = new IntakeSubsystem();
        return instance;
    }

    public IntakeSubsystem() {
        m_wheel = new TalonFX(IntakeConstants.INTAKE_WHEEL_ID);
        m_pivot = new TalonFX(IntakeConstants.INTAKE_PIVOT_ID);

        // Configure the pivot motor
        TalonFXConfiguration pivotConfig = new TalonFXConfiguration();
        pivotConfig.Slot0.kP = 1.0; // TODO: Proportional gain; tune as necessary
        pivotConfig.Slot0.kI = 0.0; // TODO: Integral gain; tune as necessary
        pivotConfig.Slot0.kD = 0.0; // TODO: Derivative gain; tune as necessary
        pivotConfig.Slot0.kV = 0.0; // TODO: Feedforward gain; tune as necessary
        pivotConfig.Slot0.kS = 0.0; // TODO: Static gain; tune as necessary
        m_pivot.getConfigurator().apply(pivotConfig);

        // Set the pivot motor to brake mode for precise position control.
        m_pivot.setNeutralMode(NeutralModeValue.Brake);
    }

    /**
     * Sets the intake wheel speed.
     * 
     * @param speed Percent output of intake motor (0 - 1).
     */
    public void setWheelMotor(double speed) {
        double motorSpeed = speed;
        m_wheel.set(motorSpeed);
    }

    /**
     * Sets the intake pivot speed.
     * 
     * @param speed Percent output of pivot motor (0 - 1).
     */
    public void setPivotMotor(double speed) {
        double motorSpeed = speed;
        m_pivot.set(motorSpeed);
    }

    public double getWheelSpeed() {
        return m_wheel.get();
    }

    public double getPivotSpeed() {
        return m_pivot.get();
    }

    public void stopWheelMotor() {
        m_wheel.set(0);
    }

    public void stopPivotMotor() {
        m_pivot.set(0);
    }

    /**
     * Rotates the intake pivot to the upward position (0 degrees).
     */
    public void rotatePivotUp() {
        // Position in encoder counts for 0 degrees
        double targetPosition = 0.0;
        PositionVoltage positionCommand = new PositionVoltage(targetPosition);
        m_pivot.setControl(positionCommand);
    }

    /**
     * Rotates the intake pivot to the downward position (90 degrees).
     */
    public void rotatePivotDown() {
        // Position in encoder counts for 90 degrees
        double targetPosition = 4608.0; // 2.25 rotations * 2048 CPR
        PositionVoltage positionCommand = new PositionVoltage(targetPosition);
        m_pivot.setControl(positionCommand);
    }

    /**
     * Checks if the pivot is at the specified position.
     * 
     * @param targetPosition The target position in degrees.
     * @return true if the pivot is at the target position, false otherwise.
     */
    public boolean isPivotAtPosition(double targetPosition) {
        double currentPosition = m_pivot.getPosition().getValueAsDouble();
        return Math.abs(currentPosition - targetPosition) < 100; // Adjust tolerance as needed.
    }
}
