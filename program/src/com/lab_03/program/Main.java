package com.lab_03.program;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.lab_03.signal.Signal;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Path is required.");
            return;
        }

        String sPath = args[0].trim();
        if (sPath.isEmpty()) {
            System.out.println("Path cannot be blank or empty.");
        }

        byte[] data;

        try {
            Path path = Paths.get(sPath);
            data = Files.readAllBytes(path);
        } catch (NoSuchFileException e) {
            System.out.println("File not found.");
            return;
        } catch (AccessDeniedException e) {
            System.out.println("Access denied.");
            return;
        } catch (IOException e) {
            System.out.println("I/O error while reading data.");
            System.out.println(e);

            return;
        } catch (Exception e) {
            System.out.println("Unexpected error.");
            System.out.println(e);

            return;
        }

        Signal signal = new Signal(data);

        System.out.println("Min: " + signal.getMinValue());
        System.out.println("Max: " + signal.getMaxValue());
        System.out.println("Average: " + signal.getAverageValue());
        System.out.println("Dynamic range: " + signal.getDynamicRange());
        System.out.println("Energy: " + signal.getEnergy());
        System.out.println("Average power: " + signal.getAveragePower());
        System.out.println("Dispersion: " + signal.getDispersion());

        System.out.println("\nAutocorrelation:");
        for (int t = -5; t <= 5; t += 1) {
            System.out.println("for tau = " + t + ": " + signal.getAutocorrelation(t));
        }

        System.out.println("\nCorrelation interval: " + signal.getCorrelationInterval());
    }
}
