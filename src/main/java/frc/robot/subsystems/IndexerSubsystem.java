package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix6.hardware.TalonFX;

public class IndexerSubsystem implements Subsystem {
    private final DigitalInput m_beamBreak;
    
    public IndexerSubsystem(DigitalInput beamBreak) {
        m_beamBreak = beamBreak;
    }
    
    public boolean isBeamBroken() {
        return !m_beamBreak.get(); // Typically beam breaks return false when broken
    }    
}
