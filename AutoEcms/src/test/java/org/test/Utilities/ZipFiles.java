package org.test.Utilities;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.*;

public class ZipFiles {

    private static ExtentTest extentTest;

    public static void validateZipReport(String downloadDir, String mobile, String empId, String trxnId, String statusToMatch, ExtentTest test) {
        extentTest = test;

        try {
            File latestZip = getLatestZipFile(downloadDir);
            extentTest.info("Found latest ZIP: " + latestZip.getName());

            File extractedFile = unzipFile(latestZip, downloadDir);
            extentTest.info("Extracted file: " + extractedFile.getName());

            if (extractedFile.getName().endsWith(".xlsx")) {
                readAndValidateExcel(extractedFile, mobile, empId, trxnId, statusToMatch);
            } else if (extractedFile.getName().endsWith(".csv")) {
                readAndValidateCSV(extractedFile, mobile, empId, trxnId, statusToMatch);
            } else {
                extentTest.warning("Unsupported file format: " + extractedFile.getName());
            }

        } catch (Exception e) {
            extentTest.fail("Error during ZIP validation: " + e.getMessage());
        }
    }

    public static File getLatestZipFile(String downloadDirPath) {
        File dir = new File(downloadDirPath);
        File[] zipFiles = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".zip"));

        if (zipFiles == null || zipFiles.length == 0) {
            throw new RuntimeException("No ZIP files found in: " + downloadDirPath);
        }

        Arrays.sort(zipFiles, Comparator.comparingLong(File::lastModified).reversed());
        return zipFiles[0];
    }

    public static File unzipFile(File zipFile, String destDir) throws IOException {
        byte[] buffer = new byte[1024];
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();

        File extractedFile = null;

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                extractedFile = new File(destDir, zipEntry.getName());
                try (FileOutputStream fos = new FileOutputStream(extractedFile)) {
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
        }

        return extractedFile;
    }

    public static void readAndValidateExcel(File file, String mobile, String empId, String trxnId, String statusToMatch) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Integer> headers = new HashMap<>();

            // Extract headers
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                headers.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String m = getCellString(row.getCell(headers.get("Mobile Number")));
                String e = getCellString(row.getCell(headers.get("Employee ID")));
                String t = getCellString(row.getCell(headers.get("Transaction ID")));
                String s = getCellString(row.getCell(headers.get("Status")));
                String r = getCellString(row.getCell(headers.get("Report Status")));

                if (m.contains(mobile) && e.equals(empId) && t.equals(trxnId) && s.equalsIgnoreCase(statusToMatch)) {
                    extentTest.pass("✅ Match found: " + m + ", " + e + ", " + t + ", " + s);
                } else {
                    extentTest.fail("❌ Mismatch: " + m + ", " + e + ", " + t + ", " + s);
                }
            }
        }
    }

    public static void readAndValidateCSV(File file, String mobile, String empId, String trxnId, String statusToMatch) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] headers = line.split(",");
            Map<String, Integer> headerIndex = new HashMap<>();

            for (int i = 0; i < headers.length; i++) {
                headerIndex.put(headers[i].trim(), i);
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String m = values[headerIndex.get("Mobile Number")];
                String e = values[headerIndex.get("Employee ID")];
                String t = values[headerIndex.get("Transaction ID")];
                String s = values[headerIndex.get("Status")];
                String r = values[headerIndex.get("Report Status")];

                if (m.contains(mobile) && e.equals(empId) && t.equals(trxnId) && s.equalsIgnoreCase(statusToMatch)) {
                    extentTest.pass("✅ Match found: " + m + ", " + e + ", " + t + ", " + s);
                } else {
                    extentTest.fail("❌ Mismatch: " + m + ", " + e + ", " + t + ", " + s);
                }
            }
        }
    }

    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
