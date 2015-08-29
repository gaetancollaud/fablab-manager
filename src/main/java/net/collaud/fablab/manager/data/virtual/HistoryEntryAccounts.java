package net.collaud.fablab.manager.data.virtual;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabien Vuilleumier
 */
public enum HistoryEntryAccounts {

    CAISSE_POSTE_BANQUE(1000),
    CREANCES_CLIENT(1100),
    DETTES_FOURNISSEUR(2000),
    MACHINES(1500),
    AUTRES_CHARGES_EXPLOITATION(6700),
    MOBILIER(1510),
    FRAIS_ACHAT(4270),
    PERTES_SUR_CREANCES(3295),
    ENTRETIEN_MACHINE(6100),
    VENTES_MARCHANDISES(3200),
    PRODUIT_EXCEPTIONNEL(8000),
    COTISATIONS(6586),
    HONORAIRES(3400),
    STOCK_DIVERS(1200);

    private final int NUMBER;

    /**
     * Parameterized constructor.
     *
     * @param number the swiss NUMBER
     */
    private HistoryEntryAccounts(final int number) {
        this.NUMBER = number;
    }

    /**
     * Get the type.
     *
     * @return the type (String)
     */
    public int getNumber() {
        return NUMBER;
    }

    public static List<String> names() {
        HistoryEntryAccounts[] accounts = values();
        List<String> names = new ArrayList<>();
        for (HistoryEntryAccounts account : accounts) {
            names.add(account.name());
        }

        return names;
    }

    public static List<String> both() {
        HistoryEntryAccounts[] accounts = values();
        List<String> names = new ArrayList<>();
        for (HistoryEntryAccounts account : accounts) {
            names.add("(" + account.NUMBER + ") " + account.name());
        }

        return names;
    }

    public static String getName(String name) {
        HistoryEntryAccounts hea = HistoryEntryAccounts.valueOf(name);
        StringBuilder res = new StringBuilder();
        res.append("(");
        res.append(hea.getNumber());
        res.append(") ");
        res.append(stringify(name));
        return res.toString();
    }

    private static StringBuilder stringify(String hea) {
        String[] splitted = hea.split("_");
        StringBuilder stringified = new StringBuilder();
        for (String s : splitted) {
            stringified.append(s);
            stringified.append(" ");
        }
        if (stringified.length() > 0) {
            stringified.deleteCharAt(stringified.length() - 1);
        }
        return stringified;
    }
}
