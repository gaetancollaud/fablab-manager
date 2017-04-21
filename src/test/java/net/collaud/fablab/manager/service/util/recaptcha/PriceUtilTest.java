package net.collaud.fablab.manager.service.util.recaptcha;

import net.collaud.fablab.manager.data.type.PriceUnit;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Gaétan on 21/04/2017.
 */
public class PriceUtilTest {

	@Test
	public void testRealEquation(){
		assertThat(PriceUtil.evaluatePrice("x*1", 1)).isEqualTo(1);
		assertThat(PriceUtil.evaluatePrice("x*2", 1.2)).isEqualTo(2.4);
		assertThat(PriceUtil.evaluatePrice("x*2", -1)).isEqualTo(-2);
		assertThat(PriceUtil.evaluatePrice("2+x*5", 1)).isEqualTo(12);
	}

}