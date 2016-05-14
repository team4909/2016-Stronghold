package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBarHighGoal extends CommandGroup {
    
    public  AutoLowBarHighGoal() {
        // Add Commands here:
    	requires(Robot.drivetrain);
    	requires(Robot.shooter);
    	requires(Robot.feeder);
    	requires(Robot.pivot);
    	this.cancel();
    	
//    	addSequential(new autoSetShooterAngle(0));
//    	addSequential(new autoGoToDefence());
//    	addSequential(new autoCrossDefenceAccel(true));
//    	addSequential(new autoMoveDistance(83.7));
//    	addSequential(new autoTurnRobot(60, false));
//    	addParallel(new autoMoveDistance(96));
//    	addParallel(new StartShooter(4000));
//    	addSequential(new autoSetShooterAngle(45));
//    	//addSequential(new autoPivotTime(.2));
//    	addSequential(new Shoot());
    	
    	
//    	addSequential(new autoSetShooterAngle(0));
//    	addSequential(new autoGoToDefence());
//    	addSequential(new autoCrossDefenceAccel(true));
//    	addSequential(new autoMoveDistance(83.7));
//    	addSequential(new autoTurnRobot(60, false));
//    	addParallel(new autoMoveDistance(96));
//    	addParallel(new StartShooter(4000));
//    	addSequential(new autoSetShooterAngle(45));
//    	//addSequential(new autoPivotTime(.2));
//    	addSequential(new Shoot());

    	addSequential(new PivotDown());
    	addSequential(new autoPivotTime(.3,1));
    	addSequential(new autoMoveTime(3));
    	addSequential(new autoTurnRobot(60));
    	addSequential(new autoPivotTime(.4,1));
    	addSequential(new HorizontalAim());
    	addSequential(new PivotDown());
    	addSequential(new autoPivotTime(1.2,1));
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
