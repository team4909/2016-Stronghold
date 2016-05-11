package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoTurnRobot extends Command {
	private double angle = 0;
	private boolean turn_left = false;
    public autoTurnRobot(double a, boolean isLeft) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	angle=a;
    	turn_left=isLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(turn_left)
    	{
    		Robot.drivetrain.turnRobotLeft();
    	}
    	
    	else
    	{
    		Robot.drivetrain.turnRobotRight();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getGyroAngle())>=angle;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.moveRobot(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
