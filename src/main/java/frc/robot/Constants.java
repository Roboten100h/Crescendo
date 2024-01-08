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
    public static final double regularSpeed = 0.7;
  }

  public static class Outtake {
    public static final int leftOuttakeMotorPort = 6;
    public static final int rightOuttakeMotorPort = 7;
    public static final int feederMotorPort = 5;
    
    public static final double shootingSpeed = 1;
    public static final double feedingSpeed = 1;
  }
}
