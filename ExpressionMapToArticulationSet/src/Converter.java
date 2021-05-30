import java.io.*;
import java.util.List;
import java.util.Objects;

public class Converter {

    /**
     * Header is always included
     */
    private static String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
            "<plist version=\"1.0\">\n" + "<dict>" + "<key>Articulations</key>\n" +
            "\t<array>";

    private int number = 1001;

    private static StringBuilder sb = new StringBuilder();

    private static File[] fileNames = new File("SourceFiles").listFiles();

    public static File[] getFileNames() {
        return fileNames;
    }

    public static void main(String[] args) {
        Converter converter = new Converter();

        int numberOfFiles = Objects.requireNonNull(new File("SourceFiles").listFiles()).length;

        for (int j = 0; j < numberOfFiles; j++) {

            Reader reader = new Reader();

            sb.append(header);

            assert fileNames != null;

            int dot = fileNames[j].getName().indexOf(".");

            String outputFileName = "OutputFiles/" + fileNames[j].getName().substring(0, dot) + "_ArticulationSet.plist";

            String tail = "</array>\n" +
                    "\t<key>Name</key>\n" +
                    "\t<string>" + outputFileName + "</string>\n" +
                    "\t<key>Switches</key>\n" +
                    "\t<array/>\n" +
                    "</dict>\n" +
                    "</plist>\n";

            Reader.parse(j);

            String internal = "";

            for (int i = 1001; i < 1001 + Reader.articulationName.size(); i++) {
                internal = "\t\t\t<dict>\n" +
                        "\t\t\t<key>ArticulationID</key>\n" +
                        "\t\t\t<integer>"+ (i - 1000)+"</integer>\n" +
                        "\t\t\t<key>ID</key>\n" +
                        "\t\t\t<integer>" + i + "</integer>\n" +
                        "\t\t\t<key>Name</key>\n" +
                        "\t\t\t<string>" + Reader.articulationName.get(i - 1001) + "</string>" + "\n" +
                        "\t\t\t<key>Output</key>\n" +
                        "\t\t\t<array>\n" +
                        "\t\t\t\t<dict>\n" +
                        "\t\t\t\t\t<key>MB1</key>\n" +
                        "\t\t\t\t\t<integer>" + Reader.keySwitchInteger.get(i - 1001) + "</integer>\n" +
                        "\t\t\t\t\t<key>Status</key>\n" +
                        "\t\t\t\t\t<string>Note On</string>\n" +
                        "\t\t\t\t</dict>\n" +
                        "\t\t\t</array>\n" +
                        "</dict>\n";

                sb.append(internal);
            }

            sb.append(tail);

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
                writer.write(sb.toString());
                sb.delete(0,sb.length());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

