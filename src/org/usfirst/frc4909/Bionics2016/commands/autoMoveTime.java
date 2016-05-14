package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoMoveTime extends Command {
	private double time;
	private double startTime;
	private double Kp=0.03;

	
    public autoMoveTime(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	time=seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime=Timer.getFPGATimestamp();
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.drivetrain.getGyroAngle(); // get current heading
        Robot.drivetrain.autoDrive(0.8, angle*Kp); // drive towards heading 0
    	//Robot.drivetrain.autoDrive(0.5, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp()-startTime>=time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
