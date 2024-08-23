package org.project.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.ThreadContext;
import org.project.stock.dto.PortfolioTrade;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class PortfolioManagerApplication {
    public static List<String> mainReadFile(String[] args) throws URISyntaxException, IOException {
//        String filename = args[0];
        String filename = "trades.json";
        File file = resolveFileFromResources(filename);
        ObjectMapper mapper = getObjectMapper();
        PortfolioTrade[] portfolioTrades = mapper.readValue(file, PortfolioTrade[].class);
        List<String> symbolsList = new ArrayList<>();

        for (PortfolioTrade portfolioTrade : portfolioTrades) {
            symbolsList.add(portfolioTrade.getSymbol());
        }

        return symbolsList;
    }

    private static void printJsonObject(Object object) throws IOException {
        Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
        ObjectMapper mapper = new ObjectMapper();
        logger.info(mapper.writeValueAsString(object));
    }

    private static File resolveFileFromResources(String filename) throws URISyntaxException {
        return Paths.get(
                Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static List<String> debugOutputs() {
        String valueOfArgument0 = "trades.json";
        String resultOfResolveFilePathArgs0 = "/workspace/bin/main/trades.json";
        String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@6150c3ec";
        String functionNameFromTestFileInStackTrace = "PortfolioManagerApplicationTest.mainReadFile()";
        String lineNumberFromTestFileInStackTrace = "29";

        return Arrays.asList(valueOfArgument0, resultOfResolveFilePathArgs0,
                toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
                lineNumberFromTestFileInStackTrace);
    }

    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {

        });
        ThreadContext.put("runId", UUID.randomUUID().toString());

        printJsonObject(mainReadFile(args));
    }
}
