package de.legoshi.challengecraft.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Objects;

public enum Message {

    PREFIX("&6&l[Chal]&r "),
    ERRPREFIX("&c&l[Chal]&c "),

    ERR_NO_PERMISSION("You dont have permission for that."),
    ERR_PLAYER_NOT_ONLINE("{0} is not online!"),

    INFO_DUEL_STARTED("The duel has started! You now have 5 minutes to get as high as possible!"),
    INFO_DUEL_WINNER("The player {0} won a duel with a height of {1}!"),
    INFO_DUEL_TIE("The player {0}, {1} tied a duel with a height of {2}!"),
    SUCC_DUEL_INVITE("Successfully invited {0}."),
    SUCC_DUEL_INVITE_OTHER("You got an invite from {0} for a bedrock tower duel!"),
    SUCC_DUEL_ACCEPT("Successfully accepted invite from {0}."),
    ERR_DUEL("/duel [invite|accept] <player>"),
    ERR_DUEL_NOT_INVITED("{0} didnt invite you!"),
    ERR_PLAYER_LEFT("A player left the match..."),

    ERR_PUZZLE("/puzzle [list|join]"),

    PLAYER_JOIN("{0} joined the Server!"),
    PLAYER_LEAVE("{0} left the Server!");

    String m;

    Message(String message) {
        this.m = message;
    }

    public String msg(Prefix prefix) {

        String manip = m;

        if (prefix == Prefix.GOOD) manip = PREFIX.m + manip;
        else if (prefix == Prefix.BAD)  manip = ERRPREFIX.m + manip;

        manip = ChatColor.translateAlternateColorCodes('&', manip);

        return manip;
    }

    public String msg(Prefix prefix, String... args) {

        String manip = m;

        for (int i = 0; i < args.length; i++) {
            String replacement = args[i];
            manip = manip.replace("{" + i + "}", Objects.nonNull(replacement) ? replacement : "<?>");
        }

        if (prefix == Prefix.GOOD) manip = PREFIX.m + manip;
        else if (prefix == Prefix.BAD)  manip = ERRPREFIX.m + manip;

        manip = ChatColor.translateAlternateColorCodes('&', manip);

        return manip;
    }

    public String getRawMessage() {
        return this.m;
    }

}
