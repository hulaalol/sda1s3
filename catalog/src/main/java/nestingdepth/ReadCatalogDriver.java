package nestingdepth;

/**
 * This driver class simply instantiates an {@link ReadCatalog}object and
 * initiates the parsing process. The resulting HTML output is written to
 * stdout.
 * 
 */
public class ReadCatalogDriver {
  /** @param argv unused
   *  @throws Exception Inaccessible file or corrupt content
   */
  public static void main(String[] argv) throws Exception {
    final AnalyzeNesting catalogReader = new AnalyzeNesting();
    catalogReader.process("memo.xml");
  }
}