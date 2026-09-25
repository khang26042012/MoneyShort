package vn.pika.moneyshort;

import java.util.logging.Logger;

import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * MoneyShort v1.0.0
 * Protected & Obfuscated Build for KhangSMP
 */
public class MoneyShort extends JavaPlugin {

    private Economy _0;

    @Override
    public void onEnable() {
        try {
            RegisteredServiceProvider<Economy> _r =
                    Bukkit.getServicesManager().getRegistration(Economy.class);
            if (_r != null) _0 = _r.getProvider();
        } catch (Throwable _t) {
            getLogger().warning(_O(new int[]{20074,20002,19996,20210,20146,20150,20379,20262,20250,20463,20395,20370,20586,20573,20520,20500,20731,20689,20658,20874,20818,20803,20970,20959,20904,20888,21111,21087,21045,21271}, 20001) + _t.getMessage());
        }
        if (_0 == null) {
            getLogger().severe(_O(new int[]{20074,20002,19996,20210,20146,20150,20379,20266,20246,20455,20407,20370,20593,20562,20528,20487,20719,20719,20649,20874,20810,20812,20967,20958,20888,20886,21143,21099,21029,21271,21213,21200,21363,21334,21303,21298,21495,21457,21417,21663,21611,21607,21782,21786}, 20001));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (Bukkit.getPluginManager().getPlugin(_O(new int[]{20081,20006,19998,20214,20144,20138,20370,20278,20235,20473,20384,20410,20566,20570}, 20001)) == null) {
            getLogger().severe(_O(new int[]{20074,20002,19996,20210,20146,20150,20379,20266,20246,20455,20407,20370,20593,20562,20528,20507,20716,20710,20668,20875,20815,20807,20986,20942,20894,20904,21124,21101,21029,21271,21213,21200,21363,21334,21303,21298,21495,21457,21417,21663,21611,21607,21782,21786}, 20001));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        try {
            boolean _k = new Exp().register();
            getLogger().info(_O(new int[]{20076,20005,19997,20217,20156,20113,20370,20273,20247,20468,20421,20400,20575,20573,20531,20480,20711,20714,20649,20888,20822,20858,20979,20953,20881,20880,21148,21110,21034,21275,21211,21215,21367,21332,21319,21290,21503,21442,21408,21682,21611,21651,21774,21789}, 20001) + _k);
        } catch (Throwable _t) {
            getLogger().warning(_O(new int[]{20074,20002,19996,20210,20146,20150,20379,20262,20254,20456,20392,20370,20598,20556,20520,20501,20714,20710,20649,20874,20801,20803,20967,20955,20895,20890,21136,21098,21045,21271}, 20001) + _t.getMessage());
        }
    }

    public double balanceOf(OfflinePlayer _p) {
        try {
            if (_0 == null || _p == null) return 0.0;
            return _0.getBalance(_p);
        } catch (Throwable _t) {
            return 0.0;
        }
    }

    public class Exp extends PlaceholderExpansion {
        @Override public String getIdentifier() { return _O(new int[]{20044,20005,19997,20217,20156,20125,20351,20271,20251,20454}, 20001); }
        @Override public String getAuthor() { return _O(new int[]{20081,20003,19992,20221,20104,20141}, 20001); }
        @Override public String getVersion() { return getDescription().getVersion(); }
        @Override public boolean persist() { return true; }
        @Override public String onRequest(OfflinePlayer _p, String _i) {
            if (_p == null) return _O(new int[]{}, 20001);
            double _b = balanceOf(_p);
            if (_i.equalsIgnoreCase(_O(new int[]{20035,20011,19999,20221,20139,20109,20338}, 20001))) return fmt(_b);
            if (_i.equalsIgnoreCase(_O(new int[]{20051,20011,19972}, 20001))) {
                if (_b == Math.floor(_b) && !Double.isInfinite(_b))
                    return String.format(_O(new int[]{19972,20070,20061,20140,20131}, 20001), _b);
                return String.format(_O(new int[]{19972,20070,20061,20142,20131}, 20001), _b);
            }
            return null;
        }
    }

    static String fmt(double _v) {
        if (Double.isNaN(_v) || Double.isInfinite(_v)) return _O(new int[]{19985}, 20001);
        if (_v < 0.0) return _O(new int[]{19980}, 20001) + fmt(-_v);
        if (_v < 1000.0) {
            if (_v == Math.floor(_v)) return String.format(_O(new int[]{19972,20070,20061,20140,20131}, 20001), _v);
            return String.format(_O(new int[]{19972,20070,20061,20142,20131}, 20001), _v);
        }
        String[] _U = {
            _O(new int[]{20042}, 20001),
            _O(new int[]{20044}, 20001),
            _O(new int[]{20035}, 20001),
            _O(new int[]{20053}, 20001)
        };
        double _n = _v;
        int _u = -1;
        while (_n >= 1000.0 && _u < _U.length - 1) {
            _n /= 1000.0;
            _u++;
        }
        String _s = String.format(_O(new int[]{19972,20068,20033,20218}, 20001), _n);
        _s = _s.replaceAll(_O(new int[]{19985,20065,20055}, 20001), _O(new int[]{}, 20001))
               .replaceAll(_O(new int[]{20093,20068,20055}, 20001), _O(new int[]{}, 20001));
        return _s + _U[_u];
    }

    private static String _O(int[] _d, int _k) {
        char[] _o = new char[_d.length];
        for (int _i = 0; _i < _d.length; _i++) {
            _o[_i] = (char) ((_d[_i] ^ (_k + (_i * 41))) & 0xFFFF);
        }
        return new String(_o);
    }
}
