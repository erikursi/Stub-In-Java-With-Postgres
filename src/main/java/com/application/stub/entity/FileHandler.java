package com.application.stub.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class FileHandler {
    @Value("${filehandler.filepath}")
    private String filePath;
    public String readUserFromFile() throws IOException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            if (lines.isEmpty()) throw new IOException("File is empty");
            Random random = new Random();
            return lines.get(random.nextInt(lines.size()));
        }
    }
    public void writeUserToFile(User user) throws IOException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(user.toJson() + System.lineSeparator());
        }
    }
}
