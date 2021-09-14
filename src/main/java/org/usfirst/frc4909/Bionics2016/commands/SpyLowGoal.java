package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpyLowGoal extends CommandGroup {
    
    public  SpyLowGoal() {
        requires(Robot.drivetrain);
        requires(Robot.pivot);
        requires(Robot.shooter);
        requires(Robot.feeder);
        
        // Add Commands here:
    	
    	addParallel(new autoMoveDistance(100));
    	addParallel(new autoSetShooterAngle(10)); //MAKE CORRECT ANGLE!!!
    	//addParallel(new autoPivotTime(.3)); //for w/o encoder only
    	addSequential(new StartShooter(2000));
    	addSequential(new Shoot());
    	
    	//Possibility of using the line up methods (ultrasonics and vision) in auto... 
    	//for now assuming that we are not as those are not yet written
    	
    	
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
