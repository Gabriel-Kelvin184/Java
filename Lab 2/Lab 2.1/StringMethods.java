public class StringMethods {

    public static void main(String[] args) {
        // The given text
        String text = "“We realizes that while our workers were thriving, the\n" +
                "surrounding villages were still suffering. It became our goal to uplift their\n" +
                "quality of life as well. I remember seeing a family of 4 on a motorbike in the\n" +
                "heavy Bombay rain — I knew I wanted to do more for these families who were\n" +
                "risking their lives for lack of an alternative” The alternative mentioned here\n" +
                "is the Tata Nano, which soon after came as the world’s cheapest car on retail\n" +
                "at a starting price of only Rs 1 lakh. These were the words of Ratan Tata in a\n" +
                "recent post by Humans of Bombay which formed the basis of his decision to come\n" +
                "up with a car like Tata Nano.";

        // String charAt()
        System.out.println("The character at index 10 is " + text.charAt(10));

        // String compareTo()
        String anotherText = "This is another text";
        System.out.println("The comparison result is " + text.compareTo(anotherText));

        // String concat()
        System.out.println("The concatenated string is " + text.concat(anotherText));

        // String contains()
        System.out.println("Does the text contain 'Tata'? " + text.contains("Tata"));

        // String endsWith()
        System.out.println("Does the text end with 'Nano.'? " + text.endsWith("Nano."));

        // String equals()
        System.out.println("Is the text equal to anotherText? " + text.equals(anotherText));

        // String equalsIgnoreCase()
        System.out.println("Is the text equal to 'text' ignoring case? " + text.equalsIgnoreCase("text"));

        // String format()
        System.out.println("The formatted string is " + String.format("The text has %d characters", text.length()));

        // String getBytes()
        byte[] bytes = text.getBytes();
        System.out.println("The byte array is ");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();

        // String getChars()
        char[] chars = new char[10];
        text.getChars(0, 10, chars, 0);
        System.out.println("The destination array is ");
        for (char c : chars) {
            System.out.print(c + " ");
        }
        System.out.println();

        // String indexOf()
        System.out.println("The index of 'Tata' is " + text.indexOf("Tata"));

        // String intern()
        System.out.println("The interned string is " + text.intern());

        // String isEmpty()
        System.out.println("Is the text empty? " + text.isEmpty());

        // String join()
        String[] words = text.split(" ");
        System.out.println("The joined string is " + String.join("-", words));

        // String lastIndexOf()
        System.out.println("The last index of 'Tata' is " + text.lastIndexOf("Tata"));

        // String length()
        System.out.println("The length of the text is " + text.length());

        // String replace()
        System.out.println("The replaced string is " + text.replace("Tata", "Bing"));

        // String replaceAll()
        System.out.println("The replaced string is " + text.replaceAll("[aeiou]", "*"));

        // String split()
        String[] sentences = text.split("\\.");
        System.out.println("The array of substrings is ");
        for (String s : sentences) {
            System.out.println(s);
        }

        // String startsWith()
        System.out.println("Does the text start with 'We'? " + text.startsWith("We"));

        // String substring()
        System.out.println("The substring from index 10 to 20 is " + text.substring(10, 20));

        // String toCharArray()
        char[] charArray = text.toCharArray();
        System.out.println("The character array is ");
        for (char c : charArray) {
            System.out.print(c + " ");
        }
        System.out.println();

        // String toLowerCase()
        System.out.println("The lower case string is " + text.toLowerCase());

        // String toUpperCase()
        System.out.println("The upper case string is " + text.toUpperCase());

        // String trim()
        System.out.println("The trimmed string is " + text.trim());

        // String valueOf()
        System.out.println("The string representation of 123 is " + String.valueOf(123));
    }
}
