package com.example.qualitydemo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumRegistrationTest {
    @Test
    public void testRegisterPageLoads() throws Exception {
        // Crear un directorio temporal único para evitar conflictos de perfil
        Path tempProfile = Files.createTempDirectory("chrome-profile-");

        // Configurar opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");              // evita abrir ventana (ideal para CI)
        options.addArguments("--no-sandbox");                // evita restricciones de permisos
        options.addArguments("--disable-dev-shm-usage");     // mejora estabilidad en runners
        options.addArguments("--user-data-dir=" + tempProfile.toString()); // perfil único

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("http://localhost:8080/register");
            assertTrue(driver.getPageSource().contains("Register"));
        } finally {
            driver.quit(); // Cierra correctamente la sesión y libera el perfil
        }
    }
}
