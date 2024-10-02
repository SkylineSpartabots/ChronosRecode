package frc.robot;

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

        public static final int intakeM = 11;

        public static final int leftIndexerM = 21;
        public static final int rightIndexerM = 22;

        public static final int leftShooterM = 31;
        public static final int rightShooterM = 32;
        
        // public static final int hoodM = 41;
    }

    public static final class CurrentLimits {

        public static final int intakeContinuousCurrentLimit = 0;
        public static final int intakePeakCurrentLimit = 0;

    }
}
