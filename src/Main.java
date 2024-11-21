import entities.PasswordClassifier;
import entities.PasswordClassifierFilter;
import entities.PasswordDateFormatter;
import repositories.CreateFileCsv;
import services.RunTests;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

public static void main(String[] args) {

        PasswordClassifier classifier = new PasswordClassifier();
        classifier.classifyPasswords(
                "./src/dataset/passwords.csv",
                "./src/dataset/password_classifier.csv"
        );

        PasswordDateFormatter formatter = new PasswordDateFormatter();
        formatter.formatDates(
                "./src/dataset/password_classifier.csv",
                "./src/dataset/passwords_formated_data.csv"
        );

        PasswordClassifierFilter filterClassifier = new PasswordClassifierFilter();
        filterClassifier.filterPasswords(
                "./src/dataset/password_classifier.csv",
                "./src/dataset/passwords_classifier.csv"
        );

        RunTests runTests = new RunTests();

        System.out.println("\n------ Inicializando ordenações... --------\n\n");

        runTests.run();
        }
}
