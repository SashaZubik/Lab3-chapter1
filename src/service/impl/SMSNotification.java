package service.impl;
import service.NotificationService;

public class SMSNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (!(recipient.startsWith("+7") || (recipient.startsWith("8")) && (recipient.length())>=11)){
            throw new IllegalArgumentException("Не корректный номер");
        }
        System.out.printf("[SMS] На номер [%s] отправлено сообщение: %s%n", recipient, message);
    }
}