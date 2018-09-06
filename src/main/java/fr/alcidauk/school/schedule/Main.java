package fr.alcidauk.school.schedule;

import fr.alcidauk.school.schedule.beans.*;
import fr.alcidauk.school.schedule.business.ActivityTimesGenerator;
import fr.alcidauk.school.schedule.business.EmptyTimesGenerator;
import fr.alcidauk.school.schedule.business.TimeConversion;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();

        configureAvailableTimes(config);
        configureDomains(config);

        WeekSchedule weekSchedule = new WeekSchedule();
        generateEmptyTimes(config, weekSchedule);

        configureNumberOfActivityPerDomaine(config, weekSchedule);

        generateActivityTimes(weekSchedule);
    }

    private static void generateActivityTimes(WeekSchedule weekSchedule) {
        new ActivityTimesGenerator().generateActivityTimes(weekSchedule);

        printSchedule(weekSchedule.getActivityTimes());
    }

    private static void printSchedule(List<ActivityTime> activityTimes) {
        System.out.println("Agenda de la semaine généré !");
        printScheduleForDay(activityTimes, 0, "Lundi");
        printScheduleForDay(activityTimes, 1, "Mardi");
        printScheduleForDay(activityTimes, 2, "Mercredi");
        printScheduleForDay(activityTimes, 3, "Jeudi");
    }

    private static void printScheduleForDay(List<ActivityTime> activityTimes, int dayOfWeek, String dayName) {
        List<ActivityTime> activitiesForDay =
                activityTimes.stream()
                        .filter(activityTime -> activityTime.getDayInWeek() == dayOfWeek)
                        .sorted((activityTime, t1) -> activityTime.getStartMinute() < t1.getStartMinute() ? 1 : -1)
                        .collect(Collectors.toList());

        TimeConversion timeConversion = new TimeConversion();

        System.out.println(dayName);
        for (ActivityTime activity : activitiesForDay) {
            System.out.println(
                    String.format("De %s à %s: %s",
                            timeConversion.convert(activity.getStartMinute()),
                            timeConversion.convert(activity.getEndMinute()),
                            activity.getDomain().getName())
            );
        }
    }

    private static void configureNumberOfActivityPerDomaine(Config config, WeekSchedule weekSchedule) {
        System.out.println("Configuration du nombre d'activités par domaine.");

        int remainingEmptyTimes = weekSchedule.getEmptyTimes().size();

        for (Domain domain : config.getDomains()) {
            System.out.println(String.format("Nombre de créneaux restants: %d", remainingEmptyTimes));
            System.out.println(String.format("Configuration pour le domaine %s.", domain.getName()));

            Scanner sc = new Scanner(System.in);
            int numberOfActivities;
            do {
                System.out.println("Entrez le nombre de créneaux à consacrer au domaine dans la semaine:");
                numberOfActivities = sc.nextInt();
            } while (numberOfActivities > remainingEmptyTimes);

            weekSchedule.addActivityNumberForDomain(domain, numberOfActivities);
            remainingEmptyTimes -= numberOfActivities;
        }
    }

    private static void generateEmptyTimes(Config config, WeekSchedule weekSchedule) {
        new EmptyTimesGenerator().generateEmptyTimes(config, weekSchedule);

        System.out.println(String.format("Nombre de créneaux disponibles: %s", weekSchedule.getEmptyTimes().size()));
    }

    private static void configureAvailableTimes(Config config) {
        System.out.println("Bonjour ! Pour commencer, entrez les crénaux disponibles pour une journée.");

        String next = inputAvailableTimeMenu();
        while (!next.equals("T")) {
            if (next.equals("A")) {
                addAvailableTime(config);
            }

            next = inputAvailableTimeMenu();
        }

        System.out.println("Configuration des créneaux terminée.");
    }

    private static void configureDomains(Config config) {
        System.out.println("Configurons maintenant les domaines.");

        String next = inputDomainsMenu();
        while (!next.equals("T")) {
            if (next.equals("A")) {
                addDomain(config);
            }

            next = inputDomainsMenu();
        }

        System.out.println("Configuration des domaines terminée.");
    }

    private static void addDomain(Config config) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le nom du domaine, puis terminez par entrée:");
        String domainName = sc.nextLine();
        System.out.println("Indiquez la priorité du domaine (numéro de 1 à 10):");
        int priority = sc.nextInt();

        System.out.println(String.format("Ajout du nouveau domaine %s avec priorité %d", domainName, priority));

        config.addDomain(new Domain(domainName, priority));
    }

    private static String inputAvailableTimeMenu() {
        System.out.println("============= MENU ==============");
        System.out.println("Pour ajouter un créneau, appuyer sur A.");
        System.out.println("Pour terminer la configuration, appuyer sur T.");

        try {
            Scanner sc = new Scanner(System.in);
            return sc.next("[AT]");
        } catch (InputMismatchException e) {
            System.out.println("Saisie impossible");
            return inputAvailableTimeMenu();
        }
    }

    private static String inputDomainsMenu() {
        System.out.println("============= MENU ==============");
        System.out.println("Pour ajouter un domaine, appuyer sur A.");
        System.out.println("Pour terminer la configuration, appuyer sur T.");

        try {
            Scanner sc = new Scanner(System.in);
            return sc.next("[AT]");
        } catch (InputMismatchException e) {
            System.out.println("Saisie impossible");
            return inputDomainsMenu();
        }
    }

    private static void endConfiguration() {

    }

    private static void addAvailableTime(Config config) {
        String startTimeAsString = getTime("Entrez, en suivant le format hh:mm, l'heure de début du créneau:");
        String endTimeAsString = getTime("Entrez, en suivant le format hh:mm, l'heure de fin du créneau:");

        TimeConversion timeConversion = new TimeConversion();
        int startTimeAsInt = timeConversion.convert(startTimeAsString);
        int endTimeAsInt = timeConversion.convert(endTimeAsString);

        System.out.println(String.format("Ajout d'un créneau de %s à %s", startTimeAsString, endTimeAsString));

        config.addAvailableTime(new AvailableTime(startTimeAsInt, endTimeAsInt));
    }

    private static String getTime(String sentence) {
        System.out.println(sentence);
        String pattern = "[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}";

        try {
            Scanner sc = new Scanner(System.in);
            return sc.next(pattern);
        } catch (InputMismatchException e) {
            System.out.println("Saisie impossible");
            return getTime(sentence);
        }
    }
}
