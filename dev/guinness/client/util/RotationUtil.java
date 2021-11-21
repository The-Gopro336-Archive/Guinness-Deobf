package dev.guinness.client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;

public class RotationUtil implements Wrapper
{
    public static float[] RotateClient(final double n, final double n2, final double n3) {
        final double diffX = n + Double.longBitsToDouble(Double.doubleToLongBits(2.4805877814838024) ^ 0x7FE3D83E6822A419L) - RotationUtil.mc.player.field_70165_t;
        final double diffY = n2 + Double.longBitsToDouble(Double.doubleToLongBits(155.92994975338294) ^ 0x7F837DC225FC367FL) - (RotationUtil.mc.player.field_70163_u + RotationUtil.mc.player.func_70047_e());
        final double diffZ = n3 + Double.longBitsToDouble(Double.doubleToLongBits(27.441460872776755) ^ 0x7FDB7103946B0A2FL) - RotationUtil.mc.player.field_70161_v;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - Float.intBitsToFloat(Float.floatToIntBits(1.7317656f) ^ 0x7D69AA7F);
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        final float[] array = new float[2];
        final int n4 = 0;
        final EntityPlayerSP player = RotationUtil.mc.player;
        array[n4] = (player.field_70177_z += MathHelper.wrapDegrees(yaw - RotationUtil.mc.player.field_70177_z));
        final int n5 = 1;
        final EntityPlayerSP player2 = RotationUtil.mc.player;
        array[n5] = (player2.field_70125_A += MathHelper.wrapDegrees(pitch - RotationUtil.mc.player.field_70125_A));
        return array;
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        double dirx = entityPlayer.field_70165_t - n;
        double diry = entityPlayer.field_70163_u - n2;
        double dirz = entityPlayer.field_70161_v - n3;
        final double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= len;
        diry /= len;
        dirz /= len;
        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);
        pitch *= Double.longBitsToDouble(Double.doubleToLongBits(0.05684625147455668) ^ 0x7FE1BF2FB7944027L);
        yaw *= Double.longBitsToDouble(Double.doubleToLongBits(0.05102168933521887) ^ 0x7FE6BA5FD44A73E5L);
        yaw += Double.longBitsToDouble(Double.doubleToLongBits(1.3477969896426776) ^ 0x7FA31093938299CFL);
        return new double[] { yaw, pitch };
    }
    
    public static double[] calculateLookAt(final EntityPlayer entityPlayer, final EntityPlayer entityPlayer2) {
        double dirx = entityPlayer2.field_70165_t - entityPlayer.field_70165_t;
        double diry = entityPlayer2.field_70163_u - entityPlayer.field_70163_u;
        double dirz = entityPlayer2.field_70161_v - entityPlayer.field_70161_v;
        final double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        dirx /= len;
        diry /= len;
        dirz /= len;
        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);
        pitch = pitch * Double.longBitsToDouble(Double.doubleToLongBits(0.053792417575221206) ^ 0x7FCD0AAE0484468BL) / Double.longBitsToDouble(Double.doubleToLongBits(0.4644440947706407) ^ 0x7FD49888ED3320EBL);
        yaw = yaw * Double.longBitsToDouble(Double.doubleToLongBits(0.010790908884655788) ^ 0x7FE0998B46079A64L) / Double.longBitsToDouble(Double.doubleToLongBits(0.7769355314014548) ^ 0x7FE1FD5CB30B2A2CL);
        yaw += Double.longBitsToDouble(Double.doubleToLongBits(0.024014911640975783) ^ 0x7FCE175D7075107BL);
        return new double[] { yaw, pitch };
    }
}
