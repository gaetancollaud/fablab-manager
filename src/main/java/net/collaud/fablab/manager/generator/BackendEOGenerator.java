package net.collaud.fablab.manager.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fabien Vuilleumier
 */
public class BackendEOGenerator {

    private static BackendEOGenerator instance;
    private final String CLASS_NAME;
    private final String CLASS_ATTRIBUTE;
    private final String TABLE_NAME;
    private final String[][] FIELDS;
    private final String CLASS_EO;
    private boolean hasList = false;
    private boolean hasEO = false;
    private boolean hasDate = false;
    private List<String> dbTypes;
    Map<String, String> lists;

    private BackendEOGenerator(String className, String tableName, String[][] fields) {
        this.CLASS_NAME = className;
        this.CLASS_ATTRIBUTE = className.substring(0, 1).toLowerCase() + className.substring(1);
        this.TABLE_NAME = tableName;
        this.FIELDS = fields;
        this.CLASS_EO = className + "EO";
    }

    public static synchronized BackendEOGenerator getInstance(String className, String tableName, String[][] fields) {
        if (instance == null) {
            instance = new BackendEOGenerator(className, tableName, fields);
        }
        return instance;
    }

    public void genereEO() throws IOException {
        boolean alreadyDate = false;
        boolean alreadyText = false;
        boolean alreadyBlob = false;
        init();
        StringBuilder str = new StringBuilder();
        str.append("package net.collaud.fablab.manager.data;").append("\n\n");
        str.append("import java.io.Serializable;").append("\n");
        str.append("import javax.persistence.Column;").append("\n");
        str.append("import javax.persistence.Entity;").append("\n");
        str.append("import javax.persistence.GeneratedValue;").append("\n");
        str.append("import javax.persistence.GenerationType;").append("\n");
        str.append("import javax.persistence.Id;").append("\n");
        if (hasList) {
            str.append("import javax.persistence.OneToMany;").append("\n");
            str.append("import javax.persistence.FetchType;").append("\n");
            str.append("import javax.persistence.CascadeType;").append("\n");
            str.append("import java.util.List;").append("\n");
            //str.append("import com.fasterxml.jackson.annotation.JsonManagedReference;").append("\n");
        }
        if (hasEO) {
            str.append("import javax.persistence.JoinColumn;").append("\n");
            str.append("import javax.persistence.ManyToOne;").append("\n");
            str.append("import javax.persistence.FetchType;").append("\n");
            //str.append("import com.fasterxml.jackson.annotation.JsonBackReference;").append("\n");

        }
        if (hasDate) {
            str.append("import java.util.Date;").append("\n");
        }
        for (String s : dbTypes) {
            switch (s.toUpperCase()) {
                case "DATE":
                case "DATETIME":
                case "TIME":
                    if (!alreadyDate) {
                        str.append("import javax.persistence.Temporal;").append("\n");
                        str.append("import javax.persistence.TemporalType;").append("\n");
                        alreadyDate = true;
                    }
                    break;
                case "TEXT":
                case "TINYTEXT":
                case "MEDIUMTEXT":
                case "LONGTEXT":
                    if (!alreadyText) {
                        str.append("import org.hibernate.annotations.Type;").append("\n");
                        alreadyText = true;
                    }
                    break;
                case "BLOB":
                case "TINYBLOB":
                case "MEDIUMBLOB":
                case "LONGBLOB":
                    if (!alreadyBlob) {
                        str.append("import java.sql.Blob;").append("\n");
                        alreadyBlob = true;
                    }
                    break;
            }
        }
        str.append("import javax.persistence.Table;").append("\n");
        str.append("import lombok.Getter;").append("\n");
        str.append("import org.hibernate.annotations.Where;").append("\n");
        str.append("import lombok.Setter;").append("\n");
        str.append("import lombok.ToString;").append("\n\n");
        str.append("/**").append("\n");
        str.append(" * This is the business class for a <tt>").append(CLASS_NAME).append("</tt>").append("\n");
        str.append(" * @author Fabien Vuilleumier").append("\n");
        str.append(" */").append("\n");
        str.append("@Entity").append("\n");
        str.append("@Table(name = \"").append(TABLE_NAME).append("\")").append("\n");
        str.append("@Getter").append("\n");
        str.append("@Setter").append("\n");
        str.append("@ToString");
        if (hasList) {
            str.append(excludeList());
        }
        str.append("\n");
        str.append("@Where(clause=\"active=1\")").append("\n");
        str.append("public class ").append(CLASS_EO).append(" extends AbstractDataEO<Integer> implements Serializable {").append("\n\n");
        if (FIELDS.length != 0) {
            if (FIELDS[0].length == 5) {
                for (String[] fields : FIELDS) {
                    String type = fields[0];
                    String name = fields[1];
                    String nullable = fields[2].equals("t") ? "true" : "false";
                    String optional = fields[2].equals("t") ? "false" : "true";
                    String dbType = fields[3];
                    if (name.equals("id")) {
                        str.append("    ").append("@Id").append("\n");
                        str.append("    ").append("@GeneratedValue(strategy = GenerationType.IDENTITY)").append("\n");
                        str.append("    ").append("@Column(name = \"").append(getId()).append("\", nullable = ").append(nullable).append(")").append("\n");
                        str.append("    ").append("private ").append("Integer").append(" id;").append("\n\n");
                    } else {
                        if (type.contains("List")) {
                            lists.put(name, type);
                            str.append("    ").append("@OneToMany(cascade = CascadeType.PERSIST, mappedBy = \"").append(CLASS_ATTRIBUTE).append("\", fetch = FetchType.LAZY)").append("\n");
                            str.append("    ").append("private ").append(type).append(" ").append(name).append(";\n\n");
                        } else if (type.contains("EO") && !type.contains("List")) {
                            str.append("    ").append("@JoinColumn(name = \"").append(getFKname(name)).append("\", referencedColumnName = \"").append(getFKname(name)).append("\")").append("\n");
                            str.append("    ").append("@ManyToOne(optional = ").append(optional).append(", fetch = FetchType.LAZY)").append("\n");
                            str.append("    ").append("private ").append(type).append(" ").append(name).append(";\n\n");
                        } else {
                            str.append("    ").append("@Column(name = \"").append(getName(name)).append("\", nullable = ").append(nullable);
                            switch (dbType) {
                                case "DATETIME":
                                    str.append(", columnDefinition=\"DATETIME\"");
                                    break;
                            }
                            str.append(" )").append("\n");
                            switch (dbType.toUpperCase()) {
                                case "DATE":
                                    str.append("    ").append("@Temporal(TemporalType.DATE)").append("\n");
                                    break;
                                case "TIME":
                                    str.append("    ").append("@Temporal(TemporalType.TIME)").append("\n");
                                    break;
                                case "DATETIME":
                                case "TIMESTAMP":
                                    str.append("    ").append("@Temporal(TemporalType.TIMESTAMP)").append("\n");
                                    break;
                                case "TEXT":
                                case "TINYTEXT":
                                case "MEDIUMTEXT":
                                case "LONGTEXT":
                                    str.append("    ").append("@Type(type = \"text\")").append("\n");
                                    break;
                            }
                            str.append("    ").append("private ").append(type).append(" ").append(name).append(";\n\n");
                        }
                    }
                }
            }
        }
        str.append("    ").append("@Column(name=\"active\", nullable = false, columnDefinition = \"TINYINT(1) DEFAULT 1\")").append("\n");
        str.append("    ").append("private boolean active;").append("\n\n");
        if (hasList) {
            for (String name : lists.keySet()) {
                String type = lists.get(name);
                StringBuilder listParameEO = new StringBuilder();
                listParameEO.append(type.substring(5));
                listParameEO.deleteCharAt(listParameEO.length() - 1);
                StringBuilder listParamWhithoutList = new StringBuilder();
                listParamWhithoutList.append(name.substring(0, name.length() - 4));
                StringBuilder listGetter = new StringBuilder();
                listGetter.append("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).append("()");
                StringBuilder setter = new StringBuilder();
                setter.append("set").append(CLASS_NAME);
                StringBuilder addMethod = new StringBuilder();
                addMethod.append("add").append(listParamWhithoutList.substring(0, 1).toUpperCase()).append(listParamWhithoutList.substring(1));
                str.append("\n    ").append("/**").append("\n");
                str.append("    ").append("* Add a ").append(CLASS_NAME).append(" to this type (bidirectionnal use).").append("\n");
                str.append("    ").append("*").append("\n");
                str.append("    ").append("* @param ").append(listParamWhithoutList).append(" the ").append(listParamWhithoutList).append("").append("\n");
                str.append("    ").append("*/").append("\n");
                str.append("    ").append("public void ").append(addMethod).append("(").append(listParameEO).append(" ").append(listParamWhithoutList).append(") {").append("\n");
                str.append("        ").append("this.").append(listGetter).append(".add(").append(listParamWhithoutList).append(");").append("\n");
                str.append("        ").append(listParamWhithoutList).append(".").append(setter).append("(this);").append("\n");
                str.append("    ").append("}").append("\n");
            }
        }
        str.append("    ").append("public ").append(CLASS_EO).append("() {").append("\n");
        str.append("        ").append("this(null);").append("\n");
        str.append("    ").append("}").append("\n\n");
        str.append("    ").append("public ").append(CLASS_EO).append("(Integer id) {").append("\n");
        str.append("        ").append("this.active = true;").append("\n");
        str.append("        ").append("this.id = id;").append("\n");
        str.append("    ").append("}").append("\n");
        str.append("}");
        createFile(str.toString(), ".\\src\\main\\java\\net\\collaud\\fablab\\api\\data\\");
    }

    private void createFile(String content, String path) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        path = path + CLASS_NAME + "EO.java";
        Path p = fs.getPath(path);
        Files.deleteIfExists(p);
        List<String> lines = new ArrayList<>();
        lines.add(content);
        Files.write(p, lines, StandardCharsets.UTF_8);
        System.out.println("File " + path + " correctly created...");
    }

