package frc.robot;

public final class Constants {
    
    public static double MaxSpeed = 4.5; //random number, need to get actual value
    public static double MaxAngularRate = 2 * Math.PI; //

    public static final double stickDeadband = 0.15;
    public static final double triggerDeadzone = 0.2;

    public static final class HardwarePorts {
        
        // I am not about to write all the IDs for the Drivetrain motors and cancoders
        // Skip 1 - 12 reserved for drivetrain

        // Shooter
        public static final int shooterTop = 20;
        public static final int shooterBottom = 21;
        // Intake
        // Indexer
    }

    public static final class CurrentLimits {
        // TODO Deduce these values
        public static final double shooterPeak = 0;
        public static final double shooterContinuous = 0;
    }


}
