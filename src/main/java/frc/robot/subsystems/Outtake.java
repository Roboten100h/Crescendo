// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Outtake extends SubsystemBase {

  private final CANSparkMax m_leftOuttakeMotor = new CANSparkMax(Constants.Outtake.leftOuttakeMotorCANPort, MotorType.kBrushless);
  private final CANSparkMax m_rightOuttakeMotor = new CANSparkMax(Constants.Outtake.rightOuttakeMotorCANPort, MotorType.kBrushless);

  private final VictorSPX m_feederMotor = new VictorSPX(Constants.Outtake.feederMotorCANPort);


  private final CANSparkMax m_wristMotor = new CANSparkMax(Constants.Outtake.wristMotorCANPort, MotorType.kBrushless);

  private final SparkPIDController m_pidController = m_wristMotor.getPIDController();
  private final RelativeEncoder m_relativeEncoder = m_wristMotor.getEncoder();
  private double m_targetRotation = 0;

  private double m_shootingSpeed = Constants.Outtake.shootingSpeed;
  private double m_feedingSpeed = Constants.Outtake.feedingSpeed;

  private double m_currentFeedingSpeed = m_feedingSpeed;

  /** Creates a new Outtake. */
  public Outtake() {
    m_wristMotor.restoreFactoryDefaults();
    m_wristMotor.setSmartCurrentLimit(20);

    m_relativeEncoder.setPosition(0.0);
    m_pidController.setP(Constants.Outtake.kP);
    m_pidController.setI(Constants.Outtake.kI);
    m_pidController.setD(Constants.Outtake.kD);

    m_wristMotor.burnFlash();

    m_wristMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Feeder speed", m_currentFeedingSpeed);
    SmartDashboard.putNumber("WristPosition", m_wristMotor.getEncoder().getPosition());
  }

  public void goToPosition(double targetPosition) {
    m_pidController.setReference(targetPosition, ControlType.kPosition);
  }

  public void stopWristAtCurrentPosition() {
    m_pidController.setReference(m_relativeEncoder.getPosition(), ControlType.kPosition);
  }

  public void raiseWrist() {
    m_wristMotor.set(Constants.Outtake.wristRaisingSpeed);
  }

  public void lowerWrist() {
    m_wristMotor.set(Constants.Outtake.wristLoweringSpeed);
  }

  public void setWristSpeed(double speed) {
    m_wristMotor.set(speed);
  }

  public void stopWrist() {
    m_wristMotor.set(0);
  }

  public void startShooterAmp() {
    m_leftOuttakeMotor.set(-0.2 * m_shootingSpeed);
    m_rightOuttakeMotor.set(0.2 * m_shootingSpeed);
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
    m_feederMotor.set(VictorSPXControlMode.PercentOutput, m_feedingSpeed);
  }

  public void setFeederSpeed(double feedSpeed) {
    m_currentFeedingSpeed = feedSpeed;
    m_feederMotor.set(VictorSPXControlMode.PercentOutput, feedSpeed);
  }

  public void stopFeeder(){
    m_currentFeedingSpeed = 0;
    m_feederMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }
}
