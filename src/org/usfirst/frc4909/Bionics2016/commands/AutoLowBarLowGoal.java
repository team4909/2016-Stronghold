package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBarLowGoal extends CommandGroup {
    
    public  AutoLowBarLowGoal() {
    	requires(Robot.drivetrain);
    	requires(Robot.shooter);
    	requires(Robot.feeder);
    	requires(Robot.pivot);
    	
    	addSequential(new autoGoToDefence());
    	addSequential(new autoCrossDefenceAccel(true));
    	addSequential(new autoMoveDistance(83.7));
    	addSequential(new autoTurnRobot(45, false));
    	addParallel(new StartShooter(4000));
    	//addParallel(new autoSetShooterAngle(70));
    	addSequential(new autoPivotTime(.3));
    	addSequential(new Shoot());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
