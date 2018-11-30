package kr.hsz.domain.account;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import kr.hsz.domain.role.Role;
import kr.hsz.dto.TempUser;
import kr.hsz.enums.EnableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false, of = "userId")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = true)
	private String email;
	@Column(nullable = true)
	private String hp;
	@Column(nullable = true)
	private String tel;
	@Column(nullable = true)
	private String post;
	@Column(nullable = true)
	private String address1;
	@Column(nullable = true)
	private String address2;

	@Column(nullable = false)
	@ColumnDefault("1")
	@Enumerated(EnumType.ORDINAL)
	private EnableStatus enable;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Role> roles;

	public Account(TempUser tempUser) {
		this.userId = tempUser.getUserId();
		this.password = tempUser.getUserPassword();
		this.username = tempUser.getUserName();
		this.enable = EnableStatus.TRUE;
		this.roles.add(new Role(4L,"ROLE_TEMP", "임시접근자"));
	}
	
}
