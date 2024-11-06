// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CommandFactory;
import frc.robot.commands.SetIndexer;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetShooter;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.subsystems.Intake.IntakeState;

public class RobotContainer {

  private final CommandSwerveDrivetrain drivetrain = CommandSwerveDrivetrain.getInstance(); // Drivetrain

  double translationDeadband = 0.1;
  double rotDeadband = 0.1;

  private static RobotContainer container;
  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController driver = new CommandXboxController(0); // Driver joystick

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
  .withDeadband(Constants.MaxSpeed * translationDeadband)
  .withRotationalDeadband(Constants.MaxAngularRate * rotDeadband)
  .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
  
    // driving in open loop
    /* Driver Buttons */
    private final Trigger driverBack = driver.back();
    // private final Trigger driverStart = driver.start();
    // private final Trigger driverA = driver.a();
    // private final Trigger driverB = driver.b();
    // private final Trigger driverX = driver.x();
    // private final Trigger driverY = driver.y();
    private final Trigger driverRightBumper = driver.rightBumper();
    private final Trigger driverLeftBumper = driver.rightBumper();
    // private final Trigger driverLeftTrigger = driver.leftTrigger();
    // private final Trigger driverRightTrigger = driver.rightTrigger();
    // private final Trigger driverDpadUp = driver.povUp();
    // private final Trigger driverDpadDown = driver.povDown();
    // private final Trigger driverDpadLeft = driver.povLeft();
    // private final Trigger driverDpadRight = driver.povRight();


  // -----===--Subsystems--===----- 
  private final Indexer s_Indexer = Indexer.getInstance();
  private final Intake s_Intake = Intake.getInstance();
  private final Shooter s_Shooter = Shooter.getInstance();

  // WHY ARE THE INSTANCES NOT IN THE BOILER OF EACH ONE OF THESE!!! -iggy

  public CommandXboxController getDriverController() {
    return driver;
  }

  public static RobotContainer getInstance() { //if we ever need to grab controller values lol idk
    if (container == null) {
        container = new RobotContainer();
    }
    return container;
  }

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
      drivetrain.applyRequest(() -> drivetrain.drive(-driver.getLeftY(), -driver.getLeftX(), -driver.getRightX())) // Drive counterclockwise with negative X (left))
    ); // this better work now omg

    driverBack.onTrue(new InstantCommand(() -> drivetrain.resetOdo(new Pose2d(0, 0, new Rotation2d()))));
    
    driver.a().onTrue(new SetIntake(IntakeState.ON));
    driver.x().onTrue(new SetIntake(IntakeState.REV));
    driver.y().onTrue(new SetShooter(0));
    driver.b().onTrue(CommandFactory.AllOff());

    driver.rightBumper().whileTrue(new SetIndexer(IndexerStates.INDEX));
    driver.leftBumper().whileTrue(new SetIndexer(IndexerStates.REV));

    driver.rightTrigger().onTrue(CommandFactory.Shoot());
    driver.leftTrigger().onTrue(CommandFactory.IntakeIndex());

    // driver.rightTrigger().onTrue(Comma\ndFactory.Shoot());
    // driver.a().onTrue(intake);
    // driver.x().onTrue(index or smth idk);
    // driver.b().onTrue();
    // driver.y().whileTrue(new SetIndexer(IndexerStates.SHOOTING));

    // NO SWERVE!

    // drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
    //     drivetrain.applyRequest(() -> drivetrain.drive(-driver.getLeftY(),-driver.getLeftX(),-driver.getRightX()))
    // );

    // driverBack.onTrue(new InstantCommand(() -> drivetrain.resetOdo(new Pose2d(0, 0, new Rotation2d()))));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
