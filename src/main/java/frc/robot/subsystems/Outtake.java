// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Outtake extends SubsystemBase {

  private final CANSparkMax m_leftOuttakeMotor = new CANSparkMax(6, MotorType.kBrushless);
  private final CANSparkMax m_rightOuttakeMotor = new CANSparkMax(5, MotorType.kBrushless);


  private double m_shootingSpeed = 1;


  /** Creates a new Outtake. */
  public Outtake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command ShootOut() {
    return this.runEnd(() -> {
      System.out.println("Shooting out");
      m_leftOuttakeMotor.set(m_shootingSpeed);
      m_rightOuttakeMotor.set(-m_shootingSpeed);
    }, 
    () -> {
      m_leftOuttakeMotor.set(0);
      m_rightOuttakeMotor.set(0);
    });
  }

    public Command ShootOutInverted() {
    return this.runEnd(() -> {
      System.out.println("Shooting out - inverted");
      m_leftOuttakeMotor.set(-m_shootingSpeed);
      m_rightOuttakeMotor.set(m_shootingSpeed);
    }, 
    () -> {
      m_leftOuttakeMotor.set(0);
      m_rightOuttakeMotor.set(0);
    });
  }

}
