package net.collaud.fablab.manager.generator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fabien Vuilleumier
 */
public class BackendBaseGenerator {

    private final String DAO = ".\\src\\main\\java\\net\\collaud\\fablab\\api\\dao";
    private final String REST_v1 = ".\\src\\main\\java\\net\\collaud\\fablab\\api\\rest\\v1";
    private final String SERVICE = ".\\src\\main\\java\\net\\collaud\\fablab\\api\\service";
    private final String SERVICE_IMPL = ".\\src\\main\\java\\net\\collaud\\fablab\\api\\service\\impl";

    private boolean hasList = false;

    private final List<Map<String, String>> FIELDS;
    private final String CLASS_NAME;
    private final String CLASS_ATTRIBUTE;
    private final String CLASS_EO;
    private final String CLASS_SERVICE;
    private final String CLASS_SERVICE_IMPL;
    private final String CLASS_SERVICE_ATTRIBUTE;
    private final String CLASS_REPOSITORY;
    private final String CLASS_DAO_ATTRIBUTE;

    private static BackendBaseGenerator instance;

    private <T> BackendBaseGenerator(Class<T> klazz, String[][] fields) {
        this.FIELDS = new LinkedList<>();
        if (fields[0].length == 5) {
            for (String[] s : fields) {
                Map<String, String> map = new HashMap<>();
                map.put("type", s[0]);
                map.put("name", s[1]);
                map.put("required", s[2]);
                map.put("dbType", s[3]);
                map.put("unique", s[4]);
                this.FIELDS.add(map);
            }
        } else {
            throw new IllegalArgumentException("PAS LE BON NOMBRE D'ARGUMENTS");
        }
        this.CLASS_NAME = klazz.getSimpleName().substring(0, klazz.getSimpleName().length() - 2);
        this.CLASS_ATTRIBUTE = CLASS_NAME.substring(0, 1).toLowerCase() + CLASS_NAME.substring(1);
        this.CLASS_EO = CLASS_NAME + "EO";
        this.CLASS_DAO_ATTRIBUTE = CLASS_ATTRIBUTE + "DAO";
        this.CLASS_SERVICE = CLASS_NAME + "Service";
        this.CLASS_SERVICE_ATTRIBUTE = CLASS_ATTRIBUTE + "Service";
        this.CLASS_SERVICE_IMPL = CLASS_NAME + "ServiceImpl";
        this.CLASS_REPOSITORY = CLASS_NAME + "Repository";
    }

    public static synchronized <T> BackendBaseGenerator getInstance(Class<T> klazz, String[][] fields) {
        if (instance == null) {
            instance = new BackendBaseGenerator(klazz, fields);
        }
        return instance;
    }

