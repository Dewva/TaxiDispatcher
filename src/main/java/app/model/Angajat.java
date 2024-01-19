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

@NamedQueries(
        {
                @NamedQuery(name = "findAngajatById", query = "from Angajat pers where pers.id = :id"),
                @NamedQuery(name = "findAllAngajat", query = "from Angajat"),
                @NamedQuery(name = "findAngajatByName", query = "from Angajat pers where pers.name = :name"),
                @NamedQuery(name = "findAngajatByTaxi", query = "from Angajat a where a.taxiId.nrInmatriculare = :nrInmatriculare"),
                @NamedQuery(name = "findAngajatByTimeOfWork", query = "from Angajat a where a.timeOfWork = :timeOfWork")
        }
)
@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Angajat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private Integer salary;

    @Column
    private boolean timeOfWork;

    @OneToOne(cascade = CascadeType.ALL)
    private Taxi taxiId;


}
