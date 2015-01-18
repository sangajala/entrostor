package voyanta.ui.utils.unused;

//import org.apache.commons.lang.BooleanUtils;

//import static StringConstants.*;
//import static java.util.Arrays.asList;
//import static org.apache.commons.lang.StringUtils.isEmpty;
//import static org.apache.commons.lang.StringUtils.trim;

import org.apache.commons.lang.BooleanUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class StringConversionUtils {

    public static final String IS = "is";
    public static final String[] IS_NOT = new String[]{"is not", "isnt", "isn't"};
    public static final String ABSENT = "absent";
    public static final String PRESENT = "present";
    public static final String ENABLED = "enabled";
    public static final String INTERNAL = "internal";
    public static final String PUBLIC = "public";
    public static final String DISABLED = "disabled";
    public static final String SUCCESSFULLY = "successfully";
    public static final String UNSUCCESSFULLY = "unsuccessfully";
    public static final String VISIBLE = "visible";
    public static final String NOT_VISIBLE = "not visible";
    public static final String NOT_FOUND = "not found";
    public static final String FOUND = "found";
    public static final String STRING_BLANK = "";
    public static final String STRING_BLANK_ALIAS = "~BLANK~";
    public static final String STRING_NULL_IN_BRACKETS = "(null)";
    public static final String STRING_NULL_NO_BRACKETS = "null";
    public static final String SPACE = " ";
    public static final String PERIOD = ".";
    public static final String THOUSANDS_SEPARATOR_UP_TO_2_DIGITS = "###,##0.##";
    public static final String THOUSANDS_SEPARATOR_2_DIGITS = "###,##0.00";
    public static final String THOUSANDS_SEPARATOR_0_DIGITS = "###,###";
    /**
     * No-op private constructor to prevent instantiation.
     */
    private StringConversionUtils() {
        super();
    }

    public static Boolean toBoolean(String booleanRepresentation) {
        try {
            if (booleanRepresentation == null || booleanRepresentation.trim().isEmpty()) {
                return false;
            } else if (booleanRepresentation.equalsIgnoreCase(PRESENT)
                    || booleanRepresentation.substring(0, 1).equalsIgnoreCase("y")
                    || booleanRepresentation.equalsIgnoreCase("true")
                    || booleanRepresentation.equalsIgnoreCase(IS)
                    || booleanRepresentation.equalsIgnoreCase(ENABLED)
                    || booleanRepresentation.equalsIgnoreCase(SUCCESSFULLY)) {
                return true;
            } else if (booleanRepresentation.equalsIgnoreCase(ABSENT)
                    || containsIgnoreCase(IS_NOT, booleanRepresentation)
                    || booleanRepresentation.substring(0, 1).equalsIgnoreCase("n")
                    || booleanRepresentation.equalsIgnoreCase(UNSUCCESSFULLY)) {
                return false;
            } else {
                return BooleanUtils.toBoolean(booleanRepresentation);
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public static Boolean containsIgnoreCase(String[] comparisonStrings, String string) {
        for (String comparisonString : comparisonStrings) {
            if (comparisonString.equalsIgnoreCase(string)) return true;
        }
        return false;
    }

    public static String stripWhitespaceAndLineBreaks(String string) {
        return string == null ? "" : string.replaceAll("(\\t|\\r|\\n|\\s+)", "");
    }


    public static String stripDots(String string) {
        return string == null ? "" : string.replaceAll(">(\\.+)<", "><");
    }

    public static String stripLeadingAndTrailing3Dots(String string) {
        return StringUtils.removeEnd(StringUtils.removeStart(string, "..."), "...");
    }


    /**
     * Removes all Windows, UNIX or Mac line terminators, taking into account if the input string is
     * text, avoiding concatenation of words; e.g.
     * Goodbye cruel
     * world.
     * becomes
     * Goodbye cruel world rather than Goodbye cruelworld.
     */
    public static String stripLineBreaks(String original) {
        return stripLineBreaksNoTrim(original).trim();
    }

    public static String stripLineBreaksNoTrim(String original) {
        return original.replaceAll("\\r\\n |\\r\\n|\\n |\\n", " ");
    }

    public static String toSuccessfully(String nullOrNotNullString) {
        return nullOrNotNullString == null ? UNSUCCESSFULLY : SUCCESSFULLY;
    }

    public static String toVisibleOrNotVisible(boolean visibleOrNotVisible) {
        return visibleOrNotVisible ? VISIBLE : NOT_VISIBLE;
    }

    public static String toSuccessfully(boolean trueOrFalseOutcome) {
        return trueOrFalseOutcome ? SUCCESSFULLY : UNSUCCESSFULLY;
    }

    public static String toSuccessfully(int responseCode) {
        return responseCode == 0 ? SUCCESSFULLY : UNSUCCESSFULLY;
    }

    public static String toCountryFromType(String countryType) {
        return countryType.equalsIgnoreCase("uk") ? "GB" : "ES";
    }

    public static String toPresentOrAbsent(Boolean presentOrAbsentState) {
        return (presentOrAbsentState == null || !presentOrAbsentState) ? ABSENT : PRESENT;
    }

    /**
     * To proper case and trim.
     *
     * @param str String to trim and proper case.
     * @return Trimmed and proper cased string.
     */
    public static String toProperCaseAndTrim(final String str) {
        if (str == null) {
            return null;
        }

        final char[] array = toLowerCaseAndTrim(str).toCharArray();

        boolean upperCase = true;
        final StringBuilder builder = new StringBuilder();
        for (final char letter : array) {
            if (letter == ' ') {
                builder.append(letter);
                upperCase = true;
            } else if (upperCase) {
                builder.append(Character.toUpperCase(letter));
                upperCase = false;
            } else {
                builder.append(letter);
            }
        }

        return builder.toString();
    }


    /**
     * To upper case and trim.
     *
     * @param str String to trim and upper case.
     * @return Trimmed and upper cased string.
     */
    public static String toUpperCaseAndTrim(final String str) {
        if (str == null) {
            return null;
        }

        return (str).trim().toUpperCase();
    }

    /**
     * To Value Or Null
     *
     * @param value String to evaluate.
     * @return if string is null or represents null -> null. Otherwise -> value.
     */
//    public static <T> T toValueOrNull(final T value) {
//        return StringUtils.isNullOrNullString(value) ? null : value;
//    }


    /**
     * Trim and lower case.
     *
     * @param str String to trim and lower case.
     * @return Trimmed and lower cased string.
     */
    public static String toLowerCaseAndTrim(final String str) {
        if (str == null) {
            return null;
        }

        return str.trim().toLowerCase();
    }

    public static <T> T valueOrDefault(final T value, final T defaultValue) {
        if (value == null || (StringUtils.isEmpty(String.valueOf(value)))) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public static <T> T valueOrEmpty(T value) {
        return (T) valueOrDefault(value, StringUtils.EMPTY);
    }


    public static boolean isNotApplicable(String value) {
        return value != null && (value.equalsIgnoreCase("n/a")
                || value.equalsIgnoreCase("na")
                || value.equalsIgnoreCase("not applicable"));
    }

    public static String lowerCaseInitial(String stringValue) {
        return (stringValue == null) ? null : stringValue.substring(0, 1).toLowerCase() + stringValue.substring(1);
    }

    public static String toEnabledOrDisabled(Boolean enabledFlag) {
        return enabledFlag ? ENABLED : DISABLED;
    }

    public static String toInternalOrPublic(Boolean isPublic) {
        return isPublic ? PUBLIC : INTERNAL;
    }

    public static Integer toInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert value [" + string + "] to toFloat", e);
        }
    }

    public static Float toFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert value [" + string + "] to toFloat", e);
        }
    }

    public static Double toDouble(String string) {
        try {
            return string == null ? null : Double.parseDouble(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot convert value [" + string + "] to toDouble", e);
        }
    }

    public static Long toLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert value [" + string + "] to Long", e);
        }
    }

    public static BigDecimal toBigDecimal(String string) {
        try {
            return new BigDecimal(string);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert value [" + string + "] to BigDecimal", e);
        }
    }

    public static List<String> convertDelimitedStringToList(final String str, final String delimiter) {
        final StringTokenizer st = new StringTokenizer(str, delimiter);

        final List<String> list = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();
            if (!token.isEmpty()) {
                list.add(token.trim());
            }
        }

        return list;
    }

    public static Set<String> convertDelimitedStringToSet(final String str, final String delimiter) {
        final StringTokenizer st = new StringTokenizer(str, delimiter);

        final Set<String> set = new HashSet<String>();
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();
            if (!token.isEmpty()) {
                set.add(token.trim());
            }
        }

        return set;
    }

    /**
     * even though pattern is provided, symbols for decimal and group separators are not guaranteed;
     * use separators in English locale.
     */
    private static DecimalFormat createDecimalFormat(String pattern) {
        DecimalFormat result = new DecimalFormat(pattern);
        result.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return result;
    }

    public static String formatNumberWithPattern(BigDecimal value, String pattern) {
        return createDecimalFormat(pattern).format(value);
    }

    public static String formatNumberWithPattern(Integer value, String pattern) {
        return createDecimalFormat(pattern).format(value);
    }

    private static String formatNumberWithPattern(final Long value, final String pattern) {
        return createDecimalFormat(pattern).format(value);
    }

    public static String formatNumberForReporting(BigDecimal value) {
        return formatNumberWithPattern(value, THOUSANDS_SEPARATOR_2_DIGITS);
    }

    public static String formatNumberForReporting(Long value) {
        return formatNumberWithPattern(value, THOUSANDS_SEPARATOR_2_DIGITS);
    }

    public static String formatNumberForReporting(String value) {
        return formatNumberWithPattern(new BigDecimal(value), THOUSANDS_SEPARATOR_2_DIGITS);
    }

    public static String formatNumberForReporting(Integer value) {
        return formatNumberWithPattern(value, THOUSANDS_SEPARATOR_0_DIGITS);
    }

    public static String deCamelCase(String s) {
        return uppercaseInitial(s.replaceAll("([A-Z])", " $1").trim());
    }

    public static String toCamelCase(String naturalLanguage) {
        StringBuilder output = new StringBuilder();
        boolean uppercaseNextLetter = false;
        for (char letter : naturalLanguage.toLowerCase().toCharArray()) {
            if (Character.isWhitespace(letter)) {
                uppercaseNextLetter = true;
                continue;
            }
            if (uppercaseNextLetter) {
                letter = Character.toUpperCase(letter);
            }
            output.append(letter);
            uppercaseNextLetter = false;
        }

        return output.toString();
    }


    public static String toConstantName(String value) {
        return value
                .trim()
                .toUpperCase()
                .replaceAll("[^A-Z0-9_ ]", "")
                .replaceAll(" ", "_");
    }

    public static String uppercaseInitial(String s) {
        if (s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String underscoresToCamelCase(String s) {
        return toCamelCase(s.replaceAll("_", " "));
    }

    public static String truncate(String value, int length) {
        return StringUtils.substring(value, 0, length);
    }

//    public static String[] splitWithNoBlanks(String stringToSplit, String separator) {
//        String[] splitStrings = stringToSplit.split(separator);
//        List<String> splitStringsAsList = new ArrayList<String>((splitStrings));
//        splitStringsAsList.remove("");
//        return splitStringsAsList.toArray(new String[0]);
//    }

    public static String wrapWith(String toWrap, String wrapper) {
        return wrapper + toWrap + wrapper;
    }

    public static String wrapWithBar(String toWrap) {
        return wrapWith(toWrap, "|");
    }

    /**
     * Split the given input string into a <code>List</code> of Strings, the length of each is equal to the listItemLength
     * parameter. Any remaining characters are returned in the last string in the list which will be less than or equal to the of length
     * listItemLength.
     */
//    public static List<String> splitIntoFixedLengthList(String inputString, int listItemLength) {
//
//        List<String> results = new ArrayList<String>();
//
//        if (!StringUtils.isNullOrNullString(inputString)) {
//            int start = 0;
//            int end = inputString.length() > listItemLength ? listItemLength : inputString.length();
//            int length = inputString.length();
//
//            while (start < length) {
//                results.add(inputString.substring(start, end));
//                start = end;
//                if (end + listItemLength > length)
//                    end = length;
//                else
//                    end = end + listItemLength;
//            }
//        }
//
//        return results;
//    }
//
//    public static <T extends Enum> T toEnum(String s, Class<T> enumClass) {
//        return StringUtils.isNullOrNullString(s) ? null : (T) Enum.valueOf(enumClass, toEnumString(s));
//    }
//
//    public static String toEnumString(String s) {
//        if (StringUtils.isNullOrNullString(s)) {
//            return null;
//        } else {
//            String spacesToUnderscores = s.trim().replaceAll(" ", "_");
//            String otherSpecialCharactersToUnderscore = spacesToUnderscores.replaceAll("[-/]", "_");
//            String stripAllRemainingCharacters = otherSpecialCharactersToUnderscore.replaceAll("[^a-zA-Z0-9_]", "");
//            String deDuplicateUnderscores = stripAllRemainingCharacters.replaceAll("_+", "_").toUpperCase();
//            return deDuplicateUnderscores;
//        }
//    }
//
//    public static String toEnumStringNoUnderscores(String s) {
//        if (StringUtils.isNullOrNullString(s)) {
//            return null;
//        } else {
//            return toEnumString(s).replaceAll("_", "");
//        }
//    }
//
//    public static String parseConcordionDocumentItem(String stringValue) {
//        return isNullOrNullString(stringValue) ? null : stringValue.trim();
//    }
//
//    public static String parseConcordionDocumentItemAndCompress(String stringValue) {
//        return isNullOrNullString(stringValue) ? null : stripLineBreaks(stringValue.trim()).replaceAll(" +", " ");
//    }
//
//    public static String parseConcordionDocumentItem(String stringValue, String replacementValue) {
//        return isNullOrNullString(stringValue) ? replacementValue : stringValue;
//    }


    public static String removeLeadingZeros(String inputString) {
        return inputString == null ? null : inputString.replaceFirst("^0+(?!$)", "");
    }

    public static String removeTrailingDotAndLineBreakAndTrim(String value) {
        return StringUtils.removeEnd(value.replaceAll("\r$", "").trim(), "\"").replaceAll("\\.$", "");
    }

    public static <T> T valueForType(String value, Class<T> returnType) {
        return valueForType(value, returnType, true);
    }

    public static <T> T valueForType(String value, Class<T> returnType, boolean allowNullsToBeReturned) {
        if (allowNullsToBeReturned && StringUtils.isEmpty(value)) return null;
        if (returnType.equals(String.class)) return (T) value;
        if (returnType.equals(Long.class)) return (T) StringConversionUtils.toLong(value);
        if (returnType.equals(Double.class)) return (T) StringConversionUtils.toDouble(value);
        if (returnType.equals(Boolean.class)) return (T) StringConversionUtils.toBoolean(value);
        if (returnType.equals(Float.class)) return (T) StringConversionUtils.toFloat(value);
        if (returnType.equals(Integer.class)) return (T) StringConversionUtils.toInteger(value);
        try {
            return (T) returnType.getMethod("valueForName", String.class).invoke(null, value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Return type [" + returnType.getSimpleName() + "] not supported when evaluating value [" + value + "]", e);
        }
    }

}
