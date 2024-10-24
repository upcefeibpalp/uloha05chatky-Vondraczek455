package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    public static void main(String[] args) {
        final int KONEC_PROGRAMU = 0;
        final int VYPIS_CHATEK = 1;
        final int VYPIS_KONKRETNI_CHATKU = 2;
        final int PRIDANI_NAVSTEVNIKU = 3;
        final int ODEBRANI_NAVSTEVNIKU = 4;
        final int CELKOVA_OBSAZENOST = 5;
        final int VYPIS_PRAZDNE_CHATKY = 6;

        final int VELIKOST_KEMPU = 5;
        final int MAX_VELIKOST_CHATKY = 10;

        Scanner scanner = new Scanner(System.in);
        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            System.out.println("""
                MENU:
                1 - Vypsat všechny chatky
                2 - Vypsat konkrétní chatku
                3 - Přidat návštěvníky
                4 - Odebrat návštěvníky
                5 - Celková obsazenost kempu
                6 - Vypsat prázdné chatky
                0 - Konec programu
                """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> vypisChatky(chatky);
                case VYPIS_KONKRETNI_CHATKU -> vypisKonkretniChatku(scanner, chatky);
                case PRIDANI_NAVSTEVNIKU -> zmenNavstevniky(scanner, chatky, MAX_VELIKOST_CHATKY, true);
                case ODEBRANI_NAVSTEVNIKU -> zmenNavstevniky(scanner, chatky, MAX_VELIKOST_CHATKY, false);
                case KONEC_PROGRAMU -> System.out.println("Konec programu");
                default -> System.err.println("Neplatna volba");
            }
        } while (operace != 0);
    }

    private static void vypisChatky(int[] chatky) {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    private static void vypisKonkretniChatku(Scanner scanner, int[] chatky) {
        int cisloChatky = zadejCisloChatky(scanner, chatky.length);
        if (cisloChatky != -1) {
            System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
        }
    }

    private static void zmenNavstevniky(Scanner scanner, int[] chatky, int maxVelikost, boolean pridat) {
        int cisloChatky = zadejCisloChatky(scanner, chatky.length);
        if (cisloChatky == -1) return;

        System.out.print("Zadej počet návštěvníků: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0) {
            System.err.println("Neplatná hodnota pro počet návštěvníků");
            return;
        }

        if (pridat) {
            if (chatky[cisloChatky] + pocetNavstevniku > maxVelikost) {
                System.err.println("Kapacita chatky byla překročena.");
            } else {
                chatky[cisloChatky] += pocetNavstevniku;
                System.out.println("Návštěvníci přidáni.");
            }
        } else {
            if (chatky[cisloChatky] - pocetNavstevniku < 0) {
                System.err.println("Nelze odebrat více návštěvníků, než je v chatce.");
            } else {
                chatky[cisloChatky] -= pocetNavstevniku;
                System.out.println("Návštěvníci odebráni.");
            }
        }
    }

    private static int zadejCisloChatky(Scanner scanner, int maxCislo) {
        System.out.print("Zadej číslo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;
        if (cisloChatky < 0 || cisloChatky >= maxCislo) {
            System.err.println("Tato chatka neexistuje");
            return -1;
        }
        return cisloChatky;
    }
}