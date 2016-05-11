package org.usfirst.frc4909.Bionics2016.commands;

import org.usfirst.frc4909.Bionics2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class HorizontalAim extends Command {
	private double kP = 1.0/(320.0); //1.0/320/2
	private double kI = .00003;//.00005
	private double error;
	double[] x={0.0};
	int total=0;
	double correctionL=0;
	double correctionR=0;
	double timeSinceVisible;
	double timeSinceInRange;
	double targetX=0;
	double maxWidth=0;
	int i=0;
	int index=0;
	AutoLowBarHighGoal parent=null;
    public HorizontalAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }
    public HorizontalAim(AutoLowBarHighGoal parent) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.parent=parent;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Done X", "No");
    	
    	timeSinceVisible=Timer.getFPGATimestamp();
    	timeSinceInRange=Timer.getFPGATimestamp();

    	total=0;
    	
    	SmartDashboard.putBoolean("Horizontally Alligned", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try{
    	maxWidth=0;
    	i=0;
    	index=0;
    	if(Robot.table.getNumberArray("centerX",x).length>0){
	    	for(double d: Robot.table.getNumberArray("width",x) ){
	    		if(d>maxWidth){
	    			maxWidth=d;
	    			index=i;
	    		}
	    		i++;
	    	}
	    	
	    	timeSinceVisible=Timer.getFPGATimestamp();
	        targetX = Robot.table.getNumberArray("centerX", x)[index];
	        error = targetX-160;
	        if(!(Math.abs(error)<8))
	        	timeSinceInRange=Timer.getFPGATimestamp();

	        if((total>0)!=(error>0))
	        	total=0;
	        total+=error;
	        SmartDashboard.putNumber("error X",error);
	        SmartDashboard.putNumber("calc X",error * kP);
	        SmartDashboard.putNumber("cX", Robot.table.getNumberArray("centerX", x)[index]);
	        SmartDashboard.putNumber("kI X", total*kI);
	        correctionL=-error * kP+(-total*kI);
	        correctionR=error * kP+(total*kI);
	        if(Math.abs(correctionL)>.5){
	        	correctionL=Math.signum(correctionL)*.5;
	        	correctionR=Math.signum(correctionR)*.5;
	        }
	        Robot.drivetrain.moveRobot(correctionL, correctionR);
    	}
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Timer.getFPGATimestamp()-timeSinceInRange>.5){
    		return true;
    	}
    	if(Timer.getFPGATimestamp()-timeSinceVisible>.2){
    		if(parent!=null)
    			parent.cancel();
    		return true;//Math.abs(error)<3}
    	}
		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Done X", "Yes");
        Robot.drivetrain.moveRobot(0, 0);
        SmartDashboard.putBoolean("Horizontally Alligned", true);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
