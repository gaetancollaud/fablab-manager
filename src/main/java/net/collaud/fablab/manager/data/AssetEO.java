package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mysema.query.annotations.QueryProjection;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssetEO extends AbstractDataEO<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_id", nullable = false)
	private Integer id;

	@Column(name = "title", nullable = true)
	private String title;

	@Lob
	@Column(name = "asset_data", nullable = false, length = 16777215)
	private byte[] data;

	@Column(name = "mime", nullable = false)
	private String mime;
	
	@Column(name = "data_size", nullable = false)
	private Integer size;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_upload", nullable = false)
	private LocalDateTime dateUpload;

	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private UserEO owner;

	@QueryProjection
	public AssetEO(Integer id, String title, String mime, Integer size, LocalDateTime dateUpload, UserEO owner) {
		this.id = id;
		this.title = title;
		this.mime = mime;
		this.owner = owner;
	}

	@QueryProjection
	public AssetEO(Integer id, String title, byte[] data, String mime, Integer size, LocalDateTime dateUpload, UserEO owner) {
		this.id = id;
		this.title = title;
		this.data = data;
		this.mime = mime;
		this.size = size;
		this.dateUpload = dateUpload;
		this.owner = owner;
	}

}
