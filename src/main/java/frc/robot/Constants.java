// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveTrain {
    public static final int motorLeftFrontPort = 1;
    public static final int motorLeftRearPort = 2;
    public static final int motorRightFrontPort = 3;
    public static final int motorRightRearPort = 4;

    public static final double slowSpeed = 0.5; 
    public static final double regularSpeed = 0.6;
  }

  public static class Intake {
    public static final int intakeAndConveyorPWMPort = 0;

    public static final double intakeSpeed = 1;
  }

  public static class Climber {
    public static final int leftClimberMotorPWMPort = 1;
    public static final int rightClimberMotorPWMPort = 2;

    public static final double climbingSpeed = 0.8;
  }

  public static class Outtake {
    public static final int leftOuttakeMotorCANPort = 6;
    public static final int rightOuttakeMotorCANPort = 7;
    public static final int wristMotorCANPort = 5;
    public static final int feederMotorCANPort = 8;
    
    public static final double shootingSpeed = 1;
    public static final double feedingSpeed = 1;

    public static final double wristRaisingSpeed = 0.6;
    public static final double wristLoweringSpeed = -0.6;

    public static final double maxWristSpeed = 0.28;

    public static final double kP = 0.05;
    public static final double kI = 0.001;
    public static final double kD = 0;
  }
}
