package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Password login = NIM kamu, ganti sesuai NIM masing-masing
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "20210140019"; // <-- GANTI DENGAN NIM KAMU

    // Menyimpan data secara temporary (tidak pakai database)
    private static List<User> dataUsers = new ArrayList<>();

    // ===================== LOGIN =====================

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        // Jika sudah login, langsung ke home
        if (session.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau password salah!");
            return "login";
        }
    }

    // ===================== HOME =====================

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        // Cek apakah sudah login
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        model.addAttribute("dataUsers", dataUsers);
        return "home";
    }

    // ===================== FORM =====================

    @GetMapping("/form")
    public String formPage(HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        return "form";
    }

    @PostMapping("/form")
    public String formSubmit(@RequestParam String nama,
                             @RequestParam String nim,
                             @RequestParam String jenisKelamin,
                             HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        // Simpan data ke list (temporary, hilang saat server restart)
        dataUsers.add(new User(nama, nim, jenisKelamin));
        return "redirect:/home";
    }

    // ===================== LOGOUT =====================

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
