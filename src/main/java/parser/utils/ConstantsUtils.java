package parser.utils;

import static parser.utils.DataUtils.CURRENT_TIME;

public class ConstantsUtils {

    public static final String PATH_TO_BLACK_LIST           = "/home/grigoriy/Work/Projects/ParserHH/src/main/resources/BlackList.txt";
    public static final String PATH_TO_VIEWED_VACANCIES_IDS = "/home/grigoriy/Work/Projects/ParserHH/src/main/resources/ViewedVacanciesIds.txt";

    public static final String URL         = "https://api.hh.ru/vacancies/?text=java&search_field=company_name&search_field=description&search_field=name&order_by=publication_time";
    public static final String PAGE_NUMBER = "&page=";

    public static final String STARTING_MESSAGE = "\nНачался сбор вакансий в " + CURRENT_TIME + ". Завершение через 6 минут.\n";

    public static final String FIRST_LOCATION   = "schedule=remote&";
    public static final String SECOND_LOCATION  = "area=1&";
    public static final String THIRD_LOCATION   = "area=24&area=72&area=26&area=68&area=53&area=76&area=99&area=78&area=104&area=54&area=66&area=88&area=3&area=4&area=2&";
    public static final String FOURTH_LOCATION  = "area=79&area=95&area=212&area=11&area=29&area=96&area=102&area=98&area=35&area=22&area=112&area=130&area=90&area=84&area=47&area=1641&area=70&area=1240&area=2020&area=77&area=107&area=71&area=58&area=41&area=49&area=1866&area=15&area=92&area=237&area=20&area=56&area=89&area=1399&area=1381&";

    public static final String COMPLETION_NOTIFICATION_PART_ONE   = "1/4: Собрались вакансии по удаленной работе";
    public static final String COMPLETION_NOTIFICATION_PART_TWO   = "2/4: Собрались вакансии по Москве";
    public static final String COMPLETION_NOTIFICATION_PART_THREE = "3/4: Собрались вакансии по городам с населением от 1КК человек";
    public static final String COMPLETION_NOTIFICATION_PART_FOUR  = "4/4: Собрались вакансии по городам с населением от 400К до 1КК человек";

    public static final String WORK_EXPERIENCE_FIRST_OPTION  = "От 3 до 6 лет";
    public static final String WORK_EXPERIENCE_SECOND_OPTION = "Более 6 лет";

    public static final String STRING_STARTS_WITH   = "|hh.ru";
    public static final String URL_PART             = "hh.ru/vacancy/";

    public static final String ITEMS                = "items";
    public static final String VACANCY_FIRST_PART   = "\"\":[";
    public static final String VACANCY_SECOND_PART  = "\"id\":\"";
    public static final String VACANCY_THIRD_PART   = "\",\"premium\":false,\"name\":\"";
    public static final String VACANCY_FOURTH_PART  = "\",\"premium\":true,\"name\":\"";
    public static final String VACANCY_FIFTH_PART   = "\"is_adv_vacancy\":false],";
    public static final String VACANCY_SIXTH_PART   = "\"is_adv_vacancy\":false,";
    public static final String VACANCY_SEVENTH_PART = "\"is_adv_vacancy\":true,";
    public static final String VACANCY_EIGHTH_PART  = "\"found\"";
    public static final String VACANCY_NINTH_PART   = "\",\"department\"";

    public static final String OPENING_CURLY_BRACE  = "{";
    public static final String CLOSING_CURLY_BRACE  = "}";
    public static final String VERTICAL_LINE        = "|";
    public static final String COLON                = ":";
    public static final String TILDA                = "~";
    public static final String AMPERSAND            = "&";
    public static final String EMPTY                = "";
    public static final String LINE_BREAK           = "\n";

    public static final int PAUSE_IN_MILLISECONDS   = 15000;

    private ConstantsUtils() { }

}
