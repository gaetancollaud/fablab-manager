package net.collaud.fablab.api.rest.v1.helper;

import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.rest.v1.data.AbstractTO;

/**
 *
 * @author Gaétan
 * @param <TO>
 * @param <EO>
 */
abstract public class AbstractTOHelper <TO extends AbstractTO, EO extends AbstractDataEO> {
	
	
	public List<TO> fromEOList(List<EO> eo){
		List<TO> to = new ArrayList<>(eo.size());
		eo.forEach(u -> to.add(fromEO(u)));
		return to;
	}
	
	public List<EO> fromTOList(List<TO> to){
		List<EO> eo = new ArrayList<>(to.size());
		to.forEach(t -> eo.add(fromTO(t)));
		return eo;
	}
	
	abstract public EO fromTO(TO to);
	abstract public TO fromEO(EO eo);
}
