package voyanta.ui.utils.unused;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * String utility class.
 */
public final class StringUtils extends org.apache.commons.lang.StringUtils {


    private static long uniqueCounter = 0L;

    /**
     * Determines if one string equals another, ignoring leading or trailing
     * spaces or case, and is safe for nulls.
     *
     * @param str1 String 1.
     * @param str2 String 2.
     * @return True if first string equals the second, else false.
     */
    public static boolean equalsIgnoreCaseAndTrim(final String str1, final String str2) {
        return equalsIgnoreCase(trim(str1), trim(str2));
    }

    /**
     * Determines if one string equals another, ignoring leading, trailing, inner spaces or case, and is safe for nulls.
     *
     * @param str1 String 1.
     * @param str2 String 2.
     * @return True if first string equals the second, else false.
     */
    public static boolean equalsIgnoreCaseAndWhiteSpace(final String str1, final String str2) {
        return equalsIgnoreCase(remove(trim(str1), " "), remove(trim(str2), " "));
    }


    /**
     * Determines if one string contains another, ignoring leading or trailing
     * spaces or case, and is safe for nulls.
     * <p/>
     * NOTE:  This method always returns false for an empty/blank search string.
     *
     * @param str       String to search.
     * @param searchStr Search string.
     * @return True if first string contains the second, else false.
     */
    public static boolean containsIgnoreCaseAndTrim(final String str, final String searchStr) {
        if (isBlank(searchStr)) {
            return false;
        }

        return containsIgnoreCase(trim(str), trim(searchStr));
    }


    /**
     * Determines if one string starts with another, ignoring leading or
     * trailing spaces or case, and is safe for nulls.
     * <p/>
     * NOTE:  This method always returns false for an empty/blank search string.
     *
     * @param str       String to search.
     * @param searchStr Search string.
     * @return True if first string starts with the second, else false.
     */
    public static boolean startsWithIgnoreCaseAndTrim(final String str, final String searchStr) {
        if (isBlank(searchStr)) {
            return false;
        }

        return startsWithIgnoreCase(trim(str), trim(searchStr));
    }


    /**
     * Determines if one string ends with another, ignoring leading or trailing
     * spaces or case, and is safe for nulls.
     * <p/>
     * NOTE:  This method always returns false for an empty/blank search string.
     *
     * @param str       String to search.
     * @param searchStr Search string.
     * @return True if first string ends with the second, else false.
     */
    public static boolean endsWithIgnoreCaseAndTrim(final String str, final String searchStr) {
        if (isBlank(searchStr)) {
            return false;
        }

        return endsWithIgnoreCase(trim(str), trim(searchStr));
    }


    /**
     * Get stack trace.
     *
     * @param throwable Throwable to get stack trace for.
     * @return Stack trace.
     */
    public static String getStackTrace(final Throwable throwable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        throwable.printStackTrace(printWriter);

        return result.toString();
    }

    public synchronized static long incrementedSeed() {
        uniqueCounter++;
        return uniqueCounter;
    }

//    public static String getRandomNumberToMaximumLength(int stringLength) {
//        long x = 1L;
//        long y = Long.valueOf(repeat("9", Math.min(stringLength, 18)));
//        Random r = new Random();
//        long number = x + ((long) (r.nextDouble() * (y - x)));
//        return leftPadWithZerosToFixedLength(number, stringLength);
//    }


    public static String getRandomCharacter() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'A');
        return String.valueOf(c);
    }

//    public static String getRandomCharacter(int length) {
//        StringBuilder returnValue = new StringBuilder();
//        for (Number testNumber : range(1, length)) {
//            returnValue.append(getRandomCharacter());
//        }
//        return returnValue.toString();
//    }

//    public static String getUniqueReferenceWithId(String prefix, final Integer id) {
//        return prefix + "-" + getRandomNumberToMaximumLength(20) + "-" + id;
//    }
//
//    public static String getUniqueReferenceWithId(final Integer id) {
//        return getRandomNumberToMaximumLength(20) + id;
//    }

//    public static String leftPadWithZerosToFixedLength(Integer inputValue, Integer returnStringLength) {
//        return leftPadWithZerosToFixedLength(inputValue.toString(), returnStringLength);
//    }
//
//    public static String leftPadWithZerosToFixedLength(Long inputValue, Integer returnStringLength) {
//        return leftPadWithZerosToFixedLength(inputValue.toString(), returnStringLength);
//    }

//    public static String leftPadWithZerosToFixedLength(String inputValue, Integer returnStringLength) {
////        String result = com.venkyold.org.adv.advance.StringUtils.repeat("0", returnStringLength) + inputValue;
////        return result.substring(result.length() - returnStringLength, result.length());
//    }
//
//    public static String rightPadWithZerosToFixedLength(String inputValue, Integer returnStringLength) {
////        String result = inputValue + com.venkyold.org.adv.advance.StringUtils.repeat("0", returnStringLength);
//        return result.substring(0, returnStringLength);
//    }

