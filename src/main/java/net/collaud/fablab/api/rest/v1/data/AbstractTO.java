package net.collaud.fablab.api.rest.v1.data;

import java.util.ArrayList;
import java.util.List;
import net.collaud.fablab.api.data.AbstractDataEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 * @param <EO>
 * @param <TO>
 */
public abstract class AbstractTO<EO extends AbstractDataEO, TO extends AbstractTO> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTO.class);

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
				LOG.error("error", ex);
			}
		});
		return toList;
	}

	public List<EO> fromTOList(Iterable<TO> to) {
		List<EO> eo = new ArrayList<>();
		to.forEach(t -> {
			eo.add((EO) t.convertToEO());
		});
		return eo;
	}

	abstract public EO convertToEO();

	abstract public TO fromEO(EO eo);
}
