package dev.guinness.client.util;

public class MotionUtil implements Wrapper
{
    public static double[] getPlayerMotion(final double n) {
        float forward = Float.intBitsToFloat(Float.floatToIntBits(3.3477515E38f) ^ 0x7F7BDB59);
        if (MotionUtil.mc.gameSettings.keyBindForward.isKeyDown()) {
            forward = Float.intBitsToFloat(Float.floatToIntBits(6.1526318f) ^ 0x7F44E25C);
        }
        float strafe = MotionUtil.mc.player.movementInput.moveStrafe;
        float yaw = MotionUtil.mc.player.field_70177_z;
        if (forward != Float.intBitsToFloat(Float.floatToIntBits(7.553189E37f) ^ 0x7E634BA7)) {
            if (strafe >= Float.intBitsToFloat(Float.floatToIntBits(6.837902f) ^ 0x7F5AD018)) {
                yaw += ((forward > Float.intBitsToFloat(Float.floatToIntBits(2.8953493E38f) ^ 0x7F59D266)) ? -45 : 45);
                strafe = Float.intBitsToFloat(Float.floatToIntBits(3.0454975E38f) ^ 0x7F651E26);
            }
            else if (strafe <= Float.intBitsToFloat(Float.floatToIntBits(-4.043121f) ^ 0x7F01613F)) {
                yaw += ((forward > Float.intBitsToFloat(Float.floatToIntBits(2.8404008E38f) ^ 0x7F55B021)) ? 45 : -45);
                strafe = Float.intBitsToFloat(Float.floatToIntBits(1.8766226E38f) ^ 0x7F0D2E70);
            }
            if (forward > Float.intBitsToFloat(Float.floatToIntBits(2.0411627E38f) ^ 0x7F198F5D)) {
                forward = Float.intBitsToFloat(Float.floatToIntBits(24.72782f) ^ 0x7E45D293);
            }
            else if (forward < Float.intBitsToFloat(Float.floatToIntBits(2.8190495E38f) ^ 0x7F5414EB)) {
                forward = Float.intBitsToFloat(Float.floatToIntBits(-5.7585707f) ^ 0x7F384636);
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + Float.intBitsToFloat(Float.floatToIntBits(1.7390193f) ^ 0x7D6A982F)));
        final double cos = Math.cos(Math.toRadians(yaw + Float.intBitsToFloat(Float.floatToIntBits(0.008935069f) ^ 0x7EA66465)));
        final double motionX = forward * n * cos + strafe * n * sin;
        final double motionZ = forward * n * sin - strafe * n * cos;
        return new double[] { motionX, motionZ };
    }
}
