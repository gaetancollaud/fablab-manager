package net.collaud.fablab.manager.rest.v1;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.ProjectEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/project")
@JavascriptAPIConstant("PROJECT_API")
@Slf4j
public class ProjectWS extends ReadWriteRestWebservice<ProjectEO, ProjectService>{

	@Autowired
	private ProjectService projectService;

	@PostConstruct
	public void postConstruct(){
		super.setService(projectService);
	}

}
