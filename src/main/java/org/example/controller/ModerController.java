package org.example.controller;

import org.example.service.ModerService;
import org.example.service.ModerServiceImpl;
import org.example.util.Utils;

public class ModerController {
    private final ModerService moderService = new ModerServiceImpl();

    public void run() {
        while (true) {
            System.out.println("""
                    \n1. Yangi kitob qo'shish
                    2. Mavjud kitob qo'shish
                    3. Kitob qidirish
                    4. Bron qilingan kitoblar
                    5. Deadline muddati o'tgan kitoblar
                    6. Olib ketilgan kitobni belgilash
                    0. exit
                    """);
            switch (Utils.scanner.nextLine()) {
                case "1" -> moderService.addNewBook();
                case "2" -> moderService.addBook();
                case "3" -> moderService.searchBook();
                case "4" -> moderService.showBooking();
                case "5" -> moderService.showDeadline();
                case "6" -> moderService.takenBook();
                case "0" -> {
                    return;
                }
                default -> System.err.println("Mavjud bo'lmagan menu tanlandi ! ");
            }
        }
    }
}
