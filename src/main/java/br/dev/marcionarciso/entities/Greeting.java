package br.dev.marcionarciso.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


@Entity
public class Greeting extends PanacheEntity {
	public String name;
}
