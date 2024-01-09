// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  private final PWMTalonSRX m_leftClimberMotor = new PWMTalonSRX(Constants.Climber.leftClimberMotorPWMPort);
  private final PWMTalonSRX m_rightClimberMotor = new PWMTalonSRX(Constants.Climber.rightClimberMotorPWMPort);

  private final double m_balancingFactor = 0.3;

  /** Creates a new Climber. */
  public Climber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climb() {
    m_leftClimberMotor.set(-Constants.Climber.climbingSpeed);
    m_rightClimberMotor.set(Constants.Climber.climbingSpeed);
  }

  public void unwind() {
    m_leftClimberMotor.set(Constants.Climber.climbingSpeed);
    m_rightClimberMotor.set(-Constants.Climber.climbingSpeed);
  }

  public void balanceLeft() {
    m_leftClimberMotor.set(m_balancingFactor * -Constants.Climber.climbingSpeed);
    m_rightClimberMotor.set(m_balancingFactor * Constants.Climber.climbingSpeed);

  }
  
  public void balanceRight() {
    m_leftClimberMotor.set(m_balancingFactor * Constants.Climber.climbingSpeed);
    m_rightClimberMotor.set(m_balancingFactor * -Constants.Climber.climbingSpeed);
  }

  public void setClimberSpeed(double speed) {
    m_leftClimberMotor.set(speed);
    m_rightClimberMotor.set(speed);
  }

  public void stopClimbing() {
    m_leftClimberMotor.set(0);
    m_rightClimberMotor.set(0);
  }
}
