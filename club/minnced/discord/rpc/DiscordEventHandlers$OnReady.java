package club.minnced.discord.rpc;

import com.sun.jna.Callback;

public interface OnReady extends Callback
{
    void accept(final DiscordUser p0);
}
