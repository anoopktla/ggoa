package ggoa.util;


import static java.lang.Math.abs;

public class NumberFormatUtil {
    static String unitsMap[] = { "zero", "one", "two", "three", "four", "five","six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    static String tensMap[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

    public static String numberToWords(int number)
    {
        if (number == 0)
            return "zero";

        if (number < 0)
            return "minus " +numberToWords(abs(number));

        String words = "";

        if ((number / 10000000) > 0)
        {
            words += numberToWords(number / 10000000) + " crore ";
            number %= 10000000;
        }

        if ((number / 100000) > 0)
        {
            words += numberToWords(number / 100000) + " lakh ";
            number %= 100000;
        }

        if ((number / 1000) > 0)
        {
            words += numberToWords(number / 1000) + " thousand ";
            number %= 1000;
        }

        if ((number / 100) > 0)
        {

                words += numberToWords(number / 100) + " hundred ";
                number %= 100;
        }

        if (number > 0)
        {
            if (number < 20){
                if(words.length()>3){
                words +="and "+ unitsMap[number];
                }else {
                    words += unitsMap[number];

                }
                }
            else
            {
                if(words.length()>3){
                words +="and "+ tensMap[number / 10];}
                else {
                    words +=tensMap[number / 10];

                }
                if ((number % 10) > 0)
                    words += "-" + unitsMap[number % 10];
            }
        }

        return words;
    }
}
