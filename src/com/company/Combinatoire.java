package com.company;

public class Combinatoire {

    public static int fact(int n) {
        return n;
    }

    public static int coeffBinomial(int n, int k) {
        return (Combinatoire.fact(n)) / (Combinatoire.fact(k) * Combinatoire.fact(n - k));
    }

    public static int permutationId(int n) {
        return Combinatoire.fact(n);
    }

    public static int permutationDif(int n, int[] m) {
        int l = 0;
        for (int i = 0; i < m.length; i++) {
            l += Combinatoire.fact(m[i]);
        }
        return (Combinatoire.fact(n)) / l;
    }

    public static int presenceDansF(int e, int f) {
        return f / e;
    }

    public static int anagrammeAvecDispoObli(int n, int k) {
        return (Combinatoire.fact(n)) / (Combinatoire.fact(n - k));
    }


}
