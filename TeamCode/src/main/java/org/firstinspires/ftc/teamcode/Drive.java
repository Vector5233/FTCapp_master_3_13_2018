package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by CCA on 11/16/2017.
 */

public class Drive extends Object {

    DcMotor frontLeft, frontRight, backLeft, backRight, liftMotor;
    Servo jewelKnocker, topLeftGrab, topRightGrab, jewelRaiser, bottomLeftGrab, bottomRightGrab;
    ModernRoboticsI2cGyro gyro;
    LinearOpMode opmode;
    //ModernRoboticsI2cRangeSensor rangeRight, rangeLeft;

    final double SPROCKET_RATIO = 2.0 / 3.0;
    final double TICKS_PER_INCH = SPROCKET_RATIO * (1120.0 / (2 * 2 * 3.14159));
    final double ROBOT_RADIUS = (135 / 103.25) * 5.75 * (90.0/76.0);
    final double TOLERANCE = 2;
    final double ALLOWANCE = 2;

    final double RIGHTGrab_COMPLETEOPEN = 0.8;
    final double RIGHTGrab_CLOSE = 0.35;
    final double LEFTGrab_COMPLETEOPEN = 0.2;
    final double LEFTGrab_CLOSE = 0.65;
    final double RIGHTGrab_OPEN = 0.5;
    final double LEFTGrab_OPEN = 0.5;

    public Drive(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR, DcMotor LM, ModernRoboticsI2cGyro G, Servo TLG, Servo TRG, Servo BLG, Servo BRG, LinearOpMode L) {
        frontLeft = FL;
        backLeft = BL;
        backRight = BR;
        frontRight = FR;
        gyro = G;
        opmode = L;
        topLeftGrab = TLG;
        topRightGrab = TRG;
        bottomLeftGrab = BLG;
        bottomRightGrab = BRG;
        liftMotor = LM;
    }

    public void NewTurnDegrees (double power, double degrees, double start) {
        if (degrees > 0) {
            double target;
            target = start + degrees;
            TurnLeftDegree(power, degrees);
            TurnLeftCorrect(target);
        } else if (degrees < 0) {
            double target;
            target = start + degrees;
            TurnRightDegree(power, -degrees);
            TurnRightCorrect(target);
        } else {


        }
    }



    public void TurnDegrees (double power, double degrees) {
        if (degrees > 0){
            double target;
            target = gyro.getIntegratedZValue() + degrees;
            opmode.telemetry.addData("Left Turn: Gyro", gyro.getIntegratedZValue());
            opmode.telemetry.update();
            TurnLeftDegree(power, degrees);
            opmode.sleep(500);
            TurnLeftCorrect(target);
        }
        else if (degrees < 0) {
            double target;
            target = gyro.getIntegratedZValue() + degrees;
            opmode.telemetry.addData("Right Turn: Gyro", gyro.getIntegratedZValue());
            opmode.telemetry.update();
            TurnRightDegree(power, -degrees);
            TurnRightCorrect(target);
        }
        else{

        }
    }

    public void TurnLeftDegree(double power, double degrees) {
        // distance in inches
        int ticks = (int) ((2 * 3.14159 / 360) * degrees * ROBOT_RADIUS * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }
        double target;
        opmode.telemetry.addData("Gyro", gyro.getIntegratedZValue());
        opmode.telemetry.update();
        target = gyro.getIntegratedZValue() + degrees;

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() && frontLeft.isBusy()) ;

