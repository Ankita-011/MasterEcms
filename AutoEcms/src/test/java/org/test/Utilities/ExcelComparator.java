package org.test.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelComparator {
	public static boolean compareExcelFiles(String file1Path, String file2Path) throws IOException {
        FileInputStream file1 = new FileInputStream(file1Path);
        FileInputStream file2 = new FileInputStream(file2Path);

        Workbook wb1 = WorkbookFactory.create(file1);
        Workbook wb2 = WorkbookFactory.create(file2);

        Sheet sheet1 = wb1.getSheetAt(0);
        Sheet sheet2 = wb2.getSheetAt(0);

        Row header1 = sheet1.getRow(0);
        Row header2 = sheet2.getRow(0);

        if (header1 == null || header2 == null) {
            System.out.println("One or both files have no header row.");
            return false;
        }

        int cellCount1 = header1.getPhysicalNumberOfCells();
        int cellCount2 = header2.getPhysicalNumberOfCells();

        if (cellCount1 != cellCount2) {
            System.out.println("Column count mismatch: " + cellCount1 + " vs " + cellCount2);
            return false;
        }

        for (int i = 0; i < cellCount1; i++) {
            String col1 = getCellValue(header1.getCell(i));
            String col2 = getCellValue(header2.getCell(i));

            if (!col1.equalsIgnoreCase(col2)) {
                System.out.println("Mismatch at column index " + i + ": " + col1 + " vs " + col2);
                return false;
            }
        }

        System.out.println("✅ Column headers and count match!");
        return true;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return cell.toString();
        }
    }
    public static File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles(File::isFile);

        if (files == null || files.length == 0) {
            System.out.println("No files found in directory!");
            return null;
        }

        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0]; // most recently modified file
    }
}
