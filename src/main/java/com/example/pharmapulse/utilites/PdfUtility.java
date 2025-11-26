package com.example.pharmapulse.utilites;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfUtility {
    public static String extractText(File file) throws IOException {
        try (PDDocument doc = PDDocument.load(file)) {
            return new PDFTextStripper().getText(doc);
        }
    }
}