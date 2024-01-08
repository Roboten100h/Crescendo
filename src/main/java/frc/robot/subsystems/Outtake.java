// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Outtake extends SubsystemBase {

  private final CANSparkMax m_leftOuttakeMotor = new CANSparkMax(Constants.Outtake.leftOuttakeMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_rightOuttakeMotor = new CANSparkMax(Constants.Outtake.rightOuttakeMotorPort, MotorType.kBrushless);

  private final CANSparkMax m_feederMotor = new CANSparkMax(Constants.Outtake.feederMotorPort, MotorType.kBrushless);

  private double m_shootingSpeed = Constants.Outtake.shootingSpeed;
  private double m_feedingSpeed = Constants.Outtake.feedingSpeed;

  private double m_currentFeedingSpeed = m_feedingSpeed;

  /** Creates a new Outtake. */
  public Outtake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Feeder speed", m_currentFeedingSpeed);
  }

  public void setFeederSpeed(double feedSpeed) {
    m_currentFeedingSpeed = feedSpeed;
    m_feederMotor.set(feedSpeed);
  }

  public void startShooter() {
    m_leftOuttakeMotor.set(-m_shootingSpeed);
    m_rightOuttakeMotor.set(m_shootingSpeed);
  }

  public void stopShooter() {
    m_leftOuttakeMotor.set(0);
    m_rightOuttakeMotor.set(0);
  }

  public void startFeeder() {
    m_currentFeedingSpeed = m_feedingSpeed;
    m_feederMotor.set(m_feedingSpeed);
  }

  public void stopFeeder(){
    m_currentFeedingSpeed = 0;
    m_feederMotor.set(0);
  }
}
