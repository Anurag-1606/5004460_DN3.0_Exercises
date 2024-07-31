public class NotificationSystemTest {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier.send("Hello, this is a basic email notification!");

        System.out.println("\nAdding SMS Notification:");
        Notifier smsNotifier = new SMSNotifierDecorator(notifier);
        smsNotifier.send("Hello, this is a combined email and SMS notification!");

        System.out.println("\nAdding Slack Notification:");
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);
        slackNotifier.send("Hello, this is a combined email, SMS, and Slack notification!");
    }
}