//    public static String uniqueValueStartingAtZeroOfMaximumLength(Integer maximumLength) {
//        return leftPadWithZerosToFixedLength(NumberUtils.uniqueValueIncrementingFromZero(), maximumLength);
//    }

    /**
     * Uses Properties(KEY=Value) to substitute in a string looking for ${KEY} substituing Value
     *
     * @param text
     * @param properties
     * @return
     */
    public static String substituteProperties(String text, Properties properties) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
        Matcher matcher = pattern.matcher(text);
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (matcher.find()) {
            builder.append(text.substring(i, matcher.start()));
            String value = properties.getProperty(matcher.group(1));
            if (value == null)
                throw new IllegalArgumentException("No property was provided to substitute variable " + matcher.group(0));
            builder.append(value);
            i = matcher.end();
        }
        builder.append(text.substring(i, text.length()));
        return builder.toString();
    }

//    public static <T> boolean isNullOrNullString(T... values) {
//        if (values == null) {
//            return true;
//        } else {
//            for (T code : values) {
//                if (StringUtils.isEmpty(String.valueOf(code)))
//                    return true;
//                if (valueOrDefault(code, StringConstants.STRING_NULL_IN_BRACKETS).toString().equalsIgnoreCase(StringConstants.STRING_NULL_IN_BRACKETS))
//                    return true;
//                if (valueOrDefault(code, StringConstants.STRING_NULL_NO_BRACKETS).toString().equalsIgnoreCase(StringConstants.STRING_NULL_NO_BRACKETS))
//                    return true;
//                if (valueOrDefault(code, StringConstants.STRING_BLANK).toString().equalsIgnoreCase(StringConstants.STRING_BLANK))
//                    return true;
//                if (valueOrDefault(code, StringConstants.STRING_BLANK_ALIAS).toString().equalsIgnoreCase(StringConstants.STRING_BLANK_ALIAS))
//                    return true;
//            }
//            return false;
//        }
//    }

    public static boolean isPGPEncrypted(String fileContents) {
        return fileContents != null && fileContents.contains("-----BEGIN PGP MESSAGE-----");
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);

        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * Remove quotes or double quotes from the specified string.
     */
//    public static String removeQuotes(String value) {
//        if (com.venkyold.org.adv.advance.StringUtils.isNotBlank(value)) {
//            if ((value.startsWith("'") && value.endsWith("'")) || (value.startsWith("\"") && value.endsWith("\""))) {
//                value = value.substring(1, value.length());
//                value = value.substring(0, value.length() - 1);
//            }
//        }
//        return value;
//    }

    public static <T> boolean areValuesEqual(T value1, T value2) {
        if (value1 == null && value2 == null) return true;
        if (value1 != null) return value1.equals(value2);
        return value2.equals(value1);
    }

    public static boolean areStringsEqualIgnoreCase(String string1, String string2) {
        if (string1 == null && string2 == null) return true;
        if (string1 != null) return equalsIgnoreCase(string1, string2);
        return equalsIgnoreCase(string2, string1);
    }


    public static boolean containsAny(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (char searchChar : searchChars) {
                if (searchChar == ch) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean containsAll(String searchIn, List<String> searchFor) {
        for (String string : searchFor) {
            if (!searchIn.contains(string)) return false;
        }
        return true;
    }

    public static boolean containsAny(String searchIn, Iterable<String> searchFor) {
        for (String string : searchFor) {
            if (searchIn.contains(string)) return true;
        }
        return false;
    }

    public static boolean containsAll(String searchIn, String... searchFor) {
        for (String string : searchFor) {
            if (!searchIn.contains(string)) return false;
        }
        return true;
    }

//    public static boolean containsAllIgnoreCase(String searchIn, Iterable<String> searchFor) {
//        for (String string : searchFor) {
//            if (!com.venkyold.org.adv.advance.StringUtils.containsIgnoreCase(searchIn, string)) return false;
//        }
//        return true;
//    }
//
//    public static boolean containsAnyIgnoreCase(String searchIn, Iterable<String> searchFor) {
//        for (String string : searchFor) {
//            if (com.venkyold.org.adv.advance.StringUtils.containsIgnoreCase(searchIn, string)) return true;
//        }
//        return false;
//    }
//
//    public static boolean containsAllIgnoreCase(String searchIn, String... searchFor) {
//        for (String string : searchFor) {
//            if (!com.venkyold.org.adv.advance.StringUtils.containsIgnoreCase(searchIn, string)) return false;
//        }
//        return true;
//    }

    public static String getStringFromRegExMatch(String regexString, String data) {
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(data);
        return (matcher.find()) ? matcher.group(1) : null;
    }
}
