package com.fatec.scel.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 5, max = 5, message = "RA deve ter 5 caracteres")
	@Column(unique = true)
	private String ra;
	@NotNull
	@Size(min = 4, max = 100, message = "Nome deve ter min. 4 caracteres")
    private String nome;
    @NotNull
	@Size(min = 9, max = 100, message = "Email deve ter min. 9 caracteres")
    private String email;
    @NotNull
	@Size(min = 9, max = 9, message = "CEP deve ter 8 caracteres")
	private String cep;
	@NotNull
	@Size(min = 10, max = 250, message = "Endereço deve ter entre 10 e 250 caracteres")
	private String endereco;

	public Aluno() {
	}

	public Aluno(String ra, String nome, String email, String cep, String endereco) {
		this.ra = ra;
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setRA (String ra) {
		this.ra = ra;
	}

	public String getRA () {
		return ra;
	}

	public void setNome (String nome) {
		this.nome = nome;
    }
    
    public String getNome () {
		return nome;
	}

	public void setEmail (String email) {
		this.email = email;
    }
    
    public String getEmail () {
		return email;
	}

	public void setCEP(String cep) {
		this.cep = cep;
    }
    
    public String getCEP() {
		return cep;
    }
    
    public void setEndereco(String endereco) {
		this.endereco = endereco;
    }
    
    public String getEndereco() {
		return endereco;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
            return false;
		Aluno other = (Aluno) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ra == null) {
			if (other.ra != null)
				return false;
		} else if (!ra.equals(other.ra))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
		return true;
	}
}