package vn.pika.moneyshort;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class MoneyShort extends JavaPlugin {

    private Economy _0;

    private static final int[] _moneyshort = new int[]{20044,20005,19997,20217,20156,20125,20351,20271,20251,20454};
    private static final int[] _khangsmp = new int[]{20042,20002,19986,20210,20130,20125,20346,20272};
    private static final int[] _author = new int[]{20074,20002,19986,20210,20130,20157,20314,20240,20297,20415,20379,20372,20581,20567,20529,20732,20675,20661,20845,20811,20798,20758,20934,20926,20894,21061};
    private static final int[] _balance = new int[]{20035,20011,19999,20221,20139,20109,20338};
    private static final int[] _money = new int[]{20044,20005,19997,20217,20156};
    private static final int[] _raw = new int[]{20051,20011,19972};
    private static final int[] _zero = new int[]{19985};
    private static final int[] _minus = new int[]{19980};
    private static final int[] _fmt_int = new int[]{19972,20070,20061,20140,20131};
    private static final int[] _fmt_dec = new int[]{19972,20070,20061,20142,20131};
    private static final int[] _fmt_main = new int[]{19972,20068,20033,20218};
    private static final int[] _k = new int[]{20042};
    private static final int[] _m = new int[]{20044};
    private static final int[] _b = new int[]{20035};
    private static final int[] _t = new int[]{20053};
    private static final int[] _strip_zero = new int[]{19985,20065,20055};
    private static final int[] _strip_dot = new int[]{20093,20068,20055};
    private static final int[] _papi = new int[]{20081,20006,19986,20223,20128,20102,20344,20268,20237,20471,20425,20389,20573,20607};
    private static final int[] _empty = new int[]{};

    @Override
    public void onEnable() {
        try {
            RegisteredServiceProvider<Economy> _r =
                    Bukkit.getServicesManager().getRegistration(Economy.class);
            if (_r != null) _0 = _r.getProvider();
        } catch (Throwable _t) {
            getLogger().warning("Khong lay duoc Vault: " + _t.getMessage());
        }
        if (_0 == null) {
            getLogger().severe("Khong tim thay Vault/Economy -> Tat plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        if (Bukkit.getPluginManager().getPlugin(_O(_papi)) == null) {
            getLogger().severe("Khong tim thay PlaceholderAPI -> Tat plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        try {
            new Exp(_O(_moneyshort)).register();
            new Exp(_O(_khangsmp)).register();
        } catch (Throwable _t) {
            getLogger().warning("Loi nap expansion: " + _t.getMessage());
        }

        getLogger().info("==================================================");
        getLogger().info("  ★ KHANGSMP - PREMIUM EDITION ★");
        getLogger().info("  Plugin: MoneyShort v1.0.0 [Ban Quyen Doc Quyen]");
        getLogger().info("  Phat trien boi: KhangSMP (phantrongkhangg)");
        getLogger().info("  Trang thai: Hoat dong voi Vault & PlaceholderAPI");
        getLogger().info("  Placeholders kich hoat:");
        getLogger().info("    • %moneyshort_balance% (1k, 1.5k, 50k, 2M, 1B)");
        getLogger().info("    • %khangsmp_balance%   (1k, 1.5k, 50k, 2M, 1B)");
        getLogger().info("    • %moneyshort_raw%     (1,000, 50,000)");
        getLogger().info("==================================================");
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
        private final String _id;
        public Exp(String _id) { this._id = _id; }
        @Override public String getIdentifier() { return _id; }
        @Override public String getAuthor() { return _O(_author); }
        @Override public String getVersion() { return getDescription().getVersion(); }
        @Override public boolean persist() { return true; }
        @Override public String onRequest(OfflinePlayer _p, String _i) {
            if (_p == null) return _O(_empty);
            double _b = balanceOf(_p);
            if (_i.equalsIgnoreCase(_O(_balance)) || _i.equalsIgnoreCase(_O(_money))) return fmt(_b);
            if (_i.equalsIgnoreCase(_O(_raw))) {
                if (_b == Math.floor(_b) && !Double.isInfinite(_b))
                    return String.format(_O(_fmt_int), _b);
                return String.format(_O(_fmt_dec), _b);
            }
            return null;
        }
    }

    static String fmt(double _v) {
        if (Double.isNaN(_v) || Double.isInfinite(_v)) return _O(_zero);
        if (_v < 0.0) return _O(_minus) + fmt(-_v);
        if (_v < 1000.0) {
            if (_v == Math.floor(_v)) return String.format(_O(_fmt_int), _v);
            return String.format(_O(_fmt_dec), _v);
        }
        String[] _U = { _O(_k), _O(_m), _O(_b), _O(_t) };
        double _n = _v;
        int _u = -1;
        while (_n >= 1000.0 && _u < _U.length - 1) {
            _n /= 1000.0;
            _u++;
        }
        String _s = String.format(_O(_fmt_main), _n);
        _s = _s.replaceAll(_O(_strip_zero), _O(_empty))
               .replaceAll(_O(_strip_dot), _O(_empty));
        return _s + _U[_u];
    }

    private static String _O(int[] _d) {
        char[] _o = new char[_d.length];
        for (int _i = 0; _i < _d.length; _i++) {
            _o[_i] = (char) ((_d[_i] ^ (20001 + (_i * 41))) & 0xFFFF);
        }
        return new String(_o);
    }
}
