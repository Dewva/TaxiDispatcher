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
                @NamedQuery(name = "findTaxiById", query = "from Taxi pers where pers.id = :id"),
                @NamedQuery(name = "findAllTaxi", query = "from Taxi "),
                @NamedQuery(name = "findTaxiByName", query = "from Taxi pers where pers.nrInmatriculare = :nrInmatriculare"),
        }
)

public class Taxi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nrInmatriculare;

    @Column
    private String stare;

}
