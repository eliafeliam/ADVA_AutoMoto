package com.adva.project.utilities;

import com.adva.project.model.Announcement;
import com.adva.project.model.Transport;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CSVAssistant {
    public void exportToCSV(HttpServletResponse response, List<? extends Announcement> transportList) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        //Musimy ustawić nagłówek "Content-Disposition" na HTTP odpowiedź
        String headerKey = "Content-Disposition";
        String headerValue = "Wyniki_wyszukiwania" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        //CsvBeanWriter jest używany jako środek zapisu w HTTP odpowiedź
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID ogloszenia", "Tytul ogloszenia","Rodzaj transportu","Marka transportu","Model transportu", "Przebieg transportu", "Opis", "Koszt"};
        String[] nameMapping = {"id", "title", "type", "brand", "model","mileage","description","price"};

        csvWriter.writeHeader(csvHeader);

        for (Announcement transport : transportList) {
            csvWriter.write(transport, nameMapping);
        }
        csvWriter.close();
    }
}
