package net.collaud.fablab.manager.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Entity
@Table(name = "t_asset")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_id", nullable = false)
	private Integer id;

	@Column(name = "title", nullable = true)
	private String title;

	@Lob
	@Column(name = "asset_data", nullable = false, length =  16777215)
	private byte[] data;

	@Column(name = "mime", nullable = false)
	private String mime;

	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private UserEO owner;

}
