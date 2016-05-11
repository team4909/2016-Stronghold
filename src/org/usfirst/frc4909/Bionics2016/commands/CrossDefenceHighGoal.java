package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossDefenceHighGoal extends CommandGroup {
    
    public  CrossDefenceHighGoal() {
    	
        requires(Robot.drivetrain);
        requires(Robot.pivot);
        requires(Robot.shooter);
        requires(Robot.feeder);
        
        //THIS IS NOT TO BE USED UNTIL A LATER DATE!

        
        //addSequential(new CrossDefence()); //based on time, update to be real time or distance
        //addSequential(new autoMoveDistance(120));
        addSequential(new autoMoveTime(2.5));
    	//addParallel(new autoSetShooterAngle(30)); //make correct angle
        addSequential(new autoPivotTime(2.5,-1));
        addSequential(new autoTurnRobot(10, true));
        addParallel(new HorizontalAim());
        addSequential(new VerticalAim());
    	addSequential(new StartShooter(4000, true));
    	addParallel(new StartShooter(4000, false));//Check this RPM
    	addSequential(new Shoot());
    	
    	//Possibility of using the line up methods (ultrasonics and vision) in auto... 
    	//for now assuming that we are not as those are not yet written
    	
    	
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
