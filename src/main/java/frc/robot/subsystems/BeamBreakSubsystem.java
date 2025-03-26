package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;
import edu.wpi.first.wpilibj.DigitalInput;

public class BeamBreakSubsystem extends SubsystemBase {
    private DigitalInput m_beamBreak;
    private static BeamBreakSubsystem instance = null;

    public BeamBreakSubsystem() {
        m_beamBreak = new DigitalInput(IndexerConstants.BEAM_BREAK_PORT_ID);
    }

    public static BeamBreakSubsystem getInstance() {
        if (instance == null) {
            instance = new BeamBreakSubsystem();
        }
        return instance;
    }

    
    /**
     * This method will check the status of the beam break.
     * @return Beam Break Status.
     */
    public boolean isBeamBroken() {
        return !m_beamBreak.get();
    }
}
