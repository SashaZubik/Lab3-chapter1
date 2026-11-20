package service.impl;

import service.NotificationService;
import java.util.regex.Pattern;

public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        String EMAIL_REGEX =
                "^(?=.{1,254}$)(?=.{1,64}@)[A-Za-z0-9._%+-]+(?:\\.[A-Za-z0-9._%+-]+)*@" +
                        "[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        // Strip leading/trailing whitespace before matching
        if (EMAIL_PATTERN.matcher(recipient.strip()).matches()) {
            System.out.printf("[EMAIL] На адрес <%s> отправлено письмо: %s%n", recipient, message);
        } else {
            // Используем стандартное исключение Java, чтобы не было проблем с импортами
            throw new IllegalArgumentException("Некорректный Email: " + recipient);
        }
    }
}