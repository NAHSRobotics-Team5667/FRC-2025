package frc.robot.util;

public class States {
    public enum ElevatorState {
        MOVING,
        LEVEL_1,
        LEVEL_2,
        LEVEL_3,
        LEVEL_4
    } 

    public enum WristState {
        AT_INTAKE,
        AT_REEF,
        AT_PROCESSOR,
        AT_BARGE,
    }

    public enum WheelState {
        INTAKE,
        IDLE,
        OUTTAKE
    }

    public enum IndexerState {
        ENABLED,
        DISABLED
    }

    public enum IntakeState {
        PIVOT_UP,
        PIVOT_DOWN,
        INTAKING,
        IDLE
    }

    public enum BeamBreakState {
        BROKEN,
        NOT_BROKEN
    }
}
