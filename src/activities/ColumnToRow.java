package activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Translation {

    private final String code;
    private final String text;
    private final Map<String, String> cultures;

    public Translation(String code, String text) {
        this.code = code;
        this.text = text;
        this.cultures = new HashMap<>();
    }

    public void addCulture(String culture, String value) {
        cultures.put(culture, value);
    }

    public Map<String, String> getCultures() { return cultures; }

    public String getCode() { return code; }

    public String getText() { return text; }
}

/**
 *
 * @author david
 */
public class ColumnToRow {

    enum Direction { COL, ROW };

    public static void main(String[] args) {

        List<Translation> translations = new ArrayList<>();

        translations.add(new Translation("HELLO", "Hello") {
            {
                addCulture("fl-PH", "Kamusta");
                addCulture("zh-CN", "Ni hao");
                /* add more culture, value here */
            }
        });

        translations.add(new Translation("BYE", "Good bye") {
            {
                addCulture("en-US", "Bye bye");
                addCulture("fl-PH", "Paalam");
                addCulture("zh-CN", "Zaijian");
                /* add more culture, value here */
            }
        });

        print(Direction.ROW, translations);
        System.out.print("\n\n");
        print(Direction.COL, translations);
    }

    private static void printHorizontalLine(int count) {
        while (count-- > 0) System.out.print('-');
        System.out.println();
    }

    private static void print(Direction direction, List<Translation> translations) {

        var defaultValue = ""; /* default value for unknown country code */

        switch (direction) {

            case Direction.ROW -> {
                System.out.println("***** START: ORGINAL DATA *****");
                
                /* column header */
                printHorizontalLine(90);
                System.out.printf("| %-10s| %-30s| %-20s| %-20s |\n", "Code", "Text", "en-US", "zh-CN");
                printHorizontalLine(90);
                
                /* column values */
                translations.forEach((translation) -> {
                    System.out.printf("| %-10s| %-30s| %-20s| %-20s |\n",
                        translation.getCode(), translation.getText(),
                        translation.getCultures().getOrDefault("en-US", defaultValue),
                        translation.getCultures().getOrDefault("zh-CN", defaultValue)
                    );
                    printHorizontalLine(90);
                });
                
                System.out.println("***** END: ORGINAL DATA *****");
            }

            case Direction.COL -> {
                System.out.println("***** START: TRANSFORMED DATA *****");
                
                /* column header */
                printHorizontalLine(57);
                System.out.printf("| %-10s| %-10s| %-30s|\n", "Code", "Culture", "Value");
                printHorizontalLine(57);
                
                /* column values */
                translations.forEach((translation) -> {
                    var code = translation.getCode();
                    var cultures = translation.getCultures().keySet();
                    cultures.forEach((culture) -> {
                        var value = translation.getCultures().getOrDefault(culture, defaultValue);
                        System.out.printf("| %-10s| %-10s| %-30s|\n", code, culture, value);
                        printHorizontalLine(57);
                    });
                });
                
                System.out.println("***** END: TRANSFORMED DATA *****");
            }

        } /* end of switch */

    } /* end of print() */

} /* end of ColumnToRow */


/*
SAMPLE run:
***** START: ORGINAL DATA *****
------------------------------------------------------------------------------------------
| Code      | Text                          | en-US               | zh-CN                |
------------------------------------------------------------------------------------------
| HELLO     | Hello                         |                     | Ni hao               |
------------------------------------------------------------------------------------------
| BYE       | Good bye                      | Bye bye             | Zaijian              |
------------------------------------------------------------------------------------------
***** END: ORGINAL DATA *****


***** START: TRANSFORMED DATA *****
---------------------------------------------------------
| Code      | Culture   | Value                         |
---------------------------------------------------------
| HELLO     | fl-PH     | Kamusta                       |
---------------------------------------------------------
| HELLO     | zh-CN     | Ni hao                        |
---------------------------------------------------------
| BYE       | fl-PH     | Paalam                        |
---------------------------------------------------------
| BYE       | en-US     | Bye bye                       |
---------------------------------------------------------
| BYE       | zh-CN     | Zaijian                       |
---------------------------------------------------------
***** END: TRANSFORMED DATA *****
BUILD SUCCESSFUL (total time: 0 seconds)
*/