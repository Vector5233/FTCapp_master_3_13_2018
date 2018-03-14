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

@TeleOp(name="RevRoboticsOpSinglePlayer", group = "myGroup")
public class RevRoboticsOpSinglePlayer extends OpMode {


    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    ColorSensor colorSensor;
    float red, green, blue;
    Servo topLeftGrab, topRightGrab, bottomLeftGrab, bottomRightGrab;
    Servo jewelKnocker, jewelRaiser;
    ModernRoboticsI2cGyro gyro;
    ElapsedTime myTimer;

    float Lt, Rt;

    final double RIGHTGrab_COMPLETEOPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.33; //used to be 0.4
    final double LEFTGrab_COMPLETEOPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.67; //used to be 0.6
    final double RIGHTGrab_OPEN = 0.5;
    final double LEFTGrab_OPEN = 0.5;

    final double JEWEL_UP = 0 + 0.94;
    final double JEWEL_DOWN = 0 + 0.4;
    final double JEWEL_RIGHT = 0 + 0.100;
    final double JEWEL_CENTER = 0 + 0.182;
    final double JEWEL_LEFT = +0.2382;

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

        topRightGrab.setPosition(0.7);
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
        telemetry.addData("Front Left:", gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        telemetry.addData("Back Right:", gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);

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

        if (gamepad1.x) {

            frontLeft.setPower(-1.0);
            frontRight.setPower(1.0);
            backLeft.setPower(1.0);
            backRight.setPower(-1.0);


        }

        if (gamepad1.b) {

            frontLeft.setPower(1.0);
            frontRight.setPower(-1.0);
            backLeft.setPower(-1.0);
            backRight.setPower(1.0);

        }

        frontLeft.setPower(-gamepad1.left_stick_y/*+gamepad1.left_stick_x*/ + gamepad1.right_stick_x);
        frontRight.setPower(-gamepad1.left_stick_y/*-gamepad1.left_stick_x*/ - gamepad1.right_stick_x);
        backLeft.setPower(-gamepad1.left_stick_y/*-gamepad1.left_stick_x*/ + gamepad1.right_stick_x);
        backRight.setPower(-gamepad1.left_stick_y/*+gamepad1.left_stick_x*/ - gamepad1.right_stick_x);


        if (gamepad1.a) {
            jewelRaiser.setPosition(JEWEL_DOWN);
        } else// if (gamepad1.b)
        {
            jewelRaiser.setPosition(JEWEL_UP);
        }


        if (gamepad1.x && gamepad1.y) {
            myTimer.reset();
            jewelKnocker.setPosition(JEWEL_CENTER);
        } else if (gamepad2.x && (myTimer.time() > 200)) {
            jewelKnocker.setPosition(JEWEL_LEFT);
        } else if (gamepad2.y && (myTimer.time() > 200)) {
            jewelKnocker.setPosition(JEWEL_RIGHT);
        }


        // trivial change
        if (gamepad1.dpad_up) {
            liftMotor.setPower(1.0);
        } else if (gamepad1.dpad_down) {
            liftMotor.setPower(-1.0);
        } else {
            // liftMotor.setPower(0);
            liftMotor.setPower(.05);
        }
        //options to fix lift fall--1. set small power to motor (.05), 2. use elastics to counter weight


        //Binary system for grabbers

        //if (gamepad2.left_bumper && gamepad2.right_bumper) {
        if (gamepad1.y) {
            topRightGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
            topLeftGrab.setPosition(LEFTGrab_COMPLETEOPEN);
            bottomLeftGrab.setPosition(RIGHTGrab_COMPLETEOPEN);
            bottomRightGrab.setPosition(LEFTGrab_COMPLETEOPEN);
        } else if (gamepad1.left_bumper) {
            topRightGrab.setPosition(RIGHTGrab_OPEN);
            topLeftGrab.setPosition(LEFTGrab_OPEN);
            bottomLeftGrab.setPosition(RIGHTGrab_OPEN);
            bottomRightGrab.setPosition(LEFTGrab_OPEN);
        } else if (gamepad1.right_bumper) {
            topRightGrab.setPosition(RIGHTGrab_CLOSE);
            topLeftGrab.setPosition(LEFTGrab_CLOSE);
            bottomLeftGrab.setPosition(RIGHTGrab_CLOSE);
            bottomRightGrab.setPosition(LEFTGrab_CLOSE);
        } else {
            //nothing
        }
    }
}