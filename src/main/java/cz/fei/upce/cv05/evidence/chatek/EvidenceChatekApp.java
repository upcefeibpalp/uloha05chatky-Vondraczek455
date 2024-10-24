package cz.fei.upce.cv05.evidence.chatek;

import java.util.Arrays;
import java.util.Scanner;

public class EvidenceChatekApp implements Runnable{
    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 5;
    static final int MAX_VELIKOST_CHATKY = 10;

    static int[] chatky = new int[VELIKOST_KEMPU];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EvidenceChatekApp evidenceChatekApp = new EvidenceChatekApp();
        evidenceChatekApp.run();

    }


    @Override
    public void run() {
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> System.out.println(vypisChattek());
                case VYPIS_KONKRETNI_CHATKU -> vypisKonkretniChatku();
                case PRIDANI_NAVSTEVNIKU -> pridaniNavstevniku();
                case ODEBRANI_NAVSTEVNIKU -> odebraniNavstevniku();
                case CELKOVA_OBSAZENOST -> celkovaobsazenost();
                case VYPIS_PRAZDNE_CHATKY -> vypisPrazdneChatky();
                case KONEC_PROGRAMU -> System.out.println("Konec programu");
                default -> System.err.println("Neplatna volba");
            }
        } while (operace != 0);
    }

    public static String vypisChattek() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chatky.length; i++) {
            result.append("Chatka [").append(i + 1).append("] = ").append(chatky[i]).append("\n");
        }
        return result.toString();
    }

    public static void vypisKonkretniChatku() {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;

        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
    }

    public static void pridaniNavstevniku() {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;

        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }

        chatky[cisloChatky] += pocetNavstevniku;
        System.out.println("Navstevnici byli pridaní. Novy pocet: " + chatky[cisloChatky]);
    }

    public static void odebraniNavstevniku() {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;

        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        System.out.print("Zadej pocet navstevniku co odeberete: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        if ((chatky[cisloChatky] - pocetNavstevniku) >= 0) {
            chatky[cisloChatky] -= pocetNavstevniku;
            System.out.println("Hotovo! Novy pocet navstevniku: " + chatky[cisloChatky]);
        } else {
            System.err.println("Prekrocen min pocet navstevniku chatky");
        }
    }


    public static void celkovaobsazenost(){
        int a = 0;
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
            a += chatky[i];
        }
        System.out.println("Celková obsazenost:" + a);
    }


    public static void vypisPrazdneChatky(){
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0){
                System.out.println("Chatka [" + (i + 1) + "] má obsazeno 0");
            }
        }
    }
}