        StopDriving();
        opmode.telemetry.addData("Gyro end of turn", gyro.getIntegratedZValue());
        opmode.telemetry.update();
    }

    public void TurnLeftCorrect (double target) {

        double g;
        g = gyro.getIntegratedZValue();
        opmode.telemetry.addData("Gyro start correct", g);
        opmode.telemetry.update();
        if (g > target + TOLERANCE){
            TurnRightDegree(0.5, g - target);//power 0.3
        } else if (g < target - TOLERANCE){
            TurnLeftDegree(0.5, target - g); //power 0.3
        }
        else{

        }

        opmode.telemetry.addData("Gyro end correct", gyro.getIntegratedZValue());
        opmode.telemetry.update();
    }

    public void TurnRightDegree(double power, double degrees) {
        // distance in inches
        int ticks = (int) ((2 * 3.14159 / 360) * degrees * ROBOT_RADIUS * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }
        double target;
        opmode.telemetry.addData("Gyro", gyro.getIntegratedZValue());
        opmode.telemetry.update();
        target = gyro.getIntegratedZValue() - degrees;

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() && frontLeft.isBusy()) ;

        StopDriving();
        opmode.telemetry.addData("Gyro", gyro.getIntegratedZValue());
        opmode.telemetry.update();
    }

    public void TurnRightCorrect (double target) {

        double g;
        g = gyro.getIntegratedZValue();
        opmode.telemetry.addData("Gyro", g);
        opmode.telemetry.update();
        if (g > target + TOLERANCE) {
            TurnRightDegree(0.5, g - target); //power 0.3
        } else if (g < target - TOLERANCE) {
            TurnLeftDegree(0.5, target - g); //power 0.3
        }
        else {
        }
    }

    public void StrafeRightDistance(double power, double distance) {

        // distance in inches
        ElapsedTime rightTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        int ticks = (int) (distance * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        rightTime.reset();
        rightTime.startTime();

        while (frontRight.isBusy() && frontLeft.isBusy()) {

            if (rightTime.seconds() < power) {
                frontLeft.setPower(rightTime.seconds());
                frontRight.setPower(rightTime.seconds());
                backLeft.setPower(rightTime.seconds());
                backRight.setPower(rightTime.seconds());
            } else {
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(power);
            }
        }

        StopDriving();
    }

    public void StrafeLeftDistance(double power, double distance) {
        // distance in inches
        ElapsedTime leftTime = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
        int ticks = (int) (distance * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        backRight.setTargetPosition(-ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        leftTime.reset();
        leftTime.startTime();

        while (frontRight.isBusy() && frontLeft.isBusy()) {
            if (leftTime.seconds() < power) {
                frontLeft.setPower(leftTime.seconds());
                frontRight.setPower(leftTime.seconds());
                backLeft.setPower(leftTime.seconds());
                backRight.setPower(leftTime.seconds());
            }

            else {
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(power);}
        }

        StopDriving();
    }

    public void DriveForwardDistance(double power, double distance) {
        // distance in inches
        int ticks = (int) (distance * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() && frontLeft.isBusy()) ;

        StopDriving();
    }

    public void DriveBackwardDistance(double power, double distance) {
        // distance in inches
        int ticks = (int) (distance * TICKS_PER_INCH);
        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        backLeft.setTargetPosition(-ticks);
        backRight.setTargetPosition(-ticks);

        SetModeAll(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while (frontRight.isBusy() && frontLeft.isBusy()) ;

        StopDriving();
    }

    /*public void BlueStrafeCorrect(double goal) {
        double d;
        d = rangeLeft.getDistance(DistanceUnit.INCH);
        if (d > goal + ALLOWANCE) {
            StrafeLeftDistance(0.5,d - goal);
            }
        else if (d < goal - ALLOWANCE) {
            StrafeRightDistance(0.5,goal - d);
            }
        else {
        }
    }

    public void RedStrafeCorrect (double goal) {
        double d;
        d = rangeRight.getDistance(DistanceUnit.INCH);
        if (d > goal + ALLOWANCE) {
            StrafeRightDistance(0.5,d - goal);
            }
        else if (d < goal - ALLOWANCE) {
            StrafeLeftDistance(0.5,goal - d);
            }
        else{
            }
    }*/

    public void StopDriving() {

        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }

    public void DriveForwardTime(double power, long time) {//4 sec.
        // distance in inches

        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SetModeAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        opmode.sleep(time);

        StopDriving();

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void DriveBackwardTime(double power, long time) {//4 sec.
        // distance in inches

        if (power > 0.65) {
            power = 0.65;
        }

        SetModeAll(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SetModeAll(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);

        opmode.sleep(time);

        StopDriving();

    }

    public void DeliverGlyph() {
        liftMotor.setDirection(DcMotor.Direction.FORWARD); //FORWARD Raises Lift
        liftMotor.setPower(-1.0);
        opmode.sleep(500);
        bottomLeftGrab.setPosition(RIGHTGrab_OPEN);
        bottomRightGrab.setPosition(LEFTGrab_OPEN);
        opmode.sleep(250);
        liftMotor.setPower(0.0);
        opmode.sleep(500);
        DriveForwardTime(0.5, 1000);
        /*opmode.sleep(500);
        TurnLeftDegree(0.3,20);
        opmode.sleep(500);
        DriveBackwardDistance(1, 6);*/
        StopDriving();
    }

    public void DeliverRed() {
        DeliverGlyph();
        TurnRightDegree(0.3,20);
        opmode.sleep(500);
        DriveBackwardDistance(1, 6);
        StopDriving();
    }

    public void DeliverBlue() {
        DeliverGlyph();
        TurnLeftDegree(0.3,20);
        opmode.sleep(500);
        DriveBackwardDistance(1,6);
        StopDriving();
    }

    public void SetModeAll(DcMotor.RunMode mode) {
        frontLeft.setMode(mode);
        frontRight.setMode(mode);
        backLeft.setMode(mode);
        backRight.setMode(mode);
    }
}
