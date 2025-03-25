package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private TalonFX m_climb;
    private MotionMagicConfigs MMconfigs;

    //==============================================================================
    //========================= SINGLETON AND CONSTRUCTOR ==========================

    public ClimberSubsystem() {
        m_climb = new TalonFX(ClimberConstants.CLIMB_MOTOR_ID);

        MMconfigs = new MotionMagicConfigs();
        MMconfigs.MotionMagicCruiseVelocity = ClimberConstants.VELOCITY;
        MMconfigs.MotionMagicAcceleration = ClimberConstants.ACCELERATION;

        m_climb.getConfigurator().apply(MMconfigs);
    }

    private static ClimberSubsystem instance = null;

    public static ClimberSubsystem getInstance() {
        if (instance == null)
            instance = new ClimberSubsystem();

        return instance;
    }

    //==============================================================================
    //================================= METHODS ====================================

    /**
     * This method will move the climber to a desired position.
     * @param targetRotations
     */
    public void moveClimber(double targetRotations) {
        final MotionMagicVoltage climbRequest = new MotionMagicVoltage(0);
        m_climb.setControl(climbRequest.withPosition(targetRotations));
    }

    /**
     * This method will stop the climber.
     */
    public void stopClimber() {
        m_climb.set(0);
    }

    /**
     * This method will get the climber's position in rotations.
     * @return The climber's position.
     */
    public double getClimberPosition() {
        return m_climb.getPosition().getValue().in(Rotations);
    }

    public boolean isMoving() {
        if (m_climb.get() > 0) {
            return true;
        } else {
            return false;
        }
    }



}
