import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUnpackingTest {

    private ClassLoader cl = ZipUnpackingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Homework10.zip");
             ZipInputStream izs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = izs.getNextEntry()) != null) {
                if(entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(izs);
                    Assertions.assertEquals("Маленький Принц",pdf.title);
                }
            }
        }
    }

    @Test
    void xlsParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Homework10.zip");
             ZipInputStream izs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = izs.getNextEntry()) != null) {
                if (entry.getName().contains(".xls")) {
                    XLS xls = new XLS(izs);
                    Assertions.assertEquals(
                            xls.excel.getSheetAt(0).getRow(3).getCell(0)
                                    .getStringCellValue(), "РАСПИСАНИЕ");
                }
            }
        }
    }

    @Test
    void csvParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Homework10.zip");
             ZipInputStream izs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = izs.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(izs));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"John \"Da Man\"", "Repici", "120 Jefferson St.",
                            "Riverside", " NJ", "08075"}, content.get(2));
                }
            }
        }
    }
}