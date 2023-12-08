package file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.StudyProgram;

public class SaveFile {
    private Workbook workbook;

    public SaveFile() {
        workbook = new XSSFWorkbook();
    }

    public void saveListToExcel(ArrayList<StudyProgram> programList, String filePath) {
    	Sheet sheet = workbook.createSheet("DanhSachNganh");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Mã Ngành");
        headerRow.createCell(1).setCellValue("Tên Ngành");
        headerRow.createCell(2).setCellValue("Điểm Chuẩn");
        headerRow.createCell(3).setCellValue("Mã Trường");
        headerRow.createCell(4).setCellValue("Tên Trường");
        headerRow.createCell(5).setCellValue("Địa Chỉ");
        headerRow.createCell(6).setCellValue("Học Phí");

        int rowNum = 1;
        for (StudyProgram program : programList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(program.getProgramCode());
            row.createCell(1).setCellValue(program.getProgramName());
            row.createCell(2).setCellValue(program.getAdmissionScore());
            row.createCell(3).setCellValue(program.getUniversity().getUniversityCode());
            row.createCell(4).setCellValue(program.getUniversity().getUniversityName());
            row.createCell(5).setCellValue(program.getUniversity().getAddress());
            row.createCell(6).setCellValue(program.getTuitionFee());
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
