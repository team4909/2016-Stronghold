package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoCrossDefenceAccel extends Command {
	private double starttime = 0;
	private double Kp = 0.03;
	private double power;
	private double lastNonFlatTime;
	
    public autoCrossDefenceAccel(boolean isLowBar) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	if(isLowBar) power = 0.3;
    	else power = 0.75;
    }

    // Called just before this Command runs the first time
    protected void initialize() {    	
    	starttime=Timer.getFPGATimestamp();
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	if(!Robot.drivetrain.accelFlat()){
        	lastNonFlatTime=Timer.getFPGATimestamp();
    	}
    	
        double angle = Robot.drivetrain.getGyroAngle(); // get current heading
        Robot.drivetrain.autoDrive(power, angle*Kp); // drive towards heading 0
       
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Timer.getFPGATimestamp()-lastNonFlatTime>1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.autoDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
