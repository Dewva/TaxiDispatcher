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
import java.util.Date;
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
                @NamedQuery(name = "findProfitById", query = "from Profit pers where pers.id = :id"),
                @NamedQuery(name = "findAllProfit", query = "from Profit "),
                @NamedQuery(name = "findProfitByDate", query = "from Profit pers where pers.data = :date"),
        }
)
public class Profit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date data;

    @Column
    private Integer incasare;

    @Column
    private Integer cheltuieli;

    @Column
    private Integer profit;


}
