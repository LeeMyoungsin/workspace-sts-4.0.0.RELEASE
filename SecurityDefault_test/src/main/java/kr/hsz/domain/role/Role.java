package kr.hsz.domain.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@EqualsAndHashCode(callSuper=false, of = "id")
public class Role {

	@Id
	private Long id;
	
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;


}
