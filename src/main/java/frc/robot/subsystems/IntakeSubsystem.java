package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/**
 * IntakeSubsystem.java
 * 
 * Refers to the robot's intake mechanism
 * 
 * MOTORS ===========
 * Kraken X44
 * 
 * SENSORS ==========
 * 
 **/
public class IntakeSubsystem extends SubsystemBase {
    private TalonFX m_intake;
   
    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------
     private static IntakeSubsystem instance = null;

     public static IntakeSubsystem getInstance() {
        if (instance == null)
            instance = new IntakeSubsystem();

        return instance;
    }

    public IntakeSubsystem() {
        m_intake = new TalonFX(IntakeConstants.INTAKE_ID);
 
    }

    // ========================================================
    // ====================== ACTIONS =========================

    /**
     * Sets relative velocity of the intake.
     * @param speed % output of intake motor. 0-100.
     */
    public void set(double speed) {
        double motorSpeed = speed/100;
        m_intake.set(motorSpeed);
    }

    public double getSpeed() {
        return m_intake.get();
    }

    public void stopMotor() {
        m_intake.set(0);
    }
}
