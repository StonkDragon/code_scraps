package tk.stonkdragon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONObject;

/**
 * Unit test for simple App.
 */
public class HypixelSkyblockBoosterCookieToUSD {
    public static void main(String[] args) throws MalformedURLException, IOException {
        File file = new File("bazaar.json");
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        CoatlWeb web = new CoatlWeb();
        web.saveWebPage("https://api.hypixel.net/skyblock/bazaar", "bazaar.json");
        System.out.println("");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String data = br.readLine();
        JSONObject bazaar = new JSONObject(data);
        double output = bazaar.getJSONObject("products").getJSONObject("BOOSTER_COOKIE").getJSONArray("sell_summary").getJSONObject(0).getLong("pricePerUnit");

        double cpb = output / 325;
        double cookiesper5dollar = 675/325;
        double coinsper5dollar = output * cookiesper5dollar;
        double coinsperdollar = coinsper5dollar / 5.94;
        String dollarstospends = System.console().readLine();
        int dollarstospend = Integer.parseInt(dollarstospends);

        System.out.println("Coins per Gem: " + (long)cpb + " Coins (assuming Base Gem Package)");
        System.out.println("Coins per Dollar: " + (long)coinsperdollar + " Coins (assuming Base Gem Package)");
        System.out.println("You will get " + (dollarstospend * (long)coinsperdollar) + " Coins for " + dollarstospend + " Dollars");

        file.delete();
        br.close();
    }
}
