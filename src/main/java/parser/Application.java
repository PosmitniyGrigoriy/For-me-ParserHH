package parser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import parser.service.VacancyService;

import static parser.utils.ConstantsUtils.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println(STARTING_MESSAGE);
        VacancyService.printLinksAndIds();
    }
}
