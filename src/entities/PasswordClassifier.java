package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PasswordClassifier {

    public void classifyPasswords(String inputFile, String outputFile) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            writer.append("id,password,length,data,class\n");

            int lineNumber = -1;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 0) continue; 

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (data.length >= 4) {
                    String password = data[1].replaceAll("\"", "");
                    int length;
                    try {
                        length = Integer.parseInt(data[2]);
                    } catch (NumberFormatException e) {
                        length = 0;
                    }

                    String passwordClass = classifyPassword(password, length);

                    writer.append(data[0]).append(",")
                            .append("\"").append(password).append("\"").append(",")
                            .append(data[2]).append(",")
                            .append(data[3]).append(",")
                            .append(passwordClass).append("\n");
                }
            }

            System.out.println("Classificação concluída. Novo arquivo gerado: " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String classifyPassword(String password, int length) {
        if (length < 5 && containsOnlyOneType(password)) {
            return "Muito Ruim";
        } else if (length <= 5 && containsOnlyOneType(password)) {
            return "Ruim";
        } else if (length <= 6 && containsExactlyTwoTypes(password)) {
            return "Fraca";
        } else if (length <= 7 && containsAllTypes(password)) {
            return "Boa";
        } else if (length > 8 && containsAllTypes(password)) {
            return "Muito Boa";
        } else {
            return "Sem Classificação";
        }
    }

    private static boolean containsOnlyOneType(String password) {
        return password.matches("^[a-z]+$") ||
                password.matches("^[A-Z]+$") ||
                password.matches("^[0-9]+$") ||
                password.matches("^[^a-zA-Z0-9]+$");
    }

    private static boolean containsExactlyTwoTypes(String password) {
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumbers = password.matches(".*[0-9].*");
        boolean hasSpecials = password.matches(".*[^a-zA-Z0-9].*");

        return (hasLowercase && hasUppercase && !hasNumbers && !hasSpecials) ||
                (hasLowercase && !hasUppercase && hasNumbers && !hasSpecials) ||
                (hasLowercase && !hasUppercase && !hasNumbers && hasSpecials) ||
                (!hasLowercase && hasUppercase && hasNumbers && !hasSpecials) ||
                (!hasLowercase && hasUppercase && !hasNumbers && hasSpecials) ||
                (!hasLowercase && !hasUppercase && hasNumbers && hasSpecials);
    }

    private static boolean containsAllTypes(String password) {
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumbers = password.matches(".*[0-9].*");
        boolean hasSpecials = password.matches(".*[^a-zA-Z0-9].*");

        return hasLowercase && hasUppercase && hasNumbers && hasSpecials;
    }
}
