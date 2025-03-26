package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.IndexerConstants;
import com.ctre.phoenix6.hardware.TalonFX;

/**
 * InderSubsystem.java
 * 
 * Refers to the robot's index
 * 
 * MOTORS ===========
 * 
 * Kraken X60 (1x)
 * 
 * SENSORS ==========
 * 
 * Beam Break (1x)
 * 
 **/

public class IndexerSubsystem implements Subsystem {
    private final TalonFX m_indexer;
    private static IndexerSubsystem instance = null;
    
    //Constructor
    public IndexerSubsystem() {
        m_indexer = new TalonFX(IndexerConstants.INDEXER_ID);
    }

    /**
     * This method will create an instance of the IntakeSubsystem.
     * @param beamBreak
     * @return Instance
     */
    public static IndexerSubsystem getInstance() {        
        if (instance == null) {
            instance = new IndexerSubsystem();
        }
        return instance;
    }


    /**
     * This method sets the speed of the indexer motor
     * @param speed Desired Motor Speed
     */
    public void setSpeed(double speed) {
        m_indexer.set(speed);
    }

    /**
     * This method gets the speed of the indexer motor
     * @return Motor Speed
     */
    public double getSpeed() {
        return m_indexer.get();
    }

    /**
     * This method stops the indexer motor
     */
    public void stopMotor() {
        m_indexer.set(0);
    }
}