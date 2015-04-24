/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.collaud.fablab.api.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PriceMachineEOPK implements Serializable {

	@Column(name = "price_revision_id", nullable = false)
	private int priceRevisionId;

	@Column(name = "machine_type_id", nullable = false)
	private int machineTypeId;

	@Column(name = "membership_type_id", nullable = false)
	private int membershipTypeId;


}
