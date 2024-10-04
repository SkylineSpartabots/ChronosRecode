package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    
    public static double MaxSpeed = 4.5; //random number, need to get actual value
    public static double MaxAngularRate = 2 * Math.PI; //

    public static final double stickDeadband = 0.15;
    public static final double triggerDeadzone = 0.2;

    public static final class HardwarePorts {
        
        public static final int frontLeftSteer = 1;
        public static final int frontLeftDrive = 2;

        public static final int backLeftSteer = 3;
        public static final int backLeftDrive = 4;

        public static final int backRightSteer = 5;
        public static final int backRightDrive = 6;

        public static final int frontRightSteer = 7;
        public static final int frontRightDrive = 8;

        public static final int frontLeftCancoder = 1;
        public static final int backLeftCancoder = 2;
        public static final int backRightCancoder = 3;
        public static final int frontRightCancoder = 4;


        public static final int intake = 11;

        public static final int leftIndexer = 21;
        public static final int rightIndexer = 22;

        public static final int leftShooter = 31;
        public static final int rightShooter = 32;
        
        // public static final int hoodM = 41;
    }

    public static final class CurrentLimits {

        public static final int intakeContinuousCurrentLimit = 0;
        public static final int intakePeakCurrentLimit = 0;
    }
}

