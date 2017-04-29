package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;
import net.collaud.fablab.manager.export.CsvToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "v_user_balance_grouped")
@Getter
@ToString
public class UserBalanceEO implements Serializable, CsvToString {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "balance", nullable = false)
	private double value;

	@Override
	public String CsvToString() {
		return String.valueOf(value);
	}
}
