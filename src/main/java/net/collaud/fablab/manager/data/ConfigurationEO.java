package net.collaud.fablab.manager.data;

import java.io.Serializable;
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

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> 
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id", nullable = false)
    private Integer id;

    @Column(name = "conf_key", nullable = false)
    private String key;

    @Column(name = "conf_value")
    private String value;

    @Column(name = "common_name", nullable = false)
    private String name;

}
