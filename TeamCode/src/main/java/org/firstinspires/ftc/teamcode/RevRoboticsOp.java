package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CCA on 8/16/2017.
 */

@TeleOp(name="RevRoboticsOp", group = "myGroup")
public class RevRoboticsOp extends OpMode {


    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    ColorSensor colorSensor;
    float red, green, blue;
    Servo topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab;
    Servo jewelKnocker, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    ElapsedTime myTimer;

    float Lt, Rt;

    // final int liftPosition = liftMotor.getCurrentPosition();
    // Note 1: NO "final" -- that makes liftPosition unchangeable.
    // Note 2: NO initialization -- liftMotor is not yet defined.

    int liftPosition;

    final double RIGHTGrab_COMPLETEOPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.33; //used to be 0.4
    final double LEFTGrab_COMPLETEOPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.67; //used to be 0.6
    final double RIGHTGrab_OPEN = 0.5;
    final double LEFTGrab_OPEN = 0.5;
    final double RIGHTBottom_CLOSE = 0.3;
    final double LEFTBottom_CLOSE = 0.7;
    //Bottom grabber values are reverse (put right in left, left in right)


    // Right, left, and center are facing the back of the bot
    final double JEWEL_UP = 0.94;
    final double JEWEL_DOWN = 0.38;
    final double JEWEL_RIGHT = 0.25;
    final double JEWEL_CENTER = 0.15;
    final double JEWEL_LEFT = 0.05;
    final double JEWEL_RETRY = 0.12;

    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        //gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

        //colorSensor = hardwareMap.colorSensor.get("color");
        jewelKnocker = hardwareMap.servo.get("jewel");
        jewelRaiser = hardwareMap.servo.get("raise");
        jewelRaiser.setPosition(JEWEL_UP);  //JEWEL_UP
        jewelKnocker.setPosition(JEWEL_CENTER);

        topRightGrab = hardwareMap.servo.get("topRightGrab");
        topLeftGrab = hardwareMap.servo.get("topLeftGrab");
        bottomLeftGrab = hardwareMap.servo.get("bottomLeftGrab");
        bottomRightGrab = hardwareMap.servo.get("bottomRightGrab");

        myTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        topRightGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
        topLeftGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        bottomRightGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        bottomLeftGrab.setPosition(RIGHTGrab_COMPLETEOPEN);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void loop() {
// 2 Drive: movement + tasks
        /*red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();
        */

        /*telemetry.addData("Red: ", red);
        telemetry.addData("Green: ", green);
        telemetry.addData("Blue: ", blue);*/
        //telemetry.addData("gyro: ",gyro.getIntegratedZValue());
        //telemetry.addData("Front Left:", gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        //telemetry.addData("Back Right:", gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);

       /* if (gamepad1.dpad_up){

            frontLeft.setPower(1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(1.0);
            backRight.setPower(1.0);

        }

        if (gamepad1.dpad_down){

            frontLeft.setPower(-1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(-1.0);
        }*/

       // TODO: Fix logic on strafe  DONE
     /*   if (gamepad1.x) {

            frontLeft.setPower(-0.5);
            frontRight.setPower(0.5);
            backLeft.setPower(0.5);
            backRight.setPower(-0.5);

        }

        else if (gamepad1.b) {

            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backRight.setPower(0.5);
            backLeft.setPower(-0.5);

        }*/
        //else {
            frontLeft.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x + gamepad1.right_stick_x);
            frontRight.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x - gamepad1.right_stick_x);
            backLeft.setPower(-gamepad1.left_stick_y-gamepad1.left_stick_x + gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.left_stick_y+gamepad1.left_stick_x - gamepad1.right_stick_x);
        //}

        /*if (gamepad2.a) {
            jewelRaiser.setPosition(JEWEL_DOWN);
        } else if (gamepad2.b) {
            jewelRaiser.setPosition(JEWEL_UP);
        }


        if (gamepad2.x&&gamepad2.y ) {
            myTimer.reset();
            jewelKnocker.setPosition(JEWEL_CENTER);
        }
        else if (gamepad2.x && (myTimer.time() > 200)) {
            jewelKnocker.setPosition(JEWEL_LEFT);
        }
        else if (gamepad2.y && (myTimer.time() > 200)) {
            jewelKnocker.setPosition(JEWEL_RIGHT);
        }
*/

        // trivial change
        if (gamepad2.dpad_up) {
            /*liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftMotor.getCurrentPosition();
            liftPosition = liftMotor.getCurrentPosition();*/
            liftMotor.setPower(0.9);
        } else if (gamepad2.dpad_down) {
            /*liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            liftPosition = liftMotor.getCurrentPosition();*/
            liftMotor.setPower(-0.9);
        } else {
           /*liftMotor.setPower(0);
            liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftMotor.setTargetPosition(liftPosition);*/
            liftMotor.setPower(0.05);
        }
        //can also do joystick
        //options to fix lift fall--1. set small power to motor (.05), 2. use elastics to counter weight


        //Binary system for grabbers

        //if (gamepad2.left_bumper && gamepad2.right_bumper) {
        if (gamepad2.y){
            topRightGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
            topLeftGrab.setPosition(LEFTGrab_COMPLETEOPEN);
            bottomLeftGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
            bottomRightGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        } else if (gamepad2.left_bumper) {
            topRightGrab.setPosition(RIGHTGrab_OPEN);
            topLeftGrab.setPosition(LEFTGrab_OPEN);
            bottomLeftGrab.setPosition(RIGHTGrab_OPEN);
            bottomRightGrab.setPosition(LEFTGrab_OPEN);
        } else if (gamepad2.right_bumper) {
            topRightGrab.setPosition(RIGHTGrab_CLOSE);
            topLeftGrab.setPosition(LEFTGrab_CLOSE);
            bottomLeftGrab.setPosition(RIGHTBottom_CLOSE);
            bottomRightGrab.setPosition(LEFTBottom_CLOSE);
        } else {
            //nothing
        }
    }
}