    private String genereServiceImpl(boolean write, String... roles) {
        StringBuilder str = new StringBuilder();
        str.append("package net.collaud.fablab.manager.service.impl;").append("\n\n");
        str.append("import java.util.ArrayList;").append("\n");
        str.append("import java.util.HashSet;").append("\n");
        str.append("import java.util.List;").append("\n");
        str.append("import java.util.Optional;").append("\n");
        str.append("import net.collaud.fablab.manager.dao.").append(CLASS_REPOSITORY).append(";").append("\n");
        str.append("import net.collaud.fablab.manager.data.").append(CLASS_EO).append(";").append("\n");
        str.append("import net.collaud.fablab.manager.security.Roles;").append("\n");
        str.append("import net.collaud.fablab.manager.service.").append(CLASS_SERVICE).append(";").append("\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
        str.append("import org.springframework.security.access.annotation.Secured;").append("\n");
        str.append("import org.springframework.stereotype.Service;").append("\n");
        str.append("import org.springframework.transaction.annotation.Transactional;").append("\n");

        str.append("/**").append("\n");
        str.append(" * This is the service implementation class for a <tt>").append(CLASS_NAME).append("</tt>.\n");
        str.append(" * @author Fabien Vuilleumier").append("\n");
        str.append(" */").append("\n");
        str.append("@Service").append("\n");
        str.append("@Transactional").append("\n");

        str.append(rolesView(roles));
        str.append("public class ").append(CLASS_SERVICE_IMPL).append(" implements ").append(CLASS_SERVICE).append(" {").append("\n\n");

        str.append("    ").append("@Autowired").append("\n");
        str.append("    ").append("private ").append(CLASS_REPOSITORY).append(" ").append(CLASS_DAO_ATTRIBUTE).append(";\n\n");

        str.append("    ").append("@Override").append("\n");
        str.append(rolesView(roles));
        str.append("    ").append("public List<").append(CLASS_EO).append("> findAll() {").append("\n");
        str.append("        ").append("return new ArrayList(new HashSet(").append(CLASS_DAO_ATTRIBUTE).append(".findAll()));").append("\n");
        str.append("    ").append("}").append("\n\n");

        str.append("    ").append("@Override").append("\n");
        str.append(rolesView(roles));
        str.append("    ").append("public Optional<").append(CLASS_EO).append("> getById(Integer id) {").append("\n");
        str.append("        ").append("return ").append(CLASS_DAO_ATTRIBUTE).append(".findOneDetails(id);").append("\n");

        str.append("    ").append("}").append("\n\n");

        if (write) {
            str.append("    ").append(" @Override").append("\n");
            str.append(roles(roles));
            str.append("    ").append("public ").append(CLASS_EO).append(" save(").append(CLASS_EO).append(" ").append(CLASS_ATTRIBUTE).append(") {").append("\n");
            str.append("        ").append("if (").append(CLASS_ATTRIBUTE).append(".getId() == null) {").append("\n");
            str.append("            ").append(CLASS_ATTRIBUTE).append(".setId(0);").append("\n");
            str.append("        ").append("}").append("\n");
            str.append("        ").append("if (").append(CLASS_ATTRIBUTE).append(".getId() > 0) {").append("\n");
            str.append("            ").append(CLASS_EO).append(" old = ").append(CLASS_DAO_ATTRIBUTE).append(".findOneDetails(").append(CLASS_ATTRIBUTE).append(".getId()).get();").append("\n");

            for (Map<String, String> f : FIELDS) {
                if (!f.get("name").equals("id")) {
                    str.append("            ").append("old.").append(setter(f)).append("(").append(CLASS_ATTRIBUTE).append(".").append(getter(f)).append(");").append("\n");
                }
            }

            str.append("            ").append("return ").append(CLASS_DAO_ATTRIBUTE).append(".saveAndFlush(old);").append("\n");
            str.append("        ").append("} else {").append("\n");
            str.append("            ").append("return ").append(CLASS_DAO_ATTRIBUTE).append(".saveAndFlush(").append(CLASS_ATTRIBUTE).append(");").append("\n");
            str.append("        ").append("}").append("\n");
            str.append("    ").append("}").append("\n\n");

            str.append("    ").append("@Override").append("\n");
            str.append(roles(roles));
            str.append("    ").append("public void remove(Integer id) {").append("\n");
            str.append("        ").append(CLASS_DAO_ATTRIBUTE).append(".delete(id);").append("\n");
            str.append("    ").append("}").append("\n\n");

            str.append("    ").append("@Override").append("\n");
            str.append(roles(roles));
            str.append("    ").append("public void softRemove(Integer id) {").append("\n");
            str.append("        ").append(CLASS_EO).append(" current = ").append(CLASS_DAO_ATTRIBUTE).append(".findOne(id);").append("\n");
            str.append("        ").append("current.setActive(false);").append("\n");
            str.append("        ").append(CLASS_DAO_ATTRIBUTE).append(".saveAndFlush(current);").append("\n");
            str.append("    ").append("}").append("\n");
        }
        str.append("}").append("\n");
        return str.toString();
    }

    private StringBuilder roles(String... roles) {
        StringBuilder str = new StringBuilder();
        str.append("    @Secured({");
        for (String s : roles) {
            str.append("Roles.").append(s).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("})").append("\n");
        return str;
    }

    private StringBuilder rolesView(String... roles) {
        StringBuilder str = new StringBuilder();
        boolean containView = false;
        str.append("    @Secured({");
        for (String s : roles) {
            containView = containView || s.toUpperCase().contains("VIEW");
            str.append("Roles.").append(s).append(",");
        }
        str.deleteCharAt(str.length() - 1);
        if (!containView) {
            for (String s : roles) {
                String[] views = s.split("_");
                if (views.length > 0) {
                    str.append("Roles.").append(views[0]).append("_VIEW,");
                }
            }
        }
        str.append("})").append("\n");
        return str;
    }

    private String genereService(boolean write) {
        StringBuilder str = new StringBuilder();
        str.append("package net.collaud.fablab.manager.service;").append("\n\n");
        str.append("import net.collaud.fablab.manager.data.");
        str.append(CLASS_EO);
        str.append(";").append("\n");
        if (write) {
            str.append("import net.collaud.fablab.manager.service.global.ReadWriteService;").append("\n");
        } else {
            str.append("import net.collaud.fablab.manager.service.global.ReadService;").append("\n\n");
        }
        str.append("/**").append("\n");
        str.append(" *This is the Service interface for a <tt>");
        str.append(CLASS_NAME);
        str.append("</tt>.").append("\n");
        str.append("* @author Fabien Vuilleumier").append("\n");
        str.append("*/").append("\n");
        str.append("public interface ").append(CLASS_SERVICE).append(" extends ");
        if (write) {
            str.append("ReadWriteService");
        } else {
            str.append("ReadService");
        }
        str.append("<").append(CLASS_EO).append(">");
        str.append("{\n\n}");
        return str.toString();
    }

    private String genereWS(boolean write) {
        StringBuilder str = new StringBuilder();
        str.append("package net.collaud.fablab.manager.rest.v1;").append("\n\n");
        str.append("import javax.annotation.PostConstruct;").append("\n");
        str.append("import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;").append("\n");
        str.append("import net.collaud.fablab.manager.data.");
        str.append(CLASS_EO);
        str.append(";").append("\n");
        if (write) {
            str.append("import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;").append("\n");
            str.append("import net.collaud.fablab.manager.exceptions.FablabException;").append("\n");
        } else {
            str.append("import net.collaud.fablab.manager.rest.v1.base.ReadRestWebservice;").append("\n");
        }
        str.append("import net.collaud.fablab.manager.service.");
        str.append(CLASS_SERVICE);
        str.append(";").append("\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
        str.append("import org.springframework.web.bind.annotation.RequestMapping;").append("\n");
        str.append("import org.springframework.web.bind.annotation.RestController;").append("\n\n");
        str.append("/**").append("\n");
        str.append(" *This is the WS class for a <tt>");
        str.append(CLASS_NAME);
        str.append("</tt>.").append("\n");
        str.append("* @author Fabien Vuilleumier").append("\n");
        str.append("*/").append("\n");
        str.append("@RestController()").append("\n");
        str.append("@RequestMapping(\"/v1/").append(CLASS_ATTRIBUTE).append("\")\n");
        str.append("@JavascriptAPIConstant(\"").append(getApiName()).append("\")").append("\n");
        str.append("public class ").append(CLASS_NAME).append("WS");
        str.append(" extends ");
        if (write) {
            str.append("ReadWriteRestWebservice<");
        } else {
            str.append("ReadRestWebservice<");
        }
        str.append(CLASS_EO).append(", ").append(CLASS_SERVICE).append(">{").append("\n\n");
        str.append("    ").append("@Autowired").append("\n");
        str.append("    ").append("private ").append(CLASS_SERVICE).append(" ").append(CLASS_SERVICE_ATTRIBUTE).append(";\n\n");
        str.append("    ").append("@PostConstruct").append("\n");
        str.append("    ").append("public void postConstruct(){").append("\n");
        str.append("        ").append("super.setService(").append(CLASS_SERVICE_ATTRIBUTE).append(");\n");
        str.append("    ").append("}").append("\n");

        str.append("}").append("\n");
        return str.toString();
    }

    private StringBuilder getApiName() {
        StringBuilder str = new StringBuilder();
        String[] words = CLASS_NAME.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String s : words) {
            str.append(s.toUpperCase()).append("_");
        }
        str.append("API");
        return str;
    }

    private String generateDaoInterface() {
        String queryAttribute = CLASS_ATTRIBUTE.substring(0, 1);
        StringBuilder str = new StringBuilder();
        str.append("package net.collaud.fablab.manager.dao;\n").append("\n");
        str.append("import java.util.List;").append("\n");
        str.append("import net.collaud.fablab.manager.data.").append(CLASS_EO).append(";\n");
        str.append("import org.springframework.data.jpa.repository.JpaRepository;").append("\n");
        str.append("import org.springframework.data.jpa.repository.Query;").append("\n");
            str.append("import java.util.Optional;").append("\n");
            str.append("import org.springframework.data.jpa.repository.Query;").append("\n");
            str.append("import org.springframework.data.repository.query.Param;").append("\n");
        str.append("/**").append("\n");
        str.append(" *This is the DAO interface for a <tt>").append(CLASS_NAME).append("</tt>.").append("\n");
        str.append(" * @author Fabien Vuilleumier").append("\n");
        str.append(" */").append("\n");
        str.append("public interface ")
                .append(CLASS_NAME)
                .append("Repository extends JpaRepository<")
                .append(CLASS_EO)
                .append(", Integer>{\n")
                .append("\n");

            str.append("    ").append("@Query(\"SELECT ").append(queryAttribute).append(" \"").append("\n");
            str.append("        ").append("+ \" FROM ").append(CLASS_EO).append(" ").append(queryAttribute).append(" ");
            for (Map<String, String> f : FIELDS) {
                if (f.get("type").contains("EO") && !f.get("type").contains("LIST")) {
                    str.append(" \" \n").append("        ").append("+ \" LEFT JOIN FETCH ").append(queryAttribute).append(".").append(f.get("name")).append(" ");
                }
            }
            str.append(" \" )").append("\n");
            str.append("    ").append("@Override").append("\n");
            str.append("    List<").append(CLASS_EO).append("> findAll();").append("\n");

            str.append("    ").append("@Query(\"SELECT ").append(queryAttribute).append(" \"").append("\n");
            str.append("        ").append("+ \" FROM ").append(CLASS_EO).append(" ").append(queryAttribute).append(" \"").append("\n");
            for (Map<String, String> f : FIELDS) {
                if (f.get("type").contains("EO") && !f.get("type").contains("List")) {
                    str.append("        ").append("+ \" LEFT JOIN FETCH ").append(queryAttribute).append(".").append(f.get("name")).append(" \"").append("\n");
                }
            }
            str.append("        ").append("+ \" WHERE ").append(queryAttribute).append(".id=:id\")").append("\n");
            str.append("    ").append("Optional<").append(CLASS_EO).append("> findOneDetails(@Param(\"id\")Integer id);").append("\n");


        str.append("}");
        return str.toString();
    }

    public void genere(boolean write, String... roles) throws IOException {
        init();
        System.out.println("CLASS_NAME : " + CLASS_NAME);
        System.out.println("CLASS_ATTRIBUTE : " + CLASS_ATTRIBUTE);
        System.out.println("CLASS_EO : " + CLASS_EO);
        System.out.println("CLASS_REPOSITORY : " + CLASS_REPOSITORY);
        System.out.println("CLASS_SERVICE : " + CLASS_SERVICE);
        System.out.println("CLASS_SERVICE_ATTRIBUTE : " + CLASS_SERVICE_ATTRIBUTE);
        System.out.println("CLASS_SERVICE_IMPL : " + CLASS_SERVICE_IMPL);
        createFile(generateDaoInterface(), DAO + "\\" + CLASS_REPOSITORY);
        createFile(genereWS(write), REST_v1 + "\\" + CLASS_NAME + "WS");
        createFile(genereService(write), SERVICE + "\\" + CLASS_SERVICE);
        createFile(genereServiceImpl(write, roles), SERVICE_IMPL + "\\" + CLASS_SERVICE_IMPL);
    }

    private void createFile(String content, String path) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        path = path + ".java";
        Path p = fs.getPath(path);
        Files.deleteIfExists(p);
        List<String> lines = new ArrayList<>();
        lines.add(content);
        Files.write(p, lines, StandardCharsets.UTF_8);
        System.out.println("File " + path + " correctly created...");
    }

    private String getter(Map<String, String> f) {
        StringBuilder str = new StringBuilder();
        if (f.get("type").toLowerCase().equals("boolean")) {
            str.append("is");
        } else {
            str.append("get");
        }
        str.append(((f.get("name")).substring(0, 1)).toUpperCase())
                .append((f.get("name")).substring(1))
                .append("()");
        return str.toString();
    }

    private String setter(Map<String, String> f) {
        StringBuilder str = new StringBuilder();
        str.append("set")
                .append((f.get("name").substring(0, 1)).toUpperCase())
                .append(f.get("name").substring(1));
        return str.toString();
    }

    private void init() {
        for (Map<String, String> f : FIELDS) {
            String type = f.get("type");
            hasList = hasList || type.contains("List");
        }
    }

}
