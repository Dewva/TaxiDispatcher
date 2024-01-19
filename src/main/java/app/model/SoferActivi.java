package app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name = "findSoferActiviById", query = "from SoferActivi pers where pers.id = :id"),
                @NamedQuery(name = "findAllSoferActivi", query = "from SoferActivi "),
               // @NamedQuery(name = "findSoferActiviByName", query = "from SoferActivi pers where pers.sofer.name = :name"),
                @NamedQuery(name = "findFreeSoferActivi", query = "from SoferActivi s where s.inCursa = false"),
        }
)

public class SoferActivi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Angajat sofer;

    @Column
    private Boolean inCursa;

    @Column
    private Integer nrCurse;

    @Column
    private Integer profit;

}
