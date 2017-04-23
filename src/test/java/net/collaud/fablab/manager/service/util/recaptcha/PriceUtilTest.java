package net.collaud.fablab.manager.service.util.recaptcha;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by Gaétan on 21/04/2017.
 */
public class PriceUtilTest {

	@Test
	public void testRealEquation(){
		assertThat(PriceUtil.evaluatePrice("x*1", 1)).isEqualTo(1);
		assertThat(PriceUtil.evaluatePrice("x*2", 1.2)).isEqualTo(2.4);
		assertThat(PriceUtil.evaluatePrice("x*2", -1)).isEqualTo(-2);
		assertThat(PriceUtil.evaluatePrice("2+x*5", 2)).isEqualTo(12);
		assertThat(PriceUtil.evaluatePrice("x*x", 2)).isEqualTo(4);
		assertThat(PriceUtil.evaluatePrice("Math.ceil(x)", 2.1)).isEqualTo(3);
	}

	@Test
	public void testWrongEquation(){
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("y*1", 1)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("x/0", 1)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("bla", 1)).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> PriceUtil.evaluatePrice("sqrt(x)", 1)).isInstanceOf(RuntimeException.class);
	}

}