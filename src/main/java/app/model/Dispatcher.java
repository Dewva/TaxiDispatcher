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
                @NamedQuery(name = "findDispatcherById", query = "from Dispatcher pers where pers.id = :id"),
                @NamedQuery(name = "findAllDispatcher", query = "from Dispatcher "),
                @NamedQuery(name = "findDispatcherByName", query = "from Dispatcher pers where pers.name = :name"),
        }
)
public class Dispatcher implements Serializable {

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
    private boolean inApel;

    @Column
    private Integer salary;


}
