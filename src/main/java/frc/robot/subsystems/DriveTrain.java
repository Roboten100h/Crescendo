// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private final CANSparkMax m_motorLeftFront = new CANSparkMax(Constants.DriveTrain.motorLeftFrontPort, MotorType.kBrushed);
  private final CANSparkMax m_motorLeftRear = new CANSparkMax(Constants.DriveTrain.motorLeftRearPort, MotorType.kBrushed);
  private final CANSparkMax m_motorRightFront = new CANSparkMax(Constants.DriveTrain.motorRightFrontPort, MotorType.kBrushed);
  private final CANSparkMax m_motorRightRear = new CANSparkMax(Constants.DriveTrain.motorRightRearPort, MotorType.kBrushed);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_motorLeftFront, m_motorRightFront);

  private double m_currentMaxSpeed = Constants.DriveTrain.regularSpeed;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    m_motorLeftFront.setInverted(true);

    m_motorLeftRear.follow(m_motorLeftFront);
    m_motorRightRear.follow(m_motorRightRear);
  }

  public Command enableSpeedLimiter() {
    return this.runOnce(() -> {
      m_currentMaxSpeed = Constants.DriveTrain.slowSpeed;
    });
  }

  public Command enableRegularSpeed() {
    return this.runOnce(() -> {
      m_currentMaxSpeed = Constants.DriveTrain.regularSpeed;
    });
  }

  public void driveArcade(double move, double rotate) {
    m_drive.arcadeDrive(m_currentMaxSpeed * move, m_currentMaxSpeed * rotate, true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
