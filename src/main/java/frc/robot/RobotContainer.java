// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.Autos;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeConveyor;
import frc.robot.subsystems.Outtake;

import static edu.wpi.first.wpilibj2.command.Commands.runOnce;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final Outtake m_outtakeSubsystem = new Outtake();
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Climber m_Climber = new Climber();
  private final IntakeConveyor m_IntakeConveyor = new IntakeConveyor();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveTrain.setDefaultCommand(new RunCommand(() -> {
      double move = m_driverController.getLeftY();
      double turn = m_driverController.getRightX();

      m_driveTrain.driveArcade(move, turn);
    }, m_driveTrain));
   
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    m_driverController.rightBumper()
      .onTrue(runOnce(() -> m_outtakeSubsystem.startFeeder()))
      .onFalse(runOnce(() -> m_outtakeSubsystem.stopFeeder()));
    
    m_driverController.leftBumper()
      .onTrue(runOnce(() -> m_outtakeSubsystem.startShooter()))
      .onFalse(runOnce(() -> m_outtakeSubsystem.stopShooter()));

    m_driverController.rightStick().onTrue(runOnce(() -> m_driveTrain.toggleDirection()));

    // m_driverController.leftStick().toggleOnTrue(new StartEndCommand(
    //   () -> m_driveTrain.invertDrivetrain(),
    //   () -> m_driveTrain.regularDirection(),
    //   m_driveTrain));

    m_driverController.leftStick().onTrue(runOnce(() -> m_driveTrain.toggleDirection()));

    m_driverController.povDown()
      .onTrue(runOnce(() -> m_IntakeConveyor.startIntake()))
      .onFalse(runOnce(() -> m_IntakeConveyor.stopMotor()));
    
    m_driverController.povUp()
      .onTrue(runOnce(() -> {
        m_IntakeConveyor.startIntakeInverted();
        m_outtakeSubsystem.startFeeder();
      } ))
      .onFalse(runOnce(() -> {
        m_IntakeConveyor.stopMotor();
        m_outtakeSubsystem.stopFeeder();
      }));

    m_driverController.povRight()
      .onTrue(runOnce(() -> m_outtakeSubsystem.startShooterAmp()))
      .onFalse(runOnce(() -> m_outtakeSubsystem.stopShooter()));

    m_driverController.a()
      .onTrue(runOnce(() -> m_Climber.climb()))
      .onFalse(runOnce(() -> m_Climber.stopClimbing()));

    m_driverController.y()
      .onTrue(runOnce(() -> m_Climber.unwind()))
      .onFalse(runOnce(() -> m_Climber.stopClimbing()));

    m_driverController.rightTrigger(0.2)
      .onTrue(runOnce(() -> m_outtakeSubsystem.setWristSpeed(m_driverController.getRightTriggerAxis())))
      .onFalse(runOnce(() -> m_outtakeSubsystem.stopWrist()));

    m_driverController.leftTrigger(0.2)
      .onTrue(runOnce(() -> m_outtakeSubsystem.setWristSpeed(-m_driverController.getLeftTriggerAxis())))
      .onFalse(runOnce(() -> m_outtakeSubsystem.stopWrist()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
