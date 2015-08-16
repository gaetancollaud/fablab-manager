package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.type.ConfigurationKey;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_configuration")
@Getter
@Setter
@ToString
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationEO extends AbstractDataEO<Integer> implements Serializable {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "conf_key", nullable = false)
	private String keyString;

	@Column(name = "conf_value")
	private String value;

	public ConfigurationEO(ConfigurationKey key, String value) {
		this.keyString = key.name();
		this.value = value;
	}

	public ConfigurationKey getKey() {
		try {
			return ConfigurationKey.valueOf(keyString);
		} catch (IllegalArgumentException ex) {
			log.error("Unknown key {}", keyString);
			return null;
		}
	}

}
