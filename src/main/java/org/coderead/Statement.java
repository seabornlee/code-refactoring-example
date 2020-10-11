package org.coderead;

import org.coderead.enums.PlayType;
import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 账单类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    public static String statement(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s", invoice.getCustomer());
        StringBuilder stringBuilder = new StringBuilder(result);

        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            int thisAmount = 0;
            PlayType playType = PlayType.valueOf(play.getType().toUpperCase());
            switch (playType) {
                case TRAGEDY:
                    thisAmount = 40000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case COMEDY:
                    thisAmount = 30000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10000 + 500 *(performance.getAudience() - 20);
                    }
                    thisAmount += 300 * performance.getAudience();
                    break;
                default:
                    throw new RuntimeException("unknown type:" + play.getType());
            }

            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            if (PlayType.COMEDY.equals(playType)) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            stringBuilder.append(String.format(" %s: %s (%d seats)\n", play.getName(), format.format(thisAmount/100), performance.getAudience()));
            totalAmount += thisAmount;
        }
        stringBuilder.append(String.format("Amount owed is %s\n", format.format(totalAmount/100)));
        stringBuilder.append(String.format("You earned %s credits\n", volumeCredits));
        return stringBuilder.toString();
    }
}
