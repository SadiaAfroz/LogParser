package net.therap.controller;

import java.util.regex.Pattern;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
interface PatternProcessor {
    String timeToMatch = "\\d+:\\d+:\\d+,\\d+";
    Pattern timePattern = Pattern.compile(timeToMatch);

    String necessaryInformationToMatch = "URI=\\[.+";
    Pattern necessaryInformationPattern = Pattern.compile(necessaryInformationToMatch);

    String getCheck = "G";
    int HOURS_IN_A_DAY = 24;

}
