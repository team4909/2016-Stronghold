// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4909.Bionics2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.AllocationException;
import edu.wpi.first.wpilibj.vision.USBCamera;

import org.usfirst.frc4909.Bionics2016.commands.*;
import org.usfirst.frc4909.Bionics2016.subsystems.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ShapeMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * dimidLineory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser autoChooser;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static Shooter shooter;
    public static Feeder feeder;
    public static Pivot pivot;
    public static Climber climber;
    int session;
    Image frame;
    NIVision.Rect midLine;
    NIVision.Rect crossLineMid;
    NIVision.Rect crossLineTop;
    NIVision.Rect crossLineBottom;

    USBCamera cam;
    NIVision.RGBValue color;
    public static NetworkTable table;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	try {
    		RobotMap.init();
    	} catch (AllocationException ex) {
    		ex.printStackTrace();
    	}
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        shooter = new Shooter();
        feeder = new Feeder();
        pivot = new Pivot("Pivot", .5, 0, 0);
        climber = new Climber();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
         table = NetworkTable.getTable("GRIP/myContoursReport");

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autoChooser = new SendableChooser();
        autoChooser.addDefault("Spy Low Goal", new SpyLowGoal());
        autoChooser.addObject("Spy High Goal", new SpyHighGoal());
        autoChooser.addObject("Defence Cross (not Low Bar)", new autoCrossDefence());
        autoChooser.addObject("Low Bar Cross", new AutoLowBarCross());
        autoChooser.addObject("Low Bar High Shot", new AutoLowBarHighGoal());
        autoChooser.addObject("Low Bar Low Shot", new AutoLowBarLowGoal());
        autoChooser.addObject("Do Nothing", new AutoDoNothing());
        cam = new USBCamera("cam0");
        
        SmartDashboard.putData("Autonomous Mode Chooser",autoChooser);
        //autoChooser.addObject("Experimental: Cross Defence, Score Low Goal", new CrossLowGoal());
        //autoChooser.addObject("Experimental: Cross Defence,, Score High Goal", new ());
        //autoChooser.addObject("Experimental!!: Start as Spy, Score High Goal, Cross a Defence", new ());
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        color = new RGBValue(0,255,0,255);
        //session = NIVision.IMAQdxOpenCamera("cam0",
          //      NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //NIVision.IMAQdxConfigureGrab(session);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	
    	try{
    		cam.stopCapture();
    	}
    	catch(Exception ex){
    	
    	}
    	
        //NIVision.IMAQdxStopAcquisition(session);    
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putNumber("left encoder", RobotMap.drivetrainleftDriveEncoder.getRaw());//Robot.shooter.getLeftRPM()
        //SmartDashboard.putNumber("right encoder", RobotMap.drivetrainrightDriveEncoder.getRaw());//Robot.shooter.getRightRPM()
        
        //SmartDashboard.putNumber("Accellerometer X",Robot.drivetrain.getAccelX());
        //SmartDashboard.putNumber("Accellerometer Y",Robot.drivetrain.getAccelY());
        //SmartDashboard.putNumber("Accellerometer Z",Robot.drivetrain.getAccelZ());
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = (Command)new CrossDefence();//autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
        if (autonomousCommand != null)
        	autonomousCommand.cancel();
        //NIVision.IMAQdxStartAcquisition(session);
        
        //NIVision Crosshair
       // midLine = new NIVision.Rect(0, 317, 480, 6);
        //crossLine = new NIVision.Rect(237, 270, 6, 100);
        
        //USBCamera Crosshair
        
        midLine = new NIVision.Rect(0, 136, 480, 6);
        crossLineMid = new NIVision.Rect(162, 89, 6, 100);
        crossLineBottom = new NIVision.Rect(215, 89, 6, 100);
        crossLineTop = new NIVision.Rect(0, 89, 6, 100);
        
        
        
        cam.setExposureManual(1);
        cam.setFPS(6);
        cam.startCapture();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        ////SmartDashboard.putNumber("encoderVal", Robot.pivot.returnPIDInput());
        //NIVision.IMAQdxGrab(session, frame, 1);
        
        cam.getImage(frame);
        //Vertical
        NIVision.imaqDrawShapeOnImage(frame, frame, midLine,
               DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 255f);
        //CrossMid
        NIVision.imaqDrawShapeOnImage(frame, frame, crossLineMid,
                DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 255f);
        //CrossBottom
        NIVision.imaqDrawShapeOnImage(frame, frame, crossLineBottom,
                DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0f);
        //CrossTop
        NIVision.imaqDrawShapeOnImage(frame, frame, crossLineTop,
                DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 255f);
        
        
        //NIVision.imaqOverlayRect(frame, midLine, color, DrawMode.PAINT_VALUE, null );
        CameraServer.getInstance().setImage(frame);
        
        /** robot code here! **/
        //Timer.delay(0.005);		// wait for a motor update time
        
        ////SmartDashboard.putNumber("left encoder", Robot.shooter.getLeftRPM());
        ////SmartDashboard.putNumber("right encoder", Robot.shooter.getRightRPM());
        ////SmartDashboard.putNumber("Test", RobotMap.shooterleftShootEncoder.getRaw());
        
        //SmartDashboard.putNumber("PDB Current6", RobotMap.PDP.getCurrent(6));
        //SmartDashboard.putNumber("PDB Current7", RobotMap.PDP.getCurrent(7));

        ////SmartDashboard.putNumber("left encoder", RobotMap.drivetrainleftDriveEncoder.getRaw());//Robot.shooter.getLeftRPM()
        ////SmartDashboard.putNumber("right encoder", RobotMap.drivetrainrightDriveEncoder.getRaw());//Robot.shooter.getRightRPM()
        
        SmartDashboard.putNumber("Center X", table.getNumber("centerX", 0));
        SmartDashboard.putNumber("Center Y", table.getNumber("centerY", 0));
        SmartDashboard.putNumber("Area", table.getNumber("area", 0));
        SmartDashboard.putNumber("Width", table.getNumber("width", 0));
        SmartDashboard.putNumber("Height",table.getNumber("height", 0));
        SmartDashboard.putNumber("Solidity", table.getNumber("solidity", 0));
        
        ////SmartDashboard.putNumber("Accellerometer X",RobotMap.drivetrainaccelerometer.getX());
        ////SmartDashboard.putNumber("Accellerometer Y",RobotMap.drivetrainaccelerometer.getY());
        ////SmartDashboard.putNumber("Accellerometer Z",RobotMap.drivetrainaccelerometer.getZ());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
