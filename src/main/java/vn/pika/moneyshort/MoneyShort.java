package vn.pika.moneyshort;

import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * MoneyShort v1.0 - plugin PAPI rieng: %moneyshort_balance% + %moneyshort_raw%.
 * Doc so du truc tiep tu Vault (EssentialsX Economy), khong lien quan EggKhang.
 * Tu viet 100%.
 */
public class MoneyShort extends JavaPlugin {

    private Economy econ;

    @Override
    public void onEnable() {
        try {
            RegisteredServiceProvider<Economy> rsp =
                    Bukkit.getServicesManager().getRegistration(Economy.class);
            if (rsp != null) econ = rsp.getProvider();
        } catch (Throwable t) {
            getLogger().warning("Khong lay duoc Vault Economy: " + t.getMessage());
        }
        if (econ == null) {
            getLogger().severe("Khong tim thay Vault/Economy -> tat MoneyShort!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("Khong tim thay PlaceholderAPI -> tat MoneyShort!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        try {
            boolean ok = new Exp().register();
            getLogger().info("MoneyShort v1.0 da bat! %moneyshort_balance% = " + ok);
        } catch (Throwable t) {
            getLogger().warning("Khong dang ky duoc expansion: " + t.getMessage());
        }
    }

    public double balanceOf(OfflinePlayer p) {
        try {
            if (econ == null || p == null) return 0;
            return econ.getBalance(p);
        } catch (Throwable t) {
            return 0;
        }
    }

    public class Exp extends PlaceholderExpansion {
        @Override public String getIdentifier() { return "moneyshort"; }
        @Override public String getAuthor() { return "PikaMC"; }
        @Override public String getVersion() { return getDescription().getVersion(); }
        @Override public boolean persist() { return true; }
        @Override public String onRequest(OfflinePlayer p, String id) {
            if (p == null) return "";
            double bal = balanceOf(p);
            if (id.equalsIgnoreCase("balance")) return fmt(bal);
            if (id.equalsIgnoreCase("raw")) {
                if (bal == Math.floor(bal) && !Double.isInfinite(bal)) return String.format("%,.0f", bal);
                return String.format("%,.2f", bal);
            }
            return null;
        }
    }

    /** 950 -> 950, 1550000 -> 1.55m, 10000 -> 10k, 2300000000 -> 2.3b. */
    static String fmt(double v) {
        if (Double.isNaN(v) || Double.isInfinite(v)) return "0";
        if (v < 0) return "-" + fmt(-v);
        if (v < 1000) {
            if (v == Math.floor(v)) return String.format("%,.0f", v);
            return String.format("%,.2f", v);
        }
        String[] units = {"k", "m", "b", "t"};
        double n = v;
        int u = -1;
        while (n >= 1000 && u < units.length - 1) {
            n /= 1000.0;
            u++;
        }
        String s = String.format("%.2f", n);
        s = s.replaceAll("0+$", "").replaceAll("\\.$", "");
        return s + units[u];
    }
}
