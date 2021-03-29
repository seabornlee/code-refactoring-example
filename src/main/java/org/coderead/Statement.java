package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private Invoice invoice;
    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Statement for %s", invoice.getCustomer()));
        stringBuilder.append(formatPerformances());
        stringBuilder.append(String.format("Amount owed is %s\n", formatUSD(getTotalAmount())));
        stringBuilder.append(String.format("You earned %s credits\n", getVolumeCredits()));
        return stringBuilder.toString();
    }

    private StringBuilder formatPerformances() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            stringBuilder.append(String.format(" %s: %s (%d seats)\n", play.getName(), formatUSD(getPerformanceCalculator(play).getAmount(performance)), performance.getAudience()));
        }
        return stringBuilder;
    }

    private int getVolumeCredits() {
        int volumeCredits = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            volumeCredits += getPerformanceCalculator(play).getVolumeCredits(performance);
        }
        return volumeCredits;
    }

    private int getTotalAmount() {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            totalAmount += getPerformanceCalculator(play).getAmount(performance);
        }
        return totalAmount;
    }

    private IPerformanceCalculator getPerformanceCalculator(Play play) {
        if ("tragedy".equals(play.getType())) {
            return new TragedyCalculator();
        }

        if ("comedy".equals(play.getType())) {
            return new ComedyCalculator();
        }
        return null;
    }

    private String formatUSD(double amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount / 100);
    }

}
