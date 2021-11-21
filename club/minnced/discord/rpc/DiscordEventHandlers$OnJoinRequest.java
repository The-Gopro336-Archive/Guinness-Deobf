package club.minnced.discord.rpc;

import com.sun.jna.Callback;

public interface OnJoinRequest extends Callback
{
    void accept(final DiscordUser p0);
}
