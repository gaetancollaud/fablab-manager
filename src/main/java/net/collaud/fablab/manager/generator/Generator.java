package net.collaud.fablab.manager.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.collaud.fablab.manager.data.EventEO;
/**
 *
 * @author Fabien Vuilleumier
 */
public class Generator {

    private final BackendEOGenerator EO;
    private BackendBaseGenerator base;
    private BackendAngularListEditGenerator angular;

    /*
     * BEGIN CHANGE SECTION
     Must be a table of : 
    
     {"java type", "java name", "nullable ? [t,f]", "db type", "unique[t,f]"} 
     */
    /*FIRST*/
    private final String CLASS_NAME = "Event";
    private final String TABLE_NAME = "t_event";
    private final String SORT_ATTR = "title";

    private final String[][] FIELDS = new String[][]{
        {"Integer", "id", "f", "INT", "t"},
        {"Date", "dateStart", "f", "DATE", "f"},
        {"Date", "dateEnd", "f", "DATE", "f"},
        {"Date", "timeStart", "f", "DATETIME", "f"},
        {"Date", "timeEnd", "f", "DATETIME", "f"},
        {"String", "title", "f", "VARCHAR", "t"},
        {"String", "theme", "t", "VARCHAR", "f"},
        {"String", "place", "t", "VARCHAR", "f"},
        {"String", "description", "t", "TEXT", "f"},
        {"EventTypeEo", "eventType", "f", "INT", "f"},
        {"UserEO", "supervisor", "f", "INT", "f"},
        {"UserEO", "cashier", "t", "INT", "f"}
    };

    private final boolean WRITE = true;
    private final String[] ROLES = new String[]{"EVENT_VIEW"};
    private final Map<String, String> nestedObjectReprAttr = new HashMap<>();

    /*SECOND*/
    private final Class KLAZZ = EventEO.class;//CertificationEO.class;

    public static void main(String[] args) {
        Generator agl = new Generator();
        agl.getNestedObjectReprAttr().put("user", "firstname");
        agl.getNestedObjectReprAttr().put("cashier", "firstname");
        agl.getNestedObjectReprAttr().put("eventType", "label");
        //agl.runEO();
        agl.runBase();
        agl.runAngular(agl.getNestedObjectReprAttr());
    }

    /*END CHANGE SECTION */
    private Generator() {
        this.EO = BackendEOGenerator.getInstance(CLASS_NAME, TABLE_NAME, FIELDS);
    }

    private void runEO() {
        try {
            EO.genereEO();

        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runBase() {
        try {
            this.base = BackendBaseGenerator.getInstance(KLAZZ, FIELDS);
            base.genere(WRITE, ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runAngular(final Map<String, String> nestedObjectReprAttr) {
        try {
            this.angular = BackendAngularListEditGenerator.getInstance(KLAZZ, nestedObjectReprAttr, FIELDS, SORT_ATTR);
            angular.genere(WRITE, "", ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public Map<String, String> getNestedObjectReprAttr() {
        return nestedObjectReprAttr;
    }

    public String getSORT_ATTR() {
        return SORT_ATTR;
    }

    public String[][] getFIELDS() {
        return FIELDS;
    }
    
    
    
    
}
