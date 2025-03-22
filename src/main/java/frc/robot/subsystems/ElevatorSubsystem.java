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

    /**
     * This will stop all running motors. Can be used in emergencies.
     */
    public void stopAllMotors() {
        m_controlMotor1.set(0); // Stop the first motor.
        m_controlMotor2.set(0); // Stop the second motor.
    }

    /**
     * This will get speeds for motor number 1.
     */
    public double getMotor1Speed() {
        return m_controlMotor1.get();
    }

    /**
     * This will get speeds for motor number 2.
     */
    public double getMotor2Speed() {
        return m_controlMotor2.get();
    }

    /**
     * This will set the motor speed for motor number 1.
     */
    public void setMotor1Speed(double speed) {
        m_controlMotor1.set(speed);
    }

    /**
     * This will set the motor speed for motor number 2.
     */
    public void setMotor2Speed(double speed) {
        m_controlMotor2.set(speed);
    }

    public void periodic(){
        
    }
}
