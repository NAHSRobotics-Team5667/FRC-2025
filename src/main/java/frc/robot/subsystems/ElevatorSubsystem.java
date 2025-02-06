package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * ElevatorSubsystem.java
 * 
 * Refers to the elevator that adjusts the height of the arm
 * 
 * MOTORS ===========
 * Kraken X44 Brushless
 * 
 * SENSORS ==========
 * 
 */
public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX m_left;
    private TalonFX m_right;
    
    // ========================================================
    // ============= CLASS & SINGLETON SETUP ==================

    // SINGLETON ----------------------------------------------
     private static ElevatorSubsystem instance = null;

     public static ElevatorSubsystem getInstance() {
        if (instance == null)
            instance = new ElevatorSubsystem();

        return instance;
    }

    public static void moveDown() {

    }

    public static void moveUp() {

    }
}