import model.CorporateRent;
import model.Rent;
import model.UserRent;
import service.NotificationService;
import service.impl.EmailNotification;
import service.impl.SMSNotification;
import Billing.CampingStore;
import exception.DuplicateRentException;
import exception.InvalidEmailException;
import exception.RentNotFoundException;

public class Main {
    public static void main(String[] args) {
        NotificationService SMSservice = new SMSNotification();
        NotificationService Emailservice = new EmailNotification();
        CampingStore billing = new CampingStore(SMSservice);

        System.out.println("=== Сценарий 1: Успешная оплата ===");
        try {
            Rent userSub = new UserRent("Иван Петров", 200.0, "Large");
            billing.registerRent("ivan@mail.ru", userSub);
            billing.ProcessPayment("ivan@mail.ru", "+7-999-123-45-67");
            System.out.println("Оплата прошла успешно!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Сценарий 2: Оплата несуществующего пользователя ===");
        try {
            billing.ProcessPayment("fake@mail.com", "+7-999-123-45-67");
        } catch (RentNotFoundException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n=== Сценарий 3: Регистрация с ошибкой (неверный email) ===");
        try {
            Rent corpSub = new CorporateRent("ООО Тмыв денег", 300.0, "Small", 10);
            billing.registerRent("неверный-email", corpSub); // Вызовет ошибку
        } catch (InvalidEmailException | DuplicateRentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n=== Сценарий 4: Попытка добавить дубликат ===");
        try {
            Rent dupSub = new UserRent("Дубль", 100.0, "Small");
            billing.registerRent("ivan@mail.ru", dupSub); // Вызовет ошибку, т.к. email уже есть
        } catch (InvalidEmailException | DuplicateRentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        System.out.println("\n=== Итоговая статистика ===");
        CampingStore.printGlobalStat();
        System.out.println("Программа завершилась без аварийного падения.");
    }
}