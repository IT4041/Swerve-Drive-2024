package frc.robot;

public class RobotMath {
    /**
     * this takes ticks from the Can Coders and converts them to degrees.
     * @param ticks
     * @return
     */
    public static double convertTicksToDegrees(double ticks) {
        // Cancoder: 2048 ticks per rotation
        // Steering gear ratio: 150/7:1
        double degrees = ticks * (1.0 / 2048.0) * (1.0 / (150 / 7)) * (360.0 / 1.0);
        return degrees;
    }

    /**
     * this takes degrees and converts them to ticks for the Can Coders.
     * @param degrees
     * @return
     */
    public static double convertDegreesToTicks(double degrees) {
    
        double ticks = degrees * 1 / ((1.0 / 2048.0) * (1.0 / (150 / 7)) * (360.0 / 1.0));
        return ticks;
    }
    public static double calculateSpeedMetersPer100ms(double speedTicksPer100miliSeconds){
        // 2048 ticks per rotation
        // 6.75:1 gear ratio
        // 4" wheels
        double speedMetersPerSecond = speedTicksPer100miliSeconds * (1 / 100d) * (1_000 / 1d) * (1 / 2048d) * (1 / 6.75)
        * ((4.0 * Math.PI) / 1d) * (.0254 / 1);
        return speedMetersPerSecond;
    }

    public static double distanceMeters(double motorSensorPosition){
        // 2048 ticks per rotation
        // 6.75:1 gear ratio
        // 4" wheels
        double distanceMeters = (1 / 2048d) * (1 / 6.75) * ((4.0 * Math.PI) / 1d) * (.0254 / 1) * motorSensorPosition;
        return distanceMeters;
    }
}
