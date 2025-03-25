package frc.robot.util;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.StatusSignal;

/**
 * This is a testing method for seeing if we can control motor data and reduce CAN utilization.
 */
public class ProcessedMotor extends TalonFX {

    public ProcessedMotor(int deviceID) {
        super(deviceID);

        // Create a configuration object.
        TalonFXConfiguration config = new TalonFXConfiguration();

        // Disable sending extra information on the CANBus.
        config.Slot0.kP = 0;
        config.Slot0.kI = 0;
        config.Slot0.kD = 0;
    }
}
