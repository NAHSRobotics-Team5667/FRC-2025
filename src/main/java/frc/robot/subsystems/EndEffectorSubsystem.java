package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

import com.ctre.phoenix6.hardware.TalonFX;

public class EndEffectorSubsystem implements Subsystem {
    private TalonFX m_wristMotor; //This motor will control the wrist on our endeffector.
    private TalonFX m_wheelMotor; //This motor will control the wheels on ou endeffector.

    public EndEffectorSubsystem() {
        
    }
    
}
