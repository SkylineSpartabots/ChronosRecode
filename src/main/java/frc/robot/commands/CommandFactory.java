package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Indexer.IndexerStates;
import frc.robot.commands.SetShooter;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetIndexer;

public class CommandFactory {

    public static Command Shoot() {
        return new SequentialCommandGroup(
            new SetShooter(10500),
            Commands.waitSeconds(1.0),
             new SetIndexer(IndexerStates.INDEX),
            Commands.waitSeconds(2.0),
            new SetShooter(0),
            new SetIndexer(IndexerStates.OFF)
        );
    }

    public static Command Index() {
        return new ParallelCommandGroup(
            new SetIndexer(IndexerStates.INDEX),
            Commands.waitSeconds(1.0),
            new SetIndexer(IndexerStates.OFF)
        );
    }
}