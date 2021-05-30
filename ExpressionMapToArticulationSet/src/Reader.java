import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    static List<String> articulationName = new ArrayList<>();
    static List<Integer> keySwitchInteger = new ArrayList<>();

    public static void parse(int k) {

        articulationName = new ArrayList<>();
        keySwitchInteger = new ArrayList<>();

        try {
            System.out.println(Converter.getFileNames()[k]);
            FileInputStream fsStream = new FileInputStream(Converter.getFileNames()[k]);

            System.out.println(fsStream);

            DataInputStream in = new DataInputStream(fsStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while((strLine = br.readLine()) != null) {

                // get names of articulations
                if(strLine.contains("<string name=\"text\" value=\"")){
                    int offset1 = 7;
                    int first = strLine.indexOf("value=\"") + offset1;
                    int last = strLine.indexOf("\" wide=");
                    articulationName.add(strLine.substring(first, last));
                }

                // get keySwitchInteger of articulations (for instance C0, C#0, ...)
                if(strLine.contains("<int name=\"data1\" value=\"")) {
                    int offset2 = 14;
                    int first = strLine.indexOf("data1\" value=\"") + offset2;
                    int last = strLine.indexOf("/>") - 1;
                    keySwitchInteger.add(Integer.parseInt(strLine.substring(first, last)));
                }

            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int halfList1 = articulationName.size()/2;

        // clip duplicate names in the xml file
        for(int i = 0; i < halfList1; i++) {
            articulationName.remove(halfList1*2 - 1 - i);
        }

        int halfList2 = keySwitchInteger.size()/2;
        for(int i = 0; i < halfList2; i++) {
            keySwitchInteger.remove(keySwitchInteger.size() - 1 - i);
        }

        System.out.println(articulationName);
        System.out.println(keySwitchInteger);

    }


    public List<Integer> getKeySwitchInteger() {
        return keySwitchInteger;
    }

    public List<String> getArticulationName() {
        return articulationName;
    }

    public static void main(String[] args) {
        parse(0);
    }

}

