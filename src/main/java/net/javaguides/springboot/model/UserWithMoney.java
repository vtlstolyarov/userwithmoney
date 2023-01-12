package net.javaguides.springboot.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class UserWithMoney extends User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
//	@Column(unique = true)
	private String email;
	
	private String password;


	private int money;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	public UserWithMoney(String email, String password, Collection<? extends GrantedAuthority> authority, int money, Collection<Role> roles) {
		super(email, password,  authority);
		this.money = money;
		this.roles = roles;
	}

	public <T> UserWithMoney(String email, String password, int money, String encode, List<T> roleUser) { //не запустится, так как есть нулл
		super(email, password, null);
		this.money = money;
	}

	public UserWithMoney() {
		super(null, null, null);
	} //не запустится, так как есть нулл

	public UserWithMoney(String email, String password,  Collection<? extends GrantedAuthority> mapRolesToAuthorities, int money) { //тут под вопросом
		super(email, password, mapRolesToAuthorities);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "UserWithMoney{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", roles=" + roles +
				'}';
	}
}
