package HastaneProjesi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MethodClass {
    static List<Durum> hastaDurumList = new ArrayList<>();
    static List<Doktor> doktorList = new ArrayList<>();
    static VeriBankasi veriBankasi = new VeriBankasi();
    static Scanner scan = new Scanner(System.in);

    public static void giris() throws InterruptedException {
        System.out.println("Hastane Bilgi İşlem Merkezine Hoşgeldiniz.");
        System.out.println("Lutfen Asagidaki menuden yapmak istediginiz islemi seciniz:");
        System.out.println("1. Doktor Bilgilerini Listele");
        System.out.println("2. Hasta Bilgilerini Listele");
        System.out.println("3. Doktor Bul");
        System.out.println("4. Hasta Bul");
        System.out.println("5. Doktor Unvan Bul");
        System.out.println("0. Cikis");
        int secim = scan.nextInt();
        switch (secim) {
            case 1: {
                doktorBilgileriniListele();
                System.out.println("Lutfen Bekleyiniz...");
                Thread.sleep(5000);
                giris();
            }
            case 2:
                hastaBilgileriniListele();
                System.out.println("Lutfen Bekleyiniz...");
                Thread.sleep(5000);
                giris();
            case 3: {
                for (int i = 0; i < veriBankasi.unvanlar.length; i++) {
                    System.out.println(veriBankasi.unvanlar[i]);
                }
                System.out.print("Lutfen listeden bir unvan seciniz: ");
                String unvan = scan.nextLine();
                Doktor arananDoktor = new Doktor();
                arananDoktor = doktorBul(unvan);
                System.out.println("Aradiginiz kriterlere uygun sonuc bulundu...");
                System.out.println(arananDoktor.getUnvan() + ", " + arananDoktor.getIsim() + " " + arananDoktor.getSoyIsim());
                System.out.println("Lutfen Bekleyiniz...");
                Thread.sleep(5000);
                giris();
            }
            case 4: {
                for (int i = 0; i < veriBankasi.hastaIsimleri.length; i++) {
                    System.out.println(veriBankasi.durumlar[i]);
                }
                System.out.println("Hangi durumdaki hastalari goruntulemek istiyorsunuz ?");
                String arananDurum = scan.nextLine();
                Hasta arananHasta = new Hasta();
                arananHasta = hastaBul(arananDurum);
                System.out.println("Aradiginiz kriterlere uygun sonuc bulundu...");
                System.out.println("ID : " + arananHasta.getHastaID() + ", " + arananHasta.getIsim() + ", "
                        + arananHasta.getSoyIsim() + " Durumu : " + arananHasta.getHastaDurumu().getAktuelDurum() +
                        " Acil mi ? " + arananHasta.getHastaDurumu().isAciliyet());
                System.out.println("Lutfen Bekleyiniz...");
                Thread.sleep(5000);
                giris();
            }
            case 5: {
                for (int i = 0; i < veriBankasi.durumlar.length; i++) {
                    System.out.println(veriBankasi.durumlar[i]);
                }
                System.out.println("Listeden bir unvan seciniz.");
                scan.nextLine();
                String unvanSecim = scan.nextLine();
                String bulunanUnvan = doktorUnvan(unvanSecim);
                System.out.println("Doktor unvan : " + bulunanUnvan);
                System.out.println("Lutfen Bekleyiniz...");
                Thread.sleep(5000);
                giris();
            }
            case 0: {
                System.out.println("Saglikli gunler dileriz...");
                System.exit(0);
            }
            default: {
                System.out.println("Hatali giris yaptiniz. Tekrar deneyin.");
                giris();
            }
        }
    }

    public static void doktorBilgileriniListele() {
        for (int i = 0; i < veriBankasi.doctorIsimleri.length; i++) {
            System.out.print(i + 1 + ": Doktor ismi : " + veriBankasi.doctorIsimleri[i] + " , ");
            System.out.print("Doktor soyismi : " + veriBankasi.doctorSoyIsimleri[i] + " , ");
            System.out.print("Doktor unvanı : " + veriBankasi.unvanlar[i] + " . ");
            System.out.println();
        }
    }

    public static void hastaBilgileriniListele() {
        for (int i = 0; i < veriBankasi.hastaIDleri.length; i++) {
            System.out.print(i + 1 + ": Hasta ID : " + veriBankasi.hastaIDleri[i] + " , ");
            System.out.print("Hasta ismi : " + veriBankasi.hastaIsimleri[i] + " , ");
            System.out.print("Hasta soyismi : " + veriBankasi.hastaSoyIsimleri[i] + " , ");
            System.out.print("Hasta durumu : " + hastaDurumList.get(i).getAktuelDurum() + " , ");
            System.out.print("Aciliyet durumu : " + hastaDurumList.get(i).isAciliyet() + " . ");
            System.out.println();
        }
    }

    public static String doktorUnvan(String aktuelDurum) {
        if (aktuelDurum.equalsIgnoreCase("Allerji")) return "Allergist";
        else if (aktuelDurum.equalsIgnoreCase("Bas agrisi")) return "Norolog";
        else if (aktuelDurum.equalsIgnoreCase("Diabet")) return "Genel Cerrah";
        else if (aktuelDurum.equalsIgnoreCase("Soguk Alginligi")) return "Cocuk doktoru";
        else if (aktuelDurum.equalsIgnoreCase("Migren")) return "Dahiliye";
        else if (aktuelDurum.equalsIgnoreCase("Kalp hastaliklari")) return "Kardiolog";
        else return "yanlis unvan";
    }

    public static Doktor doktorBul(String unvan) {
        boolean bulunnduMu = false;
        int doktorIndex = -1;
        for (int i = 0; i < doktorList.size(); i++) {
            if (doktorList.get(i).getUnvan().equalsIgnoreCase(unvan)) {
                bulunnduMu = true;
                doktorIndex = i;
            }
        }
        if (bulunnduMu) return doktorList.get(doktorIndex);
        else return null;
    }

    public static Hasta hastaBul(String actualCase) {
        Hasta arananHasta = new Hasta();
        for (int i = 0; i < hastaDurumList.size(); i++) {
            if (hastaDurumList.get(i).getAktuelDurum().equalsIgnoreCase(actualCase)) {
                arananHasta.setIsim(veriBankasi.doctorIsimleri[i]);
                arananHasta.setSoyIsim(veriBankasi.doctorSoyIsimleri[i]);
                arananHasta.setHastaID(veriBankasi.hastaIDleri[i]);
                arananHasta.setHastaDurumu(hastaDurumuBul(actualCase));
            }
        }
        return arananHasta;
    }

    public static Durum hastaDurumuBul(String aktuelDurum) {

        boolean bulunnduMu = false;
        int aktuelDurumIndex = -1;
        for (int i = 0; i < hastaDurumList.size(); i++) {
            if (hastaDurumList.get(i).getAktuelDurum().equalsIgnoreCase(aktuelDurum)) {
                bulunnduMu = true;
                aktuelDurumIndex = i;
            }
        }
        if (bulunnduMu) return hastaDurumList.get(aktuelDurumIndex);
        else return null;
    }


    public static void hastaDurumuListGuncelle() {
        for (int i = 0; i < veriBankasi.durumlar.length; i++) {
            Durum hastaDurumu = new Durum();
            hastaDurumu.setAktuelDurum(veriBankasi.durumlar[i]);
            switch (veriBankasi.durumlar[i]) {
                case "Allerji":
                    hastaDurumu.setAciliyet(false);
                    break;
                case "Bas Agrisi":
                    hastaDurumu.setAciliyet(false);
                    break;
                case "Diabet":
                    hastaDurumu.setAciliyet(false);
                    break;
                case "Soguk alginligi":
                    hastaDurumu.setAciliyet(false);
                    break;
                case "Migren":
                    hastaDurumu.setAciliyet(true);
                    break;
                case "Kalp hastaliklari":
                    hastaDurumu.setAciliyet(true);
                    break;
                default:
                    break;
            }
            hastaDurumList.add(hastaDurumu);
        }
    }

    public static void doktorListGuncelle() {
        for (int i = 0; i < veriBankasi.unvanlar.length; i++) {
            Doktor doktor = new Doktor();
            doktor.setIsim(veriBankasi.doctorIsimleri[i]);
            doktor.setSoyIsim(veriBankasi.doctorSoyIsimleri[i]);
            doktor.setUnvan(veriBankasi.unvanlar[i]);
            doktorList.add(doktor);
        }
    }
}
