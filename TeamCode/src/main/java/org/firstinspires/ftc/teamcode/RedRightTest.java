package org.firstinspires.ftc.teamcode;/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by CCA on 11/21/2017.
 */
/*@Disabled
@Autonomous(name = "RedRightTest")

public class RedRightTest extends LinearOpMode {

    DcMotor frontLeft = null;
    DcMotor liftMotor = null;
    DcMotor frontRight = null;
    DcMotor backLeft = null;
    DcMotor backRight = null;
    ColorSensor colorSensor = null;
    float red, green, blue;
    Servo topLeftGrab, topRightGrab = null;
    Servo jewelKnocker = null;
    Drive drive;

    float Lt, Rt;
    final double RIGHTGrab_OPEN = 1.0;
    final double RIGHTGrab_CLOSE = 0.4; //used to be 0.46
    final double LEFTGrab_OPEN = 0;
    final double LEFTGrab_CLOSE = 0.6; //used to be 0.54
    final double SPROCKET_RATIO = 2.0 / 3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO * (1120.0 / (2 * 2 * 3.14159));

    double JEWEL_UP = 0;
    double JEWEL_DOWN = 0 + 0.091;

    double ForwardPower = 1.0;

    ModernRoboticsI2cGyro gyro;


    public void initialization () {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");


        colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        //jewelKnocker.setPosition(jewelKnocker_Raised);
        RaiseJewelKnocker();

        topRightGrab = hardwareMap.servo.get("topRightGrab");
        leftGrab = hardwareMap.servo.get("topLeftGrab");
        rightGrab.setPosition(RIGHTGrab_CLOSE);
        leftGrab.setPosition(LEFTGrab_CLOSE);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive = new Drive(frontLeft, frontRight, backLeft, backRight, liftMotor, gyro, topLeftGrab, topRightGrab, this);
    }
    @Override
    public void runOpMode () throws InterruptedException {
        initialization();
        waitForStart();
        //liftMotor needs method (power + time/ticks) Might need encoder.
        drive.DriveForwardDistance(0.5, 25);
        sleep(1000);
        drive.TurnRightDegree(0.5, 90);
        sleep(1000);
        drive.DriveForwardDistance(0.5, 24);
        sleep(1000);
        drive.StrafeRightDistance(0.5, 18);
        sleep(1000);
        drive.DriveForwardDistance(0.5, 12);
        DeliverGlyph();
    }

    public void DeliverGlyph() {

        drive.DriveForwardDistance(0.5, 3.5);
        leftGrab.setPosition(LEFTGrab_OPEN);
        rightGrab.setPosition(RIGHTGrab_OPEN);
        drive.DriveForwardDistance(0.5, 1.5);
        drive.StopDriving();
    }

    public void LowerJewelKnocker() {

        jewelKnocker.setPosition(JEWEL_DOWN);

    }


    public void RaiseJewelKnocker(){
        jewelKnocker.setPosition(JEWEL_UP);

    }

}*/