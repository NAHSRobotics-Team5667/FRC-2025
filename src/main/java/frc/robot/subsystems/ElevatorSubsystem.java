package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

import frc.robot.Constants.ElevatorConstants;

import com.ctre.phoenix6.hardware.TalonFX;

public class ElevatorSubsystem implements Subsystem {
    private TalonFX m_controlMotor1; //This will hold the new rise motor.
    private TalonFX m_controlMotor2; //This will hold the new rise motor.

    public ElevatorSubsystem() {
        m_controlMotor1 = new TalonFX(ElevatorConstants.CONTROL_MOTOR_1_ID);
        m_controlMotor2 = new TalonFX(ElevatorConstants.COnTROL_MOTOR_2_ID);
    }
}
