package com.example.pharmapulse.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class RagService {

    private final VectorStore vectorStore;

    public RagService(EmbeddingModel embeddingModel) {
        this.vectorStore = new SimpleVectorStore(embeddingModel);
    }

    @PostConstruct
    public void init() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] pdfFiles = resolver.getResources("classpath:/docs/*.{pdf,docx}");
        List<Document> docs = new ArrayList<>();

        for (Resource resource : pdfFiles) {
            String filename = resource.getFilename();
            if (filename == null) continue;

            String content;
            if (filename.endsWith(".pdf")) {
                content = extractPdfText(resource.getInputStream());
            } else if (filename.endsWith(".docx")) {
                content = extractDocxText(resource.getInputStream());
            } else {
                continue;
            }

            docs.add(new Document(content));
            System.out.println("✅ Loaded: " + filename);
        }

        vectorStore.accept(docs);
        System.out.println("📚 All documents ingested into vector store.");
    }

    private String extractPdfText(InputStream inputStream) throws Exception {
        try (PDDocument document = PDDocument.load(inputStream)) {
            return new PDFTextStripper().getText(document);
        }
    }

    private String extractDocxText(InputStream inputStream) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (XWPFDocument doc = new XWPFDocument(inputStream)) {
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                sb.append(paragraph.getText()).append("\n");
            }
        }
        return sb.toString();
    }

    public List<Document> retrieveContext(String query) {
        return vectorStore.similaritySearch(SearchRequest.query(query).withTopK(3));
    }
}


