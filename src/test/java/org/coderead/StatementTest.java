package org.coderead;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.coderead.model.Invoice;
import org.coderead.model.Play;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * 新建类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class StatementTest {

    @Test
    public void test() {
        String expected = "Statement for BigCo Hamlet: $650.00 (55 seats)\n" +
                " As You Like It: $580.00 (35 seats)\n" +
                " Othello: $500.00 (40 seats)\n" +
                " Ji Xie Shi: $500.00 (40 seats)\n" +
                " Bai She Zhuan: $500.00 (40 seats)\n" +
                "Amount owed is $2,730.00\n" +
                "You earned 67 credits\n";
        final String plays = "{" +
                "\"hamlet\":{\"name\":\"Hamlet\",\"type\":\"tragedy\"}," +
                "\"as-like\":{\"name\":\"As You Like It\",\"type\":\"comedy\"}," +
                "\"othello\":{\"name\":\"Othello\",\"type\":\"tragedy\"}," +
                "\"jxs\":{\"name\":\"Ji Xie Shi\",\"type\":\"action\"}," +
                "\"bsz\":{\"name\":\"Bai She Zhuan\",\"type\":\"love\"}" +
                "}";

        final String invoices = "{" +
                "\"customer\":\"BigCo\",\"performances\":[" +
                "{\"playId\":\"hamlet\",\"audience\":55}" +
                "{\"playId\":\"as-like\",\"audience\":35}" +
                "{\"playId\":\"othello\",\"audience\":40}" +
                "{\"playId\":\"jxs\",\"audience\":40}" +
                "{\"playId\":\"bsz\",\"audience\":40}" +
                "]" +
                "}";

        TypeReference<Map<String, Play>> typeReference = new TypeReference<Map<String, Play>>(){};
        Map<String, Play> playMap = JSONObject.parseObject(plays, typeReference);
        Invoice invoice = JSONObject.parseObject(invoices, Invoice.class);
        Statement statement = new Statement(invoice, playMap);
        String result = statement.show();
        Assert.assertEquals(expected, result);
    }
}
