package br.com.rd.React.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull(message = "Please provide the description!")
    @NotNull(message = "{entity.description.null}")
    @Column(nullable = false) //It's used mainly in the DDL schema metadata generation. This means that if we let Hibernate generate the database schema automatically, it applies the not null constraint to the particular database column
    private String description;
    @NotNull
    @Column(nullable = false)
    private Boolean done;
    @NotNull
    @Column(nullable = false)
    private Date updateDate;

    @PrePersist //Callback for save method
    //@PreRemove //Callback for delete method
    @PreUpdate //Callback for update method (it detects if there is difference to be triggered)
    public void prePersist(){
        Date now = new Date( );
        this.setUpdateDate(now);
    }

//    @PreUpdate //Callback for update method
//    public void preUpdate(){
//        Date now = new Date( );
//        this.setUpdateDate(now);
//    }
}
