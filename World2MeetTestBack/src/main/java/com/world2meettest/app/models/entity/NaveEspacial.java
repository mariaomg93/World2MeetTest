package com.world2meettest.app.models.entity;

import java.io.Serial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "navesEspaciales")
public class NaveEspacial {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNaveEspacial;

	@NotNull(message = "The attribute nombreNaveEspacial must not be null")
	@NotEmpty(message = "The attribute nombreNaveEspacial must not be empty")
	private String nombreNaveEspacial;

}
