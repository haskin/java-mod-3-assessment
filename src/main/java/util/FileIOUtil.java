package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hospital.Hospital;

public class FileIOUtil {
    private static final String FILE_PATH = Paths.get("").toAbsolutePath().toString() + "/src/main/java/hospital.json";

    public static Optional<Hospital> getHospital() {
        File hospitalFile = new File(FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Hospital hospital = objectMapper.readValue(hospitalFile, Hospital.class);
            return Optional.of(hospital);
        } catch (Exception e) {
            System.out.println("ERROR: Could not restore previous hospital");
            return Optional.empty();
        }
    }

    public static void saveHospital(Hospital hospital) {
        File file = new File(FILE_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hospital);
            List<String> lines = Arrays.stream(jsonString.split("\n")).collect(Collectors.toList());
            try (FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                lines.forEach(line -> {
                    try {
                        bufferedWriter.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not save Hospital to file");
        }
    }

    public static boolean hospitalExists() {
        File file = new File(FILE_PATH);
        return file.exists();
    }
}
