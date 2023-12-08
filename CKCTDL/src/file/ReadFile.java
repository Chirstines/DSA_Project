package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.StudyProgram;
import model.University;

public class ReadFile {
    public ArrayList<StudyProgram> readFile(String path) {
        ArrayList<StudyProgram> list = new ArrayList<>();

        try {
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                String programCode = cleanCellValue(row.getCell(0));
                String programName = row.getCell(1).getStringCellValue();
                double admissionScore = row.getCell(2).getNumericCellValue();
                String universityCode = row.getCell(3).getStringCellValue();
                String universityName = row.getCell(4).getStringCellValue();
                String universityAddress = row.getCell(5).getStringCellValue();
                University university = new University(universityCode, universityName, universityAddress);
                Cell tuitionCell = row.getCell(6);

                long tuition = 0;
                if (tuitionCell != null && tuitionCell.getCellType() == CellType.NUMERIC) {
                    tuition = (long) tuitionCell.getNumericCellValue();
                }
                StudyProgram studyProgram = new StudyProgram(programCode, programName, university, tuition, admissionScore);
                list.add(studyProgram);
            }

            workbook.close();
            excelFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static String cleanCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        String cellValue;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }

        if (cellValue.endsWith(".0")) {
            cellValue = cellValue.substring(0, cellValue.length() - 2);
        }
        return cellValue;
    }
}


