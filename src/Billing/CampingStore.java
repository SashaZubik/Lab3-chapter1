package Billing;

import exception.DuplicateRentException;
import exception.InvalidEmailException;
import exception.RentNotFoundException;
import model.Rent;
import service.NotificationService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CampingStore {
    private static int totalOrdersCount;
    private static double totalRevenue;
    private final NotificationService notificationservice;
    private final Map<String, Rent> database = new HashMap<>();

    public CampingStore(NotificationService notificationservice) {
        this.notificationservice = notificationservice;
    }

    // ДОБАВЛЕНО: throws DuplicateRentException
    public void registerRent(String email, Rent rent) throws InvalidEmailException, DuplicateRentException {
        if (email == null) {
            throw new InvalidEmailException("Email не может быть пустым");
        }

        String EMAIL_REGEX = "^(?=.{1,254}$)(?=.{1,64}@)[A-Za-z0-9._%+-]+(?:\\.[A-Za-z0-9._%+-]+)*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (!EMAIL_PATTERN.matcher(email.strip()).matches()) {
            throw new InvalidEmailException("Некорректный Email");
        }

        // ДОБАВЛЕНО: Проверка на дубликат
        if (database.containsKey(email)) {
            throw new DuplicateRentException("Пользователь с email " + email + " уже зарегистрирован!");
        }

        database.put(email, rent);
    }

    public void ProcessPayment(String email, String contact) throws RentNotFoundException {
        Rent rent = database.get(email);

        if (rent == null) {
            throw new RentNotFoundException("Пользователь с таким email " + email + " не зарегистрирован");
        }

        double FinalPrice = rent.cost();
        RegisterOrder(FinalPrice);
        String message = String.format("Счет оплачен. Тариф [%s]. Списано: %.2f руб.", rent.Getcategory(), FinalPrice);
        notificationservice.sendNotification(message, contact);
    }

    public static int GetTotalOrderCount() {
        return totalOrdersCount;
    }

    public static double GetTotalRevenue() {
        return totalRevenue;
    }

    public static void RegisterOrder(double amount) {
        totalOrdersCount++;
        totalRevenue += amount;
    }

    public static void printGlobalStat() {
        System.out.println("Всего заказов оформлено: " + totalOrdersCount);
        System.out.println("Выручка : " + totalRevenue);
    }
}