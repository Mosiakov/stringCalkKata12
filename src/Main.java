import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение [\"a\" + \"b\", \"a\" - \"b\", \"a\" * x, \"a\" / x] где a и b - строки а x - число  от 1 до 10 включительно  + Enter ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Pattern pattern = Pattern.compile("\"([^\"]+)\"|([^\\s+])");
        Matcher matcher = pattern.matcher(input);
        String str1 = null;
        String operation = null;
        String str2 = null;
        while (matcher.find()) {
            if (str1 == null) {
                str1 = matcher.group(1);
                if (str1 == null) {
                    str1 = matcher.group(2);
                }
            } else if (operation == null) {
                operation = matcher.group(2);
            } else {
                str2 = matcher.group(1);
                if (str2 == null) {
                    str2 = matcher.group(2);
                }
            }
        }
        if (str1 == null || operation == null || str2 == null) {
            throw new RuntimeException("Invalid input");
        }

        if (str1.trim().isEmpty() || str1.length() > 10) {
            throw new RuntimeException("Invalid string");
        }
        if (str2.trim().isEmpty() || str2.length() > 10) {
            throw new RuntimeException("Invalid string");
        }

        if (operation.equals("+")) {
            System.out.println(concatenate(str1, str2));
        } else if (operation.equals("-")) {
            System.out.println(subtract(str1, str2));
        } else if (operation.equals("*")) {
            int num;
            try {
                num = Integer.parseInt(str2);
                if (num < 1 || num > 10) {
                    throw new RuntimeException("Number must be between 1 and 10");
                }
                System.out.println(multiply(str1, num));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number");
            }
        } else if (operation.equals("/")) {
            int num;
            try {
                num = Integer.parseInt(str2);
                if (num < 1 || num > 10) {
                    throw new RuntimeException("Number must be between 1 and 10");
                }
                System.out.println(divide(str1, num));
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number");
            }
        }
    }

    private static String concatenate(String str1, String str2) {
        String result = str1 + str2;
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }

    private static String subtract(String str1, String str2) {
        String result = str1.replace(str2, "");
        if (result.endsWith(" ")) {
            result = result.trim();
        }
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }

    private static String multiply(String str, int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(str);
        }
        String resultStr = result.toString();
        if (resultStr.length() > 40) {
            return resultStr.substring(0, 40) + "...";
        }
        return resultStr;
    }

    private static String divide(String str, int num) {
        if (str.length() < num) {
            return "";
        }
        String result = str.substring(0, str.length() / num);
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}