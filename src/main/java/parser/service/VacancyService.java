package parser.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import parser.utils.DataUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static parser.utils.ConstantsUtils.*;

public class VacancyService {

    private static final List<String> VIEWED_VACANCIES_IDS;
    private static final List<String> BLACK_LIST;

    static {
        try {
            VIEWED_VACANCIES_IDS = DataUtils.readDataFromFile(PATH_TO_VIEWED_VACANCIES_IDS);
            BLACK_LIST           = DataUtils.readDataFromFile(PATH_TO_BLACK_LIST);
        } catch (IOException ex) { throw new RuntimeException(ex); }
    }

    public static void printLinksAndIds() { // O(n)
        List<String> allVacancies = new ArrayList<>(VacancyService.combineAndSort());
        StringBuilder vacancyUrls = new StringBuilder();
        StringBuilder vacancyIds  = new StringBuilder();
        for (int i = 0; i < allVacancies.size(); i++) {
            allVacancies.set(i, allVacancies.get(i).substring(allVacancies.get(i).indexOf(VERTICAL_LINE)));
            vacancyUrls.append(allVacancies.get(i));
            vacancyIds.append(allVacancies.get(i).replace(URL_PART, EMPTY));
        }
        System.out.println(LINE_BREAK + vacancyUrls);
        System.out.println(LINE_BREAK + vacancyIds);
    }

    private static Set<String> combineAndSort() { // O(remoteWork + moscow + regionsFrom1KK + regionsBetween400K1KK)
        final Set<String> remoteWork            = select(FIRST_LOCATION,  COMPLETION_NOTIFICATION_PART_ONE);
        final Set<String> moscow                = select(SECOND_LOCATION, COMPLETION_NOTIFICATION_PART_TWO);
        final Set<String> regionsFrom1KK        = select(THIRD_LOCATION,  COMPLETION_NOTIFICATION_PART_THREE);
        final Set<String> regionsBetween400K1KK = select(FOURTH_LOCATION, COMPLETION_NOTIFICATION_PART_FOUR);
        Set<String> allVacancies = new TreeSet<>();
        allVacancies.addAll(remoteWork);
        allVacancies.addAll(moscow);
        allVacancies.addAll(regionsFrom1KK);
        allVacancies.addAll(regionsBetween400K1KK);
        return allVacancies;
    }

    private static Set<String> select(final String location, final String completionNotification) { // O(n)
        Set<String> vacancies = new TreeSet<>();
        for (String vacancy : get(URL, location)) {
            if (vacancy != null && vacancy.startsWith(STRING_STARTS_WITH) &&
                    !vacancy.contains(WORK_EXPERIENCE_FIRST_OPTION) &&
                    !vacancy.contains(WORK_EXPERIENCE_SECOND_OPTION)) {
                vacancy = vacancy.toLowerCase().substring(0, vacancy.indexOf(VACANCY_NINTH_PART));
                if (isPostBlacklist(vacancy) && !isVacancyBeenViewed(vacancy)) {
                    String url  = vacancy.substring(0, vacancy.indexOf(COLON));
                    String post = vacancy.substring(vacancy.indexOf(COLON) + 1);
                    vacancies.add(post.concat(url));
                }
            }
        }
        System.out.println(completionNotification);
        try { Thread.sleep(PAUSE_IN_MILLISECONDS * 2); } catch (InterruptedException ex) { throw new RuntimeException(ex); }
        return vacancies;
    }

    private static String[] get(final String url, final String location) { // O(1)
        StringBuilder vacanciesOnPages = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            try {
                Connection.Response response = Jsoup
                        .connect(url + AMPERSAND + location + PAGE_NUMBER + i)
                        .ignoreContentType(true)
                        .execute();
                vacanciesOnPages.append(response.body()
                        .replace(ITEMS, EMPTY)
                        .replace(OPENING_CURLY_BRACE,  EMPTY)
                        .replace(CLOSING_CURLY_BRACE,  EMPTY)
                        .replace(VACANCY_FIRST_PART,   EMPTY)
                        .replace(VACANCY_SECOND_PART,  VERTICAL_LINE + URL_PART)
                        .replace(VACANCY_THIRD_PART,   COLON)
                        .replace(VACANCY_FOURTH_PART,  COLON)
                        .replace(VACANCY_FIFTH_PART,   TILDA)
                        .replace(VACANCY_SIXTH_PART,   TILDA)
                        .replace(VACANCY_SEVENTH_PART, TILDA)
                        .split(VACANCY_EIGHTH_PART)[0]);
            } catch (IOException ex) { throw new RuntimeException(ex); }
            if (i == 49) {
                try { Thread.sleep(PAUSE_IN_MILLISECONDS); } catch (InterruptedException ex) { throw new RuntimeException(ex); }
            }
        }
        return vacanciesOnPages
                .toString()
                .split(TILDA);
    }

    private static boolean isPostBlacklist(final String vacancy) { // O(1)
        return BLACK_LIST
                .stream()
                .noneMatch(vacancy::contains);
    }

    private static boolean isVacancyBeenViewed(final String vacancy) { // O(1)
        return VIEWED_VACANCIES_IDS
                .stream()
                .anyMatch(vacancy::contains);
    }

}
