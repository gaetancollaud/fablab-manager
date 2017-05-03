package net.collaud.fablab.manager.service.util.recaptcha;

import net.collaud.fablab.manager.service.util.PriceUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by Gaétan on 21/04/2017.
 */
public class PriceUtilTest {

	@Test
	public void testRealEquation(){
		assertThat(PriceUtil.evaluatePrice("amount*1", 1)).isEqualTo(1);
		assertThat(PriceUtil.evaluatePrice("amount*2", 1.2)).isEqualTo(2.4);
		assertThat(PriceUtil.evaluatePrice("amount*2", -1)).isEqualTo(-2);
		assertThat(PriceUtil.evaluatePrice("amount/0", -1)).isEqualTo(Double.NEGATIVE_INFINITY);
		assertThat(PriceUtil.evaluatePrice("2+amount*5", 2)).isEqualTo(12);
		assertThat(PriceUtil.evaluatePrice("amount*amount", 2)).isEqualTo(4);
		assertThat(PriceUtil.evaluatePrice("Math.ceil(amount)", 2.1)).isEqualTo(3);
		assertThat(PriceUtil.evaluatePrice("Math.max(2, amount*0.200)", 20)).isEqualTo(4);
		assertThat(PriceUtil.evaluatePrice("Math.max(2, amount*0.200)", 10)).isEqualTo(2);
		assertThat(PriceUtil.evaluatePrice("Math.max(2, amount*0.200)", 5)).isEqualTo(2);
	}

	@Test
	public void testWrongEquation(){
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("y*1", 1)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("bla", 1)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("sqrt(amount)", 1)).isInstanceOf(RuntimeException.class);
	}

}