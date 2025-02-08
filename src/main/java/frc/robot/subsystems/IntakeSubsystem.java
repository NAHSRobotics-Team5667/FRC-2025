package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * IntakeSubsystem.java
 * 
 * Refers to the robot's intake mechanism
 * 
 * MOTORS ===========
 * Kraken X44 Brushless
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
}
