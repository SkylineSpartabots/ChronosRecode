package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    
    public static double MaxSpeed = 4.5; //random number, need to get actual value
    public static double MaxAngularRate = 2 * Math.PI; //

    public static final double stickDeadband = 0.15;
    public static final double triggerDeadzone = 0.2;

    public static final class HardwarePorts {
        
        public static final int intakeTopM = 0;
        public static final int indexerM = 0;
        
        public static final int intakeM = 0;

    }

    public static final class CurrentLimits {

        public static final int intakeContinuousCurrentLimit = 0;
        public static final int intakePeakCurrentLimit = 0;

    }
  
    public static final class colorSensor {
        public static final Color ColorSensorBlueIntake = new Color(0.19,0.43, 0.37);
        public static final Color ColorSensorRedIntake = new Color(0.44, 0.38, 0.16);
    }
}

