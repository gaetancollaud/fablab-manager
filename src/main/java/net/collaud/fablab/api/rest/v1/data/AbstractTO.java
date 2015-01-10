package net.collaud.fablab.api.rest.v1.data;

import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.rest.v1.AuthWS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 * @param <EO>
 * @param <TO>
 */
public abstract class AbstractTO<EO extends AbstractDataEO, TO extends AbstractTO> {
	private static final Logger LOG = LogManager.getLogger(AbstractTO.class);

	public AbstractTO() {
	}

	public static <E extends AbstractDataEO, T extends AbstractTO> List<T>
			fromEOList(Iterable<E> eo, Class<E> eoClass, Class<T> toClass) {
		List<T> toList = new ArrayList<>();
		eo.forEach(u -> {
			try {
				T to = toClass.newInstance();
				toList.add((T) to.fromEO(u));
			} catch (InstantiationException | IllegalAccessException ex) {
				LOG.error(ex);
			}
		});
		return toList;
	}

	public List<EO> fromTOList(Iterable<TO> to) {
		List<EO> eo = new ArrayList<>();
		to.forEach(t -> eo.add(fromTO(t)));
		return eo;
	}

	abstract public EO fromTO(TO to);

	abstract public TO fromEO(EO eo);
}
