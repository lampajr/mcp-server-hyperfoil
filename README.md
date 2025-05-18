# MCP Server for Hyperfoil integration

> [!NOTE]
> This repository is intended to showcase how Hyperfoil could be integrated with AI chatbots using MCP

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw install
```

This builds an uber-jar which you can run directly using jbang `io.github.lampajr.hyperfoil:mcp-server-hyperfoil:0.0.1-SNAPSHOT:runner`

To utilize in an MCP client (such as Claude Desktop or VS Code GitHub Copilot), you can use the following command:

```shell script
jbang --quiet io.github.lampajr.hyperfoil:mcp-server-hyperfoil:0.0.1-SNAPSHOT:runner
```

An example of MCP server configuration could look like this:
```json
{
    "servers": {
      "hyperfoil": {
          "type": "stdio",
          "command": "jbang",
          "args": ["--quiet", "io.github.lampajr.hyperfoil:mcp-server-hyperfoil:1.0.0-SNAPSHOT:runner"]
      }
  }
}
```

## Related Guides

- MCP Server - stdio ([guide](https://docs.quarkiverse.io/quarkus-mcp-server/dev/index.html)): Parent POM for Quarkiverse projects that includes the default release and artifact publishing related
    configuration
