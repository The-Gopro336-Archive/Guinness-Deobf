package club.minnced.discord.rpc;

import com.sun.jna.Callback;

public interface OnGameUpdate extends Callback
{
    void accept(final String p0);
}
