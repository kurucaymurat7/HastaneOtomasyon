package HastaneProjesi;

import java.awt.desktop.ScreenSleepEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HastaneRunner {

    public static void main(String[] args) throws InterruptedException {
        MethodClass.hastaDurumuListGuncelle(); // Arraydeki bilgileri Lİst'e atar.
        MethodClass.doktorListGuncelle();// Arraydeki bilgileri Lİst'e atar.
        Scanner scan = new Scanner(System.in);
        int kalanhak = 3;
        for (int i = 0; i < 3; i++) {
            System.out.println("Username : ");
            String username = scan.next();
            System.out.println("password");
            String password = scan.next();
            if (username.equalsIgnoreCase(password)) {
                MethodClass.giris();
            } else {
                if (kalanhak > 1) System.out.println("Username password yanlis girildi. Tekrar deneyiniz. Kalan hak : " + --kalanhak);
            }
        }
        kalanhak--;
        if (kalanhak == 0) System.out.println("3 kez hatali giris yapildi. Sistem kapatiliyor.");
    }


}
