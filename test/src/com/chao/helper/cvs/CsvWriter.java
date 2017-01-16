package com.chao.helper.cvs;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by think on 2016/11/3.
 */
public class CsvWriter extends BufferedWriter {

    /**
     * set wirter
     *
     * @param out writer
     */
    public CsvWriter(final Writer out) {
        super(out);
    }

    /**
     * csv Write line
     *
     * @param csvLine
     *            csv line
     * @throws IOException IOException
     */
    public void writeLine(final List<String> csvLine) throws IOException {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < csvLine.size(); i++) {
            String line = csvLine.get(i);
            if (line == null) {
                line = "";
            }
            sb.append("\"").append(line.replaceAll("\"", "\"\"")).append("\",");
        }

        super.write(sb.deleteCharAt(sb.length() - 1).toString());
        super.newLine();
    }

}

