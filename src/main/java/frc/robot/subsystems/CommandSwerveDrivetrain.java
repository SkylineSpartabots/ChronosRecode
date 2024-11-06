package frc.robot.subsystems;

import java.sql.Driver;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;
import org.opencv.core.Point;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.mechanisms.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.generated.TunerConstants;
import frc.robot.RobotContainer;


/**
 * Class that extends the Phoenix SwerveDrivetrain class and implements subsystem
 * so it can be used in command-based projects easily.
 */
public class CommandSwerveDrivetrain extends SwerveDrivetrain implements Subsystem {
    private static final double kSimLoopPeriod = 0.005; // 5 ms
    private Notifier m_simNotifier = null;
    private double m_lastSimTime;

    private double deadbandFactor = 0.1;

    double translationDeadband = 0.1;
    double rotDeadband = 0.05;

    private static CommandSwerveDrivetrain s_Swerve = TunerConstants.DriveTrain;

    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric();

    PIDController pidHeading = new PIDController(8, 0, 1);

    private Field2d m_field = new Field2d();

    public static CommandSwerveDrivetrain getInstance(){
        if(s_Swerve == null){
            s_Swerve = new CommandSwerveDrivetrain(TunerConstants.DrivetrainConstants,
             TunerConstants.FrontLeft,TunerConstants.FrontRight,TunerConstants.BackLeft,TunerConstants.BackRight);
        }
        return s_Swerve;
    }

    private void limit() {
        for (SwerveModule module : Modules) {
            CurrentLimitsConfigs configs = new CurrentLimitsConfigs();
            configs.SupplyCurrentLimit = 20;
            configs.SupplyCurrentLimitEnable = true;
            configs.StatorCurrentLimit = 40;
            configs.StatorCurrentLimitEnable = true;
            
            module.getDriveMotor().getConfigurator().apply(configs);
            module.getSteerMotor().getConfigurator().apply(configs);
        }
    }

    public CommandSwerveDrivetrain(SwerveDrivetrainConstants driveTrainConstants, double OdometryUpdateFrequency, SwerveModuleConstants... modules) {
        super(driveTrainConstants, OdometryUpdateFrequency, modules); //look here for parent library methods
        limit();
    }

    public CommandSwerveDrivetrain(SwerveDrivetrainConstants driveTrainConstants, SwerveModuleConstants... modules) {
        super(driveTrainConstants, modules);
        limit();
    }

    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {
        return run(() -> this.setControl(requestSupplier.get()));
    }

    public SwerveRequest drive(double driverLX, double driverLY, double driverRX) {
        return new SwerveRequest.FieldCentric()
        .withVelocityX(scaledDeadBand(driverLX))
        .withVelocityY(scaledDeadBand(driverLY))
        .withRotationalRate(scaledDeadBand(driverRX) * Constants.MaxAngularRate)
        .withDeadband(Constants.MaxSpeed * 0.05)
        .withRotationalDeadband(Constants.MaxAngularRate * 0.05)
        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    }

    public double scaledDeadBand(double input) {
        return (2 * (deadbandFactor * Math.pow(input, 3)) + (1 - deadbandFactor) * input);
    }

    public void resetOdo(Pose2d pose){
        resetOdoUtil(pose);
    }

    public void resetOdoUtil(Pose2d pose){
        try {
            m_stateLock.writeLock().lock();

            for (int i = 0; i < ModuleCount; ++i) {
                Modules[i].resetPosition();
                m_modulePositions[i] = Modules[i].getPosition(true);
            }
            m_odometry.resetPosition(Rotation2d.fromDegrees(m_yawGetter.getValue()), m_modulePositions, pose);
        } finally {
            m_stateLock.writeLock().unlock();
        }
    }

    private Pose2d autoStartPose = new Pose2d(2.0, 2.0, new Rotation2d());

    public void setAutoStartPose(Pose2d pose){
        autoStartPose = pose;
    }

    @AutoLogOutput(key = "Swerve/States/Measured")
    private SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (int i = 0; i < 4; i++) {
            states[i] = Modules[i].getCurrentState();
        }
        return states;
    }

    @AutoLogOutput(key = "Swerve/Odometry/Pose")
    public Pose2d getPose(){
        return s_Swerve.m_odometry.getEstimatedPosition();
    }

    @Override
    public void periodic() {
        }
    }
