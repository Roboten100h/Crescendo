// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeConveyor extends SubsystemBase {

    private final PWMTalonSRX m_intakeAndConveyorMotor = new PWMTalonSRX(Constants.Intake.intakeAndConveyorPWMPort);

  /** Creates a new IntakeConveyor. */
  public IntakeConveyor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntakeSpeed(double speed) {
    m_intakeAndConveyorMotor.set(speed);
  }

  public void startIntake () {
    m_intakeAndConveyorMotor.set(Constants.Intake.intakeSpeed);
  }

  public void startIntakeInverted() {
    m_intakeAndConveyorMotor.set(-Constants.Intake.intakeSpeed);
  }

  public void stopMotor() {
    m_intakeAndConveyorMotor.set(0);
  }
}
