// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Mathematic imports from WPILIB.
import static edu.wpi.first.units.Units.*;

//CTRE imports.
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

// PathPlanner imports.
import com.pathplanner.lib.auto.AutoBuilder;

// WPILIB imports.
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import edu.wpi.first.wpilibj.DigitalInput;

// Constants and subsystems import.
import frc.robot.generated.TunerConstants; //Constants for Swerve.
import frc.robot.subsystems.CommandSwerveDrivetrain; //Swerve drivetrain subsystem.

import frc.robot.subsystems.ElevatorSubsystem; //Elevator subsystem.
import frc.robot.subsystems.IndexerSubsystem; //Indexer subsystem.
import frc.robot.subsystems.IntakeSubsystem; //Intake subsystem.
import frc.robot.subsystems.EndEffectorSubsystem; //End effector subsystem.
import frc.robot.subsystems.StateManager; //State manager.

import frc.robot.Constants.OperatorConstants; //Operator constants.
import frc.robot.Constants.IndexerConstants; //Indexer constants.

public class RobotContainer {

    //===========================================================================
    //Swerve Subsystem Configurations/Constants:
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed.
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity.

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * OperatorConstants.GENERAL_DEADBAND)
            .withRotationalDeadband(MaxAngularRate * OperatorConstants.GENERAL_DEADBAND)    //Adds an 10% deadband.
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors.

    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
    private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private final CommandXboxController joystick = new CommandXboxController(0);

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    //===========================================================================
    // OTHER SUBSYSTEMS:

    private static final DigitalInput m_beamBreak = new DigitalInput(IndexerConstants.BEAM_BREAK_PORT_ID); // DIO Port for the Beam Break Sensor.
    
    /**
     * Subsystems:
     * 
     * Note: Subsystems that require beam break sensor should be instantiated after the sensor is created.
     */
    private final IndexerSubsystem m_indexerSubsystem = IndexerSubsystem.getInstance(m_beamBreak);
    private final IntakeSubsystem m_intakeSubsystem = IntakeSubsystem.getInstance(m_beamBreak);
    private final ElevatorSubsystem m_elevatorSubsystem = ElevatorSubsystem.getInstance();
    private final EndEffectorSubsystem m_endEffectorSubsystem = EndEffectorSubsystem.getInstance();
    private final StateManager stateManager = StateManager.getInstance();


    /* Path follower */
    private final SendableChooser<Command> autoChooser;

    //===========================================================================
    //ROBOT CONSTRUCTOR:

    public RobotContainer() {
        autoChooser = AutoBuilder.buildAutoChooser("Testing");
        SmartDashboard.putData("Auto Mode", autoChooser);

        configureBindings();
    }

    //===========================================================================
    //BINDINGS FOR CONTROLS:
    private void configureBindings() {

        /**
         * Driver Controls:
         * - Left Y: Forward/Backward (negative is forward)
         * - Left X: Left/Right (negative is left)
         * - Right X: Clockwise/Counterclockwise (negative is counterclockwise)
         * - A: Brake
         * - B: Point wheels at robot
         * - POV Up: Drive forward at 50% speed
         * - POV Down: Drive backward at 50% speed
         * - Back + Y: Run SysId dynamic forward
         * - Back + X: Run SysId dynamic reverse
         * - Start + Y: Run SysId quasistatic forward
         * - Start + X: Run SysId quasistatic reverse
         * - Left Bumper: Reset field-centric heading
         */

        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
            )
        );

        joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        joystick.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))
        ));

        joystick.pov(0).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(0.5).withVelocityY(0))
        );
        joystick.pov(180).whileTrue(drivetrain.applyRequest(() ->
            forwardStraight.withVelocityX(-0.5).withVelocityY(0))
        );

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // Reset the field-centric heading on left bumper press.
        joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        /* Run the path selected from the auto chooser */
        return autoChooser.getSelected();
    }
}