    private void init() {
        if (dbTypes == null) {
            dbTypes = new ArrayList<>();
        }
        if (lists == null) {
            lists = new HashMap<>();
        }
        if (FIELDS.length != 0) {
            if (FIELDS[0].length == 5) {
                for (String[] fields : FIELDS) {
                    String type = fields[0];
                    String dbType = fields[3];
                    dbTypes.add(dbType);
                    hasList = hasList || type.contains("List");
                    hasEO = hasEO || (type.contains("EO") && !type.contains("List"));
                    hasDate = hasDate || (type.equals("Date"));
                }
            }
        }
    }

    private String excludeList() {
        StringBuilder str = new StringBuilder();
        List<String> names = new ArrayList<>();
        if (FIELDS.length != 0) {
            if (FIELDS[0].length == 5) {
                for (String[] fields : FIELDS) {
                    if (fields[0].contains("List")) {
                        names.add(fields[1]);
                    }
                }
            }
        }
        boolean many = names.size() > 1;
        str.append("(exclude = ");
        if (many) {
            str.append("{");
        }

        for (String n : names) {
            str.append("\"").append(n).append("\",");
        }
        str.deleteCharAt(str.length() - 1);
        if (many) {
            str.append("}");
        }
        str.append(")");
        return str.toString();
    }

    private StringBuilder getFKname(String attributeName) {
        StringBuilder str = new StringBuilder();
        String[] words = attributeName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String s : words) {
            if (!s.toLowerCase().equals("eo")) {
                str.append(s.toLowerCase()).append("_");
            }
        }
        str.append("id");
        return str;
    }

    private StringBuilder getName(String name) {
        StringBuilder str = new StringBuilder();
        String[] words = name.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String s : words) {
            str.append(s.toLowerCase()).append("_");
        }
        str.deleteCharAt(str.length() - 1);
        return str;
    }

    private StringBuilder getId() {
        StringBuilder str = new StringBuilder();
        String[] words = CLASS_NAME.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String s : words) {
            str.append(s.toLowerCase()).append("_");
        }
        str.append("id");
        return str;
    }
}
