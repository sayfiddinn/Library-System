package org.example.controller;

import org.example.service.AdminService;
import org.example.service.AdminServiceImpl;
import org.example.util.Utils;

public class AdminController {
    private final AdminService adminService = new AdminServiceImpl();

    public void run() {
        while (true) {
            System.out.println("""
                    \n1. Userni bloklash
                    2. Moderator rolini berish
                    3. Moderator rolini bekor qilish
                    4. Userlar ro'yxati
                    0. exit
                    """);
            switch (Utils.scanner.nextLine()) {
                case "1" -> adminService.blockedUser();
                case "2" -> adminService.addModer();
                case "3" -> adminService.removeModer();
                case "4" -> adminService.showUsers();
                case "0" -> {
                    return;
                }
                default -> System.err.println("Mavjud bo'lmagan menu tanlandi ! ");
            }
        }
    }
}
