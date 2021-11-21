package dev.guinness.client.util;

import java.util.ArrayList;
import java.util.List;

public class FriendUtil
{
    public static List<String> friends;
    
    public static void addFriend(final String s) {
        FriendUtil.friends.add(s.toLowerCase());
    }
    
    public static void delFriend(final String s) {
        FriendUtil.friends.remove(s.toLowerCase());
    }
    
    public static boolean isFriend(final String s) {
        return FriendUtil.friends.contains(s.toLowerCase());
    }
    
    public static List getFriends() {
        return FriendUtil.friends;
    }
    
    static {
        FriendUtil.friends = new ArrayList<String>();
    }
}
