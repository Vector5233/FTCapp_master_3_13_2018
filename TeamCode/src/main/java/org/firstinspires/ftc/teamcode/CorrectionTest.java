package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by CCA on 1/24/2018.
 */
@Autonomous(name="CorrectionTest", group = "my group")
@Disabled
public class CorrectionTest extends LinearOpMode {
    public static final String TAG = "Vuforia VuMark Sample";

    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    Servo jewelKnocker, topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    ColorSensor colorSensor;
    float red, green, blue;
    Drive drive;
    RaymondAutonomousOpMode ray;


    float Lt, Rt;

    final double RIGHTGrab_COMPLETEOPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.35;
    final double LEFTGrab_COMPLETEOPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.65;
    final double RIGHTGrab_OPEN = 0.5;
    final double LEFTGrab_OPEN = 0.5;


    final double SPROCKET_RATIO = 2.0 / 3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO * (1120.0 / (2 * 2 * 3.14159));

    // Right, left, and center are facing the back of the bot
    final double JEWEL_UP = 0.94;
    final double JEWEL_DOWN = 0.38;
    final double JEWEL_RIGHT = 0.25;
    final double JEWEL_CENTER = 0.15;
    final double JEWEL_LEFT = 0.05;
    final double JEWEL_RETRY = 0.12;

    @Override
    public void runOpMode() {
        initialization();
        waitForStart();
        sleep(1000);
        drive.TurnDegrees(0.5,90);
        sleep(5000);
        drive.TurnDegrees(0.5,-90);
        sleep(5000);
        drive.TurnDegrees(0.5,45);
        sleep(5000);
        drive.TurnDegrees(0.5,90);
        sleep(5000);
        drive.TurnDegrees(0.5,-135);
        drive.StopDriving();
    }

    public void initialization() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get ("liftMotor");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive = new Drive(frontLeft,frontRight,backLeft,backRight, liftMotor, gyro, topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab, this);
        ray = new RaymondAutonomousOpMode(drive, jewelKnocker, jewelRaiser, colorSensor, this);

        gyro.calibrate();

        while (gyro.isCalibrating() && opModeIsActive()){
            idle();
        }
    }
}
