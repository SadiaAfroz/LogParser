package net.therap;

import java.util.regex.Pattern;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class PatternProcessor {
    static final String timeToMatch = "\\d+:\\d+:\\d+,\\d+";
    static final Pattern timePattern = Pattern.compile(timeToMatch);

    static final String necessaryInformationToMatch = "URI=\\[.+";
    static final Pattern necessaryInformationPattern = Pattern.compile(necessaryInformationToMatch);

    static final String getCheck = "G";
    public static final int HOURS_IN_A_DAY = 24;

}
