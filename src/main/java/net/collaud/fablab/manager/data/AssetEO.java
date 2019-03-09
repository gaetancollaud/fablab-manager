package net.collaud.fablab.manager.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import javax.persistence.*;
import java.time.Instant;

/**
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
public class AssetEO extends AbstractDataEO<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_id", nullable = false)
	private Long id;

	@Column(name = "title", nullable = true)
	private String title;

	@Lob
	@Column(name = "asset_data", nullable = false, length = 16777215)
	private byte[] data;

	@Column(name = "mime", nullable = false)
	private String mime;

	@Column(name = "data_size", nullable = false)
	private Integer size;

	@Column(name = "date_upload", nullable = false)
	private Instant dateUpload;

	@Column(name = "extension", nullable = false)
	private String extension;

	@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private UserEO owner;

	@QueryProjection
	public AssetEO(Long id, String title, String mime, Integer size, Instant dateUpload, String extension, UserEO owner) {
		this.id = id;
		this.title = title;
		this.mime = mime;
		this.size = size;
		this.extension = extension;
		this.owner = owner;
		this.dateUpload = dateUpload;
	}

	@QueryProjection
	public AssetEO(Long id, String title, byte[] data, String mime, Integer size, Instant dateUpload, String extension, UserEO owner) {
		this(id, title, mime, size, dateUpload, extension, owner);
		this.data = data;
	}

}
