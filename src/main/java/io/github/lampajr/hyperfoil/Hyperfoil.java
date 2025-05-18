package io.github.lampajr.hyperfoil;

import io.hyperfoil.api.config.BenchmarkDefinitionException;
import io.hyperfoil.api.config.BenchmarkSource;
import io.hyperfoil.core.impl.ProvidedBenchmarkData;
import io.hyperfoil.core.parser.BenchmarkParser;
import io.hyperfoil.core.parser.ParserException;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import io.quarkiverse.mcp.server.ToolCallException;
import io.quarkiverse.mcp.server.ToolResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class Hyperfoil {

    @Tool(description = "Parse and validate Hyperfoil benchmark")
    ToolResponse parseBenchmark(@ToolArg(description = "Full pathname of the benchmark file") String filename) {
        try {
            byte[] sourceAsBytes = Files.readAllBytes(Path.of(filename));
            BenchmarkSource source = BenchmarkParser.instance()
                    .createSource(new String(sourceAsBytes, StandardCharsets.UTF_8), new ProvidedBenchmarkData());
            BenchmarkParser.instance().buildBenchmark(source, Collections.emptyMap());
        } catch (BenchmarkDefinitionException e) {
            throw new ToolCallException("Failed to parse benchmark: " + e.getMessage(), e);
        } catch (ParserException e) {
            throw new ToolCallException("Cannot parse benchmark definition: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new ToolCallException("Something went wrong reading the file: " + e.getMessage(), e);
        }

        return ToolResponse.success("The benchmark definition has been validated successfully");
    }
}
