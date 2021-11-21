package club.minnced.discord.rpc;

import com.sun.jna.Callback;

public interface OnStatus extends Callback
{
    void accept(final int p0, final String p1);
}
